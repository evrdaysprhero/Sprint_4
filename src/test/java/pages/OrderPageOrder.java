package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPageOrder {

    private final WebDriver driver;

    public OrderPageOrder(WebDriver driver){
        this.driver = driver;
    }

    //поле ввода Когда привезти
    private static final By inputWhen = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //поле ввода Срок аренды
    private static final By inputTime = By.className("Dropdown-root");
    //поле ввода Комментарий для курьера
    private static final By inputComment = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //кнопка Заказать
    private static final By buttonOrder = By.xpath(".//button[text()='Назад']/../button[text()='Заказать']");
    //кнопка Да
    private static final By buttonYes = By.xpath(".//button[text()='Да']");
    //Заказ оформлен
    private static final By successMessage = By.xpath(".//div[text()='Заказ оформлен']");

    //Заполнить форму
    public void fillOrderForm(String when, String time, String colour, String comment) {

        WebElement setDate = driver.findElement(inputWhen);
        setDate.sendKeys(when);
        setDate.sendKeys(Keys.TAB);
        setDate.sendKeys(Keys.RETURN);

        driver.findElement(inputTime).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-option' and text()='" + time + "']")).click();

        driver.findElement(By.id(colour)).click();
        if(comment != null) {
            driver.findElement(inputComment).sendKeys(comment);
        }
    }

    //Клик по кнопке Заказать
    public void clickOrderButton() {
        driver.findElement(buttonOrder).click();
    }

    //Клик по кнопке Да
    public void clickYesButton() {
        driver.findElement(buttonYes).click();
    }

    //Сообщение об успехе
    public void getSuccessStatus() {
        driver.findElement(successMessage).isDisplayed();
    }
}
