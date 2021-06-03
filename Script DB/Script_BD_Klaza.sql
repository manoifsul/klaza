CREATE DATABASE Klaza;
USE Klaza;
#tentavias é tentativa maxima
#SEM RELAÇÃO
CREATE TABLE Aluno(
id_aluno BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
nome VARCHAR(200) NOT NULL,
matricula VARCHAR(10) NOT NULL UNIQUE,
senha VARCHAR(20) NOT NULL,
email VARCHAR(40) NOT NULL UNIQUE,
PRIMARY KEY(id_aluno)
);

INSERT INTO Aluno(id_aluno, nome, matricula, senha, email)
VALUES(0, "emanuel", "101010infq", "aluno123456789", "manogamer@gmail.com");

CREATE TABLE Professor(
id_professor BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
nome VARCHAR(200) NOT NULL,
matricula VARCHAR(10) NOT NULL UNIQUE,#tem que ter certeza do numero de caracteries
senha VARCHAR(20) NOT NULL,
email VARCHAR(40) NOT NULL UNIQUE,
PRIMARY KEY(id_professor)
);

INSERT INTO Professor(id_professor, nome, matricula, senha, email)
VALUES(0, "remor", "202020prof", "prof123456789", "remoraguia@gmail.com");

CREATE TABLE Administrador(
id_administrador BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
nome VARCHAR(200) NOT NULL,
login VARCHAR(10) NOT NULL UNIQUE,#tem que ter certeza do numero de caracteries
senha VARCHAR(20) NOT NULL,
email VARCHAR(40) NOT NULL UNIQUE,
PRIMARY KEY(id_administrador)
);

INSERT INTO Administrador(id_administrador, nome, login, senha, email)
VALUES(0, "admin", "4dm1nlogin", "4dm1ns3nh4", "admin@ifsul.com");

CREATE TABLE Materia(
id_materia BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
nome VARCHAR(40) NOT NULL,
PRIMARY KEY(id_materia)
);

INSERT INTO Materia(id_materia, nome)
VALUES(0, "Técnico de Informática");

CREATE TABLE Discord(
id_discord BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
notificacoes TEXT NOT NULL,
provas_trabalhos TEXT NOT NULL,
PRIMARY KEY(id_discord)
);

INSERT INTO Discord(id_discord, notificacoes, provas_trabalhos)
VALUES(0, "Link notificacao", "Link provas_trabalhos");

CREATE TABLE Turma(
id_turma BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
nome VARCHAR(40) NOT NULL,
id_materia BIGINT NOT NULL,
id_discord BIGINT NOT NULL,
FOREIGN KEY(id_materia) REFERENCES Materia(id_materia),
FOREIGN KEY(id_discord) REFERENCES Discord(id_discord),
PRIMARY KEY(id_turma)
);

INSERT INTO Turma(id_turma, nome, id_materia, id_discord)
VALUES(0, "2i", 0, 0);

CREATE TABLE Aula(
id_aula BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
nome VARCHAR(60) NOT NULL,
materia VARCHAR(40) NOT NULL,
descricao TEXT NOT NULL,
inicio DATETIME NOT NULL,
link TEXT NOT NULL,
PRIMARY KEY(id_aula)
);

INSERT INTO Aula(id_aula, nome, materia, descricao, inicio, link)
VALUES(0, "Aula de BD/LP", "Portugues", "Professor: Rodrigo Remor, aula sincrona", "2021-01-01 9:30", "https://meet.google.com/hip-vhmk-fjs");

CREATE TABLE Turma_Aula(
id_turma_aula BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
id_turma BIGINT NOT NULL,
id_aula BIGINT NOT NULL,
FOREIGN KEY(id_turma) REFERENCES Turma(id_turma),
FOREIGN KEY(id_aula) REFERENCES Aula(id_aula),
PRIMARY KEY(id_turma_aula)
);

CREATE TABLE Atividade(
id_atividade BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
descricao TEXT NOT NULL,
inicio DATETIME NOT NULL,
id_turma BIGINT NOT NULL,
FOREIGN KEY(id_turma) REFERENCES Turma(id_turma),
PRIMARY KEY(id_atividade)
);

INSERT INTO Atividade(id_atividade, nome, descricao, inicio, id_turma)
VALUES(0, "realizar a atividade abaixo", "oque fazer", '2021-01-01 11:30:00', 0);

