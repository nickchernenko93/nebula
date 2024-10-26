package com.obrio.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class DateOfBirthPage extends BasePage {

    @AndroidFindBy(id = "genesis.nebula:id/onboardingBirthDateImage")
    private WebElement birthDateLabel;
}
