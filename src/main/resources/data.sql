INSERT INTO CATEGORIA(nome) VALUES ('DR1');
INSERT INTO CATEGORIA(nome) VALUES ('DR2');
INSERT INTO CATEGORIA(nome) VALUES ('PB');

INSERT INTO PROJETO(nome,descricao,categoria_id) VALUES ( 'TP1','1',3);
INSERT INTO PROJETO(nome,descricao,categoria_id) VALUES ( 'TP2','2',3);
INSERT INTO PROJETO(nome,descricao,categoria_id) VALUES ( 'TP3','3',3);
INSERT INTO PROJETO(nome,descricao,categoria_id) VALUES ( 'TP4','4',3);

INSERT INTO ROLE(nome) VALUES ( 'ADMIN' );
INSERT INTO ROLE(nome) VALUES ( 'USER' );

INSERT INTO USUARIO(email,nome,status,password) VALUES ( 'admin@taskflow.com', 'Eneas',1 , 'q1w2e3r4');
INSERT INTO USUARIO(email,nome,status,password) VALUES ( 'func@taskflow.com', 'Enzo',2 , 'password');

INSERT INTO USUARIO_ROLES(ROLES_ID, USUARIO_ID) VALUES ( 1,1 );
INSERT INTO USUARIO_ROLES(ROLES_ID, USUARIO_ID) VALUES ( 2,1 );
INSERT INTO USUARIO_ROLES(ROLES_ID, USUARIO_ID) VALUES ( 2,2 );