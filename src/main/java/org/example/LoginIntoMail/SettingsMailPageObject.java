package org.example.LoginIntoMail;

import io.qameta.allure.Step;
import org.example.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SettingsMailPageObject extends MailPageObject {
    public WebDriver driver;
    public WebDriverWait wait;

    public SettingsMailPageObject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }


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
    public void settingsChange (String newPodpis){
        clickSettingsButton();
        String originalHandle = getDriver().getWindowHandle(); //запоминаем данные базовой вкладки
        System.out.println("орига = " + originalHandle);

        clickAllSettings(); //открываем на новой вкладке

        for(String childHandle : getDriver().getWindowHandles()){
            if (!childHandle.equals(originalHandle)){
                getDriver().switchTo().window(childHandle);
                System.out.println("новая вкладка = " + childHandle);
            }
        }

        if (waitVisibilityOfElement(promoButton)){
            click(promoButton);
        }

        clickGeneralSettings();
        clickPodpisButton();
        clickPodpisPlace();
        podpisPlace.clear(); //очищаем подпись
        setText(podpisPlace,newPodpis);
        System.out.println("Успешно установлена новая подпись");
        savePodpis();
        getDriver().switchTo().window(originalHandle);//возврат на базовую страницу
    }

    @Step("Нажатие кнопки для открытия меню настрроек")
    protected void clickSettingsButton(){
        click(settingsButton);
    }

    @Step("Открытие полного списка настроек в новом окне")
    protected void clickAllSettings(){
        click(allSettings);
    }


    @Step("Раскрываем Настройки")
    protected void clickGeneralSettings(){
        click(generalSettings);
    }

    @Step("Открытие раздела для редактирования подписи ")
    protected void clickPodpisButton(){
        click(podpisButton);
    }

    @Step("Выбрать место расположения подписи")
    protected void clickPodpisPlace(){
        click(podpisPlace);
    }

    protected void savePodpis(){
        click(savePodpisButton);
    }

}
