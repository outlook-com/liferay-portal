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

package com.liferay.commerce.product.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceCatalogLocalService}.
 *
 * @author Marco Leo
 * @see CommerceCatalogLocalService
 * @generated
 */
public class CommerceCatalogLocalServiceWrapper
	implements CommerceCatalogLocalService,
			   ServiceWrapper<CommerceCatalogLocalService> {

	public CommerceCatalogLocalServiceWrapper(
		CommerceCatalogLocalService commerceCatalogLocalService) {

		_commerceCatalogLocalService = commerceCatalogLocalService;
	}

	/**
	 * Adds the commerce catalog to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceCatalogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceCatalog the commerce catalog
	 * @return the commerce catalog that was added
	 */
	@Override
	public com.liferay.commerce.product.model.CommerceCatalog
		addCommerceCatalog(
			com.liferay.commerce.product.model.CommerceCatalog
				commerceCatalog) {

		return _commerceCatalogLocalService.addCommerceCatalog(commerceCatalog);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceCatalog
			addCommerceCatalog(
				String name, String commerceCurrencyCode,
				String catalogDefaultLanguageId, boolean system,
				String externalReferenceCode,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceCatalogLocalService.addCommerceCatalog(
			name, commerceCurrencyCode, catalogDefaultLanguageId, system,
			externalReferenceCode, serviceContext);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceCatalog
			addCommerceCatalog(
				String name, String commerceCurrencyCode,
				String catalogDefaultLanguageId, String externalReferenceCode,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceCatalogLocalService.addCommerceCatalog(
			name, commerceCurrencyCode, catalogDefaultLanguageId,
			externalReferenceCode, serviceContext);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceCatalog
			addDefaultCommerceCatalog(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceCatalogLocalService.addDefaultCommerceCatalog(
			companyId);
	}

	/**
	 * Creates a new commerce catalog with the primary key. Does not add the commerce catalog to the database.
	 *
	 * @param commerceCatalogId the primary key for the new commerce catalog
	 * @return the new commerce catalog
	 */
	@Override
	public com.liferay.commerce.product.model.CommerceCatalog
		createCommerceCatalog(long commerceCatalogId) {

		return _commerceCatalogLocalService.createCommerceCatalog(
			commerceCatalogId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceCatalogLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the commerce catalog from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceCatalogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceCatalog the commerce catalog
	 * @return the commerce catalog that was removed
	 * @throws PortalException
	 */
	@Override
	public com.liferay.commerce.product.model.CommerceCatalog
			deleteCommerceCatalog(
				com.liferay.commerce.product.model.CommerceCatalog
					commerceCatalog)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceCatalogLocalService.deleteCommerceCatalog(
			commerceCatalog);
	}

	/**
	 * Deletes the commerce catalog with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceCatalogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceCatalogId the primary key of the commerce catalog
	 * @return the commerce catalog that was removed
	 * @throws PortalException if a commerce catalog with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.product.model.CommerceCatalog
			deleteCommerceCatalog(long commerceCatalogId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceCatalogLocalService.deleteCommerceCatalog(
			commerceCatalogId);
	}

	@Override
	public void deleteCommerceCatalogs(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceCatalogLocalService.deleteCommerceCatalogs(companyId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceCatalogLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _commerceCatalogLocalService.dslQuery(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceCatalogLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _commerceCatalogLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CommerceCatalogModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _commerceCatalogLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CommerceCatalogModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _commerceCatalogLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _commerceCatalogLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _commerceCatalogLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceCatalog
		fetchByExternalReferenceCode(
			long companyId, String externalReferenceCode) {

		return _commerceCatalogLocalService.fetchByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceCatalog
		fetchCommerceCatalog(long commerceCatalogId) {

		return _commerceCatalogLocalService.fetchCommerceCatalog(
			commerceCatalogId);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceCatalog
		fetchCommerceCatalogByGroupId(long groupId) {

		return _commerceCatalogLocalService.fetchCommerceCatalogByGroupId(
			groupId);
	}

	/**
	 * Returns the commerce catalog with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the commerce catalog's external reference code
	 * @return the matching commerce catalog, or <code>null</code> if a matching commerce catalog could not be found
	 */
	@Override
	public com.liferay.commerce.product.model.CommerceCatalog
		fetchCommerceCatalogByReferenceCode(
			long companyId, String externalReferenceCode) {

		return _commerceCatalogLocalService.fetchCommerceCatalogByReferenceCode(
			companyId, externalReferenceCode);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _commerceCatalogLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce catalog with the primary key.
	 *
	 * @param commerceCatalogId the primary key of the commerce catalog
	 * @return the commerce catalog
	 * @throws PortalException if a commerce catalog with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.product.model.CommerceCatalog
			getCommerceCatalog(long commerceCatalogId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceCatalogLocalService.getCommerceCatalog(
			commerceCatalogId);
	}

	@Override
	public com.liferay.portal.kernel.model.Group getCommerceCatalogGroup(
			long commerceCatalogId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceCatalogLocalService.getCommerceCatalogGroup(
			commerceCatalogId);
	}

	/**
	 * Returns a range of all the commerce catalogs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.model.impl.CommerceCatalogModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce catalogs
	 * @param end the upper bound of the range of commerce catalogs (not inclusive)
	 * @return the range of commerce catalogs
	 */
	@Override
	public java.util.List<com.liferay.commerce.product.model.CommerceCatalog>
		getCommerceCatalogs(int start, int end) {

		return _commerceCatalogLocalService.getCommerceCatalogs(start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CommerceCatalog>
		getCommerceCatalogs(long companyId, boolean system) {

		return _commerceCatalogLocalService.getCommerceCatalogs(
			companyId, system);
	}

	/**
	 * Returns the number of commerce catalogs.
	 *
	 * @return the number of commerce catalogs
	 */
	@Override
	public int getCommerceCatalogsCount() {
		return _commerceCatalogLocalService.getCommerceCatalogsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _commerceCatalogLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceCatalogLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceCatalogLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CommerceCatalog>
			searchCommerceCatalogs(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceCatalogLocalService.searchCommerceCatalogs(companyId);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CommerceCatalog>
			searchCommerceCatalogs(
				long companyId, String keywords, int start, int end,
				com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceCatalogLocalService.searchCommerceCatalogs(
			companyId, keywords, start, end, sort);
	}

	@Override
	public int searchCommerceCatalogsCount(long companyId, String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceCatalogLocalService.searchCommerceCatalogsCount(
			companyId, keywords);
	}

	/**
	 * Updates the commerce catalog in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceCatalogLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceCatalog the commerce catalog
	 * @return the commerce catalog that was updated
	 */
	@Override
	public com.liferay.commerce.product.model.CommerceCatalog
		updateCommerceCatalog(
			com.liferay.commerce.product.model.CommerceCatalog
				commerceCatalog) {

		return _commerceCatalogLocalService.updateCommerceCatalog(
			commerceCatalog);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceCatalog
			updateCommerceCatalog(
				long commerceCatalogId, String name,
				String commerceCurrencyCode, String catalogDefaultLanguageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceCatalogLocalService.updateCommerceCatalog(
			commerceCatalogId, name, commerceCurrencyCode,
			catalogDefaultLanguageId);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceCatalog
			updateCommerceCatalogExternalReferenceCode(
				long commerceCatalogId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceCatalogLocalService.
			updateCommerceCatalogExternalReferenceCode(
				commerceCatalogId, externalReferenceCode);
	}

	@Override
	public CommerceCatalogLocalService getWrappedService() {
		return _commerceCatalogLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceCatalogLocalService commerceCatalogLocalService) {

		_commerceCatalogLocalService = commerceCatalogLocalService;
	}

	private CommerceCatalogLocalService _commerceCatalogLocalService;

}