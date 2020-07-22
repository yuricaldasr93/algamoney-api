CREATE TABLE pessoa(
codigo SERIAL PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
logradouro VARCHAR(200),
numero VARCHAR(10),
complemento VARCHAR(100),
bairro VARCHAR(200),
cep VARCHAR(10),
cidade VARCHAR(200),
estado VARCHAR(2),
ativo BOOLEAN NOT NULL
);

INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('José 00', 'Rua 00', '00', null, 'Bairro 00', '00000000', 'GV City', 'MG', TRUE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('José 01', 'Rua 01', '01', null, 'Bairro 01', '00000001', 'GV City', 'MG', TRUE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('José 02', 'Rua 02', '02', null, 'Bairro 02', '00000002', 'GV City', 'MG', TRUE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('José 03', 'Rua 03', '03', null, 'Bairro 03', '00000003', 'GV City', 'MG', TRUE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('José 04', 'Rua 04', '04', null, 'Bairro 04', '00000004', 'GV City', 'MG', TRUE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('José 05', 'Rua 05', '05', null, 'Bairro 05', '00000005', 'GV City', 'MG', FALSE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('José 06', 'Rua 06', '06', null, 'Bairro 06', '00000006', 'GV City', 'MG', TRUE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('José 07', 'Rua 07', '07', null, 'Bairro 07', '00000007', 'GV City', 'MG', TRUE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('José 08', 'Rua 08', '08', null, 'Bairro 08', '00000008', 'GV City', 'MG', FALSE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('José 09', 'Rua 09', '09', null, 'Bairro 09', '00000009', 'GV City', 'MG', TRUE);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('José 10', 'Rua 10', '10', null, 'Bairro 10', '00000010', 'GV City', 'MG', TRUE);