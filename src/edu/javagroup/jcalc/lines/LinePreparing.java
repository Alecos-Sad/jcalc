package edu.javagroup.jcalc.lines;

public class LinePreparing {

    public static String linePreparing(String str) {
//        Subtask #2 (?)
//        создать метод linePreparing
//        входные параметры: String
//        возвращает: String
//        реализация:
//        получаем строку на входе (далее строка)
//        1. если строка содержит пробелы, получить результат работы метода removeSpaces, записать результат в вашу строку
//        2. если строка содержит запятые, получить результат работы метода replaceCommas записать результат в вашу строку
//        3. записать в вашу строку результат работы метода leaveMathSymbols
//        4. если строка начинается на символ +, убрать этот символ за ненадобностью
//        5. вернуть строку
        String strFin = "";
        if (str.contains(" ")) {
            strFin = removeSpaces(str);
        }
        if (str.contains(",")) {
            strFin = replaceCommas(str);
        }
        strFin = leaveMathSymbol(strFin);
        if (strFin.startsWith("+")) {
            strFin.substring(1);
        }
        return str;
    }

    //work
    public static String removeSpaces(String source) {
        if (source.contains(" ")) {
            source = source.replace(" ", "");
        }
        return source;
    }

    //work
    public static String replaceCommas(String source) {
//            Subtask #4 (+)
//            создать метод replaceCommas
//            входные параметры: String
//            возвращает: String
//            реализация:
//            вернуть пришедшую строку, в которой все запятые заменены на точки
        if (source.contains(",")) {
            source = source.replace(",", ".");
        }
        return source;
    }
//work
    public static String leaveMathSymbol(String str) {
        //обработать пришедшую строку, так, чтобы она содержала, только допустимые символы: 0123456789.()*/+-
        //обработать полученную строку в методе trimTails
        //полученный результат вернуть в методе removeDuplicates
        StringBuilder result = new StringBuilder();
        String value = "0123456789.()*/+-";
        for (int i = 0; i < str.length(); i++) {
            if (value.contains(String.valueOf(str.charAt(i)))) {
                result.append(str.charAt(i));
            }
        }
        return trimTails(removeDuplicates(result.toString()));
    }

    //work
    public static String trimTails(String str) {

        int countStart = 0;
        int countFinish = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                countStart = i;
                break;
            }
        }
        for (int j = str.length() - 1; j >= 0; j--) {
            if (Character.isDigit(str.charAt(j))) {
                countFinish = j + 1;
                break;
            }
        }
        if ((str.charAt(countStart - 1) == '-') || (str.charAt(countStart - 1) == '(')) {
            --countStart;
        } else if ((str.charAt(countStart - 1) == '-') & (str.charAt(countStart - 2) == '(')) {
            countStart -= 2;
        } else if ((str.charAt(countFinish + 1) == ')')) {
            ++countFinish;
        }
        return removeDuplicates(str.substring(countStart, countFinish));
    }

    public static String removeDuplicates(String str) {
//                Subtask #7 (?)
//                создать метод removeDuplicates
//                входные параметры: String
//                возвращает: String
//                реализация:
//                получить строку, в которой все дубликаты символов * / + - заменены на единичные
        if (str.contains("--")) {
            return str.replace("--", "+");
        }
        if (str.contains("+-")) {
            return str.replace("+-", "-");
        }
        if (str.contains("-+")) {
            return str.replace("-+", "-");
        }
        if (str.contains(")(")) {
            return str.replace(")(", ")*(");
        }
        if (str.contains("()")) {
            return str.replace("()", "");
        }
        return str;
    }
}






