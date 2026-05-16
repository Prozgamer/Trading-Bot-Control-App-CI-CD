package com.khnu.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "trading_accounts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TradingAccount {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @Column(name = "mt_login", nullable = false)
    private String mtLogin;

    @Column(name = "mt_password", nullable = false)
    private String mtPassword;

    private String server;
    private String htimeframe = "PERIOD_H4";
    private String ltimeframe = "PERIOD_M15";

    @Column(name = "is_running")
    private boolean isRunning = false;

    // Поля из советника с дефолтными значениями
    @Column(name = "lookback_candles")
    private Integer lookbackCandles = 20;

    @Column(name = "max_dd_prc")
    private BigDecimal maxDdPrc = new BigDecimal("2.50");

    @Column(name = "min_fvg_size")
    private Integer minFvgSize = 50;

    @Column(name = "candles_to_live")
    private Integer candlesToLive = 9;

    @Column(name = "structure_lookback")
    private Integer structureLookback = 12;

    @Column(name = "confirmation_wait_bars")
    private Integer confirmationWaitBars = 12;

    @Column(name = "min_stop_distance_points")
    private Integer minStopDistancePoints = 100;

    @Column(name = "max_stop_distance_points")
    private Integer maxStopDistancePoints = 299;

    @Column(name = "morn_start_time")
    private LocalTime mornStartTime = LocalTime.of(10, 0);

    @Column(name = "morn_end_time")
    private LocalTime mornEndTime = LocalTime.of(14, 0);

    @Column(name = "eve_start_time")
    private LocalTime eveStartTime = LocalTime.of(15, 40);

    @Column(name = "eve_end_time")
    private LocalTime eveEndTime = LocalTime.of(18, 0);

    // 1. Обязательный пустой конструктор для JPA
    public TradingAccount() {
    }

    // Геттеры и Сеттеры

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMtLogin() {
        return mtLogin;
    }

    public void setMtLogin(String mtLogin) {
        this.mtLogin = mtLogin;
    }

    public String getMtPassword() {
        return mtPassword;
    }

    public void setMtPassword(String mtPassword) {
        this.mtPassword = mtPassword;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getHtimeframe() {
        return htimeframe;
    }

    public void setHtimeframe(String htimeframe) {
        this.htimeframe = htimeframe;
    }

    public String getLtimeframe() {
        return ltimeframe;
    }

    public void setLtimeframe(String ltimeframe) {
        this.ltimeframe = ltimeframe;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public Integer getLookbackCandles() {
        return lookbackCandles;
    }

    public void setLookbackCandles(Integer lookbackCandles) {
        this.lookbackCandles = lookbackCandles;
    }

    public BigDecimal getMaxDdPrc() {
        return maxDdPrc;
    }

    public void setMaxDdPrc(BigDecimal maxDdPrc) {
        this.maxDdPrc = maxDdPrc;
    }

    public Integer getMinFvgSize() {
        return minFvgSize;
    }

    public void setMinFvgSize(Integer minFvgSize) {
        this.minFvgSize = minFvgSize;
    }

    public Integer getCandlesToLive() {
        return candlesToLive;
    }

    public void setCandlesToLive(Integer candlesToLive) {
        this.candlesToLive = candlesToLive;
    }

    public Integer getStructureLookback() {
        return structureLookback;
    }

    public void setStructureLookback(Integer structureLookback) {
        this.structureLookback = structureLookback;
    }

    public Integer getConfirmationWaitBars() {
        return confirmationWaitBars;
    }

    public void setConfirmationWaitBars(Integer confirmationWaitBars) {
        this.confirmationWaitBars = confirmationWaitBars;
    }

    public Integer getMinStopDistancePoints() {
        return minStopDistancePoints;
    }

    public void setMinStopDistancePoints(Integer minStopDistancePoints) {
        this.minStopDistancePoints = minStopDistancePoints;
    }

    public Integer getMaxStopDistancePoints() {
        return maxStopDistancePoints;
    }

    public void setMaxStopDistancePoints(Integer maxStopDistancePoints) {
        this.maxStopDistancePoints = maxStopDistancePoints;
    }

    public LocalTime getMornStartTime() {
        return mornStartTime;
    }

    public void setMornStartTime(LocalTime mornStartTime) {
        this.mornStartTime = mornStartTime;
    }

    public LocalTime getMornEndTime() {
        return mornEndTime;
    }

    public void setMornEndTime(LocalTime mornEndTime) {
        this.mornEndTime = mornEndTime;
    }

    public LocalTime getEveStartTime() {
        return eveStartTime;
    }

    public void setEveStartTime(LocalTime eveStartTime) {
        this.eveStartTime = eveStartTime;
    }

    public LocalTime getEveEndTime() {
        return eveEndTime;
    }

    public void setEveEndTime(LocalTime eveEndTime) {
        this.eveEndTime = eveEndTime;
    }
}