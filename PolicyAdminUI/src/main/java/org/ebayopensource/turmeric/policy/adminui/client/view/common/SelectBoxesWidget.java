/*******************************************************************************
 * Copyright (c) 2006-2010 eBay Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package org.ebayopensource.turmeric.policy.adminui.client.view.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ebayopensource.turmeric.policy.adminui.client.PolicyAdminUIUtil;
import org.ebayopensource.turmeric.policy.adminui.client.model.policy.PolicySubjectAssignment;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;

/**
 * SelectBoxesWidget.
 */
public class SelectBoxesWidget extends Composite {

    /** The avail table. */
    private CellTable<String> availTable;

    /** The avail key provider. */
    private ProvidesKey<String> availKeyProvider;

    /** The avail selection model. */
    private MultiSelectionModel<String> availSelectionModel;

    /** The avail selections. */
    private List<String> availSelections = new ArrayList<String>();

    /** The avail data provider. */
    private ListDataProvider<String> availDataProvider;

    /** The selected table. */
    private CellTable<String> selectedTable;

    /** The selected key provider. */
    private ProvidesKey<String> selectedKeyProvider;

    /** The selected selection model. */
    private MultiSelectionModel<String> selectedSelectionModel;

    /** The selected selections. */
    private List<String> selectedSelections = new ArrayList<String>();

    /** The selected data provider. */
    private ListDataProvider<String> selectedDataProvider;

    /** The avail pager. */
    private SimplePager availPager;

    /** The selected pager. */
    private SimplePager selectedPager;

    /** The panel. */
    private Panel panel;

    /** The grid. */
    protected Grid grid;

    /** The add button. */
    protected Button addButton;

    /** The del button. */
    protected Button delButton;

    /** The available label. */
    protected Label availableLabel;

    /** The selected label. */
    protected Label selectedLabel;

