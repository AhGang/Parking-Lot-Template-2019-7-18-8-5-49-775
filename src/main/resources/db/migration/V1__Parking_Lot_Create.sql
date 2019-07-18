create table PARKING_LOT (
    ID varchar(100) not null,
    NAME varchar(100) not null unique,
    LOCATION varchar(100),
    CAPACITY int CHECK (CAPACITY >= 0),
);