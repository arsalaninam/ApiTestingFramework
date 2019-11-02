package com.freenow.util;

import org.testng.asserts.SoftAssert;

import java.util.Objects;

/**
 * Singleton objects class
 */
public class ObjectFactory {

    private static SoftAssert softAssert;

    private ObjectFactory() {
    }

    public static SoftAssert getSoftAssert() {
        if (Objects.isNull(softAssert)) {
            softAssert = new SoftAssert();
        }
        return softAssert;
    }
}
