package com.mcupdater.commonores.util;

import com.mcupdater.commonores.CommonOres;
import net.minecraft.util.text.translation.I18n;

public class LanguageBuilder {

	public static String buildTranslatedNames() {
		String dust = I18n.translateToLocal("commonores.dust.name.suffix");
		String ingot = I18n.translateToLocal("commonores.ingot.name.suffix");
		String ore = I18n.translateToLocal("commonores.ore.name.suffix");
		StringBuilder generated = new StringBuilder();
		for (int i = 0; i < CommonOres.types.size(); i++) {
			generated.append("tile.blockore.").append(CommonOres.types.get(i).toLowerCase()).append(".name=").append(CommonOres.types.get(i)).append(" ").append(ore).append("\n");
			generated.append("item.ingot." + CommonOres.types.get(i).toLowerCase() + ".name=" + CommonOres.types.get(i) + " " + ingot + "\n");
			generated.append("item.dust." + CommonOres.types.get(i).toLowerCase() + ".name=" + CommonOres.types.get(i) + " " + dust + "\n");
		}
		return generated.toString();
	}
}
