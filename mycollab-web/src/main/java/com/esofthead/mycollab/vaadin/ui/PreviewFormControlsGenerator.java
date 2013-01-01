package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;

public class PreviewFormControlsGenerator<T> {
    
    private AdvancedPreviewBeanForm<T> previewForm;
    
    public PreviewFormControlsGenerator(AdvancedPreviewBeanForm<T> editForm) {
        this.previewForm = editForm;
    }
    
    public HorizontalLayout createButtonControls() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setStyleName("addNewControl");
        layout.setWidth("100%");
        
        HorizontalLayout editButtons = new HorizontalLayout();
        editButtons.setSpacing(true);
        
        Button editBtn = new Button(GenericForm.EDIT_ACTION,
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;
                    
                    @Override
                    public void buttonClick(ClickEvent event) {
                        @SuppressWarnings("unchecked")
                        T item = ((BeanItem<T>) previewForm.getItemDataSource())
                                .getBean();
                        previewForm.fireEditForm(item);
                    }
                });
        editBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        editButtons.addComponent(editBtn);
        editButtons.setComponentAlignment(editBtn, Alignment.MIDDLE_CENTER);
        
        Button deleteBtn = new Button(GenericForm.DELETE_ACTION,
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;
                    
                    @Override
                    public void buttonClick(ClickEvent event) {
                        @SuppressWarnings("unchecked")
                        T item = ((BeanItem<T>) previewForm.getItemDataSource())
                                .getBean();
                        previewForm.fireDeleteForm(item);
                    }
                });
        deleteBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        editButtons.addComponent(deleteBtn);
        editButtons.setComponentAlignment(deleteBtn, Alignment.MIDDLE_CENTER);
        
        Button cloneBtn = new Button(GenericForm.CLONE_ACTION,
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;
                    
                    @Override
                    public void buttonClick(ClickEvent event) {
                        @SuppressWarnings("unchecked")
                        T item = ((BeanItem<T>) previewForm.getItemDataSource())
                                .getBean();
                        previewForm.fireCloneForm(item);
                    }
                });
        cloneBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        editButtons.addComponent(cloneBtn);
        editButtons.setComponentAlignment(cloneBtn, Alignment.MIDDLE_CENTER);
        
        layout.addComponent(editButtons);
        layout.setComponentAlignment(editButtons, Alignment.MIDDLE_CENTER);
        layout.setExpandRatio(editButtons, 1.0f);
        
        Button historyBtn = new Button(null, new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                previewForm.showHistory();
            }
        });
        historyBtn.setIcon(new ThemeResource("icons/16/history.png"));
        historyBtn.setStyleName("link");
        layout.addComponent(historyBtn);
        layout.setComponentAlignment(historyBtn, Alignment.MIDDLE_RIGHT);
        
        Button printBtn = new Button(null, new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                previewForm.doPrint();
            }
        });
        
        printBtn.setIcon(new ThemeResource("icons/16/print.png"));
        printBtn.setStyleName("link");
        layout.addComponent(printBtn);
        layout.setComponentAlignment(printBtn, Alignment.MIDDLE_RIGHT);
        return layout;
    }
}
