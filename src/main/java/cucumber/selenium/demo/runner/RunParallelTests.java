package cucumber.selenium.demo.runner;

import org.junit.runner.RunWith;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import com.github.mkolisnyk.cucumber.runner.ExtendedParallelCucumber;

import cucumber.api.CucumberOptions;

@RunWith(ExtendedParallelCucumber.class)
@ExtendedCucumberOptions(threadsCount =1)
@CucumberOptions(
        plugin = {
            "html:target/cucumber"
        },
        features = "target/classes/features/parallel",
        glue = "classpath:cucumber.selenium.demo.steps",
        tags = "@parallel")
public class RunParallelTests {
}
