set schema 'api';

INSERT INTO votacao (id, pauta, voto) VALUES
(1, 'Contratar o Ederson', 'SIM'),
(2, 'Contratar o Ederson', 'SIM'),
(3, 'Contratar o Ederson', 'SIM'),
(4, 'Contratar o Ederson', 'SIM'),
(5, 'Contratar o Ederson', 'NÃO'),
(6, 'Contratar o Ederson', 'SIM'),
(7, 'Contratar o Ederson', 'SIM'),
(8, 'Contratar o Ederson', 'NÃO'),
(9, 'Contratar o Ederson', 'SIM'),
(10,'Contratar o Ederson', 'SIM');
SELECT setval('votacao_seq', 10);