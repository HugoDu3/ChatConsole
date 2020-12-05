package fr.jayrex.chatconsole.bungee;

import fr.jayrex.chatconsole.bungee.listener.ChannelListener;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeConsoleChat extends Plugin {
	@Override
	public void onEnable() {
		getProxy().getPluginManager().registerListener(this, new ChannelListener(this));
	}
}
