create table vote (
  id integer identity primary key,
  election_id integer not null,
  voter_id integer not null,
  candidate_id integer,
  blank_vote boolean not null,
  null_vote boolean not null
);
