Group member: Jiayi He, Yufei Zhao

NetID: jhe36, yzhao87

Class: 2021_Spring_CSC_242

ReadMe File for Project2

Files:
     Main.java
     ReadCNF.java
     GSAT.java
     Model.java
     SatTest.java

     Main.java: Contains the main method and the calling method of each part to test.
     ReadCNF.java: Read.cnf files contains the representation of clauses and assignments, and the method to input the files.
     Model.java: This class is for part 2, and contains the necessary methods of part2 such as TtEntails, checkAll, PlTrue, KbPlTrue, Clause.
     GSAT: The method of GSAT algorithm which does hillclimbing for at most MAX-FLIPS steps per run, with random restarts up to MAX-TRIES times.
     SatTest: This class is for part 3 Satisfiability Testing, and contains the implementation of GSAT algorithm.
     

How to run:

1. To run the program in terminal (only)! The following is the example we run the program in the terminal.

2. Example: annideMBP:src annie$ java Main
	Part 2: 

	Please enter 1,2,3 to choose the problem.
	1
	Problem 1: Show that {P,P->Q} |= Q
	The answer is: true
	......(just follow each step and get result for each class)

3. Explanations for input when running the program:
    For Part 2:  
	You need enter 1,2,3 to choose the Problem1, Problem2 and Problem3.
	You can input ‘y’ or ‘n’ to choose keep running Part2 or not.

    For Part3:
	You need to Max Tries and Max Flips each times.
	Problem2 N-Queens: You need to enter the number for N (4, 8, 12, 16)
 	Problem3 Pigeonhole: You need to enter the number for pigeons and holes (1~20); 	You also need to input ‘y’ or ’n’ to choose to print tracing or not.

PS(very important, please read carefully).

1.Run the program in the terminal or the path of the file in the program will be wrong.
2.For some parts, the program needs users to input values to continue. We have detailed instructions in the code. Please follow the instruction carefully since wrong input will cause error.
3.for Pigeonhole problem, we turn off the tracing.
4.The program will ask user for necessary parameters such as  MAX-TRIES and MAX-FLIPS.
5.We use part 1 in solving following parts of problems. 
6.We use the files included with the project instructions to test our program and all work successfully.
7.The submission form includes our group's information. The detailed descriptions and instruction of the project are included in the README.txt. Also, there are some important comments in the source code which provide details as well.
8.The Pigeonhole problem may work slowly if the number of pigeons and holes is large, say 15 or greater. So please wait for a while. 


PLEASE FOLLOW THE INSTRUCTION CAREFULLY

