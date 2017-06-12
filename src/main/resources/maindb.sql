
/*
insert into ENDERECO(CEP,RUA,BAIRRO,CIDADE,ESTADO,NUMERO) VALUES('04940-010','Rua Afonso rui','santa lucia','sao paulo','SP','55');
insert into ENDERECO(CEP,RUA,BAIRRO,CIDADE,ESTADO,NUMERO) VALUES('04833-001','Avenida Teotonio Vilela','Vila Sao Jose','são paulo','SP','4029');
insert into ENDERECO(CEP,RUA,BAIRRO,CIDADE,ESTADO,NUMERO) VALUES('01122-333','Avenida Hum','Interlagos','sao paulo','SP','10');
insert into ENDERECO(CEP,RUA,BAIRRO,CIDADE,ESTADO,NUMERO) VALUES('33221-000','Rua Dois','Santo Amaro','sao paulo','SP','20');

insert into filial(CNPJ,DESC_NOME,DESC_FANTASIA,TELEFONE,FK_ENDERECO,ENABLED) values('0634270980001','Matriz','Matriz','51235456',1,true);
insert into filial(CNPJ,DESC_NOME,DESC_FANTASIA,TELEFONE,FK_ENDERECO,ENABLED) values('0123456780001','Filial1','Filial1','43211234',2,true);
insert into filial(CNPJ,DESC_NOME,DESC_FANTASIA,TELEFONE,FK_ENDERECO,ENABLED) values('0333222010001','Filial2','Filial2','43211200',3,true);

insert into cargo(cargo,enabled) values('Selecione',true);
insert into cargo(cargo,enabled) values('Administrador',true);
insert into cargo(cargo,enabled) values('Gerente',true);
insert into cargo(cargo,enabled) values('Vendedor',true);

insert into funcionario (COD_ACESSO,ID_CARGO,ID_FILIAL,NOME,SOBRENOME,DT_NASC,CPF,SEXO,CEL,EMAIL,ENABLED,FK_ENDERECO)
values (1,2,1,'Administrador','Santos','27/12/1990','123.456.789-00','M','11-95966-8809','joaol@gmail.com',true,1);
insert into funcionario (COD_ACESSO,ID_CARGO,ID_FILIAL,NOME,SOBRENOME,DT_NASC,CPF,SEXO,CEL,EMAIL,ENABLED,FK_ENDERECO)
values (2,3,2,'Gerente','Silva','01/01/2000','456.123.789-00','F','11-99999-1234','maria@gmail.com',true,2);
insert into funcionario (COD_ACESSO,ID_CARGO,ID_FILIAL,NOME,SOBRENOME,DT_NASC,CPF,SEXO,CEL,EMAIL,ENABLED,FK_ENDERECO)
values (3,4,3,'Vendedor','Ferreira','03/04/1992','123.000.789-00','M','11-99999-0034','jose@gmail.com',true,2);

insert into USUARIO(login,senha,id_funcionario) values('admin','admin',1);
insert into USUARIO(login,senha,id_funcionario) values('gerente','gerente',2);
insert into USUARIO(login,senha,id_funcionario) values('vendedor','vendedor',3);

insert into Categoria(desc_prod,enabled) values('Selecione',true);
insert into Categoria(desc_prod,enabled) values('Acessórios',true);
insert into Categoria(desc_prod,enabled) values('Cartuchos',true);
insert into Categoria(desc_prod,enabled) values('Computadores',true);
insert into Categoria(desc_prod,enabled) values('Impressoras',true);
insert into Categoria(desc_prod,enabled) values('Notes e Tablets',true);

insert into cliente (dt_cadastro,nome,sobrenome,dt_nasc, cpf, sexo, cel, email, enabled, fk_endereco) 
values('01/01/2017','Teste01','Silva','01/01/2001','29821824838','F','1191234-5678','teste1@',true,3); 
insert into cliente (dt_cadastro,nome,sobrenome,dt_nasc, cpf, sexo, cel, email, enabled, fk_endereco) 
values('02/02/2017','Teste02','Sousa','02/02/2002','12300078900','M','1192222-5678','teste2@',true,2); 
insert into cliente (dt_cadastro,nome,sobrenome,dt_nasc, cpf, sexo, cel, email, enabled, fk_endereco) 
values('03/03/2017','Teste03','Santos','03/03/2003','12345600000','F','1195555-5678','teste3@',true,4); 


insert into produto (vl_prod,desc_prod,fk_id_categoria,enabled) values(10,'pen drive',2,true); 
insert into produto (vl_prod,desc_prod,fk_id_categoria,enabled) values(50,'mouse',2,true); 
insert into produto (vl_prod,desc_prod,fk_id_categoria,enabled) values(1000,'computador',4,true); 


INSERT INTO ESTOQUE(DESC_ESTOQUE,FK_FILIAL,ENABLED)VALUES('Estoque Filial 2', 2,true);
INSERT INTO ESTOQUE(DESC_ESTOQUE,FK_FILIAL,ENABLED)VALUES('Estoque Filial 3', 3,true);
*/
CREATE TABLE Endereco (
    id_endereco INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    cep VARCHAR (9)NOT NULL,
    rua VARCHAR(50)NOT NULL,
    bairro VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    numero VARCHAR(10) NOT NULL
);

