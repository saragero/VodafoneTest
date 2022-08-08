
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilities.DataPropertiesProviders;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.Browser.IE;

public class Initialization extends DataPropertiesProviders {
    private WebDriver driver;
    private DataPropertiesProviders dataPropertiesProviders;

    private static Initialization instance;

    static {
        try {
            instance = new Initialization();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //make the constructor private so that this class cannot be
    //instantiated
    private Initialization() throws IOException {
        initialDriver();
    }

    public static Initialization getInstance(){
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public DataPropertiesProviders getDataPropertiesProviders() {
        return dataPropertiesProviders;
    }
     //initiate browser type
    protected WebDriver initialDriver() throws IOException {
        dataPropertiesProviders = new DataPropertiesProviders();
        dataPropertiesProviders.setDataProvider();
        //for chrome type
        if (dataPropertiesProviders.getBrowser().equals("chrome")){
            ChromeOptions options = new ChromeOptions();
            if (dataPropertiesProviders.getBrowser().contains("headless")) {
                options.addArguments("headless");
            }
            this.driver =new ChromeDriver(options);
        }//for firefox type
        else if (dataPropertiesProviders.getBrowser().equals("firefox")){
            this.driver = new FirefoxDriver();
        }//for IE type
        else if (dataPropertiesProviders.getBrowser().equals("IE")){
            InternetExplorerOptions capabilities = new InternetExplorerOptions();
            capabilities.ignoreZoomSettings();
            this.driver= new InternetExplorerDriver(capabilities);
        }
        else if (dataPropertiesProviders.getBrowser().contains("hub")){
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setBrowserName(dataPropertiesProviders.getBrowserHub());
            this.driver = new RemoteWebDriver(new URL(dataPropertiesProviders.getRemoteUrl()), caps);

        }
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }
}
