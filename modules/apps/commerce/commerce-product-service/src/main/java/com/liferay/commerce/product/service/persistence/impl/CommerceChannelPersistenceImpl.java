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

package com.liferay.commerce.product.service.persistence.impl;

import com.liferay.commerce.product.exception.NoSuchChannelException;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.model.CommerceChannelTable;
import com.liferay.commerce.product.model.impl.CommerceChannelImpl;
import com.liferay.commerce.product.model.impl.CommerceChannelModelImpl;
import com.liferay.commerce.product.service.persistence.CommerceChannelPersistence;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;

/**
 * The persistence implementation for the commerce channel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marco Leo
 * @generated
 */
public class CommerceChannelPersistenceImpl
	extends BasePersistenceImpl<CommerceChannel>
	implements CommerceChannelPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceChannelUtil</code> to access the commerce channel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceChannelImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the commerce channels where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce channels
	 */
	@Override
	public List<CommerceChannel> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce channels where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceChannelModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @return the range of matching commerce channels
	 */
	@Override
	public List<CommerceChannel> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce channels where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceChannelModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce channels
	 */
	@Override
	public List<CommerceChannel> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceChannel> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce channels where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceChannelModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce channels
	 */
	@Override
	public List<CommerceChannel> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceChannel> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByCompanyId;
				finderArgs = new Object[] {companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCompanyId;
			finderArgs = new Object[] {
				companyId, start, end, orderByComparator
			};
		}

		List<CommerceChannel> list = null;

		if (useFinderCache) {
			list = (List<CommerceChannel>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceChannel commerceChannel : list) {
					if (companyId != commerceChannel.getCompanyId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_COMMERCECHANNEL_WHERE);

			sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CommerceChannelModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				list = (List<CommerceChannel>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first commerce channel in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce channel
	 * @throws NoSuchChannelException if a matching commerce channel could not be found
	 */
	@Override
	public CommerceChannel findByCompanyId_First(
			long companyId,
			OrderByComparator<CommerceChannel> orderByComparator)
		throws NoSuchChannelException {

		CommerceChannel commerceChannel = fetchByCompanyId_First(
			companyId, orderByComparator);

		if (commerceChannel != null) {
			return commerceChannel;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchChannelException(sb.toString());
	}

	/**
	 * Returns the first commerce channel in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce channel, or <code>null</code> if a matching commerce channel could not be found
	 */
	@Override
	public CommerceChannel fetchByCompanyId_First(
		long companyId, OrderByComparator<CommerceChannel> orderByComparator) {

		List<CommerceChannel> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce channel in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce channel
	 * @throws NoSuchChannelException if a matching commerce channel could not be found
	 */
	@Override
	public CommerceChannel findByCompanyId_Last(
			long companyId,
			OrderByComparator<CommerceChannel> orderByComparator)
		throws NoSuchChannelException {

		CommerceChannel commerceChannel = fetchByCompanyId_Last(
			companyId, orderByComparator);

		if (commerceChannel != null) {
			return commerceChannel;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchChannelException(sb.toString());
	}

	/**
	 * Returns the last commerce channel in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce channel, or <code>null</code> if a matching commerce channel could not be found
	 */
	@Override
	public CommerceChannel fetchByCompanyId_Last(
		long companyId, OrderByComparator<CommerceChannel> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<CommerceChannel> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce channels before and after the current commerce channel in the ordered set where companyId = &#63;.
	 *
	 * @param commerceChannelId the primary key of the current commerce channel
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce channel
	 * @throws NoSuchChannelException if a commerce channel with the primary key could not be found
	 */
	@Override
	public CommerceChannel[] findByCompanyId_PrevAndNext(
			long commerceChannelId, long companyId,
			OrderByComparator<CommerceChannel> orderByComparator)
		throws NoSuchChannelException {

		CommerceChannel commerceChannel = findByPrimaryKey(commerceChannelId);

		Session session = null;

		try {
			session = openSession();

			CommerceChannel[] array = new CommerceChannelImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, commerceChannel, companyId, orderByComparator, true);

			array[1] = commerceChannel;

			array[2] = getByCompanyId_PrevAndNext(
				session, commerceChannel, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceChannel getByCompanyId_PrevAndNext(
		Session session, CommerceChannel commerceChannel, long companyId,
		OrderByComparator<CommerceChannel> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_COMMERCECHANNEL_WHERE);

		sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(CommerceChannelModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceChannel)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommerceChannel> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the commerce channels that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching commerce channels that the user has permission to view
	 */
	@Override
	public List<CommerceChannel> filterFindByCompanyId(long companyId) {
		return filterFindByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce channels that the user has permission to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceChannelModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @return the range of matching commerce channels that the user has permission to view
	 */
	@Override
	public List<CommerceChannel> filterFindByCompanyId(
		long companyId, int start, int end) {

		return filterFindByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce channels that the user has permissions to view where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceChannelModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce channels that the user has permission to view
	 */
	@Override
	public List<CommerceChannel> filterFindByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<CommerceChannel> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByCompanyId(companyId, start, end, orderByComparator);
		}

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				3 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			sb = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_COMMERCECHANNEL_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_COMMERCECHANNEL_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_COMMERCECHANNEL_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(CommerceChannelModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(CommerceChannelModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), CommerceChannel.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(
					_FILTER_ENTITY_ALIAS, CommerceChannelImpl.class);
			}
			else {
				sqlQuery.addEntity(
					_FILTER_ENTITY_TABLE, CommerceChannelImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(companyId);

			return (List<CommerceChannel>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the commerce channels before and after the current commerce channel in the ordered set of commerce channels that the user has permission to view where companyId = &#63;.
	 *
	 * @param commerceChannelId the primary key of the current commerce channel
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce channel
	 * @throws NoSuchChannelException if a commerce channel with the primary key could not be found
	 */
	@Override
	public CommerceChannel[] filterFindByCompanyId_PrevAndNext(
			long commerceChannelId, long companyId,
			OrderByComparator<CommerceChannel> orderByComparator)
		throws NoSuchChannelException {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByCompanyId_PrevAndNext(
				commerceChannelId, companyId, orderByComparator);
		}

		CommerceChannel commerceChannel = findByPrimaryKey(commerceChannelId);

		Session session = null;

		try {
			session = openSession();

			CommerceChannel[] array = new CommerceChannelImpl[3];

			array[0] = filterGetByCompanyId_PrevAndNext(
				session, commerceChannel, companyId, orderByComparator, true);

			array[1] = commerceChannel;

			array[2] = filterGetByCompanyId_PrevAndNext(
				session, commerceChannel, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceChannel filterGetByCompanyId_PrevAndNext(
		Session session, CommerceChannel commerceChannel, long companyId,
		OrderByComparator<CommerceChannel> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_COMMERCECHANNEL_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_COMMERCECHANNEL_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_COMMERCECHANNEL_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(CommerceChannelModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(CommerceChannelModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), CommerceChannel.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(_FILTER_ENTITY_ALIAS, CommerceChannelImpl.class);
		}
		else {
			sqlQuery.addEntity(_FILTER_ENTITY_TABLE, CommerceChannelImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceChannel)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommerceChannel> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce channels where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (CommerceChannel commerceChannel :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceChannel);
		}
	}

	/**
	 * Returns the number of commerce channels where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce channels
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMMERCECHANNEL_WHERE);

			sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of commerce channels that the user has permission to view where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching commerce channels that the user has permission to view
	 */
	@Override
	public int filterCountByCompanyId(long companyId) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return countByCompanyId(companyId);
		}

		StringBundler sb = new StringBundler(2);

		sb.append(_FILTER_SQL_COUNT_COMMERCECHANNEL_WHERE);

		sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), CommerceChannel.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(companyId);

			Long count = (Long)sqlQuery.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 =
		"commerceChannel.companyId = ?";

	private FinderPath _finderPathFetchBySiteGroupId;
	private FinderPath _finderPathCountBySiteGroupId;

	/**
	 * Returns the commerce channel where siteGroupId = &#63; or throws a <code>NoSuchChannelException</code> if it could not be found.
	 *
	 * @param siteGroupId the site group ID
	 * @return the matching commerce channel
	 * @throws NoSuchChannelException if a matching commerce channel could not be found
	 */
	@Override
	public CommerceChannel findBySiteGroupId(long siteGroupId)
		throws NoSuchChannelException {

		CommerceChannel commerceChannel = fetchBySiteGroupId(siteGroupId);

		if (commerceChannel == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("siteGroupId=");
			sb.append(siteGroupId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchChannelException(sb.toString());
		}

		return commerceChannel;
	}

	/**
	 * Returns the commerce channel where siteGroupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param siteGroupId the site group ID
	 * @return the matching commerce channel, or <code>null</code> if a matching commerce channel could not be found
	 */
	@Override
	public CommerceChannel fetchBySiteGroupId(long siteGroupId) {
		return fetchBySiteGroupId(siteGroupId, true);
	}

	/**
	 * Returns the commerce channel where siteGroupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param siteGroupId the site group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce channel, or <code>null</code> if a matching commerce channel could not be found
	 */
	@Override
	public CommerceChannel fetchBySiteGroupId(
		long siteGroupId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {siteGroupId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchBySiteGroupId, finderArgs, this);
		}

		if (result instanceof CommerceChannel) {
			CommerceChannel commerceChannel = (CommerceChannel)result;

			if (siteGroupId != commerceChannel.getSiteGroupId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_COMMERCECHANNEL_WHERE);

			sb.append(_FINDER_COLUMN_SITEGROUPID_SITEGROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(siteGroupId);

				List<CommerceChannel> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchBySiteGroupId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {siteGroupId};
							}

							_log.warn(
								"CommerceChannelPersistenceImpl.fetchBySiteGroupId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CommerceChannel commerceChannel = list.get(0);

					result = commerceChannel;

					cacheResult(commerceChannel);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (CommerceChannel)result;
		}
	}

	/**
	 * Removes the commerce channel where siteGroupId = &#63; from the database.
	 *
	 * @param siteGroupId the site group ID
	 * @return the commerce channel that was removed
	 */
	@Override
	public CommerceChannel removeBySiteGroupId(long siteGroupId)
		throws NoSuchChannelException {

		CommerceChannel commerceChannel = findBySiteGroupId(siteGroupId);

		return remove(commerceChannel);
	}

	/**
	 * Returns the number of commerce channels where siteGroupId = &#63;.
	 *
	 * @param siteGroupId the site group ID
	 * @return the number of matching commerce channels
	 */
	@Override
	public int countBySiteGroupId(long siteGroupId) {
		FinderPath finderPath = _finderPathCountBySiteGroupId;

		Object[] finderArgs = new Object[] {siteGroupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMMERCECHANNEL_WHERE);

			sb.append(_FINDER_COLUMN_SITEGROUPID_SITEGROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(siteGroupId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_SITEGROUPID_SITEGROUPID_2 =
		"commerceChannel.siteGroupId = ?";

	private FinderPath _finderPathFetchByC_ERC;
	private FinderPath _finderPathCountByC_ERC;

	/**
	 * Returns the commerce channel where companyId = &#63; and externalReferenceCode = &#63; or throws a <code>NoSuchChannelException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce channel
	 * @throws NoSuchChannelException if a matching commerce channel could not be found
	 */
	@Override
	public CommerceChannel findByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchChannelException {

		CommerceChannel commerceChannel = fetchByC_ERC(
			companyId, externalReferenceCode);

		if (commerceChannel == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("companyId=");
			sb.append(companyId);

			sb.append(", externalReferenceCode=");
			sb.append(externalReferenceCode);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchChannelException(sb.toString());
		}

		return commerceChannel;
	}

	/**
	 * Returns the commerce channel where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching commerce channel, or <code>null</code> if a matching commerce channel could not be found
	 */
	@Override
	public CommerceChannel fetchByC_ERC(
		long companyId, String externalReferenceCode) {

		return fetchByC_ERC(companyId, externalReferenceCode, true);
	}

	/**
	 * Returns the commerce channel where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce channel, or <code>null</code> if a matching commerce channel could not be found
	 */
	@Override
	public CommerceChannel fetchByC_ERC(
		long companyId, String externalReferenceCode, boolean useFinderCache) {

		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {companyId, externalReferenceCode};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_ERC, finderArgs, this);
		}

		if (result instanceof CommerceChannel) {
			CommerceChannel commerceChannel = (CommerceChannel)result;

			if ((companyId != commerceChannel.getCompanyId()) ||
				!Objects.equals(
					externalReferenceCode,
					commerceChannel.getExternalReferenceCode())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_COMMERCECHANNEL_WHERE);

			sb.append(_FINDER_COLUMN_C_ERC_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3);
			}
			else {
				bindExternalReferenceCode = true;

				sb.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindExternalReferenceCode) {
					queryPos.add(externalReferenceCode);
				}

				List<CommerceChannel> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByC_ERC, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									companyId, externalReferenceCode
								};
							}

							_log.warn(
								"CommerceChannelPersistenceImpl.fetchByC_ERC(long, String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CommerceChannel commerceChannel = list.get(0);

					result = commerceChannel;

					cacheResult(commerceChannel);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (CommerceChannel)result;
		}
	}

	/**
	 * Removes the commerce channel where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the commerce channel that was removed
	 */
	@Override
	public CommerceChannel removeByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchChannelException {

		CommerceChannel commerceChannel = findByC_ERC(
			companyId, externalReferenceCode);

		return remove(commerceChannel);
	}

	/**
	 * Returns the number of commerce channels where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching commerce channels
	 */
	@Override
	public int countByC_ERC(long companyId, String externalReferenceCode) {
		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		FinderPath finderPath = _finderPathCountByC_ERC;

		Object[] finderArgs = new Object[] {companyId, externalReferenceCode};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_COMMERCECHANNEL_WHERE);

			sb.append(_FINDER_COLUMN_C_ERC_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3);
			}
			else {
				bindExternalReferenceCode = true;

				sb.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindExternalReferenceCode) {
					queryPos.add(externalReferenceCode);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_ERC_COMPANYID_2 =
		"commerceChannel.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2 =
		"commerceChannel.externalReferenceCode = ?";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3 =
		"(commerceChannel.externalReferenceCode IS NULL OR commerceChannel.externalReferenceCode = '')";

	public CommerceChannelPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(CommerceChannel.class);

		setModelImplClass(CommerceChannelImpl.class);
		setModelPKClass(long.class);

		setTable(CommerceChannelTable.INSTANCE);
	}

	/**
	 * Caches the commerce channel in the entity cache if it is enabled.
	 *
	 * @param commerceChannel the commerce channel
	 */
	@Override
	public void cacheResult(CommerceChannel commerceChannel) {
		entityCache.putResult(
			CommerceChannelImpl.class, commerceChannel.getPrimaryKey(),
			commerceChannel);

		finderCache.putResult(
			_finderPathFetchBySiteGroupId,
			new Object[] {commerceChannel.getSiteGroupId()}, commerceChannel);

		finderCache.putResult(
			_finderPathFetchByC_ERC,
			new Object[] {
				commerceChannel.getCompanyId(),
				commerceChannel.getExternalReferenceCode()
			},
			commerceChannel);
	}

	/**
	 * Caches the commerce channels in the entity cache if it is enabled.
	 *
	 * @param commerceChannels the commerce channels
	 */
	@Override
	public void cacheResult(List<CommerceChannel> commerceChannels) {
		for (CommerceChannel commerceChannel : commerceChannels) {
			if (entityCache.getResult(
					CommerceChannelImpl.class,
					commerceChannel.getPrimaryKey()) == null) {

				cacheResult(commerceChannel);
			}
		}
	}

	/**
	 * Clears the cache for all commerce channels.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceChannelImpl.class);

		finderCache.clearCache(CommerceChannelImpl.class);
	}

	/**
	 * Clears the cache for the commerce channel.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceChannel commerceChannel) {
		entityCache.removeResult(CommerceChannelImpl.class, commerceChannel);
	}

	@Override
	public void clearCache(List<CommerceChannel> commerceChannels) {
		for (CommerceChannel commerceChannel : commerceChannels) {
			entityCache.removeResult(
				CommerceChannelImpl.class, commerceChannel);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CommerceChannelImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(CommerceChannelImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceChannelModelImpl commerceChannelModelImpl) {

		Object[] args = new Object[] {
			commerceChannelModelImpl.getSiteGroupId()
		};

		finderCache.putResult(
			_finderPathCountBySiteGroupId, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchBySiteGroupId, args, commerceChannelModelImpl,
			false);

		args = new Object[] {
			commerceChannelModelImpl.getCompanyId(),
			commerceChannelModelImpl.getExternalReferenceCode()
		};

		finderCache.putResult(
			_finderPathCountByC_ERC, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_ERC, args, commerceChannelModelImpl, false);
	}

	/**
	 * Creates a new commerce channel with the primary key. Does not add the commerce channel to the database.
	 *
	 * @param commerceChannelId the primary key for the new commerce channel
	 * @return the new commerce channel
	 */
	@Override
	public CommerceChannel create(long commerceChannelId) {
		CommerceChannel commerceChannel = new CommerceChannelImpl();

		commerceChannel.setNew(true);
		commerceChannel.setPrimaryKey(commerceChannelId);

		commerceChannel.setCompanyId(CompanyThreadLocal.getCompanyId());

		return commerceChannel;
	}

	/**
	 * Removes the commerce channel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceChannelId the primary key of the commerce channel
	 * @return the commerce channel that was removed
	 * @throws NoSuchChannelException if a commerce channel with the primary key could not be found
	 */
	@Override
	public CommerceChannel remove(long commerceChannelId)
		throws NoSuchChannelException {

		return remove((Serializable)commerceChannelId);
	}

	/**
	 * Removes the commerce channel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce channel
	 * @return the commerce channel that was removed
	 * @throws NoSuchChannelException if a commerce channel with the primary key could not be found
	 */
	@Override
	public CommerceChannel remove(Serializable primaryKey)
		throws NoSuchChannelException {

		Session session = null;

		try {
			session = openSession();

			CommerceChannel commerceChannel = (CommerceChannel)session.get(
				CommerceChannelImpl.class, primaryKey);

			if (commerceChannel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChannelException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commerceChannel);
		}
		catch (NoSuchChannelException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected CommerceChannel removeImpl(CommerceChannel commerceChannel) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceChannel)) {
				commerceChannel = (CommerceChannel)session.get(
					CommerceChannelImpl.class,
					commerceChannel.getPrimaryKeyObj());
			}

			if (commerceChannel != null) {
				session.delete(commerceChannel);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (commerceChannel != null) {
			clearCache(commerceChannel);
		}

		return commerceChannel;
	}

	@Override
	public CommerceChannel updateImpl(CommerceChannel commerceChannel) {
		boolean isNew = commerceChannel.isNew();

		if (!(commerceChannel instanceof CommerceChannelModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceChannel.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceChannel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceChannel proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceChannel implementation " +
					commerceChannel.getClass());
		}

		CommerceChannelModelImpl commerceChannelModelImpl =
			(CommerceChannelModelImpl)commerceChannel;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (commerceChannel.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceChannel.setCreateDate(now);
			}
			else {
				commerceChannel.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!commerceChannelModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceChannel.setModifiedDate(now);
			}
			else {
				commerceChannel.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(commerceChannel);
			}
			else {
				commerceChannel = (CommerceChannel)session.merge(
					commerceChannel);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			CommerceChannelImpl.class, commerceChannelModelImpl, false, true);

		cacheUniqueFindersCache(commerceChannelModelImpl);

		if (isNew) {
			commerceChannel.setNew(false);
		}

		commerceChannel.resetOriginalValues();

		return commerceChannel;
	}

	/**
	 * Returns the commerce channel with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce channel
	 * @return the commerce channel
	 * @throws NoSuchChannelException if a commerce channel with the primary key could not be found
	 */
	@Override
	public CommerceChannel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChannelException {

		CommerceChannel commerceChannel = fetchByPrimaryKey(primaryKey);

		if (commerceChannel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChannelException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceChannel;
	}

	/**
	 * Returns the commerce channel with the primary key or throws a <code>NoSuchChannelException</code> if it could not be found.
	 *
	 * @param commerceChannelId the primary key of the commerce channel
	 * @return the commerce channel
	 * @throws NoSuchChannelException if a commerce channel with the primary key could not be found
	 */
	@Override
	public CommerceChannel findByPrimaryKey(long commerceChannelId)
		throws NoSuchChannelException {

		return findByPrimaryKey((Serializable)commerceChannelId);
	}

	/**
	 * Returns the commerce channel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceChannelId the primary key of the commerce channel
	 * @return the commerce channel, or <code>null</code> if a commerce channel with the primary key could not be found
	 */
	@Override
	public CommerceChannel fetchByPrimaryKey(long commerceChannelId) {
		return fetchByPrimaryKey((Serializable)commerceChannelId);
	}

	/**
	 * Returns all the commerce channels.
	 *
	 * @return the commerce channels
	 */
	@Override
	public List<CommerceChannel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce channels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceChannelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @return the range of commerce channels
	 */
	@Override
	public List<CommerceChannel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce channels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceChannelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce channels
	 */
	@Override
	public List<CommerceChannel> findAll(
		int start, int end,
		OrderByComparator<CommerceChannel> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce channels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceChannelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce channels
	 * @param end the upper bound of the range of commerce channels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce channels
	 */
	@Override
	public List<CommerceChannel> findAll(
		int start, int end,
		OrderByComparator<CommerceChannel> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<CommerceChannel> list = null;

		if (useFinderCache) {
			list = (List<CommerceChannel>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_COMMERCECHANNEL);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCECHANNEL;

				sql = sql.concat(CommerceChannelModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CommerceChannel>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the commerce channels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceChannel commerceChannel : findAll()) {
			remove(commerceChannel);
		}
	}

	/**
	 * Returns the number of commerce channels.
	 *
	 * @return the number of commerce channels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_COMMERCECHANNEL);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "commerceChannelId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COMMERCECHANNEL;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CommerceChannelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce channel persistence.
	 */
	public void afterPropertiesSet() {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceChannelPersistenceImpl.class);

		_bundleContext = bundle.getBundleContext();

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class,
			new CommerceChannelModelArgumentsResolver(),
			MapUtil.singletonDictionary(
				"model.class.name", CommerceChannel.class.getName()));

		_finderPathWithPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByCompanyId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"companyId"}, true);

		_finderPathWithoutPaginationFindByCompanyId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] {Long.class.getName()}, new String[] {"companyId"},
			true);

		_finderPathCountByCompanyId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] {Long.class.getName()}, new String[] {"companyId"},
			false);

		_finderPathFetchBySiteGroupId = _createFinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchBySiteGroupId",
			new String[] {Long.class.getName()}, new String[] {"siteGroupId"},
			true);

		_finderPathCountBySiteGroupId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySiteGroupId",
			new String[] {Long.class.getName()}, new String[] {"siteGroupId"},
			false);

		_finderPathFetchByC_ERC = _createFinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"companyId", "externalReferenceCode"}, true);

		_finderPathCountByC_ERC = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"companyId", "externalReferenceCode"}, false);
	}

	public void destroy() {
		entityCache.removeCache(CommerceChannelImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	private BundleContext _bundleContext;

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_COMMERCECHANNEL =
		"SELECT commerceChannel FROM CommerceChannel commerceChannel";

	private static final String _SQL_SELECT_COMMERCECHANNEL_WHERE =
		"SELECT commerceChannel FROM CommerceChannel commerceChannel WHERE ";

	private static final String _SQL_COUNT_COMMERCECHANNEL =
		"SELECT COUNT(commerceChannel) FROM CommerceChannel commerceChannel";

	private static final String _SQL_COUNT_COMMERCECHANNEL_WHERE =
		"SELECT COUNT(commerceChannel) FROM CommerceChannel commerceChannel WHERE ";

	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN =
		"commerceChannel.commerceChannelId";

	private static final String _FILTER_SQL_SELECT_COMMERCECHANNEL_WHERE =
		"SELECT DISTINCT {commerceChannel.*} FROM CommerceChannel commerceChannel WHERE ";

	private static final String
		_FILTER_SQL_SELECT_COMMERCECHANNEL_NO_INLINE_DISTINCT_WHERE_1 =
			"SELECT {CommerceChannel.*} FROM (SELECT DISTINCT commerceChannel.commerceChannelId FROM CommerceChannel commerceChannel WHERE ";

	private static final String
		_FILTER_SQL_SELECT_COMMERCECHANNEL_NO_INLINE_DISTINCT_WHERE_2 =
			") TEMP_TABLE INNER JOIN CommerceChannel ON TEMP_TABLE.commerceChannelId = CommerceChannel.commerceChannelId";

	private static final String _FILTER_SQL_COUNT_COMMERCECHANNEL_WHERE =
		"SELECT COUNT(DISTINCT commerceChannel.commerceChannelId) AS COUNT_VALUE FROM CommerceChannel commerceChannel WHERE ";

	private static final String _FILTER_ENTITY_ALIAS = "commerceChannel";

	private static final String _FILTER_ENTITY_TABLE = "CommerceChannel";

	private static final String _ORDER_BY_ENTITY_ALIAS = "commerceChannel.";

	private static final String _ORDER_BY_ENTITY_TABLE = "CommerceChannel.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceChannel exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommerceChannel exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceChannelPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

	private FinderPath _createFinderPath(
		String cacheName, String methodName, String[] params,
		String[] columnNames, boolean baseModelResult) {

		FinderPath finderPath = new FinderPath(
			cacheName, methodName, params, columnNames, baseModelResult);

		if (!cacheName.equals(FINDER_CLASS_NAME_LIST_WITH_PAGINATION)) {
			_serviceRegistrations.add(
				_bundleContext.registerService(
					FinderPath.class, finderPath,
					MapUtil.singletonDictionary("cache.name", cacheName)));
		}

		return finderPath;
	}

	private ServiceRegistration<ArgumentsResolver>
		_argumentsResolverServiceRegistration;
	private Set<ServiceRegistration<FinderPath>> _serviceRegistrations =
		new HashSet<>();

	private static class CommerceChannelModelArgumentsResolver
		implements ArgumentsResolver {

		@Override
		public Object[] getArguments(
			FinderPath finderPath, BaseModel<?> baseModel, boolean checkColumn,
			boolean original) {

			String[] columnNames = finderPath.getColumnNames();

			if ((columnNames == null) || (columnNames.length == 0)) {
				if (baseModel.isNew()) {
					return FINDER_ARGS_EMPTY;
				}

				return null;
			}

			CommerceChannelModelImpl commerceChannelModelImpl =
				(CommerceChannelModelImpl)baseModel;

			long columnBitmask = commerceChannelModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(
					commerceChannelModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						commerceChannelModelImpl.getColumnBitmask(columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(
					commerceChannelModelImpl, columnNames, original);
			}

			return null;
		}

		private Object[] _getValue(
			CommerceChannelModelImpl commerceChannelModelImpl,
			String[] columnNames, boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] =
						commerceChannelModelImpl.getColumnOriginalValue(
							columnName);
				}
				else {
					arguments[i] = commerceChannelModelImpl.getColumnValue(
						columnName);
				}
			}

			return arguments;
		}

		private static Map<FinderPath, Long> _finderPathColumnBitmasksCache =
			new ConcurrentHashMap<>();

	}

}