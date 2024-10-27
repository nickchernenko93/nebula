package com.obrio.elements;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

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
