package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.PopupView;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
public class LazyPopupView extends PopupView {
    public LazyPopupView(String valueAsHtml) {
        super(new PopupContent(valueAsHtml));
        addPopupVisibilityListener(new PopupVisibilityListener() {
            @Override
            public void popupVisibilityChange(PopupVisibilityEvent event) {
                if (event.isPopupVisible()) {
                    doShow();
                } else {
                    doHide();
                }
            }
        });
        this.setStyleName("block-popupedit");
    }

    final public MVerticalLayout getWrapContent() {
        PopupView.Content content = getContent();
        MVerticalLayout layout = (MVerticalLayout) content.getPopupComponent();
        return layout;
    }

    final public void setMinimizedValueAsHTML(String valueAsHtml) {
        PopupContent content = (PopupContent) getContent();
        content.setMinimizedValueAsHTML(valueAsHtml);
    }

    protected void doShow() {
    }

    protected void doHide() {
    }

    private static class PopupContent implements PopupView.Content {
        private String valueAsHtml;
        private MVerticalLayout content;

        public PopupContent(String valueAsHtml) {
            this.valueAsHtml = valueAsHtml;
            content = new MVerticalLayout();
        }

        public void setMinimizedValueAsHTML(String valueAsHtml) {
            this.valueAsHtml = valueAsHtml;
        }

        @Override
        public String getMinimizedValueAsHTML() {
            return valueAsHtml;
        }

        @Override
        public Component getPopupComponent() {
            return content;
        }
    }
}
