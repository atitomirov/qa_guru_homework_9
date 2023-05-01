package com.atitomirov;

import com.atitomirov.data.Locale;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.containExactTextsCaseSensitive;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestLocaleWithMethodSource extends TestBase {

    static Stream<Arguments> siteContainAllButtonsForSelectedLocale() {
        return Stream.of(
                Arguments.of(Locale.En, List.of(
                        "ABOUT", "PRODUCTS", "DEVELOPMENT", "CONTACTS")),
                Arguments.of(Locale.Ru, List.of(
                        "КОМПАНИЯ", "ПРОДУКТЫ", "ТЕХНОЛОГИИ",
                        "ПАРТНЁРЫ", "ОБУЧЕНИЕ", "ТЕХПОДДЕРЖКА"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Для локали {0} отображаются кнопки {1}")
    @DisplayName("Тест для проверки отображения кнопок при выборе языка на странице")
    @Tags({@Tag("CRITICAL"), @Tag("LANGUAGE")})
    void siteContainAllButtonsForSelectedLocale(
            Locale locale,
            List<String> buttons
    ) {
        open("https://www.basealt.ru/");
        $(".lang").click();
        $(".lang.open").$(byText(locale.name())).click();
        $$("#main_menu li a").shouldHave(containExactTextsCaseSensitive(buttons));

    }

}
