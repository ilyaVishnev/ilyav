--create table gearbox(id_box integer primary key, gearbox text);
--create table transmission(id_tr integer primary key, set text);
--create table engine(id_en integer primary key, engine text);
--create table cars(id_car integer primary key, name text, id_box integer references gearbox(id_box),id_tr integer references transmission(id_tr),id_en integer references engine(id_en));
--insert into gearbox(id_box,gearbox) values(1,'gearbox1');
--insert into gearbox(id_box,gearbox) values(2,'gearbox2');
--insert into gearbox(id_box,gearbox) values(3,'gearbox3');
--insert into transmission(id_tr,set) values(1,'set1');
--insert into transmission(id_tr,set) values(2,'set2');
--insert into transmission(id_tr,set) values(3,'set3');
--insert into engine(id_en,engine) values(1,'engine1');
--insert into engine(id_en,engine) values(2,'engine2');
--insert into engine(id_en,engine) values(3,'engine3');
--insert into cars(id_car, name, id_box,id_tr ,id_en) values(1,'car1',1,1,1);
--insert into cars(id_car, name, id_box,id_tr ,id_en) values(2,'car2',2,2,2);
--select c.name from cars as c;
--select g.gearbox from cars as c right outer join gearbox as g on c.id_box=g.id_box where c.id_box is null union select t.set from cars as c right outer join transmission as t on t.id_tr=c.id_tr where c.id_tr is null union select e.engine from cars as c right outer join engine as e on e.id_en=c.id_en where c.id_en is null ;
