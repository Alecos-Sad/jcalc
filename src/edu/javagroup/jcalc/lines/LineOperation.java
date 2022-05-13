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

    private static String getResult(String source) {
        if (source == null || source.isEmpty()) {
            return "";
        } else {
            source = LinePreparing.linePreparing(source);
            if (LineCheck.isLineCorrect(source)) {
                if (LineParsing.isFinalNumber(source)) {
                    return source;
                }
                return source = (source.contains("(") || source.contains(")")) ?
                        getResultWithRoundBrackets(source) :
                        getResultWithoutRoundBrackets(source);
            }
            return source;
        }
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
