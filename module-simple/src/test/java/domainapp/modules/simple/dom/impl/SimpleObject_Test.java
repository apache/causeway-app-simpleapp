package domainapp.modules.simple.dom.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleObject_Test {

    SimpleObject simpleObject;

    @BeforeEach
    public void setUp() throws Exception {
        simpleObject = SimpleObject.ofName("Foobar");
    }

    public static class Name extends SimpleObject_Test {

        @Test
        public void happyCase() throws Exception {
            // given
            assertThat(simpleObject.getName()).isEqualTo("Foobar");

            // when
            String name = "Foobar - updated";
            simpleObject.setName(name);

            // then
            assertThat(simpleObject.getName()).isEqualTo(name);
        }
    }

}
