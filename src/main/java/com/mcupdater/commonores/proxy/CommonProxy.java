package com.mcupdater.commonores.proxy;

import com.mcupdater.commonores.client.ResourceListener;
import com.mcupdater.commonores.util.LanguageBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.util.text.translation.LanguageMap;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.io.ByteArrayInputStream;

public class CommonProxy {

	protected String localizations;

	public void preInit() {};

	public void loadComplete() {}

	public void registerModels() {}

	public void registerRenders() {}

	public void generateLocalizations() {
		localizations = LanguageBuilder.buildTranslatedNames();
	}

	public void injectLocalizations() {
		((LanguageMap) ReflectionHelper.getPrivateValue(I18n.class, new I18n(), "localizedName")).inject(new ByteArrayInputStream(localizations.getBytes()));
	}
}
