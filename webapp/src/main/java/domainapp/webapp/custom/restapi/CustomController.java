package domainapp.webapp.custom.restapi;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.isis.applib.services.user.UserMemento;
import org.apache.isis.applib.services.xactn.TransactionService;
import org.apache.isis.commons.functional.Result;
import org.apache.isis.core.interaction.session.InteractionFactory;
import org.apache.isis.core.security.authentication.Authentication;
import org.apache.isis.core.security.authentication.standard.SimpleAuthentication;

import lombok.RequiredArgsConstructor;

import domainapp.modules.simple.dom.so.SimpleObject;
import domainapp.modules.simple.dom.so.SimpleObjects;

@RestController
@RequiredArgsConstructor
class CustomController {

  private final InteractionFactory interactionFactory;
  private final TransactionService transactionService;
  private final SimpleObjects repository;

  @GetMapping("/custom/simpleObjects")
  List<SimpleObject> all() {
    return callAuthenticated(newAuthentication(), () -> repository.listAll()).orElseFail();
  }

  private SimpleAuthentication newAuthentication() {
    return SimpleAuthentication.validOf(UserMemento.ofName("sven"));
  }

  private <T> Result<T> callAuthenticated(
          final Authentication authentication,
          final Callable<T> task) {
    return interactionFactory.callAuthenticated(
            authentication,
            () -> transactionService.callWithinCurrentTransactionElseCreateNew(task));
  }
}
