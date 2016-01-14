package covers1624.cpp.gui;

import covers1624.lib.gui.DoneButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.StatCollector;

/**
 * Created by covers1624 on 1/6/2016.
 */
public class GuiPotionConfig extends GuiScreen {
	private Minecraft minecraft;
	private GuiScreen parentScreen;
	private GuiPotionConfigList configList;
	private int prevWidth;
	private int prevHeight;

	public GuiPotionConfig(GuiScreen parent) {
		minecraft = Minecraft.getMinecraft();
		parentScreen = parent;
		configList = new GuiPotionConfigList(this, minecraft);
		checkWindowSize();
	}

	@Override
	@SuppressWarnings("unchecked")
	public void initGui() {
		buttonList.add(new DoneButton(-1, width / 2/* - 155*/, height - 29, 150, 20, parentScreen));
	}

	@Override
	protected void actionPerformed(GuiButton button) {
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseEvent) {
		if (mouseEvent != 0 || !configList.func_148179_a(mouseX, mouseY, mouseEvent)) {
			super.mouseClicked(mouseX, mouseY, mouseEvent);
		}
	}

	@Override
	protected void mouseMovedOrUp(int mouseX, int mouseY, int mouseEvent) {
		if (mouseEvent != 0 || !configList.func_148181_b(mouseX, mouseY, mouseEvent)) {
			super.mouseClicked(mouseX, mouseY, mouseEvent);
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		checkWindowSize();
		drawDefaultBackground();
		configList.drawScreen(mouseX, mouseY, partialTicks);
		drawCenteredString(fontRendererObj, StatCollector.translateToLocal("gui.potionParticleConfig.name"), width / 2, 8, 16777215);

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	private void checkWindowSize() {
		if (prevHeight != minecraft.displayHeight || prevWidth != minecraft.displayWidth) {
			prevHeight = minecraft.displayHeight;
			prevWidth = minecraft.displayWidth;
			ScaledResolution scaledResolution = new ScaledResolution(minecraft, minecraft.displayWidth, minecraft.displayHeight);
			setWorldAndResolution(minecraft, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight());
			configList.setWindowSize(scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight());
		}
	}
}
