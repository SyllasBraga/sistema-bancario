INSERT INTO cep (cep, bairro, cidade, estado, rua) VALUES
('35030444', 'Centro', 'Rio de Janeiro', 'Rio de Janeiro', 'Avenida Brasil'),
('61027896', 'Copacabana', 'Rio de Janeiro', 'Rio de Janeiro', 'Rua Barata Ribeiro'),
('48200137', 'Vila Mariana', 'São Paulo', 'São Paulo', 'Rua Domingos de Moraes'),
('74256718', 'Jardim América', 'São Paulo', 'São Paulo', 'Alameda Lorena'),
('85900125', 'Mercês', 'Curitiba', 'Paraná', 'Rua Manoel Ribas'),
('32014285', 'Centro', 'Belo Horizonte', 'Minas Gerais', 'Avenida Afonso Pena'),
('54100217', 'Boa Viagem', 'Recife', 'Pernambuco', 'Avenida Boa Viagem'),
('65060183', 'Renascença', 'São Luís', 'Maranhão', 'Rua dos Nobres'),
('97010204', 'Centro', 'Santa Maria', 'Rio Grande do Sul', 'Rua Serafim Valandro'),
('36039124', 'Alto dos Passos', 'Juiz de Fora', 'Minas Gerais', 'Avenida Rio Branco'),
('49060171', 'Farolândia', 'Aracaju', 'Sergipe', 'Rua Rafael de Aguiar'),
('58040167', 'Cabo Branco', 'João Pessoa', 'Paraíba', 'Avenida Cabo Branco'),
('68905028', 'Centro', 'Macapá', 'Amapá', 'Rua Cândido Mendes'),
('70675563', 'Asa Sul', 'Brasília', 'Distrito Federal', 'Avenida W3 Sul'),
('45028421', 'Centro', 'Vitória da Conquista', 'Bahia', 'Rua da Granja'),
('40060002', 'Pelourinho', 'Salvador', 'Bahia', 'Ladeira do Carmo'),
('29010133', 'Centro', 'Vitória', 'Espírito Santo', 'Avenida Princesa Isabel'),
('68900107', 'Trem', 'Macapá', 'Amapá', 'Avenida Presidente Vargas'),
('76801141', 'Caiari', 'Porto Velho', 'Rondônia', 'Rua Barão do Rio Branco'),
('64000127', 'Centro', 'Teresina', 'Piauí', 'Rua Álvaro Mendes'),
('35030440', 'Lourdes', 'Governador Valadares', 'Minas Gerais', 'Rua Pedro Lessa'),
('39700000', null, 'Peçanha', 'Minas Gerais', null);

INSERT INTO conta (saldo, conta, datacriacao, cpf, nometitular) VALUES
(0.00, '6543219', NOW() - INTERVAL '2 days', '53324471249', 'Lucas Lima'),
(0.00, '5432198', NOW() - INTERVAL '6 days', '72183742550', 'Fernando Souza'),
(0.00, '4321987', NOW() - INTERVAL '20 days', '71738376427', 'Camila Ribeiro'),
(-400.00, '2108765', NOW() - INTERVAL '7 days', '50680607714', 'Gabriela Monteiro'),
(200.00, '3219876', NOW() - INTERVAL '4 days', '06342027587', 'Rafael Mendes'),
(-800.00, '1097654', NOW() - INTERVAL '12 days', '57233451761', 'Thiago Nunes'),
(-3500.00, '9876542', NOW() - INTERVAL '20 days', '07422295058', 'Larissa Lopes');

INSERT INTO movimentacao (idconta, valor, datamovimentacao, acao) VALUES
(4, 400.00, NOW(), 'DEPOSITAR'),
(4, -600.00, NOW(), 'RETIRAR'),
(4, -1000.00, NOW(), 'RETIRAR'),
(4, -2000.00, NOW(), 'RETIRAR'),
(4, 500.00, NOW(), 'DEPOSITAR'),

(5, 700.00, NOW(), 'DEPOSITAR'),
(5, -300.00, NOW(), 'RETIRAR'),
(5, -1500.00, NOW(), 'RETIRAR'),
(5, -800.00, NOW(), 'RETIRAR'),
(5, 200.00, NOW(), 'DEPOSITAR');

INSERT INTO movimentacao (idconta, valor, datamovimentacao, acao) VALUES
(6, 400.00, NOW(), 'DEPOSITAR'),
(6, -100.00, NOW(), 'RETIRAR'),
(6, -50.00, NOW(), 'RETIRAR'),
(6, 200.00, NOW(), 'DEPOSITAR'),
(6, -300.00, NOW(), 'RETIRAR'),
(6, 100.00, NOW(), 'DEPOSITAR'),
(6, -100.00, NOW(), 'RETIRAR'),
(6, 50.00, NOW(), 'DEPOSITAR'),

(7, 600.00, NOW(), 'DEPOSITAR'),
(7, -200.00, NOW(), 'RETIRAR'),
(7, -100.00, NOW(), 'RETIRAR'),
(7, 300.00, NOW(), 'DEPOSITAR'),
(7, -400.00, NOW(), 'RETIRAR'),
(7, 200.00, NOW(), 'DEPOSITAR'),
(7, -100.00, NOW(), 'RETIRAR'),
(7, 100.00, NOW(), 'DEPOSITAR');