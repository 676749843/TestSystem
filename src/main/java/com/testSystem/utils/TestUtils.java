package com.testSystem.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestUtils {
    public static void main(String[] args)
    {
        System.setProperty("webdriver.chrome.driver", "D:\\webdriver\\chromedriver.exe");// chromedriver服务地址
        WebDriver driver = new ChromeDriver(); // 新建一个WebDriver 的对象，但是new 的是谷歌的驱动
        String url = "http://www.baidu.com";
        driver.get(url); // 打开指定的网站
        driver.quit();
    }
}
