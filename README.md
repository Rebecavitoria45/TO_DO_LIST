# To-Do List com Java RMI

## Descrição

Este projeto implementa um sistema de To-Do List distribuído utilizando **Java RMI (Remote Method Invocation)**. O sistema permite que clientes se conectem a um servidor remoto para adicionar, listar e remover tarefas a fazer de forma remota, utilizando um terminal de comandos.

## Funcionalidades
- 👩 Criação de Usuário
- ✅ Adicionar novas tarefas
- 📋 Listar todas as tarefas 
- ❌ Remover tarefas existentes
- 🌐 Comunicação cliente-servidor via RMI

## Tecnologias Utilizadas
- Java RMI (Remote Method Invocation)

## Estrutura do Projeto

O projeto está dividido nas seguintes partes:
- `common`: Contém a interface remota com os métodos que o cliente pode invocar.
- `servidor`: Implementação do servidor RMI que registra o serviço.
- `cliente`: Implementação do cliente que consome os serviços remotos.

## Como Executar

### Pré-requisitos

- JDK instalado (Java Development Kit)

### Passos para rodar

1. Compile os arquivos:

```bash
javac common/*.java
javac servidor/*.java
javac cliente/*.java
```

2. Em outro terminal inicie o servidor:

```bash
java servidor.TodoList
```

3. Em outro terminal inicie o cliente:

```bash
java cliente.Cliente
```
## 👤 Autores

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/dedecode">
        <img src="https://avatars.githubusercontent.com/u/162852293?v=4" width="100px;" alt="Marilia"/>
        <br>
        <b>André Luiz</b>
      </a>
    </td>
     <td align="center">
      <a href="https://github.com/Rebecavitoria45">
        <img src="https://avatars.githubusercontent.com/u/117654851?v=4" width="100px;" alt="Rebeca"/>
        <br>
        <b>Rebeca vitória</b>
      </a>
    </td>
</table>
