package com.alejandro.studybooster.preference.entity;

public enum TimerInterval {
    MIN_1(60_000),
    MIN_15(15 * 60_000),
    MIN_30(30 * 60_000),
    MIN_45(45 * 60_000),
    MIN_60(60 * 60_000);

    private final long milliseconds;

    TimerInterval(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public long getMilliseconds() {
        return milliseconds;
    }
}
