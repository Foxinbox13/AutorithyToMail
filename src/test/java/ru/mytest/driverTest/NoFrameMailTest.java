package ru.mytest.driverTest;

import io.qameta.allure.Epic;
import org.example.LoginIntoMail.LoginMailPageObject;
import org.example.LoginIntoMail.MailPageObject;
import org.example.LoginIntoMail.SettingsMailPageObject;
import org.junit.Test;
import ru.mytest.BaseDriverClass;

public class NoFrameMailTest extends BaseDriverClass {

    @Epic("Тест по выполнению кейса Авторизаци -> Письмо -> Смена подписи -> Письмо -> Удаление писем")
    @Test
    public void baseTest(){

        final String login = "dollar_region";
        final String password = "flow_master";
        final String thema =  "Первое письмо" + Math.random();
        final String themaNew = "Второе письмо" + Math.random();
        final String receiver = login + "@mail.ru";
        final String letterInput = "Мы с радостью приглашаем Вас на данное мероприятие. Хвала Одину!";
        final String letterPodpis = "из Парижа с любовью \n Сергей Кожугетович";

        driver.get("https://account.mail.ru/login/");
        driver.manage().window().maximize();

        LoginMailPageObject authIntoMail = new LoginMailPageObject(driver);
        MailPageObject workWithMail = new MailPageObject(driver);
        SettingsMailPageObject settingMail = new SettingsMailPageObject(driver);

        authIntoMail.inputLogin(login);           //вводим логин
        authIntoMail.goToEnterPass();             //переходим к вводу пароля
        authIntoMail.inputPass(password);         //вводим пароль
        authIntoMail.loginAfterAll();             //авторизация после ввода логина и пароля

        workWithMail.letterCreate();              //нажимаем кнопку создания письма
        workWithMail.fillLetterAndSend(receiver, thema, letterInput); //создание нового письма
        System.out.println("Давайте посмотрим на титул первого письма  " + thema);
        workWithMail.openIncomingMail(thema);     //открываем полученное письмо в каталоге входящей почты
        workWithMail.checkLetter(letterInput);    //проверяем, что содержимое равно самому себе

        settingMail.settingsChange(letterPodpis); //редактирование и установка новой подписи

        workWithMail.letterCreate();              //вернулись и решили создать новое письмо
        workWithMail.fillLetterAndSend(receiver, themaNew, letterInput); //создание нового письма
        System.out.println("Давайте посмотрим на титул второго письма  " + themaNew);
        workWithMail.openIncomingMail(themaNew); //открываем полученное письмо в каталоге входящей почты
        workWithMail.checkLetter(letterInput);   //проверяем, что содержимое равно самому себе
        workWithMail.checkPodpis(letterPodpis);  //проверка подписи: подумать на тему вывода в консоль результата сверки
        workWithMail.openIncomingMailPath();     //открываем каталог входящей почты
        workWithMail.findAndDeleteLetter(thema, themaNew); //находим и удаляем полученные по ходу теста письма
        workWithMail.checkLettersAfterDelete(thema, themaNew); //проверяем, что письма удалены и их нет в списке писем

        System.out.println("Тест завершён.");    //выводим в консоль завершение теста
    }
}
