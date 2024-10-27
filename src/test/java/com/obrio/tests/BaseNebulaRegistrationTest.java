package com.obrio.tests;

import com.github.javafaker.Faker;
import com.obrio.drivers.DriverManager;
import com.obrio.pages.HomeScreen;
import com.obrio.pages.registration_screens.*;
import com.obrio.pages.settings_screens.ProfileSettingScreen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

public class BaseNebulaRegistrationTest {
    protected static final Faker FAKER = new Faker();
    protected final String SCREEN_SHOULD_BE_OPENED = "Screen should be opened";
    protected RegistrationHomeScreen registrationHomeScreen;
    protected GoalsScreen goalsScreen;
    protected YourGoalsScreen yourGoalsScreen;
    protected BirthChartScreen birthChartScreen;
    protected DateOfBirthScreen dateOfBirthScreen;
    protected TimeOfBirthScreen timeOfBirthScreen;
    protected PlaceOfBirthScreen placeOfBirthScreen;
    protected PalmReadingScreen palmReadingScreen;
    protected GenderScreen genderScreen;
    protected RelationshipStatusScreen relationshipStatusScreen;
    protected MotivationScreen motivationScreen;
    protected HoroscopeRemainderScreen horoscopeRemainderScreen;
    protected SocialMediaScreen socialMediaScreen;
    protected CreateAccountScreen createAccountScreen;
    protected HomeScreen homeScreen;
    protected ProfileSettingScreen settingScreen;
    protected String dayOfBirth;
    protected String monthOfBirth;
    protected String placeOfBirth;
    protected String name;
    protected String email;
    protected String password;
    protected String formattedDate;
    protected int yearOfBirth;
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