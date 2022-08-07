import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;

public class BaseTest{
    public WebDriver driver;
    protected GoogleSearch googleSearch;

    protected Initialization browserInit = new Initialization();
    //protected ActionsKeyWords keyWords ;

    public String getScreenshotPath(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        String source = ts.getScreenshotAs(OutputType.BASE64);
        String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
        FileUtils.copyFile(new File(source), new File(destinationFile));
        return destinationFile;
    }

    //open https://www.google.com
    @BeforeClass
    public void baseTest() throws IOException {
        //keyWords = new ActionsKeyWords();
        //initiate driverType
         driver = initDriver();
        //get google URL
        navigateToUrl();
        //creating new instance of GoogleSearch
        googleSearch = new GoogleSearch(driver);
    }

    public WebDriver initDriver() throws IOException {
        return browserInit.initialDriver();
    }

    public void navigateToUrl(){
        //get google URL
        this.driver.get(browserInit.getDataPropertiesProviders().getUrl());
        // maximize window
        this.driver.manage().window().maximize();
    }
}
