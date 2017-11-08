package com.manganimelist.libraries;

/**
 * Generate random character with range, lowercase, uppercase and digit
 * characters.
 *
 * @author jeprox
 */
public class RandChar {

    /**
     * Generate a random character between ch1 and ch2.
     *
     * @param ch1 the starting character
     * @param ch2 the ending character
     * @return a random generated character
     */
    public static char getRandChar(char ch1, char ch2) {
        return (char) (ch1 + Math.random() * (ch2 - ch1 + 1));
    }

    /**
     * Get lower case random generated character.
     *
     * @return random generated lower case character
     */
    public static char getRandLowerCaseLetter() {
        return getRandChar('a', 'z');
    }

    /**
     * Get upper case random generated character.
     *
     * @return random generated upper case character
     */
    public static char getRandUpperCaseLetter() {
        return getRandChar('A', 'Z');
    }

    /**
     * Get random generated digit character.
     *
     * @return random generated digit character
     */
    public static char getRandDigitChar() {
        return getRandChar('0', '9');
    }

    /**
     * Get random generated character using Unicode format.
     *
     * @return random generated character
     */
    public static char getRandChar() {
        return getRandChar('\u0000', '\uFFFF');
    }

    /**
     * Get random generated code with the combination of digits, lower case and
     * upper case letters.
     *
     * @param numChars the max number of characters
     * @return random generated string
     */
    public static String getRandGenCode(int numChars) {

        StringBuilder buildCode = new StringBuilder();
        for (int i = 0; i < numChars; i++) {
            // Get character range from 0-9, A-Z and a-z
            char ch = getRandChar('0', 'z');

            /**
             * Check if the character is a letter or digit if true combine the
             * character to new string otherwise decrease the loop count. Need a
             * checker here because special characters are also included in the
             * generated character.
             */
            if (Character.isLetter(ch) || Character.isDigit(ch)) {
                buildCode.append(ch);
            } else {
                i--;
            }

        }
        return buildCode.toString();
    }
}
