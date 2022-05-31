package edu.javagroup.jcalc.lines;

import edu.javagroup.jcalc.digits.Addition;
import edu.javagroup.jcalc.digits.Division;
import edu.javagroup.jcalc.digits.Multiplication;
import edu.javagroup.jcalc.digits.Subtraction;

public class LineOperation {

    public static String removeLastSymbol(String source) {
        return source.substring(0, source.length() - 1);
    }

    public static String addMinusPrefix(String source) {
        return "-" + source;
    }

    public static String concatLines(String sourceOne, String sourceTwo) {
        return sourceOne + sourceTwo;
    }

    public static String getResult(String source) {
        if (source == null && source.isEmpty()) {
            return "";
        } else {
            source = LinePreparing.linePreparing(source);
            if (LineCheck.isLineCorrect(source)) {
                if (LineParsing.isFinalNumber(source)) {
                    return source;
                }
                return (source.contains("(") || source.contains(")")) ?
                        getResultWithRoundBrackets(source) :
                        getResultWithoutRoundBrackets(source);
            }
            return source;
        }
    }

    public static String getResultWithRoundBrackets(String source) {
        while (source.contains("(") || source.contains(")")) {
            if (LineParsing.isFinalNumber(source)) {
                return source;
            }
            int openBracketIndex = source.lastIndexOf("(");
            int closeBracketIndex = source.indexOf(")", openBracketIndex);
            String stringInBrackets = source.substring(openBracketIndex + 1, closeBracketIndex);
            stringInBrackets = LinePreparing.linePreparing(stringInBrackets);
            stringInBrackets = getResultWithoutRoundBrackets(stringInBrackets);
            if (stringInBrackets.isEmpty()) {
                break;

            }
            source = collectLines(source, stringInBrackets, openBracketIndex, closeBracketIndex);
        }
        return getResultWithoutRoundBrackets(source);
    }

    public static String getResultWithoutRoundBrackets(String stringWithoutBrackets) {
        while (stringWithoutBrackets.contains("/") || stringWithoutBrackets.contains("-") ||
                stringWithoutBrackets.contains("*") || (stringWithoutBrackets.contains("+"))) {

            if (LineParsing.isFinalNumber(stringWithoutBrackets)) {
                return stringWithoutBrackets;
            }
            String symbol = LineParsing.findFirstMathSymbol(stringWithoutBrackets);
            if (symbol.isEmpty()) {
                return "";
            }
            stringWithoutBrackets = stringWithoutBrackets.startsWith("-") ?
                    stringWithoutBrackets.substring(1) : stringWithoutBrackets;
            int symbolIndex = stringWithoutBrackets.indexOf(symbol);
            String result = "";

            switch (symbol) {
                case "*":
                    result = multiplication(stringWithoutBrackets, symbolIndex);
                    break;
                case "/":
                    result = division(stringWithoutBrackets, symbolIndex);
                    break;
                case "+":
                    result = addition(stringWithoutBrackets, symbolIndex);
                    break;
                case "-":
                    result = subtraction(stringWithoutBrackets, symbolIndex);
            }
            stringWithoutBrackets = collectLines(stringWithoutBrackets, result, symbolIndex);
            stringWithoutBrackets = stringWithoutBrackets.contains("--") ?
                    stringWithoutBrackets.replace("--", "+") : stringWithoutBrackets;
            if (stringWithoutBrackets.contains(".")) {
                int pointIndex = stringWithoutBrackets.indexOf(".");
                String s = stringWithoutBrackets.substring(pointIndex + 1);
                int zero = Integer.parseInt(s);
                stringWithoutBrackets = zero == 0 ?
                        stringWithoutBrackets.substring(0, pointIndex) : stringWithoutBrackets;
            }
        }
        return stringWithoutBrackets;
    }


    public static String multiplication(String source, int symbolIndex) {
        return Multiplication.multiplication(LineParsing.getNumberFromLeftPart(source, symbolIndex),
                LineParsing.getNumberFromRightPart(source, symbolIndex));
    }

    public static String division(String source, int symbolIndex) {
        return Division.division(LineParsing.getNumberFromLeftPart(source, symbolIndex),
                LineParsing.getNumberFromRightPart(source, symbolIndex));
    }

    public static String addition(String source, int symbolIndex) {
        return Addition.addition(LineParsing.getNumberFromLeftPart(source, symbolIndex),
                LineParsing.getNumberFromRightPart(source, symbolIndex));
    }

    public static String subtraction(String source, int symbolIndex) {
        return Subtraction.subtraction(LineParsing.getNumberFromLeftPart(source, symbolIndex),
                LineParsing.getNumberFromRightPart(source, symbolIndex));
    }

    public static String collectLines(String sourceWithoutBrackets, String result, int symbolIndex) {
        StringBuilder stringBuilder = new StringBuilder(sourceWithoutBrackets);
        int leftCount = 0;
        int rightCount = 0;
        if (sourceWithoutBrackets.contains(".")) {

            for (int i = symbolIndex - 1; i >= 0; i--) {
                if (Character.isDigit(sourceWithoutBrackets.charAt(i)) ||
                        sourceWithoutBrackets.charAt(i) == '.') {
                    leftCount++;
                }
            }
            for (int i = symbolIndex + 1; i < sourceWithoutBrackets.length(); i++) {
                if (Character.isDigit(sourceWithoutBrackets.charAt(i)) ||
                        sourceWithoutBrackets.charAt(i) == '.') {
                    rightCount++;
                }
            }
            return stringBuilder.replace(symbolIndex - leftCount, symbolIndex + rightCount + 1, result).toString();
        }
        return stringBuilder.replace(symbolIndex - 1, symbolIndex + 2, result).toString();
    }

    public static String collectLines(String source, String result, int openBracketIndex, int closeBracketIndex) {
        StringBuilder stringBuilder = new StringBuilder(source);
        stringBuilder.replace(openBracketIndex, closeBracketIndex + 1, result);
        return stringBuilder.toString();
    }
}
