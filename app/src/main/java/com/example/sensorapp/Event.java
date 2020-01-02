package com.example.sensorapp;

import androidx.annotation.Nullable;

public class Event<T> {
    private T content;
    private boolean hasBeenHandled = false;

    public Event(@Nullable T content) {
        if (content == null) {
            throw new IllegalArgumentException("null values in Event are not allowed.");
        }
        this.content = content;
    }

    @Nullable
    public T getContentIfNotHandled() {
        if (hasBeenHandled) {
            return null;
        } else {
            hasBeenHandled = true;
            return content;
        }
    }

    public boolean hasBeenHandled() {
        return hasBeenHandled;
    }
}
