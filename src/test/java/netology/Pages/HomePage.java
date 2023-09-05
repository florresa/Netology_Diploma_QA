package netology.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {
    private SelenideElement heading = $(byText("Путешествие дня"));
    private SelenideElement buyButton = $x("//button[@class='button button_size_m button_theme_alfa-on-white']");
    private SelenideElement buyInCreditButton = $x("//button[@class='button button_view_extra button_size_m button_theme_alfa-on-white']");


    public HomePage() {
        heading.shouldBe(visible);
    }

    public BuyPage orderCardPage() {
        buyButton.click();
        return new BuyPage();
    }

    public BuyInCreditPage creditPage() {
        buyInCreditButton.click();
        return new BuyInCreditPage();
    }
}
