create table customer (
  id BIGINT AUTO_INCREMENT,
  email varchar(255) not null unique,
  name varchar(255) not null,
  primary key (id)
)