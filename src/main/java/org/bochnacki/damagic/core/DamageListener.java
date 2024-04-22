package org.bochnacki.damagic.core;

import org.bochnacki.damagic.eventhandlers.DamageEventHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bochnacki.damagic.Damagic;

public class DamageListener implements Listener {

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event) {
        if (Damagic.config.getBoolean("enabled")) {
            DamageEventHandler.handleEvent(event);
        }
    }
}
