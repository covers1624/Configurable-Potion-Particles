package covers1624.cpp.command;

import covers1624.cpp.ConfigurablePotionParticles;
import covers1624.cpp.gui.GuiPotionConfig;
import covers1624.cpp.util.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

/**
 * Created by covers1624 on 1/10/2016.
 */
public class PotionGuiCommand implements ICommand {
	@Override
	public String getCommandName() {
		return "configurePotions";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return null;
	}

	@Override
	public List getCommandAliases() {
		return null;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (sender instanceof EntityPlayer && args.length == 0) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiPotionConfig(null));
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
		return false;
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}
}
