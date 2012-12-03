package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.events.CaseEvent;
import com.esofthead.mycollab.module.crm.ui.components.AdvancedSearchLayout;
import com.esofthead.mycollab.module.crm.ui.components.BasicSearchLayout;
import com.esofthead.mycollab.module.crm.ui.components.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.themes.Reindeer;

public class CaseSearchPanel extends GenericSearchPanel<CaseSearchCriteria> {
	private static final long serialVersionUID = 1L;

	private CaseSearchCriteria searchCriteria;
	
	public CaseSearchPanel() {
		super();
	}
	
	@Override
	public void attach() {
		super.attach();
		createBasicSearchLayout();
	}

	private void createBasicSearchLayout() {
		CaseBasicSearchLayout layout = new CaseBasicSearchLayout();
		this.setCompositionRoot(layout);
	}
	
	private void createAdvancedSearchLayout() {
		CaseAdvancedSearchLayout layout = new CaseAdvancedSearchLayout();
		this.setCompositionRoot(layout);
	}
	
	private class CaseAdvancedSearchLayout extends AdvancedSearchLayout {

		@Override
		public ComponentContainer constructHeader() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ComponentContainer constructBody() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ComponentContainer constructFooter() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

	private HorizontalLayout createSearchTopPanel() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		Label searchtitle = new Label("Search Cases");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);

		Button createAccountBtn = new Button("Create",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new CaseEvent.GotoAdd(this, null));
					}
				});
		createAccountBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
		createAccountBtn.setStyleName(BaseTheme.BUTTON_LINK);

		UiUtils.addComponent(layout, createAccountBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	private class CaseBasicSearchLayout extends BasicSearchLayout {
		private static final long serialVersionUID = 1L;
		
		private TextField subjectField;
		private CheckBox myItemCheckbox;

		public CaseBasicSearchLayout() {
			super();
		}

		@Override
		public ComponentContainer constructHeader() {
			return createSearchTopPanel();
		}

		@Override
		public ComponentContainer constructBody() {
			HorizontalLayout basicSearchBody = new HorizontalLayout();
			basicSearchBody.setSpacing(true);
			basicSearchBody.addComponent(new Label("Name"));
			subjectField = new TextField();
			subjectField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(basicSearchBody, subjectField,
					Alignment.MIDDLE_CENTER);
			myItemCheckbox = new CheckBox("My Items");
			UiUtils.addComponent(basicSearchBody, myItemCheckbox,
					Alignment.MIDDLE_CENTER);

			basicSearchBody.addComponent(new Button("Search",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							searchCriteria = new CaseSearchCriteria();
							searchCriteria.setSaccountid(new NumberSearchField(
									SearchField.AND, AppContext.getAccountId()));
							searchCriteria
									.setSubject(new StringSearchField(
											SearchField.AND, (String) subjectField
													.getValue()));
							CaseSearchPanel.this
									.notifySearchHandler(searchCriteria);
						}
					}));

			basicSearchBody.addComponent(new Button("Cancel",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							subjectField.setValue("");
						}
					}));

			Button advancedSearchBtn = new Button("Advanced Search",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							CaseSearchPanel.this
									.createAdvancedSearchLayout();
						}
					});
			advancedSearchBtn.setStyleName("link");
			UiUtils.addComponent(basicSearchBody, advancedSearchBtn,
					Alignment.MIDDLE_CENTER);
			return basicSearchBody;
		}
	}
}
