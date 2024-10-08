package com.github.hannotify.patternmatching.musicstore.effects;

import java.util.Objects;

public final class Delay implements Effect {
    private int timeInMs;

    public Delay(int timeInMs) {
        setTimeInMs(timeInMs);
    }

    @Override
    public String description() {
        return String.format("Delay / timeInMs=%dms", timeInMs);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Delay)) {
            return false;
        }

        Delay delay = (Delay) o;
        return timeInMs == delay.timeInMs;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeInMs);
    }

    @Override
    public String toString() {
        return description();
    }

    public void setTimeInMs(int timeInMs) {
        this.timeInMs = timeInMs;
    }

    public int getTimeInMs() {
        return timeInMs;
    }
}
