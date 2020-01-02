package com.example.sensorapp.util;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ActivityUtils {

    private ActivityUtils() {

    }

    public static void replaceFragmentInActivity(@NonNull FragmentManager manager,
                                                 @NonNull Fragment fragment, @IdRes int frameId) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
