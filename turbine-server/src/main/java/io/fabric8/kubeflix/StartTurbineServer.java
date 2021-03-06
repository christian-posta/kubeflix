/*
 * Copyright (C) 2015 Red Hat, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.fabric8.kubeflix;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.netflix.turbine.plugins.PluginsFactory;
import io.fabric8.kubeflix.discovery.KubernetesDiscovery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.turbine.init.TurbineInit;

public class StartTurbineServer implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(StartTurbineServer.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Initing Turbine server");
        PluginsFactory.setInstanceDiscovery(new KubernetesDiscovery());
        TurbineInit.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Stopping Turbine server");
        TurbineInit.stop();
    }
}
