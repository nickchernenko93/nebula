package com.obrio.utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.obrio.utils.WaitUtils.LONG_TIMEOUT_TO_WAIT;
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

    public static boolean isClickable(WebElement element) {
        try {
            getWebDriverWait();
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public static void waitUntil(Supplier<Boolean> condition, int duration) {
        Function<WebDriver, Boolean> conditionToBeTrue = (WebDriver d) -> condition.get().equals(Boolean.TRUE);
        getWebDriverWait(duration).until(conditionToBeTrue);
    }

    public static void waitUntil(Supplier<Boolean> condition) {
        Function<WebDriver, Boolean> conditionToBeTrue = (WebDriver d) -> condition.get().equals(Boolean.TRUE);
        getWebDriverWait().until(conditionToBeTrue);
    }

    public static void waitUntilElementIsNotDisplayed(Supplier<Boolean> condition) {
        Function<WebDriver, Boolean> conditionToBeFalse = (WebDriver d) -> condition.get().equals(Boolean.FALSE);
        getWebDriverWait(LONG_TIMEOUT_TO_WAIT).until(conditionToBeFalse);
    }

    public void tryWaitUntil(Supplier<Boolean> condition, int duration) {
        try {
            waitUntil(condition, duration);
        } catch (Exception ignored) {

        }
    }

    public void tryWaitUntil(Supplier<Boolean> condition) {
        try {
            waitUntil(condition);
        } catch (Exception ignored) {

        }
    }
    public void tryWaitUntilElementIsNotDisplayed(Supplier<Boolean> condition) {
        try {
            waitUntilElementIsNotDisplayed(condition);
        } catch (Exception ignored) {
        }
    }
}
