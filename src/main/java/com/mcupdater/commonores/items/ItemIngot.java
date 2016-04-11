package com.mcupdater.commonores.items;

import com.mcupdater.commonores.CommonOres;
import com.mcupdater.commonores.CreativeTabCO;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemIngot extends Item {

	public ItemIngot() {
		super();

		this.setUnlocalizedName("ingot");
		this.setCreativeTab(CreativeTabCO.getInstance());
		this.setHasSubtypes(true);
		this.setRegistryName(CommonOres.metadata.modId,"ingot");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
	{
		for (int i = 0; i < CommonOres.types.size(); i++)
		{
			subItems.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName(stack) + "." + CommonOres.types.get(stack.getMetadata());
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		FMLLog.info(I18n.translateToLocal(getUnlocalizedName(stack)+".name"));
		return EnumActionResult.SUCCESS;
	}
}
