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
        return source;
    }

    //false
    private static String getResultWithoutRoundBrackets(String str) {

        String symbol = LineParsing.findFirstMathSymbol(str);
        int pos = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.contains("(")) {
                pos = i;
                break;
            }
            switch (String.valueOf(pos + 1)) {
                case "+":
                    return addition(str, pos);
                case "-":
                    return subtraction(str, pos);
            }
        }


        return str;
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
