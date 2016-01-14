package covers1624.cpp.asm;

import covers1624.cpp.handler.PotionHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by covers1624 on 1/4/2016.
 */
public class ASMHooks {

	@SuppressWarnings("unchecked")
	public static void updatePotionEffectsHook(EntityLivingBase entity) {
		Iterator iterator = entity.activePotionsMap.keySet().iterator();

		while (iterator.hasNext()) {
			Integer integer = (Integer) iterator.next();
			PotionEffect potioneffect = (PotionEffect) entity.activePotionsMap.get(integer);

			if (!potioneffect.onUpdate(entity)) {
				if (!entity.worldObj.isRemote) {
					iterator.remove();
					entity.onFinishedPotionEffect(potioneffect);
				}
			} else if (potioneffect.getDuration() % 600 == 0) {
				entity.onChangedPotionEffect(potioneffect, false);
			}
		}

		int i;

		if (entity.potionsNeedUpdate) {
			if (!entity.worldObj.isRemote) {
				if (entity.activePotionsMap.isEmpty()) {
					entity.dataWatcher.updateObject(8, (byte) 0);
					entity.dataWatcher.updateObject(7, 0);
					entity.setInvisible(false);
				} else {
					i = calcPotionLiquidColor(entity.activePotionsMap.values());
					entity.dataWatcher.updateObject(8, (byte) (PotionHelper.func_82817_b(entity.activePotionsMap.values()) ? 1 : 0));
					entity.dataWatcher.updateObject(7, i);
					entity.setInvisible(entity.isPotionActive(Potion.invisibility.id));
				}
			}

			entity.potionsNeedUpdate = false;
		}

		i = entity.dataWatcher.getWatchableObjectInt(7);
		boolean flag1 = entity.dataWatcher.getWatchableObjectByte(8) > 0;

		if (i > 0) {
			boolean flag;

			if (!entity.isInvisible()) {
				flag = entity.rand.nextBoolean();
			} else {
				flag = entity.rand.nextInt(15) == 0;
			}

			if (flag1) {
				flag &= entity.rand.nextInt(5) == 0;
			}

			if (flag && i > 0) {
				double r = (double) (i >> 16 & 255) / 255.0D;
				double g = (double) (i >> 8 & 255) / 255.0D;
				double b = (double) (i & 255) / 255.0D;
				entity.worldObj.spawnParticle(flag1 ? "mobSpellAmbient" : "mobSpell", entity.posX + (entity.rand.nextDouble() - 0.5D) * (double) entity.width, entity.posY + entity.rand.nextDouble() * (double) entity.height - (double) entity.yOffset, entity.posZ + (entity.rand.nextDouble() - 0.5D) * (double) entity.width, r, g, b);
			}
		}
	}

	public static int calcPotionLiquidColor(Collection<PotionEffect> collection) {
		int colour = 3694022;

		if (collection != null && !collection.isEmpty()) {
			float r = 0.0F;
			float g = 0.0F;
			float b = 0.0F;
			float noColors = 0.0F;

			for (PotionEffect potioneffect : collection) {
				if (!PotionHandler.instance.getPotionState(potioneffect)){
					continue;
				}
				int j = Potion.potionTypes[potioneffect.getPotionID()].getLiquidColor();

				for (int k = 0; k <= potioneffect.getAmplifier(); ++k) {
					r += (float) (j >> 16 & 255) / 255.0F;
					g += (float) (j >> 8 & 255) / 255.0F;
					b += (float) (j & 255) / 255.0F;
					++noColors;
				}
			}

			r = r / noColors * 255.0F;
			g = g / noColors * 255.0F;
			b = b / noColors * 255.0F;
			return (int) r << 16 | (int) g << 8 | (int) b;
		} else {
			return colour;
		}
	}

}
