package edu.javagroup.jcalc.lines;

public class LineOperation {

    private static String removeLastSymbol(String source) {
        return source.substring(0, source.length() - 1);
    }

    private static String addMinusPrefix(String source) {
        return "-" + source;
    }

    private static String concatLines(String sourceOne, String sourceTwo) {
        return sourceOne.concat(sourceTwo);
    }

    public static String getResult(String str) {
        return "";
    }

    public static String getResultWithRoundBrackets(String str) {
        return "";
    }

    public static String getResultWithoutRoundBrackets(String str) {
        return "";
    }

    public static String multiplication(String str, int coordinate) {
        return "";
    }

    public static String division(String str, int coordinate) {
        return "";
    }

    public static String addition(String str, int coordinate) {
        return "";
    }

    public static String subtraction(String str, int coordinate) {
        return "";
    }

    public static String collectLines(String str, String str1, int coordinates) {
        return "";
    }

    public static String collectLines(String str, String str1, int coordinates, int coordinates1) {
        return "";
    }

}
