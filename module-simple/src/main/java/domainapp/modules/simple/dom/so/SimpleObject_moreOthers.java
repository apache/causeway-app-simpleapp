package domainapp.modules.simple.dom.so;

import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.causeway.applib.annotation.Collection;
import org.apache.causeway.applib.annotation.MemberSupport;

@Collection
@RequiredArgsConstructor
public class SimpleObject_moreOthers {

    private final SimpleObject simpleObject;

    @MemberSupport
    public java.util.List<SimpleObject> coll() {
        return simpleObjects.listAll().stream().filter(x -> x != simpleObject).collect(Collectors.toList());
    }

    @Inject SimpleObjects simpleObjects;

}
