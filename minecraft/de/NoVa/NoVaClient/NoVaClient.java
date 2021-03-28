package de.NoVa.NoVaClient;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.NoVa.NoVaClient.events.EventManager;
import de.NoVa.NoVaClient.events.EventTarget;
import de.NoVa.NoVaClient.events.imp.ClientTickEvent;
import de.NoVa.NoVaClient.gui.hud.HUDManager;
import de.NoVa.NoVaClient.gui.mods.ModInstances;
import de.NoVa.NoVaClient.util.ExceptionHandler;
import de.NoVa.NoVaClient.util.FileManager;
import de.NoVa.NoVaClient.util.FileUtils;
import de.NoVa.NoVaClient.util.MedaSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.util.ResourceLocation;

/**
 * Meda Client 0.7 [BETA]
 * Copyright (c) 2020 Felix Eckert.
 * */
public class NoVaClient {
	private static final Logger logger = LogManager.getLogger();
	private static NoVaClient INSTANZ;
	private static HUDManager modReg;
	private static Properties  config;
	
	private static DiscordIntegration discordInt;
	
	public NoVaClient() {
		this.INSTANZ = this;
	}
	
	public void init() {
		logger.info("Initialising NoVaClient");
		logger.info("Version "+Reference.version + " build " + Reference.build + " " + Reference.stage + " stage patch "+Reference.patch);
		
		logger.info("Initialising ExceptionHandler");
		List<String> exceptionFlags = ExceptionHandler.getHandler().getFlags();
		exceptionFlags.remove("reportAll");
		exceptionFlags.add("reportIOE");
		exceptionFlags.add("reportIOEAsMsgBox");
		exceptionFlags.add("reportIOEToConsole");
		exceptionFlags.add("reportNPE");
		exceptionFlags.add("reportNPEToConsole");
		exceptionFlags.add("reportNPEAsMsgBox");
		
		// Konfiguration Laden
		logger.info("Loading Configs...");
		try {
			config = FileUtils.readPropertiesFile(Minecraft.getMinecraft().mcDataDir.getAbsolutePath()+"/medaconfig.properties");
		} catch (IOException e) {
			ExceptionHandler.getHandler().reportException(e, "Could not load Config: IOException");
		}
		logger.info("Loaded medaconfig properties file!");
		logger.info("Loading Mod Configs...");
		FileManager.init();
		logger.info("Loaded configs!");
		logger.info("Initializing DiscordRPC");
		discordInt = new DiscordIntegration();
		discordInt.Start();
		logger.info("Finished Initializing DiscordRPC");
		
		EventManager.register(this);
		
		postInit();
	}
	
	public static void postInit() {}

	public static void start() {
		modReg = HUDManager.getInstance();
		ModInstances.register(modReg);
	}
	
	public static void shutdown() {
		logger.info("Shutting down NoVaClient...");
		if (discordInt != null)
			discordInt.shutdown();
		
		MedaSettings.getSettings().saveSettingsToFile();
		logger.info("NoVaClient shutdown!");
	}
	
	public void update() {
		// Update all mods (Also renders them if they're graphical)
		//modReg.updateMods();
	}
	
	// Getter Methods
	public static Logger getLogger() { return logger; }	
	public NoVaClient getNoVaClient() { return INSTANZ; }
	public static Properties getConfig() { return config; }
	public DiscordIntegration getDiscordInt() { return discordInt; }
	
	/**
	 * Handle Client Updates
	 * */
	@EventTarget
	public void onTick(ClientTickEvent e) {
		ModInstances.getModSprintToggle().update();
		if (Minecraft.getMinecraft().gameSettings.MEDA_GUI_MOD_POS.isPressed()) {
			modReg.openConfigScreen();
		} else if (Minecraft.getMinecraft().gameSettings.MEDA_RELOAD_SETTINGS.isPressed()) {
			MedaSettings.getSettings().loadSettings();
		}
	}
	
	public static class Reference {
		// Reference Variables
		// Version Vars
		public static final String version   = "0.7";
		public static final String patch     = "1";
		public static final String stage     = "beta";
		public static final String build     = "1";
		public static final String mcVersion = "1.8.8";
		
		// Resource Directory
		public static final String resources = Minecraft.getMinecraft().mcDataDir.getAbsolutePath()+"/medaResources";
		
		// Info Vars
		public static final String developer = "Felix Eckert";
		public static final String name      = "Meda Client";
		public static final String license   = "3 Clause BSD";
	}
}
