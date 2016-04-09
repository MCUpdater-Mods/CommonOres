package com.mcupdater.commonores.proxy;

import com.mcupdater.commonores.CommonOres;
import com.mcupdater.commonores.world.BlockOre;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLLog;

public class ClientProxy extends CommonProxy {

	private ModelResourceLocation blockOreRL;

	@Override
	public void preInit() {
		blockOreRL = new ModelResourceLocation(CommonOres.metadata.modId + ":blockOre", "inventory");
	}

	@Override
	public void registerModels() {
		for (int i = 0; i <= CommonOres.types.size(); i++) {
			ModelLoader.setCustomModelResourceLocation(CommonOres.oreBlock.getItemBlock(), i, blockOreRL);
		}
	}

	@Override
	public void registerRenders() {
		for (int i = 0; i <= CommonOres.types.size(); i++) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(CommonOres.oreBlock), i, blockOreRL);
			Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new BlockOre.ColorHandler(), CommonOres.oreBlock);
			Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new BlockOre.ColorHandler(), CommonOres.oreBlock);
		}
	}
}
