# FavoriteBands

This Android app allows users to search for their favorite artists against the Bandsintown API. When a user clicks the heart icon next to an artist's name, this saves the artist to a list of favorited artists that is persisted to a database. A user can also click on an artist and navigate to a view that displays how many upcoming events that the artist has and the number of people that are tracking them.

### Technologies used:

* Target SDK of 19 to accommodate around 80% of Android users. According to this [graph](https://developer.android.com/about/dashboards/index.html)
* Retrofit is used to handle the network calls. The decision to use Retrofit 2 was based on considering the best option for handling a network request off of the Main UI thread. Async Task was at first considered, but the ease of implementing Retrofit's API and parsing the JSON (GSON) data along with the readability and reusability made using Retrofit an easy decision.
    * When it came time to fetch the data to display both an artist's name along with their photo (hitting two different endpoints), the decision to use Observables and to compose two API calls was easily implemented as well using Retrofit 2.         
* SQLite Database to persist data
* Glide to display images

### Architecture
An MVC architectural pattern was used.

[![mvc.png](https://s1.postimg.org/2rjkfzgw4f/mvc.png)](https://postimg.org/image/8vbcifjkij/)

In the model layer, we have an Artist model with various fields that are populated from our Get request. And then a Singleton store called ArtistLab to interface between the Artist model and the controllers/ database.


In the controller layer, we have two activities. The launching activity, titled MainActivity is where the User can swipe between two Fragments: SearchFragment and FavoriteFragment. And the second Activity is the ArtistActivity where details about a specific artist are displayed. In order to DRY up the code, a third fragment ArtistListFragment is utilized in both the SearchFragment and FavoriteFragment to display a list of Artists.

The View Layer is comprised of many widgets. TextViews, ImageViews, etc.


The Model layer can be found in the package: ModelLayer

The Controllers are packaged by Activity i.e. the ActivityMain package contains the MainActivity and corresponding Fragments (and adapter for its ViewPager) and the ActivityArtist holds the ArtistActivity.

And since it is used by multiple Fragments the ArtistListFragment is found in its own package called FragmentArtistList where you will find it with its adapter.

There are also separate packages to group domain logic for the Database and API calls found in the Database and API packages.



### Challenges
* ViewPager swiping

    I ran into problems with refreshing data with the ViewPager in the MainActivity. There are two tabs (Fragments) that users can swipe between Fragment 1) being the SearchFragment where the query is performed and Fragment 2) being the FavoriteFragment where a list of the user's favorite artists is displayed. 
    
     I ran into problems with the FavoriteFragment reflecting changes made in the SearchFragment (when a user would favorite/unfavorite an artist). With an endless amount of Stackoverflow responses and no concrete explanation and perhaps a bug with the FragmentPagerAdapter, the only fix that would display the new data in the FavoriteFragment causes an unfortunate side effect with the saved outstate used for configuration changes in the SearchFragment. 
     
     Because the fix for reflecting the new data in the FavoriteFragment is to manually remove and add a new SearchFragment with a fragment transaction in the adapter for the ViewPager, this causes the SearchFragment to use its SavedInstanceState Bundle and fire off a query with the saved outstate that was intended to be used when the ActivityManager destroyed the Fragment on configuration changes. So the decision was made to remove the refiring of the search query in the onResume method in the SearchFragment because it was being fired when navigating to that Fragment regardless of a configuration change, which made for an unpleasurable user experience

* Multiple loads of artists in the RecyclerView list. 

    When running the app in the emulator, responses from the User's search query populates the RecylerView multiple times. Yet when running the app from a hardware device, the list of artists only displays each artist once. When adding log statements to the onNext callback method from an emulated device, we can see that the response is logged multiple times. I am wondering if this may be related to the flatmap operator I have implemented. Or maybe updating the UI from onNext needs more consideration than what I currently have setup. My research had introduced me to [DiffUtil](https://developer.android.com/reference/android/support/v7/util/DiffUtil.html) and I will be looking into that along with learning more about RxJava / reactive programming.

 
* Glide ModelLoaders Errors. 

    Glide [doesn't support BitmapDrawable](https://github.com/bumptech/glide/issues/2461) which I am using for the favorite icon and throws errors in the log. I need to find a sustainable way to load these icons and the solution may be to just not use Glide for toggling icons.

### Future Features
* Compare search query against a User's favorited artists
    
    A feature that I think is important is to also compare a User's list of favorite artists against the query they make. So if a User has favorited the artist "Maroon 5", and then performs a search for "Maroon", if "Maroon 5" is a search result, it would be nice to reflect to the User that they have already favorited Maroon 5 and to reflect that in the UI with the favorited icon filled in 
    
* Enhanced UI/UX
     * While a user can search, favorite, and unfavorite artists. The User Experience can be more responsive. Adding loading progress bars and notifying a User when the search query yields no results would make for a better User Experience
     * Error handling. This app currently takes the happy path and assumes that the status of the Get requests is a status 200, I need to implement guard statements for responses that are not "Success"

* Testing
 	
    Muy importante and this app has no tests


### Trello board

To see my thought process and how I tackled building the app check out the [Trello Board](https://trello.com/b/yNnf8c7N), where you can also see more details and what I am currently working on



























