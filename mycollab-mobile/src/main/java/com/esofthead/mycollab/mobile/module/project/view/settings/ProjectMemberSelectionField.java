package com.esofthead.mycollab.mobile.module.project.view.settings;

import com.esofthead.mycollab.mobile.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.mobile.ui.AbstractSelectionCustomField;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.vaadin.data.Property;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.0
 * 
 */
public class ProjectMemberSelectionField extends
		AbstractSelectionCustomField<String, SimpleProjectMember> implements
		FieldSelection<SimpleProjectMember> {

	private static final long serialVersionUID = 1L;

	public ProjectMemberSelectionField() {
		super(ProjectMemberSelectionView.class);
	}

	@Override
	public void setPropertyDataSource(Property newDataSource) {
		Object value = newDataSource.getValue();
		if (value instanceof String) {
			setMemberByVal((String) value);
		}
		super.setPropertyDataSource(newDataSource);
	}

	private void setMemberByVal(String value) {
		ProjectMemberService service = ApplicationContextUtil
				.getSpringBean(ProjectMemberService.class);
		SimpleProjectMember member = service.findMemberByUsername(value,
				CurrentProjectVariables.getProjectId(),
				AppContext.getAccountId());
		if (member != null) {
			setInternalMember(member);
		}
	}

	private void setInternalMember(SimpleProjectMember member) {
		this.beanItem = member;
		navButton.setCaption(member.getDisplayName());
	}

	@Override
	public Class<String> getType() {
		return String.class;
	}

	@Override
	public void fireValueChange(SimpleProjectMember data) {
		setInternalMember(data);
		setInternalValue(data.getUsername());
	}

}
