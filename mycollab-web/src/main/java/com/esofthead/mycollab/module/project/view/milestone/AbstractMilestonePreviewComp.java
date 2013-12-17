package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.ui.components.AbstractPreviewItemComp;
import com.esofthead.mycollab.module.project.ui.components.CommentDisplay;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class AbstractMilestonePreviewComp extends
		AbstractPreviewItemComp<SimpleMilestone> {
	private static final long serialVersionUID = 1L;

	protected CommentDisplay associateCommentListComp;

	protected MilestoneBugListComp associateBugListComp;

	protected MilestoneTaskGroupListComp associateTaskGroupListComp;

	public AbstractMilestonePreviewComp() {
		super(MyCollabResource
				.newResource("icons/22/project/menu_milestone.png"));
	}

	@Override
	protected void initRelatedComponents() {
		this.associateBugListComp = new MilestoneBugListComp();
		this.associateTaskGroupListComp = new MilestoneTaskGroupListComp();

	}

	@Override
	protected void onPreviewItem() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String initFormTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AdvancedPreviewBeanForm<SimpleMilestone> initPreviewForm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AbstractBeanFieldGroupViewFieldFactory<SimpleMilestone> initBeanFormFieldFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ComponentContainer createButtonControls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ComponentContainer createBottomPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	protected void displayBugs() {
		this.associateBugListComp.displayBugs(this.beanItem);
	}

	protected void displayComments() {
		this.associateCommentListComp.loadComments(CommentType.PRJ_MILESTONE,
				this.beanItem.getId());
	}

	protected void displayTaskGroups() {
		this.associateTaskGroupListComp.displayTakLists(this.beanItem);
	}
}
