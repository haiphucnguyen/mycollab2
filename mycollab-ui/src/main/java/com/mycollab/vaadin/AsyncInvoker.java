package com.mycollab.vaadin;

import com.mycollab.configuration.SiteConfiguration;
import com.vaadin.ui.UI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author MyCollab Ltd
 * @since 5.2.1
 */
public class AsyncInvoker {
    private static Logger LOG = LoggerFactory.getLogger(AsyncInvoker.class);

    public static void access(final UI ui, final PageCommand pageCommand) {
        if (ui == null) {
            return;
        }
        pageCommand.setUI(ui);
        if (SiteConfiguration.getPullMethod() == SiteConfiguration.PullMethod.push) {
            new Thread(() -> ui.access(() -> {
                try {
                    pageCommand.run();
                    ui.push();
                    pageCommand.postRun();
                } finally {
                    pageCommand.cleanUp();
                    try {
                        ui.push();
                    } catch (Exception e) {
                        LOG.error("Error", e);
                    }
                }
            })).start();
        } else {
            ui.getSession().getLockInstance().lock();
            try {
                ui.setPollInterval(1000);
                pageCommand.run();
                pageCommand.postRun();
            } finally {
                pageCommand.cleanUp();
                ui.setPollInterval(-1);
                ui.getSession().getLockInstance().unlock();
            }
        }
    }

    public static abstract class PageCommand {
        UI currentUI;

        void setUI(UI ui) {
            currentUI = ui;
        }

        public UI getUI() {
            return currentUI;
        }

        abstract public void run();

        public void postRun() {
        }

        public void cleanUp() {
        }

        public void push() {
            if (SiteConfiguration.getPullMethod() == SiteConfiguration.PullMethod.push) {
                currentUI.push();
            }
        }
    }
}
