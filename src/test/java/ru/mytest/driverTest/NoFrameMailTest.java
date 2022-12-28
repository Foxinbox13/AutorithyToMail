package ru.mytest.driverTest;

import org.example.LoginIntoMail.LoginMailPageObject;
import org.junit.Test;
import ru.mytest.BaseDriverClass;

public class NoFrameMailTest extends BaseDriverClass {


    @Test
    public void baseTest(){
        driver.get("https://account.mail.ru/login/");
        LoginMailPageObject neweMail = new LoginMailPageObject(driver);

        neweMail.inputLogin("dollar_region"); //вводим логин
        neweMail.pressGoToPass();             // переходим к вводу пароля
        neweMail.inputPass("flow_master"); //вводим пароль
        neweMail.loginAfterAll();             //авторизация по итогу
        neweMail.letterCreate();
        neweMail.fillLetterAndSend("dollar_region@mail.ru", "Тестовое письмо" + Math.random());
        System.out.println("Тест завершён.");
    }
}
