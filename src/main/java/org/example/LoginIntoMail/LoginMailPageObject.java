package org.example.LoginIntoMail;

import io.qameta.allure.Step;
import org.example.Base;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

   // @FindBy(xpath = ".//*[@class = 'base-0-2-25 h3-0-2-29']")
   // private WebElement enterMenu;

   // @FindBy(xpath = "//iframe[@class='iframe-0-2-16']")
   // private WebElement frameAuth;


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

    /** Кнопка для открытия модалки написания письма */
    @FindBy(xpath = "*//span[@class = 'compose-button__wrapper']")
    private WebElement letterCreateButton;

    /** Определяем локатор модалки письма      */
    @FindBy(xpath = ".//div[@class= 'container--rp3CE']")
    private WebElement letterWindow;

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

    /** Локатор кнопки для перехода во "Входящие"    */
    @FindBy(xpath = "//div[contains(text(),'Входящие')]")
    private WebElement incomingMail;

    /** Раскрытие "Письма себе" */
    @FindBy(xpath = "//span[contains(text(),'Письма себе')]")
    private WebElement incomingMailForSelf;

    /** Локатор полученного письма */
    @FindBy(xpath = "//*[text()[contains(.,'Тестовое письмо')]]")
    private WebElement incomingMailSended;

    /** Локатор всплывающего окна */
    @FindBy(xpath = "//div[@class='dimmer']")
    private WebElement afterSendLetterWindow;




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
                setText(letterTo, receiver);
                setText(letterThemes, thematic);
                click(letterBody);
                setText(letterBody, letterInput);
                click(letterSend);
            } else System.out.println("Открыто точно не новое письмо");
        }
    }

    @Step("Переходим в каталог входящей почты и открываем полученное ранее письмо")
    public void openIncomingMail(String thema) {
            waitInvisibilityOfElement(afterSendLetterWindow);
        if (waitVisibilityOfElement(incomingMail)) {
            click(incomingMail);
            String xpath = "//span[contains(*,'" + thema + "')]//span[1]";
            System.out.println("Полный xpath письма во входящей почте = " + xpath);
            WebElement check1 = getDriver().findElement(By.xpath(xpath));
            if (waitVisibilityOfElement(check1)) {
                click(check1);
                System.out.println("Успешно открыто полученное письмо");
            }
        }else
        System.out.println("Не получилось открыть полученное письмо");

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

                        //новая группа локаторов для того, чтобы запипить смену подписи
    @FindBy(xpath = "//span[@class = 'button2__ico']//*[contains(@class, 'settings')]")
    private WebElement settingsButton;

    @FindBy(xpath = "//*[text()[contains(.,'Все настройки')]]")
    private WebElement allSettings;

    @FindBy(xpath = "//div[contains(@class, 'ph-project-promo-close-icon__container')]")
    private WebElement promoButton;

    @FindBy(xpath = "//a[@id ='general']/div[contains(@class,'navigation')]")
    private WebElement generalSettings;

    @FindBy(xpath = ".//button[@data-test-id = 'edit']")
    private WebElement podpisButton;

    @FindBy(xpath = "//h1[contains(text(), 'Редактирование подписи')]/..//div[@role = 'textbox']")
    private WebElement podpisPlace;

    @FindBy(xpath = "//button[@data-test-id = 'save' and @type = 'submit']")
    private WebElement savePodpisButton;

    @Step("Смена подписи в настройках")
    public void settingsChange (){
        click(settingsButton);
        String originalHandle = getDriver().getWindowHandle(); //запоминаем данные базовой вкладки
        System.out.println("орига = " + originalHandle);

        click(allSettings); //открываем на новой вкладке

        for(String childHandle : getDriver().getWindowHandles()){
            if (!childHandle.equals(originalHandle)){
                getDriver().switchTo().window(childHandle);
                System.out.println("новая вкладка = " + childHandle);
            }
        }

        if (waitVisibilityOfElement(promoButton)){
            click(promoButton);
        }

        click(generalSettings);
        click(podpisButton);
        click(podpisPlace);
        podpisPlace.clear(); //очищаем подпись
        setText(podpisPlace,"из Парижа с любовью \n Сергей Кожугетович");
        System.out.println("Успешно установлена новая подпись");
        click(savePodpisButton);
        getDriver().switchTo().window(originalHandle);//возврат на базовую страницу

    }

    @FindBy(xpath = "//div[@class ='sidebar sidebar_closed']//div[contains(text(),'Входящие')]")
    WebElement backToincomingMail;

    //возможно, возможно, что стоит заменить элемент incomingMail на этот
    @Step("Метод осуществляет переход в каталогу Входящей почты")
    public void openIncomingMailPath() {
        click(backToincomingMail);
    }

    @Step("Метод ищет полученные письма и удаляет их")
    public void findAndDeleteLetter(String thema, String themaNew){
       // String xpath1 = "//a[.//span[contains(*,'" + thema + "')]//span[1]]//div[@class='checkbox__box']";
       // System.out.println("Полный xpath письма во входящей почте = " + xpath1);
       // WebElement check1 = getDriver().findElement(By.xpath(xpath1));


        //String xpath2 = "//span[contains(*,'" + themaNew + "')]//span[1]";
        // System.out.println("Полный xpath письма во входящей почте = " + xpath2);
        // WebElement check2 = getDriver().findElement(By.xpath(xpath2));

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("document.getElementsByClassName('checkbox__box')[0].setAttribute('class','checkbox__box checkbox__box_checked')");

        String buttonCheckPath ="//a[.//span[contains(*,'" + thema + "')]//span[1]]//button//div[@class='checkbox']";
        WebElement buttonCheckBox = getDriver().findElement(By.xpath(buttonCheckPath));

        //на будущее
        String buttonCheckPathSecond ="//a[.//span[contains(*,'" + themaNew + "')]//span[1]]//button//div[@class='checkbox']";

        WebElement delete = getDriver().findElement(By.xpath("//div[contains(@class, 'button') and text()='Удалить']"));
        delete.click();




    }



}
