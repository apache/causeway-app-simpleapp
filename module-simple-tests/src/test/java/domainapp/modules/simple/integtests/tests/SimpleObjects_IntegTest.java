package domainapp.modules.simple.integtests.tests;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.RollbackException;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.causeway.applib.services.iactnlayer.InteractionService;
import org.apache.causeway.commons.functional.Try;
import org.apache.causeway.testing.unittestsupport.applib.matchers.ThrowableMatchers;

import lombok.val;

import domainapp.modules.simple.dom.so.SimpleObject;
import domainapp.modules.simple.dom.so.SimpleObjects;
import domainapp.modules.simple.fixture.SimpleObject_persona;
import domainapp.modules.simple.integtests.SimpleModuleIntegTestAbstract;

@Transactional
public class SimpleObjects_IntegTest extends SimpleModuleIntegTestAbstract {

    @Inject
    SimpleObjects menu;

    @Nested
    public static class listAll extends SimpleObjects_IntegTest {

        @Test
        public void happyCase() {

            // given
            fixtureScripts.run(new SimpleObject_persona.PersistAll());
            transactionService.flushTransaction();

            // when
            final List<SimpleObject> all = wrap(menu).listAll();

            // then
            assertThat(all).hasSize(SimpleObject_persona.values().length);
        }

        @Test
        public void whenNone() {

            // when
            final List<SimpleObject> all = wrap(menu).listAll();

            // then
            assertThat(all).hasSize(0);
        }
    }

    @Nested
    public static class create extends SimpleObjects_IntegTest {

        @Test
        public void happyCase() {

            wrap(menu).create("Faz");

            // then
            final List<SimpleObject> all = wrap(menu).listAll();
            assertThat(all).hasSize(1);
        }

        @Test
        public void whenAlreadyExists() {

            // given
            fixtureScripts.runPersona(SimpleObject_persona.FIZZ);
            interactionService.nextInteraction();

            // we execute this in its own transaction so that it can be discarded
            Try<Void> attempt = transactionService.runTransactional(Propagation.REQUIRES_NEW, () -> {

                // expect
                Throwable cause = assertThrows(Throwable.class, () -> {
                    // when
                    wrap(menu).create("Fizz");
                    transactionService.flushTransaction();
                });

                // also expect
                MatcherAssert.assertThat(cause,
                        ThrowableMatchers.causedBy(DuplicateKeyException.class));
            });


            // then
            assertThat(attempt.isFailure()).isTrue();
            val failureIfAny = attempt.getFailure();
            assertThat(failureIfAny).isPresent();
            assertThat(failureIfAny.get()).isInstanceOf(DuplicateKeyException.class);
            assertThat(failureIfAny.get().getCause()).isInstanceOf(JdbcSQLIntegrityConstraintViolationException.class);

        }

    }

    @Inject protected InteractionService interactionService;
}
