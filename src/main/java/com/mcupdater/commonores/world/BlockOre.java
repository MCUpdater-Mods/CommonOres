package com.mcupdater.commonores.world;

import com.mcupdater.commonores.CommonOres;
import com.mcupdater.commonores.CreativeTabCO;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.List;

public class BlockOre extends Block {

	public static final PropertyInteger TYPE;

	static {
		TYPE = PropertyInteger.create("type",0,CommonOres.types.size());
	}

	private Item itemBlock;

	public BlockOre() {
		super(Material.rock, Material.rock.getMaterialMapColor());
		setUnlocalizedName("blockOre");
		setRegistryName(CommonOres.metadata.modId, "blockOre");
		setHardness(3.0F);
		setResistance(5.0F);
		setStepSound(SoundType.STONE);
		setHarvestLevel("pickaxe",1);
		setDefaultState(this.blockState.getBaseState().withProperty(TYPE, 0));
		
		itemBlock = this.generateItemBlock();
		setCreativeTab(CreativeTabCO.getInstance());
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()	{
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	public Item getItemBlock() {
		return this.itemBlock;
	}

	private ItemBlock generateItemBlock() {
		ItemBlock itemBlock = new ItemOreBlock(this);
		return itemBlock;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
		for( int type = 0; type < CommonOres.types.size(); type++) {
			list.add(new ItemStack(this, 1, type));
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, TYPE);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(TYPE);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(TYPE, meta);
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState blockState, EntityLivingBase placer, ItemStack stack) {
		super.onBlockPlacedBy(world, pos, blockState, placer, stack);
		world.setBlockState(pos, blockState.withProperty(TYPE, stack.getMetadata()), 2);
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return state.getValue(TYPE);
	}

	public static class ColorHandler implements IBlockColor, IItemColor {
		@Override
		public int colorMultiplier(IBlockState state, IBlockAccess access, BlockPos pos, int tintIndex) {
			return getColor(tintIndex, state.getValue(TYPE));
		}

		@Override
		public int getColorFromItemstack(ItemStack stack, int tintIndex) {
			return getColor(tintIndex, stack.getMetadata());
		}

		private int getColor(int tintIndex, Integer type) {
			int color;
			switch(type) {
				case 0:
					color = new Color(200,150,0).getRGB();
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

	private class ItemOreBlock extends ItemBlock {

		public ItemOreBlock(BlockOre blockOre) {
			super(blockOre);
			setUnlocalizedName("blockOre");
			setRegistryName(CommonOres.metadata.modId, "blockOre");
			setHasSubtypes(true);
		}

		@Override
		public String getUnlocalizedName(ItemStack stack) {
			return super.getUnlocalizedName(stack) + "." + CommonOres.types.get(stack.getMetadata());
		}
	}
}
