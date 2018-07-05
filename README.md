# OSRS Skill Tracker

A combination of the [OSRS-Clan-Hiscore-Ranker](https://github.com/WeaverDyl/OSRS-Clan-Hiscore-Ranker) and the [OSRS-Hiscore-Grabber](https://github.com/WeaverDyl/OSRS-Hiscore-Grabber) that I wrote around a year ago. 

This is a clan toolkit for [Old School RuneScape](https://oldschool.runescape.com/). It combines a hiscore system as well as a skill event system.

![image](https://i.imgur.com/3tN9IRq.png)

### Hiscore System
The hiscore system is fairly simple. Many clans keep hiscores of their top members, but it can get tedious having to look each member up to get the data. With this program, you can simply keep a text file containing the names of all the members, load the file into the program, and calculate the relative ranks of every member of the clan for every skill in the game (including, of course, the overall level). Note that you aren't required to load a file, but can also opt to type in, or even paste a list of player names and calculate the results that way.

### Skill Event System

The skill event system is used when a clan hosts a skilling event where the goal is for each player to try to get the most experience in a certain skill in a specified time frame. An example would be a 24 hour thieving event, where whoever gets the most thieving experience in 24 hours wins. Historically, this has been a challenge for big clans to pull off, as getting the data for the start of the event takes time, but then at the end of the event, you have to do the same data collection again, and then, for each player, subtract the starting experience from the final experience, and THEN, rank them in descending order.

I think that's a giant hassle, which is the main point of this software: making skilling events easier to host. With this program, simply enter/load a list of player names, select the skill you're having an event for, and click calculate. Save this data in a file using the `Save Data` button. That is your initial data.

At the end of the event, do the same thing. Save the file just as before after calculating. Then, click the `Results` button. You'll see this window pop up:
![image](https://i.imgur.com/r79i6ME.png)

Load the initial and final data files that you previously saved using the corresponding `Load Data` buttons, and click `Calculate Winners`. Automatically, the program will rank each player in descending order by the amount of experience they gained, and separately by how many levels they gained. It's important to note that the initial and final data must be for the same skill, and must contain the same players.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.