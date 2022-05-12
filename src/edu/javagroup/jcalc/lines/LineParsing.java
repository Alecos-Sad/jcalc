package edu.javagroup.jcalc.lines;

public class LineParsing {

    public static String findFirstMathSymbol(String str) {

        if (str.startsWith("-")) {
            str = str.substring(1);
        }

        if ((str.contains("*")) || (str.contains("/"))) {
            return findFirstMathSymbol(str, 1);
        }
        if ((str.contains("+")) || (str.contains("-"))) {
            return findFirstMathSymbol(str, 2);

        }
        return "";
    }

    public static String findFirstMathSymbol(String string, int priority) {
        if (priority == 1) {
            for (int i = 0; i < string.length(); i++) {
                if ((string.charAt(i) == '/') || (string.charAt(i) == '*')) {
                    return string.charAt(i) + "";
                }
            }
        }
        if (priority == 2) {
            for (int i = 0; i < string.length(); i++) {
                if ((string.charAt(i) == '+') || (string.charAt(i) == '-')) {
                    return string.charAt(i) + "";
                }
            }
        }
        return "";
    }


    public String getNumberFromLeftPart(String str, int coordinate) {
        return "";
    }

    public String getNumberFromRightPart(String str, int coordinate) {
        return "";
    }

    public static int getInteger(String str) {
        return Integer.parseInt(str);
    }

    public static double getDouble(String str) {
        return Double.parseDouble(str);
    }

    public static boolean isFinalNumber(String str) {
        return false;
    }


}
