package com.eks.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class TouTiaoUtils {
    private static String cookieSetPathString = null;
    static {
        String filePathString = EksFileUtils.getPathBaseProject("extra/driver/chromedriver_win32/chromedriver.exe");
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,filePathString);
        cookieSetPathString = EksFileUtils.getPathBaseProject("extra/object/cookieSet");
    }
    public static WebDriver login(List<String> argumentStringList){
        String[] argumentStringArray = argumentStringList.toArray(new String[argumentStringList.size()]);
        return login(argumentStringArray);
    }
    public static WebDriver login(String... argumentStringArray){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(argumentStringArray);
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        WebDriver.Options options = webDriver.manage();
        WebDriver.Window window = options.window();
        window.maximize();
        webDriver.get("https://www.toutiao.com/");

        Set<Cookie> cookieSet = SerializeUtils.readObject(cookieSetPathString);
        if (cookieSet != null && cookieSet.size() > 0){
            for(Cookie cookie : cookieSet){
                options.addCookie(cookie);
            }
        }
        WebDriver.Navigation navigation = webDriver.navigate();
        navigation.refresh();
        WebDriverWait webDriverWait = new WebDriverWait(webDriver,60);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".name")));

        cookieSet = options.getCookies();
        if (cookieSet != null && cookieSet.size() > 0){
            SerializeUtils.writeObject(cookieSetPathString,cookieSet);
        }
        return webDriver;
    }
    public static void sendKeys(WebDriver webDriver,String cssSelectorString,String contentString){
        WebElement webElement = webDriver.findElement(By.cssSelector(cssSelectorString));
        webElement.sendKeys(contentString);
    }
    public static void click(WebDriver webDriver,int xOffset, int yOffset){
        Actions actions = new Actions(webDriver);
        WebElement webElement = webDriver.findElement(By.cssSelector("body"));
        actions.moveToElement(webElement,xOffset,yOffset).click();
        actions.release();
        actions.perform();
    }
    public static Object executeScript(WebDriver webDriver,String scriptString){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor)webDriver;
        return javascriptExecutor.executeScript(scriptString);
    }
}