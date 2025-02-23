create table CPDAvailabilityEstimate (
	uuid_ VARCHAR(75) null,
	CPDAvailabilityEstimateId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceAvailabilityEstimateId LONG,
	CProductId LONG,
	lastPublishDate DATE null
);

create table CPDefinitionInventory (
	uuid_ VARCHAR(75) null,
	CPDefinitionInventoryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	CPDefinitionId LONG,
	CPDefinitionInventoryEngine VARCHAR(75) null,
	lowStockActivity VARCHAR(75) null,
	displayAvailability BOOLEAN,
	displayStockQuantity BOOLEAN,
	minStockQuantity INTEGER,
	backOrders BOOLEAN,
	minOrderQuantity INTEGER,
	maxOrderQuantity INTEGER,
	allowedOrderQuantities VARCHAR(75) null,
	multipleOrderQuantity INTEGER
);

create table CommerceAddress (
	externalReferenceCode VARCHAR(75) null,
	commerceAddressId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	name VARCHAR(255) null,
	description STRING null,
	street1 VARCHAR(255) null,
	street2 VARCHAR(255) null,
	street3 VARCHAR(255) null,
	city VARCHAR(75) null,
	zip VARCHAR(75) null,
	commerceRegionId LONG,
	commerceCountryId LONG,
	latitude DOUBLE,
	longitude DOUBLE,
	phoneNumber VARCHAR(75) null,
	defaultBilling BOOLEAN,
	defaultShipping BOOLEAN,
	type_ INTEGER
);

create table CommerceAddressRestriction (
	commerceAddressRestrictionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	commerceCountryId LONG
);

create table CommerceAvailabilityEstimate (
	uuid_ VARCHAR(75) null,
	commerceAvailabilityEstimateId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	title STRING null,
	priority DOUBLE,
	lastPublishDate DATE null
);

create table CommerceCountry (
	uuid_ VARCHAR(75) null,
	commerceCountryId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name STRING null,
	billingAllowed BOOLEAN,
	shippingAllowed BOOLEAN,
	twoLettersISOCode VARCHAR(75) null,
	threeLettersISOCode VARCHAR(75) null,
	numericISOCode INTEGER,
	subjectToVAT BOOLEAN,
	priority DOUBLE,
	active_ BOOLEAN,
	lastPublishDate DATE null,
	channelFilterEnabled BOOLEAN
);

