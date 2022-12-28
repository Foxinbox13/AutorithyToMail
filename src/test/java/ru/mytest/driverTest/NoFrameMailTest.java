package ru.mytest.driverTest;

import org.example.LoginIntoMail.LoginMailPageObject;
import org.junit.Test;
import ru.mytest.BaseDriverClass;

import java.util.Random;

public class NoFrameMailTest extends BaseDriverClass {


    @Test
    public void baseTest(){
        String thema =  "Тестовое письмо" + Math.random();
        driver.get("https://account.mail.ru/login/");
        LoginMailPageObject neweMail = new LoginMailPageObject(driver);

        neweMail.inputLogin("dollar_region"); //вводим логин
        neweMail.pressGoToPass();             // переходим к вводу пароля
        neweMail.inputPass("flow_master"); //вводим пароль
        neweMail.loginAfterAll();             //авторизация по итогу
        neweMail.letterCreate();
        //int i = (int) (Math.random()*3); //целочисленное значение от 1 до 3
        neweMail.fillLetterAndSend("dollar_region@mail.ru", thema);
        neweMail.openIncomingMail(thema);

        System.out.println("Тест завершён.");
    }
}
