package org.bochnacki.damagic.entity;

import org.bochnacki.damagic.util.text.TextColorResolver;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bochnacki.damagic.Damagic;
import org.bochnacki.damagic.util.DamageValueFormatter;
import org.bochnacki.damagic.util.LocationChanger;

import java.util.Objects;

public class ArmorStandManager implements EntityManager {
    private ArmorStand armorStand;

    @Override
    public void spawnFloatingDamageText(String damageValue, Location location, EntityDamageEvent.DamageCause cause) {
        armorStand.setMarker(true);
        String damageColorCode = TextColorResolver.resolveColor(cause).getColorCode();
        armorStand.setCustomName(damageColorCode + DamageValueFormatter.MINUS_SIGN + damageValue);
        armorStand.setCustomNameVisible(true);
    }

    @Override
    public void spawnEntity(Location location) {
        armorStand = (ArmorStand) Objects.requireNonNull(location.getWorld())
                .spawnEntity(location, EntityType.ARMOR_STAND);
    }

    @Override
    public void makeEntityUntouchable() {
        armorStand.setInvisible(true);
        armorStand.setInvulnerable(true);
        armorStand.setGravity(false);
    }

    @Override
    public void despawnAfterTicks(int ticks) {
        Bukkit.getScheduler().runTaskLater(Damagic.getPlugin(Damagic.class), () -> {
            armorStand.setCustomName(null);
            armorStand.setCustomNameVisible(false);
            armorStand.setMarker(false);
            armorStand.remove();
        }, ticks);
    }

    @Override
    public void teleportEntity(int x, int y, int z) {
        armorStand.teleport(LocationChanger.changeLocation(armorStand.getLocation(), x, y, z));

    }

}
