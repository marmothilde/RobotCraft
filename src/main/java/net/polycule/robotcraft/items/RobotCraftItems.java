package net.polycule.robotcraft.items;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.polycule.robotcraft.RobotCraft;

public class RobotCraftItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RobotCraft.MODID);
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RobotCraft.MODID);

	// Creates a new food item with the id "examplemod:example_id", nutrition 1 and saturation 2
	public static final RegistryObject<Item> CARD = ITEMS.register("card", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().nutrition(1).saturationModifier(2f).build())));

	// Creates a creative tab with the id "examplemod:example_tab" for the example item, that is placed after the combat tab
	public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register(RobotCraft.MODID, () -> CreativeModeTab.builder().withTabsBefore(CreativeModeTabs.COMBAT).icon(() -> CARD.get().getDefaultInstance()).displayItems((parameters, output) -> {
		output.accept(CARD.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
	}).build());

	public static void register(IEventBus modEventBus) {
		// Register the Deferred Register to the mod event bus so items get registered
		ITEMS.register(modEventBus);
		// Register the Deferred Register to the mod event bus so tabs get registered
		CREATIVE_MODE_TABS.register(modEventBus);
	}

}
