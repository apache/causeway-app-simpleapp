package domainapp.webapp.application.services.health;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.springframework.stereotype.Service;

import org.apache.causeway.applib.services.health.Health;
import org.apache.causeway.applib.services.health.HealthCheckService;

import domainapp.modules.simple.dom.so.SimpleObjects;

@Service
@Named("domainapp.HealthCheckServiceImpl")
public class HealthCheckServiceImpl implements HealthCheckService {

    private final SimpleObjects simpleObjects;

    @Inject
    public HealthCheckServiceImpl(SimpleObjects simpleObjects) {
        this.simpleObjects = simpleObjects;
    }

    @Override
    public Health check() {
        try {
            simpleObjects.ping();
            return Health.ok();
        } catch (Exception ex) {
            return Health.error(ex);
        }
    }
}
