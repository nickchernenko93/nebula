package com.obrio.tests;

import com.obrio.data.ui.fields.SettingsFields;
import com.obrio.pages.registration_screens.RegistrationHomeScreen;
import com.obrio.pages.settings_screens.AccountSettingScreen;
import com.obrio.pages.settings_screens.ProfileSettingScreen;
import com.obrio.utils.DateFormatUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import static com.obrio.data.registration.Genders.*;
import static com.obrio.data.registration.Goals.*;
import static com.obrio.data.registration.Interests.LOVE;
import static com.obrio.data.registration.Interests.MONEY;
import static com.obrio.data.registration.RelationshipStatuses.SINGLE;

public class VerifySuccessfulNebulaRegistrationTest extends BaseNebulaRegistrationTest {

    private final SettingsFields NAME = SettingsFields.NAME;
    private final SettingsFields GENDER = SettingsFields.GENDER;
    private final SettingsFields RELATIONSHIP_STATUS = SettingsFields.RELATIONSHIP_STATUS;
    private final SettingsFields DATE_OF_BIRTH = SettingsFields.DATE_OF_BIRTH;
    private final SettingsFields PLACE_OF_BIRTH = SettingsFields.PLACE_OF_BIRTH;
    private final SettingsFields EMAIL = SettingsFields.EMAIL;
    private final SettingsFields LOGIN_METHOD = SettingsFields.LOGIN_METHOD;
    private final SettingsFields USER_ID = SettingsFields.USER_ID;
    private String dayOfBirth;
    private String monthOfBirth;
    private String placeOfBirth;
    private String name;
    private String email;
    private String password;
    private String formattedDate;
    private String yearOfBirth;

    @DataProvider(name = "goalsDataProvider")
    public Object[][] goalsDataProvider() {
        return new Object[][]{
                {UNDERSTAND_MYSELF.getValue()},
                {FIND_PERFECT_MATCH.getValue()},
                {RECEIVE_DAILY_INSIGHT_AND_TIPS.getValue()},
                {IMPROVE_RELATIONSHIP.getValue()},
        };
    }

    @BeforeClass(alwaysRun = true)
    public RegistrationHomeScreen openHomeScreen() {
        createTestData();
        return registrationHomeScreen = new RegistrationHomeScreen();
    }

    @Test
    public void verifyRegistrationHomeScreenIsOpened() {
        Assert.assertTrue(registrationHomeScreen.isRegistrationHomeScreenOpened(), SCREEN_SHOULD_BE_OPENED);
    }

    @Test(dataProvider = "goalsDataProvider", dependsOnMethods = "verifyRegistrationHomeScreenIsOpened", alwaysRun = true)
    public void verifyGoalScreenIsOpenedAndGoalsAreSelected(String goal) {
        goalsScreen.selectGoals(goal);

        soft.assertTrue(goalsScreen.isGoalSelected(goal), String.format("'%s' goal should be selected", goal));
        soft.assertAll();
    }

    @Test(dependsOnMethods = "verifyGoalScreenIsOpenedAndGoalsAreSelected", alwaysRun = true)
    public void verifyYourGoalsScreenIsOpened() {
        yourGoalsScreen = goalsScreen.clickNextButtonAndOpenYourGoalsScreen();
        Assert.assertTrue(yourGoalsScreen.isYourGoalPageOpened(), SCREEN_SHOULD_BE_OPENED);
    }

    @Test(dependsOnMethods = "verifyYourGoalsScreenIsOpened", alwaysRun = true)
    public void verifyBirthChatScreenIsOpened() {
        birthChartScreen = yourGoalsScreen.clickNextButtonAndOpenBirthChartScreen();

        Assert.assertTrue(birthChartScreen.isBirthChartPageOpened(), SCREEN_SHOULD_BE_OPENED);
    }

    @Test(dependsOnMethods = "verifyBirthChatScreenIsOpened", alwaysRun = true)
    public void verifyDateOfBirthScreenOpenedAndDateIsSet() {
        dateOfBirthScreen = birthChartScreen.clickNextButtonAndOpenDateOfBirthScreen();
        dateOfBirthScreen.selectMonth(monthOfBirth);
        dateOfBirthScreen.selectDay(dayOfBirth);
        dateOfBirthScreen.selectYear(yearOfBirth);

        Arrays.asList(dayOfBirth, monthOfBirth, yearOfBirth).forEach(value -> soft.assertTrue(dateOfBirthScreen.isDateValueSetInPickerWheel(value),
                String.format("'%s' value should be present and set", value)));
        soft.assertAll();
    }

    @Test(dependsOnMethods = "verifyDateOfBirthScreenOpenedAndDateIsSet", alwaysRun = true)
    public void verifyInfoMessageAppearedAfterPressingIDontKnowButton() {
        String expectedInfoMessage = "We need the time of your birth to make astrological predictions more accurate." +
                " You can come back and add it when you figure it out.";
        timeOfBirthScreen = dateOfBirthScreen.clickNextButtonAndOpenTimeOfBirthScreen()
                .tapIDontKnowButton();
        String actualMessage = timeOfBirthScreen
                .getTextFromInfoMessage();

        Assert.assertEquals(actualMessage, expectedInfoMessage,
                String.format("'%s' actual message is not equal to '%s' expected", actualMessage, expectedInfoMessage));
    }

