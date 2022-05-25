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

    public static String getNumberFromLeftPart(String string, int position) {

        string = string.substring(0, position);

        if (string.contains(")")) {
            for (int i = string.length() - 1; i >= 0; i--) {
                if (string.charAt(i) == ')') {
                    string = string.substring(0, i + 1);
                    break;
                }
            }
        }
        if (string.contains("("))
            for (int j = 0; j < string.length(); j++) {
                if (string.charAt(j) == '(') {
                    string = string.substring(j + 1, string.length() - 1);
                    return string;
                }
            }
        for (int j = string.length() - 1; j > 0; j--) {
            if ((string.charAt(j) == '-') || (string.charAt(j) == '/') ||
                    (string.charAt(j) == '+') || (string.charAt(j) == '*')) {
                string = string.substring(j + 1);
                return string;
            }
        }
        return string;
    }

    public static String getNumberFromRightPart(String string, int position) {

        string = string.substring(position + 1);

        while (string.contains("(")) {
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == '(') {
                    string = string.substring(i + 1);
                    break;
                }
            }
        }
        if (string.contains(")"))
            for (int j = 0; j < string.length(); j++) {
                if (string.charAt(j) == ')') {
                    string = string.substring(0, j);
                    return string;
                }
            }
        for (int j = 0; j < string.length() - 1; j++) {
            if ((string.charAt(j) == '-') || (string.charAt(j) == '/') ||
                    (string.charAt(j) == '+') || (string.charAt(j) == '*')) {

                string = string.substring(0, j);

                return string;
            }
        }
        return string;
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




