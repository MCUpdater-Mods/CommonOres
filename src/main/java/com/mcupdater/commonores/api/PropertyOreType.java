package com.mcupdater.commonores.api;

import com.google.common.base.Optional;
import net.minecraft.block.properties.IProperty;

import java.util.Collection;

public class PropertyOreType implements IProperty<String> {
	@Override
	public String getName() {
		return null;
	}

	@Override
	public Collection<String> getAllowedValues() {
		return null;
	}

	@Override
	public Class<String> getValueClass() {
		return null;
	}

	@Override
	public Optional<String> parseValue(String value) {
		return null;
	}

	@Override
	public String getName(String value) {
		return null;
	}
}
