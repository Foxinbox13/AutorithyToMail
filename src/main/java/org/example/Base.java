package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Base {
    private static final Logger LOG = LoggerFactory.getLogger(Base.class);
    private WebDriver driver;
    private final int driverWaitTime = getDriverWaitTime();

    public Base(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;

    }

    public int getDriverWaitTime() {
        int waitTime = 60;
        String drWt = System.getProperty("DriverWaitTime");
        if (drWt != null) {
            waitTime = Integer.parseInt(drWt);
        }

        return waitTime;
    }

    /**
     * Ждем пока элемент отобразится на странице.
     * Метод возвращает Boolean.
     *
     * @param element
     * @return
     */
    public Boolean waitVisibilityOfElement(final WebElement element) {
        //   // switchToFrame();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(driverWaitTime));
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            //          switchToDefaultFrame();
            return true;
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        //      switchToDefaultFrame();
        return false;
    }

    /**
     * Метод ждет пока элемент появится на странице.
     * Затем кликает по этому элементу.
     *
     * @param webElement
     */
    public void click(final WebElement webElement) {
        //c.sleep(2);
        waitVisibilityOfElement(webElement);
     /*   Random r = new Random();
        index = r.nextInt(sec);
        //      // switchToFrame();
        c.sleep(index);*/
        webElement.click();
        //    switchToDefaultFrame();
    }

    /**
     * Вводит текст в поле webElement.
     *
     * @param webElement
     * @param string
     */
    protected void setText(final WebElement webElement, final String string) {
        waitVisibilityOfElement(webElement);
        //    // switchToFrame();
        webElement.sendKeys(string);
        //   switchToDefaultFrame();
    }

}
