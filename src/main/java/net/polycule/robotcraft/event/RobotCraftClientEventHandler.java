package net.polycule.robotcraft.event;

import net.minecraft.client.renderer.entity.IronGolemRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.polycule.robotcraft.RobotCraft;
import net.polycule.robotcraft.renderer.RobotRenderer;

public class RobotCraftClientEventHandler {
    
	public static void init(IEventBus modBus) {
		
		modBus.addListener(RobotCraftClientEventHandler::registerEntityRenders);
	}
	
	private static void registerEntityRenders(final EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(RobotCraft.ROBOT.get(), RobotRenderer::new);
	}
}
