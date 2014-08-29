/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc., and individual contributors
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
package org.jboss.weld.environment.se.example.numberguess;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.InjectionTarget;

/**
 * @author Kirill Gaevskii
 *
 */
public class AddBeanExtension implements Extension {

    public void afterBeanDiscovery(@Observes AfterBeanDiscovery after, BeanManager beanMgr) {
        AnnotatedType<IntegerBean> type = beanMgr.createAnnotatedType(IntegerBean.class);
        final InjectionTarget<IntegerBean> injectionTgt = beanMgr.createInjectionTarget(type);
        System.err.println("Hello world");
        final IntegerBean tmpBean = new IntegerBean();
        injectionTgt.inject(tmpBean, (CreationalContext<IntegerBean>) after);
        after.addBean(tmpBean);
    }
}
