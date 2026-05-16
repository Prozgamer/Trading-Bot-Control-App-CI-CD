package com.khnu.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "settings")
public class AppConfigurations {
    private String applicationVersion;
    private TestMode testMode = new TestMode();


    public String getApplicationVersion() {
        return applicationVersion;
    }

    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public TestMode getTestMode() {
        return testMode;
    }

    public void setTestMode(TestMode value) {
        this.testMode = value;
    }

    public static class TestMode
    {
        private boolean enabled;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean value) {
            this.enabled = value;
        }
    }
}