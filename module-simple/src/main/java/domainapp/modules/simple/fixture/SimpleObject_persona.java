package domainapp.modules.simple.fixture;

import java.time.LocalDate;

import javax.inject.Inject;

import org.apache.isis.applib.services.clock.ClockService;
import org.apache.isis.applib.services.registry.ServiceRegistry;
import org.apache.isis.testing.fakedata.applib.services.FakeDataService;
import org.apache.isis.testing.fixtures.applib.personas.Persona;
import org.apache.isis.testing.fixtures.applib.setup.PersonaEnumPersistAll;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.val;

import domainapp.modules.simple.dom.so.SimpleObject;
import domainapp.modules.simple.dom.so.SimpleObjects;

@RequiredArgsConstructor
public enum SimpleObject_persona
implements Persona<SimpleObject, SimpleObjectBuilder> {

    FOO("Foo", "Foo.pdf"),
    BAR("Bar", "Bar.pdf"),
    BAZ("Baz", null),
    FRODO("Frodo", "Frodo.pdf"),
    FROYO("Froyo", null),
    FIZZ("Fizz", "Fizz.pdf"),
    BIP("Bip", null),
    BOP("Bop", null),
    BANG("Bang", "Bang.pdf"),
    BOO("Boo", null);

    private final String name;
    private final String contentFileName;

    @Override
    public SimpleObjectBuilder builder() {
                return new SimpleObjectBuilder()
                    .setName(name)
                    .setContentFileName(contentFileName);
    }

    @Override
    public SimpleObject findUsing(final ServiceRegistry serviceRegistry) {
        SimpleObjects simpleObjects = serviceRegistry.lookupService(SimpleObjects.class).orElse(null);
        return simpleObjects.findByNameExact(name);
    }

    public static class PersistAll
    extends PersonaEnumPersistAll<SimpleObject, SimpleObject_persona, SimpleObjectBuilder> {

        public PersistAll() {
            super(SimpleObject_persona.class);
        }
    }

}
