package urlauba.quickreplenish;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.util.math.Direction;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.client.gui.screen.TitleScreen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuickReplenish implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("quick-replenish");

	@Override
	public void onInitialize() {
		// Left-Click
		AttackBlockCallback.EVENT
				.register((PlayerEntity player, World world, Hand hand, BlockPos pos, Direction direction) -> {
					LOGGER.info("AttackBlockCallback");

					PlayerInventory inventory = player.getInventory();
					ItemStack item = inventory.getMainHandStack();
					LOGGER.info(item.getName().toString());
					LOGGER.info(item.isEmpty() ? "isEmpty" : "isNotEmpty");
					LOGGER.info(item.isFood() ? "isFood" : "isNotFood");
					LOGGER.info(String.valueOf(item.getDamage()));

					return ActionResult.PASS;
				});

		// Right-Click
		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			LOGGER.info("UseItemCallback");
			return ActionResult.PASS;
		});

		UseItemCallback.EVENT.register((player, world, hand) -> {
			LOGGER.info("UseItemCallback");
			// Logger.info(player, world, hand);
			return TypedActionResult.pass(ItemStack.EMPTY);
		});
	}
}