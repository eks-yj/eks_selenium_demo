package com.eks.other;

import com.eks.utils.FileUtils;
import com.eks.utils.SerializeUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Set;

public class OtherTest {
    private static String cookieSetPathString = null;
    @Before
    public void before(){
        String filePathString = FileUtils.generatePathBaseProjectPath("extra/driver/chromedriver_win32/chromedriver.exe");
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,filePathString);
        cookieSetPathString = FileUtils.generatePathBaseProjectPath("extra/object/cookieSet");
    }
    @Test
    public void test1(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.baidu.com/");
    }
    @Test
    public void test2(){
        ChromeOptions chromeOptions = new ChromeOptions();
        //以中文utf-8编码格式启动浏览器
        chromeOptions.addArguments("--lang=zh_CN.UTF-8");
        //设置浏览器最大化,其实此处无必要，直接在代码中设置最大化即可，不建议使用
        chromeOptions.addArguments("--start-maximized");
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        webDriver.get("https://www.baidu.com/");
    }
    @Test
    public void test3() throws InterruptedException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.baidu.cn");
        webDriver.manage().window().maximize();
        Thread.sleep(2000);
        webDriver.get("https://m.baidu.cn");
        webDriver.manage().window().setSize(new Dimension(480, 800));
        Thread.sleep(2000);
        webDriver.quit();
    }
    @Test
    public void test4() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //设置为 headless 模式
        chromeOptions.addArguments("--headless");
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().window().maximize();
        webDriver.get("https://www.baidu.com/");
        while (true){
            try {
                Thread.sleep(2000);
                String titleString = webDriver.getTitle();
                System.out.println(titleString);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void test5() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //设置为 headless 模式
//        chromeOptions.addArguments("--headless");
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().window().maximize();
        webDriver.get("https://www.baidu.com/");
        try {
            Thread.sleep(2000);
            String titleString = webDriver.getTitle();
            System.out.println(titleString);
            WebElement searchInputWebElement = webDriver.findElement(By.id("kw"));
            searchInputWebElement.sendKeys("Hello World");
            WebElement searchButtonWebElement = webDriver.findElement(By.id("su"));
            searchButtonWebElement.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true){}
    }
    @Test
    public void test6() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //设置为 headless 模式
//        chromeOptions.addArguments("--headless");
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().window().maximize();
        webDriver.get("https://www.toutiao.com/");

        WebDriver.Options options = webDriver.manage();

        WebDriverWait webDriverWait = new WebDriverWait(webDriver,60 * 10,1);
        ExpectedCondition<WebElement> expectedCondition = new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver tempWebDriver) {
                return tempWebDriver.findElement(By.className("name"));
            }
        };
        webDriverWait.until(expectedCondition);

        Set<Cookie> cookieSet = webDriver.manage().getCookies();
        for(Cookie cookie : cookieSet){
            System.out.println(String.format("%s -> %s -> %s -> %s -> %s",cookie.getDomain(), cookie.getName(), cookie.getValue(),cookie.getExpiry(),cookie.getPath()));
        }
        while (true){}
    }
    @Test
    public void test7() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //设置为 headless 模式
//        chromeOptions.addArguments("--headless");
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().window().maximize();
        webDriver.get("https://www.toutiao.com/");

        WebDriverWait webDriverWait = new WebDriverWait(webDriver,60 * 10,1);
        ExpectedCondition<WebElement> expectedCondition = new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver text) {
                return text.findElement(By.className("name"));
            }
        };
        webDriverWait.until(expectedCondition);
        try {
            Thread.sleep(2000);
            String titleString = webDriver.getTitle();
            System.out.println(titleString);
            WebElement titleInputWebElement = webDriver.findElement(By.className("title-input"));
            titleInputWebElement.sendKeys("Hello World");

            WebElement fileElemWebElement = webDriver.findElement(By.id("fileElem"));
            fileElemWebElement.sendKeys("E:\\EksCodeWorkspace\\eks_selenium_demo\\src\\main\\resources\\public\\image.png");
            ((JavascriptExecutor)webDriver).executeScript("uploadActionClick()");

            Thread.sleep(9000);
            WebElement uploadPublishWebElement = webDriver.findElement(By.className("upload-publish"));
            uploadPublishWebElement.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true){}
    }
    @Test
    public void test8(){
        String cookieSetPathString = "E:\\EksCodeWorkspace\\eks_selenium_demo\\extra\\object\\cookieSet";
        Set<Cookie> newCookieSet = SerializeUtils.readObject(cookieSetPathString);
        for(Cookie cookie : newCookieSet){
            System.out.println(String.format("%s -> %s -> %s -> %s -> %s",cookie.getDomain(), cookie.getName(), cookie.getValue(),cookie.getExpiry(),cookie.getPath()));
        }
    }
    @Test
    public void test9() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //设置为 headless 模式
