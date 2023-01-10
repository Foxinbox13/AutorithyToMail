package ru.mytest.driverTest;

import com.beust.jcommander.Parameter;
import org.example.LoginIntoMail.LoginMailPageObject;
import org.junit.Test;
import ru.mytest.BaseDriverClass;

public class NoFrameMailTest extends BaseDriverClass {

    @Test
    public void baseTest(){

        driver.manage().window().maximize();

        String login = "dollar_region";
        String password = "flow_master";
        String thema =  "Тестовое письмо" + Math.random();
        String themaNew = "Что-то новое" + Math.random();
        String receiver = "dollar_region@mail.ru";
        String letterInput = "Мы с радостью приглашаем Вас на данное мероприятие. Хвала Одину!";

        driver.get("https://account.mail.ru/login/");
        LoginMailPageObject newMail = new LoginMailPageObject(driver);

        newMail.inputLogin(login);           //вводим логин
        newMail.pressGoToPass();             //переходим к вводу пароля
        newMail.inputPass(password);         //вводим пароль
        newMail.loginAfterAll();             //авторизация после ввода логина и пароля
        newMail.letterCreate();              //нажимаем кнопку создания письма

        newMail.fillLetterAndSend(receiver, thema, letterInput); //создание нового письма
        System.out.println("давайте посмотрим, что мы выводим:  " + thema);

        //тут почему-то работает только в режиме отладки
        newMail.openIncomingMail(thema); //открываем полученное письмо в каталоге входящей почты
        //newMail.checkLetter(letterInput);           //такое себе, но проверяем, что содержимое равно самому себе

    //// дальше к настройкам

        newMail.settingsChange();
        //вернулись и решили создать новое письмо
        newMail.letterCreate();
        newMail.fillLetterAndSend(receiver, themaNew, letterInput); //создание нового письма
        newMail.openIncomingMail(themaNew);
        //newMail.checkLetter(letterInput);

        newMail.openIncomingMailPath();
        newMail.findAndDeleteLetter(thema, themaNew);

        System.out.println("Тест завершён.");       //выводим в консоль завершение теста
    }
}
