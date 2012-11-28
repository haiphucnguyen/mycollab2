package com.esofthead.mycollab.module.project.ui.components;

import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.WizardStep;
import org.vaadin.teemu.wizards.event.WizardCancelledEvent;
import org.vaadin.teemu.wizards.event.WizardCompletedEvent;
import org.vaadin.teemu.wizards.event.WizardProgressListener;
import org.vaadin.teemu.wizards.event.WizardStepActivationEvent;
import org.vaadin.teemu.wizards.event.WizardStepSetChangedEvent;

import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.ui.events.ProjectEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.WizardExt;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class ProjectAddWindow extends Window {

	private Project project;
	private BeanItem<Project> beanItem;

	public ProjectAddWindow() {
		super();
		project = new Project();
		beanItem = new BeanItem<Project>(project);
		initComponents();
		center();
		this.setWidth("900px");
		this.setHeight("300px");
	}

	private void initComponents() {
		Wizard wizard = new WizardExt();
		wizard.addListener(new WizardProgressListener() {

			@Override
			public void wizardCompleted(WizardCompletedEvent event) {
				project.setSaccountid(AppContext.getAccountId());
				project.setOwner(AppContext.getUsername());
				ProjectAddWindow.this.getParent().removeWindow(
						ProjectAddWindow.this);

				ProjectService projectService = AppContext
						.getSpringBean(ProjectService.class);
				projectService.saveWithSession(project,
						AppContext.getUsername());

				EventBus.getInstance().fireEvent(
						new ProjectEvent.SaveProjectSucess(this, null));
			}

			@Override
			public void wizardCancelled(WizardCancelledEvent event) {
				ProjectAddWindow.this.getParent().removeWindow(
						ProjectAddWindow.this);
			}

			@Override
			public void stepSetChanged(WizardStepSetChangedEvent event) {

			}

			@Override
			public void activeStepChanged(WizardStepActivationEvent event) {
				WizardStep activeStep = event.getActivatedStep();
				if (activeStep instanceof ProjectAttributesStep
						|| activeStep instanceof ProjectBudgetStep) {
					event.getWizard().getFinishButton().setEnabled(true);
				}
			}
		});

		wizard.addStep(new ProjectBasicInformation());
		wizard.addStep(new ProjectAttributesStep());
		wizard.addStep(new ProjectBudgetStep());
		this.addComponent(wizard);
	}

	private class ProjectBasicInformation extends Form implements WizardStep {
		private GridFormLayoutHelper layoutHelper;

		public ProjectBasicInformation() {
			super();
			layoutHelper = new GridFormLayoutHelper(2, 3);
			this.setLayout(layoutHelper.getLayout());
			this.setFormFieldFactory(new DefaultEditFormFieldFactory() {
				@Override
				protected Field onCreateField(Item item, Object propertyId,
						Component uiContext) {
					if (propertyId.equals("projecttype")) {
						return new ProjectTypeComboBox();
					} else if (propertyId.equals("description")) {
						TextArea textArea = new TextArea();
						textArea.setWidth("400px");
						return textArea;
					}

					return null;
				}
			});
			this.setItemDataSource(beanItem);
		}

		@Override
		protected void attachField(Object propertyId, Field field) {
			if (propertyId.equals("name")) {
				layoutHelper.addComponent(field, "Project Name", 0, 0);
			} else if (propertyId.equals("homepage")) {
				layoutHelper.addComponent(field, "Home Page", 1, 0);
			} else if (propertyId.equals("shortname")) {
				layoutHelper.addComponent(field, "Short Name", 0, 1);
			} else if (propertyId.equals("projecttype")) {
				layoutHelper.addComponent(field, "Type", 1, 1);
			} else if (propertyId.equals("description")) {
				layoutHelper.addComponent(field, "Description", 0, 2, 1, 2);
			}
		}

		@Override
		public String getTitle() {
			return "Project Information";
		}

		@Override
		public Component getContent() {
			return this;
		}

		@Override
		public boolean onAdvance() {
			return true;
		}

		@Override
		public boolean onBack() {
			return true;
		}
	}

	private class ProjectAttributesStep extends Form implements WizardStep {

		private GridFormLayoutHelper layoutHelper;

		private ProjectAttributesStep() {
			super();
			layoutHelper = new GridFormLayoutHelper(2, 3);
			this.setLayout(layoutHelper.getLayout());
			this.setFormFieldFactory(new DefaultEditFormFieldFactory());
			this.setItemDataSource(beanItem);
		}

		@Override
		protected void attachField(Object propertyId, Field field) {
			if (propertyId.equals("planstartdate")) {
				layoutHelper.addComponent(field, "Planned Start Date", 0, 0);
			} else if (propertyId.equals("planenddate")) {
				layoutHelper.addComponent(field, "Planned End Date", 0, 1);
			} else if (propertyId.equals("currencyid")) {
				layoutHelper.addComponent(field, "Currency", 0, 2);
			} else if (propertyId.equals("targetbudget")) {
				layoutHelper.addComponent(field, "Target Budget", 1, 0);
			} else if (propertyId.equals("defaultbillingrate")) {
				layoutHelper.addComponent(field, "Billing Rate", 1, 1);
			} else if (propertyId.equals("defaultovertimebillingrate")) {
				layoutHelper.addComponent(field, "Overtime Billing Rate", 1, 2);
			}
		}

		@Override
		public String getTitle() {
			return "Project Attributes";
		}

		@Override
		public Component getContent() {
			return this;
		}

		@Override
		public boolean onAdvance() {
			return true;
		}

		@Override
		public boolean onBack() {
			return true;
		}
	}

	private class ProjectBudgetStep extends Form implements WizardStep {
		private GridFormLayoutHelper layoutHelper;

		public ProjectBudgetStep() {
			super();
			layoutHelper = new GridFormLayoutHelper(2, 3);
			this.setLayout(layoutHelper.getLayout());
			this.setFormFieldFactory(new DefaultEditFormFieldFactory() {
				@Override
				protected Field onCreateField(Item item, Object propertyId,
						Component uiContext) {

					return null;
				}
			});
			this.setItemDataSource(beanItem);
		}

		@Override
		protected void attachField(Object propertyId, Field field) {
			if (propertyId.equals("currencyid")) {
				layoutHelper.addComponent(field, "Currency", 0, 0);
			} else if (propertyId.equals("defaultbillingrate")) {
				layoutHelper.addComponent(field, "Billing Rate", 0, 1);
			} else if (propertyId.equals("defaultovertimebillingrate")) {
				layoutHelper.addComponent(field, "Overtime Billing Rate", 0, 2);
			} else if (propertyId.equals("targetbudget")) {
				layoutHelper.addComponent(field, "Target Budget", 1, 1);
			} else if (propertyId.equals("actualbudget")) {
				layoutHelper.addComponent(field, "Actual Budget", 1, 2);
			}
		}

		@Override
		public String getTitle() {
			return "Project Budget";
		}

		@Override
		public Component getContent() {
			return this;
		}

		@Override
		public boolean onAdvance() {
			return true;
		}

		@Override
		public boolean onBack() {
			return true;
		}

	}
}
