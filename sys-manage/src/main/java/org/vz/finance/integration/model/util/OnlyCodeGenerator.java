package org.vz.finance.integration.model.util;


public class OnlyCodeGenerator {

    private static final IdWorker idworker = new IdWorker(6L, 8L, 8L);

    public OnlyCodeGenerator() {

    }

    public static String distriId() {
        return String.valueOf(idworker.nextId());
    }

    public static String sessionSecuretKey() {
        return String.valueOf(System.currentTimeMillis());
    }
}
