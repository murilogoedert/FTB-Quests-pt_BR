package dev.ftb.mods.ftbquests.net;

import dev.ftb.mods.ftbquests.FTBQuests;
import me.shedaniel.architectury.networking.NetworkManager;
import me.shedaniel.architectury.networking.simple.BaseS2CMessage;
import me.shedaniel.architectury.networking.simple.MessageType;
import net.minecraft.network.FriendlyByteBuf;

import java.util.UUID;

/**
 * @author LatvianModder
 */
public class ObjectStartedResetMessage extends BaseS2CMessage {
	private final UUID team;
	private final long id;

	public ObjectStartedResetMessage(FriendlyByteBuf buffer) {
		team = buffer.readUUID();
		id = buffer.readLong();
	}

	public ObjectStartedResetMessage(UUID t, long i) {
		team = t;
		id = i;
	}

	@Override
	public MessageType getType() {
		return FTBQuestsNetHandler.OBJECT_STARTED_RESET;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeUUID(team);
		buffer.writeLong(id);
	}

	@Override
	public void handle(NetworkManager.PacketContext context) {
		FTBQuests.NET_PROXY.objectStarted(team, id, null);
	}
}