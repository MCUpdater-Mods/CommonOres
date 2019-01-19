package com.mcupdater.commonores.client;

import com.mcupdater.commonores.CommonOres;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraftforge.client.resource.IResourceType;
import net.minecraftforge.client.resource.ISelectiveResourceReloadListener;
import net.minecraftforge.client.resource.VanillaResourceType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.function.Predicate;

@SideOnly(Side.CLIENT)
public class ResourceListener implements ISelectiveResourceReloadListener {

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate) {
		if (resourcePredicate.test(VanillaResourceType.LANGUAGES)) {
			CommonOres.proxy.injectLocalizations();
		}

	}
}
