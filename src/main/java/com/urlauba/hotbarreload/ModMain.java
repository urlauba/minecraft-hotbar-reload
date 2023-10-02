package com.urlauba.hotbarreload;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

@Mod("hotbarreload")
public class ModMain {

    public ModMain() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onPlayerDestroyItem(PlayerDestroyItemEvent event) {
        ItemStack damangedItem = event.getOriginal();

        Player player = event.getEntity();
        Inventory inventory = player.getInventory();
        int replacementIndex = findItemIndexInInventory(inventory, damangedItem.getItem());

        if (replacementIndex == -1) {
            return;
        }

        int selectedIndex = inventory.selected;
        inventory.setItem(selectedIndex, inventory.getItem(replacementIndex));
        inventory.setItem(replacementIndex, ItemStack.EMPTY);
    }

    public int findItemIndexInInventory(Inventory inventory, Item item) {
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            if (inventory.getItem(i).getItem() == item) {
                return i;
            }
        }
        return -1;
    }
}
