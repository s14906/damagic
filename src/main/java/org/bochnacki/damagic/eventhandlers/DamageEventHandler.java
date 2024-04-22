package org.bochnacki.damagic.eventhandlers;
import org.bochnacki.damagic.entity.EntityManager;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bochnacki.damagic.Damagic;
import org.bochnacki.damagic.entity.ArmorStandManager;
import org.bochnacki.damagic.util.text.TextColorResolver;
import org.bochnacki.damagic.util.DamageValueFormatter;
import org.bochnacki.damagic.util.LocationChanger;


public class DamageEventHandler {

    public static void handleEvent(EntityDamageEvent event) {
        Entity damagedEntity = event.getEntity();
        if (isEntityValid(damagedEntity)) {
            displayDamageMarker(event, damagedEntity.getLocation());
            if (Damagic.config.getBoolean("playerDamageInChat") && damagedEntity.getType().equals(EntityType.PLAYER)) {
                displayDamageToPlayerChat(event, damagedEntity);
            }
//            broadcastMessageToChat(event, damagedEntity, formatDamage(event));
        }
    }

    private static void displayDamageToPlayerChat(EntityDamageEvent event, Entity damagedEntity) {
        Player player = (Player) damagedEntity;
        String playerMessage = "Suffered " +
                TextColorResolver.resolveColor(event.getCause()).getColorCode() +
                DamageValueFormatter.formatDamage(event.getFinalDamage()) +
                " §rdamage.";
        player.sendRawMessage(playerMessage);
    }

    private static void displayDamageMarker(EntityDamageEvent event, Location location) {
        EntityManager entityManager = new ArmorStandManager();
        createArmorStand(location, entityManager);
        entityManager.spawnFloatingDamageText(
                DamageValueFormatter.formatDamage(event.getFinalDamage()),
                location,
                event.getCause());
        entityManager.despawnAfterTicks(20);
    }

    private static void createArmorStand(Location location, EntityManager entityManager) {
        Location spawnLocation = LocationChanger.changeLocation(location, 0, 100, 0);
        entityManager.spawnEntity(spawnLocation);
        entityManager.makeEntityUntouchable();
        entityManager.teleportEntity(0, -98, 0);
    }

//    private static void broadcastMessageToChat(EntityDamageEvent event, Entity damagedEntity, String formattedDamageString) {
//        String cause = event.getCause().toString();
//        Bukkit.broadcastMessage("Entity " + damagedEntity.getName() + " suffered " + formattedDamageString + " damage due to " +
//                cause);
//    }


    private static boolean isEntityValid(Entity entity) {
        return entity.getType().isAlive() && !entity.getType().equals(EntityType.ARMOR_STAND);
    }
}
