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
        boolean headless = Boolean.valueOf(properties.getProperty("headless"));

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
    }

    @AfterAll
    void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        log.info("Starting new context");
        context = browser.newContext();

        // Start tracing before creating / navigating a page.
        setupTracing();

        page = context.newPage();
    }

    private void setupTracing() {
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }

    @AfterEach
    void closeContext() {
        log.info("This trace is saved " + "trace" + context.hashCode()+".zip");
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace" + context.hashCode() + ".zip")));
        context.close();
    }

    public void testWithPropertiesFile() throws IOException {
        properties = loadProperties("test.properties");
        // Use the loaded properties in your test code
        String value = properties.getProperty("headless");
        // ...
    }

    private Properties loadProperties(String fileName) {
        properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            //Log
        }
        return properties;
    }
}
