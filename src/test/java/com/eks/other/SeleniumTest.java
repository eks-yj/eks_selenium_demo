package com.eks.other;

import com.eks.utils.FileUtils;
import com.eks.utils.SeleniumUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SeleniumTest {
    @Test
    public void test1(){
        String pathBaseProjectPath = FileUtils.getPathBaseProjectPath("extra/driver/chromedriver_win32/84.0.4147.30/chromedriver.exe");
        SeleniumUtils.setChromeDriverProperty(new File(pathBaseProjectPath));
        WebDriver webDriver = SeleniumUtils.getChromeDriver(true, false);
        webDriver.manage().window().maximize();
        //与浏览器同步非常重要，必须等待浏览器加载完毕
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //下面这句等价于webDriver.navigate().to("www.baidu.com");
        webDriver.navigate().to("https://www.lz13.cn/zheli/184189.html");
        //在输入框中填写要搜索的内容
        WebElement webElement = webDriver.findElement(By.cssSelector("#node-8890 > div.PostContent"));
        log.info("webElement:{}", webElement.getAttribute("outerHTML"));
    }
}
