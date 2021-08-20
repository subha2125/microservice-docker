use springsecurity;
use employeedb;

create database cache;
use employeeDB;
use springtest;


select * from Course;
select * from Teacher;

select c.title,t.first_name from Teacher t,Course c
where t.teacher_id=c.teacher_id;

SELECT c.title,t.first_name from Teacher t
INNER JOIN Course c ON t.teacher_id=c.teacher_id where c.course_id=3;

select * from Course_material;
select * from tbl_Student;


select * from EmployeeInfo;

create table EmployeeSalary(
   id INT NOT NULL,
   salary VARCHAR(100) NOT NULL,
   PRIMARY KEY ( id )
);

insert into EmployeeSalary values (5,"567900");
select * from EmployeeSalary;

insert into EmployeeInfo values
(4,"Actress","Film","Professional","Deepika","Bollywood");

commit;

use springsecurity;
select * from user;
select * from role;

update user set password='$2a$10$3m0wbIwB3IJRgbHcvwoDdupccbjyTdYbbvrCOTz3HAxs9QfNVCj76' where id=1;
commit;