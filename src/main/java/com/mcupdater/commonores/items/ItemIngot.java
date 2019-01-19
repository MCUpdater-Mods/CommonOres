package com.mcupdater.commonores.items;

import com.mcupdater.commonores.CommonOres;
import com.mcupdater.commonores.CreativeTabCO;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemIngot extends Item {

	public ItemIngot() {
		super();

		this.setUnlocalizedName("ingot");
		this.setCreativeTab(CreativeTabCO.getInstance());
		this.setHasSubtypes(true);
		this.setRegistryName(CommonOres.metadata.modId, "ingot");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
		for (int i = 0; i < CommonOres.types.size(); i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName(stack) + "." + CommonOres.types.get(stack.getMetadata()).toLowerCase();
	}
}
