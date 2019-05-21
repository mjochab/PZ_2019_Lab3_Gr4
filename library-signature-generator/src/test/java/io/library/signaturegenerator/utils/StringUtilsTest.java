package io.library.signaturegenerator.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void isNotBlank() {
        assertTrue(StringUtils.isNotBlank("text"));
    }

    @Test
    void isBlank() {
        assertFalse(StringUtils.isNotBlank(null));
    }
}