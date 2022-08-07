package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataPropertiesProviders {

    static String browser, url, remoteUrl, browserHub;
    public Properties pro;

    public String excelFilePath() {
        return System.getProperty("user.dir")+ "\\src\\main\\resources\\testDataVooda.xlsx";
    }

    public void setDataProvider() throws IOException {
        pro = new Properties();
        FileInputStream xmlDataFile = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\utilities\\data.properties");
        pro.load(xmlDataFile);
        browser = pro.getProperty("browser");
        url = pro.getProperty("url");
        remoteUrl = pro.getProperty("remoteUrl");
        browserHub = pro.getProperty("browserHub");

    }

    public String getBrowser() {
        return browser;
    }

    public String getUrl() {
        return url;
    }

    public String getRemoteUrl(){
        return remoteUrl;
    }

    public String getBrowserHub(){
        return browserHub;
    }

}



