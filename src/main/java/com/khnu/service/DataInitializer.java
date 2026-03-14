package com.khnu.service;

import com.khnu.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Value("${settings.testMode.enabled:false}")
    private boolean isTestMode;

    @EventListener(ApplicationReadyEvent.class)
    public void fillTestData() {
        if (isTestMode) {
            logger.info("Test mode enabled. Filling database with mock data...");

            User testUser = new User();
            testUser.setLogin("prozgamer");
            testUser.setPassword("password123");


            logger.info("Test user 'prozgamer' created with ID: {}", testUser.getId());
        } else {
            logger.info("Test mode disabled. Skipping data initialization.");
        }
    }
}