import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.MainPage;
import pages.OrderPageOrder;
import pages.OrderPagePerson;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(Parameterized.class)
public class OrderFlowTest {

    private final WebDriver driver = new ChromeDriver();
    //private WebDriver driver = new FirefoxDriver();
    private final int buttonOrder;
    private final String name;
    private final String surname;
    private final String adress;
    private final String metro;
    private final String phone;
    private String orderDate;
    private final String time;
    private final String colour;
    private final String comment;

    public OrderFlowTest(int buttonOrder, String name, String surname, String adress, String metro, String phone,
                         String orderDate, String time, String colour, String comment) {
        this.buttonOrder = buttonOrder;
        this.name = name;
        this.surname = surname;
        this.adress = adress;
        this.metro = metro;
        this.phone = phone;
        this.orderDate = orderDate;
        this.time = time;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][] {
                { 0 , "Человек", "Велосипед", "Тверская улица", "Тверская", "89999999999", "01.01.2025", "сутки", "black", "очень срочно"},
                { 0 , "чебурек", "такси", "Москва", "Лубянка", "+79999999999", null, "трое суток", "grey", null},

        };
    }

    @Test
    public void orderFlow() {

        if(orderDate==null) {
            orderDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.clickOrderButton(buttonOrder);

        OrderPagePerson orderPagePerson = new OrderPagePerson(driver);
        orderPagePerson.fillOrderForm(name, surname, adress, metro, phone);
        orderPagePerson.clickForward();

        OrderPageOrder orderPageOrder = new OrderPageOrder(driver);
        orderPageOrder.fillOrderForm(orderDate, time, colour, comment);
        orderPageOrder.clickOrderButton();
        orderPageOrder.clickYesButton(); //В хроме не кликается Да
        orderPageOrder.getSuccessStatus();

    }

    @After
    public void quit() {
        driver.quit();
    }
}
