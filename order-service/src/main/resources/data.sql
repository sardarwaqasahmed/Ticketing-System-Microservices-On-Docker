-- insert in system user
insert into system_user (id,user_name,password) values (user_sequence.nextval,'ordersrv','ordersrv');
-- insert the order_delivery
insert into order_delivery (id,customer_type,delivery_status,expected_delivery_time,distance_from_destination,food_preparation_mean_time,time_to_reach_destination) values (ordersrv_sequence.nextval,'VIP','Received',TIMESTAMP '2015-01-14T21:32:29',6,45,TIMESTAMP '2015-01-14T21:32:29');