CREATE TABLE Trabalho(
id_trabalho BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
prazo DATETIME NOT NULL,
descricao TEXT NOT NULL,
inicio DATETIME NOT NULL,
tempo LONG NOT NULL,
tentativas VARBINARY(2) NOT NULL,
tipo VARBINARY(1) NOT NULL,
id_turma BIGINT NOT NULL,
FOREIGN KEY(id_turma) REFERENCES Turma(id_turma),
PRIMARY KEY(id_trabalho)
);

INSERT INTO Trabalho(id_trabalho, nome, prazo, descricao, inicio, tempo, tentativas, tipo, id_turma)
VALUES(0, "trabalho sobre matematica", '2021-02-01 23:55:00', "descricao do trab", '2021-01-01 11:30:00', '10800', 3, 0, 0);

CREATE TABLE Prova(
id_prova BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
prazo DATETIME NOT NULL,
descricao TEXT NOT NULL,
inicio DATETIME NOT NULL,
tempo LONG NOT NULL,
tentativas INT NOT NULL,
id_turma BIGINT NOT NULL,
FOREIGN KEY(id_turma) REFERENCES Turma(id_turma),
PRIMARY KEY(id_prova)
);

INSERT INTO Prova(id_prova, nome, prazo, descricao, inicio, tempo, tentativas, id_turma)
VALUES(0, "prova de matematica", '2021-01-08 23:55:00', "descricao da prova", '2021-01-01 11:30:00', 10800, 2, 0);

CREATE TABLE Questao(
id_questao BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
pergunta TEXT NOT NULL,
tipo VARBINARY(1) NOT NULL,
PRIMARY KEY(id_questao)
);

INSERT INTO Questao(id_questao, pergunta, tipo)
VALUES(0, "quanto é 1+1?", 1);

CREATE TABLE Arquivo_Aula(
id_arquivo_aula BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
id_aula BIGINT NOT NULL,
link TEXT NOT NULL,
FOREIGN KEY(id_aula) REFERENCES Aula(id_aula),
PRIMARY KEY(id_arquivo_aula)
);

INSERT INTO Arquivo_Aula(id_arquivo_aula)
VALUES(0, 0, "www.arquivo.com");

CREATE TABLE Arquivo_Prova(
id_arquivo_prova BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
id_prova BIGINT NOT NULL,
link TEXT NOT NULL,
FOREIGN KEY(id_prova) REFERENCES Prova(id_prova),
PRIMARY KEY(id_arquivo_prova)
);

INSERT INTO Arquivo_Prova(id_arquivo_prova, id_prova, link)
VALUES(0, 0, "www.arquivo.com");

CREATE TABLE Arquivo_Trabalho(
id_arquivo_trabalho BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
id_trabalho BIGINT NOT NULL,
link TEXT NOT NULL,
FOREIGN KEY(id_trabalho) REFERENCES Trabalho(id_trabalho),
PRIMARY KEY(id_arquivo_trabalho)
);

INSERT INTO Arquivo_Trabalho(id_arquivo_trabalho, id_trabalho, link)
VALUES(0, 0, "www.arquivo.com");

CREATE TABLE Arquivo_Atividade(
id_arquivo_atividade BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
id_atividade BIGINT NOT NULL,
link TEXT NOT NULL,
FOREIGN KEY(id_atividade) REFERENCES Atividade(id_atividade),
PRIMARY KEY(id_arquivo_atividade)
);

INSERT INTO Arquivo_Atividade(id_arquivo_atividade, id_atividade, link)
VALUES(0, 0, "www.arquivo.com");

CREATE TABLE Turma_Aluno(
id_turma_aluno BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
id_turma BIGINT NOT NULL,
id_aluno BIGINT NOT NULL,
FOREIGN KEY(id_turma) REFERENCES Turma(id_turma),
FOREIGN KEY(id_aluno) REFERENCES Aluno(id_aluno),
PRIMARY KEY(id_turma_aluno)
);

INSERT INTO Turma_Aluno(id_turma_aluno, id_turma, id_aluno)
VALUES(0, 0, 0);

CREATE TABLE Aluno_Prova(
id_aluno_prova BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
tempo LONG NOT NULL,
inicio DATETIME NOT NULL,
finalizada DATETIME NOT NULL,
id_aluno BIGINT NOT NULL,
id_prova BIGINT NOT NULL,
FOREIGN KEY(id_aluno) REFERENCES Aluno(id_aluno),
FOREIGN KEY(id_prova) REFERENCES Prova(id_prova),
PRIMARY KEY(id_aluno_prova)
);

