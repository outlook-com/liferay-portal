/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.dynamic.data.mapping.model;

import com.liferay.petra.lang.HashUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Pablo Carvalho
 */
public class DDMFormFieldOptions implements Serializable {

	public DDMFormFieldOptions() {
		_defaultLocale = LocaleUtil.getDefault();
	}

	public DDMFormFieldOptions(DDMFormFieldOptions ddmFormFieldOptions) {
		_defaultLocale = ddmFormFieldOptions._defaultLocale;

		Map<String, String> optionsReferences =
			ddmFormFieldOptions._optionsReferences;

		Map<String, LocalizedValue> options = ddmFormFieldOptions._options;

		for (Map.Entry<String, LocalizedValue> entry : options.entrySet()) {
			LocalizedValue localizedValue = entry.getValue();

			String optionValue = entry.getKey();

			for (Locale locale : localizedValue.getAvailableLocales()) {
				addOptionLabel(
					optionValue, locale, localizedValue.getString(locale));
			}

			addOptionReference(optionValue, optionsReferences.get(optionValue));
		}
	}

	public void addOption(String value) {
		_options.put(value, new LocalizedValue(_defaultLocale));
	}

	public void addOptionLabel(
		String optionValue, Locale locale, String label) {

		LocalizedValue labels = _options.get(optionValue);

		if (labels == null) {
			labels = new LocalizedValue(_defaultLocale);

			_options.put(optionValue, labels);
		}

		labels.addString(locale, label);
	}

	public void addOptionReference(String optionValue, String optionReference) {
		_optionsReferences.put(optionValue, optionReference);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DDMFormFieldOptions)) {
			return false;
		}

		DDMFormFieldOptions ddmFormFieldOptions = (DDMFormFieldOptions)object;

		if (Objects.equals(
				_defaultLocale, ddmFormFieldOptions._defaultLocale) &&
			Objects.equals(_options, ddmFormFieldOptions._options)) {

			return true;
		}

		return false;
	}

	public Locale getDefaultLocale() {
		return _defaultLocale;
	}

	public LocalizedValue getOptionLabels(String optionValue) {
		return _options.get(optionValue);
	}

	public String getOptionReference(String optionValue) {
		return _optionsReferences.get(optionValue);
	}

	public Map<String, LocalizedValue> getOptions() {
		return _options;
	}

	public Map<String, String> getOptionsReferences() {
		return _optionsReferences;
	}

	public Set<String> getOptionsValues() {
		return _options.keySet();
	}

	@Override
	public int hashCode() {
		int hash = HashUtil.hash(0, _defaultLocale);

		return HashUtil.hash(hash, _options);
	}

	public void setDefaultLocale(Locale defaultLocale) {
		_defaultLocale = defaultLocale;

		for (LocalizedValue localizedValue : _options.values()) {
			localizedValue.setDefaultLocale(defaultLocale);
		}
	}

	private Locale _defaultLocale;
	private final Map<String, LocalizedValue> _options = new LinkedHashMap<>();
	private final Map<String, String> _optionsReferences =
		new LinkedHashMap<>();

}