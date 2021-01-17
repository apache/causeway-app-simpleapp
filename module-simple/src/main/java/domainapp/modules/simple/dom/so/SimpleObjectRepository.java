package domainapp.modules.simple.dom.so;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SimpleObjectRepository extends JpaRepository<SimpleObject, Long> {

    List<SimpleObject> findByNameContaining(final String name);

    SimpleObject findByName(final String name);

}
