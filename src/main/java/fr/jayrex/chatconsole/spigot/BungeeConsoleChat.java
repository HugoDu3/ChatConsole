package fr.jayrex.chatconsole.spigot;

import fr.jayrex.chatconsole.spigot.events.Events;
import fr.jayrex.chatconsole.spigot.message.BungeeMessage;
import org.bukkit.plugin.java.JavaPlugin;

public class BungeeConsoleChat extends JavaPlugin {
	@Override
	public void onEnable() {
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

		BungeeMessage bungeeMessage = new BungeeMessage(this);

		getServer().getPluginManager().registerEvents(new Events(bungeeMessage), this);
	}
}