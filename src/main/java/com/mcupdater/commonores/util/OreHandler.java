package com.mcupdater.commonores.util;

import com.google.common.collect.Lists;
import com.mcupdater.commonores.CommonOres;
import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.util.List;

public class OreHandler {
	private static OreHandler instance;
	private List<String> types;

	private OreHandler() {
		types = Lists.newArrayList();
		OreHandler.instance = this;
	}

	public static OreHandler getInstance() {
		if( instance == null ) {
			new OreHandler();
		}
		return instance;
	}

	public static void parseOreDefinition(File file) {
		String name = FileUtils.removeExtension(file.getName());
		CommonOres.log.info("parsing ore "+name);
		getInstance().types.add(name);

		// TODO: actually parse the file now :)
	}

	public static int numTypes() {
		return getInstance().types.size();
	}
	public static String get(int idx) {
		return getInstance().types.get(idx);
	}
}
