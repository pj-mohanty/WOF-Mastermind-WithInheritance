# Wheel of Fortune & Mastermind With Inheritance Project
## Project- 4

#Overview

-Introduces a structured hierarchy to support multiple game types.
-Organized into two main sections:
Wheel of Fortune
Adding Mastermind
-This project demonstrates the use of inheritance, polymorphism, and interfaces to create a flexible framework, allowing for smooth integration of new games while maintaining code organization and functionality.

Part 1: Refactoring Wheel of Fortune
Goal: Restructure the existing Wheel of Fortune game with an organized class hierarchy.

Core Classes and Interfaces:

Game (Base Class): Provides core functionality for any game type.
WheelOfFortune (Subclass): Specialized for Wheel of Fortune gameplay.
WheelOfFortuneUserGame & WheelOfFortuneAIGame: Two specific implementations for user and AI gameplay.
Player Interface:
Renamed to GuessingPlayer to make it more versatile.
Enables flexible player behavior through polymorphism, where the nextGuess method adapts based on the player’s actions.
Gameplay Tracking:

Each completed game provides a GameRecord, storing:
Player’s ID
Player’s score
AllGamesRecord:
Collects a list of GameRecord objects, covering results for all players.
Provides access to all player IDs and scores from multiple games

Part 2: Integrating Mastermind and Reworking Wheel of Fortune
Objective: Introduce the Mastermind game while reusing elements of the Wheel of Fortune structure.

Changes for Compatibility:

Guess Input Adjustments:
Updated getGuess and nextGuess methods to return String instead of char.
Wheel of Fortune: Ensures guesses are single-character strings.
Mastermind: Expects guesses as four-character strings (representing color codes).
Player Interface Revision:

The GuessingPlayer Interface (formerly "WheelOfFortunePlayer") was generalized for use across both games.
The name change reflects its expanded purpose for multiple guessing games.

Key Points for Both Versions
The main method relies on playAll() for handling game loops and player interactions, reducing redundancy.
Output: After all games have been played, the main method prints the AllGamesRecord, showing a summary of player performance, top scores, and other statistics.

