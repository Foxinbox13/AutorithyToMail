package org.example.LoginIntoMail;

import io.qameta.allure.Step;
import org.example.Base;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginMailPageObject extends Base {
    public WebDriver driver;
    public WebDriverWait wait;

    public LoginMailPageObject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ".//*[@class = 'ph-login svelte-1hiqrvn']")
    private WebElement enterButtonLogin;

    /** Поиск локатора для поля ввода логина **/
    @FindBy(xpath = ".//*[@class = 'input-0-2-77']")
    private WebElement userLoginLocator;

    @FindBy(xpath = ".//button[@class= 'base-0-2-87 primary-0-2-101 auto-0-2-113']")
    private WebElement nextButton;

    /** Поиск локатора для поля ввода пароля **/
    @FindBy(xpath = ".//input[@name= 'password']")
    private WebElement userPassLocator;

    /** Кнопка авторизации уже псле ввода логина и пароля */
    @FindBy(xpath = "*//button[@class = 'base-0-2-87 primary-0-2-101 auto-0-2-113']")
    private WebElement authButtonLogin;

    @Step("Метод нажатия кнопки для открытия модального окна для последующего ввода логина и пароля")
    public void pressEnter() {
        if (waitVisibilityOfElement(enterButtonLogin)) {
            click(enterButtonLogin);
        }
    }

    @Step("Метод ввода логина в окне авторизации")
    public void inputLogin(String login) {
        setText(userLoginLocator, login);
    }

    @Step("Метод ввода пароля в окне авторизации")
    public void inputPass(String password) {
        if (waitVisibilityOfElement(userPassLocator)) {
            click(userPassLocator);
            setText(userPassLocator,password);
        }
    }

    @Step("Переход ко вводу пароля после указания логина")
    public void goToEnterPass() {
        if (waitVisibilityOfElement(nextButton)) {
            click(nextButton);
        }
    }

    @Step("Подтверждение авторизации после ввода логина и пароля")
    public void loginAfterAll() {
        if (waitVisibilityOfElement(authButtonLogin)) {
            click(authButtonLogin);
        }
    }


}
