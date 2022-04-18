# QA.GURU School
## Lesson 3. Page Object

In this repo we have 1 autotest to check [this](https://demoqa.com/automation-practice-form) registration form. 

Test data are generated via Java Faker for each test run 
![Regustration Form Under Test](src/test/resources/form.png)


Test do 2 steps in serial. First, fill the fields. 
![1st_step](src/test/resources/1st.png)


Second, obtain data from DOM and compare with those sent on previous step
![2nd_step](src/test/resources/2nd.png)


After that press Close button.
