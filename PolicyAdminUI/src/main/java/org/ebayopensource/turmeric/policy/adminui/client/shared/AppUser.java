/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.shared;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import org.ebayopensource.turmeric.policy.adminui.client.model.AuthenticationState;
import org.ebayopensource.turmeric.policy.adminui.client.model.UserAction;

/**
 * The Class AppUser.
 */
public class AppUser implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8423602941996307213L;
	
	/** The username. */
	private String username;
	
	/** The token. */
	private String token;
	
	/** The password. */
	private String password;
	
	/** The domain. */
	private String domain;
	
	/** The admin user. */
	private Boolean adminUser;
	
	/** The authentication state. */
	private AuthenticationState authenticationState;
	
	/** The roles. */
	private Collection<AppUserRole> roles;
	
	/** The user. */
	private static AppUser user;
	
	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public static AppUser getUser () {
	    return user;
	}
	
	
	
	public static void logout () {
	    user = null;
	}
	
	
	public static AppUser fromCookie (String cookie) {
	    if (user != null)
	        logout();
	    if (cookie == null)
	        return null;

	    //parse cookie
	    String[] split = cookie.split("\\|");

	    if (split == null)
	        return null;
	    if (split.length < 3)
	        return null;

	   return newAppUser(split[0], split[1], split[2]);
	}
	
	public static String toCookie () {
	    if (user == null)
	        return null;
	    
	    return user.getUsername()+"|"+user.getPassword() +"|"+user.getDomain()+"|"+user.isAdminUser();
	}
	
	public Boolean isAdminUser() {
        return adminUser;
    }



    public static AppUser newAppUser (String login, String credential, String domain) {
	    if (user != null)
	        logout();
	    
	    user = new AppUser();
	    user.setUsername(login);
	    user.setPassword(credential);
	    user.setDomain(domain);
	    user.setAdminUser(Boolean.FALSE);
	    return user;
	}
	
	
	/**
	 * Sets the admin user.
	 *
	 * @param value the new admin user
	 */
	public void setAdminUser(Boolean value) {
       this.adminUser = value;
    }



    private AppUser() {
		authenticationState = AuthenticationState.Unauthenticated;
		roles = Collections.emptyList();
	}
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	
	/**
	 * Sets the token.
	 *
	 * @param token the new token
	 */
	public void setToken(String token) {
		this.token = token;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param pwd the new password
	 */
	public void setPassword(String pwd) {
	    this.password = pwd;
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
	    return this.password;
	}
	
	/**
	 * Gets the authentication state.
	 *
	 * @return the authentication state
	 */
	public AuthenticationState getAuthenticationState() {
		return authenticationState;
	}
	
	/**
	 * Sets the authentication state.
	 *
	 * @param authenticationState the new authentication state
	 */
	public void setAuthenticationState(AuthenticationState authenticationState) {
		this.authenticationState = authenticationState;
	}
	
	/**
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	public Collection<AppUserRole> getRoles() {
		return roles;
	}
	
	/**
	 * Sets the roles.
	 *
	 * @param roles the new roles
	 */
	public void setRoles(Collection<AppUserRole> roles) {
		this.roles = roles;
	}

	/**
	 * Sets the domain.
	 *
	 * @param domain the new domain
	 */
	public void setDomain(String domain) {
        this.domain = domain;
    }



    /**
     * Gets the domain.
     *
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }



    @Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("username {").append(username).append("}");
		buffer.append(" token {").append(token).append("}");
		buffer.append(" authenticationState {").append(authenticationState).append("}");
		if (roles != null && roles.size() > 0) {
			for (AppUserRole role : roles) {
				buffer.append(" role {").append(role).append("}");
			}
		}
		
		return buffer.toString();
	}
}
