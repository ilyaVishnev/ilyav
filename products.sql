--create table type(id integer primary key, name text);
--create table product(id integer primary key, name text, type_id integer references type(id), expired_date date, price integer);
--insert into type(id,name) values(1,'cheese');
--insert into type(id,name) values(2,'milk');
--insert into type(id,name) values(3,'sausage');
--insert into product(id, name, type_id , expired_date, price) values(1,'piKcheese',1,'2018-05-10',100);
--insert into product(id, name, type_id , expired_date, price) values(2,'piKmilk',2,'2018-05-14',110);
--insert into product(id, name, type_id , expired_date, price) values(3,'Heighcheese',1,'2022-05-10',1000000);
--insert into product(id, name, type_id , expired_date, price) values(4,'toPcheese',1,'2022-05-10',10);
--insert into product(id, name, type_id , expired_date, price) values(6,'keYcheese',1,'2022-05-10',12);
--insert into product(id, name, type_id , expired_date, price) values(7,'TErcheese',1,'2022-05-10',1045);
--insert into product(id, name, type_id , expired_date, price) values(8,'gERTcheese',1,'2022-05-10',103);
--insert into product(id, name, type_id , expired_date, price) values(9,'POwcheese',1,'2022-05-10',10);
--insert into product(id, name, type_id , expired_date, price) values(10,'vERcheese',1,'2022-05-10',23);
--insert into product(id, name, type_id , expired_date, price) values(11,'juYcheese',1,'2022-05-10',1034);
--insert into product(id, name, type_id , expired_date, price) values(12,'kiDmilk',2,'2023-05-14',113);
--insert into product(id, name, type_id , expired_date, price) values(13,'saDmilk',2,'2023-05-14',15);
--insert into product(id, name, type_id , expired_date, price) values(14,'werTmilk',2,'2023-05-14',119);
--insert into product(id, name, type_id , expired_date, price) values(15,'doubLesausage',3,'2023-05-14',113);
--insert into product(id, name, type_id , expired_date, price) values(16,'thirDsausage',3,'2023-05-14',135);
--insert into product(id, name, type_id , expired_date, price) values(17,'iceCreammilk',2,'2023-05-14',149);
--select p.name from product as p inner join type as t on p.type_id=t.id where t.name='cheese'; 
--select p.name from product as p where p.name like '%iceCream%';
--select p.name from product as p where p.expired_date <'2018-05-29';
--select p.name from product as p where p.price=(select MAX(p.price) from product as p); 
--select count(*) from product as p inner join type as t on p.type_id=t.id where t.name='cheese';
--select t.name  from type as t inner join product as p on p.type_id=t.id group by t.name having count(p.name)<10; 
--select p.name, t.name from product as p inner join type as t on p.type_id=t.id;


