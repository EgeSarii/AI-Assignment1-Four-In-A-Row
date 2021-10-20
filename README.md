# AI: Principles & Techniques Assignment 1 : Four In A Row 

This is a given assignment in the course AI: Principles & Techniques at Radboud University.  In this assignment, I have implemented MinMax algorithm with pruning and without pruning. I have used Alpha-Beta pruning to decrease the complexity of the algorithm.
I have made comparison between the algorithms with and without pruning about their design choices and implementations. Also I have tested them and discussed the results based on their complexity. The report itself can be found in the file Report.

There are some significant remarks on this project. First of all, I should indicate that even it is an AI project, the concern is on the MinMax algorithm itself, not on the heuristic evaluation. I have used the simple heuristics that was given, and since it is out of scope, I did not improved the heuristics. Because of this, most of time, the AI does weird choices, but calculated correctly. Also since we have to limit the depth of the game tree, because the space complexity will be huge for bigger games.

I have implemented the algorithm in Java and used VSCode. The source code can be dowloaded and also installed on any Java running platform. There are three posssible players : Human, MinMax and AlphaBeta. They can be chosen in the App.java file. Also I didn't implemented the program such that it plays by itself, it gives recommendation for the next move.

It was a good experiment about AI implementations in real world.

