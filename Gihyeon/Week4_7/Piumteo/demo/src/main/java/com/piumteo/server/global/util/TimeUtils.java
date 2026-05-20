package com.piumteo.server.global.util;

import java.time.Instant;

public final class TimeUtils {

    private TimeUtils() {
    }

    public static long currentHourKey() {
        return Instant.now().getEpochSecond() / 3600;
    }
}