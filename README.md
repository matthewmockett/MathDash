# MathDash
MathDash
Group 42
Fall 2024

Created for CS2212 at Western University

About The Project
Our project aims to develop a game that not only teaches valuable lessons, skills, or concepts but also ensures a fun, engaging, and memorable experience for the target audience. The specific game proposed here is a math-themed race designed to test and teach arithmetic skills in an enjoyable way. Players will face a series of arithmetic questions, with their progress in the race dependent on their ability to answer these questions correctly. Success leads to advancement on the track, while incorrect answers result in a setback, adding an element of suspense and competition by racing against computer-controlled opponents. This game aims to cater elementary school students and essential and lifelong skill. With a simple yet interactive graphical user interface (GUI), the game is designed to be accessible and engaging, encouraging players to improve their arithmetic skills through repetitive play.

Required Libraries and Third Party Tools
All elements used in our application were built soley off built in Java libraries.

The only required library required to run our program is JUnit 5 which is required to run the test cases for our software

To build the software, no third party libraries are required to run the acutal game software, however, to run the test case code, JUnit 5 is required.

Installation / Compiling Code
Clone the repo (you will need to have permission to clone)

git clone ssh://git@repo.csd.uwo.ca:7999/compsci2212_w2024/group42.git

UnZip File

Unzip file to local system - varies based on OS 
Move all .Java Files Into SRC Folder and all images/audio/etc out of the SRC Folder

This will vary based on device and IDE specifications 
Running The Code
Navigate to directory of stored code and run the command in your terminal:
java GUI5.java
You may also go into your preferred IDE and run the GUI5 file to start the game
After running this command, the main game interface will appear and you can begin playing the game!

User Guide
To use our software as a user, the software when first loaded places you into the Main Menu screen. From there, the user has options ranging from Play Game, Instructor Dash board, tutorial page, scoreboard, daily bonus, feedback, and the hidden debug mode is on the right at the end of the road. To play a new game or load a saved state, the user can enter their userID that was previously used when they last played which will automatically load their progress, or if they are a new player, they can enter a new userID and their progress will be 0. To play, in the Play Game menu, the user can then select a math section ranging from addition, subtraction, division or multiplication and can then choose levels ranging from 1 to 10 depending on which are unlocked. Levels are unlocked by completing the previous level. In the actual gameplay, the user will compete against a CPU player and must beat them in the race to win. The race is a turn by turn race where the user answers a question and if they get it right move forward, and if they get it wrong they don't move for that question. Going back to the main menu, if the user wants to view the highest scores from other users, they can press the scoreboard button and view the high scores. If the user wants to claim their daily bonus, they can press the daily bonus button from the main menu and will receive their daily bonus for that day. If a user wants to learn how to play, they can press the tutorial button from the main menu to view a step by step presentation of how to play. For teachers, they are able to view all the progress of the users by accessing the teacher dashboard from the main menu. To access the instructor dashboard, the instructor presses the button in the main menu and will be prompted to enter a password which is "ducklover". From there, the instructor can view every single users progress from their score, levels completed, etc. If a user has feedback, they can press the feedback button in the main menu and submit their response. Lastly, to access the debugger mode where all levels are unlocked, the user can select an hidden easter egg which is placed at the top right in the end of the road.

Instructor / Debug Mode Passwords
[ ] Instructor Dashboard: To access the instructor dashboard navigate to the main menu and click the instructor dashboard button and once prompted enter the password below.

 password: ducklover
[ ] Debug Mode:

To access the debug mode, navigate to the main menu and click the hidden button located in the bottom right corner of the road and once prompted enter the hardcoded password below.

` password: debugduck

External resouces used
https://pngimg.com/image/104713 - Image of Race Flag Used In Scoreboard and instructordashboard backgrounds

Shortcuts/Commands
"m" - mute/unmute sound

"s" - save game

"p" - pause game

Developers
GROUP 42

Mohammed Chams

Yijie Wang

Mirna Aziz

Vincent Tsay

Matthew Mockett
