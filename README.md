"# Squash-Ball-Game" 

This is a simple game that is impemented using OOP, Computational Geometry, and 
thread concepts. It uses the Java programming language. 

To run the game, you just need to copy all the files into a Java project, and run 
the code. A GUI window will appear with two components:

1. The player represented as a red rectangle at the bottom of the screen.
2. The Squash Ball represented as an octagon.

The ball will spawn in different positions giving the game a sense of randomness 
every time you decide to play the game. The ball uses normal physics by which
it gets knocked in a predictable direction every time it interacts with the 
boundaries of the map.

The player can move the rectangle using the left and right arrow keys, and every 
time the player pushes the ball back, it gets faster until it reaches its maximum
velocity. The game will keep track of how many times the player pushes the ball 
back.

When the ball hits the bottom part of the frame, the game is stopped and a pop-up
window will be printed presenting the score that the player achieved.
