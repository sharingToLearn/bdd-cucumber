package todo;

import org.springframework.http.HttpStatus;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.dsl.design.TestDesigner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DSPRestApiSteps {

	@CitrusResource
	private TestDesigner designer;

	private String payLoad;

	@Given("^I have a payload with \"([^\"]*)\"$")
	public void i_have_a_payload_with(String arg1) throws Throwable {
		payLoad = arg1;
	}

	@When("^I go to url \"([^\"]*)\" with method type \"([^\"]*)\"$")
	public void i_go_to_url_with_method_type(String url, String methodType) throws Throwable {
		if (methodType.equalsIgnoreCase("GET")) {
			designer.http()
				.client("dspTestClient")
				.get(url);
		} else {
			designer.http()
				.client("dspTestClient")
				.post(url)
				.payload(payLoad)
				.contentType("application/xml");
		}
	}

	@Then("^the response code should be \"([^\"]*)\" and response should be \"([^\"]*)\"$")
	public void the_response_code_should_be_and_response_should_be(String code, String response) throws Throwable {
		designer.http()
			.client("dspTestClient")
			.response(HttpStatus.valueOf(Integer.parseInt(code)))
			.payload(response);
	}

	@Then("^the value of \"([^\"]*)\" for query \"([^\"]*)\" should be \"([^\"]*)\"$")
	public void the_value_of_for_query_should_be(String columnName, String selectQuery, String expectedValue)
			throws Throwable {
		designer.query(DSPTestDataSource.getOracleDataSource())
			.statement(selectQuery)
			.validate(columnName, expectedValue);
	}

}
