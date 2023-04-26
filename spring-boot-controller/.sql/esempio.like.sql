delete from comments;
delete from posts;
delete from authors;


INSERT into authors(firstName, lastName, email)
values ('Mirko', 'Abbrescia', 'mirkoabbrescia@gmail.com'),
       ('Andrea', 'Mininni', 'andreamininni@gmail.com'),
       ('Siria', 'Bertazzoni', 'siriabertazzoni@gmail.com'),
       ('Jacopo', 'Paccioretti', 'jacopopaccioretti@gmail.com');


INSERT into posts(title, body, publish_date, author_id)
values ('sconosciuto@gmail.com', 'Lorem ipsum', '20230413', 1),
       ('sconosciuto@gmail.com', 'Lorem ipsum', '20230413', 1),
       ('sconosciuto@gmail.com', 'Lorem ipsum', '20230413', 2),
       ('sconosciuto@gmail.com', 'Lorem ipsum', '20230413', 2),
       ('sconosciuto@gmail.com', 'Lorem ipsum', '20230413', 3),
       ('sconosciuto@gmail.com', 'Lorem ipsum', '20230413', 3),
       ('sconosciuto@gmail.com', 'Lorem ipsum', '20230413', 4),
       ('sconosciuto@gmail.com', 'Lorem ipsum', '20230413', 1),
       ('sconosciuto@gmail.com', 'Lorem ipsum', '20230413', 2),
       ('sconosciuto@gmail.com', 'Lorem ipsum', '20230413', 3);



INSERT into comments (email, body, date, post_id)
values ('sconosciuto@gmail.com', 'Lorem ipsum', '20230413', 1),
       ('sconosciuto@gmail.com', 'Lorem ipsum', '20230413', 2),
       ('sconosciuto@gmail.com', 'Lorem ipsum', '20230413', 3),
       ('sconosciuto@gmail.com', 'Lorem ipsum', '20230413', 4),
       ('sconosciuto@gmail.com', 'Lorem ipsum', '20230413', 5),
       ('sconosciuto@gmail.com', 'Lorem ipsum', '20230413', 6),
       ('sconosciuto@gmail.com', 'Lorem ipsum', '20230413', 7),
       ('sconosciuto@gmail.com', 'Lorem ipsum', '20230413', 8),
       ('sconosciuto@gmail.com', 'Lorem ipsum', '20230413', 9),
       ('sconosciuto@gmail.com', 'Lorem ipsum', '20230413', 10);

-- SELECT * from authors WHERE email like '%s%'