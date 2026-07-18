package wile.engineersdecor.datagen.providers;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import wile.engineersdecor.ModContent;
import wile.engineersdecor.ModEngineersDecor;

public class EdBlockTags extends BlockTagsProvider {

	public EdBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
			@Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, ModEngineersDecor.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		List<Block> blocks = ModContent.getRegisteredBlocks();
		tag(BlockTags.MINEABLE_WITH_PICKAXE).add(bySoundType(blocks, SoundType.STONE, SoundType.METAL, SoundType.NETHER_BRICKS, SoundType.DEEPSLATE));
		tag(BlockTags.MINEABLE_WITH_AXE).add(bySoundType(blocks, SoundType.WOOD));
		tag(BlockTags.MINEABLE_WITH_SHOVEL).add(bySoundType(blocks, SoundType.GRAVEL, SoundType.SAND));

		tag(BlockTags.SLABS).add(byNameSuffix(blocks, "_slab"));
		tag(BlockTags.WALLS).add(byNameSuffix(blocks, "_wall"));
		tag(BlockTags.STAIRS).add(byNameSuffix(blocks, "_stairs"));
		tag(BlockTags.FENCES).add(byNameSuffix(blocks, "_fence"));
		tag(BlockTags.FENCE_GATES).add(byNameSuffix(blocks, "_fence_gate"));
		tag(BlockTags.DOORS).add(byNameSuffix(blocks, "_door"));
		tag(BlockTags.TRAPDOORS).add(byNameSuffix(blocks, "_trapdoor"));
		tag(BlockTags.WOODEN_DOORS).add(ModContent.getBlock("old_industrial_wood_door"));
		tag(BlockTags.WITHER_IMMUNE).add(byNameSubstring(blocks, "rebar_concrete", "panzerglass"));
		tag(BlockTags.DIRT).add(ModContent.getBlock("dense_grit_dirt_block"));
		tag(BlockTags.CLIMBABLE).add(ModContent.getBlock("metal_rung_ladder"))
		.add(ModContent.getBlock("metal_rung_steps"));
		tag(BlockTags.MINEABLE_WITH_AXE)
	    .remove(ForgeRegistries.BLOCKS.getKey(ModContent.getBlock("steel_mesh_fence_gate")));
	}

	@Override
	public String getName() {
		return ModEngineersDecor.MODNAME + " Block Tags";
	}

	// work around method for now, since some blocks dont extend the expected
	// vanailla classes like variantslabblocks. todo for later.
	private static Block[] byNameSuffix(List<Block> blocks, String suffix) {
		return blocks.stream().filter(b -> ForgeRegistries.BLOCKS.getKey(b).getPath().endsWith(suffix))
				.toArray(Block[]::new);
	}

	//not sure if its a good idea, but actually based on the sound type its clear,
	//>if its a wood or a stone/metal block, and therefore we can use this to put 
	//all blocks into the right tool tag
	private static Block[] bySoundType(List<Block> blocks, SoundType... types) {
		Set<SoundType> wanted = Set.of(types);
		return blocks.stream().filter(b -> wanted.contains(b.defaultBlockState().getSoundType())).toArray(Block[]::new);
	}
	
	//since everything is registred directly, and there are no static final holders, 
	//we might as well use a string filter search to grap certain types of blocks. 
	private static Block[] byNameSubstring(List<Block> blocks, String... serachString)
	  {
	        return blocks.stream()
	                .filter(b -> {
	                        String path = ForgeRegistries.BLOCKS.getKey(b).getPath();
	                        for (String string: serachString) if (path.contains(string)) return true;
	                        return false;
	                })
	                .toArray(Block[]::new);
	  }
}