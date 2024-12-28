package rest_assured_practice.main.files;

public class Payload {

	public static String AddPlace() {
		return """
                {\r
                  "location": {\r
                    "lat": -38.383494,\r
                    "lng": 33.427362\r
                  },\r
                  "accuracy": 50,\r
                  "name": "Rahul Shetty Academy",\r
                  "phone_number": "(+91) 983 893 3937",\r
                  "address": "29, side layout, cohen 09",\r
                  "types": [\r
                    "shoe park",\r
                    "shop"\r
                  ],\r
                  "website": "http://rahulshettyacademy.com",\r
                  "language": "French-IN"\r
                }\r
                """;
	}
	
	public static String CoursePrice() {
		return """
                {\r
                  "dashboard": {\r
                    "purchaseAmount": 1162,\r
                    "website": "rahulshettyacademy.com"\r
                  },\r
                  "courses": [\r
                    {\r
                      "title": "Selenium Python",\r
                      "price": 50,\r
                      "copies": 6\r
                    },\r
                    {\r
                      "title": "Cypress",\r
                      "price": 40,\r
                      "copies": 4\r
                    },\r
                    {\r
                      "title": "RPA",\r
                      "price": 45,\r
                      "copies": 10\r
                    },\r
                     {\r
                      "title": "Appium",\r
                      "price": 36,\r
                      "copies": 7\r
                    }\r
                    \r
                    \r
                    \r
                  ]\r
                }\r
                """;
	}

	public static String addBook() {
        return """
                {
                "name":"Learn Appium Automation with Java",
                "isbn":"bcd",
                "aisle":"2227330",
                "author":"John foe"
                }
                """;
	}
}