    @Test(dependsOnMethods = "verifyInfoMessageAppearedAfterPressingIDontKnowButton", alwaysRun = true)
    public void verifyPlaceOfBirthValueIsSet() {
        placeOfBirthScreen = timeOfBirthScreen.clickSkipButtonAndOpenPlaceOfBirthScreen();
        placeOfBirthScreen.setPlaceOfBirth(placeOfBirth);

        Assert.assertTrue(placeOfBirthScreen.isSearchedPlacePresentInSearchResult(placeOfBirth),
                String.format("'%s' city should be present in search result", placeOfBirth));
    }

    @Test(dependsOnMethods = "verifyPlaceOfBirthValueIsSet", alwaysRun = true)
    public void verifyPlaceOfBirthSelectedAndPalmScreenOpened() {
        palmReadingScreen = placeOfBirthScreen.confirmPlaceOfBirthAndOpenPalmReadingScreen(placeOfBirth);

        Assert.assertTrue(palmReadingScreen.isPalmReadingScreenOpened(), SCREEN_SHOULD_BE_OPENED);
    }

    @Test(dependsOnMethods = "verifyPlaceOfBirthSelectedAndPalmScreenOpened", alwaysRun = true)
    public void verifyGenderScreenOpenedAndGenderTilePresent() {
        genderScreen = palmReadingScreen.clickSkipButtonAndOpenGenderScreen();

        Arrays.asList(MALE, FEMALE, NON_BINARY).forEach(genders -> soft.assertTrue(genderScreen.isGenderTileShown(genders),
                String.format("'%s' gender tile should be shown", genders.getValue())));
        soft.assertAll();
    }

    @Test(dependsOnMethods = "verifyGenderScreenOpenedAndGenderTilePresent", alwaysRun = true)
    public void verifyNameIsSetAndRelationshipScreenOpened() {
        relationshipStatusScreen = genderScreen.selectGenderAndOpenNameScreen(MALE)
                .setNameAndOpenRelationshipStatusScreen(name);

        Assert.assertTrue(relationshipStatusScreen.isRelationshipScreenOpened(), SCREEN_SHOULD_BE_OPENED);
    }

    @Test(dependsOnMethods = "verifyNameIsSetAndRelationshipScreenOpened", alwaysRun = true)
    public void verifyRelationshipStatusAndInterestsSelectedAndMotivationScreenOpened() {
        motivationScreen = relationshipStatusScreen.selectRelationshipStatusAndOpenInterestsScreen(SINGLE)
                .selectInterestsAndOpenMotivationScreen(MONEY.getValue(), LOVE.getValue());

        Assert.assertTrue(motivationScreen.isMotivationScreenOpened(), SCREEN_SHOULD_BE_OPENED);
    }

    @Test(dependsOnMethods = "verifyRelationshipStatusAndInterestsSelectedAndMotivationScreenOpened", alwaysRun = true)
    public void verifyInterestsSelectedAndHoroscopeRemainderScreenOpened() {
        final String YES_OPTION = "YES";
        final String NO_OPTION = "NO";
        horoscopeRemainderScreen = motivationScreen.selectOption(YES_OPTION)
                .clickNextButton()
                .selectOption(NO_OPTION)
                .selectOption(YES_OPTION)
                .clickNextButton()
                .selectOption(YES_OPTION)
                .clickNextButtonAndOpenHoroscopeRemainderScreen();

        Assert.assertTrue(horoscopeRemainderScreen.isBellImageShown(), "Bell image should be shown");
    }

    @Test(dependsOnMethods = "verifyInterestsSelectedAndHoroscopeRemainderScreenOpened", alwaysRun = true)
    public void verifyNextButtonIsDisableWhenInterestIsNotSelectedAndSkipButtonIsEnableOnSocialMediaScreen() {
        socialMediaScreen = horoscopeRemainderScreen.clickSkipButtonAndOpenSocialMediaScreen();
        String nextButtonName = "NEXT";
        String skipButtonName = "SKIP";

        soft.assertFalse(socialMediaScreen.isButtonEnabled(nextButtonName),
                String.format("'%s' button should be disabled", nextButtonName));
        soft.assertTrue(socialMediaScreen.isButtonEnabled(skipButtonName),
                String.format("'%s' button should be enabled", skipButtonName));
        soft.assertAll();
    }

