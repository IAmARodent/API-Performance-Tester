/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.jmeter.assertions.jmespath.gui;

import org.apache.jmeter.assertions.jmespath.JMESPathAssertion;
import org.apache.jmeter.testelement.TestElement;
import org.junit.jupiter.api.Test;

public class JMESPathAssertionGuiTest {

    @Test
    public void testInit() {
        JMESPathAssertionGui instance = new JMESPathAssertionGui();
        instance.stateChanged(null);
    }

    @Test
    public void testClearGui() {
        JMESPathAssertionGui instance = new JMESPathAssertionGui();
        instance.clearGui();
    }

    @Test
    public void testCreateTestElement() {
        JMESPathAssertionGui instance = new JMESPathAssertionGui();
        instance.createTestElement();
    }

    @Test
    public void testGetLabelResource() {
        JMESPathAssertionGui instance = new JMESPathAssertionGui();
        instance.getLabelResource();
    }

    @Test
    public void testGetStaticLabel() {
        JMESPathAssertionGui instance = new JMESPathAssertionGui();
        instance.getStaticLabel();
    }

    @Test
    public void testModifyTestElement() {
        TestElement element = new JMESPathAssertion();
        JMESPathAssertionGui instance = new JMESPathAssertionGui();
        instance.modifyTestElement(element);
    }

    @Test
    public void testConfigure() {
        TestElement element = new JMESPathAssertion();
        JMESPathAssertionGui instance = new JMESPathAssertionGui();
        instance.configure(element);
    }
}
