package ru.mytest.driverTest;

import org.example.LoginIntoMail.LoginMailPageObject;
import org.junit.Test;
import ru.mytest.BaseDriverClass;

public class ExampleTest extends  BaseDriverClass{

    @Test
    public void baseTest() {
       // driver.get("https://mail.ru/");
        driver.get("https://account.mail.ru/login/");
        LoginMailPageObject mailRu = new LoginMailPageObject(driver);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("driver is " + driver);
        //mailRu.pressEnter();

        //mailRu.frameSearch();

        mailRu.inputLogin("dollar_region");
        mailRu.pressGoToPass();
        mailRu.inputPass("flow_master");
        mailRu.loginAfterAll();

    }

}
