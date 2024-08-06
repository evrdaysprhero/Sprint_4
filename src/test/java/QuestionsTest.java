import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

@RunWith(Parameterized.class)
public class QuestionsTest {
    private final WebDriver driver = new ChromeDriver();
    private final String question;
    private final String answer;

    public QuestionsTest(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    @Parameterized.Parameters
    public static Object[][] getQuestionData() {
        return new Object[][] {
                { "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                { "Хочу сразу несколько самокатов! Так можно?",
                        "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
        };
    }

    @Test
    public void checkQuestionsTest() {
    //Проверяем выпадающий список в разделе «Вопросы о важном»
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.scrollToQuestions();
        String answerFinded = mainPage.getQuestionAnswer(question);
        Assert.assertEquals("Текст ответа не соответствует ожидаемому", answer, answerFinded);

    }

    @After
    public void quit() {
        driver.quit();
    }
}
