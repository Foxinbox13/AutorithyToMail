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

    /** Локатор всплывающего окна */
    @FindBy(xpath = "//div[@class='dimmer']")
    private WebElement afterSendLetterWindow;

    @FindBy(xpath = "//div[@class ='sidebar sidebar_closed']//div[contains(text(),'Входящие')]")
    WebElement backToincomingMail;

    @FindBy(xpath = "//div[@class = 'letter-list__react']")
    WebElement letterList;

    @FindBy(xpath = "//div[@data-signature-widget = 'content']")
    private WebElement podpisIntoLetter;

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
    public void openIncomingMail(String incomingLetters) {
            waitInvisibilityOfElement(afterSendLetterWindow);
        if (waitVisibilityOfElement(backToincomingMail)) {
            // click(incomingMail);
            click(backToincomingMail);
            if (waitVisibilityOfElement(letterList)) {
                //waitVisibilityOfElement(backToincomingMail);
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


    @Step("Метод проверки сравнения подписи в письме с новозаданной подписью")
    public void checkPodpis(String textPopdisi){
        if (waitVisibilityOfElement(podpisIntoLetter)){
            Assert.assertEquals("Ошибка: подписи отлчичаются",podpisIntoLetter.getText(),textPopdisi);
            System.out.println("Подпись в письме проверена");
        }
    }


    @Step("Смена подписи в настройках")
    public void settingsChange (String newPodpis){
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
        setText(podpisPlace,newPodpis);
        System.out.println("Успешно установлена новая подпись");
        click(savePodpisButton);
        getDriver().switchTo().window(originalHandle);//возврат на базовую страницу

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

    @Step("Метод проверяет, что в списке нет писем, которые были удалены")
    public void checkLettersAfterDelete(String letter1, String letter2){

        String xpathLetter1 = "//div[@class='letter-body']//div[text()[contains(.,'" + letter1 + "')]]";
        String xpathLetter2 = "//div[@class='letter-body']//div[text()[contains(.,'" + letter2 + "')]]";
        System.out.println("xpathLetter1 = " + xpathLetter1);
        System.out.println("xpathLetter2 = " + xpathLetter2);

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
