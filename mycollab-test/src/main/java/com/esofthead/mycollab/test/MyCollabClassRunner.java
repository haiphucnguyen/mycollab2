/**
 * Engroup - Enterprise Groupware Platform Copyright (C) 2007-2009 eSoftHead
 * Company <engroup@esofthead.com> http://www.esofthead.com
 *
 * Licensed under the GPL, Version 3.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 * http://www.gnu.org/licenses/gpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.esofthead.mycollab.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.esofthead.mycollab.test.module.DbUnitModule;

public class MyCollabClassRunner extends SpringJUnit4ClassRunner {

    private List<MyCollabTestModule> testModules;

    public MyCollabClassRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }

    @Override
    protected Statement methodInvoker(FrameworkMethod method, Object test) {
        preInvokeMethod(method);
        Statement methodInvoker = super.methodInvoker(method, test);
        postInvokeMethod(method);
        return methodInvoker;
    }

    private void preInvokeMethod(FrameworkMethod method) {
        testModules = new ArrayList<MyCollabTestModule>();
        DataSet dataSetAnno = method.getAnnotation(DataSet.class);
        if (dataSetAnno != null) {
            DbUnitModule dbModule = new DbUnitModule();
            dbModule.setHost(this.getTestClass().getJavaClass());
            testModules.add(dbModule);
        }

        for (MyCollabTestModule module : testModules) {
            module.setUp();
        }
    }

    private void postInvokeMethod(FrameworkMethod method) {
        for (MyCollabTestModule module : testModules) {
            module.tearDown();
        }
    }
}
