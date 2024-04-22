package org.bochnacki.damagic.util.text;

public enum TextColor {
    WHITE("§l"),
    PURPLE("§d§l"),
    BLUE("§9§l"),
    GREEN("§a§l"),
    ORANGE("§6§l");

    private final String colorCode;

    TextColor(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorCode() {
        return colorCode;
    }
}
