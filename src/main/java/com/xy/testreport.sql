
 DROP TABLE IF EXISTS `College`;  
 CREATE TABLE `College`  ( 
  
 `name` varchar(255) , 
 `location` varchar(255) , 
 `budget` varchar(255) , 
 `profession` varchar(255) , 
 ) ;
 DROP TABLE IF EXISTS `Course`;  
 CREATE TABLE `Course`  ( 
  
 `CourseID` varchar(255) , 
 `SerialNumber` varchar(255) , 
 `CourseName` varchar(255) , 
 `College` varchar(255) , 
 `Credit` varchar(255) , 
 `FacultyNumber` varchar(255) , 
 ) ;
 DROP TABLE IF EXISTS `CourseList`;  
 CREATE TABLE `CourseList`  ( 
  
 `FacultyNumber` varchar(255) , 
 `StudentID` varchar(255) , 
 `SerialNumber` varchar(255) , 
 `CourseID` varchar(255) , 
 `semester` varchar(255) , 
 `year` varchar(255) , 
 `CourseName` varchar(255) , 
 `StuName` varchar(255) , 
 `grades` varchar(255) , 
 ) ;
 DROP TABLE IF EXISTS `CourseSelection`;  
 CREATE TABLE `CourseSelection`  ( 
  
 `StudentID` varchar(255) , 
 `SerialNumber` varchar(255) , 
 `CourseID` varchar(255) , 
 `semester` varchar(255) , 
 `year` varchar(255) , 
 `courseName` varchar(255) , 
 ) ;
 DROP TABLE IF EXISTS `CourseStartList`;  
 CREATE TABLE `CourseStartList`  ( 
  
 `CourseID` varchar(255) , 
 `SerialNumber` varchar(255) , 
 `year` varchar(255) , 
 `semester` varchar(255) , 
 `location` varchar(255) , 
 `timeid` varchar(255) , 
 `courseName` varchar(255) , 
 `credits` varchar(255) , 
 ) ;
 DROP TABLE IF EXISTS `ExamArrangement`;  
 CREATE TABLE `ExamArrangement`  ( 
  
 `SerialNumber` varchar(255) , 
 `CourseID` varchar(255) , 
 `semester` varchar(255) , 
 `year` varchar(255) , 
 `location` varchar(255) , 
 `timeid` varchar(255) , 
 ) ;
 DROP TABLE IF EXISTS `Location`;  
 CREATE TABLE `Location`  ( 
  
 `location` varchar(255) , 
 `status` varchar(255) , 
 ) ;
 DROP TABLE IF EXISTS `Manager`;  
 CREATE TABLE `Manager`  ( 
  
 `ID` varchar(255) , 
 `password` varchar(255) , 
 ) ;
 DROP TABLE IF EXISTS `PreCourse`;  
 CREATE TABLE `PreCourse`  ( 
  
 `studentID` varchar(255) , 
 `CourseID` varchar(255) , 
 `SerialNumber1` varchar(255) , 
 `preCourseID` varchar(255) , 
 `SerialNumber2` varchar(255) , 
 `pass` varchar(255) , 
 ) ;
 DROP TABLE IF EXISTS `PreSet`;  
 CREATE TABLE `PreSet`  ( 
  
 `studentID` varchar(255) , 
 `SerialNumber` varchar(255) , 
 `CourseID` varchar(255) , 
 `semester` varchar(255) , 
 `year` varchar(255) , 
 ) ;
 DROP TABLE IF EXISTS `StudentInformation`;  
 CREATE TABLE `StudentInformation`  ( 
  
 `studentID` varchar(255) , 
 `profession` varchar(255) , 
 `name` varchar(255) , 
 `gender` varchar(255) , 
 `password` varchar(255) , 
 ) ;
 DROP TABLE IF EXISTS `Teacher`;  
 CREATE TABLE `Teacher`  ( 
  
 `FacultyNumber` varchar(255) , 
 `CollegeName` varchar(255) , 
 `name` varchar(255) , 
 `gender` varchar(255) , 
 `password` varchar(255) , 
 ) ;
 DROP TABLE IF EXISTS `TeacherStartList`;  
 CREATE TABLE `TeacherStartList`  ( 
  
 `CourseID` varchar(255) , 
 `SerialNumber` varchar(255) , 
 `FacultyNumber` varchar(255) , 
 `name` varchar(255) , 
 `CollegeName` varchar(255) , 
 ) ;
 DROP TABLE IF EXISTS `Time`;  
 CREATE TABLE `Time`  ( 
  
 `timeid` varchar(255) , 
 ) ;
 DROP TABLE IF EXISTS `Transcript`;  
 CREATE TABLE `Transcript`  ( 
  
 `studentID` varchar(255) , 
 `CourseID` varchar(255) , 
 `SerialNumber` varchar(255) , 
 `Grades` varchar(255) , 
 `SubName` varchar(255) , 
 ) ;
 DROP TABLE IF EXISTS `User`;  
 CREATE TABLE `User`  ( 
  
 `id` varchar(255) , 
 `name` varchar(255) , 
 `pwd` varchar(255) , 
 ) ;