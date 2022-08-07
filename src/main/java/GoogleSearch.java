import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoogleSearch {

    public WebDriver driver ;

    public  GoogleSearch(WebDriver driver){
        this.driver = driver;
    }
    // declaring WebElements
    private By searchField = By.name("q");
    private By nextBtn = By.xpath("//td[@aria-level='3']");
    private By searchResultsLink = By.xpath("//a/h3");

    public WebElement getSearchField() {
        return driver.findElement(searchField);
    }

    public WebElement getNextBtn(){
        return driver.findElement(nextBtn);
    }

    public List<WebElement> getSearchResultsLinks(){
        return driver.findElements(searchResultsLink);
    }
}
