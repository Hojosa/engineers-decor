package wile.engineersdecor.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wile.engineersdecor.ModEngineersDecor;
import wile.engineersdecor.datagen.providers.EdBlockTags;
import wile.engineersdecor.datagen.providers.EdItemTags;

@Mod.EventBusSubscriber(modid = ModEngineersDecor.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EdDataGenerators {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		PackOutput packOutput = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		
		EdBlockTags blockTags = new EdBlockTags(packOutput, lookupProvider, existingFileHelper);
		generator.addProvider(event.includeServer(), blockTags);
		generator.addProvider(event.includeServer(), new EdItemTags(packOutput, lookupProvider, blockTags, existingFileHelper));
	}
}
