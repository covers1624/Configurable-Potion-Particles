package covers1624.cpp.proxy;

import covers1624.cpp.command.PotionGuiCommand;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.client.ClientCommandHandler;

/**
 * Created by covers1624 on 1/10/2016.
 */
public class ClientProxy extends CommonProxy{

	@Override
	public void preInit(FMLPreInitializationEvent event) {

	}

	@Override
	public void init(FMLInitializationEvent event) {
		ClientCommandHandler.instance.registerCommand(new PotionGuiCommand());
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
	}
}
