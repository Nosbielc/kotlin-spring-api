create table book (
  id BIGINT AUTO_INCREMENT,
  name varchar(255) not null,
  price decimal(10,2) not null,
  status integer not null,
  customer_id BIGINT not null,
  foreign key (customer_id) references customer(id),
  primary key (id)
)