create table purchase (
  id integer generated by default as identity,
  customer_id int not null,
  nef varchar(255),
  created_at timestamp,
  foreign key (customer_id) references customer(id),
  primary key (id)
);

create table purchase_book (
  purchase_id int not null,
  book_id int not null,
  foreign key (purchase_id) references purchase(id),
  foreign key (book_id) references book(id),
  primary key (purchase_id, book_id)
)