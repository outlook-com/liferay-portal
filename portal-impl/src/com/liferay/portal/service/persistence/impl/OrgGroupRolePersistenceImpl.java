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

package com.liferay.portal.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.NoSuchOrgGroupRoleException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.OrgGroupRole;
import com.liferay.portal.kernel.model.OrgGroupRoleTable;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.persistence.OrgGroupRolePK;
import com.liferay.portal.kernel.service.persistence.OrgGroupRolePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.model.impl.OrgGroupRoleImpl;
import com.liferay.portal.model.impl.OrgGroupRoleModelImpl;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceRegistration;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The persistence implementation for the org group role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class OrgGroupRolePersistenceImpl
	extends BasePersistenceImpl<OrgGroupRole>
	implements OrgGroupRolePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>OrgGroupRoleUtil</code> to access the org group role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		OrgGroupRoleImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the org group roles where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching org group roles
	 */
	@Override
	public List<OrgGroupRole> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the org group roles where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgGroupRoleModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of org group roles
	 * @param end the upper bound of the range of org group roles (not inclusive)
	 * @return the range of matching org group roles
	 */
	@Override
	public List<OrgGroupRole> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the org group roles where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgGroupRoleModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of org group roles
	 * @param end the upper bound of the range of org group roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching org group roles
	 */
	@Override
	public List<OrgGroupRole> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<OrgGroupRole> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the org group roles where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgGroupRoleModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of org group roles
	 * @param end the upper bound of the range of org group roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching org group roles
	 */
	@Override
	public List<OrgGroupRole> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<OrgGroupRole> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByGroupId;
				finderArgs = new Object[] {groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByGroupId;
			finderArgs = new Object[] {groupId, start, end, orderByComparator};
		}

		List<OrgGroupRole> list = null;

		if (useFinderCache) {
			list = (List<OrgGroupRole>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OrgGroupRole orgGroupRole : list) {
					if (groupId != orgGroupRole.getGroupId()) {
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

			sb.append(_SQL_SELECT_ORGGROUPROLE_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(OrgGroupRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<OrgGroupRole>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
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
	 * Returns the first org group role in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org group role
	 * @throws NoSuchOrgGroupRoleException if a matching org group role could not be found
	 */
	@Override
	public OrgGroupRole findByGroupId_First(
			long groupId, OrderByComparator<OrgGroupRole> orderByComparator)
		throws NoSuchOrgGroupRoleException {

		OrgGroupRole orgGroupRole = fetchByGroupId_First(
			groupId, orderByComparator);

		if (orgGroupRole != null) {
			return orgGroupRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchOrgGroupRoleException(sb.toString());
	}

	/**
	 * Returns the first org group role in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org group role, or <code>null</code> if a matching org group role could not be found
	 */
	@Override
	public OrgGroupRole fetchByGroupId_First(
		long groupId, OrderByComparator<OrgGroupRole> orderByComparator) {

		List<OrgGroupRole> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last org group role in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org group role
	 * @throws NoSuchOrgGroupRoleException if a matching org group role could not be found
	 */
	@Override
	public OrgGroupRole findByGroupId_Last(
			long groupId, OrderByComparator<OrgGroupRole> orderByComparator)
		throws NoSuchOrgGroupRoleException {

		OrgGroupRole orgGroupRole = fetchByGroupId_Last(
			groupId, orderByComparator);

		if (orgGroupRole != null) {
			return orgGroupRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchOrgGroupRoleException(sb.toString());
	}

	/**
	 * Returns the last org group role in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org group role, or <code>null</code> if a matching org group role could not be found
	 */
	@Override
	public OrgGroupRole fetchByGroupId_Last(
		long groupId, OrderByComparator<OrgGroupRole> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<OrgGroupRole> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the org group roles before and after the current org group role in the ordered set where groupId = &#63;.
	 *
	 * @param orgGroupRolePK the primary key of the current org group role
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next org group role
	 * @throws NoSuchOrgGroupRoleException if a org group role with the primary key could not be found
	 */
	@Override
	public OrgGroupRole[] findByGroupId_PrevAndNext(
			OrgGroupRolePK orgGroupRolePK, long groupId,
			OrderByComparator<OrgGroupRole> orderByComparator)
		throws NoSuchOrgGroupRoleException {

		OrgGroupRole orgGroupRole = findByPrimaryKey(orgGroupRolePK);

		Session session = null;

		try {
			session = openSession();

			OrgGroupRole[] array = new OrgGroupRoleImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, orgGroupRole, groupId, orderByComparator, true);

			array[1] = orgGroupRole;

			array[2] = getByGroupId_PrevAndNext(
				session, orgGroupRole, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected OrgGroupRole getByGroupId_PrevAndNext(
		Session session, OrgGroupRole orgGroupRole, long groupId,
		OrderByComparator<OrgGroupRole> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ORGGROUPROLE_WHERE);

		sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			sb.append(OrgGroupRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(orgGroupRole)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<OrgGroupRole> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the org group roles where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (OrgGroupRole orgGroupRole :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(orgGroupRole);
		}
	}

	/**
	 * Returns the number of org group roles where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching org group roles
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ORGGROUPROLE_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"orgGroupRole.id.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByRoleId;
	private FinderPath _finderPathWithoutPaginationFindByRoleId;
	private FinderPath _finderPathCountByRoleId;

	/**
	 * Returns all the org group roles where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @return the matching org group roles
	 */
	@Override
	public List<OrgGroupRole> findByRoleId(long roleId) {
		return findByRoleId(roleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the org group roles where roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgGroupRoleModelImpl</code>.
	 * </p>
	 *
	 * @param roleId the role ID
	 * @param start the lower bound of the range of org group roles
	 * @param end the upper bound of the range of org group roles (not inclusive)
	 * @return the range of matching org group roles
	 */
	@Override
	public List<OrgGroupRole> findByRoleId(long roleId, int start, int end) {
		return findByRoleId(roleId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the org group roles where roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgGroupRoleModelImpl</code>.
	 * </p>
	 *
	 * @param roleId the role ID
	 * @param start the lower bound of the range of org group roles
	 * @param end the upper bound of the range of org group roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching org group roles
	 */
	@Override
	public List<OrgGroupRole> findByRoleId(
		long roleId, int start, int end,
		OrderByComparator<OrgGroupRole> orderByComparator) {

		return findByRoleId(roleId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the org group roles where roleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgGroupRoleModelImpl</code>.
	 * </p>
	 *
	 * @param roleId the role ID
	 * @param start the lower bound of the range of org group roles
	 * @param end the upper bound of the range of org group roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching org group roles
	 */
	@Override
	public List<OrgGroupRole> findByRoleId(
		long roleId, int start, int end,
		OrderByComparator<OrgGroupRole> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByRoleId;
				finderArgs = new Object[] {roleId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByRoleId;
			finderArgs = new Object[] {roleId, start, end, orderByComparator};
		}

		List<OrgGroupRole> list = null;

		if (useFinderCache) {
			list = (List<OrgGroupRole>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (OrgGroupRole orgGroupRole : list) {
					if (roleId != orgGroupRole.getRoleId()) {
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

			sb.append(_SQL_SELECT_ORGGROUPROLE_WHERE);

			sb.append(_FINDER_COLUMN_ROLEID_ROLEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(OrgGroupRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(roleId);

				list = (List<OrgGroupRole>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
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
	 * Returns the first org group role in the ordered set where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org group role
	 * @throws NoSuchOrgGroupRoleException if a matching org group role could not be found
	 */
	@Override
	public OrgGroupRole findByRoleId_First(
			long roleId, OrderByComparator<OrgGroupRole> orderByComparator)
		throws NoSuchOrgGroupRoleException {

		OrgGroupRole orgGroupRole = fetchByRoleId_First(
			roleId, orderByComparator);

		if (orgGroupRole != null) {
			return orgGroupRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("roleId=");
		sb.append(roleId);

		sb.append("}");

		throw new NoSuchOrgGroupRoleException(sb.toString());
	}

	/**
	 * Returns the first org group role in the ordered set where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching org group role, or <code>null</code> if a matching org group role could not be found
	 */
	@Override
	public OrgGroupRole fetchByRoleId_First(
		long roleId, OrderByComparator<OrgGroupRole> orderByComparator) {

		List<OrgGroupRole> list = findByRoleId(roleId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last org group role in the ordered set where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org group role
	 * @throws NoSuchOrgGroupRoleException if a matching org group role could not be found
	 */
	@Override
	public OrgGroupRole findByRoleId_Last(
			long roleId, OrderByComparator<OrgGroupRole> orderByComparator)
		throws NoSuchOrgGroupRoleException {

		OrgGroupRole orgGroupRole = fetchByRoleId_Last(
			roleId, orderByComparator);

		if (orgGroupRole != null) {
			return orgGroupRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("roleId=");
		sb.append(roleId);

		sb.append("}");

		throw new NoSuchOrgGroupRoleException(sb.toString());
	}

	/**
	 * Returns the last org group role in the ordered set where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching org group role, or <code>null</code> if a matching org group role could not be found
	 */
	@Override
	public OrgGroupRole fetchByRoleId_Last(
		long roleId, OrderByComparator<OrgGroupRole> orderByComparator) {

		int count = countByRoleId(roleId);

		if (count == 0) {
			return null;
		}

		List<OrgGroupRole> list = findByRoleId(
			roleId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the org group roles before and after the current org group role in the ordered set where roleId = &#63;.
	 *
	 * @param orgGroupRolePK the primary key of the current org group role
	 * @param roleId the role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next org group role
	 * @throws NoSuchOrgGroupRoleException if a org group role with the primary key could not be found
	 */
	@Override
	public OrgGroupRole[] findByRoleId_PrevAndNext(
			OrgGroupRolePK orgGroupRolePK, long roleId,
			OrderByComparator<OrgGroupRole> orderByComparator)
		throws NoSuchOrgGroupRoleException {

		OrgGroupRole orgGroupRole = findByPrimaryKey(orgGroupRolePK);

		Session session = null;

		try {
			session = openSession();

			OrgGroupRole[] array = new OrgGroupRoleImpl[3];

			array[0] = getByRoleId_PrevAndNext(
				session, orgGroupRole, roleId, orderByComparator, true);

			array[1] = orgGroupRole;

			array[2] = getByRoleId_PrevAndNext(
				session, orgGroupRole, roleId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected OrgGroupRole getByRoleId_PrevAndNext(
		Session session, OrgGroupRole orgGroupRole, long roleId,
		OrderByComparator<OrgGroupRole> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ORGGROUPROLE_WHERE);

		sb.append(_FINDER_COLUMN_ROLEID_ROLEID_2);

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
			sb.append(OrgGroupRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(roleId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(orgGroupRole)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<OrgGroupRole> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the org group roles where roleId = &#63; from the database.
	 *
	 * @param roleId the role ID
	 */
	@Override
	public void removeByRoleId(long roleId) {
		for (OrgGroupRole orgGroupRole :
				findByRoleId(
					roleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(orgGroupRole);
		}
	}

	/**
	 * Returns the number of org group roles where roleId = &#63;.
	 *
	 * @param roleId the role ID
	 * @return the number of matching org group roles
	 */
	@Override
	public int countByRoleId(long roleId) {
		FinderPath finderPath = _finderPathCountByRoleId;

		Object[] finderArgs = new Object[] {roleId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ORGGROUPROLE_WHERE);

			sb.append(_FINDER_COLUMN_ROLEID_ROLEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(roleId);

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
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

	private static final String _FINDER_COLUMN_ROLEID_ROLEID_2 =
		"orgGroupRole.id.roleId = ?";

	public OrgGroupRolePersistenceImpl() {
		setModelClass(OrgGroupRole.class);

		setModelImplClass(OrgGroupRoleImpl.class);
		setModelPKClass(OrgGroupRolePK.class);

		setTable(OrgGroupRoleTable.INSTANCE);
	}

	/**
	 * Caches the org group role in the entity cache if it is enabled.
	 *
	 * @param orgGroupRole the org group role
	 */
	@Override
	public void cacheResult(OrgGroupRole orgGroupRole) {
		EntityCacheUtil.putResult(
			OrgGroupRoleImpl.class, orgGroupRole.getPrimaryKey(), orgGroupRole);
	}

	/**
	 * Caches the org group roles in the entity cache if it is enabled.
	 *
	 * @param orgGroupRoles the org group roles
	 */
	@Override
	public void cacheResult(List<OrgGroupRole> orgGroupRoles) {
		for (OrgGroupRole orgGroupRole : orgGroupRoles) {
			if (EntityCacheUtil.getResult(
					OrgGroupRoleImpl.class, orgGroupRole.getPrimaryKey()) ==
						null) {

				cacheResult(orgGroupRole);
			}
		}
	}

	/**
	 * Clears the cache for all org group roles.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>com.liferay.portal.kernel.dao.orm.FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(OrgGroupRoleImpl.class);

		FinderCacheUtil.clearCache(OrgGroupRoleImpl.class);
	}

	/**
	 * Clears the cache for the org group role.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>com.liferay.portal.kernel.dao.orm.FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OrgGroupRole orgGroupRole) {
		EntityCacheUtil.removeResult(OrgGroupRoleImpl.class, orgGroupRole);
	}

	@Override
	public void clearCache(List<OrgGroupRole> orgGroupRoles) {
		for (OrgGroupRole orgGroupRole : orgGroupRoles) {
			EntityCacheUtil.removeResult(OrgGroupRoleImpl.class, orgGroupRole);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		FinderCacheUtil.clearCache(OrgGroupRoleImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			EntityCacheUtil.removeResult(OrgGroupRoleImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new org group role with the primary key. Does not add the org group role to the database.
	 *
	 * @param orgGroupRolePK the primary key for the new org group role
	 * @return the new org group role
	 */
	@Override
	public OrgGroupRole create(OrgGroupRolePK orgGroupRolePK) {
		OrgGroupRole orgGroupRole = new OrgGroupRoleImpl();

		orgGroupRole.setNew(true);
		orgGroupRole.setPrimaryKey(orgGroupRolePK);

		orgGroupRole.setCompanyId(CompanyThreadLocal.getCompanyId());

		return orgGroupRole;
	}

	/**
	 * Removes the org group role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param orgGroupRolePK the primary key of the org group role
	 * @return the org group role that was removed
	 * @throws NoSuchOrgGroupRoleException if a org group role with the primary key could not be found
	 */
	@Override
	public OrgGroupRole remove(OrgGroupRolePK orgGroupRolePK)
		throws NoSuchOrgGroupRoleException {

		return remove((Serializable)orgGroupRolePK);
	}

	/**
	 * Removes the org group role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the org group role
	 * @return the org group role that was removed
	 * @throws NoSuchOrgGroupRoleException if a org group role with the primary key could not be found
	 */
	@Override
	public OrgGroupRole remove(Serializable primaryKey)
		throws NoSuchOrgGroupRoleException {

		Session session = null;

		try {
			session = openSession();

			OrgGroupRole orgGroupRole = (OrgGroupRole)session.get(
				OrgGroupRoleImpl.class, primaryKey);

			if (orgGroupRole == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOrgGroupRoleException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(orgGroupRole);
		}
		catch (NoSuchOrgGroupRoleException noSuchEntityException) {
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
	protected OrgGroupRole removeImpl(OrgGroupRole orgGroupRole) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(orgGroupRole)) {
				orgGroupRole = (OrgGroupRole)session.get(
					OrgGroupRoleImpl.class, orgGroupRole.getPrimaryKeyObj());
			}

			if (orgGroupRole != null) {
				session.delete(orgGroupRole);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (orgGroupRole != null) {
			clearCache(orgGroupRole);
		}

		return orgGroupRole;
	}

	@Override
	public OrgGroupRole updateImpl(OrgGroupRole orgGroupRole) {
		boolean isNew = orgGroupRole.isNew();

		if (!(orgGroupRole instanceof OrgGroupRoleModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(orgGroupRole.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					orgGroupRole);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in orgGroupRole proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom OrgGroupRole implementation " +
					orgGroupRole.getClass());
		}

		OrgGroupRoleModelImpl orgGroupRoleModelImpl =
			(OrgGroupRoleModelImpl)orgGroupRole;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(orgGroupRole);
			}
			else {
				orgGroupRole = (OrgGroupRole)session.merge(orgGroupRole);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		EntityCacheUtil.putResult(
			OrgGroupRoleImpl.class, orgGroupRoleModelImpl, false, true);

		if (isNew) {
			orgGroupRole.setNew(false);
		}

		orgGroupRole.resetOriginalValues();

		return orgGroupRole;
	}

	/**
	 * Returns the org group role with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the org group role
	 * @return the org group role
	 * @throws NoSuchOrgGroupRoleException if a org group role with the primary key could not be found
	 */
	@Override
	public OrgGroupRole findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOrgGroupRoleException {

		OrgGroupRole orgGroupRole = fetchByPrimaryKey(primaryKey);

		if (orgGroupRole == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOrgGroupRoleException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return orgGroupRole;
	}

	/**
	 * Returns the org group role with the primary key or throws a <code>NoSuchOrgGroupRoleException</code> if it could not be found.
	 *
	 * @param orgGroupRolePK the primary key of the org group role
	 * @return the org group role
	 * @throws NoSuchOrgGroupRoleException if a org group role with the primary key could not be found
	 */
	@Override
	public OrgGroupRole findByPrimaryKey(OrgGroupRolePK orgGroupRolePK)
		throws NoSuchOrgGroupRoleException {

		return findByPrimaryKey((Serializable)orgGroupRolePK);
	}

	/**
	 * Returns the org group role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param orgGroupRolePK the primary key of the org group role
	 * @return the org group role, or <code>null</code> if a org group role with the primary key could not be found
	 */
	@Override
	public OrgGroupRole fetchByPrimaryKey(OrgGroupRolePK orgGroupRolePK) {
		return fetchByPrimaryKey((Serializable)orgGroupRolePK);
	}

	/**
	 * Returns all the org group roles.
	 *
	 * @return the org group roles
	 */
	@Override
	public List<OrgGroupRole> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the org group roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgGroupRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org group roles
	 * @param end the upper bound of the range of org group roles (not inclusive)
	 * @return the range of org group roles
	 */
	@Override
	public List<OrgGroupRole> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the org group roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgGroupRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org group roles
	 * @param end the upper bound of the range of org group roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of org group roles
	 */
	@Override
	public List<OrgGroupRole> findAll(
		int start, int end, OrderByComparator<OrgGroupRole> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the org group roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OrgGroupRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of org group roles
	 * @param end the upper bound of the range of org group roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of org group roles
	 */
	@Override
	public List<OrgGroupRole> findAll(
		int start, int end, OrderByComparator<OrgGroupRole> orderByComparator,
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

		List<OrgGroupRole> list = null;

		if (useFinderCache) {
			list = (List<OrgGroupRole>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ORGGROUPROLE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ORGGROUPROLE;

				sql = sql.concat(OrgGroupRoleModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<OrgGroupRole>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
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
	 * Removes all the org group roles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (OrgGroupRole orgGroupRole : findAll()) {
			remove(orgGroupRole);
		}
	}

	/**
	 * Returns the number of org group roles.
	 *
	 * @return the number of org group roles
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ORGGROUPROLE);

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(
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
	public Set<String> getCompoundPKColumnNames() {
		return _compoundPKColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return EntityCacheUtil.getEntityCache();
	}

	@Override
	protected String getPKDBName() {
		return "orgGroupRolePK";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ORGGROUPROLE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return OrgGroupRoleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the org group role persistence.
	 */
	public void afterPropertiesSet() {
		Registry registry = RegistryUtil.getRegistry();

		_argumentsResolverServiceRegistration = registry.registerService(
			ArgumentsResolver.class, new OrgGroupRoleModelArgumentsResolver(),
			HashMapBuilder.<String, Object>put(
				"model.class.name", OrgGroupRole.class.getName()
			).build());

		_finderPathWithPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByGroupId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"groupId"}, true);

		_finderPathWithoutPaginationFindByGroupId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
			true);

		_finderPathCountByGroupId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
			false);

		_finderPathWithPaginationFindByRoleId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByRoleId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"roleId"}, true);

		_finderPathWithoutPaginationFindByRoleId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByRoleId",
			new String[] {Long.class.getName()}, new String[] {"roleId"}, true);

		_finderPathCountByRoleId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRoleId",
			new String[] {Long.class.getName()}, new String[] {"roleId"},
			false);
	}

	public void destroy() {
		EntityCacheUtil.removeCache(OrgGroupRoleImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	private static final String _SQL_SELECT_ORGGROUPROLE =
		"SELECT orgGroupRole FROM OrgGroupRole orgGroupRole";

	private static final String _SQL_SELECT_ORGGROUPROLE_WHERE =
		"SELECT orgGroupRole FROM OrgGroupRole orgGroupRole WHERE ";

	private static final String _SQL_COUNT_ORGGROUPROLE =
		"SELECT COUNT(orgGroupRole) FROM OrgGroupRole orgGroupRole";

	private static final String _SQL_COUNT_ORGGROUPROLE_WHERE =
		"SELECT COUNT(orgGroupRole) FROM OrgGroupRole orgGroupRole WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "orgGroupRole.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No OrgGroupRole exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No OrgGroupRole exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		OrgGroupRolePersistenceImpl.class);

	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"organizationId", "groupId", "roleId"});

	private FinderPath _createFinderPath(
		String cacheName, String methodName, String[] params,
		String[] columnNames, boolean baseModelResult) {

		FinderPath finderPath = new FinderPath(
			cacheName, methodName, params, columnNames, baseModelResult);

		if (!cacheName.equals(FINDER_CLASS_NAME_LIST_WITH_PAGINATION)) {
			Registry registry = RegistryUtil.getRegistry();

			_serviceRegistrations.add(
				registry.registerService(
					FinderPath.class, finderPath,
					HashMapBuilder.<String, Object>put(
						"cache.name", cacheName
					).build()));
		}

		return finderPath;
	}

	private ServiceRegistration<ArgumentsResolver>
		_argumentsResolverServiceRegistration;
	private Set<ServiceRegistration<FinderPath>> _serviceRegistrations =
		new HashSet<>();

	private static class OrgGroupRoleModelArgumentsResolver
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

			OrgGroupRoleModelImpl orgGroupRoleModelImpl =
				(OrgGroupRoleModelImpl)baseModel;

			long columnBitmask = orgGroupRoleModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(orgGroupRoleModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						orgGroupRoleModelImpl.getColumnBitmask(columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(orgGroupRoleModelImpl, columnNames, original);
			}

			return null;
		}

		private Object[] _getValue(
			OrgGroupRoleModelImpl orgGroupRoleModelImpl, String[] columnNames,
			boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] = orgGroupRoleModelImpl.getColumnOriginalValue(
						columnName);
				}
				else {
					arguments[i] = orgGroupRoleModelImpl.getColumnValue(
						columnName);
				}
			}

			return arguments;
		}

		private static Map<FinderPath, Long> _finderPathColumnBitmasksCache =
			new ConcurrentHashMap<>();

	}

}