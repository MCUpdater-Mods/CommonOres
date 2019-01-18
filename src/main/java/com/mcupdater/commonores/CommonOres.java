package com.mcupdater.commonores;

import com.mcupdater.commonores.items.ItemIngot;
import com.mcupdater.commonores.proxy.CommonProxy;
import com.mcupdater.commonores.world.BlockOre;
import com.mcupdater.commonores.world.WorldGenCommonOres;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
@Mod(modid = "commonores", useMetadata = true)
public class CommonOres {

	@SidedProxy(clientSide = "com.mcupdater.commonores.proxy.ClientProxy", serverSide = "com.mcupdater.commonores.proxy.CommonProxy")
	public static CommonProxy proxy;
	public static Logger log;

	public static ModMetadata metadata;
	public static Config config;

	public static BlockOre oreBlock;
	public static List<String> types = new ArrayList<String>();
	public static ItemIngot ingotItem;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		log = event.getModLog();
		log.info("preInit");

		metadata = event.getModMetadata();
		config = new Config(event.getSuggestedConfigurationFile());

		// TODO: fork this out into a separate handler
		final String[] oreTypes = {"Copper", "Tin"};

		for (String type : oreTypes) {
			types.add(type);
		}
		oreBlock = new BlockOre();
		ingotItem = new ItemIngot();

		proxy.preInit();
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		log.info("registerBlocks");
		event.getRegistry().register(oreBlock);
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		log.info("registerItems");
		event.getRegistry().register(oreBlock.getItemBlock());
		event.getRegistry().register(ingotItem);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		log.info("init");
		proxy.registerModels();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		log.info("postInit");
		proxy.registerRenders();

		GameRegistry.registerWorldGenerator(new WorldGenCommonOres(oreBlock), 0);
	}

	@EventHandler
	public void loadComplete(FMLLoadCompleteEvent event) {
		log.info("generating localizations");
		proxy.generateLocalizations();
		proxy.injectLocalizations();
		proxy.loadComplete();
	}
}
