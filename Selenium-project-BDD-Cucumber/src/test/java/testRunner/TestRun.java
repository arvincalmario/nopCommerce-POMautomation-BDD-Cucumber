package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		features = {".//features/"},
		glue = "stepDefinitions",
		dryRun = false,
		plugin = {"pretty","html:test-output.html"}
		//tags = {"@sanity","@regression"} //OR statement, if AND it should be {"@sanity" , "@regression}
		
		)

public class TestRun {

}
