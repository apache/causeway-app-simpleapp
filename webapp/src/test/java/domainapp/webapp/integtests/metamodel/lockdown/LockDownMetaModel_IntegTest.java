package domainapp.webapp.integtests.metamodel.lockdown;

import java.util.List;

import javax.inject.Inject;

import org.approvaltests.namer.StackTraceNamer;
import org.approvaltests.reporters.DiffReporter;
import org.approvaltests.reporters.QuietReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.jupiter.api.Test;

import org.apache.isis.applib.services.jaxb.JaxbService;
import org.apache.isis.applib.services.metamodel.MetaModelService;
import org.apache.isis.schema.metamodel.v1.DomainClassDto;
import org.apache.isis.schema.metamodel.v1.MetamodelDto;

import static org.approvaltests.Approvals.getReporter;
import static org.approvaltests.Approvals.verify;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assume.assumeThat;

import domainapp.webapp.integtests.ApplicationIntegTestAbstract;
import domainapp.webapp.util.ReceivedVsApprovedApprovalTextWriter;

class LockDownMetaModel_IntegTest extends ApplicationIntegTestAbstract {

    @Inject MetaModelService metaModelService;
    @Inject JaxbService jaxbService;

    //
    // learn...
    //
    // ... move the resultant files in "received" directory over to "approved".
    //
    @UseReporter(QuietReporter.class)
    @Test
    void _1_learn() throws Exception {

        assumeThat(System.getProperty("learn"), is(notNullValue()));

        // when
        MetamodelDto metamodelDto =
                metaModelService.exportMetaModel(
                        new MetaModelService.Config()
                        .withIgnoreNoop()
                        .withIgnoreAbstractClasses()
                        .withIgnoreBuiltInValueTypes()
                        .withIgnoreInterfaces()
                        .withPackagePrefix("domainapp")
                        );

        // then
        final List<DomainClassDto> domainClassDto = metamodelDto.getDomainClassDto();
        for (final DomainClassDto domainClass : domainClassDto) {
            try {
                verifyClass(domainClass);
            } catch (Error e) {
                //ignore ... learning.
            }
        }
    }


    //
    // verify ...
    //
    // ... ie compare the current metamodel to that previously captured.
    //
    @UseReporter(DiffReporter.class)
    @Test
    void _2_verify() throws Exception {

        assumeThat(System.getProperty("lockdown"), is(notNullValue()));

        // when
        MetamodelDto metamodelDto =
                metaModelService.exportMetaModel(
                        new MetaModelService.Config()
                        .withIgnoreNoop()
                        .withIgnoreAbstractClasses()
                        .withIgnoreBuiltInValueTypes()
                        .withIgnoreInterfaces()
                        .withPackagePrefix("domainapp")
                        );

        // then
        final List<DomainClassDto> domainClassDto = metamodelDto.getDomainClassDto();
        for (final DomainClassDto domainClass : domainClassDto) {
            verifyClass(domainClass);
        }
    }

    private void verifyClass(final DomainClassDto domainClass) {
        String asXml = jaxbService.toXml(domainClass);
        verify(
                new ReceivedVsApprovedApprovalTextWriter(asXml, "xml"),
                new StackTraceNamer() {
                    @Override public String getApprovalName() {
                        return domainClass.getId();
                    }
                }, getReporter());
    }

}