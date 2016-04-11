package com.mcupdater.commonores.client;

import com.mcupdater.commonores.CommonOres;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ResourceListener implements IResourceManagerReloadListener {
	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {
		CommonOres.proxy.injectLocalizations();
	}
}
