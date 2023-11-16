import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;




public class DebitCardTest {
    @BeforeEach
    public void initialUrl() {
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
    public void shouldFillValidDataWithHyphen() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Рогов-Василий");
        form.$("[data-test-id=phone] input").setValue("+79990009988");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__text").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    public void shouldFillFieldWrongName() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Timur");
        form.$("[data-test-id=phone] input").setValue("+79990009988");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__text").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    public void shouldFillFieldNameEmpty() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+79990009988");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__text").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }
    @Test
    public void shouldFillFieldPhoneEmpty() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Петров Александр");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__text").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }
    @Test
    public void shouldFillFieldWrongPhone() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Петров Александр");
        form.$("[data-test-id=phone] input").setValue("999999999");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__text").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    public void shouldFillFieldPhoneWithSpace() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Петров Александр");
        form.$("[data-test-id=phone] input").setValue("+7 999 900 1234");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__text").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    public void shouldFillFieldCheckBoxEmpty() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Петров Александр");
        form.$("[data-test-id=phone] input").setValue("+79999001234");
        form.$(".button__text").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}