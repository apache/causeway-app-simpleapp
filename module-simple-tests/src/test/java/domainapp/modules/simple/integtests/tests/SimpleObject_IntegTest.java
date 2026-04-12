package domainapp.modules.simple.integtests.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.transaction.annotation.Transactional;

import org.apache.causeway.applib.services.wrapper.DisabledException;
import org.apache.causeway.applib.services.wrapper.InvalidException;

import domainapp.modules.simple.dom.so.SimpleObject;
import domainapp.modules.simple.fixture.SimpleObject_persona;
import domainapp.modules.simple.integtests.SimpleModuleIntegTestAbstract;

public class SimpleObject_IntegTest {

    public static abstract class Base extends SimpleModuleIntegTestAbstract {
        SimpleObject simpleObject;

        @BeforeEach
        public void setUp() {
            // given
            simpleObject = fixtureScripts.runPersona(SimpleObject_persona.FOO);
        }
    }

    @Nested
    public class name extends Base {

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
    public class updateName extends Base {


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
