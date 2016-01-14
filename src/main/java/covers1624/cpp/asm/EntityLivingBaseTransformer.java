package covers1624.cpp.asm;

import covers1624.cpp.util.ASMUtils;
import covers1624.cpp.util.LogHelper;
import covers1624.lib.repack.codechicken.lib.asm.CC_ClassWriter;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

/**
 * Created by covers1624 on 1/2/2016.
 */
public class EntityLivingBaseTransformer implements IClassTransformer {

	public static String updatePotionEffectsName = ASMUtils.obfuscated ? "func_70679_bo" : "updatePotionEffects";

	@Override
	public byte[] transform(String name, String tName, byte[] bytes) {
		try {
			if (!name.equals("net.minecraft.entity.EntityLivingBase")) {
				return bytes;
			}
			ClassNode classNode = new ClassNode();
			ClassReader classReader = new ClassReader(bytes);
			CC_ClassWriter classWriter = new CC_ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
			classReader.accept(classNode, 0);
			MethodNode updatePotionEffectsNode = ASMUtils.findMethodNodeOfClass(classNode, updatePotionEffectsName, "()V");
			if (updatePotionEffectsNode == null) {
				throw new RuntimeException(String.format("Unable to find method node %s in class %s", updatePotionEffectsName, name));
			}
			updatePotionEffectsNode.instructions.clear();
			updatePotionEffectsNode.localVariables.clear();
			updatePotionEffectsNode.tryCatchBlocks.clear();
			updatePotionEffectsNode.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
			updatePotionEffectsNode.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "covers1624/cpp/asm/ASMHooks", "updatePotionEffectsHook", "(Lnet/minecraft/entity/EntityLivingBase;)V", false));
			updatePotionEffectsNode.instructions.add(new InsnNode(Opcodes.RETURN));
			classNode.accept(classWriter);
			return classWriter.toByteArray();
		} catch (Throwable ignored) {
			ignored.printStackTrace();
			LogHelper.fatal("Unable to transform class %s", name);
			return bytes;
		}
	}
}
