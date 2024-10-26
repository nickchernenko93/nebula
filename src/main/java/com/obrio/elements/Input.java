package com.obrio.elements;

import com.obrio.utils.ElementUtils;
import org.openqa.selenium.WebElement;

import static com.obrio.utils.ElementUtils.isClickable;
import static com.obrio.utils.ElementUtils.waitUntil;

public class Input extends AbstractUIElements implements IInput {
    public Input(WebElement element, String elementName) {
        super(element, elementName);
    }

    public Input(WebElement element) {
        super(element);
    }

    @Override
    public WebElement getWrappedElement() {
        return super.getElement();
    }

    public void type(String text) {
        waitUntil(()-> getWrappedElement().isDisplayed(), 3);
        waitUntil(() -> isClickable(getWrappedElement()));
        getWrappedElement().click();
        sendKeys(text);
    }

    @Override
    public void sendKeys(String text) {
        getWrappedElement().sendKeys(text);
    }
}
