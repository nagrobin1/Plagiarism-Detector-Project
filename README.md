# Team 33 : Plagiarism Detector

Hello,

This directory consists of our source code for Plagiarism Detector, as our academic project for graduate level course Managing Software Development (CS-5500) at Northeastern University.

## System Requirements:
1) Install Java 6.0 or above
2) Install Node.js


## Steps to run the Plagiarism Detector:
#### 1) Clone the github repository on your local machine
#### 2) Go to `team-33/phaseC/frontend` in your locally cloned directory using terminal
#### 3) Run command `npm install -g bower && npm install -g grunt-cli && npm install && bower install` to install all dependencies
#### 4) Once #3 is successful, run command `grunt serve` to start local server
#### 4) You will be redirected to homepage
#### 5) Go to `team-33/phaseC/backend` and run command `mvn clean install`
#### 6) Once #5 is successful, run command `mvn spring-boot:run` to start our backend server

![Alt text](/screenshots/home.png?raw=true "Home Page")

#### 7) Click on the "Get to work!" or "Login" button. It will redirect you to Login Page
#### 8) Enter username as `Tester` and password as `Test123` and click on "Login" button

![Alt text](/screenshots/login.png?raw=true "Login")

#### 7) Once you are logged in, it will ask you to upload submissions

![Alt text](/screenshots/dashboard.png?raw=true "Dashboard")

#### 8) Drag and drop the zipped directory which contains all submission directories as subdirectories (Please make sure to put all the submission directories in one directory and zip the final directory before uploading it)

![Alt text](/screenshots/upload_instructions.png?raw=true "Upload instructions")

#### 9) Once you have uploaded the submissions, click on "Detect Plagiarism" button to get the results

![Alt text](/screenshots/result.png?raw=true "Result")


## Citations/References:
1) https://tomassetti.me/getting-started-with-javaparser-analyzing-java-code-programmatically/

## Test Suites

All tests for the backend are contained within the BackendTestSuite class: /team-33/source-code/backend/src/test/java/com/msd/team33/BackendTestSuite.java.

## How the Comparison Algorithm Works

The comparison results displayed represent the relative likelihood that one of the submissions from a particular comparison contains plagiarized code; the higher the result, the higher the relative likelihood of plagiarism. Such results are most useful when analyzing multiple submissions— for example, when uploading all submissions from a class for a particular assignment. Each submission is compared with each other submission, and the resulting pairwise comparisons are displayed in descending order of plagiarism likelihood; i.e., the comparisons listed first are the most likely to contain plagiarism. It is recommended that the instructor manually compare submission source code from the top of the results list down, until comparisons no longer seem to actually contain any plagiarized code. Put another way, plagiarism should bubble to the top of this sorted list of results; the further down the list you go, the less likely it is that one of the submissions plagiarized from the other.

The displayed comparison result for a comparison is actually the average of several component “factor” scores, where each “factor” is a different strategy for comparing source code. Presently, the system supports four factors (identifier matching, comment matching, literal matching, and statement matching), and the component scores for relevant factors* are displayed below the overall comparison result.

For the identifier matching strategy, the system compiles a list of names used for classes, interfaces, methods, and variables from each submission. These lists are then compared. The identifier matching score is the percentage of identifiers from the smaller list that also appear in the larger list.

For the comment matching strategy, the system compiles a list of the contents of comments from each submission, extracting the contents from each of the following three types of comments: block comments, javadoc comments, and line comments. As with the identifier matching strategy, these lists of comment contents are compared, and the comment matching score is the percentage of comment contents from the smaller list that also appear in the larger list.

For the literal matching strategy, the system compiles a list of the literal values from each submission: booleans, chars, doubles, ints, null, longs, Strings. As with the first two strategies, these lists of literals are compared, and the literal matching score is the percentage of literals from the smaller list that also appear in the larger list.

Finally, for the statement matching strategy, the system compiles a list of statement blocks from each submission. For each statement block, the system counts the number of each statement type found within that block: assert statement, block statement, break statement, continue statement, do statement, empty statement, explicit constructor invocation statement, expression statement, for each statement, for statement, if statement, labeled statement, local class declaration statement, return statement, switch entry statement, switch statement, synchronized statement, throw statement, unparsable statement, and while statement. If two statement blocks contain the same count for each type of statement, these two statement blocks are considered to be a match. The statement matching score is the percentage of total statements from the submission with fewer statements that are contained within statement blocks that match statement blocks from the other submission.

*If a particular matching strategy is irrelevant for a particular comparison (e.g., if one of the submission contains no comments, the comment matching strategy is irrelevant), that strategy’s factor score (which will always be zero) is no considered when calculating the final matching result.