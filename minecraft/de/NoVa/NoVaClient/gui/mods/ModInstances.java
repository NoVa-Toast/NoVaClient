package de.NoVa.NoVaClient.gui.mods;

import de.NoVa.NoVaClient.gui.hud.HUDManager;
import de.NoVa.NoVaClient.gui.mods.imp.XYZ;
import de.NoVa.NoVaClient.gui.mods.imp.keystrokes.Keystrokes;
import de.NoVa.NoVaClient.gui.mods.imp.FPS;
import de.NoVa.NoVaClient.gui.mods.imp.SprintToggle;

public class ModInstances {
	private static FPS modFPS;
	private static XYZ modXYZ;
	private static Keystrokes modKeystrokes;
	private static SprintToggle modSprintToggle;
	
	public static void register(HUDManager api) {
		modFPS = new FPS();
		modXYZ = new XYZ();
		modKeystrokes = new Keystrokes();
		modSprintToggle = new SprintToggle();
		
		api.register(modFPS);
		api.register(modXYZ);
		api.register(modKeystrokes);
		api.register(modSprintToggle);
	}
	
	public static FPS getModFPS() { return modFPS; }
	public static XYZ getModXYZ() { return modXYZ; }
	public static Keystrokes getModKeystrokes() { return modKeystrokes; }
	public static SprintToggle getModSprintToggle() { return modSprintToggle; }
}
