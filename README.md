# Drinks
🍹 Android App using Kotlin, Clean Architecture, MVVM, ViewModel, LiveData, Coroutines, Hilt, Navigation Component, View Binding, Retrofit, Gson, Room, Glide, CircleImageView, Material Design, and Bottom Navigation. 

This app displays a list of drinks retrieved from this API - https://www.thecocktaildb.com/api.php, as well as detailed information about each drink. There is also searching for drinks, finding a drink of some category, getting a random drink, and searching for an ingredient.

The app uses bottom navigation. There are tabs: cocktails, categories, random cocktail, and ingredients.

The first tab contains the list of cocktails. You can also search for cocktails, and by clicking on a cocktail, a screen with detailed info about the cocktail is shown.

The second tab contains categories. By clicking on a category, a screen with subcategories is opened, and by clicking on a subcategory, the list of cocktails of the chosen subcategory is shown. By clicking on a cocktail, a screen with detailed info about the cocktail is displayed.

The third tab displays a random cocktail. By swiping down, a new random cocktail will be shown.

The last tab allows you to search for an ingredient. You can click on the search button and enter the name of an ingredient. If the ingredient is found, detailed info about this ingredient will be displayed, otherwise you will see no results screen.

In the whole app, when the data is loading, a progress indicator is displayed. In addition, there is a fail screen. It will be displayed if there is no connection, the service is not available, or another error has occurred.

There is also automatic saving of some data to the local database, for example, saving the list of cocktails. That allows to not load the whole list from the API every time a user opens the tab with cocktails, and the user can see the data if there is no internet connection.

![1](https://user-images.githubusercontent.com/76612421/195991064-2f2f1bd9-a39f-4bb7-8964-f0feb95c6718.PNG)
![2](https://user-images.githubusercontent.com/76612421/195991066-8a4f414d-7841-40c1-bb79-59f84d68f42c.PNG)
![3](https://user-images.githubusercontent.com/76612421/195991068-f538d276-0fba-4227-bb9d-cbe2caad30de.PNG)
![4](https://user-images.githubusercontent.com/76612421/195991069-eaf9751a-2a54-4c4b-86d1-a9807211960b.PNG)
![5](https://user-images.githubusercontent.com/76612421/195991072-43819abb-94f3-4bb0-9e28-824bebf3cf41.PNG)
![6](https://user-images.githubusercontent.com/76612421/195991075-30955d0e-e30a-4fb4-a0cf-d52d66207be1.PNG)
![7](https://user-images.githubusercontent.com/76612421/195991079-1e8ead81-931a-473d-81bd-d492c1e64051.PNG)
![8](https://user-images.githubusercontent.com/76612421/195991080-f2ad35f3-5bc1-4f25-ab03-2e20d52d6720.PNG)
![9](https://user-images.githubusercontent.com/76612421/195991084-6457b188-1282-4f32-9143-a99778d97b93.PNG)
![10](https://user-images.githubusercontent.com/76612421/195991091-fca7e45e-0a1b-4a14-98fe-61163379d849.PNG)
![11](https://user-images.githubusercontent.com/76612421/195991094-358bb7cb-e295-4fd5-923d-ffc2637578ce.PNG)
![12](https://user-images.githubusercontent.com/76612421/195991096-0d18a6b7-7c58-49fa-a379-22aeec6d0079.PNG)
![13](https://user-images.githubusercontent.com/76612421/195991097-7273a604-b2e6-4bcc-8893-3b85400989a9.PNG)
![14](https://user-images.githubusercontent.com/76612421/195991116-45e810c1-97db-4afa-9eb3-16f32e998d10.PNG)
![15](https://user-images.githubusercontent.com/76612421/195991120-566c79eb-8246-4d82-8f40-eef87496fed2.PNG)
![16](https://user-images.githubusercontent.com/76612421/195991125-d8f762e6-df5e-4228-b3a4-1df76137f3b3.PNG)
![17](https://user-images.githubusercontent.com/76612421/195991135-2c773dd4-30eb-4887-a14a-b72593b6fc98.PNG)
![18](https://user-images.githubusercontent.com/76612421/195991139-38523e5c-3d7e-4c31-81a9-561ddd13ba94.PNG)
![19](https://user-images.githubusercontent.com/76612421/195991143-a909e57a-3fa5-4edf-b748-47a94ffd8480.PNG)
![20](https://user-images.githubusercontent.com/76612421/195991145-b6e8c4ab-caaa-4eae-88c9-8158afaf4235.PNG)
![21](https://user-images.githubusercontent.com/76612421/195991147-24016173-6353-46b4-9eb6-f868d22e196e.PNG)
![22](https://user-images.githubusercontent.com/76612421/195991149-59cdd99d-150c-444e-83e3-df12537bb0de.PNG)
![23](https://user-images.githubusercontent.com/76612421/195991153-d58ba314-9dd8-4db5-a15e-dfeb277b33da.PNG)
![24](https://user-images.githubusercontent.com/76612421/195991157-bd467439-498b-4bf0-a645-7e78833cb137.PNG)
![25](https://user-images.githubusercontent.com/76612421/195991160-a34620c6-2c65-4320-9793-dfd7fbcacb49.PNG)
![26](https://user-images.githubusercontent.com/76612421/195991166-549d874d-1ca4-4fdb-bd15-8b4e226c4d3f.PNG)
