package com.esofthead.mycollab.uicomponents;

import com.esofthead.mycollab.pages.HomePage;
import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.time.Duration;

import java.lang.reflect.Method;

public class ContactFormNotificationPanel extends Panel {
    private int countDown = 5;

    public ContactFormNotificationPanel(String id) {
        super(id);
        final Label notificationText = new Label("notification-txt", new PropertyModel<Integer>(this, "countDown"));
        notificationText.add(new AjaxSelfUpdatingTimerBehavior(Duration.ONE_SECOND));

        add(notificationText);
        add(new AbstractAjaxTimerBehavior(Duration.ONE_SECOND){

            @Override
            protected void onTimer(AjaxRequestTarget target) {
                if(getCountDown() == 0)
                    throw new RestartResponseException(HomePage.class, new PageParameters());
                else
                    decreaseCountDown();

            }

            @Override
            public boolean canCallListenerInterface(Component component, Method method) {
                if(ContactFormNotificationPanel.this.equals(component) &&
                        method.getDeclaringClass().equals(org.apache.wicket.behavior.IBehaviorListener.class) &&
                        method.getName().equals("onRequest")){
                    return true;
                }
                return super.canCallListenerInterface(component, method);
            }
        });
    }

    public int getCountDown(){
        return countDown;
    }

    public void setCountDown(int value) {
        countDown = value;
    }

    public void decreaseCountDown() {
        countDown--;
    }
}
