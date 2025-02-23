/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.data.integration.model.impl;

import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcessLog;
import com.liferay.commerce.data.integration.service.CommerceDataIntegrationProcessLogLocalServiceUtil;

/**
 * The extended model base implementation for the CommerceDataIntegrationProcessLog service. Represents a row in the &quot;CDataIntegrationProcessLog&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceDataIntegrationProcessLogImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceDataIntegrationProcessLogImpl
 * @see CommerceDataIntegrationProcessLog
 * @generated
 */
public abstract class CommerceDataIntegrationProcessLogBaseImpl
	extends CommerceDataIntegrationProcessLogModelImpl
	implements CommerceDataIntegrationProcessLog {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce data integration process log model instance should use the <code>CommerceDataIntegrationProcessLog</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CommerceDataIntegrationProcessLogLocalServiceUtil.
				addCommerceDataIntegrationProcessLog(this);
		}
		else {
			CommerceDataIntegrationProcessLogLocalServiceUtil.
				updateCommerceDataIntegrationProcessLog(this);
		}
	}

}