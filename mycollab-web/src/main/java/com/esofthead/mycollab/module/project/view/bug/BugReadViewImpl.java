package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.crm.ui.components.AddViewLayout;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;

@ViewComponent
public class BugReadViewImpl extends AbstractView implements BugReadView {

    private static final long serialVersionUID = 1L;
    private SimpleBug bug;
    private BugPreviewForm previewForm;

    public BugReadViewImpl() {
        super();

        previewForm = new BugPreviewForm();
        this.addComponent(previewForm);
    }

    @Override
    public void previewItem(SimpleBug item) {
        bug = item;
        previewForm.setItemDataSource(new BeanItem<SimpleBug>(item));
    }

    @Override
    public SimpleBug getItem() {
        return bug;
    }

    @Override
    public void doPrint() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private class BugPreviewForm extends AdvancedPreviewBeanForm<SimpleBug> {

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new EditFormFieldFactory());
            super.setItemDataSource(newDataSource);
        }

        private class FormLayoutFactory implements IFormLayoutFactory {

            private GridFormLayoutHelper informationLayout;

            @Override
            public Layout getLayout() {
                AddViewLayout taskListAddLayout = new AddViewLayout("New Task List", new ThemeResource("icons/48/project/bug.png"));

                HorizontalLayout topPanel = new HorizontalLayout();
                topPanel.setSpacing(true);
                topPanel.setMargin(true);
                topPanel.setWidth("100%");
                
                HorizontalLayout buttonControls = new HorizontalLayout();
                buttonControls.setSpacing(true);
                
                Button assignBtn = new Button("Assign", new Button.ClickListener() {

                    @Override
                    public void buttonClick(ClickEvent event) {
                        
                    }
                });
                assignBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
                buttonControls.addComponent(assignBtn);
                buttonControls.setComponentAlignment(assignBtn, Alignment.MIDDLE_CENTER);
                
                Button commentBtn = new Button("Comment", new Button.ClickListener() {

                    @Override
                    public void buttonClick(ClickEvent event) {
                        
                    }
                });
                commentBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
                buttonControls.addComponent(commentBtn);
                buttonControls.setComponentAlignment(commentBtn, Alignment.MIDDLE_CENTER);
                
                Button editBtn = new Button("Edit", new Button.ClickListener() {

                    @Override
                    public void buttonClick(ClickEvent event) {
                        
                    }
                });
                editBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
                buttonControls.addComponent(editBtn);
                buttonControls.setComponentAlignment(editBtn, Alignment.MIDDLE_CENTER);
                
                Button deleteBtn = new Button("Delete", new Button.ClickListener() {

                    @Override
                    public void buttonClick(ClickEvent event) {
                        
                    }
                });
                deleteBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
                buttonControls.addComponent(deleteBtn);
                buttonControls.setComponentAlignment(deleteBtn ,Alignment.MIDDLE_CENTER);
                
                topPanel.addComponent(buttonControls);
                topPanel.setComponentAlignment(buttonControls, Alignment.MIDDLE_CENTER);
                
                
                taskListAddLayout.addTopControls(topPanel);
                
                informationLayout = new GridFormLayoutHelper(4, 9);
                taskListAddLayout.addBody(informationLayout.getLayout());
                return taskListAddLayout;
            }

            @Override
            public void attachField(Object propertyId, Field field) {
                if (propertyId.equals("summary")) {
                    informationLayout.addComponent(field, "Summary", 0, 0, 4, "100%");
                } else if (propertyId.equals("description")) {
                    informationLayout.addComponent(field, "Description", 0, 1, 4, "100%");
                } else if (propertyId.equals("status")) {
                    informationLayout.addComponent(field, "Status", 0, 2);
                } else if (propertyId.equals("priority")) {
                    informationLayout.addComponent(field, "Priority", 1, 2);
                } else if (propertyId.equals("severity")) {
                    informationLayout.addComponent(field, "Severity", 2, 2);
                } else if (propertyId.equals("duedate")) {
                    informationLayout.addComponent(field, "Due Date", 3, 2);
                } else if (propertyId.equals("loguserFullName")) {
                    informationLayout.addComponent(field, "Logged by", 0, 4, 2, "100%");
                } else if (propertyId.equals("assignuserFullName")) {
                    informationLayout.addComponent(field, "Assigned to", 2, 4, 2, "100%");
                } else if (propertyId.equals("components")) {
                    informationLayout.addComponent(field, "Components", 0, 5, 4, "100%");
                } else if (propertyId.equals("affectedVersions")) {
                    informationLayout.addComponent(field, "Affected Versions", 0, 6, 4, "100%");
                } else if (propertyId.equals("fixedVersions")) {
                    informationLayout.addComponent(field, "Fixed Versions", 0, 7, 4, "100%");
                } else if (propertyId.equals("attachments")) {
                    informationLayout.addComponent(field, "Attachments", 0, 8, 4, "100%");
                }
            }
        }

        private class EditFormFieldFactory extends DefaultFormViewFieldFactory {

            private static final long serialVersionUID = 1L;

            @Override
            protected Field onCreateField(Item item, Object propertyId,
                    com.vaadin.ui.Component uiContext) {
                if (propertyId.equals("duedate")) {
                    return new FormDateViewField(bug.getDuedate());
                }
                return null;
            }
        }
    }
}
