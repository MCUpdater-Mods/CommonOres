package com.mcupdater.commonores.proxy;

import com.mcupdater.commonores.CommonOres;
import com.mcupdater.commonores.client.ResourceListener;
import com.mcupdater.commonores.util.ColorHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.Locale;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.item.Item;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.util.text.translation.LanguageMap;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.io.ByteArrayInputStream;

public class ClientProxy extends CommonProxy {

	private ModelResourceLocation blockOreRL;
	private ModelResourceLocation itemIngotRL;

	@Override
	public void preInit() {
		blockOreRL = new ModelResourceLocation(CommonOres.metadata.modId + ":blockOre", "inventory");
		itemIngotRL = new ModelResourceLocation(CommonOres.metadata.modId + ":ingot", "inventory");
	}

	@Override
	public void loadComplete() {
		((SimpleReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(new ResourceListener());
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
			Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new ColorHandler(), CommonOres.oreBlock);
			Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ColorHandler(), CommonOres.oreBlock);
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(CommonOres.ingotItem, i, itemIngotRL);
			Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ColorHandler(), CommonOres.ingotItem);
		}
	}

	@Override
	public void injectLocalizations() {
		super.injectLocalizations();
	}
}
