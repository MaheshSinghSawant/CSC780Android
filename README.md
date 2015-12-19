# CSC780 Android Bolt Fitness Tracker

Bolt Fitness Tracker is an android application created as a class project at SF State university.
It is a fitness tracking app that helps you track various physical and nutritional functions like:
♣	Number of steps taken by the user
♣	Calories consumed, the user just has to enter the food item he/she had.
♣	Calories Expended.
♣	Track the route taken by the user on the map.

# Pre-Requisites:

Before you launch the app, there are a few things you have to do to ensure everything runs smoothly:

1.	Log in to google developers console and make a new project , link : https://console.developers.google.com
2.	Enable the following APIs with package name: com.example.mahesh.boltfitnesstracker

                                           ♣	Google Maps Android API
                                           ♣	Google + API
3.	Authenticate the APIs and place the keys in the strings.xml.
4.	Internet Connection is required for the map and Nutrition functionalities.
5.  Min SDKVersion 21

# How to use the app

After launching the app you are prompted to sign in using your google+ information.
After logging in you are taken to the Homescreen. The Homescreen consists of a timer which has been implemented as a service. It also shows the calories burned and the number of steps taken by the user. The number of steps taken is calculated using the in built sensors of the Nexus 5. The user can start, play and stop the timer using the play and stop buttons.

![alt tag](https://cloud.githubusercontent.com/assets/14020237/11911833/2405aabc-a5d8-11e5-9d87-be308ae601a4.png)

The map icon located on the bottom right corner allows the user to track their path on the map. It can be used to track the users path taken. (Press start button and then Reset Camera Button)

![alt tag](https://cloud.githubusercontent.com/assets/14020237/11911834/258992d6-a5d8-11e5-8f46-28aff149930e.png
)

The Nutrition Screen is accessed via the navigation drawer. The user can search and add the food item he/she had to which would then lead to a search results page and then the food item clicked on returns the calories consumed.
![alt tag](https://cloud.githubusercontent.com/assets/14020237/11911836/27e6d0a2-a5d8-11e5-86e6-fc21789e87b0.png)
![alt tag](https://cloud.githubusercontent.com/assets/14020237/11911893/51e01b1e-a5da-11e5-8685-f3fc7b451e43.png)
![alt tag](https://cloud.githubusercontent.com/assets/14020237/11911894/54114656-a5da-11e5-85b6-e97f308d0d52.png)