INSERT INTO Aluno_Prova(id_aluno_prova, tempo, inicio, finalizada, id_aluno, id_prova)
VALUES(0, 7200, "2021-01-01 11:30", "2021-01-20 15:34", 0, 0);

CREATE TABLE Aluno_Trabalho(
id_aluno_trabalho BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
tempo LONG NOT NULL,
inicio DATETIME NOT NULL,
finalizada DATETIME NOT NULL,
id_aluno BIGINT NOT NULL,
id_trabalho BIGINT NOT NULL,
FOREIGN KEY(id_aluno) REFERENCES Aluno(id_aluno),
FOREIGN KEY(id_trabalho) REFERENCES Trabalho(id_trabalho),
PRIMARY KEY(id_aluno_trabalho)
);

INSERT INTO Aluno_Trabalho(id_aluno_trabalho, tempo, inicio, finalizada, id_aluno, id_trabalho)
VALUES(0, 7200, "2021-01-01 11:30", "2021-01-20 15:34", 0, 0);

CREATE TABLE Resposta_Aluno(
id_resposta_aluno BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
resposta TEXT NOT NULL,
id_aluno BIGINT NOT NULL,
id_questao BIGINT NOT NULL,
FOREIGN KEY(id_aluno) REFERENCES Aluno(id_aluno),
FOREIGN KEY(id_questao) REFERENCES Questao(id_questao),
PRIMARY KEY(id_resposta_aluno)
);

INSERT INTO Resposta_Aluno(id_resposta_aluno, resposta, id_aluno, id_questao)
VALUES(0, "a resposta é o jogo", 0, 0);

CREATE TABLE Materia_Professor(
id_materia_professor BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
id_materia BIGINT NOT NULL,
id_professor BIGINT NOT NULL,
FOREIGN KEY(id_materia) REFERENCES Materia(id_materia),
FOREIGN KEY(id_professor) REFERENCES Professor(id_professor),
PRIMARY KEY(id_materia_professor)
);

INSERT INTO Materia_Professor(id_materia_professor, id_materia, id_professor)
VALUES(0, 0, 0);

CREATE TABLE Turma_Professor(
id_turma_professor BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
id_turma BIGINT NOT NULL,
id_professor BIGINT NOT NULL,
FOREIGN KEY(id_turma) REFERENCES Turma(id_turma),
FOREIGN KEY(id_professor) REFERENCES Professor(id_professor),
PRIMARY KEY(id_turma_professor)
);

INSERT INTO Turma_Professor(id_turma_professor, id_turma, id_professor)
VALUES(0, 0, 0);

CREATE TABLE Professor_Prova(
id_professor_prova BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
id_professor BIGINT NOT NULL,
id_prova BIGINT NOT NULL,
FOREIGN KEY(id_professor) REFERENCES Professor(id_professor),
FOREIGN KEY(id_prova) REFERENCES Prova(id_prova),
PRIMARY KEY(id_professor_prova)
);

INSERT INTO Professor_Prova(id_professor_prova, id_professor, id_prova)
VALUES(0, 0, 0);

CREATE TABLE Professor_Trabalho(
id_professor_trabalho BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
id_professor BIGINT NOT NULL,
id_trabalho BIGINT NOT NULL,
FOREIGN KEY(id_professor) REFERENCES Professor(id_professor),
FOREIGN KEY(id_trabalho) REFERENCES Trabalho(id_trabalho),
PRIMARY KEY(id_professor_trabalho)
);

INSERT INTO(id_professor_trabalho, id_professor, id_trabalho)
VALUES(0, 0, 0);

CREATE TABLE Professor_Atividade(
id_professor_atividade BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
id_professor BIGINT NOT NULL,
id_atividade BIGINT NOT NULL,
FOREIGN KEY(id_professor) REFERENCES Professor(id_professor),
FOREIGN KEY(id_atividade) REFERENCES Atividade(id_atividade),
PRIMARY KEY(id_professor_atividade)
);

INSERT INTO Professor_Atividade(id_professor_atividade, id_professor, id_atividade)
VALUES(0, 0, 0);

CREATE TABLE Administrador_Prova(
id_administrador_prova BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
id_administrador BIGINT NOT NULL,
id_prova BIGINT NOT NULL,
FOREIGN KEY(id_administrador) REFERENCES Administrador(id_administrador),
FOREIGN KEY(id_prova) REFERENCES Prova(id_prova),
PRIMARY KEY(id_administrador_prova)
);

