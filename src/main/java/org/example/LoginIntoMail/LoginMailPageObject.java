package org.example.LoginIntoMail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.TimeUnit;

public class LoginMailPageObject{
    public WebDriver driver;
    //WebDriverWait wait = new WebDriverWait(driver, 30);
    public LoginMailPageObject(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
        //this.driver=driver;

    }
    @FindBy(xpath = ".//*[@class = 'ph-login svelte-1hiqrvn']")
    private WebElement  enterButtonLogin;
    @FindBy(xpath = ".//input[@name = 'username']")
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
  //      Wait waiter = new FluentWait(driver);
  //      WebElement element = (WebElement) waiter
  //              .until(ExpectedConditions.invisibilityOfElementLocated(By.name("clb831530")));
        enterButtonLogin.click();
    }

    /**
     * метод ввода логина и пароля в окне авторизации
     */
    public void inputLogin(String login) {
        userLoginLocator.sendKeys(login);
    }

    public void inputPass(String password) {
        userPassLocator.sendKeys(password);
    }

    /**
     * переход ко вводу пароля
     */
    public void pressGoToPass() {
        nextButton.click();
    }

    public void loginAfterAll() {
        authButtonLogin.click();
    }

}
