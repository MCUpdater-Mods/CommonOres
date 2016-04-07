package com.mcupdater.commonores.world;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOre extends Block {

	public final Map<String,Integer> types;
	
	public BlockOre() {
		super(Material.rock);
		setHardness(3.0F);
		setResistance(5.0F);
		setStepSound(SoundType.STONE);
		
		setUnlocalizedName("oreCommon");
		setHarvestLevel("pickaxe",1);
		
		types = Maps.newHashMap();
	}
		
	public void addType(String type) {
		types.put(type, types.size());
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
		for( String type : types.keySet() ) {
			list.add(new ItemStack(this, 1, types.get(type)));
		}
	}
	
}
