import mysql.connector
import requests
from faker import Faker

#pip install Faker requests mysql-connector-python
#pip install mysql-connector-python requests

# Configure as informações de conexão com o banco de dados
connection = mysql.connector.connect(
    host="LocalHost",
    user="root",
    password="kauan123",
    database="apsproject4"
)

cursor = connection.cursor()

# Criando uma instância do Faker para gerar dados fictícios
fake = Faker()

# Loop para inserir registros fictícios na tabela
for _ in range(50000):
    nome = fake.name()
    idade = fake.random_int(min=1, max=99)
    cpf = fake.unique.random_int(min=10000000000, max=99999999999)
    pais = fake.country()
    
    # Usando a API Lorem Picsum para obter uma imagem aleatória
    response = requests.get("https://picsum.photos/200/200")
    imagem_blob = response.content

    # Inserindo os dados na tabela
    cursor.execute("INSERT INTO APS (APS_NOME, APS_IDADE, APS_CPF, APS_PAIS, APS_IMAGEMPAIS) VALUES (%s, %s, %s, %s, %s)",
                   (nome, idade, cpf, pais, imagem_blob))
    print(_)
print('Dados inseridos com sucesso!')
connection.commit()

# Feche a conexão com o banco de dados
cursor.close()
connection.close()

#CREATE TABLE APS (
#    APS_NOME VARCHAR(255) NOT NULL,
#    APS_IDADE INT NOT NULL,
#    APS_CPF VARCHAR(11) NOT NULL,
#    APS_PAIS VARCHAR(255),
#    APS_IMAGEMPAIS LONGBLOB,
#    UNIQUE (APS_CPF)
#)
