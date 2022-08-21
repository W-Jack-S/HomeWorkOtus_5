package webDriverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.Locale;

public class WebDriverFactory {
    public static WebDriver getDriver(){

        String browser = System.getProperty("browser");

        if (browser == null) {
            WebDriverManager.chromedriver().setup();

            return new ChromeDriver();
        }

        switch (browser.toLowerCase(Locale.ROOT).trim()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "opera":
                WebDriverManager.operadriver().setup();
                return new OperaDriver();
            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();

        }
        return new ChromeDriver();
    }
}
