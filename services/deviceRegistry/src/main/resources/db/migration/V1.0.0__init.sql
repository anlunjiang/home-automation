create table DEVICES(
    identifier identity not null primary key,
    name varchar(50) not null,
    type varchar(50) not null,
    kind varchar(50) not null,
    controller_name varchar(50) not null,
    room json not null,
    attributes json,
    state_providers array
);

create index idx_devices_id
    on DEVICES(identifier);