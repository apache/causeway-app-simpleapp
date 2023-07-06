package domainapp.modules.simple.dom.so;

import java.util.Optional;
import java.util.stream.Stream;

import org.apache.causeway.applib.graph.tree.TreeAdapter;

import lombok.val;

public class SimpleObjectTreeAdapter implements TreeAdapter<SimpleObject> {

    @Override
    public Optional<SimpleObject> parentOf(final SimpleObject value) {
        val allInstances = value.allInstances();
        final int index = allInstances.indexOf(value);
        return index>0
                ? allInstances.get(index-1)
                : Optional.empty();
    }

    @Override
    public int childCountOf(final SimpleObject value) {
        val allInstances = value.allInstances();
        final int index = allInstances.indexOf(value);
        final int last_index = allInstances.size()-1;
        return index>-1
                && index < last_index
                ? 1
                : 0;
    }

    @Override
    public Stream<SimpleObject> childrenOf(final SimpleObject value) {
        val allInstances = value.allInstances();
        final int index = allInstances.indexOf(value);
        final int last_index = allInstances.size()-1;
        return index>-1
                && index<last_index
                ? allInstances.get(index+1).stream()
                : Stream.empty();
    }


}
