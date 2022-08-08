import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import utilities.ExcelUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ActionsKeyWords extends BaseTest{

    public void navigateToUrl() {
        //get google URL
        driver.get(browserInit.getDataPropertiesProviders().getUrl());
        // maximize window
        driver.manage().window().maximize();
    }

    public void scrollDown(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,4000)");

    }

    public void getSearchTestData() throws IOException {
        List<String> searchData = ExcelUtils.getOneRowData(browserInit.getDataPropertiesProviders().excelFilePath(),"Sheet1",
                Arrays.asList("TestCase","testData"),Arrays.asList("verify search data"));

        googleSearch.getSearchField().sendKeys(searchData.get(0), Keys.ENTER);
    }

    public int getPageCount(){
        return googleSearch.getSearchResultsLinks().size();
    }

    public void getNextBtnClicked(){
        googleSearch.getNextBtn().click();
    }

    public void closeBrowser(){
        driver.close();
    }
}
