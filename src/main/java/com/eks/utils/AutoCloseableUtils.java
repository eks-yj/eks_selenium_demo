package com.eks.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AutoCloseableUtils {
    public static void close(AutoCloseable autoCloseable){
        try {
            if (autoCloseable != null){
                autoCloseable.close();
            }
        } catch (Exception e) {
            log.error("Exception:{}", e);
        }
    }
}
