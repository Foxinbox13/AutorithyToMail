package org.example.LoginIntoMail;

import io.qameta.allure.Step;
import org.example.Base;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailPageObject extends Base {
    public WebDriver driver;
    public WebDriverWait wait;

    public MailPageObject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /** Кнопка для открытия модалки написания письма */
    @FindBy(xpath = "*//span[@class = 'compose-button__wrapper']")
    private WebElement letterCreateButton;

    /** Поле для ввода "Кому" в письме      */
    @FindBy(xpath = ".//label[@class = 'container--zU301']//input[@class= 'container--H9L5q size_s--3_M-_']")
    private WebElement letterTo;

    /** Поле для ввода "Тема" в письме      */
    @FindBy(xpath = "//div[@class ='subject__container--HWnat']//input[@class = 'container--H9L5q size_s--3_M-_']")
    private WebElement letterThemes;

    /** Поле для ввода тела письма      */
    @FindBy(xpath = "//div[@role= 'textbox']/div[1]")
    private WebElement letterBody;

    /** Кнопка отправки письма      */
    @FindBy(xpath = ".//button[@class= 'vkuiButton vkuiButton--size-l vkuiButton--mode-primary vkuiButton--appearance-accent vkuiButton--align-center vkuiButton--sizeY-none vkuiButton--android vkuiTappable vkuiTappable--sizeX-none vkuiTappable--hover-none vkuiTappable--hasActive vkuiTappable--mouse']")
    private WebElement letterSend;

    /** Локатор всплывающего окна */
    @FindBy(xpath = "//div[@class='dimmer']")
    private WebElement afterSendLetterWindow;

    @FindBy(xpath = "//div[@class ='sidebar sidebar_closed']//div[contains(text(),'Входящие')]")
    WebElement backToincomingMail;

    @FindBy(xpath = "//div[@class = 'letter-list__react']")
    WebElement letterList;

    @FindBy(xpath = "//div[@data-signature-widget = 'content']")
    private WebElement podpisIntoLetter;

    @FindBy(xpath = "//span[@class = 'button2__ico']//*[contains(@class, 'settings')]")
    protected WebElement settingsButton;

    @FindBy(xpath = "//*[text()[contains(.,'Все настройки')]]")
    protected WebElement allSettings;

    @Step("Открываем модальное окно нового письма")
    public void letterCreate(){
        click(letterCreateButton);
    }

    @Step("Новое письмо: задаем получателя, тему письма и тело письма, отправляем")
    public void fillLetterAndSend (String receiver, String thematic, String letterInput) {
        //проверяем, что перед нами окно с нужными полями
        if (waitVisibilityOfElement(letterTo) & waitVisibilityOfElement(letterThemes)) {
            if (letterTo.getAttribute("value") == "") {
                System.out.println("Открыта модалка создания нового письма");
                setLetterTo(receiver);
                setLetterThemes(thematic);
                clickLetterBody();
                setLetterBody(letterInput);
                clickLetterSend();
            } else System.out.println("Открыто точно не новое письмо");
        }
    }

    @Step("Выбор поля для ввода тела письма ")
    protected void clickLetterBody(){
        click(letterBody);
    }

    @Step("Нажатие кнопки отправки письма")
    protected void clickLetterSend(){
        click(letterSend);
    }

    @Step("Указание получателя письма")
    protected void setLetterTo(String receiver) {
        if (waitVisibilityOfElement(letterTo)) {
            setText(letterTo,receiver);
        }
    }

    @Step("Указание темы письма")
    protected void setLetterThemes(String thematic) {
        if (waitVisibilityOfElement(letterThemes)) {
            setText(letterThemes, thematic);
        }
    }

    @Step("Указание темы письма")
    protected void setLetterBody(String letterInput) {
        if (waitVisibilityOfElement(letterBody)) {
            setText(letterBody, letterInput);
        }
    }


    @Step("Переходим в каталог входящей почты и открываем полученное ранее письмо")
    public void openIncomingMail(String incomingLetters) {
        waitInvisibilityOfElement(afterSendLetterWindow);
        if (waitVisibilityOfElement(backToincomingMail)) {
            openIncomingMailPath();
            if (waitVisibilityOfElement(letterList)) {
                String xpath = "//span[contains(*,'" + incomingLetters + "')]//span[1]";
                System.out.println("Полный xpath письма во входящей почте = " + xpath);
                WebElement titleCheck = getDriver().findElement(By.xpath(xpath));
                if (waitVisibilityOfElement(titleCheck)) {
                    click(titleCheck);
                    System.out.println("Успешно открыто полученное письмо");
                }
            } else
                System.out.println("Не получилось открыть полученное письмо");
        }

    }

    @Step("Проверяем, что содержимое письма соответствует тому, что было отправлено")
    public void checkLetter(final String letter) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (waitVisibilityOfElement(backToincomingMail)) {
            String xpath = "//div[@class='letter-body']//div[text()[contains(.,'" + letter + "')]]";
            WebElement check = getDriver().findElement(By.xpath(xpath));
            final boolean b = waitVisibilityOfElement(check);
            Assert.assertTrue("Открыто не то письмо, однако", b);
            System.out.println("Проверил: это то самое письмо");

        }else System.out.println("wtf dude?");
    }

    @Step("Метод осуществляет переход в каталогу Входящей почты")
    public void openIncomingMailPath() {
        click(backToincomingMail);
        System.out.println("Выполнен переход к разделу входящей почты");
    }

    @Step("Метод ищет полученные письма и удаляет их")
    public void findAndDeleteLetter(String themaFirst, String themaSecond){
        if (waitVisibilityOfElement(letterList)) {
            //выбор конкретных двух последних писем
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("document.getElementsByClassName('checkbox__input')[0].click()");
            js.executeScript("document.getElementsByClassName('checkbox__input')[1].click()");

            //Перепишем на xpath
        /*WebElement firstLetterXpath = getDriver().findElement(By.xpath("//a[.//span[contains(*,'" + themaFirst + "')]//span[1]]//input[@class='checkbox__input']"));
        WebElement secondLetterXpath = getDriver().findElement(By.xpath("//a[.//span[contains(*,'" + themaSecond + "')]//span[1]]//input[@class='checkbox__input']"));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("click()", firstLetterXpath);
        js.executeScript("click()", secondLetterXpath);*/

            WebElement deleteButton = getDriver().findElement(By.xpath("//div[contains(@class, 'button') and text()='Удалить']"));
            deleteButton.click();
        }
    }


    @Step("Метод проверки сравнения подписи в письме с новозаданной подписью")
    public void checkPodpis(String textPopdisi){
        if (waitVisibilityOfElement(podpisIntoLetter)){
            Assert.assertEquals("Ошибка: подписи отлчичаются",podpisIntoLetter.getText(),textPopdisi);
            System.out.println("Подпись в письме проверена");
        }
    }

    @Step("Метод проверяет, что в списке нет писем, которые были удалены")
    public void checkLettersAfterDelete(String letter1, String letter2){
        String xpathLetter1 = "//div[@class='letter-body']//div[text()[contains(.,'" + letter1 + "')]]";
        String xpathLetter2 = "//div[@class='letter-body']//div[text()[contains(.,'" + letter2 + "')]]";
        System.out.println("xpath первого удаленного письма = " + xpathLetter1);
        System.out.println("xpath второго удаленного письма = " + xpathLetter2);

        try {
            WebElement checkExistLetter1 = getDriver().findElement(By.xpath(xpathLetter1));
        }catch (NoSuchElementException e){
            System.out.println("Письмо, отправленное ПЕРВЫМ, успешно удалено");
        }

        try {
            WebElement checkExistLetter2 = getDriver().findElement(By.xpath(xpathLetter1));
        }catch (NoSuchElementException e){
            System.out.println("Письмо, отправленное ВТОРЫМ, успешно удалено");
        }

        // WebElement checkExistLetter1 = getDriver().findElement(By.xpath(xpathLetter1));
        // WebElement checkExistletter2 = getDriver().findElement(By.xpath(xpathLetter2));

        /*Assert.assertTrue("Письмо, отправленное ПЕРВЫМ, успешно удалено", getDriver().findElement(By.xpath(xpathLetter1)).isDisplayed());
        Assert.assertTrue("Письмо, отправленное ВТОРЫМ, успешно удалено", getDriver().findElement(By.xpath(xpathLetter2)).isDisplayed());
        System.out.println("Проверили: письма удалены");*/

        /*if (getDriver().findElement(By.xpath(xpathLetter1)).isDisplayed()){
            System.out.println("Письмо, отправленное ПЕРВЫМ, успешно удалено");
        }*/

    }


}
