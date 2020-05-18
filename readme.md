## Java I - Intro to Java

These are assignments and sandboxes for learning and getting familar with Java, several of which include using graphics and interacting with APIs such as Google's Nest API and the Wunderground weather API.

# Java II - Android Development

These are the projects and assignments for my Java II class of my senior year in high school. Java II is where I first learned Android development.

This repository includes assignments and projects and I have worked on during the semester, and since the start, I have taught myself the fundamentals of Android development, as well as learning to navigate around an Android project efficiently.

Do not expect best practices, caching, or performance-enhanced code. Included in this repository is my total knowledge of Android development, since I began learning it. Perhaps one day I will get into best practices, but for now, this is a reminder of my beginnings.


## Assignments

#### BMI Calculator

This is the first assignment for Java II, and it is a simple BMI calculator. Using a Spinner, you can choose between either the Metric or Imperial units of measurement to calculate your Body Mass Index.

#### World Population Calculator

This assignment is a world population calculator. It involves taking the growth rate of the human population, and using the Pe^rt formula, it calculates the population of a given year. It uses a single activity, and a layout file with an input field to grab the desired year.

#### Temperature Converter

This assignment uses three text inputs for Fahrenheit, Celsius, and Kelvin. When you enter a value into a single field and switch focus onto another field, the value is converted into the two other measurements. The six formulas used were tested repeatedly to ensure that the conversions match one another.

#### Wunderground Conditions

Using the Weather Underground API, this application accepts a city and a state, and returns the current weather conditions at that location.

#### Wunderground Forecast

This is somewhat similar to the Wunderground Conditions assignment, except it gives you a five-day forecast. Using the wonderful [Weather Underground Icons](https://github.com/manifestinteractive/weather-underground-icons) created by Ashley Jager, a five-day forecast with images, highs and lows, and the current condition is displayed. This is the first time I used layout includes to display each item.

#### Midterm

For my midterm, I decided to use the [Word Associations API](https://wordassociations.net/en/api) to create an application that gives you associated words for an input. This is the first time I used a RecyclerView to display a list of items. The word, its weight, and its grammatical type is displayed in the list.

#### Nest API

This assignment interacts with Google's [Nest API](https://developers.nest.com/), giving you the name and current status of a Nest Protect, a smoke and carbon monoxide detector, using OAuth Bearer tokens and the [OkHttp](http://square.github.io/okhttp/) API.

#### Barcode Scanner (Work In Progress)

This is, by far, the most complicated Android project I have worked on. Using Google's [Mobile Vision](https://developers.google.com/vision/) API, it uses the phone's camera to track barcodes. When you click a barcode, you get the option to edit it and search Walmart for the barcode, to return any items matching the UPC. I plan on adding several other search options in the future, but I am very excited that I have gotten it to work the way I want it to, even if it is pretty basic in terms of functionality.


#### Lyrics Performance Task

Performance tasks apply to both Java I and Java II, so they tend to be something most students are able to create. This application simply takes the last word out of a string of lyrics, and prints it to the beginning of a ScrollView.
