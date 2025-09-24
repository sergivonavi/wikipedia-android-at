package org.wikipedia.android.at.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;
import java.util.Collections;

public class GestureUtils {

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    /**
     * Выполняет свайп по экрану.
     *
     * @param driver      драйвер
     * @param direction   направление свайпа
     * @param lengthRatio часть длины экрана, на которую выполнить свайп (0.0 - 1.0)
     * @see Direction
     */
    public static void swipe(RemoteWebDriver driver, Direction direction, double lengthRatio) {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = size.height / 2;
        int endX = startX;
        int endY = startY;

        switch (direction) {
            case UP -> endY = (int) (startY - size.height * lengthRatio);
            case DOWN -> endY = (int) (startY + size.height * lengthRatio);
            case LEFT -> endX = (int) (startX - size.width * lengthRatio);
            case RIGHT -> endX = (int) (startX + size.width * lengthRatio);
        }

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }
}
