INSERT INTO ChessPlayer(id, firstName, lastName) VALUES(1, 'Magnus', 'Carlsen');
INSERT INTO ChessPlayer(id, firstName, lastName) VALUES(2, 'Hikaru', 'Nakamura');
INSERT INTO ChessPlayer(id, firstName, lastName) VALUES(3, 'Fabiano', 'Caruana');
INSERT INTO ChessPlayer(id, firstName, lastName) VALUES(4, 'Vincent', 'Keimer');

INSERT INTO ChessTournament(id, name) VALUES (1, 'Fide Grand Swiss 2023');
INSERT INTO ChessTournament(id, name) VALUES (2, 'Titled Tuesday');

INSERT INTO ChessTournament_ChessPlayer (tournaments_id, players_id) VALUES (1, 1);
INSERT INTO ChessTournament_ChessPlayer (tournaments_id, players_id) VALUES (1, 2);
INSERT INTO ChessTournament_ChessPlayer (tournaments_id, players_id) VALUES (1, 3);
INSERT INTO ChessTournament_ChessPlayer (tournaments_id, players_id) VALUES (1, 4);
INSERT INTO ChessTournament_ChessPlayer (tournaments_id, players_id) VALUES (2, 2);
INSERT INTO ChessTournament_ChessPlayer (tournaments_id, players_id) VALUES (2, 4);