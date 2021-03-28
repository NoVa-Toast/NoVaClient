package de.NoVa.NoVaClient.hud.copy;

import java.util.Collection;
import java.util.Set;

import com.google.common.collect.Sets;

import de.NoVa.NoVaClient.events.EventManager;
import de.NoVa.NoVaClient.events.EventTarget;
import de.NoVa.NoVaClient.events.imp.RenderEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiContainer;

public class HUDManager {
	private Set<IRender> registeredRenderers = Sets.newHashSet();
	private Minecraft mc = Minecraft.getMinecraft();

	private static HUDManager INSTANZ = null;
	
	private HUDManager() {}
	
	/**
	 * Returns an instance of the HUDManager
	 * */
	public static HUDManager getInstance() {
		if (INSTANZ != null) {
			return INSTANZ;
		}
		
		INSTANZ = new HUDManager();
		EventManager.register(INSTANZ);
		return INSTANZ;
	}

	public void register(IRender... renders) {
		for (IRender render : renders) {
			this.registeredRenderers.add(render);
		}
	}
	
	public void unregister(IRender... renders) {
		for (IRender render : renders) {
			this.registeredRenderers.remove(render);
		}
	}
	
	public Collection<IRender> getRegisteredRenderers() {
		return Sets.newHashSet(registeredRenderers);
	}
	
	public void openConfigScreen() {
		mc.displayGuiScreen(new HUDConfigScreen(this));
	}
	
	@EventTarget
	public void onRender(RenderEvent e) {
		if (mc.currentScreen == null || mc.currentScreen instanceof GuiContainer || mc.currentScreen instanceof GuiChat) {
			for (IRender render : registeredRenderers) {
				callRender(render);
			}
		}
	}

	private void callRender(IRender render) {
		if (!render.isEnabled()) {
			return;
		}
		
		ScreenPosition pos = render.load();
		
		if (pos == null) {
			pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
		}
		
		render.render(pos);
	}
}
