package com.mcupdater.commonores;

import com.mcupdater.commonores.world.BlockOre;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = "commonores", useMetadata = true)
public class CommonOres {

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// TODO: read config
		final String[] oreTypes = { "copper", "tin" };
		
		// TODO: fork this out into a separate handler
		BlockOre block = new BlockOre();		
		for( String type : oreTypes ) {
			block.addType(type);
		}
		Item item = new ItemBlock(block);
		
		ResourceLocation loc = new ResourceLocation("commonores", block.getUnlocalizedName());
		GameRegistry.register(block, loc);
		GameRegistry.register(item, loc);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
	}
}
