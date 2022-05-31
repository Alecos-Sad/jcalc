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

    public static String getNumberFromLeftPart(String source, int position) {

        StringBuilder result = new StringBuilder();
        for (int i = position - 1; i >= 0; i--) {
            if (source.charAt(i) == ')') {
                continue;
            }
            if (Character.isDigit(source.charAt(i)) || source.charAt(i) == '.' ||
                    source.charAt(i) == '-') {
                result.append(source.charAt(i));
            } else {
                break;
            }
        }
        return result.reverse().toString();
    }


    public static String getNumberFromRightPart(String str, int position) {

        StringBuilder result = new StringBuilder();
        for (int i = position + 1; i <= str.length() - 1; i++) {
            if (str.charAt(i) == ')' || str.charAt(i) == '(') {
                continue;
            }
            if (Character.isDigit(str.charAt(i)) || str.charAt(i) == '.' ||
                    str.charAt(i) == '-') {
                result.append(str.charAt(i));
            } else {
                break;
            }
        }
        return result.toString();
    }

    public static int getInteger(String str) {
        return Integer.parseInt(str);
    }

    public static double getDouble(String str) {
        return Double.parseDouble(str.trim());
    }

    public static boolean isFinalNumber(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (!(Character.isDigit(string.charAt(i)) || !(string.charAt(i) == '-') ||
                    !(string.charAt(i) == '.'))) {
                return false;
            }
            if (string.startsWith("-")) {
                string = string.substring(1);

            }
            if ((string.startsWith(".")) || string.endsWith(".")) {
                return false;
            }
            int count = 0;
            for (i = 0; i < string.length(); i++) {
                if (string.charAt(i) == '.') {
                    count = count + 1;
                    if (count > 1) {
                        break;
                    }
                }
            }
        }
        return true;
    }
}




