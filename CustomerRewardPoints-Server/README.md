# endpoints

 1) /rewardPoints/customers (HTTP Method - GET)
 
	Sample Response: 
		[
			{
				"id": 111213,
				"firstName": "Surya",
				"lastName": "Cherala"
			},
			{
				"id": 111214,
				"firstName": "Vijay",
				"lastName": "Krishna"
			},
			{
				"id": 111215,
				"firstName": "Abhishek",
				"lastName": "Kumar"
			}
		]
		
 2) /rewardPoints/customer/{customerId}/{year}/{month} (HTTP Method - GET)
 
	Sample Request: http://localhost:9090/rewardPoints/customer/111213/2020/1
	
	Sample Response:
	
		{
			"transactions": [
				{
					"id": 100,
					"customerId": 111213,
					"postedTime": "2020-01-01T09:30:20.000+00:00",
					"amount": 120.0,
					"rewardPoints": 90.0
				},
				{
					"id": 2001,
					"customerId": 111213,
					"postedTime": "2020-01-02T09:30:20.000+00:00",
					"amount": 220.0,
					"rewardPoints": 290
				},
				{
					"id": 2002,
					"customerId": 111213,
					"postedTime": "2020-01-03T09:30:20.000+00:00",
					"amount": 550.0,
					"rewardPoints": 950.0
				}
			],
			"totalRewardPoints": 1330.0
		}
	
	
# reward points calculator
Calculates the reward points of the customer on monthly basis

The getCustomers endpoint fetchs the users in the DB and prepopulate on the Client Side.

The getCustomerRewardPoints endpoint accepts customerId, year and month as 3 parameters from the client side.
This endpoint finds particular transactions related to the customerId for the selected month in the database.
For each transaction of the customer the respective reward points are calculated by calling calculateRewardPoints.
In the end response all the reward points of the customer for the month are summed up and returns the response to client side.
