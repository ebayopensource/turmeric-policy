/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ebayopensource.turmeric.policy.adminui.client.event.LoginEvent;
import org.ebayopensource.turmeric.policy.adminui.client.event.LoginEventHandler;
import org.ebayopensource.turmeric.policy.adminui.client.event.LoginFailureEvent;
import org.ebayopensource.turmeric.policy.adminui.client.event.LoginFailureEventHandler;
import org.ebayopensource.turmeric.policy.adminui.client.event.LoginSuccessEvent;
import org.ebayopensource.turmeric.policy.adminui.client.event.LoginSuccessEventHandler;
import org.ebayopensource.turmeric.policy.adminui.client.event.LogoutEvent;
import org.ebayopensource.turmeric.policy.adminui.client.event.LogoutEventHandler;
import org.ebayopensource.turmeric.policy.adminui.client.model.HistoryToken;
import org.ebayopensource.turmeric.policy.adminui.client.model.PolicyAdminUIService;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.OperationKey;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyEnforcementService;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyEnforcementService.VerifyAccessResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicyQueryService.FindSubjectGroupsResponse;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroup;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroupKey;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.SubjectGroupQuery;
import org.ebayopensource.turmeric.policy.adminui.client.presenter.MenuController;
import org.ebayopensource.turmeric.policy.adminui.client.presenter.Presenter;
import org.ebayopensource.turmeric.policy.adminui.client.presenter.SplashPresenter;
import org.ebayopensource.turmeric.policy.adminui.client.session.SessionInterface;
import org.ebayopensource.turmeric.policy.adminui.client.session.SessionInterfaceAsync;
import org.ebayopensource.turmeric.policy.adminui.client.session.SessionService;
import org.ebayopensource.turmeric.policy.adminui.client.shared.AppUser;
import org.ebayopensource.turmeric.policy.adminui.client.util.AppKeyUtil;
import org.ebayopensource.turmeric.policy.adminui.client.util.PresenterUtil;
import org.ebayopensource.turmeric.policy.adminui.client.view.SplashView;
import org.ebayopensource.turmeric.policy.adminui.client.view.policy.ApplicationMenuView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * The Class AppController.
 */
public class AppController implements Controller, ValueChangeHandler<String> {

    /** The event bus. */
    protected HandlerManager eventBus;
    
    /** The root container. */
    protected HasWidgets rootContainer;
    
    /** The presenters. */
    protected Map<String, Presenter> presenters = new HashMap<String, Presenter>();
    
    /** The service map. */
    protected Map<SupportedService, PolicyAdminUIService> serviceMap;

    private Timer sessionTimeoutResponseTimer;
    private SessionService sessionService;

    /**
     * Added to the first session timeout check to allow for startup time
     */
    private final int INITIAL_TIMEOUT_PAD = 30000;

    /**
     * Added to the session timeout check timer.
     */
    private final int TIMEOUT_PAD = 10000;
    
    /**
     * Instantiates a new app controller.
     *
     * @param eventBus the event bus
     * @param rootContainer the root container
     * @param serviceMap the service map
     */
    public AppController(HandlerManager eventBus, HasWidgets rootContainer,
                    Map<SupportedService, PolicyAdminUIService> serviceMap) {
        this.eventBus = eventBus;
        this.rootContainer = rootContainer;
        this.serviceMap = serviceMap;

        initPresenters();
    }

    /**
     * Start the application.
     */
    public void start() {
        bind();
        History.addValueChangeHandler(this);
        final HistoryToken token = HistoryToken.newHistoryToken();
        // See if a security cookie has been stored to identify the user
        AppUser user = AppUser.fromCookie(Cookies.getCookie(AppKeyUtil.COOKIE_SESSID_KEY));
        if (user != null) {
            // the user has been identified take them to the last page they were on
            // unless that was not saved
            if (token == null || token.getPresenterId() == null) {
                // no history, default landing page is dashboard
                History.newItem(HistoryToken.newHistoryToken(MenuController.PRESENTER_ID, null).toString());
            }

            History.fireCurrentHistoryState();
        }
        else {
            // user not identified, go to the "login" page
            PresenterUtil.forceRedirectToPresenter(token, presenters.get(SplashPresenter.SPLASH_ID));
        }
    }

