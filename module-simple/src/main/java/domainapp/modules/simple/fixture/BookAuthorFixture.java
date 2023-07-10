package domainapp.modules.simple.fixture;

import javax.inject.Inject;

import org.apache.causeway.testing.fixtures.applib.fixturescripts.FixtureScript;

import domainapp.modules.simple.dom.tree.Authors;
import domainapp.modules.simple.dom.tree.Books;

public class BookAuthorFixture extends FixtureScript {
	
	@Inject Authors authors;
	@Inject Books books;

	@Override
	protected void execute(ExecutionContext executionContext) {
		books.create("Book 1", authors.create("Author 1"));		
	}

}
