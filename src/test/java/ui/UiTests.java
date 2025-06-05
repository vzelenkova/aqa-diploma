package ui;

import com.codeborne.selenide.Configuration;
import ru.netology.tour.DataHelper;
import db.DbUtils;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class UiTests {

    @BeforeAll
    static void setupAll() {
        Configuration.headless = true;
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void openPage() throws Exception {
        DbUtils.clearTables();
        open(System.getProperty("sut.url", "http://localhost:8080"));
    }

    @Test
    void shouldBuyTourWithApprovedCard() {
        $$("button").findBy(text("Купить")).click();
        fillCardForm(DataHelper.getApprovedCard());
        $(".notification_status_ok").shouldBe(visible);
    }

    @Test
    void shouldDeclineTourWithDeclinedCard() {
        $$("button").findBy(text("Купить")).click();
        fillCardForm(DataHelper.getDeclinedCard());
        $(".notification_status_error").shouldBe(visible);
    }

    private void fillCardForm(ru.netology.tour.CardInfo info) {
        $$(".input__control").get(0).setValue(info.getNumber());
        $$(".input__control").get(1).setValue(info.getMonth());
        $$(".input__control").get(2).setValue(info.getYear());
        $$(".input__control").get(3).setValue(info.getHolder());
        $$(".input__control").get(4).setValue(info.getCvc());
        $$("button").findBy(text("Продолжить")).click();
    }
}