create table CommerceOrder (
	uuid_ VARCHAR(75) null,
	externalReferenceCode VARCHAR(75) null,
	commerceOrderId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceAccountId LONG,
	commerceCurrencyId LONG,
	billingAddressId LONG,
	shippingAddressId LONG,
	commercePaymentMethodKey VARCHAR(75) null,
	transactionId TEXT null,
	commerceShippingMethodId LONG,
	shippingOptionName VARCHAR(255) null,
	purchaseOrderNumber VARCHAR(75) null,
	couponCode VARCHAR(75) null,
	lastPriceUpdateDate DATE null,
	subtotal DECIMAL(30, 16) null,
	subtotalDiscountAmount DECIMAL(30, 16) null,
	subtotalDiscountPercentLevel1 DECIMAL(30, 16) null,
	subtotalDiscountPercentLevel2 DECIMAL(30, 16) null,
	subtotalDiscountPercentLevel3 DECIMAL(30, 16) null,
	subtotalDiscountPercentLevel4 DECIMAL(30, 16) null,
	shippingAmount DECIMAL(30, 16) null,
	shippingDiscountAmount DECIMAL(30, 16) null,
	shippingDiscountPercentLevel1 DECIMAL(30, 16) null,
	shippingDiscountPercentLevel2 DECIMAL(30, 16) null,
	shippingDiscountPercentLevel3 DECIMAL(30, 16) null,
	shippingDiscountPercentLevel4 DECIMAL(30, 16) null,
	taxAmount DECIMAL(30, 16) null,
	total DECIMAL(30, 16) null,
	totalDiscountAmount DECIMAL(30, 16) null,
	totalDiscountPercentageLevel1 DECIMAL(30, 16) null,
	totalDiscountPercentageLevel2 DECIMAL(30, 16) null,
	totalDiscountPercentageLevel3 DECIMAL(30, 16) null,
	totalDiscountPercentageLevel4 DECIMAL(30, 16) null,
	subtotalWithTaxAmount DECIMAL(30, 16) null,
	subtotalDiscountWithTaxAmount DECIMAL(30, 16) null,
	subtotalDiscountPctLev1WithTax DECIMAL(30, 16) null,
	subtotalDiscountPctLev2WithTax DECIMAL(30, 16) null,
	subtotalDiscountPctLev3WithTax DECIMAL(30, 16) null,
	subtotalDiscountPctLev4WithTax DECIMAL(30, 16) null,
	shippingWithTaxAmount DECIMAL(30, 16) null,
	shippingDiscountWithTaxAmount DECIMAL(30, 16) null,
	shippingDiscountPctLev1WithTax DECIMAL(30, 16) null,
	shippingDiscountPctLev2WithTax DECIMAL(30, 16) null,
	shippingDiscountPctLev3WithTax DECIMAL(30, 16) null,
	shippingDiscountPctLev4WithTax DECIMAL(30, 16) null,
	totalWithTaxAmount DECIMAL(30, 16) null,
	totalDiscountWithTaxAmount DECIMAL(30, 16) null,
	totalDiscountPctLev1WithTax DECIMAL(30, 16) null,
	totalDiscountPctLev2WithTax DECIMAL(30, 16) null,
	totalDiscountPctLev3WithTax DECIMAL(30, 16) null,
	totalDiscountPctLev4WithTax DECIMAL(30, 16) null,
	advanceStatus VARCHAR(75) null,
	paymentStatus INTEGER,
	orderDate DATE null,
	orderStatus INTEGER,
	printedNote STRING null,
	requestedDeliveryDate DATE null,
	manuallyAdjusted BOOLEAN,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table CommerceOrderItem (
	externalReferenceCode VARCHAR(75) null,
	commerceOrderItemId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceOrderId LONG,
	commercePriceListId LONG,
	CProductId LONG,
	CPInstanceId LONG,
	parentCommerceOrderItemId LONG,
	quantity INTEGER,
	shippedQuantity INTEGER,
	json TEXT null,
	name STRING null,
	sku VARCHAR(75) null,
	unitPrice DECIMAL(30, 16) null,
	promoPrice DECIMAL(30, 16) null,
	discountAmount DECIMAL(30, 16) null,
	finalPrice DECIMAL(30, 16) null,
	discountPercentageLevel1 DECIMAL(30, 16) null,
	discountPercentageLevel2 DECIMAL(30, 16) null,
	discountPercentageLevel3 DECIMAL(30, 16) null,
	discountPercentageLevel4 DECIMAL(30, 16) null,
	unitPriceWithTaxAmount DECIMAL(30, 16) null,
	promoPriceWithTaxAmount DECIMAL(30, 16) null,
	discountWithTaxAmount DECIMAL(30, 16) null,
	finalPriceWithTaxAmount DECIMAL(30, 16) null,
	discountPctLevel1WithTaxAmount DECIMAL(30, 16) null,
	discountPctLevel2WithTaxAmount DECIMAL(30, 16) null,
	discountPctLevel3WithTaxAmount DECIMAL(30, 16) null,
	discountPctLevel4WithTaxAmount DECIMAL(30, 16) null,
	subscription BOOLEAN,
	deliveryGroup VARCHAR(75) null,
	shippingAddressId LONG,
	printedNote STRING null,
	requestedDeliveryDate DATE null,
	bookedQuantityId LONG,
	manuallyAdjusted BOOLEAN
);

create table CommerceOrderNote (
	externalReferenceCode VARCHAR(75) null,
	commerceOrderNoteId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceOrderId LONG,
	content STRING null,
	restricted BOOLEAN
);

create table CommerceOrderPayment (
	commerceOrderPaymentId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceOrderId LONG,
	commercePaymentMethodKey VARCHAR(75) null,
	content TEXT null,
	status INTEGER
);

create table CommerceRegion (
	uuid_ VARCHAR(75) null,
	commerceRegionId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceCountryId LONG,
	name VARCHAR(75) null,
	code_ VARCHAR(75) null,
	priority DOUBLE,
	active_ BOOLEAN,
	lastPublishDate DATE null
);

create table CommerceShipment (
	commerceShipmentId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceAccountId LONG,
	commerceAddressId LONG,
	commerceShippingMethodId LONG,
	shippingOptionName TEXT null,
	carrier VARCHAR(75) null,
	trackingNumber VARCHAR(75) null,
	shippingDate DATE null,
	expectedDate DATE null,
	status INTEGER
);

create table CommerceShipmentItem (
	commerceShipmentItemId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	commerceShipmentId LONG,
	commerceOrderItemId LONG,
	commerceInventoryWarehouseId LONG,
	quantity INTEGER
);

create table CommerceShippingMethod (
	commerceShippingMethodId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name STRING null,
	description STRING null,
	imageId LONG,
	engineKey VARCHAR(75) null,
	priority DOUBLE,
	active_ BOOLEAN
);

create table CommerceSubscriptionEntry (
	uuid_ VARCHAR(75) null,
	commerceSubscriptionEntryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	CPInstanceUuid VARCHAR(75) null,
	CProductId LONG,
	commerceOrderItemId LONG,
	subscriptionLength INTEGER,
	subscriptionType VARCHAR(75) null,
	subscriptionTypeSettings TEXT null,
	currentCycle LONG,
	maxSubscriptionCycles LONG,
	subscriptionStatus INTEGER,
	lastIterationDate DATE null,
	nextIterationDate DATE null,
	startDate DATE null,
	deliverySubscriptionLength INTEGER,
	deliverySubscriptionType VARCHAR(75) null,
	deliverySubTypeSettings VARCHAR(75) null,
	deliveryCurrentCycle LONG,
	deliveryMaxSubscriptionCycles LONG,
	deliverySubscriptionStatus INTEGER,
	deliveryLastIterationDate DATE null,
	deliveryNextIterationDate DATE null,
	deliveryStartDate DATE null
);