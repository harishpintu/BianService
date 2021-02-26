create table Balance
(
   accountNumber varchar(255) not null,
   lastUpdateTimestamp date not null,
   balance varchar(255) not null,
   primary key(accountNumber )
);

create table Transaction
(
   accountNumber varchar(255) not null,
   transactionTs date not null,
   type varchar(255) not null,
   amount varchar(255) not null,
   primary key(accountNumber )
);

