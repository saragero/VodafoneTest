import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import utilities.ExcelUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class GoogleSearchTest extends BaseTest{

    @Test
    public void verifyThirdPageLinksCount() throws IOException {
        //initiate variable for count validation
        int thirdPageCount, secondPageLinkCount;
        //get search test data from Excel file
        getSearchTestData();
        //scroll down till the navigator
        scrollDown();
        //navigate to the next button to second page
        getNextBtnClicked();
        //get the links count for second page
        secondPageLinkCount = getPageCount();
        //scroll down till the navigator
        scrollDown();
        //navigate to the next button to third page
        getNextBtnClicked();
        //Thread.sleep(300);
        //get the links count for Third page
        thirdPageCount = getPageCount();
        //validate the second page count versus the third count
        assertEquals(thirdPageCount,secondPageLinkCount);

    }


    @AfterTest
    public void tearDown(){
        closeBrowser();
}

// start from here trying to do the keyword driven framework
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

