package io.library.signaturegenerator.utils;

public class StringUtils {
    private StringUtils() {
        throw new AssertionError();
    }

    /**
     * This method is used to check that given param is not blank
     * (and is not null)
     * @param text This is the parameter to check
     * @return boolean result of checking
     */
    public static boolean isNotBlank(String text) {
        return text != null && text.trim().length() > 0;
    }
}