    /**
     * Instantiates a new select boxes widget.
     * 
     * @param availableName
     *            the available name
     * @param isAvailableMulti
     *            the is available multi
     * @param selectedName
     *            the selected name
     * @param isSelectedMulti
     *            the is selected multi
     */
    public SelectBoxesWidget(String availableName, boolean isAvailableMulti, String selectedName,
                    boolean isSelectedMulti) {
        configureAvailableTable();
        configureSelectedTable();

        panel = new SimplePanel();
        availableLabel = new Label(availableName);
        selectedLabel = new Label(selectedName);

        // arrows
        addButton = new Button(">>");
        delButton = new Button("<<");

        addButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                moveToSelected();
            }
        });

        delButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                moveToAvailable();
            }
        });

        grid = new Grid(3, 3);
        grid.setWidget(0, 0, availableLabel);
        grid.setWidget(0, 2, selectedLabel);
        grid.setWidget(1, 0, availTable);
        grid.setWidget(2, 0, availPager);
        grid.setWidget(2, 2, selectedPager);
        grid.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);

        Grid arrowGrid = new Grid(2, 1);
        arrowGrid.setWidget(0, 0, addButton);
        arrowGrid.setWidget(1, 0, delButton);
        arrowGrid.setWidth("80px");
        arrowGrid.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
        arrowGrid.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
        grid.setWidget(1, 1, arrowGrid);
        grid.getCellFormatter().setVerticalAlignment(1, 1, HasVerticalAlignment.ALIGN_MIDDLE);
        grid.getCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_CENTER);

        grid.setWidget(1, 2, selectedTable);
        grid.getCellFormatter().setHorizontalAlignment(1, 2, HasHorizontalAlignment.ALIGN_CENTER);
        panel.add(grid);
        initWidget(panel);
    }

    /**
     * Configure available table.
     */
    private void configureAvailableTable() {
        availTable = new CellTable<String>(availKeyProvider);
        availSelectionModel = new MultiSelectionModel<String>(availKeyProvider);
        availTable.setSelectionModel(availSelectionModel);
        availSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            public void onSelectionChange(SelectionChangeEvent event) {
                Set<String> x = availSelectionModel.getSelectedSet();
                availSelections.clear();
                if (x != null)
                    availSelections.addAll(x);
            }
        });
        availDataProvider = new ListDataProvider<String>();
        availDataProvider.addDataDisplay(availTable);

        availPager = new TurmericPager();
        availPager.setPageSize(5);
        availPager.setDisplay(availTable);

        // text column for type
        ClickableTextCell sbTypeCellClickable = new ClickableTextCell();
        Column<String, String> typeCol = new Column<String, String>(sbTypeCellClickable) {

            @Override
            public String getValue(String value) {
                return value;
            }
        };
        availTable.addColumn(typeCol, "");
    }

    /**
     * Configure selected table.
     */
    private void configureSelectedTable() {
        selectedTable = new CellTable<String>(selectedKeyProvider);
        selectedSelectionModel = new MultiSelectionModel<String>(selectedKeyProvider);
        selectedTable.setSelectionModel(selectedSelectionModel);
        selectedSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            public void onSelectionChange(SelectionChangeEvent event) {
                Set<String> x = selectedSelectionModel.getSelectedSet();
                selectedSelections.clear();
                if (x != null)
                    selectedSelections.addAll(x);
            }
        });
        selectedDataProvider = new ListDataProvider<String>();
        selectedDataProvider.addDataDisplay(selectedTable);

        selectedPager = new TurmericPager();
        selectedPager.setPageSize(5);
        selectedPager.setDisplay(selectedTable);

        // text column for type
        ClickableTextCell sbTypeCellClickable = new ClickableTextCell();
        Column<String, String> typeCol = new Column<String, String>(sbTypeCellClickable) {

            @Override
            public String getValue(String arg0) {
                // TODO Auto-generated method stub
                return arg0;
            }
        };
        selectedTable.addColumn(typeCol, "");
    }

    /**
     * Gets the selections.
     * 
     * @return the selections
     */
    public List<String> getSelections() {
        return selectedDataProvider.getList();
    }

    /**
     * Gets the availables.
     * 
     * @return the availables
     */
    public List<String> getAvailables() {
        return availDataProvider.getList();
    }

    /**
     * Sets the availables.
     * 
     * @param availables
     *            the new availables
     */
    public void setAvailables(List<String> availables) {
        availDataProvider.getList().clear();
        availPager.setPageSize(5);
        availPager.setRangeLimited(true);
        if (availables != null && availables.size() > 0) {
            for (String s : availables) {
                if (!selectedDataProvider.getList().contains(s))
                    availDataProvider.getList().add(s);
            }
        }
        availTable.redraw();
    }

    /**
     * Sets the with for operations.
     */
    public void setWithForOperations() {

    }

    /**
     * Sets the selections.
     * 
     * @param selects
     *            the new selections
     */
    public void setSelections(List<String> selects) {
        selectedDataProvider.getList().clear();
        selectedPager.setPageSize(5);
        selectedPager.setRangeLimited(true);
        if (selects != null && selects.size() > 0) {
            for (String s : selects) {
                if (!availDataProvider.getList().contains(s))
                    selectedDataProvider.getList().add(s);
            }
        }
        selectedTable.redraw();
    }

    /**
     * Move to available.
     */
    protected void moveToAvailable() {
        if (!selectedSelections.isEmpty()) {
            List<String> removedElements = new ArrayList<String>();
            for (String selectedElemnt : selectedSelections) {
                availDataProvider.getList().add(selectedElemnt);
                removedElements.add(selectedElemnt);
            }
            selectedDataProvider.getList().removeAll(removedElements);
            selectedSelections.clear();
        }
    }

    /**
     * Move to selected.
     */
    protected void moveToSelected() {
        if (!availSelections.isEmpty()) {
            List<String> removedElements = new ArrayList<String>();
            for (String selectedElemnt : availSelections) {
                selectedDataProvider.getList().add(selectedElemnt);
                removedElements.add(selectedElemnt);
            }
            availDataProvider.getList().removeAll(removedElements);
            availSelections.clear();
        }
    }

}
