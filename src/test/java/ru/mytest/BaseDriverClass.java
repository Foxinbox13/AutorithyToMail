package ru.mytest;

import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseDriverClass {

    protected WebDriver driver;
    WebDriverWait wait;
    //порт для запуска на локальной машине
    //protected String ggUrl = "http://localhost:4445";
    protected String ggUrl = "https://mail.ru/";

    @Rule
    public TestWatcher watcher;

    {
        watcher = new TestWatcher() {
            @Override
            protected void starting(Description description) {
                ChromeOptions chrome = new ChromeOptions();
                URL hub = null;

                try {
                    //hub = new URL(ggUrl + "/wd/hub");
                    hub = new URL(ggUrl);
                    //If something does not work, you can easily check that Selenoid is running with opening status url:
                    //hub = new URL(ggUrl + "/status");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                driver = new RemoteWebDriver(hub, chrome);
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

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
