package com.obrio.elements;

import org.openqa.selenium.WebElement;

public abstract class AbstractUiElements implements UIElements {

    protected WebElement element;

    public AbstractUiElements(WebElement element, String elementName) {
        this.element = element;
    }

    public AbstractUiElements(WebElement element) {
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
