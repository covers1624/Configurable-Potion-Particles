package covers1624.cpp.handler;

import covers1624.cpp.util.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by covers1624 on 1/6/2016.
 */
public class PotionHandler {

	public static PotionHandler instance = new PotionHandler();
	private HashMap<Integer, Boolean> stateMap = new HashMap<Integer, Boolean>();
	private HashMap<Integer, Boolean> cache = new HashMap<Integer, Boolean>();
	private File config;

	public void init(File configFolder) {
		if (!configFolder.exists()) {
			LogHelper.fatal("For some unknown reason to science, The mod configuration directory FML gave us does not exist. Attempting to create directory...");
			if (!configFolder.mkdir()) {
				LogHelper.fatal("Unable to create directory..");
				FMLCommonHandler.instance().exitJava(-1, false);
			}
			LogHelper.info("Creating directory was a success, There was no reason that this folder shouldn't exist. FML should have created it for us beforehand.");
		}
		config = new File(configFolder, "CustomPotionParticles.cfg");
		if (config.exists()) {
			readFile(config);
		} else {
			initDefaults();
			writeFile(config);
		}
	}

	private void readFile(File config) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(config));
			String curLine;
			while ((curLine = in.readLine()) != null) {
				if (curLine.isEmpty() || curLine.startsWith("#")) {
					continue;
				}
				String[] curSplit = curLine.split(":");
				String first = curSplit[0];
				String second = curSplit[1];
				if (second.contains("#")) {
					int index = second.indexOf("#");
					String split = second.substring(0, index);
					second = split.trim();
				}
				int id = Integer.parseInt(first);
				boolean state = Boolean.parseBoolean(second);
				stateMap.put(id, state);
			}
			in.close();
		} catch (Exception e) {
			LogHelper.fatal("Unable to read config file..");
			e.printStackTrace();
		}
	}

	private void writeFile(File config) {
		try {
			if (!config.exists()) {
				config.createNewFile();
			} else {
				config.delete();
				config.createNewFile();
			}
			PrintWriter out = new PrintWriter(new FileOutputStream(config));
			out.println("# This is the configuration for Custom Potion Particles. It is very basic..");
			out.println("# How this works:");
			out.println("#  <PotionID>:<true or false>");
			out.println("#  Comments are made with the # character and are only supported after the boolean value.");
			out.println("\n\n");
			for (Map.Entry<Integer, Boolean> entry: stateMap.entrySet()){
				out.println(entry.getKey() + ":" + entry.getValue() + "# " + Potion.potionTypes[entry.getKey()].getName());
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			LogHelper.fatal("Failed to write config file..");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void save(){
		if (!cache.equals(stateMap)){
			writeFile(config);
			cache = (HashMap<Integer, Boolean>) stateMap.clone();
		}
	}

	private void initDefaults() {
		for (Potion potion : Potion.potionTypes) {
			if (potion == null) {
				continue;
			}
			stateMap.put(potion.id, true);
		}
	}

	/**
	 * Returns true if it is enabled.
	 */
	public boolean getPotionState(PotionEffect effect) {
		return getPotionState(effect.getPotionID());
	}

	/**
	 * Returns true if it is enabled.
	 */
	public boolean getPotionState(int potionId) {
		if (stateMap.isEmpty()) {
			LogHelper.bigFatal("Trying to access StateMap when it is empty...");
			return true;
		}
		return stateMap.get(potionId);
	}

	public void setPotionState(int potionId, boolean state) {
		LogHelper.info("Setting potion %s to state %s", potionId, state ? "Enabled" : "Disabled");
		if (stateMap.containsKey(potionId)) {
			stateMap.remove(potionId);
			stateMap.put(potionId, state);
		} else {
			LogHelper.bigFatal("Trying to set state for a potion that does not exist..");
			stateMap.put(potionId, state);
		}
	}

	public HashMap<Integer, Boolean> getStateMap() {
		return stateMap;
	}

	public void setStateMap(HashMap<Integer, Boolean> stateMap) {
		this.stateMap = stateMap;
	}
}
