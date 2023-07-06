package domainapp.modules.simple.dom.so;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Service;

import org.apache.causeway.applib.graph.tree.TreeNode;
import org.apache.causeway.applib.graph.tree.TreePath;
import org.apache.causeway.applib.services.repository.RepositoryService;
import org.apache.causeway.commons.collections.Can;

import domainapp.modules.simple.SimpleModule;
import lombok.RequiredArgsConstructor;

@Service
@Named(SimpleModule.NAMESPACE + ".SimpleObjectTreeService")
@RequiredArgsConstructor(onConstructor_ = { @Inject })
public class SimpleObjectTreeService {

    final RepositoryService repositoryService;

    public TreeNode<SimpleObject> getTree() {

        final Can<SimpleObject> allInstances = Can.ofCollection(repositoryService.allInstances(SimpleObject.class));

        return allInstances.getFirst()
        .map(rootNode->{
            final TreeNode<SimpleObject> tree = TreeNode.lazy(rootNode, SimpleObjectTreeAdapter.class);
            tree.expand(TreePath.of(0)); // expand the root node
            return tree;
        })
        .orElse(null);
    }

}
