package ru.netology.tour;

import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import ru.netology.tour.CardInfo;
import ru.netology.tour.DataHelper;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardPage {

    private final SelenideElement cardNumberField = $$("input.input__control").get(0);
    private final SelenideElement monthField = $$("input.input__control").get(1);
    private final SelenideElement yearField = $$("input.input__control").get(2);
    private final SelenideElement holderField = $$("input.input__control").get(3);
    private final SelenideElement cvcField = $$("input.input__control").get(4);

    private final SelenideElement continueButton = $$("button.button").findBy(text("Продолжить"));

    private final SelenideElement successNotification = $(".notification_status_ok");
    private final SelenideElement errorNotification = $(".notification_status_error");

    public CardPage() {
        $("form").shouldBe(visible);
    }

    public void fillCardInfo(CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getNumber());
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        holderField.setValue(cardInfo.getHolder());
        cvcField.setValue(cardInfo.getCvc());
        continueButton.click();
    }

    public void shouldShowSuccessNotification() {
        successNotification.shouldBe(visible, Duration.ofSeconds(15));
        successNotification.shouldHave(text("Операция одобрена Банком"));
    }

    public void shouldShowErrorNotification() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(15));
        errorNotification.shouldHave(text("Ошибка! Банк отказал в проведении операции."));
    }

    public void shouldHighlightInvalidFields() {
        cardNumberField.parent().shouldHave(cssClass("input_invalid"));
        monthField.parent().shouldHave(cssClass("input_invalid"));
        yearField.parent().shouldHave(cssClass("input_invalid"));
        holderField.parent().shouldHave(cssClass("input_invalid"));
        cvcField.parent().shouldHave(cssClass("input_invalid"));
    }
}

