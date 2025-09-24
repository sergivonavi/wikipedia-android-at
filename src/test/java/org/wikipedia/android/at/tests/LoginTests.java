package org.wikipedia.android.at.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.wikipedia.android.at.utils.allure.annotations.Manual;

import static io.qameta.allure.Allure.step;

@DisplayName("Авторизация")
public class LoginTests {

    @Test
    @Manual
    @DisplayName("Авторизация с валидными данными")
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

    @Test
    @Manual
    @DisplayName("Авторизация с несуществующим логином")
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
