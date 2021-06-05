package ru.geekbrains.my.frist.project.at.example5;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.geekbrains.my.frist.project.at.example2.Page;



@Story("Навигация")
public class FristTest {

    @BeforeEach
    void setUP() {
        Configuration.browserCapabilities.setCapability("enableVNC", true);
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(false));

    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Программы обучения",
            "Мероприятия",
            "Форум",
            "Блог",
            "Тесты",
            "Карьера"
    })

    @Feature("Проверка перехода")
    public void  button(String name){
        Selenide.open("https://gb.ru/tests");
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().window().maximize();


        Page page = new Page();
        page.getNavigation().getButton(name).click();
        page.getHeader().getTitle().shouldHave(Condition.exactText(name));
    }
}