    /* (non-Javadoc)
     * @see com.google.gwt.event.logical.shared.ValueChangeHandler#onValueChange(com.google.gwt.event.logical.shared.ValueChangeEvent)
     */
    public void onValueChange(ValueChangeEvent<String> event) {

        final HistoryToken token = HistoryToken.newHistoryToken(event.getValue());

        if (AppUser.getUser() != null) {
            // identified
            if (SplashPresenter.SPLASH_ID.equals(token.getPresenterId())) {
                return;
            }
        }
        else {
            // not identified, force to the "login" page
            if (!SplashPresenter.SPLASH_ID.equals(token.getPresenterId())) {
                return;
            }
        }

        selectPresenter(token);
    }

    private void bind() {
        // if login succeeded
        this.eventBus.addHandler(LoginSuccessEvent.TYPE, new LoginSuccessEventHandler() {
            public void onSuccess(AppUser arg) {

                AppController.this.rootContainer.clear();
                History.newItem(HistoryToken.newHistoryToken(MenuController.PRESENTER_ID, null).toString());
            }
        });

        // if login failed
        this.eventBus.addHandler(LoginFailureEvent.TYPE, new LoginFailureEventHandler() {
            public void onFailure() {
                SplashPresenter concreteSplashPresenter = (SplashPresenter) presenters.get(SplashPresenter.SPLASH_ID);
                concreteSplashPresenter.handleLoginErrorView();
            }
        });

        // logout
        this.eventBus.addHandler(LogoutEvent.TYPE, new LogoutEventHandler() {
            public void onLogout(LogoutEvent event) {

                Cookies.removeCookie(AppKeyUtil.COOKIE_SESSID_KEY);
                AppUser.logout();

                // Clean up
                cleanup();

                initPresenters();

                // Go back to the splash screen
                HistoryToken token = HistoryToken.newHistoryToken();
                PresenterUtil.forceRedirectToPresenter(token, presenters.get(SplashPresenter.SPLASH_ID));

                SplashPresenter concreteSplashPresenter = (SplashPresenter) presenters.get(SplashPresenter.SPLASH_ID);
                concreteSplashPresenter.handleLogoutSuccessView();
            }
        });

        // login event handler
        this.eventBus.addHandler(LoginEvent.TYPE, new LoginEventHandler() {
            public void onLogin(LoginEvent event) {
                // there is no service to authenticate the username/password
                // This info has to be presented on every request, so the only
                // time we find out if login/password is accepted is when we supply
                // it on a request.
                AppUser.newAppUser(event.getLogin(), event.getPassword(), event.getDomain());
                Cookies.removeCookie(AppKeyUtil.COOKIE_SESSID_KEY);

                Map<String, String> credentials = new HashMap<String, String>();
                credentials.put("X-TURMERIC-SECURITY-PASSWORD", AppUser.getUser().getPassword());
                OperationKey opKey = new OperationKey();

                opKey.setResourceName(PolicyEnforcementService.POLICY_SERVICE_NAME);
                opKey.setOperationName("");
                opKey.setResourceType("OBJECT");

                List<String> policyTypes = Collections.singletonList("AUTHZ");

                String[] subjectType = { "USER", AppUser.getUser().getUsername() };
                List<String[]> subjectTypes = Collections.singletonList(subjectType);

                PolicyEnforcementService enforcementService = (PolicyEnforcementService) serviceMap
                                .get(SupportedService.POLICY_ENFORCEMENT_SERVICE);

                enforcementService.verify(opKey, policyTypes, credentials, subjectTypes, null, null, null,
                                new AsyncCallback<VerifyAccessResponse>() {

                                    public void onFailure(Throwable arg) {
                                        AppController.this.eventBus.fireEvent(new LoginFailureEvent());
                                    }

                                    public void onSuccess(VerifyAccessResponse response) {
                                        PolicyQueryService policyService = (PolicyQueryService) serviceMap
                                                        .get(SupportedService.POLICY_QUERY_SERVICE);
                                        SubjectGroupQuery query = new SubjectGroupQuery();
                                        SubjectGroupKey key = new SubjectGroupKey();
                                        key.setType("USER");
                                        key.setName("Admin_Policy_SuperPolicy");
                                        query.setGroupKeys(Arrays.asList(key));
                                        query.setIncludeSubjects(true);
                                        policyService.findSubjectGroups(
                                                        query,
                                                        new AsyncCallback<PolicyQueryService.FindSubjectGroupsResponse>() {

                                                            @Override
                                                            public void onSuccess(FindSubjectGroupsResponse arg0) {
                                                                // arg0.getGroups().
                                                                boolean isAdmin = false;
                                                                if (arg0.getGroups() != null
                                                                                && !arg0.getGroups().isEmpty()) {
                                                                    SubjectGroup sgSuperPolicy = arg0.getGroups()
                                                                                    .get(0);
                                                                    if (sgSuperPolicy.getSubjects() != null
                                                                                    && !sgSuperPolicy.getSubjects()
                                                                                                    .isEmpty()) {
                                                                        isAdmin = sgSuperPolicy.getSubjects()
                                                                                        .contains(AppUser.getUser()
                                                                                                        .getUsername());
                                                                    }
                                                                }

                                                                AppUser.getUser().setAdminUser(isAdmin);
                                                            }

                                                            @Override
                                                            public void onFailure(Throwable arg0) {
                                                                AppUser.getUser().setAdminUser(false);
                                                            }
                                                        });

                                        //initSessionTimers();
                                        AppController.this.eventBus.fireEvent(new LoginSuccessEvent(AppUser.getUser()));

                                    }
                                });

            }
        });
    }

