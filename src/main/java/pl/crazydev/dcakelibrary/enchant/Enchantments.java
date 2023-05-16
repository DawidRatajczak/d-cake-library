package pl.crazydev.dcakelibrary.enchant;

import org.bukkit.enchantments.Enchantment;
import pl.crazydev.dcakelibrary.log.Logger;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Enchantments {

    public static boolean isCustomEnchant(Enchantment enchant) {
        return !"protection_fire_protection_feather_falling_blast_protection_projectile_protection_respiration_aqua_affinity_thorns_depth_strider_frost_walker_binding_curse_sharpness_smite_bane_of_arthropods_knockback_fire_aspect_looting_sweeping_efficiency_silk_touch_unbreaking_fortune_power_punch_flame_infinity_luck_of_the_sea_lure_loyalty_impaling_riptide_channeling_multishot_quick_charge_piercing_mending_vanishing_curse_soul_speed_swift_sneak"
                .contains(enchant.getKey().getKey());
    }

    public static void registerEnchant(Enchantment enchant) {
        boolean isRegistered = Arrays.stream(Enchantment.values()).toList().contains(enchant);

        if (isRegistered) {
            Logger.log(enchant.getKey().getNamespace().concat(" is registered."));
            return;
        }

        try {
            Field field = Enchantment.class.getDeclaredField("acceptingNew");

            field.setAccessible(true);
            field.set(null, true);

            Enchantment.registerEnchantment(enchant);
        } catch (NoSuchFieldException | IllegalAccessException exception) {
            Logger.error("Failed to register enchant ".concat(enchant.getKey().getNamespace()));
        }
    }
}
