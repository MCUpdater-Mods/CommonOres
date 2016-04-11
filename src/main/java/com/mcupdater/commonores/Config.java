package com.mcupdater.commonores;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Config {
	
	// TODO: add enum for categories
	// TODO: add comment
	// TODO: add expected data type ? Or we could continue to infer it
	public enum ConfigSettings {
		DEFINITION_DIRECTORY("options", "ore_definition_directory", "commonores"),
		DISABLE_VANILLA_OREGEN("options", "replace_vanilla_oregen", false),
		GENERATE_DENSE_ORES("options", "enable_dense_ores", true),
		GENERATE_POOR_ORES("options", "enable_poor_ores", true);
		
		private final String category;
		private final String key;
		private Object value;
		
		private ConfigSettings(String category, String key, Object value) {
			this.category = category;
			this.key = key;
			this.value = value;
		}
		
		public boolean getBoolean() {
			return (Boolean)value;
		}
		public double getDouble() {
			return (Double)value;
		}
		public String getString() {
			return (String)value;
		}
	}
	
	private final Configuration config;

	public Config(final File configFile) {
		config = new Configuration(configFile);
		config.load();
		
		initConfigFile();
		loadDefinitions();
		
		config.save();
	}
	
	private void initConfigFile() {
		// parse the config
		for( ConfigSettings option : ConfigSettings.values() ) {
			final Property prop;
			if( option.value instanceof Boolean ) {
				prop = config.get(option.category, option.key, option.getBoolean());
				option.value = prop.getBoolean();
			} else if ( option.value instanceof Double ) { 
				prop = config.get(option.category, option.key, option.getDouble());
				option.value = prop.getDouble();
			} else {
				// treat anything else as a string
				prop = config.get(option.category, option.key, option.getString());
				option.value = prop.getString();
			}
		}
	}
	
	private void loadDefinitions() {
		final File dir = new File(config.getConfigFile().getParent(), ConfigSettings.DEFINITION_DIRECTORY.getString());
		if( !dir.exists() ) {
			dir.mkdirs();
		} else if( !dir.isDirectory() ) {
			// TODO: complain loudly
			return;
		}
		
		// TODO: filter the file list for .json extension
		for( final File file : dir.listFiles() ) {
			parseDefinitions(file);
		}
	}

	private void parseDefinitions(File file) {
		// TODO Auto-generated method stub
		
	}
	
}
