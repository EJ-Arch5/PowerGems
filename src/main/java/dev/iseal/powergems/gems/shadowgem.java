
package dev.iseal.powergems.gems;

import dev.iseal.powergems.PowerGems;
import dev.iseal.powergems.listeners.AvoidTargetListener;
import dev.iseal.powergems.listeners.FallingBlockHitListener;
import dev.iseal.powergems.misc.AbstractClasses.Gem;
import dev.iseal.sealLib.Systems.I18N.I18N;
import dev.iseal.sealLib.Utils.SpigotGlobalUtils;
import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.*;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Comparator;

public class shadowGem extends Gem { super("Shadow");
    }
    private final FallingBlockHitListener fbhl = sm.fallingBlockHitListen;// Check if the player is holding the Shadow Gem and is sneaking.
// An event listener will check for PlayerToggleSneakEvent.
@EventHandler
public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
    Player player = event.getPlayer();

    if (player.isSneaking()) {
        // Apply the invisibility effect when the player starts sneaking.
        // The amplifier determines the strength of the effect.
        // `false` prevents particles from being shown.
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
    } else {
        // Remove the invisibility effect when the player stops sneaking.
        player.removePotionEffect(PotionEffectType.INVISIBILITY);
    }
}

// Check if the player is holding the Shadow Gem and right-clicks.
// An event listener will check for PlayerInteractEvent.
@EventHandler
public void onPlayerInteract(PlayerInteractEvent event) {
    Player player = event.getPlayer();

    // Check if the action is a right-click and the player is holding the gem.
    if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
        // Verify the player is holding your Shadow Gem item.
        ItemStack item = player.getInventory().getItemInMainHand();
        if (isShadowGem(item)) { // `isShadowGem` would be a custom method to check for the correct gem.
            // Create the darkness effect.
            player.getWorld().getNearbyEntities(player.getLocation(), 10, 10, 10).forEach(entity -> {
                if (entity instanceof LivingEntity && !entity.equals(player)) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 5, 1)); // 5 seconds of blindness
                }
            });
        }
    }
}
