# Quiz-Management
Requirements:
  •	Intellij IDE
  •	Postgresql database.

Installation and setup:
1.	Download the project zip from here
2.	Extract the project files using any extractor program (WinRAR, 7zip)
3.	Open the project file you downloaded in Step 1 using IntelliJ
4.	Inside the IntelliJ IDE Go to File-> Project Structure -> Select  Dependencies-> Click the + icon and add postgresql-42.4.0.jar.
5.	Use the dump-quiz-manager-db.sql file available inside the project folder ‘sql’ to create the database
6.	Open DBeaver and Create a New Database.
7.	Right click on the database you created and select tools->restore
8.	In the restore dialog box now select the quiz_manager_db.sql and click Start.
9.	Now the database is cloned.
10.	Go to credentials.properties file in the project and set up your desired admin username, admin password.
11.	Go to database.properties file in the project and set up your url, username, password of you postgresql server
12.	Project is now ready to execute.

On executing you will provided with below three option ,
1.	Admin
2.	Student
3.	Exit

The user has to select the options from above, if the selected choice is 1 then the user needs to give the admin credentials i.e. username and password this will be validated from the (credentials . properties) file. The authenticated user can able to perform all the admin operations which are mentioned below.

1. ADMIN:

    Operations on students who can able to take the quiz:
          
          •	Create a student
          •	Update a student
          •	Read all students
          •	Delete a student
          •	Operations on Quiz: 
              • Create quiz
              •	Update quiz
              •	Delete quiz
              •	Search quiz 
              •	Demo quiz
 
Create a Student:
             
             Admin can create the table for students and can also be able to insert new students. Admin has to enter the student name and student ID (password).

Update a student:
           
           Admin can update the student name and student ID (password) of the student.

Read all students:
             
             Admin can Read the student from the table.

Delete a student:
            
            Admin can delete the student from the table.

After that by choosing the option for operation on the quiz admin has to give the quiz type in which the admin wants to perform. Admin will be provided with two types of quizzes like below:
      
      a)	MCQ
      b)	Open Questions

Operations on quiz:
	
  On MCQ:
      
      •	Admin can create the table for MCQ quiz and can also be able to insert new MCQ quiz. Admin has to enter the quiz sno, topics, question, correct answer, choice1, choice2, choice3, description.
      •	Admin can update the quiz sno, topics, question, correct answer, choice1, choice2, choice3, description.
      •	Admin can delete the quiz from the table.
      •	Admin can view the quiz.
      •	Admin can search the quiz based on particular topic.

On Open Questions:
      
      •	Admin can create the table for Open Questions quiz and can also be able to insert new Open Questions quiz. Admin has to enter the quiz sno, topics, question, correct answer, description.
      •	Admin can update the quiz sno, topics, question, correct answer, description.
      •	Admin can delete the quiz from the table.
      •	Admin can also view the quiz.
      •	Admin can search the quiz based on particular topic.    
	
2. Student:

    •	Once the user chooses option 2, can able to take the quiz by giving the quiz type, topics, and the total number of questions.
    •	The student has to answer every question and will be evaluated at the end.
    •	Finally, it will be exported into the plain text format for this user has to enter the file path.

3. Exit:
	
	  On selecting the option 3 you will be come out of the program.
