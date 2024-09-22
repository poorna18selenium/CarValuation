package com.registration.base;

import com.registration.util.ReadFile;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WebDriverBase {
    public  static WebDriver driver;
    public  static Properties prop;

    public WebDriverBase()  {
        prop=new Properties();
        try {
            ClassLoader classLoader = WebDriverBase.class.getClassLoader();
            FileInputStream fis = new FileInputStream(new File(classLoader.getResource("config.properties").getFile()).getAbsolutePath());
            prop.load(fis);
        }catch (Exception e){
            e.getMessage();
        }

    }
    public static void initialBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));
    }



public static void close(){
        driver.quit();
}

}
