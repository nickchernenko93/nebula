package com.obrio.tests;

import com.github.javafaker.Faker;
import com.obrio.drivers.DriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    protected static final Faker FAKER = new Faker();
    protected SoftAssert soft;

    @BeforeClass(alwaysRun = true)
    public void beforeClassActions() {
        soft = new SoftAssert();
    }

    @AfterClass(alwaysRun = true)
    public void afterClassActions() {
        DriverManager.tearDown();
    }
}