create table users(id int primary key auto_increment,name varchar(50) not null,fathername varchar(50) not null,email varchar(50) not null unique,password varchar(50) not null default "na",dateofjoining timestamp not null default current_timestamp,department int,foreign key(department) references departments(id), active boolean default 1,activeaccount boolean default 0,roles boolean not null default 0);
drop table users;
create table students(id int primary key auto_increment,regno int not null unique,name varchar(50) not null,fathername varchar(50),department int,foreign key(department) references departments(id),dateofbirth date  not null,address varchar(100) not null,active boolean default 1);
drop table students;
create table subjects(id int primary key,name varchar(50) not null unique);

create table marks(id int primary key auto_increment,sid int not null,foreign key(sid) references students(regno),subid int not null,foreign key(subid) references subjects(id),marks int check (marks>0),check (marks<100), unique(sid,subid));
drop table marks;
create table grades(grade char primary key,minmark int not null,maxmark int not null);
drop table grades;
create table departments (id int primary key auto_increment,name varchar(50) not null unique);

select * from users;
select * from students;
select * from subjects;
select * from marks;
select * from departments;
select * from grades;
insert into grades (grade,minmark,maxmark) values ('a',75,100),('b',60,74),('c',40,59),('f',0,39);
truncate table marks;
truncate table departments;
truncate table grades;
select u.id,u.name,u.fathername,u.email,u.dateofjoining,u.active,u.activeaccount,u.roles,d.id,d.name from users as u, departments as d where u.department=d.id and email="cs@gmail.com" and password="cs" and active=1 and activeaccount=1;
select sid,avg(marks) as average from marks group by sid order by average desc;
update grades set minmark=70,maxmark=100 where grade='a';
select grade from grades where 80 between minmark and maxmark;
select m.sid,m.marks,m.subid, avg(m.marks) as average from marks as m,subjects as s where m.subid=s.id and m.sid=1 group by m.sid,m.subid;
select st.id,st.regno,st.name,st.fathername,st.department,st.dateofbirth,st.address,st.active,m.id,m.sid,m.subid,m.marks,sub.id,sub.name,dept.id,dept.name from students as st,marks as m,subjects as sub,departments as dept where m.subid=sub.id and st.department=dept.id and st.regno=m.sid and st.regno=1;


select m.*,st.id,st.name,st.regno,s.name from marks as m,grades as g,students as st,subjects as s where m.subid=s.id and g.grade='b' group by s.name;

select s.id,s.regno,s.name,s.fathername,d.id,d.name,s.dateofbirth,s.address,s.active from students as s, departments as d where s.department=d.id order by s.id;