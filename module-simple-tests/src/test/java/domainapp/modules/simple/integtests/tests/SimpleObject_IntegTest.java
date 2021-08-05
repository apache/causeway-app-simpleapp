package domainapp.modules.simple.integtests.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.isis.applib.services.wrapper.DisabledException;
import org.apache.isis.applib.services.wrapper.InvalidException;

import domainapp.modules.simple.dom.so.SimpleObject;
import domainapp.modules.simple.fixture.SimpleObject_persona;
import domainapp.modules.simple.integtests.SimpleModuleIntegTestAbstract;

@Transactional
public class SimpleObject_IntegTest extends SimpleModuleIntegTestAbstract {

    SimpleObject simpleObject;

    @BeforeEach
    public void setUp() {
        // given
        simpleObject = fixtureScripts.runPersona(SimpleObject_persona.FOO);
    }


    @Nested
    public static class name extends SimpleObject_IntegTest {

        @Test
        public void accessible() {
            // when
            final String name = wrap(simpleObject).getName();

            // then
            assertThat(name).isEqualTo(simpleObject.getName());
        }

        @Test
        public void not_editable() {

            // expect
            assertThrows(DisabledException.class, ()->{

                // when
                wrap(simpleObject).setName("new name");
            });
        }

    }

    @Nested
    public static class updateName extends SimpleObject_IntegTest {


        @Test
        public void can_be_updated_directly() {

            // when
            wrap(simpleObject).updateName("new name");
            transactionService.flushTransaction();

            // then
            assertThat(wrap(simpleObject).getName()).isEqualTo("new name");
        }

        @Test
        public void fails_validation() {

            // expect
            InvalidException cause = assertThrows(InvalidException.class, ()->{

                // when
                wrap(simpleObject).updateName("new name!");
            });

            // then
            assertThat(cause.getMessage()).contains("Character '!' is not allowed");
        }
    }

}
