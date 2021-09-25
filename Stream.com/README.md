Stream.com had major corruption of their database. During electricity outage there were problems with data integrity and their are looking for your help.You need to create a program that will process 3 files (streamer, following and followers) and save data in the program

Based on processed information from files, generate following reports:

All follower that have made at least one follow [output result in txt file] 
Followers with multiple follows of one streamer [output result in txt file]
All followers that have wrong calculation of followed streamers (from followers.txt file) [output result in txt file]
Follower(s) with most follows in total, with removed duplicate follows. Calculate using threads [output result in txt file] 
Output original files again, but this time without duplicate following information
 

File: streamer.txt (streamer name)
Blue owl
Green dragon
Red sloth
Black lemming

File: followings.txt (follower name, streamer name)
calm.spectator, Black lemming
Annoying commenter, Red sloth
calm.spectator, Black lemming
joe2371, Green dragon
muddy__chair, Blue owl
calm.spectator, Black lemming
muddy__chair, Green dragon
muddy__chair, Green dragon
calm.spectator, Red sloth
joe2371, Black lemming
calm.spectator, Red sloth

File: followers.txt (follower name, total subscribers following)
calm.spectator, 2
Annoying commenter, 1
joe2371, 3
muddy__chair, 3