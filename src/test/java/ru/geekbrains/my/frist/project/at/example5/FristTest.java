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
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.geekbrains.my.frist.project.at.example2.Page;



@Story("Навигация")
public class FristTest {

    @BeforeEach
    void setUp() {
        //Url удалённого веб драйвера
        Configuration.remote = "http://localhost:4444/wd/hub";
        //Определяем какой браузер будем использовать
        Configuration.browser = "chrome";
        //Размер окна браузера
        Configuration.browserSize = "1920x1080";
        //Создаём объект класса DesiredCapabilities, используется как настройка  вашей конфигурации с помощью пары ключ-значение
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //Включить поддержку отображения экрана браузера во время выполнения теста
        capabilities.setCapability("enableVNC", true);
        //Включение записи видео в процессе выполнения тестов
        capabilities.setCapability("enableVideo", true);
        //Переопределяем Browser capabilities
        Configuration.browserCapabilities = capabilities;


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