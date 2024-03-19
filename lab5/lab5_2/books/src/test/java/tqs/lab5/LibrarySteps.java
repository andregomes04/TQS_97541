package tqs.lab5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
public class LibrarySteps {
	Library library = new Library();
	List<Book> result = new ArrayList<>();
    List<Book> result2 = new ArrayList<>();

    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public LocalDateTime date(String year, String month, String day){
        return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day),0, 0);
    }
 
	@Given("a book with the title {string}, written by {string}, published in {date}")
	public void addNewBook(final String title, final String author, final LocalDateTime published) {
        Date date = Date.from(published.toInstant(ZoneOffset.UTC));
		Book book = new Book(title, author, date);
		library.addBook(book);
	}

    @Given("another book with the title {string}, written by {string}, published in {date}")
    public void another_book_with_the_title_written_by_published_in(String title, String author, LocalDateTime published) {
        Date date = Date.from(published.toInstant(ZoneOffset.UTC));
		Book book = new Book(title, author, date);
		library.addBook(book);
    }
 
	@When("the customer searches for books published between {date} and {date}")
	public void setSearchParameters(final LocalDateTime from, final LocalDateTime to) {
        Date start = Date.from(from.toInstant(ZoneOffset.UTC));
        Date end = Date.from(to.toInstant(ZoneOffset.UTC));
		result = library.findBooks(start, end);
	}
 
	@Then("{int} books should have been found")
	public void verifyAmountOfBooksFound(final int booksFound) {
		assertEquals(result.size(), booksFound);
	}
 
	@Then("Book {int} should have the title {string}")
	public void verifyBookAtPosition(final int position, final String title) {
		assertEquals(result.get(position - 1).getTitle(), title);
	}

    @When("the customer searches for books published with the title {string}")
	public void searchByDate(final String title) {
		result2 = library.findBooksbyTitle(title);
	}

    @Then("{int} book with that title should have been found")
	public void verifyAmountOfBooksFoundByTitle(final int books) {
		assertEquals(result2.size(), books);
	}

    @Then("Book {int} must have the title {string}")
	public void verifyBookFound(final int position, final String title) {
		assertEquals(result2.get(position - 1).getTitle(), title);
	}
}