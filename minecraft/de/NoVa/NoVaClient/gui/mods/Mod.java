package de.NoVa.NoVaClient.gui.mods;

import org.lwjgl.input.Keyboard;

import de.NoVa.NoVaClient.NoVaClient;
import de.NoVa.NoVaClient.events.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Mod {
	private boolean isEnabled = true;
	protected final Minecraft mc;
	protected final FontRenderer fr;
	protected final NoVaClient client;
	protected String name = "";
	
	private boolean pressed = false, toggled = false;
	
	public Mod() {
		this.mc = Minecraft.getMinecraft();
		this.fr = this.mc.fontRendererObj;
		this.client = this.mc.getClient();
		
		setEnabled(isEnabled);
	}

	public void update() {
	}
	
	private void setEnabled(boolean b) {
		this.isEnabled = b;
		
		if (isEnabled) {
			EventManager.register(this);
		} else {
			EventManager.unregister(this);
		}
	}
	
	private boolean getEnabled() {
		return isEnabled;
	}
}
