package covers1624.cpp;

import covers1624.cpp.handler.GuiHandler;
import covers1624.cpp.handler.PotionHandler;
import covers1624.cpp.proxy.CommonProxy;
import covers1624.cpp.reference.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

/**
 * Created by covers1624 on 1/2/2016.
 */
@Mod(name = Reference.MOD_NAME, modid = Reference.MOD_ID, version = Reference.MOD_VERSION, guiFactory = Reference.GUI_FACTORY)
public class ConfigurablePotionParticles {

	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
	public static CommonProxy proxy;

	@Mod.Instance(Reference.MOD_NAME)
	public static ConfigurablePotionParticles instance;

	@Mod.EventHandler
	public void preinit(FMLPreInitializationEvent event){
		instance = this;
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		PotionHandler.instance.init(event.getModConfigurationDirectory());
		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event){
		proxy.init(event);
	}

	public void postInit(FMLPostInitializationEvent event){
		proxy.postInit(event);
	}
}
