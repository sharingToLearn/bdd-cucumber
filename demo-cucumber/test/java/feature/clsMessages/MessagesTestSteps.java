package feature.clsMessages;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class MessagesTestSteps {

	private static String url;

	private HttpResponse response;

	@Given("^A url \"([^\"]*)\"$")
	public void a_url(String arg1) throws Throwable {
		url = arg1;
	}

	@When("^User goes to above URL with method type as \"([^\"]*)\"$")
	public void user_goes_to_above_URL_with_method_type_as(String arg1) throws Throwable {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(url);
		getRequest.addHeader("accept", "application/json");
		response = httpClient.execute(getRequest);

	}

	
	
	@Then("^response should be \"([^\"]*)\"$")
	public void response_should_be(String arg1) throws Throwable {
		Assert.assertEquals(arg1, convertStreamToString(response.getEntity().getContent()));
	}

	@Then("^status should be \"([^\"]*)\"$")
	public void status_should_be(String arg1) throws Throwable {
		Assert.assertEquals(Integer.parseInt(arg1), response.getStatusLine().getStatusCode());
	}
	
	static String convertStreamToString(java.io.InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}

}
