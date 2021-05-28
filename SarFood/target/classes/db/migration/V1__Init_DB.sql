create table category (
	id bigint not null auto_increment,
	type varchar(255), 
	primary key (id)
);

create table product (
	id bigint not null auto_increment,
	locale varchar(2),
	messagekey varchar(255),
	content varchar(255),
	price decimal(10,2),
	link varchar (255),
	category_id bigint,
	primary key (id)
	);
	
create table order_item (
	id bigint not null auto_increment, 
	count integer,
	order_id bigint,
	menu_id bigint, 
	primary key (id)
	);
	
create table orders (
	id bigint not null auto_increment,
	created datetime,
	user_id bigint,
	primary key (id)
	);
	
create table user_role (
	user_id bigint not null,
	roles varchar(255)
	);
	
create table usr (
	id bigint not null auto_increment,
	activation_code varchar(255),
	active bit not null,
	email varchar(255),
	password varchar(255),
	username varchar(10),
	phone varchar(20),
	primary key (id)
	);
	
alter table product
	add constraint product_category_fk 
	foreign key (category_id) references category (id);
	
alter table order_item
	add constraint order_item_product_fk
	foreign key (menu_id) references product (id);
	
alter table user_role 
	add constraint user_role_user_fk 
	foreign key (user_id) references usr (id);