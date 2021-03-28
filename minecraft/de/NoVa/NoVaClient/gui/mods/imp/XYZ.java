package de.NoVa.NoVaClient.gui.mods.imp;

import de.NoVa.NoVaClient.gui.hud.ScreenPosition;
import de.NoVa.NoVaClient.gui.mods.ModDraggable;
import de.NoVa.NoVaClient.util.MedaSettings;

public class XYZ extends ModDraggable {
	private ScreenPosition pos = NoVaSettings.getSettings().getScreenPosOf(name);
	
	public XYZ() {
		this.name = "xyz";
	}
	
	@Override
	public int getWidth() {
		return fr.getStringWidth("X: "+(int)mc.thePlayer.posX+" Y: "+(int)mc.thePlayer.posY+" Z: "+(int)mc.thePlayer.posZ);
	}

	@Override
	public int getHeight() {
		return fr.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		int x = (int)mc.thePlayer.posX;
		int y = (int)mc.thePlayer.posY;
		int z = (int)mc.thePlayer.posZ;
		
		fr.drawString("X: ", pos.getAbsoluteX()+1, pos.getAbsoluteY(), NoVaSettings.getSettings().getColorOf(name, "baseColor"));
		fr.drawString(String.valueOf(x), pos.getAbsoluteX()+fr.getStringWidth("X: "), pos.getAbsoluteY(), NoVaSettings.getSettings().getColorOf(name, "valueColor"));
		fr.drawString(" Y: ", pos.getAbsoluteX()+fr.getStringWidth("X: "+x), pos.getAbsoluteY(), NoVaSettings.getSettings().getColorOf(name, "baseColor"));
		fr.drawString(String.valueOf(y), pos.getAbsoluteX()+fr.getStringWidth("X: "+x+" Y: "), pos.getAbsoluteY(), NoVaSettings.getSettings().getColorOf(name, "valueColor"));
		fr.drawString(" Z: ", pos.getAbsoluteX()+fr.getStringWidth("X: "+x+" Y: "+y), pos.getAbsoluteY(), NoVaSettings.getSettings().getColorOf(name, "baseColor"));
		fr.drawString(String.valueOf(z), pos.getAbsoluteX()+fr.getStringWidth("X: "+x+"Y: "+y+" Z: "), pos.getAbsoluteY(), NoVaSettings.getSettings().getColorOf(name, "valueColor"));
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		fr.drawString("X: "+(int)mc.thePlayer.posX+" Y: "+(int)mc.thePlayer.posY+" Z: "+(int)mc.thePlayer.posZ, pos.getAbsoluteX()+1, pos.getAbsoluteY(), 0xFF00FF00);
	}

	@Override
	public void save(ScreenPosition pos) {
		this.pos = pos;
		NoVaSettings.getSettings().setScreenPosOf(name, pos);
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
