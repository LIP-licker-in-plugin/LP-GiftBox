package com.licker2689.lgb.events;

import com.licker2689.lgb.GiftBox;
import com.licker2689.lgb.functions.LGBFunction
;
import com.licker2689.lgb.functions.SettingType;
import com.licker2689.lpc.api.inventory.LInventory;
import com.licker2689.lpc.utils.NBT;
import com.licker2689.lpc.utils.Tuple;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class LGBEvent implements Listener {
    private final GiftBox plugin = GiftBox.getInstance();

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        if (e.getInventory() instanceof LInventory) {
            LInventory inv = (LInventory) e.getInventory();
            if (inv.isValidHandler(plugin)) {
                Tuple<String, SettingType> tpl = (Tuple<String, SettingType>) inv.getObj();
                if (tpl.getB() == SettingType.COUPON) {
                    LGBFunction
.saveCouponSetting(p, inv);
                }
                if (tpl.getB() == SettingType.PRIZE) {
                    LGBFunction
.savePrizeSetting(p, inv);
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory() instanceof LInventory) {
            LInventory inv = (LInventory) e.getInventory();
            if (inv.isValidHandler(GiftBox.getInstance())) {
                Tuple<String, SettingType> tpl = (Tuple<String, SettingType>) inv.getObj();
                if (tpl.getB() == SettingType.COUPON) {
                    if (e.getClickedInventory() instanceof LInventory && e.getSlot() != 13) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if(e.getHand() == EquipmentSlot.OFF_HAND) return;
        if (e.getItem() != null) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (NBT.hasTagKey(e.getItem(), "DGB")) {
                    String name = NBT.getStringTag(e.getItem(), "DGB");
                    LGBFunction
.givePrize(e.getPlayer(), name, e.getItem());
                    e.setCancelled(true);
                }
            }
        }
    }
}
