INSERT INTO Address (street,postal_code,city,country)
    VALUES ('gata1','postalcode1','city1','country1'),
           ('gata2','postalcode2','city2','country2'),
           ('gata3','postalcode3','city3','country3');

INSERT INTO Customer (user_name,first_name,last_name,email,phone,address_id)
VALUES ('user1','firstname1','lastname1','email1','phone1',1),
       ('user2','firstname2','lastname2','email2','phone2',2);

INSERT INTO Venue (name,location,field_type,opening_time,closing_time)
VALUES ('venue1','venue1location','indoors','11:00','15:00'),
       ('venue2','venue2location','outdoors','11:00','15:00'),
       ('venue3','venue3location','outdoors','09:00','18:00');


INSERT INTO Booking (price,date,start_time,end_time,total_players,customer_id,venue_id)
    VALUES (100,'2024-06-14','12:00','15:00','4',1,1),
           (100,'2024-06-14','11:00','13:00','2',2,2),
           (100,'2024-06-14','11:00','16:00','2',2,3);
           --(,'','','',''),
           --(,'','','','');












