package com.eks.thread;

import com.eks.utils.SeleniumUtils2;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Data//注解在类上;提供类所有非static且非final属性的get和set方法,final属性只提供get方法,此外还提供了equals、canEqual、hashCode、toString 方法
@EqualsAndHashCode(callSuper = false)//是否调用父类的equals和hachCode方法
@Accessors(chain = true)//chain=boolean值，默认false。如果设置为true，setter返回的是此对象，方便链式调用方法
@NoArgsConstructor//无参构造器
public class PinDuoDuoThread extends Thread{
    @Override
    public void run() {
        try {
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
            List<WebElement> webElementList = SeleniumUtils2.findElements(webDriver, "#__next > div.jsx-1070787851.container > main > div.single-promotion-wrapper.main-content > div.single-promotion-list > a");
            for(int i = 0,length = webElementList.size();i < length;i++){
                if (i % 2 == 0){
                    WebElement webElement = webElementList.get(i);
                    String hrefString = webElement.getAttribute("href");
                    String[] hrefStringArray = hrefString.split("=");
                    String goodsIdString = hrefStringArray[hrefStringArray.length - 1];
                    System.out.println("PinDuoDuoThread" + ",index=" + i +":" + goodsIdString);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
