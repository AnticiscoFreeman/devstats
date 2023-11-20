create table account
(
    active   bit    not null,
    id       bigint not null auto_increment,
    name     varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (id)
) engine=InnoDB;

create table account_roles
(
    account_id bigint not null,
    roles      enum ('ADMIN','GUEST','USER')
) engine=InnoDB;

create table developer
(
    dismiss bit    not null,
    id      bigint not null auto_increment,
    name    varchar(255),
    surname varchar(255),
    type    varchar(255),
    primary key (id)
) engine=InnoDB;

create table developer_tasks_list
(
    developer_id  bigint not null,
    tasks_list_id bigint not null
) engine=InnoDB;

create table project
(
    id   bigint not null auto_increment,
    name varchar(255),
    primary key (id)
) engine=InnoDB;

create table project_tasks_list
(
    project_id    bigint not null,
    tasks_list_id bigint not null
) engine=InnoDB;

create table task
(
    count_defect    integer not null,
    count_return    integer not null,
    count_revert    integer not null,
    dev_description integer not null,
    id              bigint  not null auto_increment,
    time            datetime(6),
    developer       varchar(255),
    number          varchar(255),
    project         varchar(255),
    primary key (id)
) engine=InnoDB;

alter table developer_tasks_list
    add constraint developer_tasks_list_fk unique (tasks_list_id);

alter table project_tasks_list
    add constraint project_tasks_list_fk unique (tasks_list_id);

alter table account_roles
    add constraint account_roles_fk foreign key (account_id) references account (id);

alter table developer_tasks_list
    add constraint developer_tasks_list_tasks_fk foreign key (tasks_list_id) references task (id);

alter table developer_tasks_list
    add constraint developer_tasks_list_devs_fk foreign key (developer_id) references developer (id);

alter table project_tasks_list
    add constraint project_tasks_list_tasks_fk foreign key (tasks_list_id) references task (id);

alter table project_tasks_list
    add constraint project_tasks_list_projects_fk foreign key (project_id) references project (id);