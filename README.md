rest-api-sdk-android
=========================

Hover API Rest SDK for Android

Welcome to the Hover SDK for Android, this SDK is for building apps for Android-powered devices based on the Hover API. The Hover SDK for Android makes it easy to integrate a full Hover API services into Android apps. 


Requirements
============

* Java JDK 7 or higher
* Android SDK last version
* Dependency Gson for json serialization


SDK Integration and Configuration
============

Build all Hover/src code into a single jar file as a library and then add to your project.

The configuration for the sdk is based on an properties file where you must provide:

* HTTP parameters
* Tenant configuration
* Credentials

Configuring your sdk is easy, first you must create a file called `sdk_config.properties` like placed on `assets` folder in your application project. Secondly you must provide the name properties file and the context for loading as a resource,

```java
	// SDK initializaton
	SDKTools.initialize("sdk_config.properties", getApplicationContext());
```
Now the SDK is ready for use!! 


Manage the request
============

All requests to the RESTful API on the SDK are made using a serialized class, so, each request has their own translation from json to class, for example: to register a single user use User class and its attributes instead build a json file.

> NOTE: If you want add more attributes to any serialized class, then just extends from the desired class.


Manage the response
======================

All responses from the RESTful API on the SDK are managed by ```Response``` class, this class stores the HTTP Status Code, the raw response (json, xml, etc ...)  as a string and the body response casted to a desired class.
For example if you register a user, then your response instance stores the serialized object, just provide the class to map:

`User userInfo = (User) response.getBodyT(User.class);`.


SDK Samples
====================

Under `Example` directory exists a bunchet of code snippets that allow testing diferent scenarios and shows you how to use the API with the SDK.

Registering a user
------
Shows how to register an user using the default fields and extending of your own user settings


About
=====

You can find more info about courses of how to use the API, SDK's or integration of Hover into your app,
visiting us at: http:// or mail us: thehover@hovanetworks.support.com
