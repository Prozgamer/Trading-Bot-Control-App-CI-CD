package com.khnu.model;

import java.util.UUID;

public class TradingAccount {
    private UUID id;
    private String mtLogin;
    private String mtPassword;
    private String server;
    private String timeframe;
    private boolean isRunning;

    public TradingAccount() {
    }

    public TradingAccount(String mtLogin, String mtPassword, String server, String timeframe) {
        this.id = UUID.randomUUID();
        this.mtLogin = mtLogin;
        this.mtPassword = mtPassword;
        this.server = server;
        this.timeframe = timeframe;
        this.isRunning = false; // By default
    }


    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getMtLogin() { return mtLogin; }
    public void setMtLogin(String mtLogin) { this.mtLogin = mtLogin; }

    public String getMtPassword() { return mtPassword; }
    public void setMtPassword(String mtPassword) { this.mtPassword = mtPassword; }

    public String getServer() { return server; }
    public void setServer(String server) { this.server = server; }

    public String getTimeframe() { return timeframe; }
    public void setTimeframe(String timeframe) { this.timeframe = timeframe; }

    public boolean isRunning() { return isRunning; }
    public void setRunning(boolean running) { isRunning = running; }
}