package com.eks.utils;

import com.eks.thread.PinDuoDuoThread;
import com.eks.thread.PinDuoDuoThread2;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PinDuoDuoUtilsTest {
    @Test
    public void test1() throws InterruptedException, IOException, UnsupportedFlavorException {
        WebDriver webDriver = SeleniumUtils2.getWebDriver();
        String urlString = "https://jinbao.pinduoduo.com/promotion/single-promotion";
        String cookieFileNameString = SeleniumUtils2.loginByCookie(webDriver, urlString);
        //登录名称
        Boolean loginBoolean = SeleniumUtils2.checkLogin(webDriver, 30, "#__next > div.jsx-1070787851.container > section > div > div.jsx-322011365.login-wrapper.right > div > span.jsx-322011365.welcome-text");
        if (loginBoolean){
            SeleniumUtils2.writeCookieToFile(webDriver,cookieFileNameString);
        }
        Thread.sleep(2000);
        //知道了
        SeleniumUtils2.click(webDriver,".animation.button");
        //立即推广
        //获取所有商品
        List<WebElement> webElementList = SeleniumUtils2.findElements(webDriver, "#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.single-promotion-list > a:nth-child(1) > div > div > div.goods-info > div.sale-info > div.btn");
        SeleniumUtils2.click(webDriver,webElementList.get(0));
        Thread.sleep(2000);
        //确定
        SeleniumUtils2.click(webDriver,"#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.pid-modal-wrapper > div > div.content > div.btn-wrapper > div:nth-child(1)");
        Thread.sleep(2000);
        //双人团
        SeleniumUtils2.click(webDriver,"#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.share-info-wrapper > div > div > div.tab > ul:nth-child(2) > li:nth-child(1)");
        Thread.sleep(2000);
        //小程序
        SeleniumUtils2.click(webDriver,"#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.share-info-wrapper > div > div > div.tab > ul:nth-child(1) > li:nth-child(5)");
        Thread.sleep(5000);
        //复制图片
        SeleniumUtils2.click(webDriver,"#copy-img-btn");
        //存储图片
        String companyNameString = SeleniumUtils2.getCompanyName(urlString);
        String basalFilePathString = FileUtils.getPathBaseProjectPath("extra/image/" + companyNameString + "/");
        Image image = ClipboardUtils.getClipboardImage();
        String newFilePathString = FileUtils.getPathBaseProjectPath(basalFilePathString + "temp.png");
        ImageIO.write((RenderedImage) image, "png", new File(newFilePathString));
        while (true){}
    }
    @Test
    public void test2() {
        new PinDuoDuoThread().start();
        new PinDuoDuoThread2().start();
        while (true){}
    }
    @Test
    public void test3() throws InterruptedException {
        WebDriver webDriver = SeleniumUtils2.getWebDriver();
        String urlString = "https://jinbao.pinduoduo.com/promotion/single-promotion";
        String cookieFileNameString = SeleniumUtils2.loginByCookie(webDriver, urlString);
        //登录名称
        Boolean loginBoolean = SeleniumUtils2.checkLogin(webDriver, 30, "#__next > div.jsx-1070787851.container > section > div > div.jsx-322011365.login-wrapper.right > div > span.jsx-322011365.welcome-text");
        if (loginBoolean){
            SeleniumUtils2.writeCookieToFile(webDriver,cookieFileNameString);
        }
        Thread.sleep(2000);
        //知道了
        SeleniumUtils2.click(webDriver,".animation.button");
        Thread.sleep(2000);
        List<WebElement> webElementList = SeleniumUtils2.findElements(webDriver, "#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.single-promotion-list > a");
        WebElement webElement = webElementList.get(0);
        Thread.sleep(2000);
        String hrefString = webElement.getAttribute("href");
        String[] hrefStringArray = hrefString.split("=");
        String goodsIdString = hrefStringArray[hrefStringArray.length - 1];
        System.out.println("goodsID:" + goodsIdString);
        //立即推广
        WebElement element3 = SeleniumUtils2.findElement(webElement, "div > div > div.goods-info > div.sale-info > div.btn");
        SeleniumUtils2.click(webDriver,element3);
        //确定
        SeleniumUtils2.click(webDriver,"#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.pid-modal-wrapper > div > div.content > div.btn-wrapper > div:nth-child(1)");
        Thread.sleep(2000);
        //长链接
        WebElement element = SeleniumUtils2.findElement(webDriver, "#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.share-info-wrapper > div > div > div.copy-wrapper > div > div > p.weChatLongUrl");
        System.out.println("长链接:" + element.getAttribute("innerHTML"));
        Thread.sleep(50000);
        //小程序
        SeleniumUtils2.click(webDriver,"#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.share-info-wrapper > div > div > div.tab > ul:nth-child(1) > li:nth-child(5)");
        Thread.sleep(5000);
        WebElement element2 = SeleniumUtils2.findElement(webDriver, "#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.share-info-wrapper > div > div > div.copy-wrapper > div > img");
        System.out.println("小程序:" + element2.getAttribute("src"));
    }
    @Test
    public void test4() throws InterruptedException {
        WebDriver webDriver = SeleniumUtils2.getWebDriver();
        String urlString = "https://jinbao.pinduoduo.com/promotion/single-promotion";
        String cookieFileNameString = SeleniumUtils2.loginByCookie(webDriver, urlString);
        //登录名称
        Boolean loginBoolean = SeleniumUtils2.checkLogin(webDriver, 30, "#__next > div.jsx-1070787851.container > section > div > div.jsx-322011365.login-wrapper.right > div > span.jsx-322011365.welcome-text");
        if (loginBoolean){
            SeleniumUtils2.writeCookieToFile(webDriver,cookieFileNameString);
        }
        SeleniumUtils2.executeScript(webDriver,"document.body.style.zoom = 0.7");
        Thread.sleep(2000);
        //知道了
        SeleniumUtils2.clickByJs(webDriver,".animation.button");
        //获取所有的"立即推广"按钮
        List<WebElement> btnWebElementList = SeleniumUtils2.findElements(webDriver, "#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.single-promotion-list > a > div > div > div.goods-info > div.sale-info > div.btn");
        Thread.sleep(2000);
        for(WebElement webElement : btnWebElementList){
            //单击立即推广
            SeleniumUtils2.clickByJs(webDriver,webElement);
            Thread.sleep(2000);
            //单击确定
            SeleniumUtils2.clickByJs(webDriver,"#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.pid-modal-wrapper > div > div.content > div.btn-wrapper > div:nth-child(1)");
            Thread.sleep(2000);
            //商品介绍_包邮信息
            WebElement element1 = SeleniumUtils2.findElement(webDriver, "#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.share-info-wrapper > div > div > div.copy-wrapper > div > div > div > p:nth-child(1)");
            System.out.println("商品介绍_包邮信息:" + element1.getAttribute("innerHTML"));
            Thread.sleep(1000);
            //商品介绍_价格信息
            WebElement element2 = SeleniumUtils2.findElement(webDriver, "#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.share-info-wrapper > div > div > div.copy-wrapper > div > div > div > p:nth-child(2)");
            System.out.println("商品介绍_价格信息:" + element2.getAttribute("innerHTML"));
            Thread.sleep(1000);
            //商品介绍_内部优惠价
            WebElement element3 = SeleniumUtils2.findElement(webDriver, "#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.share-info-wrapper > div > div > div.copy-wrapper > div > div > div > p:nth-child(3)");
            System.out.println("商品介绍_内部优惠价:" + element3.getAttribute("innerHTML"));
            Thread.sleep(1000);
            //短链接
            WebElement element4 = SeleniumUtils2.findElement(webDriver, "#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.share-info-wrapper > div > div > div.copy-wrapper > div > div > p.shortUrl");
            System.out.println("短链接:" + element4.getAttribute("innerHTML"));
            Thread.sleep(1000);
            //点击小程序标题
            SeleniumUtils2.clickByJs(webDriver,"#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.share-info-wrapper > div > div > div.tab > ul:nth-child(1) > li:nth-child(5)");
            Thread.sleep(5000);
            WebElement element5 = SeleniumUtils2.findElement(webDriver, "#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.share-info-wrapper > div > div > div.copy-wrapper > div > img");
            System.out.println("小程序:" + element5.getAttribute("src"));
            Thread.sleep(1000);
            //点击取消
            SeleniumUtils2.clickByJs(webDriver,"#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.share-info-wrapper > div > div > div.btn-wrapper > div:nth-child(2)");
            Thread.sleep(10000);
        }
    }
    @Test
    public void test5() throws InterruptedException {
        WebDriver webDriver = SeleniumUtils2.getWebDriver();
        String cookieFileNameString = SeleniumUtils2.loginByCookie(webDriver, "https://jinbao.pinduoduo.com/promotion/single-promotion");
        //登录名称
        Boolean loginBoolean = SeleniumUtils2.checkLogin(webDriver, 30, "#__next > div.jsx-1070787851.container > section > div > div.jsx-322011365.login-wrapper.right > div > span.jsx-322011365.welcome-text");
        if (loginBoolean){
            SeleniumUtils2.writeCookieToFile(webDriver,cookieFileNameString);
        }
        Thread.sleep(2000);
        //知道了
        SeleniumUtils2.click(webDriver,".animation.button");
        //立即推广
        List<WebElement> webElementList = SeleniumUtils.findElements(webDriver, "#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.single-promotion-list > a:nth-child(1) > div > div > div.goods-info > div.sale-info > div.btn");
        SeleniumUtils2.click(webDriver,webElementList.get(0));
        Thread.sleep(2000);
        //确定
        SeleniumUtils2.click(webDriver,"#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.pid-modal-wrapper > div > div.content > div.btn-wrapper > div:nth-child(1)");
        Thread.sleep(2000);
        //双人团
        SeleniumUtils2.click(webDriver,"#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.share-info-wrapper > div > div > div.tab > ul:nth-child(2) > li:nth-child(1)");
        Thread.sleep(2000);
        //小程序
        SeleniumUtils2.click(webDriver,"#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.share-info-wrapper > div > div > div.tab > ul:nth-child(1) > li:nth-child(5)");
        Thread.sleep(5000);
        //复制图片
        SeleniumUtils2.click(webDriver,"#copy-img-btn");
        RobotUtils.clickMouse(500,500, 2277, 284);
        RobotUtils.pressKey(500, false, KeyEvent.VK_CONTROL,KeyEvent.VK_V);
        RobotUtils.pressKey(500, true,KeyEvent.VK_ENTER);
        while (true){}
    }
}
