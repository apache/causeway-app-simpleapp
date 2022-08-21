package domainapp.webapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import org.apache.isis.applib.IsisModuleApplibChangeAndExecutionLoggers;
import org.apache.isis.applib.IsisModuleApplibMixins;
import org.apache.isis.core.config.presets.IsisPresets;
import org.apache.isis.core.runtimeservices.IsisModuleCoreRuntimeServices;
import org.apache.isis.extensions.audittrail.jpa.IsisModuleExtAuditTrailPersistenceJpa;
import org.apache.isis.extensions.commandlog.jpa.IsisModuleExtCommandLogPersistenceJpa;
import org.apache.isis.extensions.executionlog.jpa.IsisModuleExtExecutionLogPersistenceJpa;
import org.apache.isis.extensions.executionoutbox.jpa.IsisModuleExtExecutionOutboxPersistenceJpa;
import org.apache.isis.extensions.flyway.impl.IsisModuleExtFlywayImpl;
import org.apache.isis.extensions.sessionlog.jpa.IsisModuleExtSessionLogPersistenceJpa;
import org.apache.isis.persistence.jpa.eclipselink.IsisModulePersistenceJpaEclipselink;
import org.apache.isis.security.shiro.IsisModuleSecurityShiro;
import org.apache.isis.testing.fixtures.applib.IsisModuleTestingFixturesApplib;
import org.apache.isis.testing.h2console.ui.IsisModuleTestingH2ConsoleUi;
import org.apache.isis.valuetypes.asciidoc.metamodel.IsisModuleValAsciidocMetaModel;
import org.apache.isis.valuetypes.asciidoc.ui.wkt.IsisModuleValAsciidocUiWkt;
import org.apache.isis.viewer.restfulobjects.jaxrsresteasy4.IsisModuleViewerRestfulObjectsJaxrsResteasy4;
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
        IsisModulePersistenceJpaEclipselink.class,
        IsisModuleViewerRestfulObjectsJaxrsResteasy4.class,
        IsisModuleViewerWicketViewer.class,

        IsisModuleTestingFixturesApplib.class,
        IsisModuleTestingH2ConsoleUi.class,

        IsisModuleExtFlywayImpl.class,

        IsisModuleExtSessionLogPersistenceJpa.class,
        IsisModuleExtAuditTrailPersistenceJpa.class,
        IsisModuleExtCommandLogPersistenceJpa.class,
        IsisModuleExtExecutionLogPersistenceJpa.class,
        IsisModuleExtExecutionOutboxPersistenceJpa.class,

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
