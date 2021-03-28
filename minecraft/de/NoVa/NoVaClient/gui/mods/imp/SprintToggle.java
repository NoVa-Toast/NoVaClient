package de.NoVa.NoVaClient.gui.mods.imp;

import org.lwjgl.input.Keyboard;

import de.NoVa.NoVaClient.gui.hud.ScreenPosition;
import de.NoVa.NoVaClient.gui.mods.ModDraggable;
import de.NoVa.NoVaClient.util.MedaSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class SprintToggle extends ModDraggable {
	private ScreenPosition pos = NoVaSettings.getSettings().getScreenPosOf("sprintToggle");
	
	boolean toggled = false, pressed = false;
	
	public SprintToggle() {
		this.name = "sprintToggle";
	}
	
	@Override
	public int getWidth() {
		FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
		String toggledState = toggled ? "Aktiv" : "Inaktiv"; 
		return fr.getStringWidth("[Sprint Toggle ("+toggledState+")]");
	}

	@Override
	public int getHeight() {
		FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
		return fr.FONT_HEIGHT;
	}
	
	@Override
	public void update() {
		if (!NoVaSettings.getSettings().getModEnabled("sprintToggle"))
			return;
		
		// Update Sprint Toggle Variable
		if (Minecraft.getMinecraft().gameSettings.NOVA_SPRINT_TOGGLE.isPressed()) {
			pressed = true;
			if (toggled == false) {
				toggled = true;
			} else {
				toggled = false;
			}
		}	
		if (!Minecraft.getMinecraft().gameSettings.NOVA_SPRINT_TOGGLE.isPressed() && pressed == true && pressed == false) {
			pressed = false;
		}
		
		// Change Sprintstate
		Minecraft.getMinecraft().thePlayer.setSprinting(toggled);
	}

	@Override
	public void render(ScreenPosition pos) {
		FontRenderer fr = Minecraft.getMinecraft().fontRendererObj; 
		String toggledState = toggled ? "Aktiv" : "Inaktiv";
		
		fr.drawString("[Sprint Toggle ("+toggledState+")]", pos.getAbsoluteX(), pos.getAbsoluteY(), NoVaSettings.getSettings().getColorOf("sprintToggle", "color"));
	}

	@Override
	public void save(ScreenPosition pos) {
		this.pos = pos;
	}

	@Override
	public ScreenPosition load() {
		return pos;
	}
	
	@Override
	public boolean isEnabled() {
		return NoVaSettings.getSettings().getModEnabled(name);
	}
}
