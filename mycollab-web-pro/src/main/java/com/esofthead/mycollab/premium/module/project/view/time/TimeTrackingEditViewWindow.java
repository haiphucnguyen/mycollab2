package com.esofthead.mycollab.premium.module.project.view.time;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberSelectionBox;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.DateFieldExt;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class TimeTrackingEditViewWindow extends Window implements
		AssignmentSelectableComp {
	private static final long serialVersionUID = 1L;

	private CheckBox isBillableCheckBox;
	private ProjectMemberSelectionBox projectMemberSelectionBox;
	private RichTextArea descArea;
	private TimeTrackingListView parentView;
	private ProjectGenericTask selectionTask;
	private HorizontalLayout taskLayout;
	private DateFieldExt dateField;
	private TextField timeField;
	private SimpleItemTimeLogging item;
	private ObjectProperty<Double> property;

	public TimeTrackingEditViewWindow(TimeTrackingListView view,
			SimpleItemTimeLogging itemInput) {
		this.item = itemInput;
		this.setWidth("800px");
		this.setModal(true);
		this.setResizable(false);
		this.parentView = view;

		this.setCaption(AppContext
				.getMessage(TimeTrackingI18nEnum.DIALOG_LOG_TIME_ENTRY_TITLE));

		dateField = new DateFieldExt("Select date:", this.item.getLogforday());

		property = new ObjectProperty<>(item.getLogvalue());
		timeField = new TextField("Hours:", property);

		projectMemberSelectionBox = new ProjectMemberSelectionBox();
		projectMemberSelectionBox.setValue(this.item.getLoguser());
		projectMemberSelectionBox.setCaption(AppContext
				.getMessage(TimeTrackingI18nEnum.FORM_WHO));

		isBillableCheckBox = new CheckBox();
		isBillableCheckBox.setValue(this.item.getIsbillable());

		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);
		content.setMargin(true);
		HorizontalLayout grid = new HorizontalLayout();
		grid.setSpacing(true);

		HorizontalLayout isBillable = new HorizontalLayout();
		isBillable.setSpacing(true);
		isBillable.setDefaultComponentAlignment(Alignment.BOTTOM_LEFT);
		Label billableTitle = new Label(
				AppContext.getMessage(TimeTrackingI18nEnum.FORM_IS_BILLABLE));
		isBillable.addComponent(billableTitle);
		isBillable.addComponent(isBillableCheckBox);
		isBillable.setCaption("");

		grid.addComponent(projectMemberSelectionBox);
		grid.addComponent(dateField);
		grid.addComponent(timeField);
		grid.addComponent(isBillable);

		content.addComponent(grid);

		Label descriptionLbl = new Label(
				AppContext.getMessage(GenericI18Enum.FORM_DESCRIPTION));
		content.addComponent(descriptionLbl);

		descArea = new RichTextArea();
		descArea.setValue(this.item.getNote());
		descArea.setWidth("100%");
		content.addComponent(descArea);
		HorizontalLayout footer = new HorizontalLayout();
		taskLayout = new HorizontalLayout();
		taskLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
		taskLayout.setSpacing(true);
		createLinkTaskButton();

		if (this.item.getType() != null && this.item.getTypeid() != null) {
			ProjectGenericTask tempSelectionTask = new ProjectGenericTask();
			tempSelectionTask.setType(this.item.getType());
			tempSelectionTask.setTypeId(this.item.getTypeid());
			String name = new GenericTaskDetailMapper(tempSelectionTask.getType(),
					tempSelectionTask.getTypeId()).getName();

			tempSelectionTask.setName(name);
			updateLinkTask(tempSelectionTask);
		}

		footer.addComponent(taskLayout);

		HorizontalLayout controlsLayout = new HorizontalLayout();
		controlsLayout.setSpacing(true);
		
		Button cancelBtn = new Button(
				AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						TimeTrackingEditViewWindow.this.close();
					}
				});
		cancelBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
		controlsLayout.addComponent(cancelBtn);
		
		Button saveBtn = new Button(
				AppContext.getMessage(TimeTrackingI18nEnum.BUTTON_LOG_TIME),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						saveTimeLoggingItems();
						TimeTrackingEditViewWindow.this.close();
					}
				});
		saveBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
		controlsLayout.addComponent(saveBtn);

		footer.addComponent(controlsLayout);
		footer.setSizeFull();
		footer.setComponentAlignment(controlsLayout, Alignment.TOP_RIGHT);
		content.addComponent(footer);
		this.setContent(content);
		this.center();
	}

	@Override
	public void updateLinkTask(ProjectGenericTask selectionTask) {
		this.selectionTask = selectionTask;
		if (this.selectionTask != null) {
			final String taskName = this.selectionTask.getName();
			taskLayout.removeAllComponents();

			Button detachTaskBtn = new Button(
					AppContext
							.getMessage(TimeTrackingI18nEnum.BUTTON_DETACH_TASK),
					new Button.ClickListener() {

						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							createLinkTaskButton();
							updateLinkTask(null);
						}
					});
			detachTaskBtn.setStyleName(UIConstants.THEME_RED_LINK);
			taskLayout.addComponent(detachTaskBtn);

			Label attachTaskBtn = new Label(
					StringUtils.trim(taskName, 40, true));

			attachTaskBtn.addStyleName("task-attached");
			attachTaskBtn.setWidth("300px");

			attachTaskBtn
					.setDescription(new ProjectGenericTaskTooltipGenerator(
							this.selectionTask.getType(), this.selectionTask
									.getTypeId()).getContent());
			taskLayout.addComponent(attachTaskBtn);
			this.selectionTask.getTypeId();
		}
	}

	private void createLinkTaskButton() {
		taskLayout.removeAllComponents();
		Button attachTaskBtn = new Button(
				AppContext.getMessage(TimeTrackingI18nEnum.BUTTON_LINK_TASK),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {

						ProjectGenericTaskSelectionWindow selectionTaskWindow = new ProjectGenericTaskSelectionWindow(
								TimeTrackingEditViewWindow.this);
						TimeTrackingEditViewWindow.this.getUI().addWindow(
								selectionTaskWindow);
					}
				});
		attachTaskBtn.addStyleName(UIConstants.THEME_GREEN_LINK);

		taskLayout.addComponent(attachTaskBtn);
	}

	private void saveTimeLoggingItems() {

		SimpleProjectMember user = (SimpleProjectMember) projectMemberSelectionBox
				.getValue();
		item.setCreateduser(AppContext.getUsername());
		item.setLoguser(user.getUsername());
		item.setLogUserFullName(user.getMemberFullName());
		if (user.getMemberAvatarId() != null) {
			item.setLogUserAvatarId(user.getMemberAvatarId());
		}
		item.setLogforday(dateField.getValue());
		item.setLogvalue(property.getValue());
		item.setIsbillable(isBillableCheckBox.getValue());
		item.setNote(descArea.getValue());
		item.setSaccountid(AppContext.getAccountId());
		if (selectionTask != null) {
			item.setType(selectionTask.getType());
			item.setTypeid(selectionTask.getTypeId());
			item.setSummary(selectionTask.getName());
		} else {
			item.setType(null);
			item.setTypeid(null);
			item.setSummary(null);
		}

		ItemTimeLoggingService itemTimeLoggingService = ApplicationContextUtil
				.getSpringBean(ItemTimeLoggingService.class);
		itemTimeLoggingService
				.updateWithSession(item, AppContext.getUsername());

		parentView.refresh();
	}
}
