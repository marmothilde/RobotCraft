package net.polycule.robotcraft.event;

import net.minecraft.world.entity.animal.IronGolem;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.polycule.robotcraft.RobotCraft;

public class RobotCraftEventHandler {

	public static void init(IEventBus modBus) {
		
		modBus.addListener(RobotCraftEventHandler::addAttributes);
	}
		
	public static void addAttributes(EntityAttributeCreationEvent event) {
		event.put(RobotCraft.ROBOT.get(), IronGolem.createAttributes().build());
	}
}