/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package domainapp.webapp.integtests;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import org.apache.isis.core.config.presets.IsisPresets;
import org.apache.isis.core.runtimeservices.IsisModuleCoreRuntimeServices;
import org.apache.isis.persistence.jdo.datanucleus.IsisModuleJdoDatanucleus;
import org.apache.isis.security.bypass.IsisModuleSecurityBypass;
import org.apache.isis.testing.fixtures.applib.IsisModuleTestingFixturesApplib;

import domainapp.modules.simple.SimpleModule;

/**
 * Compared to the production app manifest <code>domainapp.webapp.AppManifest</code>,
 * here we in effect disable security checks, and we exclude any web/UI modules.
 */
@SpringBootConfiguration
@Import({

    IsisModuleCoreRuntimeServices.class,
    IsisModuleSecurityBypass.class,
    IsisModuleJdoDatanucleus.class,
    IsisModuleTestingFixturesApplib.class,

    SimpleModule.class
})
@PropertySources({
    @PropertySource(IsisPresets.H2InMemory_withUniqueSchema),
    @PropertySource(IsisPresets.DataNucleusAutoCreate),
    @PropertySource(IsisPresets.UseLog4j2Test),
})
public class SimpleWebAppTestConfiguration_usingJdo {


}
