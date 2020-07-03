Simple app made up of two screens.

First screen shows list of articles from NewsApi. Articles are saved local database. If locally saved articles exist and are not older than 5 minutes they will be shown, otherwise the articles will be fetched remotely from the server.

If there is an error while making request to NewsApi an alert will be shown.

When user on one of the articles in the list a second screen with carousel of articles will be shown.

<div float="left">
  <img src="https://github.com/amirjanus/assets/blob/master/news-reader-mvp/Screenshot_1593775155.png" width="200" />
  <img src="https://github.com/amirjanus/assets/blob/master/news-reader-mvp/Screenshot_1593775338.png" width="200" />
  <img src="https://github.com/amirjanus/assets/blob/master/news-reader-mvp/Screenshot_1593775198.png" width="200" />
</div>

How to run app

1. Add “key.js” file in project root directory and in it create object “keys” with “newsApi” property.
