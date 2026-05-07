package com.khnu.model;

import jakarta.persistence.*;


import java.util.UUID;

@Entity
@Table(name = "trading_accounts")
public class TradingAccount {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String mtLogin;
    private String mtPassword;
    private String server;
    private String Htimeframe;
    private String Ltimeframe;
    private boolean isRunning;

    public TradingAccount() {
    }

    public TradingAccount(String mtLogin, String mtPassword, String server, String Htimeframe, String Ltimeframe) {
        this.id = UUID.randomUUID();
        this.mtLogin = mtLogin;
        this.mtPassword = mtPassword;
        this.server = server;
        this.Htimeframe = Htimeframe;
        this.Ltimeframe = Ltimeframe;
        this.isRunning = false; // By default
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getMtLogin() { return mtLogin; }
    public void setMtLogin(String mtLogin) { this.mtLogin = mtLogin; }

    public String getMtPassword() { return mtPassword; }
    public void setMtPassword(String mtPassword) { this.mtPassword = mtPassword; }

    public String getServer() { return server; }
    public void setServer(String server) { this.server = server; }

    public String getHTimeframe() { return Htimeframe; }
    public void setHTimeframe(String timeframe) { this.Htimeframe = timeframe; }

    public String getLTimeframe() { return Ltimeframe; }
    public void setLTimeframe(String timeframe) { this.Ltimeframe = timeframe; }

    public boolean isRunning() { return isRunning; }
    public void setRunning(boolean running) { isRunning = running; }
}