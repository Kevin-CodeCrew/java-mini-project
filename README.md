# Java mini-Project: Quizzer

In this mini-project, you will build a local file-driven Quizzer Java console application.

### Primary Requirements
* When the program is launched, the program should load (at least) 5 random multiple choice questions from a database of questions stored in a text file called ```quizzer.txt```
* The program should prompt the User for his/her name
* The program should display each of the questions one at a time to the User and allow them to enter A, B, C, or D, tracking their correct answers
* At conclusion of quiz, let the user know the number they got correct and final percentage e.g.
```Kevn, you correctly answered 3 out of 5 questions for a score of 60%```


### Tips and Suggestions
* Load your questions in a data structure that makes it easy to choose your random questions like a ```HashMap```
* Use ```java.util.Random``` to pick your questions (http://www.codecodex.com/wiki/Generate_Random_Numbers_Without_Repetition)
* Start your question database ```quizzer.txt``` by creating a new text file and entering at least 5 questions manually
* You may structure your question records how ever you wish, but each question should have:
```
Question Text
4 possible answers (with only 1 correct answer)
Correct answer flag (1, 2, 3, or 4)
```
A simple record layout might resemble the following (Answer 2 'Float' is the right answer):
```
Which of the following is not a primitive type in Java,int,Float,boolean,char,2
```
* A file with some random questions is included should you choose this record format
* Additional questions can be found here: https://www.geeksforgeeks.org/java-multiple-choice-questions/
* Using JSON data as your record format is an option but you will have to load an additional JAR and add as a module just like we did with the PostgreSQL JDBC driver (https://www.geeksforgeeks.org/parse-json-java/)
* You should create question template method that you can re-use that takes a question record and outputs a formatted question with answers, prompts for an answer, and grades their response


### Challenges
* Add a prompt at startup that let's the User specify how many questions should be in the quiz
* Add an option to allow User to skip to the next question (The Quizzer should return to skipped questions after others have been answered)
* At end of quiz, list any questions they missed along with correct answers
* Store your questions in PostgreSQL as opposed to a text file

