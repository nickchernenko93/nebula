package com.obrio.utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.obrio.utils.WaitUtils.getWebDriverWait;

public class ElementUtils {
    private static ElementUtils elementUtils;

    private ElementUtils() {
    }

    public static ElementUtils getInstance() {
        if (elementUtils == null) {
            elementUtils = new ElementUtils();
        }
        return elementUtils;
    }

    public static boolean isElementShown(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException ignored) {
            return false;
        }
    }

    public static boolean isElementEnable(WebElement element) {
        try {
            return element.isEnabled();
        } catch (NoSuchElementException | StaleElementReferenceException ignored) {
            return false;
        }
    }

    public static void waitUntil(Supplier<Boolean> condition, int duration) {
        Function<WebDriver, Boolean> conditionToBeTrue = (WebDriver d) -> condition.get().equals(Boolean.TRUE);
        getWebDriverWait(duration).until(conditionToBeTrue);
    }

    public void tryWaitUntil(Supplier<Boolean> condition, int duration) {
        try {
            waitUntil(condition, duration);
        } catch (Exception ignored) {

        }
    }
}
