package org.example.LoginIntoMail;

import org.example.Base;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;

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

    /** Раскрытие "Письма себе" */
    @FindBy(xpath = "//span[contains(text(),'Письма себе')]")
    private WebElement incomingMailForSelf;

    /** Локатор полученного письма */
    @FindBy(xpath = "//*[text()[contains(.,'Тестовое письмо')]]")
    private WebElement incomingMailSended;




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
            setText(userPassLocator,password);
            //userPassLocator.sendKeys(password);
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
    /** Пишем новое письмо: задаем получателя, тему письма и тело письма, отправляем */
    public void fillLetterAndSend (String receiver, String thema, String letterInput) {
       //проверяем, что перед нами окно с нужными полями
        if (waitVisibilityOfElement(letterTo) & waitVisibilityOfElement(letterThemes)) {
            if (letterTo.getAttribute("value") == "") {
                System.out.println("Открыта модалка нового письма");
                setText(letterTo, receiver);
                setText(letterThemes, thema);
                click(letterBody);
                setText(letterBody, letterInput);
                click(letterSend);
            } else System.out.println("Открыто точно не новое письмо");
        }
    }

    /** переходим в каталог входящей почты и открываем полученное письмо */
    public void openIncomingMail(String thema){
        waitVisibilityOfElement(incomingMailSended);
        if (waitVisibilityOfElement(incomingMailSended)) {
            click(incomingMailSended);
            //String xpath = "//span[text()[contains(.,'" + thema + "')]]";
            String xpath = "//span[contains(*,'" + thema + "')]//span[1]";
            System.out.println("полный xpath нужного письма " + xpath);
            //WebElement check1 = getDriver().findElement(By.xpath("//span[text()[contains(.,'" + thema + "')]]"));
            WebElement check1 = getDriver().findElement(By.xpath(xpath));
            waitVisibilityOfElement(check1);
            if (waitVisibilityOfElement(check1)) {
                click(check1);
                System.out.println("успешно выбрано полученное письмо");
            }
            //click(incomingMailSended.findElement(By.xpath("//*[text()[contains(.,'" + thema + "')]]")));


        }else
        System.out.println("Не получилось открыть полученное письмо");

    }

    /**
     * Проверяем, что в письме то, что нужно. Содержимое письма: Тестовое письмо + random.
     */
    public void checkLetter(final String letter) {

        String xpath = "//div[@class='letter-body']//div[text()[contains(.,'" + letter + "')]]";
        ////div[@class='letter-body']//div[text()[contains(.,'Мы с радо')]]
        WebElement check = getDriver().findElement(By.xpath(xpath));
        final String message = "Содержимое письма: " + letter;
        final boolean b = waitVisibilityOfElement(check);
        if (waitVisibilityOfElement(check)) {
            Assert.assertTrue(message, b);
        }
    }

    //новая группа локаторов для того, чтобы запипить смену подписи
    @FindBy(xpath = "//span[@class = 'button2__ico']//*[contains(@class, 'settings')]")
    WebElement settingsButton;

    @FindBy(xpath = "//*[text()[contains(.,'Все настройки')]]")
    WebElement allSettings;

    //@FindBy(xpath = "//div[@data-test-id ='navigation-menu-item:general' and @data-test-active='false']//a[@href = '/settings/general']/div[1]")
    //@FindBy(xpath = "//div[contains(@class,  'Layout-mobile__tabs')]//a[@href = '/settings/general']//span[contains(text(), 'Общие')]")
    @FindBy(xpath = "//a[@id ='general']/div[contains(@class,'navigation')]")
    WebElement generalSettings;

    @FindBy(xpath = ".//button[@data-test-id = 'edit']")
    WebElement podpisButton;

    @FindBy(xpath = "//h1[contains(text(), 'Редактирование подписи')]/..//div[@role = 'textbox']")
    WebElement podpisPlace;

    @FindBy(xpath = "//button[@data-test-id = 'save' and @type = 'submit']")
    WebElement savePodpisButton;

    public void settingsChange (){
        click(settingsButton);


        //тут взято из интернета
        String originalHandle = getDriver().getWindowHandle();

        //открываем на новую вкладку
        click(allSettings);

        //for (String windowHandle : getDriver().getWindowHandles()){
        //    getDriver().switchTo().window(windowHandle);
        //}

        for(String childHandle : getDriver().getWindowHandles()){
            if (!childHandle.equals(originalHandle)){
                getDriver().switchTo().window(childHandle);
                System.out.println("новая вклдка = " + childHandle);
            }
        }

        System.out.println("орига = " + originalHandle);
        waitVisibilityOfElement(generalSettings);
        click(generalSettings);


        click(podpisButton);
        click(podpisPlace);
        setText(podpisPlace,"из Парижа с любовью \n Сергей Кожугетович");
        System.out.println("установлена новая подпись");
        click(savePodpisButton);

    }





}
