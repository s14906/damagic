package org.bochnacki.damagic.entity;

import org.bukkit.Location;
import org.bukkit.event.entity.EntityDamageEvent;

public interface EntityManager {

    void spawnFloatingDamageText(String damageValue, Location location, EntityDamageEvent.DamageCause cause);

    void makeEntityUntouchable();

    void spawnEntity(Location location);

    void despawnAfterTicks(int ticks);

    void teleportEntity(int x, int y, int z);
}
