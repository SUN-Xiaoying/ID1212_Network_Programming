# Web application (Quiz)
Your task is to write a quiz with the tomcat application server. The user enters username, password and email on a startpage before the quiz starts and each test has a default number of questions. The questions should be multiple choice checkboxes.

The design shall follow the MVC design pattern with

- HttpServlet(s) (as Controller)
- JavaBeans (as Model)
- jsp-pages (as View)

Netbeans/Tomcat/MySQL is the only environment we are supporting during labs. That does not mean we require you to use this combination but it's up to you to fix a working combination of IDE / application server / DB if you use another combination than stated above.

### Note:

- It's recommended that you use the following db-structure:

- A sql-script for the structure above can be found here.
- After login, the user is presented with the choice of taking one of the available quizzes together with a display of previous results.
- There is no requirement to have an admin interface for adding users, quizzes or questions


**Extra assignment**: Implement it with the Spring Framework (Spring MVC, not Spring Boot) with Thymeleaf. You still need to solve the mandatory task.