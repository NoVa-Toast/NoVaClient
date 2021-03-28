package de.NoVa.NoVaClient.gui.mods.imp.keystrokes;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import de.NoVa.NoVaClient.gui.hud.ScreenPosition;
import de.NoVa.NoVaClient.gui.mods.ModDraggable;
import de.NoVa.NoVaClient.util.NoVaSettings;
import net.minecraft.client.gui.Gui;

public class Keystrokes extends ModDraggable {
	private ScreenPosition pos = NoVaSettings.getSettings().getScreenPosOf("keyStrokes");
	private KeystrokesMode mode = KeystrokesMode.getMode(NoVaSettings.getSettings().getModParam("keyStrokes", "mode"));
	private int notPressed = NoVaSettings.getSettings().getColorOf("keyStrokes", "box_normalColor");
	private int pressed = NoVaSettings.getSettings().getColorOf("keyStrokes", "box_pressedColor");
	
	public Keystrokes() {
		this.name = "keyStrokes";
	}
	
	@Override
	public int getWidth() {
		return mode.getWidth();
	}

	@Override
	public int getHeight() {
		return mode.getHeight();
	}

	@Override
	public void render(ScreenPosition pos) {
		GL11.glPushMatrix();
			for (Key key : mode.getKeys()) {
				int textWidth = fr.getStringWidth(key.getName());
				
				// Drawing the Rect containing the String
				Gui.drawRect(
						pos.getAbsoluteX() + key.getX(),
						pos.getAbsoluteY() + key.getY(), 
						pos.getAbsoluteX() + key.getX() + key.getWidth(), 
						pos.getAbsoluteY() + key.getY() + key.getHeight(), 
						key.isDown() ? NoVaSettings.getSettings().getColorOf("keyStrokes", "box_pressedColor") : NoVaSettings.getSettings().getColorOf("keyStrokes", "box_normalColor")
				);
				
				// Drawing the String
				fr.drawString(
						key.getName(), 
						pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2, 
						pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 - 4, 
						key.isDown() ? NoVaSettings.getSettings().getColorOf("keyStrokes", "text_pressedColor") : NoVaSettings.getSettings().getColorOf("keyStrokes", "text_normalColor")
				);
			}
		GL11.glPopMatrix();
	}

	@Override
	public void save(ScreenPosition pos) {
		this.pos = pos;
		NoVaSettings.getSettings().setScreenPosOf("keyStrokes", pos);
	}

	@Override
	public ScreenPosition load() {
		return pos;
	}
}
