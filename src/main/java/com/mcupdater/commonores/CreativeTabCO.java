package com.mcupdater.commonores;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabCO extends CreativeTabs {

	private static CreativeTabCO instance;
	private ItemStack iconItem;

	public CreativeTabCO() {
		super(CommonOres.metadata.modId);
		CreativeTabCO.instance = this;
	}

	public static CreativeTabCO getInstance() {
		if (instance == null) {
			instance = new CreativeTabCO();
		}
		return instance;
	}

	@Override
	public ItemStack getTabIconItem() {
		if (getInstance().iconItem == null) {
			getInstance().iconItem = new ItemStack(CommonOres.oreBlock.getItemBlock(), 1);
		}

		return getInstance().iconItem;
	}
}
