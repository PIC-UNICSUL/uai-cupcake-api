# Uai Cupcakes

## Para rodar local
- Configure o docker 
- Definir o profile local

## Como Configurar o Banco com Docker

### Pré-requisitos:
- Instale o [Docker](https://www.docker.com/) e o [Docker Compose](https://docs.docker.com/compose/).

---

### Para iniciar o Banco:

No diretório `docker/`, execute:
   ```bash
        docker-compose up
   ```

Isso iniciará um contêiner com PostgreSQL configurado para o projeto.

### Conexão ao Banco:
- Host: localhost
- Porta: 5432
- Usuário: cupcake_user
- Senha: cupcake_password
- Banco de dados: cupcake_db


### Parar o contêiner:

Para parar e remover o contêiner:

   ```bash
        docker-compose down
   ```
---