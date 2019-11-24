package com.tongji.meeting.model;

import java.sql.Timestamp;

public class EventRepetition {
    private int detailId;
    private int repeatUnit;
    private int repeatAmount;
    private Timestamp until;

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getRepeatUnit() {
        return repeatUnit;
    }

    public void setRepeatUnit(int repeatUnit) {
        this.repeatUnit = repeatUnit;
    }

    public int getRepeatAmount() {
        return repeatAmount;
    }

    public void setRepeatAmount(int repeatAmount) {
        this.repeatAmount = repeatAmount;
    }

    public Timestamp getUntil() {
        return until;
    }

    public void setUntil(Timestamp until) {
        this.until = until;
    }
}
