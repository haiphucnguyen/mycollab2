package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class CaseSimpleSearchPanel extends
		GenericSearchPanel<CaseSearchCriteria> {
	
	private CaseSearchCriteria searchCriteria;
	
	private TextField textValueField;
    private UserComboBox userBox;
    private GridLayout layoutSearchPane;
    
    @Override
	public void attach() {
		super.attach();
		this.setHeight("32px");
		createBasicSearchLayout();
	}
    
    private void createBasicSearchLayout() {
    	 layoutSearchPane = new GridLayout(3, 3);
         layoutSearchPane.setSpacing(true);

         final ValueComboBox group = new ValueComboBox(false, new String[]{
                     "Subject", "Account Name", "Status", "Assigned to"});
         group.select("Name");
         group.setImmediate(true);
         group.addListener(new Property.ValueChangeListener() {
             @Override
             public void valueChange(ValueChangeEvent event) {
                 removeComponents();
                 String searchType = (String) group.getValue();
                 if (searchType.equals("Subject")) {
                     addTextFieldSearch();
                 } else if (searchType.equals("Account Name")) {
                     addTextFieldSearch();
                 } else if (searchType.equals("Status")) {
                     addTextFieldSearch();
                 } else if (searchType.equals("Assigned to")) {
                     addUserListSelectField();
                 }
             }
         });

         layoutSearchPane.addComponent(group, 1, 0);
         layoutSearchPane.setComponentAlignment(group, Alignment.MIDDLE_CENTER);
         addTextFieldSearch();

         Button searchBtn = new Button("Search");
         searchBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
         searchBtn.addListener(new Button.ClickListener() {
             @Override
             public void buttonClick(ClickEvent event) {
                 searchCriteria = new CaseSearchCriteria();
                 searchCriteria.setSaccountid(new NumberSearchField(
                         SearchField.AND, AppContext.getAccountId()));

                 String searchType = (String) group.getValue();
                 if (StringUtil.isNotNullOrEmpty(searchType)) {

                     if (textValueField != null) {
                         String strSearch = (String) textValueField.getValue();
                         if (StringUtil.isNotNullOrEmpty(strSearch)) {

                             if (searchType.equals("Subject")) {
                                 searchCriteria
                                         .setSubject(new StringSearchField(
                                         SearchField.AND, strSearch));
                             } else if (searchType.equals("Account Name")) {
                                 searchCriteria
                                         .setAccountName(new StringSearchField(
                                         SearchField.AND, strSearch));
                             } else if (searchType.equals("Status")) {
                                 searchCriteria
                                         .setStatuses(new SetSearchField<String>(
                                         SearchField.AND,
                                         new String[]{strSearch}));
                             }
                         }
                     }

                     if (userBox != null) {
                         String user = (String) userBox.getValue();
                         if (StringUtil.isNotNullOrEmpty(user)) {
                             searchCriteria
                                     .setAssignUsers(new SetSearchField<String>(
                                     SearchField.AND,
                                     new String[]{user}));
                         }
                     }
                 }

                 CaseSimpleSearchPanel.this.notifySearchHandler(searchCriteria);
             }
         });
         layoutSearchPane.addComponent(searchBtn, 2, 0);
         layoutSearchPane.setComponentAlignment(searchBtn, Alignment.MIDDLE_CENTER);
         this.setCompositionRoot(layoutSearchPane);
    }

    private void addTextFieldSearch() {
        textValueField = new TextField();
        layoutSearchPane.addComponent(textValueField, 0, 0);
        layoutSearchPane.setComponentAlignment(textValueField, Alignment.MIDDLE_CENTER);
    }

    private void addUserListSelectField() {
        userBox = new UserComboBox();
        userBox.setImmediate(true);
        layoutSearchPane.addComponent(userBox, 0, 0);
        layoutSearchPane.setComponentAlignment(userBox, Alignment.MIDDLE_CENTER);
    }

    private void removeComponents() {
        layoutSearchPane.removeComponent(0, 0);
        userBox = null;
        textValueField = null;
    }

}
