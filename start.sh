#!/bin/sh

#echo abc
#//sleep 10
obsActiveFile=$1

#echo $abc

#THESE TWO LINES ARE NEEDED, I disabled them because during dev
read -p "Enter number option: " choiceNum
#choiceNum=3
#echo "suk $fullname"

#/home/pros/.1_Programming/


#/home/pros/.jdks/openjdk-19.0.2/bin/java -cp /home/pros/.0dev/Scripts/obsHackee /home/pros/.0dev/Scripts/obsHackee /home/pros/.0dev/Scripts/hackee/obsActiveFile choiceNum

/home/pros/.jdks/openjdk-19.0.2/bin/java -cp /home/pros/.0dev/Scripts/obshackee classes.ObsHackeeParser $obsActiveFile $choiceNum

#/home/pros/.jdks/openjdk-19.0.2/bin/java -cp /home/pros/Playground/05.May/26/IdeaProjects/SimpleServ/build/classes/java/main spec.ProdParser /home/pros/.1_Programming/Scripts/hackee/obsActiveFile fullname




#sleep 60



#///home/pros/.jdks/openjdk-19.0.2/bin/java -cp /home/pros/Playground/05.May/26/IdeaProjects/SimpleServ/build/classes/java/main spec.ProdParser "Uno" "Dos"

