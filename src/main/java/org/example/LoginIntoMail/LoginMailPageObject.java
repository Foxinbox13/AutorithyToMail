package org.example.LoginIntoMail;

import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.TimeUnit;

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

    @FindBy(xpath = ".//*[@placeholder = 'Имя аккаунта']")
    private WebElement userLoginLocator;

    @FindBy(xpath = ".//button[@class= 'base-0-2-79 primary-0-2-93']")
    private WebElement nextButton;

    @FindBy(xpath = ".//input[@name= 'password']")
    private WebElement userPassLocator;
    @FindBy(xpath = ".//button[@class= 'base-0-2-79 primary-0-2-93']")
    private WebElement authButtonLogin;


    /**
     * метод нажатия кнопки для открытия модального окна для последующего ввода логина и пароля
     */
    public void pressEnter() {
        if (waitVisibilityOfElement(enterButtonLogin)) {
            click(enterButtonLogin);
        }
    }

    /**
     * метод ввода логина и пароля в окне авторизации
     */
    public void inputLogin(String login) {
        setText(userLoginLocator, login);

    }

    public void inputPass(String password) {
        if (waitVisibilityOfElement(userPassLocator)) {
            click(userPassLocator);
            userPassLocator.sendKeys(password);
        }
    }

        /**
         * переход ко вводу пароля
         */


        public void pressGoToPass(){
            if (waitVisibilityOfElement(nextButton)) {
                click(nextButton);
            }

        }

        public void loginAfterAll() {
            if (waitVisibilityOfElement(authButtonLogin)) {
                click(authButtonLogin);
            }
            authButtonLogin.click();
        }

    }
