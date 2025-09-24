package org.wikipedia.android.at.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.wikipedia.android.at.utils.allure.annotations.Layer;
import org.wikipedia.android.at.utils.allure.annotations.Manual;

import static io.qameta.allure.Allure.step;

@Owner("sergivonavi")
@Layer("mobile")
@Tag("mobile")
@Epic("Android-приложение")
@Feature("Авторизация")
@Story("Вход в приложение")
@DisplayName("Авторизация")
public class LoginTests {

    @Tags({@Tag("regress"), @Tag("smoke")})
    @Test
    @Manual
    @DisplayName("Авторизация с валидными данными")
    @Severity(SeverityLevel.CRITICAL)
    void loginWithValidCredentialsTest() {
        step("Пропускаем онбординг");
        step("Нажимаем таб \"More\"");
        step("Нажимаем опцию \"Log in / join Wikipedia\"");
        step("Нажимаем кнопку \"Log in\"");
        step("Авторизуемся как пользователь Test Testov", () -> {
            step("Вводим [test_testov] в поле \"Username\"");
            step("Вводим [test1234] в поле \"Password\"");
            step("Нажимаем кнопку \"Log in\"");
        });
        step("Проверяем переход на экран профиля");
    }

    @Tag("regress")
    @Test
    @Manual
    @DisplayName("Авторизация с несуществующим логином")
    @Severity(SeverityLevel.NORMAL)
    void loginWithInvalidCredentialsTest() {
        step("Пропускаем онбординг");
        step("Нажимаем таб \"More\"");
        step("Нажимаем опцию \"Log in / join Wikipedia\"");
        step("Нажимаем кнопку \"Log in\"");
        step("Авторизуемся как пользователь", () -> {
            step("Вводим несуществующий логин в поле \"Username\"");
            step("Вводим любой пароль в поле \"Password\"");
            step("Нажимаем кнопку \"Log in\"");
        });
        step("Проверяем отображение снекбара с ошибкой");
    }
}
