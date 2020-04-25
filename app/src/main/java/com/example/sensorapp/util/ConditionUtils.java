package com.example.sensorapp.util;

import android.os.Build;

import java.util.Objects;

public class ConditionUtils {

    private ConditionUtils() {
    }

    public static <T> T requireNonNull(T obj) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return Objects.requireNonNull(obj);
        } else if (obj == null){
            throw new NullPointerException();
        }
        return obj;
    }
}
