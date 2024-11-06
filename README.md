# Code-Refactor-UCB-QSG
Repositório para manter os códigos originais e refatorados do projeto em Java de POO. Atividade da matéria de Qualidade de Software e Governança

## Projeto de Refatoração de Código em Java
Este repositório contém um projeto em Java dividido em duas versões: a versão original e a versão refatorada. O objetivo é demonstrar como aplicar técnicas de refatoração para melhorar a qualidade e a manutenção do código. A refatoração aborda problemas de complexidade ciclomática, tratamento de entradas do usuário e melhorias de estrutura do código.

---

### Funcionalidades do Projeto
A aplicação consiste em um sistema de gerenciamento de funcionários e projetos, com as seguintes funcionalidades:

- Cadastrar funcionários: Adiciona novos funcionários ao projeto.
- Alocar/Remover funcionários: Aloca ou remove funcionários dos projetos existentes.
- Verificar prazos de entrega: Permite que o usuário visualize as datas de entrega dos projetos.

### Melhorias na Versão Refatorada
- Melhor tratamento de entradas do usuário: Foi adicionada uma limpeza do buffer do Scanner após as chamadas de nextInt() e nextDouble() para evitar que o programa pule entradas de texto.
- Organização e modularização: O código foi dividido em métodos menores e reutilizáveis para reduzir a complexidade e melhorar a legibilidade.
- Controle de fluxo: Verificação adicional foi adicionada para impedir ações em projetos inexistentes, retornando ao menu principal em caso de erro.

---

Arthur Lemos Bendini

