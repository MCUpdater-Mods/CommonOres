package com.mcupdater.commonores.world;

import com.mcupdater.commonores.CommonOres;
import com.mcupdater.commonores.CreativeTabCO;
import com.mcupdater.commonores.util.OreHandler;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOre extends Block {

	public static final PropertyInteger TYPE;
	public static final int MAX_TYPES = 15;

	static {
		TYPE = PropertyInteger.create("type",0, MAX_TYPES );
	}

	private Item itemBlock;

	public BlockOre() {
		super(Material.ROCK, Material.ROCK.getMaterialMapColor());
		setUnlocalizedName("blockore");
		setRegistryName(CommonOres.metadata.modId, "blockore");
		setHardness(3.0F);
		setResistance(5.0F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 1);
		setDefaultState(this.blockState.getBaseState().withProperty(TYPE, 0));

		itemBlock = this.generateItemBlock();
		setCreativeTab(CreativeTabCO.getInstance());
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
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
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		for (int type = 0; type < OreHandler.numTypes(); type++) {
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
	public int damageDropped(IBlockState state) {
		return state.getValue(TYPE);
	}

	private class ItemOreBlock extends ItemBlock {

		public ItemOreBlock(BlockOre blockOre) {
			super(blockOre);
			setUnlocalizedName("blockore");
			setRegistryName(CommonOres.metadata.modId, "blockore");
			setHasSubtypes(true);
		}

		@Override
		public String getUnlocalizedName(ItemStack stack) {
			// NB: should already be lowercase, but recast just for safety
			return super.getUnlocalizedName(stack) + "." + OreHandler.get(stack.getMetadata()).toLowerCase();
		}
	}
}
