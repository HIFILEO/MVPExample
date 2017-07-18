#STATIC LEAK EXAMPLE - DO NOT MERGE
THIS BRANCH IS NOT TO BE MERGE IN. It's here only as an example of static references causing memory problems.

##ISSUE
Using a static reference to hold data for activities, in order to avoid the rotation
restore problem, has three major issues. 

1. Static data is not cleared upon activity death. Meaning we are bloating the size of the 
application's foot print. Which means application is more likely to be removed from memory by OS.
2. Static data is not saved (normally). This means when the activity is re-created after the OS removes
<b>application</b> from memory, you can't restore your state. 
3. You cannot use the same activity class more than once at the same time because they will both
rely on the same static reference. This means if you have an Activity called A and you create two 
instances of A at the same time (A1 & A2), both will reference and modify the same static variable.

Note - although you can use SingleInstance here to mitigate this problem, it's not true you want
 to do that in every case. Ex - sharing an activity that simply launches a webpage from w/in the app.
 
## How to reproduce
In order to reproduce this static memory leak when the OS gets destroyed you do the following steps.
 
1. Launch app from IDE
2. Click on a list item so detail page loads.
3. Using ADB, kill the process.
4. Relaunch app from minimizer. Detail page is re-created.
5. Hit back button, watch main page not load data. 

### Logcat

The output from the above steps will be:

Launch app

07-17 18:37:05.283 8219-8219/? I/MvpExampleApplication: ***** onCreate() - Application *****
07-17 18:37:05.446 8219-8219/? I/NowPlayingActivity: ***** onCreate(NEW)  - static isEmpty *****
07-17 18:37:06.690 8219-8219/com.example.mvpexample I/NowPlayingActivity: ***** onStart() *****
07-17 18:37:06.691 8219-8219/com.example.mvpexample I/NowPlayingActivity: ***** onResume() *****
07-17 18:37:30.362 8219-8219/com.example.mvpexample I/NowPlayingActivity: ***** onPause() *****

Select Item In List

07-17 18:37:30.366 8219-8219/com.example.mvpexample I/MovieDetailsActivity: ***** onCreate(NEW) *****
07-17 18:37:30.381 8219-8219/com.example.mvpexample I/MovieDetailsActivity: ***** onStart() *****
07-17 18:37:30.383 8219-8219/com.example.mvpexample I/MovieDetailsActivity: ***** onResume() *****
07-17 18:37:31.044 8219-8219/com.example.mvpexample I/NowPlayingActivity: ***** onSaveInstanceState() *****
07-17 18:37:31.050 8219-8219/com.example.mvpexample I/NowPlayingActivity: ***** onStop() *****
07-17 18:37:31.058 8219-8219/com.example.mvpexample I/NowPlayingActivity: ***** onDestroy() *****
07-17 18:37:34.694 8219-8219/com.example.mvpexample I/MovieDetailsActivity: ***** onPause() *****
07-17 18:37:34.845 8219-8219/com.example.mvpexample I/MovieDetailsActivity: ***** onSaveInstanceState() *****
07-17 18:37:34.865 8219-8219/com.example.mvpexample I/MovieDetailsActivity: ***** onStop() *****
07-17 18:37:34.927 8219-8219/com.example.mvpexample I/MovieDetailsActivity: ***** onDestroy() *****

KILL process using ADB. Then relaunch. 

07-17 18:44:53.662 13788-13788/com.example.mvpexample I/MvpExampleApplication: ***** onCreate() - Application *****
07-17 18:44:53.796 13788-13788/com.example.mvpexample I/MovieDetailsActivity: ***** onCreate(FROM SAVED STATE *****
07-17 18:44:53.983 13788-13788/com.example.mvpexample I/MovieDetailsActivity: ***** onStart() *****
07-17 18:44:53.987 13788-13788/com.example.mvpexample I/MovieDetailsActivity: ***** onResume() *****

HIT BACK BUTTON 

07-17 18:54:12.989 13788-13788/com.example.mvpexample I/MovieDetailsActivity: ***** finish() *****
07-17 18:54:12.989 13788-13788/com.example.mvpexample I/MovieDetailsActivity: ***** onPause() *****
07-17 18:54:13.027 13788-13788/com.example.mvpexample I/NowPlayingActivity: ***** onCreate(FROM SAVED STATE  - static isEmpty *****
07-17 18:54:13.564 13788-13788/com.example.mvpexample I/NowPlayingActivity: ***** onStart() *****
07-17 18:54:13.581 13788-13788/com.example.mvpexample I/NowPlayingActivity: ***** onResume() *****
07-17 18:54:14.138 13788-13788/com.example.mvpexample I/MovieDetailsActivity: ***** onStop() *****
07-17 18:54:14.140 13788-13788/com.example.mvpexample I/MovieDetailsActivity: ***** onDestroy() *****