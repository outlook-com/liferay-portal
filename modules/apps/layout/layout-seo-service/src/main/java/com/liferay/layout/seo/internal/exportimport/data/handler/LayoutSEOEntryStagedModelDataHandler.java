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

package com.liferay.layout.seo.internal.exportimport.data.handler;

import com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializer;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializerSerializeRequest;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializerSerializeResponse;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.storage.StorageEngine;
import com.liferay.exportimport.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.layout.seo.model.LayoutSEOEntry;
import com.liferay.layout.seo.service.LayoutSEOEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.xml.Element;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(service = StagedModelDataHandler.class)
public class LayoutSEOEntryStagedModelDataHandler
	extends BaseStagedModelDataHandler<LayoutSEOEntry> {

	public static final String[] CLASS_NAMES = {LayoutSEOEntry.class.getName()};

	@Override
	public void deleteStagedModel(LayoutSEOEntry layoutSEOEntry) {
		_layoutSEOEntryLocalService.deleteLayoutSEOEntry(layoutSEOEntry);
	}

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException {

		_layoutSEOEntryLocalService.deleteLayoutSEOEntry(uuid, groupId);
	}

	@Override
	public List<LayoutSEOEntry> fetchStagedModelsByUuidAndCompanyId(
		String uuid, long companyId) {

		return Collections.singletonList(
			_layoutSEOEntryLocalService.fetchLayoutSEOEntryByUuidAndGroupId(
				uuid, companyId));
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext,
			LayoutSEOEntry layoutSEOEntry)
		throws Exception {

		DDMStructure ddmStructure = _getDDMStructure(
			portletDataContext.getCompanyId());

		StagedModelDataHandlerUtil.exportReferenceStagedModel(
			portletDataContext, layoutSEOEntry, ddmStructure,
			PortletDataContext.REFERENCE_TYPE_STRONG);

		Element layoutSEOEntryElement = portletDataContext.getExportDataElement(
			layoutSEOEntry);

		Element structureFieldsElement = layoutSEOEntryElement.addElement(
			"structure-fields");

		if (layoutSEOEntry.getDDMStorageId() != 0) {
			String ddmFormValuesPath = ExportImportPathUtil.getModelPath(
				ddmStructure, String.valueOf(layoutSEOEntry.getDDMStorageId()));

			structureFieldsElement.addAttribute(
				"ddm-form-values-path", ddmFormValuesPath);

			DDMFormValuesSerializerSerializeResponse
				ddmFormValuesSerializerSerializeResponse =
					_jsonDDMFormValuesSerializer.serialize(
						DDMFormValuesSerializerSerializeRequest.Builder.
							newBuilder(
								_storageEngine.getDDMFormValues(
									layoutSEOEntry.getDDMStorageId())
							).build());

			portletDataContext.addZipEntry(
				ddmFormValuesPath,
				ddmFormValuesSerializerSerializeResponse.getContent());
		}

		portletDataContext.addClassedModel(
			layoutSEOEntryElement,
			ExportImportPathUtil.getModelPath(layoutSEOEntry), layoutSEOEntry);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext,
			LayoutSEOEntry layoutSEOEntry)
		throws Exception {

		LayoutSEOEntry existingLayoutSEOEntry =
			fetchStagedModelByUuidAndGroupId(
				layoutSEOEntry.getUuid(), layoutSEOEntry.getGroupId());

		if (existingLayoutSEOEntry == null) {
			Map<Long, Layout> newPrimaryKeysMap =
				(Map<Long, Layout>)portletDataContext.getNewPrimaryKeysMap(
					Layout.class + ".layout");

			Layout layout = newPrimaryKeysMap.get(layoutSEOEntry.getLayoutId());

			_layoutSEOEntryLocalService.updateLayoutSEOEntry(
				layoutSEOEntry.getUserId(), layout.getGroupId(),
				layout.isPrivateLayout(), layout.getLayoutId(),
				layoutSEOEntry.isCanonicalURLEnabled(),
				layoutSEOEntry.getCanonicalURLMap(),
				layoutSEOEntry.isOpenGraphDescriptionEnabled(),
				layoutSEOEntry.getOpenGraphDescriptionMap(),
				layoutSEOEntry.getOpenGraphImageAltMap(),
				layoutSEOEntry.getOpenGraphImageFileEntryId(),
				layoutSEOEntry.isOpenGraphTitleEnabled(),
				layoutSEOEntry.getOpenGraphTitleMap(),
				_createServiceContext(layoutSEOEntry, portletDataContext));
		}
		else {
			_layoutSEOEntryLocalService.updateLayoutSEOEntry(
				existingLayoutSEOEntry.getUserId(),
				portletDataContext.getScopeGroupId(),
				layoutSEOEntry.isPrivateLayout(),
				existingLayoutSEOEntry.getLayoutId(),
				layoutSEOEntry.isCanonicalURLEnabled(),
				layoutSEOEntry.getCanonicalURLMap(),
				layoutSEOEntry.isOpenGraphDescriptionEnabled(),
				layoutSEOEntry.getOpenGraphDescriptionMap(),
				layoutSEOEntry.getOpenGraphImageAltMap(),
				layoutSEOEntry.getOpenGraphImageFileEntryId(),
				layoutSEOEntry.isOpenGraphTitleEnabled(),
				layoutSEOEntry.getOpenGraphTitleMap(),
				_createServiceContext(layoutSEOEntry, portletDataContext));
		}
	}

	private ServiceContext _createServiceContext(
			LayoutSEOEntry layoutSEOEntry,
			PortletDataContext portletDataContext)
		throws Exception {

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			layoutSEOEntry);

		DDMStructure ddmStructure = _getDDMStructure(
			portletDataContext.getCompanyId());

		Element layoutSEOEntryElement = portletDataContext.getImportDataElement(
			layoutSEOEntry);

		Element structureFieldsElement =
			(Element)layoutSEOEntryElement.selectSingleNode("structure-fields");

		String serializedDDMFormValues = portletDataContext.getZipEntryAsString(
			structureFieldsElement.attributeValue("ddm-form-values-path"));

		serviceContext.setAttribute(
			ddmStructure.getStructureId() + "ddmFormValues",
			serializedDDMFormValues);

		return serviceContext;
	}

	private DDMStructure _getDDMStructure(long companyId) throws Exception {
		Group companyGroup = _groupLocalService.getCompanyGroup(companyId);

		return _ddmStructureLocalService.getStructure(
			companyGroup.getGroupId(),
			_classNameLocalService.getClassNameId(
				LayoutSEOEntry.class.getName()),
			"custom-meta-tags");
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference(target = "(ddm.form.values.serializer.type=json)")
	private DDMFormValuesSerializer _jsonDDMFormValuesSerializer;

	@Reference
	private LayoutSEOEntryLocalService _layoutSEOEntryLocalService;

	@Reference
	private StorageEngine _storageEngine;

}