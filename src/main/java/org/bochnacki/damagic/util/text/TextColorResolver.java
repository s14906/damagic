package org.bochnacki.damagic.util.text;

import org.bukkit.event.entity.EntityDamageEvent;

public class TextColorResolver {

    public static TextColor resolveColor(EntityDamageEvent.DamageCause cause) {
        return switch (cause) {
            case FIRE, FIRE_TICK, LAVA, HOT_FLOOR -> TextColor.ORANGE;
            case POISON -> TextColor.GREEN;
            case FREEZE -> TextColor.BLUE;
            case MAGIC -> TextColor.PURPLE;
            default -> TextColor.WHITE;
        };
    }
}
