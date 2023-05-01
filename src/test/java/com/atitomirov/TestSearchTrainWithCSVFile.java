package com.atitomirov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TestSearchTrainWithCSVFile extends TestBase {

    @CsvFileSource(resources = "/successfulSearchTrainTest.csv")
    @ParameterizedTest(name = "Найден транспорт для перемещения из: {0} в: {1}")
    @DisplayName("Тест для проверки поиска транспорта")
    @Tags({
            @Tag("SMOKE"),
            @Tag("SEARCH")
    })
    void successfulSearchTest(String cityDeparture, String cityArrival) {

        open("https://ezdy.ru/raspisanie-poezdov/");

        $("[placeholder=Откуда]").setValue(cityDeparture);
        $("[placeholder=Куда]").setValue(cityArrival);
        $("input.btn.btn_custom").click();
        $(".schedule").shouldHave(text(cityDeparture + " - " + cityArrival));

    }

}
