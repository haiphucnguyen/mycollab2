package com.esofthead.mycollab.reporting;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.utils.FieldGroupFormatter;
import com.esofthead.mycollab.vaadin.AppContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MyCollab Ltd
 * @since 5.2.11
 */
public class FormReportStreamSource<B> extends ReportStreamSource {
    private B bean;
    private FormReportLayout formReportLayout;
    private FieldGroupFormatter fieldGroupFormatter;

    public FormReportStreamSource(FormReportTemplateExecutor<B> templateExecutor) {
        super(templateExecutor);
    }

    public B getBean() {
        return bean;
    }

    public void setBean(B bean) {
        this.bean = bean;
    }

    public void setFormLayout(FormReportLayout formReportLayout) {
        this.formReportLayout = formReportLayout;
    }

    public void setFieldGroupFormatter(FieldGroupFormatter fieldGroupFormatter) {
        this.fieldGroupFormatter = fieldGroupFormatter;
    }

    @Override
    protected Map<String, Object> initReportParameters() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("siteUrl", AppContext.getSiteUrl());
        if (bean != null) {
            parameters.put("bean", bean);
        } else {
            throw new MyCollabException("Bean must be not null");
        }

        parameters.put("layout", formReportLayout);
        parameters.put("formatter", fieldGroupFormatter);
        return parameters;
    }
}
