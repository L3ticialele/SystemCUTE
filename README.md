# TCC - Sistema de Telemetria para Cubesats

## Descrição do Projeto
<p>Trabalho de Conclusão de Curso de Informática.</p>
<p>O trabalho consiste no desenvolvimento de um sistema que permita fazer a transferência de dados de um cubesat por telemetria, incluindo o armazenamento e a segurança dos dados.</p>

## 🔨 Funcionalidades do Projeto

- `Funcionalidade 1`: Coletar dados do cubesat.
- `Funcionalidade 2`: Criptografar dados.
- `Funcionalidade 3`: Armazenar dados, como CO2, luz, umidade e pressão, no banco de dados.
- `Funcionalidade 4`: Traduzir sinais numéricos para sinais textuais.

## 🧑‍🔬 Desenvolvedores

- Hilary Kate
- Letícia Ferreira
- Marina Lopes
- Matheus Amaral
- Victor Nunes

## Funcionamento do Projeto 
<p>1- Para que o projeto funcione, é necessário que a máquina do usuário contenha o PgAdmin 16 instalado.</p>
<p>1.2- O próprio código contém o necessário para criar o banco de dados, basta alterar os campos "user" e "password" que se encontram no arquivo "persistence.xml" na seção "source" que está dentro do módulo "entidades" na pasta "src/main/resources/META-INF"</p>
<p>2- O sistema possui autenticação de usuário, ou seja, é necessário que o usuário realize seu cadastro para que, posteriormente, possa logar no sistema</p>
<p>2.1- Caso o usuário não lembre qual foi a senha cadastrada, ele pode solicitar uma nova senha na opção "Esqueci minha senha". Esta será enviada para o e-mail cadastrado e poderá ser alterada na seção "Perfil"</p>
<p>3- Para cadastrar novos dados no cubesat, é necessário que o arquivo ".txt" enviado esteja no formato específico do que está contido na pasta "Arquivo de dados do Cubesat"</p>
<p>4- Não é possível apagar os dados que já foram cadastrados no cubesat</p>
<p>5- O usuário pode baixar os dados numa planilha selecionando a opção "Baixar planilha" do cubesat escolhido</p>
