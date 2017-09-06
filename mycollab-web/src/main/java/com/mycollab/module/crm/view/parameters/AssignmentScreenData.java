package com.mycollab.module.crm.view.parameters;

import com.mycollab.module.crm.domain.CrmTask;
import com.mycollab.vaadin.mvp.ScreenData;

public class AssignmentScreenData {
    public static class Add extends ScreenData<CrmTask> {

        public Add(CrmTask task) {
            super(task);
        }
    }

    public static class Edit extends ScreenData<CrmTask> {

        public Edit(CrmTask task) {
            super(task);
        }
    }

    public static class Read extends ScreenData<Integer> {

        public Read(Integer params) {
            super(params);
        }
    }
}
