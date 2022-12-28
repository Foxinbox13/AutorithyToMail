package org.example.LoginIntoMail;

import org.example.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginMailPageObject extends Base {
    public WebDriver driver;

    //WebDriverWait wait = new WebDriverWait(driver, 30);
    public LoginMailPageObject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        //this.driver = driver;


    }


    @FindBy(xpath = ".//*[@class = 'ph-login svelte-1hiqrvn']")
    private WebElement enterButtonLogin;

    @FindBy(xpath = ".//*[@class = 'base-0-2-25 h3-0-2-29']")
    private WebElement enterMenu;


    @FindBy(xpath = "//iframe[@class='iframe-0-2-16']")
    private WebElement frameAuth;


    /**
     * поиск локатора для поля ввода логина
     **/
    @FindBy(xpath = ".//*[@class = 'input-0-2-77']")
    private WebElement userLoginLocator;


    @FindBy(xpath = ".//button[@class= 'base-0-2-87 primary-0-2-101 auto-0-2-113']")
    private WebElement nextButton;

    /**
     * поиск локатора для поля ввода пароля
     **/
    @FindBy(xpath = ".//input[@name= 'password']")
    private WebElement userPassLocator;

    /**
     * кнопка авторизации уже псле ввода логина и пароля
     */
    @FindBy(xpath = "*//button[@class = 'base-0-2-87 primary-0-2-101 auto-0-2-113']")
    private WebElement authButtonLogin;

    /**
     * кнопка для написания письма
     */
    @FindBy(xpath = "*//span[@class = 'compose-button__wrapper']")
    private WebElement letterCreateButton;

    /*** определяем локатор модалки письма      */
    @FindBy(xpath = ".//div[@class= 'container--rp3CE']")
    private WebElement letterWindow;

    /*** поле для ввода "Кому" в письме      */
    @FindBy(xpath = ".//label[@class = 'container--zU301']//input[@class= 'container--H9L5q size_s--3_M-_']")
    private WebElement letterTo;

    /*** поле для ввода "Тема" в письме      */
    @FindBy(xpath = "//div[@class ='subject__container--HWnat']//input[@class = 'container--H9L5q size_s--3_M-_']")
    private WebElement letterThemes;

    /*** поле для ввода тела письма      */
    @FindBy(xpath = "//div[@role= 'textbox']/div[1]")
    private WebElement letterBody;

    /*** кнопка отправки письма      */
    @FindBy(xpath = ".//button[@class= 'vkuiButton vkuiButton--size-l vkuiButton--mode-primary vkuiButton--appearance-accent vkuiButton--align-center vkuiButton--sizeY-none vkuiButton--android vkuiTappable vkuiTappable--sizeX-none vkuiTappable--hover-none vkuiTappable--hasActive vkuiTappable--mouse']")
    private WebElement letterSend;

    /**
     * Кнопка "Входящие"
     */
    @FindBy(xpath = ".//a[@title= 'Входящие']")
    private WebElement incomingMail;




    /**
     * метод нажатия кнопки для открытия модального окна для последующего ввода логина и пароля
     */
    public void pressEnter() {
        if (waitVisibilityOfElement(enterButtonLogin)) {
            click(enterButtonLogin);
        }
    }

    /**  метод ввода логина в окне авторизации */
    public void inputLogin(String login) {
        setText(userLoginLocator, login);
    }

    /**  метод ввода пароля в окне авторизации */
    public void inputPass(String password) {
        if (waitVisibilityOfElement(userPassLocator)) {
            click(userPassLocator);
            userPassLocator.sendKeys(password);
        }
    }

    /** переход ко вводу пароля после указания логина */
    public void pressGoToPass() {
        if (waitVisibilityOfElement(nextButton)) {
            click(nextButton);
        }
    }

    /** вход после ввода логина и пароля */
    public void loginAfterAll() {
        if (waitVisibilityOfElement(authButtonLogin)) {
            click(authButtonLogin);
        }
    }

    /** начинаем писать письмо */
    public void letterCreate(){
            click(letterCreateButton);
    }
    /** начинаем писать письмо */
    public void fillLetterAndSend (String receiver, String thema) {
        if (waitVisibilityOfElement(letterTo) & waitVisibilityOfElement(letterThemes)) {

            //driver.findElement(letterTo).getAttribute("value").compareTo("");
            if (letterTo.getAttribute("value") == "") {
                System.out.println("Открыта модалка нового письма");
                setText(letterTo, receiver);
                setText(letterThemes, thema);
                click(letterBody);
                setText(letterBody, "Мы с радостью приглашаем Вас на данное мероприятие. Хвала Одину!");
                click(letterSend);
            } else System.out.println("Открыто точно не новое письмо");

        }
    }

    /** начинаем писать письмо */
    public void openIncomingMail(){
        if (waitVisibilityOfElement(incomingMail)) {
            click(incomingMail);
        }
    }


}
