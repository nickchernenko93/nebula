package com.obrio.drivers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class DriverManager {

    private DriverManager(){

    }

    private static Properties properties = null;
    private static UiAutomator2Options capabilities = null;

    private static AndroidDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        setProperties();
        setCapability();
        setDriver();
    }

    @AfterClass(alwaysRun = true)
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private static void setProperties() {
        if (properties == null) {
            try {
                properties = new Properties();
                FileInputStream fis = new FileInputStream(getPathWithCorrectSeparator("src/test/resources/config.properties"));
                properties.load(fis);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void setDriver() {
        try {
            driver = new AndroidDriver(
                    new URL(properties.getProperty("appium.server.url")),
                    capabilities
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static AndroidDriver getDriverInstance() {
        if (driver == null) {
            try {
                setProperties();
                setCapability();
                driver = new AndroidDriver(new URL(properties.getProperty("appium.server.url")), capabilities);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return driver;
    }

    private static void setCapability() {
        if (capabilities == null) {
            capabilities = new UiAutomator2Options();
            capabilities.setCapability("platformName", properties.getProperty("platform.name"));
            capabilities.setCapability("platformVersion", properties.getProperty("platform.version"));
            capabilities.setCapability("deviceName", properties.getProperty("device.name"));
            capabilities.setCapability("automationName", properties.getProperty("automationName"));
            capabilities.setCapability("app", getAppAbsolutePath());
            capabilities.setCapability("newCommandTimeout", 1200);
        }
    }


    private static String getAppAbsolutePath() {
        String appName = properties.getProperty("app.name");
        URL resource = DriverManager.class.getClassLoader().getResource(appName);
        if (resource == null) {
            throw new RuntimeException("App not found " + appName + " in resource directory");
        }
        try {
            return new File(resource.toURI()).getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Something went wrong for app: " + appName);
        }
    }

    public static String getSessionId() {
        if (driver != null) {
            return driver.getSessionId().toString();
        } else {
            throw new IllegalStateException("Session has not been started yet.");
        }
    }

    private static String getPathWithCorrectSeparator(String path) {
        return path.replaceAll("/", File.separator);
    }
}
