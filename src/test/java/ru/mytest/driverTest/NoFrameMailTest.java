package ru.mytest.driverTest;

import org.example.LoginIntoMail.LoginMailPageObject;
import org.junit.Test;
import ru.mytest.BaseDriverClass;

public class NoFrameMailTest extends BaseDriverClass {

    @Test
    public void baseTest(){

        final String login = "dollar_region";
        final String password = "flow_master";
        final String thema =  "Первое письмо" + Math.random();
        final String themaNew = "Второе письмо" + Math.random();
        final String receiver = "dollar_region@mail.ru";
        final String letterInput = "Мы с радостью приглашаем Вас на данное мероприятие. Хвала Одину!";
        final String letterPodpis = "из Парижа с любовью \n Сергей Кожугетович";

        driver.get("https://account.mail.ru/login/");
        driver.manage().window().maximize();

        LoginMailPageObject newMail = new LoginMailPageObject(driver);

        newMail.inputLogin(login);           //вводим логин
        newMail.goToEnterPass();             //переходим к вводу пароля
        newMail.inputPass(password);         //вводим пароль
        newMail.loginAfterAll();             //авторизация после ввода логина и пароля

        newMail.letterCreate();              //нажимаем кнопку создания письма
        newMail.fillLetterAndSend(receiver, thema, letterInput); //создание нового письма
        System.out.println("Давайте посмотрим на титул первого письма  " + thema);

        newMail.openIncomingMail(thema); //открываем полученное письмо в каталоге входящей почты
        //тут почему-то работает только в режиме отладки
        newMail.checkLetter(letterInput);           //такое себе, но проверяем, что содержимое равно самому себе

        newMail.settingsChange(letterPodpis);             //редактирование подписи
        newMail.letterCreate();               //вернулись и решили создать новое письмо
        newMail.fillLetterAndSend(receiver, themaNew, letterInput); //создание нового письма
        System.out.println("Давайте посмотрим на титул второго письма  " + themaNew);
        newMail.openIncomingMail(themaNew);
        newMail.checkLetter(letterInput);


        newMail.checkPodpis(letterPodpis); //проверка подписи: подумать на тему вывода в консоль результата сверки

        newMail.openIncomingMailPath();
        newMail.findAndDeleteLetter(thema, themaNew);
        newMail.checkLettersAfterDelete(thema, themaNew);

        System.out.println("Тест завершён.");       //выводим в консоль завершение теста
    }
}
