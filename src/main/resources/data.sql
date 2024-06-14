INSERT INTO Address (street,postal_code,city,country)
    VALUES ('gata1','postalcode1','city1','country1'),
           ('gata2','postalcode2','city2','country2'),
           ('gata3','postalcode3','city3','country3');

INSERT INTO Customer (user_name,first_name,last_name,email,phone,address_id)
VALUES ('user1','firstname1','lastname1','email1','phone1',1),
       ('user2','firstname2','lastname2','email2','phone2',2);

INSERT INTO Venue (name,location,field_type)
VALUES ('venue1','venue1location','indoors'),
       ('venue2','venue2location','outdoors');

INSERT INTO Booking (price,start_date,end_date,total_players,customer_id,venue_id)
    VALUES (100,'2024-06-13T12:00','2024-06-13T14:00','4',1,1),
           (150,'2024-06-13T11:00','2024-06-13T14:00','2',2,2);
           --(,'','','',''),
           --(,'','','','');










