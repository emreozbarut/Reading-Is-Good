package com.readingisgood.utils;

public final class SkuUtils {

    private SkuUtils() {}

    public static boolean isStockNotEnough(Integer stock, Integer quantity) {
        return stock < quantity;
    }
}
