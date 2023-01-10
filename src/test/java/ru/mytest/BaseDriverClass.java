package ru.mytest;

import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseDriverClass {

    protected WebDriver driver;
    //порт для запуска на локальной машине
    protected String ggUrl = "http://localhost:4445";
    //protected String ggUrl = "https://mail.ru/";

    @Rule
    public TestWatcher watcher;

    {
        watcher = new TestWatcher() {
            @Override
            protected void starting(Description description) {
                ChromeOptions chrome = new ChromeOptions();
                URL hub = null;

                try {
                    hub = new URL(ggUrl + "/wd/hub");
                    //hub = new URL(ggUrl);
                    //If something does not work, you can easily check that Selenoid is running with opening status url:
                    //hub = new URL(ggUrl + "/status");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                //driver = new ChromeDriver(chrome);
                driver = new RemoteWebDriver(hub, chrome);

            }

            @Override
            protected void succeeded(Description description) {

            }

            @Override
            protected void failed(Throwable e, Description description) {

            }

            @Override
            protected void finished(Description description) {
                //driver.quit();
                if (driver != null) {
                    driver.quit();
                }

            }
        };


    }

}
