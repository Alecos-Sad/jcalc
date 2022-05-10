package edu.javagroup.jcalc.digits;

import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * Используется для округления числа
 *
 * @author SadovNick
 */

public class Round {

    /** количество знаков после запятой */
    public static final int CONST_ROUND = 2;

    public static double round(double value) {
        return new BigDecimal(value).setScale(CONST_ROUND, RoundingMode.HALF_UP).doubleValue();
    }
}