    private void initSessionTimers()
    {

    	sessionService = new SessionService();

        sessionService.getUserSessionTimeout(new AsyncCallback<Integer>()
        {
            public void onSuccess(Integer timeout)
            {
                sessionTimeoutResponseTimer = new Timer()
                {
                    @Override
                    public void run()
                    {
                        checkUserSessionAlive();
                    }
                };
                sessionTimeoutResponseTimer.schedule(timeout + INITIAL_TIMEOUT_PAD);
            }

            public void onFailure(Throwable caught)
            {
                displaySessionTimedOut();
            }
        });
        
    }

    private void checkUserSessionAlive()
    {
    	sessionService.getUserSessionTimeout(new AsyncCallback<Integer>()
        {
            public void onSuccess(Integer timeout)
            {
                sessionTimeoutResponseTimer.cancel();
                sessionTimeoutResponseTimer.schedule(timeout + TIMEOUT_PAD);
            }

            public void onFailure(Throwable caught)
            {
                displaySessionTimedOut();
            }
        });

    }

    private void displaySessionTimedOut()
    {
       Window.alert("Your session has timed out.");
       Window.Location.reload();
        
    }

    
    private void initPresenters() {
        Presenter presenter = null;
        // splash page
        presenter = new SplashPresenter(this.eventBus, new SplashView());
        addPresenter(presenter.getId(), presenter);
        // main page
        presenter = new MenuController(this.eventBus, rootContainer, new ApplicationMenuView(), serviceMap);
        addPresenter(presenter.getId(), presenter);
    }

    private void cleanup() {
        this.rootContainer.clear(); // get rid of whatever is being displayed
        this.presenters.clear();
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.Controller#addPresenter(java.lang.String, org.ebayopensource.turmeric.policy.adminui.client.presenter.Presenter)
     */
    public void addPresenter(String id, Presenter p) {
        this.presenters.put(id, p);
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.Controller#getPresenter(java.lang.String)
     */
    public Presenter getPresenter(String id) {
        return this.presenters.get(id);
    }

    /* (non-Javadoc)
     * @see org.ebayopensource.turmeric.policy.adminui.client.Controller#selectPresenter(org.ebayopensource.turmeric.policy.adminui.client.model.HistoryToken)
     */
    public void selectPresenter(HistoryToken token) {
        String presenterId = token != null ? token.getPresenterId() : null;
        Presenter presenter = null;
        presenter = this.presenters.get(presenterId);
        if (presenter != null) {
            presenter.go(this.rootContainer, token);
        }
        else {
            presenter = presenters.get(MenuController.PRESENTER_ID);
            if (presenter != null)
                presenter.go(this.rootContainer, token);
        }
    }
}
