package com.mcupdater.commonores.world;

import com.google.common.collect.Lists;
import com.mcupdater.commonores.CommonOres;
import com.mcupdater.commonores.util.OreHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.List;
import java.util.Random;

public class WorldGenCommonOres implements IWorldGenerator {
	public static final int DEFAULT_ORE_AMOUNT = 8;
	public static final int DEFAULT_CHANCE = 25;
	public static final int DEFAULT_TRIES = 4;
	public static final int DEFAULT_MIN_HEIGHT = 16;
	public static final int DEFAULT_MAX_HEIGHT = 72;

	public static final int WORLD_HEIGHT_BUFFER = 5;

	private final List<WorldGenerator> generators;

	public WorldGenCommonOres(BlockOre block) {
		generators = Lists.newArrayList();
		for(int type = 0; type < OreHandler.numTypes(); ++type ) {
			final IBlockState state = block.getDefaultState().withProperty(BlockOre.TYPE, type);
			CommonOres.log.info("registering generator for "+state.toString()+", "+ OreHandler.get(type));
			final WorldGenMinable gen = new WorldGenMinable(
					state,
					DEFAULT_ORE_AMOUNT,
					BlockMatcher.forBlock(Blocks.STONE)
			);
			generators.add(gen);
		}
	}

	private void run(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int tries, int minHeight, int maxHeight) {
		if ( minHeight > maxHeight || minHeight < WORLD_HEIGHT_BUFFER || maxHeight > world.getHeight() - WORLD_HEIGHT_BUFFER ) {
			throw new IllegalArgumentException("attempt to generate outside of allowed vertical bounds");
		}

		// pick a position
		final int _y = maxHeight - minHeight + 1;
		for( int k = 0; k < tries; ++k) {
			final int x = chunkX * 16 + rand.nextInt(16);
			final int y = minHeight + rand.nextInt(_y);
			final int z = chunkZ * 16 + rand.nextInt(16);
			final BlockPos pos = new BlockPos(x,y,z);

			if ( gen.generate(world, rand, pos) ) {
				CommonOres.log.debug(gen+" @ "+pos);
				return;
			}
		}
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		for( WorldGenerator gen : generators ) {
			if ( random.nextInt(100) < DEFAULT_CHANCE ) {
				run(gen, world, random, chunkX, chunkZ, DEFAULT_TRIES, DEFAULT_MIN_HEIGHT, DEFAULT_MAX_HEIGHT);
			}
		}
	}
}
