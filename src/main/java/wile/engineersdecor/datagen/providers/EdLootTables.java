package wile.engineersdecor.datagen.providers;

import java.util.Map;
import java.util.stream.Collectors;

import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.DynamicLoot;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.functions.SetContainerContents;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.ForgeRegistries;
import wile.engineersdecor.ModContent;
import wile.engineersdecor.ModEngineersDecor;
import wile.engineersdecor.libmc.StandardDoorBlock;

public class EdLootTables extends VanillaBlockLoot {

	@Override
	protected void generate() {
		getKnownBlocks().forEach(entry -> {
			if (entry instanceof StandardDoorBlock) {
				add(entry, createDoorTable(entry));
			} else if (!(entry instanceof EntityBlock)) {
				dropSelf(entry);
			}

		});
		// BEs that dont save nbt data
		dropSelf(ModContent.getBlock("small_freezer"));
		dropSelf(ModContent.getBlock("small_mineral_smelter"));
		dropSelf(ModContent.getBlock("test_block"));
		dropSelf(ModContent.getBlock("small_block_breaker"));
		dropSelf(ModContent.getBlock("small_solar_panel"));
		dropSelf(ModContent.getBlock("small_milking_machine"));
		dropSelf(ModContent.getBlock("small_tree_cutter"));
		dropSelf(ModContent.getBlock("straight_pipe_valve"));
		dropSelf(ModContent.getBlock("straight_pipe_valve_redstone"));
		dropSelf(ModContent.getBlock("straight_pipe_valve_redstone_analog"));

		// BEs where we save the nbt data on drop
		createStandardTable(ModContent.getBlock("small_lab_furnace"),
				ModContent.getBlockEntityTypeOfBlock("small_lab_furnace"), "Items", "BurnTime", "CookTime", "CookTimeTotal", "FuelBurnTime", "XpStored", "Energy");

//		todo:
//		"inventory" tag group (smelting inventory):
//		- small_lab_furnace
//		- small_electrical_furnace
//
//		 "tedata" tag group (stored items/filter/fluid settings):
//		 - factory_dropper
//		 - factory_placer
//		 - factory_hopper
//		 - small_waste_incinerator
//		 - fluid_barrel
//		 - small_fluid_funnel
		// TODO: temporary placeholders below, still have live dropList() overrides
		dropSelf(ModContent.getBlock("small_electrical_furnace"));
		dropSelf(ModContent.getBlock("factory_dropper"));
		dropSelf(ModContent.getBlock("factory_placer"));
		dropSelf(ModContent.getBlock("factory_hopper"));
		dropSelf(ModContent.getBlock("small_waste_incinerator"));
		dropSelf(ModContent.getBlock("fluid_barrel"));
		dropSelf(ModContent.getBlock("small_fluid_funnel"));

	}

	private void createStandardTable(Block block, BlockEntityType<?> type, String... tags) {
		LootPoolSingletonContainer.Builder<?> lti = LootItem.lootTableItem(block);
		lti.apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY));
		for (String tag : tags) {
			lti.apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy(tag, "BlockEntityTag." + tag,
					CopyNbtFunction.MergeStrategy.REPLACE));
		}
		//lti.apply(SetContainerContents.setContents(type)
				//.withEntry(DynamicLoot.dynamicEntry(new ResourceLocation("minecraft", "contents"))));

		LootPool.Builder builder = LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(lti);
		add(block, LootTable.lootTable().withPool(builder));
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return ForgeRegistries.BLOCKS.getEntries().stream()
				.filter(e -> e.getKey().location().getNamespace().equals(ModEngineersDecor.MODID))
				.map(Map.Entry::getValue).collect(Collectors.toList());
	}
}