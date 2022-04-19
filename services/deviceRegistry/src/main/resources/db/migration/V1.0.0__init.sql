create table REGISTRY (
    id varchar(50) not null primary key,
    name varchar(50) not null,
    type varchar(50) not null,
    kind varchar(50) not null,
    controller_name varchar(50) not null,
    attributes json,
    state_providers array
);

create index idx_registry_id
    on REGISTRY(id);