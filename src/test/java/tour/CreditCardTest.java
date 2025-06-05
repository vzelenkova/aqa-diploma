package tour;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import ru.netology.tour.DataHelper;
import ru.netology.tour.CardPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CreditCardTest {

    @BeforeEach
    void setUp() {
        Configuration.headless = true;
        open("http://localhost:8080"); // Заменить на актуальный порт приложения
    }

    @Test
    void shouldSuccessfullyPayWithApprovedCard() {
        clickBuyButton(); // "Купить"
        var cardPage = new CardPage();
        var approvedCard = DataHelper.getApprovedCard();
        cardPage.fillCardInfo(approvedCard);
        cardPage.shouldShowSuccessNotification();
    }

    @Test
    void shouldDeclinePaymentWithDeclinedCard() {
        clickBuyButton();
        var cardPage = new CardPage();
        var declinedCard = DataHelper.getDeclinedCard();
        cardPage.fillCardInfo(declinedCard);
        cardPage.shouldShowErrorNotification();
    }

    @Test
    void shouldSuccessfullyBuyOnCreditWithApprovedCard() {
        clickCreditButton(); // "Купить в кредит"
        var cardPage = new CardPage();
        var approvedCard = DataHelper.getApprovedCard();
        cardPage.fillCardInfo(approvedCard);
        cardPage.shouldShowSuccessNotification();
    }

    @Test
    void shouldDeclineCreditWithDeclinedCard() {
        clickCreditButton();
        var cardPage = new CardPage();
        var declinedCard = DataHelper.getDeclinedCard();
        cardPage.fillCardInfo(declinedCard);
        cardPage.shouldShowErrorNotification();
    }

    @Test
    void shouldShowValidationErrorsForEmptyFields() {
        clickBuyButton();
        var cardPage = new CardPage();
        cardPage.fillCardInfo(DataHelper.getEmptyCard());
        cardPage.shouldHighlightInvalidFields();
    }

    @Test
    void shouldRejectInvalidCardNumber() {
        clickBuyButton();
        var cardPage = new CardPage();
        var card = DataHelper.getInvalidCard();
        cardPage.fillCardInfo(card);
        cardPage.shouldShowErrorNotification();
    }

//    @Test
//    void shouldRejectInvalidMonthAndYear() {
//        clickBuyButton();
//        var cardPage = new CardPage();
//        var card = DataHelper.getCardWithInvalidMonthYear();
//        cardPage.fillCardInfo(card);
//        cardPage.shouldShowErrorNotification();
//    }

//    @Test
//    void shouldRejectInvalidHolderAndCVC() {
//        clickBuyButton();
//        var cardPage = new CardPage();
//        var card = DataHelper.getCardWithInvalidHolderAndCVC();
//        cardPage.fillCardInfo(card);
//        cardPage.shouldShowErrorNotification();
//    }

    // Вспомогательные методы
    private void clickBuyButton() {
        $$("button").findBy(Condition.text("Купить")).click();
    }

    private void clickCreditButton() {
        $$("button").findBy(Condition.text("Купить в кредит")).click();
    }
}

