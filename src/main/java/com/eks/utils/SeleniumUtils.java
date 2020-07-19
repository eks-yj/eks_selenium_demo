package com.eks.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SeleniumUtils {
    public static void setChromeDriverProperty(File chromeDriverFile){
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, chromeDriverFile.getAbsolutePath());
    }
    public static WebDriver getChromeDriver(Boolean headlessBoolean, Boolean verboseBoolean){
        return getChromeDriver(headlessBoolean, verboseBoolean, null);
    }
    public static WebDriver getChromeDriver(Boolean headlessBoolean, Boolean verboseBoolean, String whitelistedIpsString){
        List<String> argumentStringList = new ArrayList<>();
        if (headlessBoolean != null && headlessBoolean){
            argumentStringList.add("--headless");
            argumentStringList.add("--disable-gpu");
        }
        ChromeDriverService.Builder builder = new ChromeDriverService.Builder();
        if (StringUtils.isNotBlank(whitelistedIpsString)){
            builder.withWhitelistedIps(whitelistedIpsString);
        }
        if (verboseBoolean != null && verboseBoolean){
            builder.withVerbose(true);
        }
        return getChromeDriver(argumentStringList, builder.build());
    }
    public static WebDriver getChromeDriver(List<String> argumentStringList, ChromeDriverService chromeDriverService){
        ChromeOptions chromeOptions = new ChromeOptions();
        if (CollectionUtils.isNotEmpty(argumentStringList)) {
            chromeOptions.addArguments(argumentStringList.toArray(new String[argumentStringList.size()]));
        }
        return getChromeDriver(chromeOptions, chromeDriverService);
    }
    public static WebDriver getChromeDriver(ChromeOptions chromeOptions, ChromeDriverService chromeDriverService){
        return new ChromeDriver(chromeDriverService, chromeOptions);
    }
    public static Object executeScript(WebDriver webDriver, String scriptString){
        return ((JavascriptExecutor)webDriver).executeScript(scriptString);
    }
    public static WebElement findElementByCss(SearchContext searchContext, String cssSelectorString){
        return searchContext.findElement(By.cssSelector(cssSelectorString));
    }
    public static List<WebElement> findElementsByCss(SearchContext searchContext, String cssSelectorString){
        return searchContext.findElements(By.cssSelector(cssSelectorString));
    }
    public static WebElement findElementByClassName(SearchContext searchContext, String classNameString){
        return searchContext.findElement(By.className(classNameString));
    }
    public static List<WebElement> findElementsByClassName(SearchContext searchContext, String classNameString){
        return searchContext.findElements(By.className(classNameString));
    }
}
