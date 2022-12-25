package ru.mytest.driverTest;

import org.example.LoginIntoMail.LoginMailPageObject;
import org.junit.Test;
import ru.mytest.BaseDriverClass;

import java.util.concurrent.TimeUnit;

public class ExampleTest extends BaseDriverClass {

    @Test
    public void BaseTest() {
        LoginMailPageObject kkk = new LoginMailPageObject(driver);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        kkk.pressEnter();
        kkk.inputLogin("dollar_region");
        kkk.pressGoToPass();
        kkk.inputPass("flow_master");
        kkk.loginAfterAll();

    }

}
