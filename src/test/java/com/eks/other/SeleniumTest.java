package com.eks.other;

import com.eks.utils.EksFileUtils;
import com.eks.utils.SeleniumUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SeleniumTest {
    private final static String pathBaseProjectPath = EksFileUtils.getPathBaseProject("extra/driver/chromedriver_win32/84.0.4147.30/chromedriver.exe");
    public void test1(String hrefString) throws IOException {
        SeleniumUtils.setChromeDriverProperty(new File(pathBaseProjectPath));
        WebDriver webDriver = SeleniumUtils.getChromeDriver(true, false);
        webDriver.get(hrefString);
        WebElement webElement = webDriver.findElement(By.cssSelector("div.PostContent"));
        EksFileUtils.writeStringToFile(null, EksFileUtils.getFileBaseProject("system/temp/" + getName(hrefString) + ".txt").getAbsolutePath(), webElement.getAttribute("outerHTML"), false);
        webDriver.close();
    }
    public static String getName(String hrefString){
        String newNameString = hrefString.replaceAll("/", "_");
        newNameString = newNameString.replaceAll(":", "");
        return newNameString;
    }
    @Test
    public void test2(){
        SeleniumUtils.setChromeDriverProperty(new File(pathBaseProjectPath));
        WebDriver webDriver = SeleniumUtils.getChromeDriver(true, false);
        webDriver.get("https://www.lz13.cn/lizhi/lizhiwenzhang.html");
        List<WebElement> webElementList = SeleniumUtils.findElementsByCss(webDriver, "div.PostHead > span > h3 > a");
        List<String> hrefStringList = new ArrayList<>();
        for(WebElement webElement : webElementList){
            hrefStringList.add(webElement.getAttribute("href"));
        }
        webDriver.close();
        log.info("hrefStringList:{}", hrefStringList);
        for(String hrefString : hrefStringList){
            try {
                test1(hrefString);
            } catch (IOException e) {
                log.info("IOException:{}", e);
            }
        }
    }
    @Test
    public void test3() throws IOException, InterruptedException {
        SeleniumUtils.setChromeDriverProperty(new File(pathBaseProjectPath));
        WebDriver webDriver = SeleniumUtils.getChromeDriver(false, false);
        String filePathString = System.getProperty("user.dir") + "/extra/temp/temp.html";
        webDriver.get(filePathString);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Thread.sleep(1000);
        //调用截图方法
        File file = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "/extra/temp/" + DateFormatUtils.format(Calendar.getInstance(), "yyyyMMdd_HHmmss_SSS") + ".png"));
    }
}
