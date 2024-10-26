package com.obrio.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.NoSuchElementException;

import static com.obrio.drivers.DriverManager.getDriverInstance;

public class SwipeUtils {

    private static final PointerInput FINGER = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    public final int DEFAULT_SWIPE_ATTEMPT = 12;

    public static void swipe(int startX, int startY, int endX, int endY) {
        Sequence swipe = new Sequence(FINGER, 1);
        swipe.addAction(FINGER.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(FINGER.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(FINGER.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(FINGER.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        getDriverInstance().perform(Collections.singletonList(swipe));
    }

    /**
     * Element could not be present in DOM from the beginning
     */
    public void swipeToElement(By locator, int swipeAttempt) {
        int count = 0;
        int startX = 1056;
        int startY = 1041;
        int endX = 1184;
        int endY = 1137;
        WebDriverWait wait = new WebDriverWait(getDriverInstance(), Duration.ofSeconds(10));
        while (count < swipeAttempt) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                return;
            } catch (TimeoutException | NoSuchElementException e) {
                swipe(startX, startY, endX, endY);
                count++;
            }
        }
    }
}








