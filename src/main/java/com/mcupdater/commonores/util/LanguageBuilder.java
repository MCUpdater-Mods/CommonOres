package com.mcupdater.commonores.util;

import com.mcupdater.commonores.CommonOres;
import net.minecraft.util.text.translation.I18n;

public class LanguageBuilder {

	public static String buildTranslatedNames() {
		String dust = I18n.translateToLocal("commonores.dust.name.suffix");
		String ingot = I18n.translateToLocal("commonores.ingot.name.suffix");
		String ore = I18n.translateToLocal("commonores.ore.name.suffix");
		StringBuilder generated = new StringBuilder();
		for (int i = 0; i < OreHandler.numTypes(); i++) {
			generated.append("tile.blockore.").append(OreHandler.get(i).toLowerCase()).append(".name=").append(OreHandler.get(i)).append(" ").append(ore).append("\n");
			generated.append("item.ingot." + OreHandler.get(i).toLowerCase() + ".name=" + OreHandler.get(i) + " " + ingot + "\n");
			generated.append("item.dust." + OreHandler.get(i).toLowerCase() + ".name=" + OreHandler.get(i) + " " + dust + "\n");
		}
		return generated.toString();
	}
}
