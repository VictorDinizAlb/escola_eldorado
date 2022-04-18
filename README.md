## escola_eldorado

# Após clonar o projeto certifique-se de atualizar as variáveis de conexão com o banco de dados, conforme a sua máquina, em src/main/resources/application.properties.
# Depois, crie um banco de dados Mysql chamado "db_escola"
# Na pasta raíz da aplicação, execute o comando "mvn clean install", o Hibernate criará as tabelas automaticamente.
# Após isso, execute "mvn spring-boot:run" se tudo ocorrer bem, a API estará rodando.

## Algunas ends-points:

  => Para criar usuario: (post) http://localhost:8080/usuario, passando um json com os campos ("nome", "email", "senha", "categoria")
  => Para se autenticar: (post) http://localhost:8080/auth, passando um json com os campos ("email", "senha")