    @Test(dependsOnMethods = "verifyNextButtonIsDisableWhenInterestIsNotSelectedAndSkipButtonIsEnableOnSocialMediaScreen", alwaysRun = true)
    public void verifyCreateAccountScreenOpenedAndSignInViaEmailAndViaGoogleButtonsPresent() {
        createAccountScreen = socialMediaScreen.clickSkipButtonAndOpenReachYourGoalScreen()
                .clickNextButtonAndOpenReviewsScreen()
                .clickNextButtonAndOpenCreateAccountScreen();
        final String BUTTON_SHOULD_BE_SHOWN = "Button should be shown";

        soft.assertTrue(createAccountScreen.isCreateAccountViaEmailButtonShown(), BUTTON_SHOULD_BE_SHOWN);
        soft.assertTrue(createAccountScreen.isCreateAccountViaGoogleButtonShown(), BUTTON_SHOULD_BE_SHOWN);
        soft.assertAll();
    }

    @Test(dependsOnMethods = "verifyCreateAccountScreenOpenedAndSignInViaEmailAndViaGoogleButtonsPresent", alwaysRun = true)
    public void verifyEmailAndPasswordSetAndHoroscopeScreenOpened() {
        homeScreen = createAccountScreen.clickCreateAccountViaEmailButtonAndOpenSignUpScreen()
                .fillInDataForRegistrationAndExpectHomeScreen(email, password);

        Assert.assertTrue(homeScreen.isHomeScreenOpened(), SCREEN_SHOULD_BE_OPENED);
    }

    @Test(dependsOnMethods = "verifyEmailAndPasswordSetAndHoroscopeScreenOpened", alwaysRun = true)
    public void verifyProfileSettingsFieldHasCorrectValuesAfterRegistration() {
        settingScreen = homeScreen.openSettings()
                .openNeededSettingScreen("My Profile", ProfileSettingScreen::new);

        soft.assertEquals(settingScreen.getValueFromField(NAME), name,
                String.format(VALUE_FROM_FIELD_SHOULD_BE_EQUAL_TO_EXPECTED, NAME, name));
        soft.assertEquals(settingScreen.getValueFromField(GENDER), MALE.getValue(),
                String.format(VALUE_FROM_FIELD_SHOULD_BE_EQUAL_TO_EXPECTED, GENDER, MALE.getValue()));
        soft.assertEquals(settingScreen.getValueFromField(RELATIONSHIP_STATUS), SINGLE.getValue(),
                String.format(VALUE_FROM_FIELD_SHOULD_BE_EQUAL_TO_EXPECTED, RELATIONSHIP_STATUS, SINGLE.getValue()));
        soft.assertEquals(settingScreen.getValueFromField(DATE_OF_BIRTH), formattedDate,
                String.format(VALUE_FROM_FIELD_SHOULD_BE_EQUAL_TO_EXPECTED, DATE_OF_BIRTH, formattedDate));
        soft.assertTrue(settingScreen.getValueFromField(PLACE_OF_BIRTH).contains(placeOfBirth),
                String.format("'%s' value should be set in '%s' field", PLACE_OF_BIRTH, placeOfBirth));
        soft.assertAll();
    }

    @Test(dependsOnMethods = "verifyProfileSettingsFieldHasCorrectValuesAfterRegistration", alwaysRun = true)
    public void verifyAccountFieldValuesHasCorrectValuesAndUserIdFieldPopulatedAfterRegistration() {
        settingScreen.closeSettingAndOpenNeededSettingScreen("Account details", AccountSettingScreen::new);

        soft.assertEquals(settingScreen.getValueFromField(EMAIL), email,
                String.format(VALUE_FROM_FIELD_SHOULD_BE_EQUAL_TO_EXPECTED, EMAIL, email));
        soft.assertEquals(settingScreen.getValueFromField(LOGIN_METHOD), EMAIL.getValue(),
                String.format(VALUE_FROM_FIELD_SHOULD_BE_EQUAL_TO_EXPECTED, LOGIN_METHOD, EMAIL.getValue()));
        soft.assertFalse(settingScreen.getValueFromField(USER_ID).isEmpty(),
                String.format("'%s' field should not be empty", USER_ID));
        soft.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethodActions(Method method) {
        if (method.getName().equalsIgnoreCase("verifyRegistrationHomeScreenIsOpened")) {
            goalsScreen = registrationHomeScreen.clickGetStartedButtonAndOpenGoalsScreen();
        }
    }

    // TODO: 27.10.2024 need to create/use another generator for email/password because sometimes FAKER set invalid values
    private void createTestData() {
        Date date = FAKER.date().birthday(34, 36);
        DateFormatUtils dateFormatUtils = new DateFormatUtils();
        formattedDate = dateFormatUtils.formatDate(String.valueOf(date));
        dayOfBirth = dateFormatUtils.formatDayDate(date);
        monthOfBirth = dateFormatUtils.formatMonthDate(date);
        yearOfBirth = dateFormatUtils.formatYearDate(date);
        placeOfBirth = FAKER.address().cityPrefix();
        name = FAKER.name().fullName();
        password = FAKER.internet().password(8, 12, true, true);;
        email = FAKER.internet().emailAddress();
    }
}
