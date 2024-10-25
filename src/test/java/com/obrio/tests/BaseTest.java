package com.obrio.tests;

import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    protected SoftAssert soft;
    @BeforeClass(alwaysRun = true)
    public void beforeClassActions() {
        soft = new SoftAssert();
    }
}