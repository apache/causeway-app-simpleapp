package domainapp.webapp.application.seed;

import java.util.function.Supplier;

import javax.inject.Inject;

import org.apache.isis.applib.services.appfeat.ApplicationFeatureId;
import org.apache.isis.commons.collections.Can;
import org.apache.isis.core.config.IsisConfiguration;
import org.apache.isis.extensions.secman.applib.permission.dom.ApplicationPermissionMode;
import org.apache.isis.extensions.secman.applib.permission.dom.ApplicationPermissionRule;
import org.apache.isis.extensions.secman.applib.role.fixtures.AbstractRoleAndPermissionsFixtureScript;
import org.apache.isis.extensions.secman.applib.user.dom.AccountType;
import org.apache.isis.extensions.secman.applib.user.fixtures.AbstractUserAndRolesFixtureScript;
import org.apache.isis.testing.fixtures.applib.fixturescripts.FixtureScript;

public class CustomRolesAndUsers extends FixtureScript {

    @Override
    protected void execute(ExecutionContext executionContext) {
        executionContext.executeChildren(this,
                new SimpleModuleSuperuserRole(),
                new SvenUser());
    }

    private static class SimpleModuleSuperuserRole extends AbstractRoleAndPermissionsFixtureScript {

        public static final String ROLE_NAME = "simple-superuser";

        public SimpleModuleSuperuserRole() {
            super(ROLE_NAME, "Permission to use everything in the 'simple' module");
        }

        @Override
        protected void execute(ExecutionContext executionContext) {
            newPermissions(
                    ApplicationPermissionRule.ALLOW,
                    ApplicationPermissionMode.CHANGING,
                    Can.of(ApplicationFeatureId.newNamespace("simple"))
            );
        }
    }

    private static class SvenUser extends AbstractUserAndRolesFixtureScript {
        public SvenUser() {
            super(() -> "sven", () -> "pass", () -> AccountType.LOCAL, new RoleSupplier());
        }

        private static class RoleSupplier implements Supplier<Can<String>> {
            @Override
            public Can<String> get() {
                return Can.of(
                        isisConfiguration.getExtensions().getSecman().getSeed().getRegularUser().getRoleName(), // built-in stuff
                        SimpleModuleSuperuserRole.ROLE_NAME
                        );
            }
            @Inject IsisConfiguration isisConfiguration;
        }
    }

}
