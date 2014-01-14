package com.esofthead.mycollab.module.crm.view.opportunity;

import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleContactOpportunityRel;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.service.ContactOpportunityService;
import com.esofthead.mycollab.module.crm.view.contact.ContactSelectionField;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout2;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
@ViewComponent
public class ContactRoleEditViewImpl extends AbstractPageView implements
		ContactRoleEditView {
	private static final long serialVersionUID = 1L;

	private Opportunity opportunity;

	@Override
	public void display(Opportunity opportunity) {
		this.opportunity = opportunity;
		this.removeAllComponents();

		AddViewLayout2 previewLayout = new AddViewLayout2(
				"Add or Edit Contact Roles",
				MyCollabResource.newResource("icons/22/crm/contact.png"));
		this.addComponent(previewLayout);

		VerticalLayout informationLayout = new VerticalLayout();
		informationLayout.addStyleName("main-info");

		ComponentContainer actionControls = createButtonControls();
		if (actionControls != null) {
			actionControls.addStyleName("control-buttons");
			informationLayout.addComponent(actionControls);
			informationLayout.setComponentAlignment(actionControls,
					Alignment.MIDDLE_CENTER);
		}

		previewLayout.addBody(informationLayout);
		previewLayout.addBody(constructContactOpportunityList());

		Button addMoreContactRolesBtn = new Button("Add more contact roles",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						// TODO Auto-generated method stub

					}
				});
		addMoreContactRolesBtn.setStyleName(UIConstants.THEME_BLUE_LINK);

		HorizontalLayout buttonControls = new HorizontalLayout();
		buttonControls.addComponent(addMoreContactRolesBtn);

		previewLayout.addBody(buttonControls);
	}

	private VerticalLayout constructContactOpportunityList() {
		VerticalLayout layout = new VerticalLayout();
		ContactOpportunityService contactOppoService = ApplicationContextUtil
				.getSpringBean(ContactOpportunityService.class);
		ContactSearchCriteria criteria = new ContactSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setOpportunityId(new NumberSearchField(SearchField.AND,
				opportunity.getId()));
		List<SimpleContactOpportunityRel> contactOppoRels = contactOppoService
				.findPagableListByCriteria(new SearchRequest<ContactSearchCriteria>(
						criteria));
		if (contactOppoRels != null && contactOppoRels.size() > 0) {
			for (SimpleContactOpportunityRel contactOppoRel : contactOppoRels) {
				ContactRoleRowComp rowComp = new ContactRoleRowComp(
						contactOppoRel);
				layout.addComponent(rowComp);
			}
		}
		return layout;
	}

	private ComponentContainer createButtonControls() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);
		layout.setWidth("100%");

		Button updateBtn = new Button("Update", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		});
		updateBtn.setIcon(MyCollabResource.newResource("icons/16/save.png"));
		updateBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		layout.addComponent(updateBtn);
		layout.setComponentAlignment(updateBtn, Alignment.MIDDLE_CENTER);

		Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				ViewState viewState = HistoryViewManager.back();

				if (viewState instanceof NullViewState) {
					EventBus.getInstance().fireEvent(
							new ContactEvent.GotoList(this, null));
				}

			}
		});
		cancelBtn.setIcon(MyCollabResource.newResource("icons/16/cancel.png"));
		cancelBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		layout.addComponent(cancelBtn);
		layout.setComponentAlignment(cancelBtn, Alignment.MIDDLE_CENTER);

		return layout;
	}

	private static class ContactRoleRowComp extends HorizontalLayout {
		private static final long serialVersionUID = 1L;

		public ContactRoleRowComp(SimpleContactOpportunityRel contactOpp) {
			ContactSelectionField contactField = new ContactSelectionField();
			this.addComponent(contactField);

			RoleDecisionComboBox roleBox = new RoleDecisionComboBox();
			this.addComponent(roleBox);
		}

	}

	private static class RoleDecisionComboBox extends ValueComboBox {
		private static final long serialVersionUID = 1L;

	}
}
