package com.eks.other;

import com.eks.utils.EksFileUtils;
import com.eks.utils.SeleniumUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
}
