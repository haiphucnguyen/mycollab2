package com.esofthead.mycollab.module.user.accountsettings.billing.view;

import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.user.accountsettings.localization.UserI18nEnum;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class CancelAccountViewImpl extends AbstractView implements
        CancelAccountView {
    private static final long serialVersionUID = 1L;

    public CancelAccountViewImpl() {
        this.setWidth("100%");
        this.setMargin(true);
        this.setSpacing(true);
        HorizontalLayout header = createHeader();
        this.addComponent(header);
        this.setComponentAlignment(header, Alignment.TOP_CENTER);
        this.addComponent(createBody());
    }

    protected HorizontalLayout createHeader() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeUndefined();
        layout.setSpacing(true);
        layout.addComponent(new Embedded(null, MyCollabResource
                .newResource("icons/sad_face.png")));
        VerticalLayout header = new VerticalLayout();
        header.setSpacing(true);
        header.addStyleName("cancelAccountHeader");

        Label headerTopLine = new Label(
                LocalizationHelper
                        .getMessage(UserI18nEnum.CANCEL_ACCOUNT_FIRST_LINE));
        headerTopLine.addStyleName("first-line");
        headerTopLine.setWidth(SIZE_UNDEFINED, 0);
        header.addComponent(headerTopLine);
        header.setComponentAlignment(headerTopLine, Alignment.MIDDLE_CENTER);

        Label headerMsg = new Label(
                LocalizationHelper
                        .getMessage(UserI18nEnum.CANCEL_ACCOUNT_MESSAGE),
                Label.CONTENT_XHTML);
        headerMsg.addStyleName("header-content");
        headerMsg.setWidth(SIZE_UNDEFINED, 0);
        header.addComponent(headerMsg);
        header.setComponentAlignment(headerMsg, Alignment.MIDDLE_CENTER);

        Label headerNote = new Label(
                LocalizationHelper.getMessage(UserI18nEnum.CANCEL_ACCOUNT_NOTE));
        headerNote.addStyleName("header-note");
        headerNote.setWidth(SIZE_UNDEFINED, 0);
        header.addComponent(headerNote);
        header.setComponentAlignment(headerNote, Alignment.MIDDLE_CENTER);
        layout.addComponent(header);

        return layout;
    }

    protected CssLayout createBody() {
        CssLayout layout = new CssLayout();
        layout.setWidth("100%");
        layout.addStyleName("cancelAccountBody");

        VerticalLayout innerLayout = new VerticalLayout();
        innerLayout.setSpacing(true);
        innerLayout.setMargin(true);

        GridFormLayoutHelper layoutHelper = new GridFormLayoutHelper(1, 4,
                "100%", "200px");

        layoutHelper.addComponentNoWrapper(new TextField(),
                "Why are you leaving us?", 0, 0);

        OptionGroup optionGroupField = new OptionGroup();
        optionGroupField
                .addItem("I'm cancelling this account to join or open a new MyCollab account");
        optionGroupField.addItem("I'm missing an important integration");
        optionGroupField
                .addItem("MyCollab doesn't have all the features I'm looking for");
        optionGroupField.addItem("Too expensive");
        optionGroupField.addItem("None of the above");

        layoutHelper.addComponentNoWrapper(optionGroupField,
                "Do any of these apply?", 0, 1);

        layoutHelper
                .addComponentNoWrapper(
                        new TextField(),
                        "Are you considering any other online CRM or sales tracking tools?",
                        0, 2);

        layoutHelper.addComponentNoWrapper(new TextArea(),
                "What would it take to have you back?", 0, 3);

        layoutHelper.getLayout().setWidth("800px");
        layoutHelper.getLayout().setSpacing(true);
        innerLayout.addComponent(layoutHelper.getLayout());
        innerLayout.setComponentAlignment(layoutHelper.getLayout(),
                Alignment.MIDDLE_CENTER);

        Button submitBtn = new Button("Submit and delete my account");
        submitBtn.addStyleName(UIConstants.THEME_BLUE_LINK);

        Button cancelBtn = new Button("Never mind, go back");
        cancelBtn.addStyleName(UIConstants.THEME_GRAY_LINK);

        HorizontalLayout formControls = new HorizontalLayout();
        formControls.setSpacing(true);
        formControls.setMargin(true);
        formControls.setSizeUndefined();
        formControls.addComponent(submitBtn);
        formControls.addComponent(cancelBtn);

        innerLayout.addComponent(formControls);
        innerLayout
                .setComponentAlignment(formControls, Alignment.MIDDLE_CENTER);

        Label confirmNote = new Label(
                LocalizationHelper
                        .getMessage(UserI18nEnum.CANCEL_ACCOUNT_CONFIRM_NOTE),
                Label.CONTENT_XHTML);
        confirmNote.setWidth("600px");
        innerLayout.addComponent(confirmNote);
        innerLayout.setComponentAlignment(confirmNote, Alignment.TOP_CENTER);
        layout.addComponent(innerLayout);
        return layout;
    }
}
