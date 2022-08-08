import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class GoogleSearchTest extends BaseTest {

    @Test
    public void verifyThirdPageLinksCount() throws IOException {
        //initiate variable for count validation
        int thirdPageCount, secondPageLinkCount;
        //get search test data from Excel file
        keyWords.getSearchTestData();
        //scroll down till the navigator
        keyWords.scrollDown();
        //navigate to the next button to second page
        keyWords.getNextBtnClicked();
        //get the links count for second page
        secondPageLinkCount = keyWords.getPageCount();
        //scroll down till the navigator
        keyWords.scrollDown();
        //navigate to the next button to third page
        keyWords.getNextBtnClicked();
        //Thread.sleep(300);
        //get the links count for Third page
        thirdPageCount = keyWords.getPageCount();
        //validate the second page count versus the third count
        assertEquals(thirdPageCount, secondPageLinkCount);
    }

    @AfterTest
    public void tearDown() {
        keyWords.closeBrowser();
    }
}

