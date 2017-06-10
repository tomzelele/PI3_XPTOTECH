
/*
insert into ENDERECO(CEP,RUA,BAIRRO,CIDADE,ESTADO,NUMERO) VALUES('04940-010','Rua Afonso rui','santa lucia','são paulo','SP','55');
insert into ENDERECO(CEP,RUA,BAIRRO,CIDADE,ESTADO,NUMERO) VALUES('04833-001','Avenida Teotonio Vilela','Vila São José','são paulo','SP','4029');
insert into filial(CNPJ,DESC_NOME,DESC_FANTASIA,TELEFONE,FK_ENDERECO,ENABLED) values('0634270980001','MATRIZ','MATRIZ','51235456',1,true);
insert into filial(CNPJ,DESC_NOME,DESC_FANTASIA,TELEFONE,FK_ENDERECO,ENABLED) values('0123456780001','FILIAL','FILIAL','43211234',2,true);

insert into cargo(cargo,enabled) values('Administrador',true);
insert into cargo(cargo,enabled) values('Gerente',true);
insert into cargo(cargo,enabled) values('Vendedor',true);

insert into funcionario (COD_ACESSO,ID_CARGO,ID_FILIAL,NOME,SOBRENOME,DT_NASC,CPF,SEXO,CEL,EMAIL,ENABLED,FK_ENDERECO)
values (1,1,1,'Joao','Souza','27/12/90','36342709858','M','959668809','jazoniel@gmail.com',true,1);

insert into funcionario (COD_ACESSO,ID_CARGO,ID_FILIAL,NOME,SOBRENOME,DT_NASC,CPF,SEXO,CEL,EMAIL,ENABLED,FK_ENDERECO)
values (2,1,1,'Kelly','Cristina','27/12/90','12345678900','F','999991234','kel0705@gmail.com',true,2);

insert into USUARIO(login,senha,id_funcionario) values('admin','admin',1);
insert into USUARIO(login,senha,id_funcionario) values('kelly','kelly',2);

insert into Categoria(desc_prod,enabled) values('Acessórios',true);
insert into Categoria(desc_prod,enabled) values('Cartuchos',true);
insert into Categoria(desc_prod,enabled) values('Computadores',true);
insert into Categoria(desc_prod,enabled) values('Impressoras',true);
insert into Categoria(desc_prod,enabled) values('Notes e Tablets',true);


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
    cnpj VARCHAR(14) NOT NULL,
    desc_nome VARCHAR(50),
    desc_fantasia VARCHAR(50),
    telefone VARCHAR(15),
    fk_endereco INTEGER NOT NULL REFERENCES ENDERECO(ID_ENDERECO),
    enabled BOOLEAN
);

CREATE TABLE cargo (
    id_cargo INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
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
    FK_ID_PRODUTO INTEGER NOT NULL REFERENCES PRODUTO(ID_PRODUTO),
    ENABLED BOOLEAN
);

CREATE TABLE MOVIMENTACAO_ESTOQUE (
    ID_MOVIMENTACAO INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    FK_PRODUTO INTEGER NOT NULL REFERENCES PRODUTO(ID_PRODUTO),
    FK_ESTOQUE INTEGER NOT NULL REFERENCES ESTOQUE(ID_ESTOQUE),
    QTDUND INTEGER NOT NULL
  
);


-- Cria tabela Venda
CREATE TABLE Venda (
    id_venda INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    dt_venda DATE ,
    valor_compra DOUBLE NOT NULL,
    cliente_id INTEGER NOT NULL REFERENCES cliente(id_cliente)    
    filial_id INTEGER NOT NULL REFERENCES filial(id_filial) 
   
);
 
-- Cria tabela Venda_produto

CREATE TABLE Venda_Produto (
    
    produto_id INTEGER NOT NULL REFERENCES produto(id_produto),
    id_venda INTEGER NOT NULL REFERENCES venda (id_venda),
    qtdUnd INTEGER NOT NULL
    
);

CREATE TABLE Usuario(
    id_usuario INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    login Varchar(50),
    senha Varchar(50),
    id_funcionario INTEGER NOT NULL REFERENCES FUNCIONARIO (id_funcionario)
);



