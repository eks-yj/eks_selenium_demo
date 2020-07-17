package com.eks.utils;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class TouTiaoUtilsTest {
    @Test
    public void test1() throws InterruptedException {
        List<String> argumentStringList = new ArrayList<>();
        //设置为 headless 模式
//        argumentStringList.add("--headless");
        //以中文utf-8编码格式启动浏览器
        argumentStringList.add("--lang=zh_CN.UTF-8");
        //设置浏览器最大化,其实此处无必要，直接在代码中设置最大化即可，不建议使用
        argumentStringList.add("--start-maximized");
        WebDriver webDriver = TouTiaoUtils.login(argumentStringList);
        Thread.sleep(3000);
        TouTiaoUtils.sendKeys(webDriver, ".title-input","Hello");
        Thread.sleep(3000);
        WebElement webElement = webDriver.findElement(By.cssSelector(".show-image-uploader.show-uploader"));
        webElement.click();
        Thread.sleep(3000);
        String filePathString = FileUtils.getPathBaseProjectPath("extra/image/gratisography/gratisography-157H.jpg");
        TouTiaoUtils.sendKeys(webDriver, "#fileElem",filePathString);
        Thread.sleep(3000);
        WebDriver.Options options = webDriver.manage();
        WebDriver.Window window = options.window();
        Dimension dimension = window.getSize();
        int heightInt = dimension.getHeight();
        while (true){
            Thread.sleep(3000);
            TouTiaoUtils.click(webDriver,1,heightInt / 2);
            TouTiaoUtils.executeScript(webDriver,"console.log('hello world')");
        }
    }
}