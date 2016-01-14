package covers1624.cpp.asm;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import oracle.jrockit.jfr.jdkevents.ThrowableTracer;

import java.util.Map;

/**
 * Created by covers1624 on 1/2/2016.
 */
public class ASMHandler implements IFMLLoadingPlugin{


	@Override
	public String[] getASMTransformerClass() {
		Launch.classLoader.registerTransformer(EntityLivingBaseTransformer.class.getName());
		return null;
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {

	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}
}
