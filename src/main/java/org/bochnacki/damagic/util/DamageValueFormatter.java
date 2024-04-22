package org.bochnacki.damagic.util;

import java.text.DecimalFormat;

public class DamageValueFormatter {
    private static final String DAMAGE_FORMAT = "#,##0.0";
    public static final String MINUS_SIGN = "-";

    public static String formatDamage(double damage) {
        DecimalFormat decimalFormat = new DecimalFormat(DAMAGE_FORMAT);
        return decimalFormat.format(damage);
    }
}
