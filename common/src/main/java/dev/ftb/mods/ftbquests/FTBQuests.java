package dev.ftb.mods.ftbquests;

import dev.ftb.mods.ftbquests.client.FTBQuestsClient;
import dev.ftb.mods.ftbquests.client.FTBQuestsNetClient;
import dev.ftb.mods.ftbquests.command.ChangeProgressArgument;
import dev.ftb.mods.ftbquests.command.QuestObjectArgument;
import dev.ftb.mods.ftbquests.integration.kubejs.KubeJSIntegration;
import dev.ftb.mods.ftbquests.item.FTBQuestsItems;
import dev.ftb.mods.ftbquests.net.FTBQuestsNetHandler;
import dev.ftb.mods.ftbquests.quest.reward.RewardTypes;
import dev.ftb.mods.ftbquests.quest.task.TaskTypes;
import me.shedaniel.architectury.platform.Platform;
import me.shedaniel.architectury.registry.CreativeTabs;
import me.shedaniel.architectury.utils.EnvExecutor;
import net.minecraft.commands.synchronization.ArgumentTypes;
import net.minecraft.commands.synchronization.EmptyArgumentSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FTBQuests {
	public static final String MOD_ID = "ftbquests";
	public static final Logger LOGGER = LogManager.getLogger("FTB Quests");

	public static FTBQuests instance;

	public static FTBQuestsCommon PROXY;
	public static FTBQuestsNetCommon NET_PROXY;

	public static final CreativeModeTab ITEM_GROUP = CreativeTabs.create(new ResourceLocation(FTBQuests.MOD_ID, FTBQuests.MOD_ID), () -> new ItemStack(FTBQuestsItems.BOOK.get()));

	public FTBQuests() {
		TaskTypes.init();
		RewardTypes.init();
		FTBQuestsNetHandler.init();
		PROXY = EnvExecutor.getEnvSpecific(() -> FTBQuestsClient::new, () -> FTBQuestsCommon::new);
		NET_PROXY = EnvExecutor.getEnvSpecific(() -> FTBQuestsNetClient::new, () -> FTBQuestsNetCommon::new);
		new FTBQuestsEventHandler().init();

		if (Platform.isModLoaded("kubejs")) {
			KubeJSIntegration.init();
		}

		PROXY.init();
	}

	public void setup() {
		ArgumentTypes.register("ftbquests:change_progress", ChangeProgressArgument.class, new EmptyArgumentSerializer<>(ChangeProgressArgument::changeProgress));
		ArgumentTypes.register("ftbquests:quest_object", QuestObjectArgument.class, new EmptyArgumentSerializer<>(QuestObjectArgument::new));
	}
}