--create table itemsState (
--id_itemsState integer primary key, nameOfitem text);
--create table category (id_category integer primary key, nameOfcategory text);
--create table rules (id_rule integer primary key, nameOfrule text);
--create table roles (id_roles integer primary key, nameOfroles text);
--create table rulesRole (id_rulesRole integer primary key, id_rule integer references rules(id_rule),id_roles integer references roles(id_roles));
--create table item (id_item integer primary key, id_category integer references category (id_category) ,id_itemsState integer references itemsState (id_itemsState));
--create table attachs (id_attach integer primary key, nameOfattach text,id_item integer references item (id_item));
--create table comments (id_comment integer primary key, contentC text,id_item integer references item (id_item));
--create table users (id_users integer primary key, firstName text, secondName text,id_roles integer references roles (id_roles),id_item integer references item (id_item));
--insert into itemsState (id_itemsState, nameOfitem) values (1, 'item1');
--insert into itemsState (id_itemsState, nameOfitem) values (2, 'item2');
--insert into category (id_category , nameOfcategory) values (1, 'category1');
--insert into category (id_category , nameOfcategory) values (2, 'category2');
--insert into rules (id_rule , nameOfrule ) values (1, 'rule1');
--insert into rules (id_rule , nameOfrule ) values (2, 'rule2');
--insert into roles (id_roles , nameOfroles ) values (1, 'role1');
--insert into roles (id_roles , nameOfroles ) values (2, 'role2');
--insert into rulesRole (id_rulesRole , id_rule ,id_roles) values (1, 1,1);
--insert into rulesRole (id_rulesRole , id_rule ,id_roles) values (2, 2,2);
--insert into item (id_item , id_category ,id_itemsState) values (1, 1,1);
--insert into item (id_item , id_category ,id_itemsState) values (2, 2,2);
--insert into attachs (id_attach, nameOfattach ,id_item ) values (1, 'attach1',1);
--insert into attachs (id_attach, nameOfattach ,id_item ) values (2, 'attach2',2);
--insert into comments (id_comment , contentC ,id_item) values (1, 'comments1',1);
--insert into comments (id_comment , contentC ,id_item) values (2, 'comments2',2);
--insert into users (id_users , firstName, secondName ,id_roles,id_item) values (1, 'ilya','h',1,1);
--insert into users (id_users , firstName, secondName ,id_roles,id_item) values (2, 'philip','n',2,2);






