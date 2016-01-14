package covers1624.cpp.gui;

import covers1624.cpp.handler.PotionHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;

import java.util.ArrayList;

/**
 * Created by covers1624 on 1/9/2016.
 */
public class GuiPotionConfigList extends GuiListExtended {

	private final ArrayList<IGuiListEntry> entries;
	private final Minecraft mc;
	//This is set by the max string width of a localized potion name;
	private int buttonOffset = 0;

	public GuiPotionConfigList(GuiPotionConfig potionGui, Minecraft minecraft) {
		super(minecraft, potionGui.width, potionGui.height, 63, potionGui.height - 32, 20);
		mc = minecraft;
		entries = new ArrayList<IGuiListEntry>();

		for (Potion potion : Potion.potionTypes) {
			if (potion == null) {
				continue;
			}
			entries.add(new PotionEntry(potion));

			int length = minecraft.fontRenderer.getStringWidth(I18n.format(potion.getName()));

			if (length > buttonOffset) {
				buttonOffset = length;
			}
		}
	}

	public void setWindowSize(int width, int height) {
		this.width = width;
		this.right = width;
		this.height = height;
		this.bottom = height - 32;
	}

	@Override
	public IGuiListEntry getListEntry(int index) {
		return entries.get(index);
	}

	@Override
	protected int getScrollBarX() {
		return super.getScrollBarX() + 15;
	}

	@Override
	public int getListWidth() {
		return super.getListWidth() + 32;
	}

	@Override
	protected int getSize() {
		return entries.size();
	}

	@Override
	public void drawScreen(int p_148128_1_, int p_148128_2_, float p_148128_3_) {
		//LogHelper.info("Drawing PotionList.");
		super.drawScreen(p_148128_1_, p_148128_2_, p_148128_3_);
	}

	public class PotionEntry implements IGuiListEntry {

		private final int potionID;
		private final PotionStateButton button;
		private String buttonDescriptor;

		private PotionEntry(Potion potionEffect) {
			potionID = potionEffect.getId();
			button = new PotionStateButton(0, 0, 0, 75, 18, potionID);
			buttonDescriptor = I18n.format(potionEffect.getName());
		}

		@Override
		public void drawEntry(int index, int x, int y, int listWidth, int listHeight, Tessellator tessellator, int mouseX, int mouseY, boolean isSelected) {
			mc.fontRenderer.drawString(buttonDescriptor, x + 90 - buttonOffset, y + listHeight / 2 - mc.fontRenderer.FONT_HEIGHT / 2, 16777215);
			button.xPosition = x + 105;
			button.yPosition = y;
			button.drawButton(mc, mouseX, mouseY);
		}

		@Override
		public boolean mousePressed(int index, int x, int y, int mouseEvent, int relativeX, int relativeY) {
			if (button.mousePressed(mc, x, y)){
				button.func_146113_a(mc.getSoundHandler());
				PotionHandler.instance.save();
				return true;
			}
			return false;
		}

		@Override
		public void mouseReleased(int index, int x, int y, int mouseEvent, int relativeX, int relativeY) {
			button.mouseReleased(x, y);
		}
	}
}
