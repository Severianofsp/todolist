# To do list
O Projeto tem o propósito de criar uma aplicação onde o usuário poderá gerenciar sua lista de coisas a fazer no dia a dia;

### Requisitos
- Java 1.8 [download](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html) obs: necessário fazer cadastro para realizar download
- Node.Js

### Iniciando Projeto
Para iniciar o backend, só é necessário acessar a pasta do projeto usando alguma IDE como [Eclipse](https://www.eclipse.org/downloads/download.php?file=/oomph/epp/2020-12/R/eclipse-inst-jre-win64.exe) ou [Intellij](https://www.jetbrains.com/pt-br/idea/download/download-thanks.html?platform=windows&code=IIC) e ir na classe TodolistApplication.java dentro de src/main/java/br/com/teste/todolist/ e pressionar o botão para inicializar o projeto. Ele está configurado para rodar no localhost:8080/


### Endpoints
Foram criados inicialmente endpoints para cadastro de usuários, no decorrer da evolução do projeto serão incluídos novos Endpoints como:

##### prefix: /user
-  GET : Retorna todos usuários cadastrados
-  POST: Salva usuário no banco de dados
-  GET /id : Retorna usuário cadastrado com a ID selecionada
-  PUT /id : Atualiza usuário selecionado passando as alterações pelo body
-  DELETE /id : Deleta usuário no banco de dados
