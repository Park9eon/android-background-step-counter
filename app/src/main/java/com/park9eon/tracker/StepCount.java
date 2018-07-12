package com.park9eon.tracker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class StepCount {

    public static final DateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.KOREA);

    private Long id;
    private Long step;
    private Date timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStep() {
        return step;
    }

    public void setStep(Long step) {
        this.step = step;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "StepCount{" +
                "id=" + id +
                ", step=" + step +
                ", timestamp=" + timestampFormat.format(timestamp) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StepCount stepCount = (StepCount) o;
        return Objects.equals(id, stepCount.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
