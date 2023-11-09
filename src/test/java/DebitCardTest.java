import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;




public class DebitCardTest {
    @BeforeEach
    public void InitialUrl() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldFillValidData() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Рогов Василий");
        form.$("[data-test-id=phone] input").setValue("+79990009988");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__text").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldFillValidDataWithHyphen() {gg
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Рогов-Василий");
    }
}