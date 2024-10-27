package com.obrio.utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.obrio.drivers.DriverManager.getDriverInstance;

public class WaitUtils {
    public static final int DEFAULT_TIMEOUT_TO_WAIT = 3;
    public static final int LONG_TIMEOUT_TO_WAIT = 30;

    private static WaitUtils waitUtils;

    private WaitUtils() {
    }

    public static WaitUtils getInstance() {
        if (waitUtils == null) {
            waitUtils = new WaitUtils();
        }
        return waitUtils;
    }

    public static FluentWait<AndroidDriver> getWebDriverWait(int timeInSeconds) {
        return new FluentWait<>(getDriverInstance()).withTimeout(Duration.ofSeconds(timeInSeconds))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class);
    }

    public static FluentWait<AndroidDriver> getWebDriverWait() {
        return getWebDriverWait(DEFAULT_TIMEOUT_TO_WAIT);
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

    public static void waitForSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
