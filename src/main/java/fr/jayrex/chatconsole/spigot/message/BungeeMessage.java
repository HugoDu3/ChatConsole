package fr.jayrex.chatconsole.spigot.message;

import fr.jayrex.chatconsole.spigot.BungeeConsoleChat;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class BungeeMessage {
	private BungeeConsoleChat plugin;

	public BungeeMessage(BungeeConsoleChat plugin) {
		this.plugin = plugin;
	}

	public void sendChatBungee(Player player, String message) {
		ByteArrayDataOutput byteArrayDataOutput = ByteStreams.newDataOutput();

		byteArrayDataOutput.writeUTF("ConsoleChat");
		byteArrayDataOutput.writeUTF("Message");

		byteArrayDataOutput.writeUTF(message);
		byteArrayDataOutput.writeUTF(player.getName());

		player.sendPluginMessage(plugin, "BungeeCord", byteArrayDataOutput.toByteArray());
	}
}
