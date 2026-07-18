package wile.engineersdecor;

import blusunrize.immersiveengineering.common.register.IEBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;

@Mod.EventBusSubscriber(modid = ModEngineersDecor.MODID)
public class EdEvents {

	@SubscribeEvent
    public static void onMissingMappings(MissingMappingsEvent event) {
    	// For blocks
    	for (MissingMappingsEvent.Mapping<Block> mapping : event.getMappings(ForgeRegistries.Keys.BLOCKS, ModEngineersDecor.MODID)) {
    	    if(mapping.getKey().getPath().equals("slag_brick_block")) {
    	    	mapping.remap(IEBlocks.StoneDecoration.SLAG_BRICK.get());
    	    }
    	    if(mapping.getKey().getPath().equals("slag_brick_slab")) {
    	    	mapping.remap(IEBlocks.TO_SLAB.get(IEBlocks.StoneDecoration.SLAG_BRICK.getId()).get());
    	    }
    	    if(mapping.getKey().getPath().equals("slag_brick_stairs")) {
    	    	mapping.remap(IEBlocks.TO_STAIRS.get(IEBlocks.StoneDecoration.SLAG_BRICK.getId()).get());
    	    }
    	    if(mapping.getKey().getPath().equals("clinker_brick_block")) {
    	    	mapping.remap(IEBlocks.StoneDecoration.CLINKER_BRICK.get());
    	    }
    	    if(mapping.getKey().getPath().equals("clinker_brick_slab")) {
    	    	mapping.remap(IEBlocks.TO_SLAB.get(IEBlocks.StoneDecoration.CLINKER_BRICK.getId()).get());
    	    }
    	    if(mapping.getKey().getPath().equals("clinker_brick_stairs")) {
    	    	mapping.remap(IEBlocks.TO_STAIRS.get(IEBlocks.StoneDecoration.CLINKER_BRICK.getId()).get());
    	    }
    	    if(mapping.getKey().getPath().equals("clinker_brick_sastor_corner_block")) {
    	    	mapping.remap(IEBlocks.StoneDecoration.CLINKER_BRICK_QUOIN.get());
    	    }
    	}
    	// For items
    	for (MissingMappingsEvent.Mapping<Item> mapping : event.getMappings(ForgeRegistries.Keys.ITEMS, ModEngineersDecor.MODID)) {
    		if(mapping.getKey().getPath().equals("slag_brick_block")) {
    	    	mapping.remap(IEBlocks.StoneDecoration.SLAG_BRICK.asItem());
    	    }
    		if(mapping.getKey().getPath().equals("slag_brick_slab")) {
    	    	mapping.remap(IEBlocks.TO_SLAB.get(IEBlocks.StoneDecoration.SLAG_BRICK.getId()).asItem());
    	    }
    	    if(mapping.getKey().getPath().equals("slag_brick_stairs")) {
    	    	mapping.remap(IEBlocks.TO_STAIRS.get(IEBlocks.StoneDecoration.SLAG_BRICK.getId()).asItem());
    	    }
    	    if(mapping.getKey().getPath().equals("clinker_brick_block")) {
    	    	mapping.remap(IEBlocks.StoneDecoration.CLINKER_BRICK.asItem());
    	    }
    	    if(mapping.getKey().getPath().equals("clinker_brick_slab")) {
    	    	mapping.remap(IEBlocks.TO_SLAB.get(IEBlocks.StoneDecoration.CLINKER_BRICK.getId()).asItem());
    	    }
    	    if(mapping.getKey().getPath().equals("clinker_brick_stairs")) {
    	    	mapping.remap(IEBlocks.TO_STAIRS.get(IEBlocks.StoneDecoration.CLINKER_BRICK.getId()).asItem());
    	    }
    	    if(mapping.getKey().getPath().equals("clinker_brick_sastor_corner_block")) {
    	    	mapping.remap(IEBlocks.StoneDecoration.CLINKER_BRICK_QUOIN.asItem());
    	    }
    	}
	}
}