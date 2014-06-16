package com.esofthead.mycollab.premium.module.project.view.standup;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.LabelLink;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.i18n.StandupI18nEnum;
import com.esofthead.mycollab.module.project.service.StandupReportService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class StandupMissingComp extends VerticalLayout {
	private static final long serialVersionUID = 5332956503787026253L;

	private VerticalLayout bodyWrap;
	private Label headerLbl;

	public StandupMissingComp() {
		super();
		this.setStyleName("standup-missing-comp");

		initUI();
	}

	protected void initUI() {
		CssLayout headerWrap = new CssLayout();
		headerWrap.setStyleName("header-wrap");

		headerLbl = new Label(
				AppContext
						.getMessage(StandupI18nEnum.STANDUP_MEMBER_NOT_REPORT));
		headerLbl.setSizeUndefined();
		headerWrap.addComponent(headerLbl);
		this.addComponent(headerWrap);

		bodyWrap = new VerticalLayout();
		bodyWrap.setStyleName("comp-body-wrap");
		this.addComponent(bodyWrap);
	}

	public void search(Date date) {
		bodyWrap.removeAllComponents();
		StandupReportService searchService = ApplicationContextUtil
				.getSpringBean(StandupReportService.class);
		List<SimpleUser> someGuys = searchService.findUsersNotDoReportYet(
				CurrentProjectVariables.getProjectId(), date,
				AppContext.getAccountId());
		if (someGuys.size() == 0) {
			bodyWrap.addComponent(new Label(AppContext
					.getMessage(StandupI18nEnum.STANDUP_NO_ITEM)));
		} else {
			Iterator<SimpleUser> iterator = someGuys.iterator();
			while (iterator.hasNext()) {
				final SimpleUser user = iterator.next();
				HorizontalLayout row = new HorizontalLayout();
				row.setWidth("100%");
				row.setStyleName("row-wrap");
				row.setMargin(true);
				row.setSpacing(true);
				row.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
				row.addComponent(UserAvatarControlFactory
						.createUserAvatarEmbeddedComponent(user.getAvatarid(),
								16));
				LabelLink userBtn = new LabelLink(user.getDisplayName(),
						ProjectLinkBuilder.generateProjectMemberFullLink(
								CurrentProjectVariables.getProjectId(),
								user.getUsername()));
				userBtn.setStyleName("link");
				row.addComponent(userBtn);
				row.setExpandRatio(userBtn, 1.0f);
				bodyWrap.addComponent(row);
			}
		}
	}

}
