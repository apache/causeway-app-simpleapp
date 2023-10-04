package domainapp.webapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import org.apache.causeway.applib.CausewayModuleApplibChangeAndExecutionLoggers;
import org.apache.causeway.applib.CausewayModuleApplibMixins;
import org.apache.causeway.core.config.presets.CausewayPresets;
import org.apache.causeway.core.metamodel.inspect.CausewayModuleCoreMetamodelMixins;
import org.apache.causeway.core.runtimeservices.CausewayModuleCoreRuntimeServices;
import org.apache.causeway.extensions.audittrail.jdo.CausewayModuleExtAuditTrailPersistenceJdo;
import org.apache.causeway.extensions.commandlog.jdo.CausewayModuleExtCommandLogPersistenceJdo;
import org.apache.causeway.extensions.exceldownload.wkt.ui.CausewayModuleExtExcelDownloadWicketUi;
import org.apache.causeway.extensions.executionlog.jdo.CausewayModuleExtExecutionLogPersistenceJdo;
import org.apache.causeway.extensions.executionoutbox.jdo.CausewayModuleExtExecutionOutboxPersistenceJdo;
import org.apache.causeway.extensions.flyway.impl.CausewayModuleExtFlywayImpl;
import org.apache.causeway.extensions.fullcalendar.wkt.ui.viewer.CausewayModuleExtFullCalendarWicketUi;
import org.apache.causeway.extensions.pdfjs.wkt.ui.CausewayModuleExtPdfjsWicketUi;
import org.apache.causeway.extensions.secman.encryption.spring.CausewayModuleExtSecmanEncryptionSpring;
import org.apache.causeway.extensions.secman.jdo.CausewayModuleExtSecmanPersistenceJdo;
import org.apache.causeway.extensions.sessionlog.jdo.CausewayModuleExtSessionLogPersistenceJdo;
import org.apache.causeway.persistence.jdo.datanucleus.CausewayModulePersistenceJdoDatanucleus;
import org.apache.causeway.persistence.jdo.datanucleus.CausewayModulePersistenceJdoDatanucleusMixins;
import org.apache.causeway.testing.fixtures.applib.CausewayModuleTestingFixturesApplib;
import org.apache.causeway.testing.h2console.ui.CausewayModuleTestingH2ConsoleUi;
import org.apache.causeway.valuetypes.asciidoc.metamodel.CausewayModuleValAsciidocMetaModel;
import org.apache.causeway.valuetypes.asciidoc.ui.wkt.CausewayModuleValAsciidocUiWkt;
import org.apache.causeway.viewer.restfulobjects.jaxrsresteasy.CausewayModuleViewerRestfulObjectsJaxrsResteasy;
import org.apache.causeway.viewer.wicket.applib.CausewayModuleViewerWicketApplibMixins;
import org.apache.causeway.viewer.wicket.viewer.CausewayModuleViewerWicketViewer;

import domainapp.webapp.application.ApplicationModule;
import domainapp.webapp.application.fixture.scenarios.DomainAppDemo;
import domainapp.webapp.custom.CustomModule;
import domainapp.webapp.quartz.QuartzModule;

@Configuration
@Import({
        CausewayModuleApplibMixins.class,
        CausewayModuleCoreMetamodelMixins.class,
        CausewayModulePersistenceJdoDatanucleusMixins.class,
        CausewayModuleViewerWicketApplibMixins.class,

        CausewayModuleApplibChangeAndExecutionLoggers.class,

        CausewayModuleCoreRuntimeServices.class,
        CausewayModulePersistenceJdoDatanucleus.class,
        CausewayModuleViewerRestfulObjectsJaxrsResteasy.class,
        CausewayModuleViewerWicketViewer.class,

        CausewayModuleTestingFixturesApplib.class,
        CausewayModuleTestingH2ConsoleUi.class,

        CausewayModuleExtFlywayImpl.class,

        CausewayModuleExtSecmanPersistenceJdo.class,
        CausewayModuleExtSecmanEncryptionSpring.class,
        CausewayModuleExtSessionLogPersistenceJdo.class,
        CausewayModuleExtAuditTrailPersistenceJdo.class,
        CausewayModuleExtCommandLogPersistenceJdo.class,
        CausewayModuleExtExecutionLogPersistenceJdo.class,
        CausewayModuleExtExecutionOutboxPersistenceJdo.class,

        CausewayModuleExtExcelDownloadWicketUi.class,
        CausewayModuleExtFullCalendarWicketUi.class,
        CausewayModuleExtPdfjsWicketUi.class,

        CausewayModuleValAsciidocMetaModel.class, // for pretty rendering of DTO objects such as CommandDto, InteractionDto
        CausewayModuleValAsciidocUiWkt.class,

        ApplicationModule.class,
        CustomModule.class,
        QuartzModule.class,

        // discoverable fixtures
        DomainAppDemo.class
})
@PropertySources({
        @PropertySource(CausewayPresets.DebugDiscovery),
})
public class AppManifest {

}
