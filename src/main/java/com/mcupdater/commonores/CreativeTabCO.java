package com.mcupdater.commonores;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabCO extends CreativeTabs {

	private static CreativeTabCO instance;

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
	public Item getTabIconItem() {
		return CommonOres.oreBlock.getItemBlock();
	}
}
