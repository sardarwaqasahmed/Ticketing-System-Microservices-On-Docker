-- insert in system user
insert into system_user (id,user_name,password) values (user_sequence.nextval,'admin','admin');
insert into system_user (id,user_name,password) values (user_sequence.nextval,'ordersrv','ordersrv');