INSERT INTO Administrador_Prova(id_administrador_prova, id_administrador, id_prova)
VALUES(0, 0, 0);

CREATE TABLE Administrador_Trabalho(
id_administrador_trabalho BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
id_administrador BIGINT NOT NULL,
id_trabalho BIGINT NOT NULL,
FOREIGN KEY(id_administrador) REFERENCES Administrador(id_administrador),
FOREIGN KEY(id_trabalho) REFERENCES Trabalho(id_trabalho),
PRIMARY KEY(id_administrador_trabalho)
);

INSERT INTO Administrador_Trabalho(id_administrador_trabalho, id_administrador, id_trabalho)
VALUES(0, 0, 0);

CREATE TABLE Administrador_Atividade(
id_administrador_atividade BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
id_administrador BIGINT NOT NULL,
id_atividade BIGINT NOT NULL,
FOREIGN KEY(id_administrador) REFERENCES Administrador(id_administrador),
FOREIGN KEY(id_atividade) REFERENCES Atividade(id_atividade),
PRIMARY KEY(id_administrador_atividade)
);

INSERT INTO Administrador_Atividade(id_administrador_atividade, id_administrador, id_atividade)
VALUES(0, 0, 0);

CREATE TABLE Questao_Correta(
id_questao_correta BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
id_questao BIGINT NOT NULL,
id_questao_alternativa BIGINT NOT NULL,
FOREIGN KEY(id_questao) REFERENCES Questao(id_questao),
FOREIGN KEY(id_questao_alternativa) REFERENCES Questao_Alternativa(id_questao_alternativa),
PRIMARY KEY(id_questao_correta)
);

INSERT INTO Questao_Correta(id_questao_correta, id_questao, id_questao_alternativa)
VALUES(0, 0, 0);

CREATE TABLE Questao_Alternativa(
id_questao_alternativa BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
resposta TEXT NOT NULL,
id_questao BIGINT NOT NULL,
FOREIGN KEY(id_questao) REFERENCES Questao(id_questao),
PRIMARY KEY(id_questao_alternativa)
);

INSERT INTO Questao_Alternativa(id_questao_alternativa, resposta, id_questao)
VALUES(0, "resposta esperada", 0);

CREATE TABLE Questao_Trabalho(
id_questao_trabalho BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
id_questao BIGINT NOT NULL,
id_trabalho BIGINT NOT NULL,
FOREIGN KEY(id_questao) REFERENCES Questao(id_questao),
FOREIGN KEY(id_trabalho) REFERENCES Trabalho(id_trabalho),
PRIMARY KEY(id_questao_trabalho)
);

INSERT INTO Questao_Trabalho(id_questao_trabalho, id_questao, id_trabalho)
VALUES(0, 0, 0);

CREATE TABLE Questao_Prova(
id_questao_prova BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
id_questao BIGINT NOT NULL,
id_prova BIGINT NOT NULL,
FOREIGN KEY(id_questao) REFERENCES Questao(id_questao),
FOREIGN KEY(id_prova) REFERENCES Prova(id_prova),
PRIMARY KEY(id_questao_prova)
);

INSERT INTO Questao_Prova(id_questao_prova, id_questao, id_prova)
VALUES(0, 0, 0);

CREATE TABLE Nota_Trabalho(
id_nota_trabalho BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
valor FLOAT NOT NULL,
id_trabalho BIGINT NOT NULL,
id_aluno BIGINT NOT NULL,
FOREIGN KEY(id_trabalho) REFERENCES Trabalho(id_trabalho),
FOREIGN KEY(id_aluno) REFERENCES Aluno(id_aluno),
PRIMARY KEY(id_nota_trabalho)
);

INSERT INTO Nota_Trabalho(id_nota_trabalho, valor, id_trabalho, id_aluno)
VALUES(0, 1.5, 0, 0);

CREATE TABLE Nota_Prova(
id_nota_prova BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
valor FLOAT NOT NULL,
id_prova BIGINT NOT NULL,
id_aluno BIGINT NOT NULL,
FOREIGN KEY(id_prova) REFERENCES Prova(id_prova),
FOREIGN KEY(id_aluno) REFERENCES Aluno(id_aluno),
PRIMARY KEY(id_nota_prova)
);

INSERT INTO Nota_Prova(id_nota_prova, valor, id_prova, id_aluno)
VALUES(0, 3.5, 0, 0);



