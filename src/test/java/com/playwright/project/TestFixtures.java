package com.playwright.project;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

// Subclasses will inherit PER_CLASS behavior.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class TestFixtures {
    // Shared between all tests in the class.
    Playwright playwright;
    Browser browser;

    // New instance for each test method.
    BrowserContext context;
    Page page;

    static Properties properties;

    @BeforeAll
    void launchBrowser() {
        log.info("Loading properties file");
        properties = loadProperties("test.properties");

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(Boolean.valueOf(properties.getProperty("headless")))
        );
    }

    @AfterAll
    void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        log.info("Starting new context [" + context.hashCode() +"]");
        setupTracing();

        page = context.newPage();
    }

    private void setupTracing() {
        log.info("Setting up tracing options");
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(Boolean.valueOf(getPropertyValue("screenshots")))
                .setSnapshots(Boolean.valueOf(getPropertyValue("snapshots")))
                .setSources(Boolean.valueOf(getPropertyValue("sources"))));
    }

    @AfterEach
    void closeContext() {
        log.info("This trace is saved " + "trace" + context.hashCode() + ".zip");
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get(getPropertyValue("traceDirectory")+"trace" + context.hashCode() + ".zip")));
        context.close();
    }

    public static String getPropertyValue(String value) {
        log.info("Grabbing property value [" + value + "] from property file");
        return properties.getProperty(value);
    }

    private Properties loadProperties(String fileName) {
        properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("Properties file did not load successfully");
        }
        return properties;
    }
}
