package cucumber.selenium.demo.steps;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanFactory {

    @Bean
    public WebDriver getWebDriver() {
	System.getProperties().setProperty("webdriver.chrome.driver", "bin/chromedriver");
	
	ChromeOptions co = new ChromeOptions();
	co.addArguments("--test-type");
	co.addArguments("--disable-extensions");

	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	capabilities.setCapability(ChromeOptions.CAPABILITY, co);
	capabilities.setCapability("chrome.switches", Arrays.asList("--allow-insecure-localhost"));

	return new ChromeDriver(capabilities);
    }
}
