INSERT INTO `authors` (name) VALUES ('Kentarou Miura');
INSERT INTO `authors` (name) VALUES ('Eiichiro Oda');
INSERT INTO `authors` (name) VALUES ('Akasaka Aka');
INSERT INTO `authors` (name) VALUES ('Akira Toriyama');
INSERT INTO `authors` (name) VALUES ('Osamu Tezuka');
INSERT INTO `authors` (name) VALUES ('Masashi Kishimoto');
INSERT INTO `authors` (name) VALUES ('Naoki Urasawa');
INSERT INTO `authors` (name) VALUES ('Junji Ito');
INSERT INTO `authors` (name) VALUES ('Takehiko Inoue');
INSERT INTO `authors` (name) VALUES ('Masashi Kishimoto');
INSERT INTO `authors` (name) VALUES ('Yushihiro Togashi');

INSERT INTO `genres` (name) VALUES ('Action');
INSERT INTO `genres` (name) VALUES ('Adventure');
INSERT INTO `genres` (name) VALUES ('Romance');
INSERT INTO `genres` (name) VALUES ('Sports');
INSERT INTO `genres` (name) VALUES ('Slice of Life');
INSERT INTO `genres` (name) VALUES ('Comedy');
INSERT INTO `genres` (name) VALUES ('Drama');
INSERT INTO `genres` (name) VALUES ('Fantasy');
INSERT INTO `genres` (name) VALUES ('Isekai');
INSERT INTO `genres` (name) VALUES ('Horror');
INSERT INTO `genres` (name) VALUES ('Josei');
INSERT INTO `genres` (name) VALUES ('Seinen');
INSERT INTO `genres` (name) VALUES ('Shonen');
INSERT INTO `genres` (name) VALUES ('Shojo');

INSERT INTO `status` (name) VALUES ('Ongoing');
INSERT INTO `status` (name) VALUES ('Completed');
INSERT INTO `status` (name) VALUES ('Hiatus');
INSERT INTO `status` (name) VALUES ('Cancelled');

INSERT INTO `manga` (title, description, release_date, status_id) VALUES ('Berserker', 'Guts, a former mercenary now known as the Black Swordsman, is out for revenge. After a tumultuous childhood, he finally finds someone he respects and believes he can trust, only to have everything fall apart when this person takes away everything important to Guts for the purpose of fulfilling his own desires. Now marked for death, Guts becomes condemned to a fate in which he is relentlessly pursued by demonic beings.

Setting out on a dreadful quest riddled with misfortune, Guts, armed with a massive sword and monstrous strength, will let nothing stop him, not even death itself, until he is finally able to take the head of the one who stripped him—and his loved one—of their humanity.', '1989-08-25', 1);
INSERT INTO `author_manga` (manga_id, author_id) VALUES (1, 1);
INSERT INTO `genre_manga` (manga_id, genre_id) VALUES (1, 1);
INSERT INTO `genre_manga` (manga_id, genre_id) VALUES (1, 2);
INSERT INTO `genre_manga` (manga_id, genre_id) VALUES (1, 7);
INSERT INTO `genre_manga` (manga_id, genre_id) VALUES (1, 8);
INSERT INTO `genre_manga` (manga_id, genre_id) VALUES (1, 10);


INSERT INTO `manga` (title, description, release_date, status_id) VALUES ('One Piece', 'Gol D. Roger, a man referred to as the King of the Pirates, is set to be executed by the World Government. But just before his demise, he confirms the existence of a great treasure, One Piece, located somewhere within the vast ocean known as the Grand Line. Announcing that One Piece can be claimed by anyone worthy enough to reach it, the King of the Pirates is executed and the Great Age of Pirates begins.

Twenty-two years later, a young man by the name of Monkey D. Luffy is ready to embark on his own adventure, searching for One Piece and striving to become the new King of the Pirates. Armed with just a straw hat, a small boat, and an elastic body, he sets out on a fantastic journey to gather his own crew and a worthy ship that will take them across the Grand Line to claim the greatest status on the high seas.', '1997-07-22', 1);
INSERT INTO `author_manga` (manga_id, author_id) VALUES (2, 2);
INSERT INTO `genre_manga` (manga_id, genre_id) VALUES (2, 1);
INSERT INTO `genre_manga` (manga_id, genre_id) VALUES (2, 2);
INSERT INTO `genre_manga` (manga_id, genre_id) VALUES (2, 8);


INSERT INTO `manga` (title, description, release_date, status_id) VALUES ('Kaguya-sama: Love Is War', 'Considered a genius due to having the highest grades in the country, Miyuki Shirogane leads the prestigious Shuchiin Academys student council as its president, working alongside the beautiful and wealthy vice president Kaguya Shinomiya. The two are often regarded as the perfect couple by students despite them not being in any sort of romantic relationship.

However, the truth is that after spending so much time together, the two have developed feelings for one another; unfortunately, neither is willing to confess, as doing so would be a sign of weakness. With their pride as elite students on the line, Miyuki and Kaguya embark on a quest to do whatever is necessary to get a confession out of the other. Through their daily antics, the battle of love begins!', '2015-05-19', 2);
INSERT INTO `author_manga` (manga_id, author_id) VALUES (3, 3);
INSERT INTO `genre_manga` (manga_id, genre_id) VALUES (3, 3);
INSERT INTO `genre_manga` (manga_id, genre_id) VALUES (3, 6);

INSERT INTO `manga` (title, description, release_date, status_id) VALUES ('Dragon Ball', 'Bulma, a headstrong 16-year-old girl, is on a quest to find the mythical Dragon Balls—seven scattered magic orbs that grant the finder a single wish. She has but one desire in mind: a perfect boyfriend. On her journey, Bulma stumbles upon Gokuu Son, a powerful orphan who has only ever known one human besides her. Gokuu possesses one of the Dragon Balls, it being a memento from his late grandfather. In exchange for it, Bulma invites Gokuu to be a companion in her travels.

By Bulmas side, Gokuu discovers a world completely alien to him. Powerful enemies embark on their own pursuits of the Dragon Balls, pushing Gokuu beyond his limits in order to protect Bulma and their growing circle of allies. However, Gokuu has secrets unbeknownst to even himself; the incredible strength within him stems from a mysterious source, one that threatens the many people he grows to hold dear.

As his prowess in martial arts flourishes, Gokuu attracts stronger opponents whose villainous plans could collapse beneath his might. He undertakes the endless venture of combat training to defend his loved ones and the fate of the planet itself.', '2015-05-19', 2);
INSERT INTO `author_manga` (manga_id, author_id) VALUES (4, 4);
INSERT INTO `genre_manga` (manga_id, genre_id) VALUES (4, 1);
INSERT INTO `genre_manga` (manga_id, genre_id) VALUES (4, 2);
INSERT INTO `genre_manga` (manga_id, genre_id) VALUES (4, 8);

INSERT INTO `users` (`username`, `password`) VALUES ('admin', '{noop}12345678');
INSERT INTO `users` (`username`, `password`) VALUES ('user', '{noop}12345678');

INSERT INTO `roles` (`name`) VALUES ('ADMIN');
INSERT INTO `roles` (`name`) VALUES ('USER');

INSERT INTO `role_user` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `role_user` (`user_id`, `role_id`) VALUES (2, 2);