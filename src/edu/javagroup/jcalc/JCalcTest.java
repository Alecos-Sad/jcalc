package edu.javagroup.jcalc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author kaa
 * @version 2.0
 */
public class JCalcTest {

    public static final Map<String, Class> CLASS_MAP;

    static {

        Map<String, Class> map = new LinkedHashMap<>();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;

        try {
            Class roundClass = classLoader.loadClass("edu.javagroup.jcalc.digits.Round");
            if (roundClass != null) {
                map.put("0", roundClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class additionClass = classLoader.loadClass("edu.javagroup.jcalc.digits.Addition");
            if (additionClass != null) {
                map.put("1", additionClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class divisionClass = classLoader.loadClass("edu.javagroup.jcalc.digits.Division");
            if (divisionClass != null) {
                map.put("2", divisionClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class multiplicationClass = classLoader.loadClass("edu.javagroup.jcalc.digits.Multiplication");
            if (multiplicationClass != null) {
                map.put("3", multiplicationClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class subtractionClass = classLoader.loadClass("edu.javagroup.jcalc.digits.Subtraction");
            if (subtractionClass != null) {
                map.put("4", subtractionClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class lineCheckClass = classLoader.loadClass("edu.javagroup.jcalc.lines.LineCheck");
            if (lineCheckClass != null) {
                map.put("5", lineCheckClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class lineOperationClass = classLoader.loadClass("edu.javagroup.jcalc.lines.LineOperation");
            if (lineOperationClass != null) {
                map.put("6", lineOperationClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class lineParsingClass = classLoader.loadClass("edu.javagroup.jcalc.lines.LineParsing");
            if (lineParsingClass != null) {
                map.put("7", lineParsingClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        try {
            Class linePreparingClass = classLoader.loadClass("edu.javagroup.jcalc.lines.LinePreparing");
            if (linePreparingClass != null) {
                map.put("8", linePreparingClass);
            }
        } catch (ClassNotFoundException exception) {
            //exception.printStackTrace();
        }
        CLASS_MAP = Collections.unmodifiableMap(map);
    }

    //------------------------------------------------------------------------------------------------------------------

    public void startTest() {
        if (!CLASS_MAP.isEmpty()) {
            System.out.println("--- Выбери класс ---");
            for (Map.Entry<String, Class> item : CLASS_MAP.entrySet()) {
                System.out.println(item.getKey() + " - " + item.getValue().getSimpleName());
            }
            System.out.print("твой выбор: ");
            String classMenuItem = getMenuItem();
            Class testClass = CLASS_MAP.getOrDefault(classMenuItem, null);
            if (testClass == null) {
                System.out.println("Не найден искомый класс, наверное не расскоментирован в JCalcTest или ошибка выбора");
            } else {
                testClass(testClass);
            }
        } else {
            System.out.println("Ни один класс для тестов не найден");
        }
    }

    private String getMenuItem() {
        try {
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return "";
    }

    private void testClass(Class testClass) {
        switch (testClass.getSimpleName()) {
            case "Round":
                testRound(testClass);
                break;
            case "Addition":
                testAddition(testClass);
                break;
            case "Division":
                testDivision(testClass);
                break;
            case "Multiplication":
                testMultiplication(testClass);
                break;
            case "Subtraction":
                testSubtraction(testClass);
                break;
            case "LineCheck":
                testLineCheck(testClass);
                break;
            case "LineOperation":
                testLineOperation(testClass);
                break;
            case "LineParsing":
                testLineParsing(testClass);
                break;
            case "LinePreparing":
                testLinePreparing(testClass);
                break;
            default:
                System.out.println("Не найден искомый класс для теста методов");
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    private void testRound(Class testClass) {
        System.out.println("--- " + testClass.getSimpleName() + " ---");

        Double result1 = (Double) testMethod(testClass, "round", new Object[]{1.10601});
        System.out.println("Метод: round - отправлено: 1.10601, получено: " + result1 + " => " + (result1 != null && result1 == 1.11 ? "УСПЕХ" : "ПРОВАЛ"));
    }

    private void testAddition(Class testClass) {
        System.out.println("--- " + testClass.getSimpleName() + " ---");

        String result1 = (String) testMethod(testClass, "addition", new Class[]{String.class, String.class}, new Object[]{"3", "2"});
        System.out.println("Метод: addition - отправлено: 3 + 2, получено: " + result1 + " => " + ("5".equals(result1) ? "УСПЕХ" : "ПРОВАЛ"));

        String result2 = (String) testMethod(testClass, "addition", new Class[]{String.class, String.class}, new Object[]{"3", "2.34"});
        System.out.println("Метод: addition - отправлено: 3 + 2.34, получено + " + result2 + " => " + ("5.34".equals(result2) ? "УСПЕХ" : "ПРОВАЛ"));

        String result3 = (String) testMethod(testClass, "addition", new Class[]{String.class, String.class}, new Object[]{"3.34", "2"});
        System.out.println("Метод: addition - отправлено: 3.34 + 2, получено + " + result3 + " => " + ("5.34".equals(result3) ? "УСПЕХ" : "ПРОВАЛ"));

        String result4 = (String) testMethod(testClass, "addition", new Class[]{String.class, String.class}, new Object[]{"3.34", "2.11"});
        System.out.println("Метод: addition - отправлено: 3.34 + 2.11, получено + " + result4 + " => " + ("5.45".equals(result4) ? "УСПЕХ" : "ПРОВАЛ"));
    }

    private void testDivision(Class testClass) {
        System.out.println("--- " + testClass.getSimpleName() + " ---");

        String result1 = (String) testMethod(testClass, "division", new Class[]{String.class, String.class}, new Object[]{"5", "3"});
        System.out.println("Метод: division - отправлено: 5 / 3, получено: " + result1 + " => " + ("1.67".equals(result1) ? "УСПЕХ" : "ПРОВАЛ"));

        String result2 = (String) testMethod(testClass, "division", new Class[]{String.class, String.class}, new Object[]{"2", "1.1"});
        System.out.println("Метод: division - отправлено: 2 / 1.1, получено + " + result2 + " => " + ("1.82".equals(result2) ? "УСПЕХ" : "ПРОВАЛ"));

        String result3 = (String) testMethod(testClass, "division", new Class[]{String.class, String.class}, new Object[]{"2.2", "3"});
        System.out.println("Метод: division - отправлено: 2.2 / 3, получено + " + result3 + " => " + ("0.73".equals(result3) ? "УСПЕХ" : "ПРОВАЛ"));

        String result4 = (String) testMethod(testClass, "division", new Class[]{String.class, String.class}, new Object[]{"54.22", "18.02"});
        System.out.println("Метод: division - отправлено: 54.22 / 18.02, получено + " + result4 + " => " + ("3.01".equals(result4) ? "УСПЕХ" : "ПРОВАЛ"));
    }

    private void testMultiplication(Class testClass) {
        System.out.println("--- " + testClass.getSimpleName() + " ---");

        String result1 = (String) testMethod(testClass, "multiplication", new Class[]{String.class, String.class}, new Object[]{"5", "3"});
        System.out.println("Метод: multiplication - отправлено: 5 * 3, получено: " + result1 + " => " + ("15".equals(result1) ? "УСПЕХ" : "ПРОВАЛ"));

        String result2 = (String) testMethod(testClass, "multiplication", new Class[]{String.class, String.class}, new Object[]{"2", "1.1"});
        System.out.println("Метод: multiplication - отправлено: 2 * 1.1, получено + " + result2 + " => " + ("2.2".equals(result2) ? "УСПЕХ" : "ПРОВАЛ"));

        String result3 = (String) testMethod(testClass, "multiplication", new Class[]{String.class, String.class}, new Object[]{"2.2", "3"});
        System.out.println("Метод: multiplication - отправлено: 2.2 * 3, получено + " + result3 + " => " + ("6.6".equals(result3) ? "УСПЕХ" : "ПРОВАЛ"));

        String result4 = (String) testMethod(testClass, "multiplication", new Class[]{String.class, String.class}, new Object[]{"54.22", "18.02"});
        System.out.println("Метод: multiplication - отправлено: 54.22 * 18.02, получено + " + result4 + " => " + ("977.04".equals(result4) ? "УСПЕХ" : "ПРОВАЛ"));
    }

    private void testSubtraction(Class testClass) {
        System.out.println("--- " + testClass.getSimpleName() + " ---");

        String result1 = (String) testMethod(testClass, "subtraction", new Class[]{String.class, String.class}, new Object[]{"5", "3"});
        System.out.println("Метод: subtraction - отправлено: 5 - 3, получено: " + result1 + " => " + ("2".equals(result1) ? "УСПЕХ" : "ПРОВАЛ"));

        String result2 = (String) testMethod(testClass, "subtraction", new Class[]{String.class, String.class}, new Object[]{"2", "1.1"});
        System.out.println("Метод: subtraction - отправлено: 2 - 1.1, получено + " + result2 + " => " + ("0.9".equals(result2) ? "УСПЕХ" : "ПРОВАЛ"));

        String result3 = (String) testMethod(testClass, "subtraction", new Class[]{String.class, String.class}, new Object[]{"2.2", "3"});
        System.out.println("Метод: subtraction - отправлено: 2.2 - 3, получено + " + result3 + " => " + ("-0.8".equals(result3) ? "УСПЕХ" : "ПРОВАЛ"));

        String result4 = (String) testMethod(testClass, "subtraction", new Class[]{String.class, String.class}, new Object[]{"54.22", "18.02"});
        System.out.println("Метод: subtraction - отправлено: 54.22 - 18.02, получено + " + result4 + " => " + ("36.2".equals(result4) ? "УСПЕХ" : "ПРОВАЛ"));
    }

    private void testLineCheck(Class testClass) {
        Method[] methodArray = testClass.getDeclaredMethods();
        if (methodArray.length > 0) {
            System.out.println("--- Выбери метод ---");
            for (int i = 0; i < methodArray.length; i++) {
                System.out.println(i + " - " + methodArray[i].getName());
            }
            System.out.print("твой выбор: ");
            String menuItem = getMenuItem();
            int menuNumber = Integer.parseInt(menuItem);
            testLineCheck(testClass, methodArray[menuNumber].getName());
        } else {
            System.out.println("В классе " + testClass.getSimpleName() + ", методы не обнаружены");
            startTest();
        }
    }

    private void testLineCheck(Class testClass, String methodName) {
        System.out.println("--- " + testClass.getSimpleName() + " ---");
        switch (methodName) {
            case "isMathSymbolsCorrect":
                Boolean result1 = (Boolean) testMethod(testClass, "isMathSymbolsCorrect", new Object[]{"*1"});
                System.out.println("Метод: isMathSymbolsCorrect - отправлено: *1, получено: " + result1 + " => " + (result1 != null && !result1 ? "УСПЕХ" : "ПРОВАЛ"));
                Boolean result2 = (Boolean) testMethod(testClass, "isMathSymbolsCorrect", new Object[]{"1*"});
                System.out.println("Метод: isMathSymbolsCorrect - отправлено: 1*, получено: " + result2 + " => " + (result2 != null && !result2 ? "УСПЕХ" : "ПРОВАЛ"));
                Boolean result3 = (Boolean) testMethod(testClass, "isMathSymbolsCorrect", new Object[]{"/1"});
                System.out.println("Метод: isMathSymbolsCorrect - отправлено: /1, получено: " + result3 + " => " + (result3 != null && !result3 ? "УСПЕХ" : "ПРОВАЛ"));
                Boolean result4 = (Boolean) testMethod(testClass, "isMathSymbolsCorrect", new Object[]{"1/"});
                System.out.println("Метод: isMathSymbolsCorrect - отправлено: 1/, получено: " + result4 + " => " + (result4 != null && !result4 ? "УСПЕХ" : "ПРОВАЛ"));
                Boolean result5 = (Boolean) testMethod(testClass, "isMathSymbolsCorrect", new Object[]{"+1"});
                System.out.println("Метод: isMathSymbolsCorrect - отправлено: +1, получено: " + result5 + " => " + (result5 != null && !result5 ? "УСПЕХ" : "ПРОВАЛ"));
                Boolean result6 = (Boolean) testMethod(testClass, "isMathSymbolsCorrect", new Object[]{"1+"});
                System.out.println("Метод: isMathSymbolsCorrect - отправлено: 1+, получено: " + result6 + " => " + (result6 != null && !result6 ? "УСПЕХ" : "ПРОВАЛ"));
                Boolean result7 = (Boolean) testMethod(testClass, "isMathSymbolsCorrect", new Object[]{"-1"});
                System.out.println("Метод: isMathSymbolsCorrect - отправлено: -1, получено: " + result7 + " => " + (result7 != null && result7 ? "УСПЕХ" : "ПРОВАЛ"));
                Boolean result8 = (Boolean) testMethod(testClass, "isMathSymbolsCorrect", new Object[]{"1-"});
                System.out.println("Метод: isMathSymbolsCorrect - отправлено: 1-, получено: " + result8 + " => " + (result8 != null && !result8 ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            case "isRoundBracketsPositionsCorrect":
                Boolean result9 = (Boolean) testMethod(testClass, "isRoundBracketsPositionsCorrect", new Object[]{"1)2(3"});
                System.out.println("Метод: isRoundBracketsPositionsCorrect - отправлено: 1)2(3, получено: " + result9 + " => " + (result9 != null && !result9 ? "УСПЕХ" : "ПРОВАЛ"));
                Boolean result10 = (Boolean) testMethod(testClass, "isRoundBracketsPositionsCorrect", new Object[]{"1(2)3"});
                System.out.println("Метод: isRoundBracketsPositionsCorrect - отправлено: 1(2)3, получено: " + result10 + " => " + (result10 != null && result10 ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            case "isRoundBracketsCountCorrect":
                Boolean result11 = (Boolean) testMethod(testClass, "isRoundBracketsCountCorrect", new Object[]{"1(2)3)4"});
                System.out.println("Метод: isRoundBracketsCountCorrect - отправлено: 1(2)3)4, получено: " + result11 + " => " + (result11 != null && !result11 ? "УСПЕХ" : "ПРОВАЛ"));
                Boolean result12 = (Boolean) testMethod(testClass, "isRoundBracketsCountCorrect", new Object[]{"1(2(3)4)5"});
                System.out.println("Метод: isRoundBracketsCountCorrect - отправлено: 1(2(3)4)5, получено: " + result12 + " => " + (result12 != null && result12 ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            case "isRoundBracketsCorrect":
                Boolean result13 = (Boolean) testMethod(testClass, "isRoundBracketsCorrect", new Object[]{"1234"});
                System.out.println("Метод: isRoundBracketsCorrect - отправлено: 1234, получено: " + result13 + " => " + (result13 != null && result13 ? "УСПЕХ" : "ПРОВАЛ"));
                Boolean result14 = (Boolean) testMethod(testClass, "isRoundBracketsCorrect", new Object[]{"1(2(3)"});
                System.out.println("Метод: isRoundBracketsCorrect - отправлено: 1(2(3), получено: " + result14 + " => " + (result14 != null && !result14 ? "УСПЕХ" : "ПРОВАЛ"));
                Boolean result15 = (Boolean) testMethod(testClass, "isRoundBracketsCorrect", new Object[]{"1(2(3)4)5"});
                System.out.println("Метод: isRoundBracketsCorrect - отправлено: 1(2(3)4)5, получено: " + result15 + " => " + (result15 != null && result15 ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            case "isLineCorrect":
                Boolean result16 = (Boolean) testMethod(testClass, "isLineCorrect", new Object[]{"1234"});
                System.out.println("Метод: isLineCorrect - отправлено: 1234, получено: " + result16 + " => " + (result16 != null && result16 ? "УСПЕХ" : "ПРОВАЛ"));
                Boolean result17 = (Boolean) testMethod(testClass, "isLineCorrect", new Object[]{"1(2(3)"});
                System.out.println("Метод: isLineCorrect - отправлено: 1(2(3), получено: " + result17 + " => " + (result17 != null && !result17 ? "УСПЕХ" : "ПРОВАЛ"));
                Boolean result18 = (Boolean) testMethod(testClass, "isLineCorrect", new Object[]{"1(2(3)4)5"});
                System.out.println("Метод: isLineCorrect - отправлено: 1(2(3)4)5, получено: " + result18 + " => " + (result18 != null && result18 ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            default:
                System.out.println("Метод не обнаружен");
        }
    }

    private void testLineOperation(Class testClass) {
        Method[] methodArray = testClass.getDeclaredMethods();
        if (methodArray.length > 0) {
            System.out.println("--- Выбери метод ---");
            for (int i = 0; i < methodArray.length; i++) {
                System.out.println(i + " - " + methodArray[i].getName());
            }
            System.out.print("твой выбор: ");
            String menuItem = getMenuItem();
            int menuNumber = Integer.parseInt(menuItem);
            testLineOperation(testClass, methodArray[menuNumber].getName());
        } else {
            System.out.println("В классе " + testClass.getSimpleName() + ", методы не обнаружены");
            startTest();
        }
    }

    private void testLineOperation(Class testClass, String methodName) {
        System.out.println("--- " + testClass.getSimpleName() + " ---");
        switch (methodName) {
            case "collectLines":
                String result1 = (String) testMethod(testClass, "collectLines", new Object[]{"1+2+4", "3", 1});
                System.out.println("Метод: collectLines - отправлено: 1+2+4 и 3 и 1, получено: " + result1 + " => " + ("3+4".equals(result1) ? "УСПЕХ" : "ПРОВАЛ"));
                String result2 = (String) testMethod(testClass, "collectLines", new Object[]{"1+(2+3)+4", "5", 2, 6});
                System.out.println("Метод: collectLines - отправлено: 1+(2+3)+4 и 5 и 2 и 6, получено: " + result2 + " => " + ("1+5+4".equals(result2) ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            case "subtraction":
                String result3 = (String) testMethod(testClass, "subtraction", new Object[]{"1+2-3+4", 3});
                System.out.println("Метод: subtraction - отправлено: 1+2-3+4 и 3, получено: " + result3 + " => " + ("-1".equals(result3) ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            case "addition":
                String result4 = (String) testMethod(testClass, "addition", new Object[]{"1-2+3-4", 3});
                System.out.println("Метод: addition - отправлено: 1-2+3-4 и 3, получено: " + result4 + " => " + ("5".equals(result4) ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            case "division":
                String result5 = (String) testMethod(testClass, "division", new Object[]{"1-2/3-4", 3});
                System.out.println("Метод: division - отправлено: 1-2/3-4 и 3, получено: " + result5 + " => " + ("-0.67".equals(result5) ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            case "multiplication":
                String result6 = (String) testMethod(testClass, "multiplication", new Object[]{"1-2*3-4", 3});
                System.out.println("Метод: multiplication - отправлено: 1-2*3-4 и 3, получено: " + result6 + " => " + ("-6".equals(result6) ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            case "getResultWithoutRoundBrackets":
                String result7 = (String) testMethod(testClass, "getResultWithoutRoundBrackets", new Object[]{"1+4"});
                System.out.println("Метод: getResultWithoutRoundBrackets - отправлено: 1+4, получено: " + result7 + " => " + ("5".equals(result7) ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            case "getResultWithRoundBrackets":
                String result8 = (String) testMethod(testClass, "getResultWithRoundBrackets", new Object[]{"1+(2+3)+4"});
                System.out.println("Метод: getResultWithRoundBrackets - отправлено: 1+(2+3)+4, получено: " + result8 + " => " + ("10".equals(result8) ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            case "getResult":
                String result9 = (String) testMethod(testClass, "getResult", new Object[]{"1+1"});
                System.out.println("Метод: getResult - отправлено: 1+1, получено: " + result9 + " => " + ("2".equals(result9) ? "УСПЕХ" : "ПРОВАЛ"));
                String result10 = (String) testMethod(testClass, "getResult", new Object[]{"2+2*2"});
                System.out.println("Метод: getResult - отправлено: 2+2*2, получено: " + result10 + " => " + ("6".equals(result10) ? "УСПЕХ" : "ПРОВАЛ"));
                String result11 = (String) testMethod(testClass, "getResult", new Object[]{"(2+2)*2"});
                System.out.println("Метод: getResult - отправлено: (2+2)*2, получено: " + result11 + " => " + ("8".equals(result11) ? "УСПЕХ" : "ПРОВАЛ"));
                String result12 = (String) testMethod(testClass, "getResult", new Object[]{"-1-1"});
                System.out.println("Метод: getResult - отправлено: -1-1, получено: " + result12 + " => " + ("-2".equals(result12) ? "УСПЕХ" : "ПРОВАЛ"));
                String result13 = (String) testMethod(testClass, "getResult", new Object[]{"-1*2+3/4"});
                System.out.println("Метод: getResult - отправлено: -1*2+3/4, получено: " + result13 + " => " + ("-1.25".equals(result13) ? "УСПЕХ" : "ПРОВАЛ"));
                String result14 = (String) testMethod(testClass, "getResult", new Object[]{"-1*(2+3)/4"});
                System.out.println("Метод: getResult - отправлено: -1*(2+3)/4, получено: " + result14 + " => " + ("-1.25".equals(result14) ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            case "concatLines":
                String result15 = (String) testMethod(testClass, "concatLines", new Object[]{"1", "2"});
                System.out.println("Метод: concatLines - отправлено: 1, 2, получено: " + result15 + " => " + ("12".equals(result15) ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            case "addMinusPrefix":
                String result16 = (String) testMethod(testClass, "addMinusPrefix", new Object[]{"1"});
                System.out.println("Метод: addMinusPrefix - отправлено: 1, получено: " + result16 + " => " + ("-1".equals(result16) ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            case "removeLastSymbol":
                String result17 = (String) testMethod(testClass, "removeLastSymbol", new Object[]{"1+2+34"});
                System.out.println("Метод: removeLastSymbol - отправлено: 1+2+34, получено: " + result17 + " => " + ("1+2+3".equals(result17) ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            default:
                System.out.println("Метод не обнаружен");
        }
    }

    private void testLineParsing(Class testClass) {
        Method[] methodArray = testClass.getDeclaredMethods();
        if (methodArray.length > 0) {
            System.out.println("--- Выбери метод ---");
            for (int i = 0; i < methodArray.length; i++) {
                System.out.println(i + " - " + methodArray[i].getName());
            }
            System.out.print("твой выбор: ");
            String menuItem = getMenuItem();
            int menuNumber = Integer.parseInt(menuItem);
            testLineParsing(testClass, methodArray[menuNumber].getName());
        } else {
            System.out.println("В классе " + testClass.getSimpleName() + ", методы не обнаружены");
            startTest();
        }
    }

    private void testLineParsing(Class testClass, String methodName) {
        System.out.println("--- " + testClass.getSimpleName() + " ---");
        switch (methodName) {
            case "getNumberFromRightPart":
                String result1 = (String) testMethod(testClass, "getNumberFromRightPart", new Object[]{"1.2+3.4*5.6/7.8", 7});
                System.out.println("Метод: getNumberFromRightPart - отправлено: 1.2+3.4*5.6/7.8 и 7, получено: " + result1 + " => " + ("5.6".equals(result1) ? "УСПЕХ" : "ПРОВАЛ"));
                String result2 = (String) testMethod(testClass, "getNumberFromRightPart", new Object[]{"1.2+3.4*(-5.6)/7.8", 7});
                System.out.println("Метод: getNumberFromRightPart - отправлено: 1.2+3.4*(-5.6)/7.8 и 7, получено: " + result2 + " => " + ("-5.6".equals(result2) ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            case "getNumberFromLeftPart":
                String result3 = (String) testMethod(testClass, "getNumberFromLeftPart", new Object[]{"1.2+3.4*5.6/7.8", 7});
                System.out.println("Метод: getNumberFromLeftPart - отправлено: 1.2+3.4*5.6/7.8 и 7, получено: " + result3 + " => " + ("3.4".equals(result3) ? "УСПЕХ" : "ПРОВАЛ"));
                String result4 = (String) testMethod(testClass, "getNumberFromLeftPart", new Object[]{"1.2+(-3.4)*5.6/7.8", 10});
                System.out.println("Метод: getNumberFromLeftPart - отправлено: 1.2+(-3.4)*5.6/7.8 и 10, получено: " + result4 + " => " + ("-3.4".equals(result4) ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            case "findFirstMathSymbol":
                String result5 = (String) testMethod(testClass, "findFirstMathSymbol", new Object[]{"1.2+3.4*5.6/7.8", 1});
                System.out.println("Метод: findFirstMathSymbol - отправлено: 1.2+3.4*5.6/7.8 и 1, получено: " + result5 + " => " + ("*".equals(result5) ? "УСПЕХ" : "ПРОВАЛ"));
                String result6 = (String) testMethod(testClass, "findFirstMathSymbol", new Object[]{"1.2+3.4*5.6/7.8", 2});
                System.out.println("Метод: findFirstMathSymbol - отправлено: 1.2+3.4*5.6/7.8 и 2, получено: " + result6 + " => " + ("+".equals(result6) ? "УСПЕХ" : "ПРОВАЛ"));
                String result7 = (String) testMethod(testClass, "findFirstMathSymbol", new Object[]{"1.2+3.4*5.6/7.8"});
                System.out.println("Метод: findFirstMathSymbol - отправлено: 1.2+3.4*5.6/7.8, получено: " + result7 + " => " + ("*".equals(result7) ? "УСПЕХ" : "ПРОВАЛ"));
                String result8 = (String) testMethod(testClass, "findFirstMathSymbol", new Object[]{"1.2+3.4-5.6"});
                System.out.println("Метод: findFirstMathSymbol - отправлено: 1.2+3.4-5.6, получено: " + result8 + " => " + ("+".equals(result8) ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            case "isFinalNumber":
                Boolean result9 = (Boolean) testMethod(testClass, "isFinalNumber", new Object[]{"0"});
                System.out.println("Метод: isFinalNumber - отправлено: 0, получено: " + result9 + " => " + (result9 != null && result9 ? "УСПЕХ" : "ПРОВАЛ"));
                Boolean result10 = (Boolean) testMethod(testClass, "isFinalNumber", new Object[]{"-1"});
                System.out.println("Метод: isFinalNumber - отправлено: -1, получено: " + result10 + " => " + (result10 != null && result10 ? "УСПЕХ" : "ПРОВАЛ"));
                Boolean result11 = (Boolean) testMethod(testClass, "isFinalNumber", new Object[]{"-1.2"});
                System.out.println("Метод: isFinalNumber - отправлено: -1.2, получено: " + result11 + " => " + (result11 != null && result11 ? "УСПЕХ" : "ПРОВАЛ"));
                Boolean result12 = (Boolean) testMethod(testClass, "isFinalNumber", new Object[]{"-1.2+3.4"});
                System.out.println("Метод: isFinalNumber - отправлено: -1.2+3.4, получено: " + result12 + " => " + (result12 != null && !result12 ? "УСПЕХ" : "ПРОВАЛ"));
                Boolean result13 = (Boolean) testMethod(testClass, "isFinalNumber", new Object[]{"-.1.2."});
                System.out.println("Метод: isFinalNumber - отправлено: -.1.2., получено: " + result13 + " => " + (result13 != null && !result13 ? "УСПЕХ" : "ПРОВАЛ"));
                Boolean result14 = (Boolean) testMethod(testClass, "isFinalNumber", new Object[]{"(1)"});
                System.out.println("Метод: isFinalNumber - отправлено: (1), получено: " + result14 + " => " + (result14 != null && !result14 ? "УСПЕХ" : "ПРОВАЛ"));
                Boolean result15 = (Boolean) testMethod(testClass, "isFinalNumber", new Object[]{"."});
                System.out.println("Метод: isFinalNumber - отправлено: ., получено: " + result15 + " => " + (result15 != null && !result15 ? "УСПЕХ" : "ПРОВАЛ"));
                Boolean result16 = (Boolean) testMethod(testClass, "isFinalNumber", new Object[]{"x"});
                System.out.println("Метод: isFinalNumber - отправлено: x, получено: " + result16 + " => " + (result16 != null && !result16 ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            default:
                System.out.println("Метод не обнаружен");
        }
    }

    private void testLinePreparing(Class testClass) {
        Method[] methodArray = testClass.getDeclaredMethods();
        if (methodArray.length > 0) {
            System.out.println("--- Выбери метод ---");
            for (int i = 0; i < methodArray.length; i++) {
                System.out.println(i + " - " + methodArray[i].getName());
            }
            System.out.print("твой выбор: ");
            String menuItem = getMenuItem();
            int menuNumber = Integer.parseInt(menuItem);
            testLinePreparing(testClass, methodArray[menuNumber].getName());
        } else {
            System.out.println("В классе " + testClass.getSimpleName() + ", методы не обнаружены");
            startTest();
        }
    }

    private void testLinePreparing(Class testClass, String methodName) {
        System.out.println("--- " + testClass.getSimpleName() + " ---");
        switch (methodName) {
            case "removeRoundBrackets":
                String result1 = (String) testMethod(testClass, "removeRoundBrackets", new Object[]{"(1)+(-2)+(-3)+(4)"});
                System.out.println("Метод: removeRoundBrackets - отправлено: (1)+(-2)+(-3)+(4), получено: " + result1 + " => " + ("1+(-2)+(-3)+4".equals(result1) ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            case "removeDuplicates":
                String result2 = (String) testMethod(testClass, "removeDuplicates", new Object[]{"++++--+--///*//***()-)(++**"});
                System.out.println("Метод: removeDuplicates - отправлено: ++++--+--///*//***()-)(++**, получено: " + result2 + " => " + ("+/*/*-)*(+*".equals(result2) ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            case "trimTails":
                String result3 = (String) testMethod(testClass, "trimTails", new Object[]{"++++--+--1--1++//**"});
                System.out.println("Метод: trimTails - отправлено: ++++--+--1--1++//**, получено: " + result3 + " => " + ("-1+1".equals(result3) ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            case "leaveMathSymbol":
                String result4 = (String) testMethod(testClass, "leaveMathSymbol", new Object[]{"+aaaa-1b+b1+cccc"});
                System.out.println("Метод: leaveMathSymbol - отправлено: +aaaa-1b--b1+cccc, получено: " + result4 + " => " + ("-1+1".equals(result4) ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            case "replaceCommas":
                String result5 = (String) testMethod(testClass, "replaceCommas", new Object[]{"1,2+3,4"});
                System.out.println("Метод: replaceCommas - отправлено: 1,2+3,4, получено: " + result5 + " => " + ("1.2+3.4".equals(result5) ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            case "removeSpaces":
                String result6 = (String) testMethod(testClass, "removeSpaces", new Object[]{"1 + 2 - 4"});
                System.out.println("Метод: removeSpaces - отправлено: 1 + 2 - 4, получено: " + result6 + " => " + ("1+2-4".equals(result6) ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            case "linePreparing":
                String result7 = (String) testMethod(testClass, "linePreparing", new Object[]{"-- // ++ -- 1 ++ ( ) ++ ( 2 - 4 ) ( 4 ** 5 ) ++"});
                System.out.println("Метод: linePreparing - отправлено: -- // ++ -- 1 ++ ( ) ++ ( 2 - 4 ) ( 4 ** 5 ) ++, получено: " + result7 + " => " + ("-1+(2-4)*(4*5)".equals(result7) ? "УСПЕХ" : "ПРОВАЛ"));
                break;
            default:
                System.out.println("Метод не обнаружен");
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    private Object testMethod(Class testClass, String methodName, Object[] parameterValueArray) {
        try {
            Method[] methodArray = testClass.getDeclaredMethods();
            for (Method item : methodArray) {
                Class[] parameterTypeArray = item.getParameterTypes();
                if (item.getName().equals(methodName) && parameterTypeArray.length == parameterValueArray.length) {
                    Method method = testClass.getDeclaredMethod(methodName, parameterTypeArray);
                    method.setAccessible(true);
                    return method.invoke(testClass, parameterValueArray);
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private Object testMethod(Class testClass, String methodName, Class[] parameterTypeArray, Object[] parameterValueArray) {
        try {
            Method[] methodArray = testClass.getDeclaredMethods();
            for (Method item : methodArray) {
                if (item.getName().equals(methodName) && parameterTypeArray.length == parameterValueArray.length) {
                    Method method = testClass.getDeclaredMethod(methodName, parameterTypeArray);
                    method.setAccessible(true);
                    return method.invoke(testClass, parameterValueArray);
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
