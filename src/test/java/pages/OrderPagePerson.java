package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPagePerson {

    private final WebDriver driver;

    public OrderPagePerson(WebDriver driver){
        this.driver = driver;
    }

    //поле ввода Имя
    private static final By inputName = By.xpath(".//input[@placeholder='* Имя']");
    //поле ввода Фамилия
    private static final By inputSurname = By.xpath(".//input[@placeholder='* Фамилия']");
    //поле ввода Адрес
    private static final By inputAdress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //поле ввода Станция метро
    private static final By inputMetro = By.xpath(".//input[@placeholder='* Станция метро']");
    //поле ввода Телефон
    private static final By inputPhone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //кнопка Далее
    private static final By buttonForward = By.xpath(".//button[text()='Далее']");

    public void fillOrderForm(String name, String surname, String adress, String metro, String phone) {
        driver.findElement(inputName).sendKeys(name);
        driver.findElement(inputSurname).sendKeys(surname);
        driver.findElement(inputAdress).sendKeys(adress);
        WebElement chooseMetro = driver.findElement(inputMetro);
        chooseMetro.sendKeys(metro);
        chooseMetro.sendKeys(Keys.DOWN);
        chooseMetro.sendKeys(Keys.RETURN);
        driver.findElement(inputPhone).sendKeys(phone);
    }

    public void clickForward () {
        driver.findElement(buttonForward).click();
    }
}
