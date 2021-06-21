package com.calculator.app_utils;

import java.util.Arrays;

public final class AppUtils {

    private AppUtils() {
    }

    public static boolean arrayContainsValue(String[] array, String string) {
        return Arrays.asList(array).contains(string);
    }
}
