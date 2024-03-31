use newsweb;
create table role (
                      id int not null primary key auto_increment,
                      name varchar(255) not null,
                      code varchar(255) not null
);
create table user (
                      id int not null primary key auto_increment,
                      username varchar(255) not null,
                      password varchar(255) not null,
                      status int not null,
                      roleid int not null,
                      foreign key (roleid) references role(id)
);

create table category(
                         id int not null primary key auto_increment,
                         name varchar(255) not null,
                         code varchar(255)
);
create table news (
                      id int not null primary key auto_increment,
                      title varchar(255) not null,
                      thumbnail varchar(255) not null,
                      shortdescription varchar(255),
                      content text null,
                      categoryid int,
                      createdby varchar(255),
                      createddate timestamp,
                      foreign key (categoryid) references category(id)
);
create table comment (
                         id int not null primary key auto_increment,
                         content text null,
                         userid int,
                         newsid int,
                         foreign key (userid) references user(id),
                         foreign key (newsid) references news(id)
);