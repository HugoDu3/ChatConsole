package fr.jayrex.chatconsole.spigot.events;

import fr.jayrex.chatconsole.spigot.message.BungeeMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Events implements Listener {
	private BungeeMessage bungeeMessage;

	public Events(BungeeMessage bungeeMessage) {
		this.bungeeMessage = bungeeMessage;
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		bungeeMessage.sendChatBungee(event.getPlayer(), event.getMessage());
	}
}
