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
(450.00, '1234567', NOW() - INTERVAL '3 days', '12345678901', 'João Silva'),
(-500.00, '7654321', NOW() - INTERVAL '7 days', '23456789012', 'Maria Oliveira'),
(-900.00, '2345678', NOW() - INTERVAL '12 days', '34567890123', 'Carlos Santos'),
(-2700.00, '8765432', NOW() - INTERVAL '20 days', '45678901234', 'Ana Souza'),
(-1700.00, '3456789', NOW() - INTERVAL '30 days', '56789012345', 'Pedro Lima');

INSERT INTO movimentacao (idconta, valor, datamovimentacao, acao) VALUES
(1, 500.00, NOW(), 'DEPOSITAR'),
(1, 300.00, NOW(), 'DEPOSITAR'),
(1, -200.00, NOW(), 'RETIRAR'),
(1, -100.00, NOW(), 'RETIRAR'),
(1, -50.00, NOW(), 'RETIRAR');

INSERT INTO movimentacao (idconta, valor, datamovimentacao, acao) VALUES
(2, 300.00, NOW(), 'DEPOSITAR'),
(2, -100.00, NOW(), 'RETIRAR'),
(2, -400.00, NOW(), 'RETIRAR'),
(2, 200.00, NOW(), 'DEPOSITAR'),
(2, -500.00, NOW(), 'RETIRAR');

INSERT INTO movimentacao (idconta, valor, datamovimentacao, acao) VALUES
(3, 600.00, NOW(), 'DEPOSITAR'),
(3, -500.00, NOW(), 'RETIRAR'),
(3, -300.00, NOW(), 'RETIRAR'),
(3, -800.00, NOW(), 'RETIRAR'),
(3, 100.00, NOW(), 'DEPOSITAR');

INSERT INTO movimentacao (idconta, valor, datamovimentacao, acao) VALUES
(4, 400.00, NOW(), 'DEPOSITAR'),
(4, -600.00, NOW(), 'RETIRAR'),
(4, -1000.00, NOW(), 'RETIRAR'),
(4, -2000.00, NOW(), 'RETIRAR'),
(4, 500.00, NOW(), 'DEPOSITAR');

INSERT INTO movimentacao (idconta, valor, datamovimentacao, acao) VALUES
(5, 700.00, NOW(), 'DEPOSITAR'),
(5, -300.00, NOW(), 'RETIRAR'),
(5, -1500.00, NOW(), 'RETIRAR'),
(5, -800.00, NOW(), 'RETIRAR'),
(5, 200.00, NOW(), 'DEPOSITAR');