insert into category(id, type) values
	(1, 'Soup'),
	(2, 'Pizza'),
	(3, 'Dessert'),
	(4, 'Salad');

insert into product(locale, messagekey, content, price, link, category_id) values
	('ru', 'menu.borsh', 'Борщ', 70, 'media/borsh.jpg', 1),
	('ru', 'menu.kharcho', 'Харчо', 85, 'media/kharcho.jpg', 1),
	('ru','menu.pepperoni', 'Пепперони', 140, 'media/pepperoni.png', 2),
	('ru','menu.vegetarian', 'Вегетарианская', 110, 'media/vegetarian.jpeg', 2),
	('ru','menu.berry.pudding', 'Ягодный Пудинг', 60, 'media/Berry_Pudding.jpg', 3),
	('ru','menu.greek.salad', 'Салат "Греческий"', 90, 'media/Greek_Salad.png', 4),
	('ru','menu.cesar.salad', 'Салат "Цезарь"', 95, 'media/Cesar_Salad.jpg', 4),
	('ru','menu.waldofor.salad', 'Вальдорфский Cалат', 75, 'media/Waldorf_Salad.jpg', 4),
	('ru','menu.chocolateBananaBread.pudding', 'Шоколадно-Банановый Пудинг', 70, 'media/chocolate_banana_bread_pudding.jpg', 3),
	('ru','menu.solyanka', 'Солянка', 105, 'media/solyanka.jpg', 1),
	('ru','menu.hunting', 'Охотничья', 165, 'media/Muslyvska.png', 2);