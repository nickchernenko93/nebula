package com.obrio.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.obrio.utils.WaitUtils.getWebDriverWait;

public abstract class AbstractUIElements implements UIElements {

    protected WebElement element;

    public AbstractUIElements(WebElement element, String elementName) {
        this.element = element;
    }

    public AbstractUIElements(WebElement element) {
        this.element = element;
    }

    @Override
    public void click() {
        element.click();
    }

    @Override
    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    @Override
    public String getText() {
        return element.getText();
    }

    @Override
    public boolean isEnabled() {
        return element.isEnabled();
    }

    public WebElement getElement() {
        return element;
    }

}
