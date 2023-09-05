package netology.Tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import netology.Data.DataGenerator;
import netology.Data.SqlHelper;
import netology.Pages.BuyInCreditPage;
import netology.Pages.BuyPage;
import netology.Pages.HomePage;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Tests {

    HomePage homePage = open("http://localhost:8080/", HomePage.class);

    @AfterEach
    public void cleanBase() throws SQLException {
        SqlHelper.cleanDatabase();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

   // тесты на покупку тура картой
    @Test
    void checkCardApprovedForCard() {
        homePage.orderCardPage();
        var cardInfo = DataGenerator.getApprovedCard();
        var buyByCardPage = new BuyPage();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkSuccessNotification();
        assertEquals("APPROVED", SqlHelper.getCardRequestStatus());
    }

//    тут есть баг
    @Test
    void checkCardDeclinedForCard() {
        homePage.orderCardPage();
        var cardInfo = DataGenerator.getDeclinedCard();
        var buyByCardPage = new BuyPage();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkErrorNotification();
        assertEquals("DECLINED", SqlHelper.getCardRequestStatus());
    }

    @Test
    void checkAllFieldEmptyForCard() {
        homePage.orderCardPage();
        var cardInfo = DataGenerator.getEmptyCard();
        var buyByCardPage = new BuyPage();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkErrorNotificationFourFields();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void checkInvalidCardNumberForCard() {
        homePage.orderCardPage();
        var cardInfo = DataGenerator.getRandomInvalidCard();
        var buyByCardPage = new BuyPage();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void checkRandomValidCardForCard() {
        homePage.orderCardPage();
        var cardInfo = DataGenerator.getRandomValidCard();
        var buyByCardPage = new BuyPage();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkErrorNotification();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void checkInvalidMonthForCard() {
        homePage.orderCardPage();
        var cardInfo = DataGenerator.getRandomInvalidMonth();
        var buyByCardPage = new BuyPage();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongExpiryDateNotification();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void checkInvalidMonthOneSymbolForCard() {
        homePage.orderCardPage();
        var cardInfo = DataGenerator.getRandomInvalidMonthOneSymbol();
        var buyByCardPage = new BuyPage();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void checkInvalidYearForCard() {
        homePage.orderCardPage();
        var cardInfo = DataGenerator.getRandomInvalidYear();
        var buyByCardPage = new BuyPage();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkCardExpiredNotification();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void checkOneSymbolYearForCard() {
        homePage.orderCardPage();
        var cardInfo = DataGenerator.getRandomOneSymbolYear();
        var buyByCardPage = new BuyPage();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void checkInvalidCvcForCard() {
        homePage.orderCardPage();
        var cardInfo = DataGenerator.getRandomInvalidCvc();
        var buyByCardPage = new BuyPage();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void checkInvalidCvcOneSymbolForCard() {
        homePage.orderCardPage();
        var cardInfo = DataGenerator.getRandomInvalidCvcOneSymbol();
        var buyByCardPage = new BuyPage();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void checkEmptyFieldForCard() {
        homePage.orderCardPage();
        var cardInfo = DataGenerator.getOwnerEmptyFieldCard();
        var buyByCardPage = new BuyPage();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkFieldRequiredNotification();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    //    тут есть баг
    @Test
    void checkFullNameForCard() {
        homePage.orderCardPage();
        var cardInfo = DataGenerator.getRandomFullName();
        var buyByCardPage = new BuyPage();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    //    тут есть баг
    @Test
    void checkRussianNameForCard() {
        homePage.orderCardPage();
        var cardInfo = DataGenerator.getRandomRussianName();
        var buyByCardPage = new BuyPage();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    //    тут есть баг
    @Test
    void checkNumericNameForCard() {
        homePage.orderCardPage();
        var cardInfo = DataGenerator.getRandomNumericForName();
        var buyByCardPage = new BuyPage();
        buyByCardPage.fillInCardData(cardInfo);
        buyByCardPage.checkWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }



    // тесты на покупку тура в кредит
    @Test
    void checkCardApprovedForCredit() {
        homePage.creditPage();
        var cardInfo = DataGenerator.getApprovedCard();
        var creditPage = new BuyInCreditPage();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkSuccessNotification();
        assertEquals("APPROVED", SqlHelper.getCreditRequestStatus());
    }

    //    тут есть баг
    @Test
    void checkCardDeclinedForCredit() {
        homePage.creditPage();
        var cardInfo = DataGenerator.getDeclinedCard();
        var creditPage = new BuyInCreditPage();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkErrorNotification();
        assertEquals("DECLINED", SqlHelper.getCardRequestStatus());
    }

    @Test
    void checkAllFieldEmptyForCredit() {
        homePage.creditPage();
        var cardInfo = DataGenerator.getEmptyCard();
        var creditPage = new BuyInCreditPage();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkErrorNotificationFourFields();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void checkInvalidCardNumberForCredit() {
        homePage.creditPage();
        var cardInfo = DataGenerator.getRandomInvalidCard();
        var creditPage = new BuyInCreditPage();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void checkRandomValidCardForCredit() {
        homePage.creditPage();
        var cardInfo = DataGenerator.getRandomValidCard();
        var creditPage = new BuyInCreditPage();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkErrorNotification();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void checkInvalidMonthForCredit() {
        homePage.creditPage();
        var cardInfo = DataGenerator.getRandomInvalidMonth();
        var creditPage = new BuyInCreditPage();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongExpiryDateNotification();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void checkInvalidMonthOneSymbolForCredit() {
        homePage.creditPage();
        var cardInfo = DataGenerator.getRandomInvalidMonthOneSymbol();
        var creditPage = new BuyInCreditPage();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void checkInvalidYearForCredit() {
        homePage.creditPage();
        var cardInfo = DataGenerator.getRandomInvalidYear();
        var creditPage = new BuyInCreditPage();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkCardExpiredNotification();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void checkOneSymbolYearForCredit() {
        homePage.creditPage();
        var cardInfo = DataGenerator.getRandomOneSymbolYear();
        var creditPage = new BuyInCreditPage();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void checkInvalidCvcForCredit() {
        homePage.creditPage();
        var cardInfo = DataGenerator.getRandomInvalidCvc();
        var creditPage = new BuyInCreditPage();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void checkInvalidCvcOneSymbolForCredit() {
        homePage.creditPage();
        var cardInfo = DataGenerator.getRandomInvalidCvcOneSymbol();
        var creditPage = new BuyInCreditPage();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    @Test
    void checkEmptyFieldForCredit() {
        homePage.creditPage();
        var cardInfo = DataGenerator.getOwnerEmptyFieldCard();
        var creditPage = new BuyInCreditPage();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkFieldRequiredNotification();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    //    тут есть баг
    @Test
    void checkFullNameForCredit() {
        homePage.creditPage();
        var cardInfo = DataGenerator.getRandomFullName();
        var creditPage = new BuyInCreditPage();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    //    тут есть баг
    @Test
    void checkRussianNameForCredit() {
        homePage.creditPage();
        var cardInfo = DataGenerator.getRandomRussianName();
        var creditPage = new BuyInCreditPage();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }

    //    тут есть баг
    @Test
    void checkNumericNameForCredit() {
        homePage.creditPage();
        var cardInfo = DataGenerator.getRandomNumericForName();
        var creditPage = new BuyInCreditPage();
        creditPage.fillInCardData(cardInfo);
        creditPage.checkWrongFormat();
        assertEquals("0", SqlHelper.getOrderCount());
    }
}
