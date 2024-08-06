package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    //заголовок раздела
    private static final By heading = By.xpath(".//div[text()='Вопросы о важном']");
    //кнопки Заказать
    private static final By orderButton = By.xpath(".//button[text()='Заказать']");

    public void openPage () {
        String url = "https://qa-scooter.praktikum-services.ru/";
        driver.get(url);
    }

    //прокрутить до раздела Вопросы о важном
    public void scrollToQuestions() {
        WebElement header = driver.findElement(heading);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", header);
    }

    //Раскрыть вопрос и получить ответ
    public String getQuestionAnswer(String questionText) {
        //не поняла, как в общем списке локаторов можно добавить переменную, поэтому локатор тут
        driver.findElement(By.xpath(".//div[@class='accordion']//div[text()='" + questionText + "']")).click();

        WebElement answer = driver.findElement(By.xpath(".//div[@class='accordion']//div[text()='" + questionText + "']/../..//p"));
        // Ожидание, что текст появится; ожидание не больше 3 секунд
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(answer));


        return answer.getText();
    }

    //клик по кнопке Заказать
    public void clickOrderButton(int buttonOrder) {
        driver.findElements(orderButton).get(buttonOrder).click();
    }

}
