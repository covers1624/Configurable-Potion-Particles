package covers1624.lib.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

/**
 * Created by covers1624 on 1/10/2016.
 */
public class DoneButton extends GuiButton {
	private GuiScreen backScreen;
	public DoneButton(int id, int xPos, int yPos, int width, int height, GuiScreen back) {
		super(id, xPos, yPos, width, height, I18n.format("gui.done"));
		backScreen = back;
	}

	@Override
	public boolean mousePressed(Minecraft minecraft, int mouseX, int mouseY) {
		if (super.mousePressed(minecraft,mouseX,mouseY)){
			minecraft.displayGuiScreen(backScreen);
			return true;
		}
		return false;
	}

	public void setBackScreen(GuiScreen screen){
		backScreen = screen;
	}
}
