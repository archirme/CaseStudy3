package runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	features = "src/test/java/features/GoogleAPI.feature",
	glue= {"stepdefinition"},
	plugin = {"pretty",
			"html:target/cucumber-reports/cucumber.html", 
			"json:target/cucumber-reports/cucumber.json"}
		)

public class TestRunner extends AbstractTestNGCucumberTests {

}
