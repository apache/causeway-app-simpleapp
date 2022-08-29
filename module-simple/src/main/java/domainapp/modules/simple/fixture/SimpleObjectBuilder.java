package domainapp.modules.simple.fixture;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.inject.Inject;

import org.springframework.core.io.ClassPathResource;

import org.apache.isis.applib.value.Blob;
import org.apache.isis.testing.fixtures.applib.personas.BuilderScriptWithResult;

import domainapp.modules.simple.dom.so.SimpleObject;
import domainapp.modules.simple.dom.so.SimpleObjects;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.val;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class SimpleObjectBuilder extends BuilderScriptWithResult<SimpleObject> {

    @Getter @Setter
    private String name;
    @Getter @Setter
    private String contentFileName;

    @Override
    protected SimpleObject buildResult(final ExecutionContext ec) {

        checkParam("name", ec, String.class);

        SimpleObject simpleObject = wrap(simpleObjects).create(name);

        if (contentFileName != null) {
            val bytes = toBytes(contentFileName);
            val attachment = new Blob(contentFileName, "application/pdf", bytes);
            simpleObject.updateAttachment(attachment);
        }
        return simpleObject;
    }

    @SneakyThrows
    private byte[] toBytes(String fileName){
        InputStream inputStream = new ClassPathResource(fileName, getClass()).getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        return buffer.toByteArray();
    }

    // -- DEPENDENCIES

    @Inject SimpleObjects simpleObjects;

}
