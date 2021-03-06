/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.weld.probe.integration.tests;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.jboss.weld.probe.Strings.BEAN_DISCOVERY_MODE;
import static org.jboss.weld.probe.integration.tests.JSONTestUtil.DEPLOYMENT_PATH;
import static org.jboss.weld.probe.integration.tests.JSONTestUtil.getDeploymentByName;

import java.io.IOException;
import java.net.URL;

import javax.json.JsonObject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Tomas Remes
 */
@RunWith(Arquillian.class)
public class ProbeDeploymentsTest extends ProbeIntegrationTest {

    @ArquillianResource
    private URL url;

    private static final String TEST_ARCHIVE_NAME = "probe-deployments-test";

    @Deployment(testable = false)
    public static WebArchive deploy() {
        return ShrinkWrap.create(WebArchive.class, TEST_ARCHIVE_NAME + ".war")
                .addAsWebInfResource(ProbeDeploymentsTest.class.getPackage(), "web.xml", "web.xml")
                .addAsWebInfResource(ProbeDeploymentsTest.class.getPackage(), "beans.xml", "beans.xml")
                .addClass(ProbeDeploymentsTest.class);
    }

    @Test
    public void testDeploymentEndpoint() throws IOException {
        JsonObject testArchive = getDeploymentByName(DEPLOYMENT_PATH, TEST_ARCHIVE_NAME, url);
        assertNotNull("Cannot find test archive in Probe deployments!", testArchive);
        assertEquals("ALL", testArchive.getString(BEAN_DISCOVERY_MODE));
    }

}
