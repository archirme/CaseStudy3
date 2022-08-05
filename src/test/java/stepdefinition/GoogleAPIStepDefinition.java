package stepdefinition;

import java.io.IOException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import reusables.GoogleAPIRe;

public class GoogleAPIStepDefinition {

	GoogleAPIRe go=new GoogleAPIRe();
	
	// Post/Create Methods are below:
	
	@Given("^User establish the server connection$")
	public void connection() throws IOException
	{
		go.connect();
	}

	@When("^User provides all request details$")
	public void postReqDetails()
	{
		go.createReqInput();
	}
	
	@And("^User send the request to the server$")
	public void postReqSend()
	{
		go.postreq();
	}
	
	@Then("^User should get the proper server response$")
	public void validServerResponse()
	{
		go.postResValid();
	}
	

	@And("^User should be able to retrieve the created data from database$")
	public void getReq() throws IOException
	{
		go.getReq();
	}
	
	
	@And("^User should be able to match the data$")
	public void getReqPostInput()
	{
		go.getAssertion();
	}

	// Update Methods are below:
	
	@When("^User provides all request details for Update Request$")
	public void updateReqDetails()
	{
		go.createUpdateReqInput();
	}

	@And("^User send the update request to the server$")
	public void updateReqSend()
	{
		go.updatereq();
	}

	@Then("^User should get the proper server response for update$")
	public void validUpdateServerResponse()
	{
		go.updateResValid();
	}

	@And("^User should be able to retrieve the updated data from database$")
	public void getReqUpdate() throws IOException
	{
		go.getReq();
	}
	
	@And("^User should be able confirm that updated address is available in database$")
	public void getReqPostUpdate()
	{
		go.getUpdateAssertion();
	}
	
// Delete Methods are below:
	
	@When("^User provides all request details for Delete Request$")
	public void deleteReqDetails()
	{
		go.createDelReqInput();
	}

	@And("^User send the delete request to the server$")
	public void delReqSend()
	{
		go.delreq();
	}

	@Then("^User should get the proper server response for delete$")
	public void validDelServerResponse()
	{
		go.delResValid();
	}

	@And("^User should not be able to retrieve the deleted data from database$")
	public void getReqDel() throws IOException
	{
		go.getReq();
	}
	
	@And("^User should be able confirm that placeid not available in database$")
	public void getReqPostDel()
	{
		go.getDelAssertion();
	}

	
}
