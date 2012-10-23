/**
 * Engroup - Enterprise Groupware Platform
 * Copyright (C) 2007-2009 eSoftHead Company <engroup@esofthead.com>
 * http://www.esofthead.com
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.esofthead.test;

/**
 * Engroup test class can be tested against external entities like database,
 * ldap, email server etc or combine several of them. EngroupTestModule provide
 * the plugin module for engroup test class that they can add several supported
 * module that helps the process of set up and destroy external entities without
 * impacting to test code.
 * 
 * The following code depicts how to make the integration test against database:
 * <code>
 * public class ATest {
 *     public ATest () {
 *         addTestModule(new DbUnitModule());
 *     }
 *
 *     public void testXyz() {
 *         //do the test against database
 *     }
 * }
 * </code>
 * 
 * @author Hai Nguyen (hainguyen@esofthead.com)
 */
public interface EngroupTestModule {
    /**
     * Assign the test host to this module. Depend on the functionality of this
     * module, it get some variables from this host like data source, test
     * configurations etc and serves for setUp and tearDown actions.
     * 
     * @param host
     *            The test class attach to this module
     */
    void setHost(Class host);

    /**
     * Init the module. Note that this method is invoked after the test host
     * init the testing.
     */
    void setUp();

    /**
     * Clean up the testing includes resources or extra configuration settings.
     * This method is invoked after the test host clean up testing.
     */
    void tearDown();
}
