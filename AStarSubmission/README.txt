To utilize the bash scripts first do:
chmod -x CompileAll.sh RunAStar1.sh RunAStar2.sh RunAStar3.sh

Then first run:
./CompileAll.sh

After you run that you should be able to do any of the following:
./RunAStar1.sh
./RunAStar2.sh
./RunAStar3.sh


=====================================================================================================


ALTERNATIVELY:

To compile any of the AStar programs go into the respective folder and:
javac AStar.java

To run any of the AStar programs go into the respective folder and:
java AStar

From there each AStar program asks for a starting city from the list of cities presented.

=====================================================================================================

Information about the AStar in each folder:

AStar1
Uses the hueristic function of: Hueristic_Distance + Total_Path_Length

AStar2
Uses the hueristic function of: Hueristic_Distance + Road_Condition/Danger * .2 * Total_Path_Length

AStar3
Uses the hueristic function of: Hueristic_Distance + Danger/Road_Condition * .2 * Total_Path_Length
