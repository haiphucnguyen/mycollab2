package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.vaadin.ui.DateSelectionField;
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
public class CampaignSimpleSearchPanel extends
		GenericSearchPanel<CampaignSearchCriteria> {
	
	private CampaignSearchCriteria searchCriteria;	
	private TextField textValueField;
    private GridLayout layoutSearchPane;
    private DateSelectionField dateSearchField;
    
    @Override
	public void attach() {
		super.attach();
		createBasicSearchLayout();
	}
    
    private void createBasicSearchLayout() {
    	  layoutSearchPane = new GridLayout(3, 2);
          layoutSearchPane.setSpacing(true);

          final ValueComboBox group = new ValueComboBox(false, new String[]{
                      "Campaign Name", "Start Date", "End Date"});
          group.select("Campaign Name");
          group.setImmediate(true);
          group.addListener(new Property.ValueChangeListener() {
              @Override
              public void valueChange(ValueChangeEvent event) {
                  removeComponents();
                  String searchType = (String) group.getValue();
                  if (searchType.equals("Campaign Name")) {
                      addTextFieldSearch();
                  } else if (searchType.equals("Start Date")) {
                      addDateFieldSearch();
                  } else if (searchType.equals("End Date")) {
                      addDateFieldSearch();
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
                  searchCriteria = new CampaignSearchCriteria();
                  searchCriteria.setSaccountid(new NumberSearchField(
                          SearchField.AND, AppContext.getAccountId()));

                  String searchType = (String) group.getValue();
                  if (StringUtil.isNotNullOrEmpty(searchType)) {

                      if (textValueField != null) {
                          String strSearch = (String) textValueField.getValue();
                          if (StringUtil.isNotNullOrEmpty(strSearch)) {

                              if (searchType.equals("Campaign Name")) {
                                  searchCriteria
                                          .setCampaignName(new StringSearchField(
                                          SearchField.AND, strSearch));
                              } else if (searchType.equals("Email")) {
                              } else if (searchType.equals("Phone")) {
                              }
                          }
                      }

                      if (dateSearchField != null) {
                          SearchField searchDate = dateSearchField.getValue();
                          if (searchType.equals("Start Date")) {
                              if (searchDate != null
                                      && (searchDate instanceof DateSearchField)) {
                                  searchCriteria
                                          .setStartDate((DateSearchField) searchDate);
                              } else if (searchDate != null
                                      && (searchDate instanceof RangeDateSearchField)) {
                                  searchCriteria
                                          .setStartDateRange((RangeDateSearchField) searchDate);
                              }
                          } else if (searchType.equals("End Date")) {
                              if (searchDate != null
                                      && (searchDate instanceof DateSearchField)) {
                                  searchCriteria
                                          .setEndDate((DateSearchField) searchDate);
                              } else if (searchDate != null
                                      && (searchDate instanceof RangeDateSearchField)) {
                                  searchCriteria
                                          .setEndDateRange((RangeDateSearchField) searchDate);
                              }
                          }
                      }
                  }
                  CampaignSimpleSearchPanel.this.notifySearchHandler(searchCriteria);
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

    private void addDateFieldSearch() {
        dateSearchField = new DateSelectionField();
        dateSearchField.setDateFormat(AppContext.getDateFormat());
        layoutSearchPane.addComponent(dateSearchField, 0, 0);
        layoutSearchPane.setComponentAlignment(dateSearchField, Alignment.MIDDLE_CENTER);
    }

    private void removeComponents() {
        layoutSearchPane.removeComponent(0, 0);
        textValueField = null;
        dateSearchField = null;
    }

}
