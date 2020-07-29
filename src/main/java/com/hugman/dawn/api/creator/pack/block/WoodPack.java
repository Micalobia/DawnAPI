package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.BlockGetter;
import com.hugman.dawn.api.creator.ModData;
import com.hugman.dawn.api.creator.pack.Pack;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MaterialColor;

public class WoodPack extends Pack {
	private final Block planks;
	private final LogsPack logs;
	private final MSBlockPack woodenBlocks;
	private final MSBlockPack barkBlocks;

	protected WoodPack(ModData modData, String name, MaterialColor planksColor, MaterialColor insideColor, MaterialColor barkColor, boolean isNether) {
		FabricBlockSettings settings = FabricBlockSettings.copyOf(isNether ? Blocks.CRIMSON_PLANKS : Blocks.OAK_PLANKS).materialColor(planksColor);
		this.planks = add(new BlockCreator.Builder(name, BlockGetter.PLANKS, settings), modData);
		this.logs = add(new LogsPack.Builder(name, insideColor, barkColor, isNether), modData);
		this.woodenBlocks = add(new MSBlockPack.Builder(name, settings,
				BlockGetter.STAIRS,
				BlockGetter.SLAB,
				BlockGetter.VERTICAL_SLAB,
				BlockGetter.TRAPDOOR,
				BlockGetter.WOOD_PRESSURE_PLATE,
				BlockGetter.WOOD_BUTTON,
				BlockGetter.FENCE,
				BlockGetter.FENCE_GATE,
				BlockGetter.DOOR).copy(planks), modData);
		this.barkBlocks = add(new MSBlockPack.Builder(name + logs.getWoodName(), settings.materialColor(barkColor),
				BlockGetter.STAIRS,
				BlockGetter.SLAB,
				BlockGetter.VERTICAL_SLAB,
				BlockGetter.WOOD_BUTTON).copy(logs.getWood()), modData);
	}

	public static class Builder extends Pack.Builder {
		private final String name;
		private final MaterialColor planksColor;
		private final MaterialColor insideColor;
		private final MaterialColor barkColor;
		private final boolean isNether;

		/**
		 * Creates an entry pack containing blocks for basic wood types.
		 *
		 * @param name        The name of the wood type. (ex: <code>oak</code>)
		 * @param planksColor The material color of the planks.
		 * @param insideColor The material color of the inside of logs.
		 * @param barkColor   The material color of the bark side of logs.
		 * @param isNether    Defines if the wood type comes from the nether. (changes the name, sounds and materials)
		 */
		public Builder(String name, MaterialColor planksColor, MaterialColor insideColor, MaterialColor barkColor, boolean isNether) {
			this.name = name;
			this.planksColor = planksColor;
			this.insideColor = insideColor;
			this.barkColor = barkColor;
			this.isNether = isNether;
		}

		public WoodPack build(ModData modData) {
			return new WoodPack(modData, name, planksColor, insideColor, barkColor, isNether);
		}
	}

	public Block getPlanks() {
		return planks;
	}

	public Block getLog() {
		return logs.getLog();
	}

	public Block getStrippedLog() {
		return logs.getStrippedLog();
	}

	public Block getWood() {
		return logs.getWood();
	}

	public Block getStrippedWood() {
		return logs.getStrippedWood();
	}

	public Block getWoodStairs() {
		return barkBlocks.getBlock(BlockGetter.STAIRS);
	}

	public Block getWoodSlab() {
		return barkBlocks.getBlock(BlockGetter.SLAB);
	}

	public Block getWoodVerticalSlab() {
		return barkBlocks.getBlock(BlockGetter.VERTICAL_SLAB);
	}

	public Block getWoodButton() {
		return barkBlocks.getBlock(BlockGetter.WOOD_BUTTON);
	}

	public Block getPressurePlate() {
		return woodenBlocks.getBlock(BlockGetter.WOOD_PRESSURE_PLATE);
	}

	public Block getTrapdoor() {
		return woodenBlocks.getBlock(BlockGetter.TRAPDOOR);
	}

	public Block getButton() {
		return woodenBlocks.getBlock(BlockGetter.WOOD_BUTTON);
	}

	public Block getStairs() {
		return woodenBlocks.getBlock(BlockGetter.STAIRS);
	}

	public Block getSlab() {
		return woodenBlocks.getBlock(BlockGetter.SLAB);
	}

	public Block getVerticalSlab() {
		return woodenBlocks.getBlock(BlockGetter.VERTICAL_SLAB);
	}

	public Block getFenceGate() {
		return woodenBlocks.getBlock(BlockGetter.FENCE_GATE);
	}

	public Block getFence() {
		return woodenBlocks.getBlock(BlockGetter.FENCE);
	}

	public Block getDoor() {
		return woodenBlocks.getBlock(BlockGetter.DOOR);
	}
}
