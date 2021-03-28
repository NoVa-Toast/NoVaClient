package de.NoVa.NoVaClient.gui.mods.imp;

import de.NoVa.NoVaClient.gui.hud.ScreenPosition;
import de.NoVa.NoVaClient.gui.mods.ModDraggable;
import de.NoVa.NoVaClient.util.NoVaSettings;

public class ModTest extends ModDraggable {
	private ScreenPosition pos;
	private String text = "Hello World (Dummy)";
	
	@Override
	public int getWidth() {
		return fr.getStringWidth(text);
	}

	@Override
	public int getHeight() {
		return fr.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		fr.drawString(text, pos.getAbsoluteX()+1, pos.getAbsoluteY(), -1);
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		fr.drawString(text, pos.getAbsoluteX()+1, pos.getAbsoluteY(), 0xFF00FF00);
	}

	@Override
	public void save(ScreenPosition pos) {
		this.pos = pos;
	}

	@Override
	public ScreenPosition load() {
		return this.pos;
	}

	@Override
	public boolean isEnabled() {
		return NoVaSettings.getSettings().getModEnabled(name);
	}
}
