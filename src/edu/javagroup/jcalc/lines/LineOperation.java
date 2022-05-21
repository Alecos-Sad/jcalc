package edu.javagroup.jcalc.lines;

import edu.javagroup.jcalc.digits.Addition;
import edu.javagroup.jcalc.digits.Division;
import edu.javagroup.jcalc.digits.Multiplication;
import edu.javagroup.jcalc.digits.Subtraction;

public class LineOperation {
    //work
    private static String removeLastSymbol(String source) {
        return source.substring(0, source.length() - 1);
    }

    //work
    private static String addMinusPrefix(String source) {
        return "-" + source;
    }

    //work
    private static String concatLines(String sourceOne, String sourceTwo) {
        return sourceOne + sourceTwo;
    }

    //FALSE
    private static String getResult(String source) {
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

    //false
    private static String getResultWithRoundBrackets(String source) {
        while (source.indexOf("(") >= 0) {
            if (LineParsing.isFinalNumber(source)) {
                return source;
            }
            int openBracketIndex = source.lastIndexOf("(");
            int closeBracketIndex = source.indexOf(")", openBracketIndex);
            String result = source.substring(openBracketIndex + 1, closeBracketIndex);
            result = LinePreparing.linePreparing(result);
            result = getResultWithoutRoundBrackets(result);
            if (result.isEmpty()) {
                break;
            }
            source = collectLines(source, result, openBracketIndex, closeBracketIndex);
        }
        return getResultWithoutRoundBrackets(source);
    }

    //false
    private static String getResultWithoutRoundBrackets(String source) {
        String whileContains = "/*+-";
        for (int i = 0; i < source.length(); i++) {
            String symbol = Character.toString(source.charAt(i));
            if (whileContains.contains(symbol)) {
                if (LineParsing.isFinalNumber(source)) {
                    return source;
                }
                source = LineParsing.findFirstMathSymbol(source);
                if (source.isEmpty()) {
                    return "";
                }
                int symbolIndex = source.indexOf(symbol, i);
                String result = "";
                switch (symbol) {
                    case "*":
                        result = multiplication(source, symbolIndex);
                        break;
                    case "/":
                        result = division(source, symbolIndex);
                        break;
                    case "+":
                        result = addition(source, symbolIndex);
                        break;
                    case "-":
                        result = subtraction(source, symbolIndex);
                }
                source = collectLines(source, result, symbolIndex);
                source = source.replace("--", "+");
                if (source.contains(".")) {
                    int pointIndex = source.indexOf(".");
                    String s = source.substring(pointIndex + 1);
                    int zero = Integer.parseInt(s);
                    source = zero == 0 ? source.substring(0, pointIndex) : source;
                }
            }
            return source;
        }
        return source;
    }

    //work
    private static String multiplication(String source, int symbolIndex) {
        return Multiplication.multiplication(LineParsing.getNumberFromLeftPart(source, symbolIndex),
                LineParsing.getNumberFromRightPart(source, symbolIndex));
    }

    //work
    private static String division(String source, int symbolIndex) {
        return Division.division(LineParsing.getNumberFromLeftPart(source, symbolIndex),
                LineParsing.getNumberFromRightPart(source, symbolIndex));
    }


    //work
    private static String addition(String source, int symbolIndex) {
        return Addition.addition(LineParsing.getNumberFromLeftPart(source, symbolIndex),
                LineParsing.getNumberFromRightPart(source, symbolIndex));
    }

    //work
    private static String subtraction(String source, int symbolIndex) {
        return Subtraction.subtraction(LineParsing.getNumberFromLeftPart(source, symbolIndex),
                LineParsing.getNumberFromRightPart(source, symbolIndex));
    }

    //work
    private static String collectLines(String source, String result, int symbolIndex) {
        return source.substring(0, source.indexOf(LineParsing.getNumberFromLeftPart(source, symbolIndex))) + result +
                source.substring(source.indexOf(LineParsing.getNumberFromRightPart(source, symbolIndex)) + 1);
    }

    //work
    private static String collectLines(String str, String result, int openBracketIndex, int closeBracketIndex) {
        return str.substring(0, openBracketIndex) + result + str.substring(closeBracketIndex + 1);
    }
}
