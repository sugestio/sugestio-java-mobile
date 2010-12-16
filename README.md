# Overview

This is a lightweight Java library for interfacing with the [Sugestio](http://www.sugestio.com) 
recommendation service. It is compatible with Android 2.x. Data is submitted as POST variables.  

## About Sugestio

Sugestio is a scalable and fault tolerant service that now brings the power of 
web personalisation to all developers. The RESTful web service provides an easy to use 
interface and a set of developer libraries that enable you to enrich 
your content portals, e-commerce sites and mobile applications.

### Access credentials and the Sandbox

To access the Sugestio service, you need only an account name. To run the examples from the 
tutorial, you can use the following credentials:

* account name: <code>sandbox</code>

The Sandbox is a *read-only* account. You can use these credentials to experiment 
with the service. The Sandbox can give personal recommendations for users 1 through 5, 
and similar items for items 1 through 5.

When you are ready to work with real data, you may apply for a developer account through 
the [Sugestio website](http://www.sugestio.com).  

## About this library

### Important notice!

This library is intended to be used from a mobile device, e.g. an Android smart phone. Normally, requests
to the recommendation service need to be signed with an OAuth key pair. This would imply exposing 
your OAuth credentials to every user of your mobile app, defeating the purpose of signing requests. 
In order to use this library, security on your account would have to be lifted. Contact us for more information.

### Features

The following [API](http://www.sugestio.com/documentation) features are implemented:

* get personalized recommendations for a given user
* get items that are similar to a given item
* submit user activity (consumptions): clicks, purchases, ratings, ...
* submit item metadata: description, location, tags, categories, ...  	
* submit user metadata: gender, location, birthday, ...

### Requirements

The library uses [google-gson](http://code.google.com/p/google-gson/) to convert JSON to Java 
objects and vice-versa. [HttpClient 4](http://hc.apache.org/) is used to perform unsigned 
HTTP requests. The Android 2.x platform already includes HttpClient 4.

## Quick start

	SugestioClient client = new SugestioClient("sandbox");
		
	Consumption consumption = new Consumption();
	consumption.setUserid("1");
	consumption.setItemid("x");
	consumption.setType("RATING");
	consumption.setDetail("STAR:5:1:3");
	consumption.setDate("NOW");
	
	SugestioResult result = client.addConsumption(consumption);	

See src/example/Example.java for more sample code.