CREATE TABLE Filial(
    id_filial INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    cnpj VARCHAR(19) NOT NULL,
    desc_nome VARCHAR(50),
    desc_fantasia VARCHAR(50),
    telefone VARCHAR(15),
    fk_endereco INTEGER NOT NULL REFERENCES ENDERECO(ID_ENDERECO),
    enabled BOOLEAN
);

CREATE TABLE cargo (
    ID_CARGO INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    cargo VARCHAR(50) NOT NULL,
    enabled BOOLEAN
);

CREATE TABLE FUNCIONARIO(
    id_funcionario INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    cod_acesso INTEGER NOT NULL,
    id_cargo INTEGER NOT NULL REFERENCES Cargo(id_cargo),
    id_filial INTEGER NOT NULL REFERENCES Filial(id_filial),
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(200) NOT NULL,
    dt_nasc VARCHAR(10)NOT NULL,
    cpf VARCHAR(15) NOT NULL UNIQUE,
    sexo VARCHAR(10),
    cel VARCHAR(15) NOT NULL,
    email VARCHAR(50) NOT NULL,
    enabled BOOLEAN,
    fk_endereco INTEGER NOT NULL REFERENCES Endereco(id_endereco)
);

CREATE TABLE Cliente (
    id_cliente INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    dt_cadastro VARCHAR(50)NOT NULL,
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(200) NOT NULL,
    dt_nasc VARCHAR(10)NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    sexo VARCHAR(10),
    cel VARCHAR(15) NOT NULL,
    email VARCHAR(50) NOT NULL,
    enabled BOOLEAN,
    fk_endereco INTEGER NOT NULL REFERENCES Endereco(id_endereco)
);

CREATE TABLE Categoria (
    id_categoria INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    desc_prod VARCHAR(50) NOT NULL,
    enabled BOOLEAN
);

CREATE TABLE Produto (
    id_produto INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    vl_prod DOUBLE NOT NULL,
    desc_prod VARCHAR(50),
    FK_ID_CATEGORIA INTEGER NOT NULL REFERENCES CATEGORIA(ID_CATEGORIA),
    enabled BOOLEAN
);

CREATE TABLE ESTOQUE (
    ID_ESTOQUE INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    DESC_ESTOQUE VARCHAR (20),
    FK_FILIAL INTEGER NOT NULL REFERENCES FILIAL(ID_FILIAL),
    ENABLED BOOLEAN
);

CREATE TABLE MOVIMENTACAO_ESTOQUE (
    ID_MOVIMENTACAO INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    TG_ES VARCHAR(1),
    FK_PRODUTO INTEGER NOT NULL REFERENCES PRODUTO(ID_PRODUTO),
    FK_ESTOQUE INTEGER NOT NULL REFERENCES ESTOQUE(ID_ESTOQUE),
    QTDUND INTEGER NOT NULL
);

CREATE TABLE Venda (
    id_venda INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    dt_venda DATE ,
    valor_compra DOUBLE NOT NULL,
    cliente_id INTEGER NOT NULL REFERENCES cliente(id_cliente),    
    filial_id INTEGER NOT NULL REFERENCES filial(id_filial) 
);
 
CREATE TABLE Usuario(
    id_usuario INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    login Varchar(50),
    senha Varchar(50),
    id_funcionario INTEGER NOT NULL REFERENCES FUNCIONARIO (id_funcionario)
);

CREATE VIEW VERIFICA_ESTOQUE AS
SELECT
    FK_ESTOQUE,
    FK_PRODUTO,
    SUM(CASE WHEN TG_ES = 'E' THEN QTDUND ELSE QTDUND * (-1) END) AS SALDO
FROM MOVIMENTACAO_ESTOQUE
GROUP BY
    FK_ESTOQUE,
    FK_PRODUTO
;
CREATE TABLE VENDA_PRODUTO (
    qtdund INTEGER NOT NULL,
    id_venda INTEGER NOT NULL REFERENCES venda(id_venda),      
    produto_id INTEGER NOT NULL REFERENCES produto(id_produto) 
);

