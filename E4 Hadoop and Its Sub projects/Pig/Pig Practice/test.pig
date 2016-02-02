
#Exercice 1: Average note for each student

#load the data files from local to HDFS

copyFromLocal /home/hadoop/Documents/Pig_Pra/data/student.txt student;
copyFromLocal /home/hadoop/Documents/Pig_Pra/data/course.txt course;
copyFromLocal /home/hadoop/Documents/Pig_Pra/data/course_choices.txt course_choice; 

#use relation 'A' to save the student table
A = load 'student' using PigStorage(':') as (Snum:chararray,Sname:chararray,Ssex:chararray,Sage:int,Sdept:chararray);

#use relation 'B' to save the choice
B = load 'course_choice' using PigStorage(',') as (Snum:chararray,Cnum:chararray,note:int);

#use relation 'C' to save the course table
C = load 'course' using PigStorage(',') as (Cnum:chararray,Cname:chararray,preCnum:chararray,credit:int);

#use relation 'D' to join A and B
D = join A by Snum, B by Snum;

#use relation 'E' to save useful elements
E = foreach D generate A::Snum,Sname,note;

#use relation 'F' to group
F = cogroup E by(Snum,Sname);

#use relation 'G' to calcul average notes
G = foreach F generate group.Sname,(SUM(E.note)/COUNT(E));


#Exercice 2: Find students and course who have a note belowing to 90

#Make a filter 
D = filter B by note<90;

#use joining
E = join D by Snum,A by Snum;

#use joining
F = join E by Cnum,C by Cnum;

#projection
G = foreach F generate Sname,Cname,note;



#Exercice 3: Find all students whose pre-course is 'C language'

# Self join
D = load 'course' using PigStorage(',') as (Cnum:chararray,Cname:chararray,preCnum:chararray,credit:int);
E = join C by preCnum, D by Cnum;

F = filter E by D::Cname == 'C Language';
G = foreach F generate C::Cnum;
H = join G by Cnum,B by Cnum;
I = join H by Snum,A by Snum;
J = foreach I generate Sname;












