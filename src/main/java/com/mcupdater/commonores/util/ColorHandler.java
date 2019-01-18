package com.mcupdater.commonores.util;

import com.mcupdater.commonores.world.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.FMLLog;

import java.awt.*;

public class ColorHandler implements IBlockColor, IItemColor {
	@Override
	public int colorMultiplier(IBlockState state, IBlockAccess access, BlockPos pos, int tintIndex) {
		return getColor(tintIndex, state.getValue(BlockOre.TYPE));
	}

	@Override
	public int colorMultiplier(ItemStack stack, int tintIndex) {
		return getColor(tintIndex, stack.getMetadata());
	}

	private int getColor(int tintIndex, Integer type) {
		int color;
		switch (type) {
			case 0:
				color = new Color(200, 150, 0).getRGB();
				break;
			case 1:
				color = new Color(250, 250, 250).getRGB();
				break;
			default:
				color = -1;
		}
		return (tintIndex == 0 ? -1 : color);
	}
}
