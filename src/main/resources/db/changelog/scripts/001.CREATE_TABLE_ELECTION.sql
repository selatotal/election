create table election (
  id integer identity primary key,
  year integer(4) not null,
  state_code varchar(5) not null,
  description varchar(255) not null
);
