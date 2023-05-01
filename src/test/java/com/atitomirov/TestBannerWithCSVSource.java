package com.atitomirov;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.DisplayName;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestBannerWithCSVSource extends TestBase {

    @CsvSource({"Совместимость, Информация о совместимости",
            "Купить, Купить ОС «Альт»"})

    @ParameterizedTest(name = "Для кнопки: {0} на баннере отображается: {1}")
    @DisplayName("Тест для проверки отображения корректного баннера")
    @Tags({
            @Tag("CRITICAL"),
            @Tag("BANNER")
    })
    void searchShopByBrandWithCsvSource(String button, String message) {

        open("https://www.basealt.ru/");

        $(".top").$(byText(button)).click();
        $(".head").shouldHave(text(message));

    }

}
