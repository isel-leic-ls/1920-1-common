create table courses (
  cid serial primary key,
  name varchar(80)
);

create table students (
  number int primary key,
  name varchar(80),
  course int references courses(cid)
);
