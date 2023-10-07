create table purchase (
  id BIGINT AUTO_INCREMENT,
  customer_id BIGINT not null,
  nef varchar(255),
  created_at timestamp,
  foreign key (customer_id) references customer(id),
  primary key (id)
);

create table purchase_book (
  purchase_id BIGINT not null,
  book_id BIGINT not null,
  foreign key (purchase_id) references purchase(id),
  foreign key (book_id) references book(id),
  primary key (purchase_id, book_id)
)