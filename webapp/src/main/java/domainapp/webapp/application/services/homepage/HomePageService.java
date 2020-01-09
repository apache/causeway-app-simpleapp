package domainapp.webapp.application.services.homepage;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Service;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.HomePage;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.factory.FactoryService;

@Service
@Named("domainapp.HomePageService")
public class HomePageService {

    private final FactoryService factoryService;

    @Inject
    public HomePageService(final FactoryService factoryService) {
        this.factoryService = factoryService;
    }

    @Action(semantics = SemanticsOf.SAFE)
    @HomePage
    public HomePageViewModel homePage() {
        return factoryService.instantiate(HomePageViewModel.class);
    }

}
