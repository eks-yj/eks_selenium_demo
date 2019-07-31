package com.eks.utils;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.event.KeyEvent;
import java.util.List;

public class PinDuoDuoUtilsTest {
    @Test
    public void test1() throws InterruptedException {
        WebDriver webDriver = SeleniumUtils.getWebDriver();
        String cookieFileNameString = SeleniumUtils.loginByCookie(webDriver, "https://jinbao.pinduoduo.com/promotion/single-promotion");
        //登录名称
        Boolean loginBoolean = SeleniumUtils.checkLogin(webDriver, 30, "#__next > div.jsx-1070787851.container > section > div > div.jsx-322011365.login-wrapper.right > div > span.jsx-322011365.welcome-text");
        if (loginBoolean){
            SeleniumUtils.writeCookieToFile(webDriver,cookieFileNameString);
        }
        Thread.sleep(2000);
        //知道了
        SeleniumUtils.click(webDriver,".animation.button");
        //立即推广
        List<WebElement> webElementList = SeleniumUtils.findElements(webDriver, "#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.single-promotion-list > a:nth-child(1) > div > div > div.goods-info > div.sale-info > div.btn");
        SeleniumUtils.click(webDriver,webElementList.get(0));
        Thread.sleep(2000);
        //确定
        SeleniumUtils.click(webDriver,"#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.pid-modal-wrapper > div > div.content > div.btn-wrapper > div:nth-child(1)");
        Thread.sleep(2000);
        //双人团
        SeleniumUtils.click(webDriver,"#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.share-info-wrapper > div > div > div.tab > ul:nth-child(2) > li:nth-child(1)");
        Thread.sleep(2000);
        //小程序
        SeleniumUtils.click(webDriver,"#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.share-info-wrapper > div > div > div.tab > ul:nth-child(1) > li:nth-child(5)");
        Thread.sleep(5000);
        //复制图片
        SeleniumUtils.click(webDriver,"#copy-img-btn");
        RobotUtils.clickMouse(500,500, 2277, 284);
        RobotUtils.pressKey(500, false, KeyEvent.VK_CONTROL,KeyEvent.VK_V);
        RobotUtils.pressKey(500, true,KeyEvent.VK_ENTER);
        while (true){}
    }
}
