package edu.javagroup.jcalc.lines;

public class LineCheck {

    public static boolean isLineCorrect(String source) {
        return isRoundBracketsCorrect(source) && isMathSymbolsCorrect(source);
    }

    public static boolean isRoundBracketsCorrect(String source) {
        return !source.contains("(") && !source.contains(")") || isRoundBracketsCountCorrect(source);
    }

    public static boolean isRoundBracketsCountCorrect(String source) {
        int openBracket = 0;
        int closeBracket = 0;
        for (int i = 0; i < source.length(); i++) {
            openBracket = source.charAt(i) == '(' ? ++openBracket : openBracket;
            closeBracket = source.charAt(i) == ')' ? ++closeBracket : closeBracket;
        }
        return openBracket == closeBracket &&  isRoundBracketsPositionsCorrect(source);
    }

    public static boolean  isRoundBracketsPositionsCorrect(String source) {
        return !(source.indexOf(")") < source.indexOf("(")) || !(source.lastIndexOf("(") > source.lastIndexOf(")"));

    }

    public static boolean isMathSymbolsCorrect(String source) {
        return isFirstMathSymbol(source, "*") && isFirstMathSymbol(source, "/") && isLastMathSymbol(source, "*") &&
                isLastMathSymbol(source, "/") && isFirstMathSymbol(source, "+") && isLastMathSymbol(source, "+") &&
                isLastMathSymbol(source, "-");
    }

    public static boolean isFirstMathSymbol(String source, String symbol) {
        return !(source.trim().startsWith(symbol));
    }

    public static boolean isLastMathSymbol(String source, String symbol) {
        return !(source.trim().endsWith(symbol));
    }


}
