DukeMarket-JavaFX

CREATE TABLE produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codBarras VARCHAR(11),
    descricao VARCHAR(30),
    qtd VARCHAR(15),
    valorCompra VARCHAR(15),
    valorVenda ,
    dataCadastro DATE);






CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cPF VARCHAR(11),
    nome VARCHAR(30),
    endereco VARCHAR(15),
    cidade VARCHAR(15),
    uF VARCHAR(5),
    dataCadastro DATE);

