<%--
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
--%>

<%@ include file="/admin/init.jsp" %>

<%
String navigation = ParamUtil.getString(request, "navigation", "credentials");

String redirect = ParamUtil.getString(request, "redirect");

long oAuth2ApplicationId = 0;

OAuth2Application oAuth2Application = oAuth2AdminPortletDisplayContext.getOAuth2Application();

if (oAuth2Application != null) {
	oAuth2ApplicationId = oAuth2Application.getOAuth2ApplicationId();
}

String oAuth2ApplicationIdString = String.valueOf(oAuth2ApplicationId);
String headerTitle = LanguageUtil.get(request, "add-o-auth2-application");

if (oAuth2Application != null) {
	headerTitle = oAuth2Application.getName();
}

renderResponse.setTitle(headerTitle);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

boolean showTreeScopesView = false;

if (request.getAttribute(OAuth2ProviderWebKeys.ASSIGN_SCOPES_TREE_DISPLAY_CONTEXT) != null) {
	showTreeScopesView = true;
}
%>

<c:if test="<%= oAuth2Application != null %>">
	<clay:navigation-bar
		navigationItems='<%=
			new JSPNavigationItemList(pageContext) {
				{
					add(
						navigationItem -> {
							navigationItem.setActive(navigation.equals("credentials"));

							PortletURL portletURL = renderResponse.createRenderURL();

							portletURL.setParameter("mvcRenderCommandName", "/admin/update_oauth2_application");
							portletURL.setParameter("navigation", "credentials");
							portletURL.setParameter("redirect", redirect);
							portletURL.setParameter("oAuth2ApplicationId", oAuth2ApplicationIdString);

							navigationItem.setHref(portletURL.toString());

							navigationItem.setLabel(LanguageUtil.get(httpServletRequest, "credentials"));
						});

					add(
						navigationItem -> {
							navigationItem.setActive(navigation.equals("assign_scopes"));

							PortletURL portletURL = renderResponse.createRenderURL();

							portletURL.setParameter("mvcRenderCommandName", "/admin/assign_scopes");
							portletURL.setParameter("navigation", "assign_scopes");
							portletURL.setParameter("redirect", redirect);
							portletURL.setParameter("oAuth2ApplicationId", oAuth2ApplicationIdString);

							navigationItem.setHref(portletURL.toString());

							navigationItem.setLabel(LanguageUtil.get(httpServletRequest, "scopes"));
						});

					if (oAuth2AdminPortletDisplayContext.hasViewGrantedAuthorizationsPermission()) {
						add(
							navigationItem -> {
								navigationItem.setActive(navigation.equals("application_authorizations"));

								PortletURL portletURL = renderResponse.createRenderURL();

								portletURL.setParameter("mvcRenderCommandName", "/admin/view_oauth2_authorizations");
								portletURL.setParameter("navigation", "application_authorizations");
								portletURL.setParameter("redirect", redirect);
								portletURL.setParameter("oAuth2ApplicationId", oAuth2ApplicationIdString);

								navigationItem.setHref(portletURL.toString());

								navigationItem.setLabel(LanguageUtil.get(httpServletRequest, "authorizations"));
							});
					}
				}
			}
		%>'
	/>
</c:if>

<c:choose>
	<c:when test='<%= navigation.equals("credentials") && ((oAuth2Application == null) || oAuth2AdminPortletDisplayContext.hasUpdatePermission(oAuth2Application)) %>'>
		<liferay-util:include page="/admin/edit_application_credentials.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= (oAuth2Application != null) && navigation.equals("assign_scopes") && oAuth2AdminPortletDisplayContext.hasUpdatePermission(oAuth2Application) && showTreeScopesView %>'>
		<liferay-util:include page="/admin/assign_scopes_tree.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= (oAuth2Application != null) && navigation.equals("assign_scopes") && oAuth2AdminPortletDisplayContext.hasUpdatePermission(oAuth2Application) && !showTreeScopesView %>'>
		<liferay-util:include page="/admin/assign_scopes.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= (oAuth2Application != null) && navigation.equals("application_authorizations") && oAuth2AdminPortletDisplayContext.hasViewGrantedAuthorizationsPermission() %>'>
		<liferay-util:include page="/admin/application_authorizations.jsp" servletContext="<%= application %>" />
	</c:when>
</c:choose>