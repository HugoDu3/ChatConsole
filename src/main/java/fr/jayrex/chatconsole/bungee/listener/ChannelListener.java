package fr.jayrex.chatconsole.bungee.listener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.bukkit.entity.Player;

import fr.jayrex.chatconsole.bungee.BungeeConsoleChat;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import fr.jayrex.chatconsole.spigot.utils.*;

public class ChannelListener implements Listener {
	private BungeeConsoleChat plugin;
	
	private LuckPerms luckapi = LuckPermsProvider.get();

	public ChannelListener(BungeeConsoleChat plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPluginMessage(PluginMessageEvent event) throws IOException {
		if (!event.getTag().equals("BungeeCord")) {
			return;
		}

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(event.getData());
		DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);

		String name = dataInputStream.readUTF();

		if (!name.equals("ConsoleChat")) {
			return;
		}

		if (!(event.getSender() instanceof Server)) {
			return;
		}

		String method = dataInputStream.readUTF();
		String message = dataInputStream.readUTF();
		String user = dataInputStream.readUTF();

		if (method.equals("Message")) {
			ProxiedPlayer player = plugin.getProxy().getPlayer(user);
			plugin.getProxy().getLogger()
					.info("[" + player.getServer().getInfo().getName() + "] " + getPrefix(player) + player.getName() + ": " + message);
		}
	}
	
	private User loadUser(ProxiedPlayer player) {
		return luckapi.getUserManager().getUser(player.getUniqueId());
	}
	
	private CachedMetaData playerMeta(ProxiedPlayer player) {
		return loadUser(player).getCachedData().getMetaData(luckapi.getContextManager().getQueryOptions(player));
	}
	
	private String getPrefix(ProxiedPlayer player) {
		String prefix = playerMeta(player).getPrefix();
		return (prefix != null) ? prefix : "";
	}

}
