# Cinema Room Manager

A JetBrains Academy Project written using Java and Intellij Idea

## Description

A command line app that manages a cinema theatre. Simulates selling tickets, 
checking available seats, and displaying sales statistics

## Instructions

The Cinema Room Manager options are Show the seats, Buy a ticket, Statistics, 
Exit.

* Option 1. Show the seats - displays a 2d chart of seats with rows and columns.
* Option 2. Buy a ticket - choose a row number and a seat Number in that row.
* Option 3. Statistics - displays number of purchased tickets, percentage of sold seats, current income, total income.
* Option 0. Exit - exit the program.


    Enter the number of rows: (enter a number of rows for the seating chart)

    Enter the number of seats in each row: (enter a number of seats per row)

1. Show the seats

        Cinema:
          1 2 3 4 5
        1 S S S S S
        2 S S S S S
        3 S S B S S
        4 S S S S S
        5 S S S S S

        Legend: S = available seat, B = bought seat

2. Buy a ticket

    >Enter a row number:<br>
    >2     
    enter a seat Number in that row:<br>
    >2

3. Statistics

    >Number of purchased tickets: 1<br>
    Percentage: 4.00%<br>
    Current income: $10<br>
    Total income: $250

0. Exit - exit the program

## Installation

Run app from Intellij Idea after cloning or...

* Clone the repository into directory CinemaRoomManager.
* Open a command prompt window and go to the directory where you save the program.
* Change to directory - CinemaRoomManager\src\main\java\org\example
* Compile code - javac *.java
* Change directory to java - CinemaRoomManager\src\main\java
* Run app - java org.example.Cinema

## Sources

https://hyperskill.org/projects/133?track=8


    
        