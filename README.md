DukeMarket-JavaFX

CREATE TABLE produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codBarras VARCHAR(15),
    descricao VARCHAR(30),
    qtd INT,
    valorCompra DECIMAL,
    valorVenda DECIMAL,
    dataCadastro DATETIME default current_timestamp);






CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cPF VARCHAR(11),
    nome VARCHAR(30),
    endereco VARCHAR(15),
    cidade VARCHAR(15),
    uF VARCHAR(5),
    dataCadastro DATE);

