package domainapp.webapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import org.apache.isis.applib.IsisModuleApplibChangeAndExecutionLoggers;
import org.apache.isis.applib.IsisModuleApplibMixins;
import org.apache.isis.core.config.presets.IsisPresets;
import org.apache.isis.core.runtimeservices.IsisModuleCoreRuntimeServices;
import org.apache.isis.extensions.audittrail.jdo.IsisModuleExtAuditTrailPersistenceJdo;
import org.apache.isis.extensions.commandlog.jdo.IsisModuleExtCommandLogPersistenceJdo;
import org.apache.isis.extensions.executionlog.jdo.IsisModuleExtExecutionLogPersistenceJdo;
import org.apache.isis.extensions.executionoutbox.jdo.IsisModuleExtExecutionOutboxPersistenceJdo;
import org.apache.isis.extensions.flyway.impl.IsisModuleExtFlywayImpl;
import org.apache.isis.extensions.sessionlog.jdo.IsisModuleExtSessionLogPersistenceJdo;
import org.apache.isis.persistence.jdo.datanucleus.IsisModulePersistenceJdoDatanucleus;
import org.apache.isis.persistence.jdo.datanucleus.IsisModulePersistenceJdoDatanucleusMixins;
import org.apache.isis.security.shiro.IsisModuleSecurityShiro;
import org.apache.isis.testing.fixtures.applib.IsisModuleTestingFixturesApplib;
import org.apache.isis.testing.h2console.ui.IsisModuleTestingH2ConsoleUi;
import org.apache.isis.valuetypes.asciidoc.metamodel.IsisModuleValAsciidocMetaModel;
import org.apache.isis.valuetypes.asciidoc.ui.wkt.IsisModuleValAsciidocUiWkt;
import org.apache.isis.viewer.restfulobjects.jaxrsresteasy4.IsisModuleViewerRestfulObjectsJaxrsResteasy4;
import org.apache.isis.viewer.wicket.applib.IsisModuleViewerWicketApplibMixins;
import org.apache.isis.viewer.wicket.viewer.IsisModuleViewerWicketViewer;

import domainapp.webapp.application.ApplicationModule;
import domainapp.webapp.application.fixture.scenarios.DomainAppDemo;
import domainapp.webapp.custom.CustomModule;
import domainapp.webapp.quartz.QuartzModule;

@Configuration
@Import({
        IsisModuleApplibMixins.class,
        IsisModuleApplibChangeAndExecutionLoggers.class,

        IsisModuleCoreRuntimeServices.class,
        IsisModuleSecurityShiro.class,
        IsisModulePersistenceJdoDatanucleus.class,
        IsisModulePersistenceJdoDatanucleusMixins.class,
        IsisModuleViewerRestfulObjectsJaxrsResteasy4.class,
        IsisModuleViewerWicketApplibMixins.class,
        IsisModuleViewerWicketViewer.class,

        IsisModuleTestingFixturesApplib.class,
        IsisModuleTestingH2ConsoleUi.class,

        IsisModuleExtFlywayImpl.class,

        IsisModuleExtSessionLogPersistenceJdo.class,
        IsisModuleExtAuditTrailPersistenceJdo.class,
        IsisModuleExtCommandLogPersistenceJdo.class,
        IsisModuleExtExecutionLogPersistenceJdo.class,
        IsisModuleExtExecutionOutboxPersistenceJdo.class,

        IsisModuleValAsciidocMetaModel.class, // for pretty rendering of DTO objects such as CommandDto, InteractionDto
        IsisModuleValAsciidocUiWkt.class,


        ApplicationModule.class,
        CustomModule.class,
        QuartzModule.class,

        // discoverable fixtures
        DomainAppDemo.class
})
@PropertySources({
        @PropertySource(IsisPresets.DebugDiscovery),
})
public class AppManifest {


}
