package com.esofthead.mycollab.reporting;

import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.Extension;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.AbstractComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.11
 */
public class PrintWindowOpener extends BrowserWindowOpener {
    public PrintWindowOpener(AbstractComponent extendSource, FormReportStreamSource resource) {
        super(new StreamResource(resource, "print.pdf"));
        List<Extension> extensions = new ArrayList<>(extendSource.getExtensions());
//        for (int i = extensions.size() - 1; i >= 0; i--) {
//            Extension extension = extensions.get(i);
//            if (extension instanceof Extension) {
//                extendSource.removeExtension(extension);
//            }
//        }
        this.extend(extendSource);
    }
}
