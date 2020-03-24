package domainapp.webapp.unittests.archunit;

import javax.jdo.annotations.PersistenceCapable;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;

@AnalyzeClasses(
        packages = "domainapp.modules.simple",
        importOptions = {
                ImportOption.DoNotIncludeTests.class,
                ImportOption.DoNotIncludeJars.class
        })
public class ArchitectureTests {

    @ArchTest
    static ArchRule package_dependencies =
            layeredArchitecture()
                    .layer("dom").definedBy("..dom..")
                    .layer("fixture").definedBy("..fixture..")
                    .layer("types").definedBy("..types..")

            .whereLayer("types").mayOnlyBeAccessedByLayers("dom")
            .whereLayer("dom").mayOnlyBeAccessedByLayers("fixture")
            .whereLayer("fixture").mayNotBeAccessedByAnyLayer();

    @ArchTest
    static ArchRule classes_annotated_with_PersistenceCapable_are_also_annotated_with_DomainObject =
            classes()
                   .that().areAnnotatedWith(PersistenceCapable.class)
            .should().beAnnotatedWith(DomainObject.class);

    @ArchTest
    static ArchRule classes_annotated_with_DomainObject_are_also_annotated_with_DomainObjectLayout =
            classes()
                   .that().areAnnotatedWith(DomainObject.class)
            .should().beAnnotatedWith(DomainObjectLayout.class);

}