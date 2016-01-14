package covers1624.cpp.gui;

import covers1624.cpp.handler.PotionHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.StatCollector;

/**
 * Created by covers1624 on 1/10/2016.
 */
public class PotionStateButton extends GuiButton{
	private int potionId;
	public PotionStateButton(int id, int xPos, int yPos, int width, int height, int potionId) {
		super(id,xPos,yPos,width,height, "");
		this.potionId = potionId;
		updateButtonText();
	}

	@Override
	public boolean mousePressed(Minecraft minecraft, int mouseX, int mouseY) {
		if (super.mousePressed(minecraft, mouseX, mouseY)){
			PotionHandler.instance.setPotionState(potionId, !PotionHandler.instance.getPotionState(potionId));
			updateButtonText();
			return true;
		}
		return false;
	}

	private void updateButtonText(){
		this.displayString = StatCollector.translateToLocal(PotionHandler.instance.getPotionState(potionId) ? "button.enabled" : "button.disabled");
	}
}
