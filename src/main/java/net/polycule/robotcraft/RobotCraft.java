package net.polycule.robotcraft;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.polycule.robotcraft.event.RobotCraftClientEventHandler;
import net.polycule.robotcraft.event.RobotCraftEventHandler;
import net.polycule.robotcraft.items.RobotCraftItems;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RobotCraft.MODID)
public class RobotCraft {
	// Define mod id in a common place for everything to reference
	public static final String MODID = "robotcraft";
	// Directly reference a slf4j logger
	private static final Logger LOGGER = LogUtils.getLogger();
	// Create a Deferred Register to hold Blocks which will all be registered under the "robotcraft" namespace

	public static final DeferredRegister<EntityType<?>> ENTITES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

	public static final RegistryObject<EntityType<IronGolem>> ROBOT = ENTITES.register("robot", () -> EntityType.Builder.<IronGolem>of(IronGolem::new, MobCategory.MISC).sized(1.0F, 2.0F).clientTrackingRange(10).build((new ResourceLocation(MODID, "robot")).toString()));



	public RobotCraft() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		// Register the commonSetup method for modloading
		modEventBus.addListener(this::commonSetup);

		ENTITES.register(modEventBus);

		RobotCraftItems.register(modEventBus);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);

		// Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

		RobotCraftEventHandler.init(modEventBus);

		if (FMLEnvironment.dist == Dist.CLIENT) {
			RobotCraftClientEventHandler.init(modEventBus);
		}
	}

	private void commonSetup(final FMLCommonSetupEvent event) {        // Some common setup code
		LOGGER.info("HELLO FROM COMMON SETUP");
	}

	// You can use SubscribeEvent and let the Event Bus discover methods to call
	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event) {
		// Do something when the server starts
		LOGGER.info("HELLO from server starting");
	}

	// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
	@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientModEvents {
		@SubscribeEvent
		public static void onClientSetup(FMLClientSetupEvent event) {
			// Some client setup code
			LOGGER.info("HELLO FROM CLIENT SETUP");
			LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
		}
	}
}