//        chromeOptions.addArguments("--headless");
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        WebDriver.Options options = webDriver.manage();
        options.window().maximize();
        webDriver.get("https://www.toutiao.com/");
        Set<Cookie> cookieSet = null;
        try {
            cookieSet = SerializeUtils.readObject(cookieSetPathString);
            for(Cookie cookie : cookieSet){
                System.out.println(String.format("%s -> %s -> %s -> %s -> %s",cookie.getDomain(), cookie.getName(), cookie.getValue(),cookie.getExpiry(),cookie.getPath()));
                options.addCookie(cookie);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        WebDriver.Navigation navigation = webDriver.navigate();
        navigation.refresh();
        WebDriverWait webDriverWait = new WebDriverWait(webDriver,60 * 10,1);
        ExpectedCondition<WebElement> expectedCondition = new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver text) {
                return text.findElement(By.className("name"));
            }
        };
        webDriverWait.until(expectedCondition);

        cookieSet = options.getCookies();
        if (cookieSet != null){
            SerializeUtils.writeObject(cookieSetPathString,cookieSet);
        }

        try {
            Thread.sleep(2000);
            String titleString = webDriver.getTitle();
            System.out.println(titleString);
            WebElement titleInputWebElement = webDriver.findElement(By.className("title-input"));
            titleInputWebElement.sendKeys("Hello World");

//            WebElement fileElemWebElement = webDriver.findElement(By.id("fileElem"));
//            fileElemWebElement.sendKeys("E:\\EksCodeWorkspace\\eks_selenium_demo\\src\\main\\resources\\public\\image.png");
//            ((JavascriptExecutor)webDriver).executeScript("uploadActionClick()");
//
//            Thread.sleep(9000);
            WebElement uploadPublishWebElement1 = webDriver.findElement(By.cssSelector(".show-image-uploader.show-uploader"));
            uploadPublishWebElement1.click();
//            WebElement uploadPublishWebElement = webDriver.findElement(By.cssSelector(".upload-img-item.upload-img-add"));
//            uploadPublishWebElement.click();

            WebElement uploadPublishWebElement = webDriver.findElement(By.id("fileElem"));
            uploadPublishWebElement.sendKeys("D:\\20190112_1105_GitHub_NameSpace\\eks_selenium_demo\\extra\\image\\image.png");
            Thread.sleep(3000);
            uploadPublishWebElement = webDriver.findElement(By.id("fileElem"));
            uploadPublishWebElement.sendKeys("D:\\20190112_1105_GitHub_NameSpace\\eks_selenium_demo\\extra\\image\\image.png");
            Thread.sleep(3000);
            uploadPublishWebElement = webDriver.findElement(By.id("fileElem"));
            uploadPublishWebElement.sendKeys("D:\\20190112_1105_GitHub_NameSpace\\eks_selenium_demo\\extra\\image\\image.png");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true){}
    }
    @Test
    public void excelRead() throws Exception {
        String filePathString = FileUtils.generatePathBaseProjectPath("extra/xlsx/eks_dictum_note.xls");
        //创建输入流，接受目标excel文件
        FileInputStream fileInputStream = new FileInputStream(filePathString);
        //得到POI文件系统对象
        POIFSFileSystem poifsFileSystem = new POIFSFileSystem(fileInputStream);
        //得到Excel工作簿对象
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
        //得到Excel工作簿的第一页，即excel工作表对象
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
        //遍历工作表
        //遍历行对象
        for (Row row : hssfSheet) {
            //打印行索引
            int rowNum = row.getRowNum();
            System.out.println("第" + rowNum + "行");
            //遍历单元格对象
            Cell cell1 = row.getCell(0);
            if (cell1 != null) {
                System.out.println(cell1.toString() + "1=====");
            }
            for (Cell cell : row) {
                int columnIndex = cell.getColumnIndex();
                System.out.println(columnIndex + ":" + cell.toString() + "---");
            }
        }

    }
    @Test
    public void excelWrite() throws Exception {
        //获得Excel文件输出流
        FileOutputStream out = new FileOutputStream(new File("D:/C盘迁移/Desktop/编程日常/POI导出测试数据.xls"));
        //创建excel工作簿对象
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建excel页
        HSSFSheet sheet = wb.createSheet("POI导出测试");
        //创建表头
        HSSFRow row1 = sheet.createRow(0);
        //创建表头的单元格-------------------------------
        HSSFCell cell1_1 = row1.createCell(0);
        cell1_1.setCellValue("学号");
        HSSFCell cell1_2 = row1.createCell(1);
        cell1_2.setCellValue("姓名");
        HSSFCell cell1_3 = row1.createCell(2);
        cell1_3.setCellValue("年级");
        HSSFCell cell1_4 = row1.createCell(3);
        cell1_4.setCellValue("年龄");
        HSSFCell cell1_5 = row1.createCell(4);
        cell1_5.setCellValue("性别");
        //--------------------------------------------
        //写入一行内容：
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell2_1 = row2.createCell(0);
        cell2_1.setCellValue(1);
        HSSFCell cell2_2 = row2.createCell(1);
        cell2_2.setCellValue("阿荣");
        HSSFCell cell2_3 = row2.createCell(2);
        cell2_3.setCellValue("17(3)");
        HSSFCell cell2_4 = row2.createCell(3);
        cell2_4.setCellValue(20);
        HSSFCell cell2_5 = row2.createCell(4);
        cell2_5.setCellValue("男");


        //输出excel的错误形式：
        //out.write(fs.getBytes(), 0, fs.getBytes().length);
        //正确形式：
        wb.write(out);
        //关流
        out.close();
    }
}
