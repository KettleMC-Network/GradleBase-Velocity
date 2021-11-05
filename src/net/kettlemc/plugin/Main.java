package net.kettlemc.plugin;

import static net.kettlemc.plugin.utils.PluginConstants.DESCRIPTION;
import static net.kettlemc.plugin.utils.PluginConstants.ID;
import static net.kettlemc.plugin.utils.PluginConstants.NAME;
import static net.kettlemc.plugin.utils.PluginConstants.URL;
import static net.kettlemc.plugin.utils.PluginConstants.VERSION;

import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;

import java.nio.file.Path;
import java.util.logging.Logger;

@Plugin(id = ID, name = NAME, version = VERSION, url = URL, description = DESCRIPTION, authors = { "LeStegii" })
public class Main {
	
	private final ProxyServer server;
	private final Logger logger;
	private final Path dataDirectory;
	private final CommandManager commandManager;
	
	@Inject
	public Main(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
		this.server = server;
		this.logger = logger;
		this.dataDirectory = dataDirectory;
		this.commandManager = server.getCommandManager();
	}
	
	@Subscribe
	public void onProxyInitialization(ProxyInitializeEvent event) {
		logger.info("Initialized Plugin. Running on " + server.getVersion().getName());
	}
	
	public ProxyServer getProxyServer() {
		return this.server;
	}

	public Logger getLogger() {
		return this.logger;
	}

	public Path getDataDirectory() {
		return this.dataDirectory;
	}

	private CommandMeta getCommandMeta(String name, String... aliases) {
		return commandManager.metaBuilder(name).aliases(aliases).build();
	}

	private CommandMeta getCommandMeta(String name) {
		return commandManager.metaBuilder(name).build();
	}
}