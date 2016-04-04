package com.esofthead.mycollab.reporting;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.vaadin.AppContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MyCollab Ltd
 * @since 5.2.11
 */
public class FormReportStreamSource<B> extends ReportStreamSource {
    private B bean;

    public FormReportStreamSource(FormReportTemplateExecutor<B> templateExecutor) {
        super(templateExecutor);
    }

    public B getBean() {
        return bean;
    }

    public void setBean(B bean) {
        this.bean = bean;
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

        return parameters;
    }
}
