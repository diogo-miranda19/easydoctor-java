# EasyDoctor - Sistema de Diagnóstico Baseado em Estruturas de Dados

> Projeto académico desenvolvido no âmbito da unidade curricular de **Algoritmos e Estruturas de Dados (AED)** em duplas da Licenciatura em Engenharia Informática no UPB.

---

## Sobre o Projeto
O **EasyDoctor** é uma aplicação desenvolvida em **Java** cujo principal objetivo pedagógico é **implementar estruturas de dados e coleções do zero** (sem depender das bibliotecas padrão do Java como `ArrayList` ou `HashMap`). 

O programa simula um sistema de suporte à decisão médica, permitindo carregar sintomas e doenças a partir de um ficheiro de texto (`Data_EN.txt`), gerir coleções dinâmicas personalizadas e processar diagnósticos baseados no input do utilizador através da consola.

---

## Arquitetura e Estruturas de Dados Implementadas

O projeto está dividido em dois packages principais:

### 1. `mycollections` (Core / Data Structures)
Implementação manual de estruturas fundamentais de gestão de memória e armazenamento:
* **`SinglyLinkedList`**: Lista ligada simples com suporte a nós internos (`Node`) e iterador próprio (`SLLIterator`).
* **`DoublyLinkedList`**: Lista duplamente ligada que permite percorrer os elementos nos dois sentidos (`DLLIterator`).
* **`SortedDoublyLinkedList`**: Variante da lista duplamente ligada que mantém automaticamente os elementos ordenados por ordem de inserção/comparação.
* **`Map`, `AbstractMap` & `LinkedMap`**: Estrutura associativa key-value implementada manualmente para permitir lookups e indexação de dados médicos.

### 2. `principal` (Lógica da Aplicação)
* **`EasyDoctor.java`**: Classe principal que gere a lógica de interação com o utilizador, leitura de ficheiros, exibição de menus de sintomas e matching de informação para diagnostico.
* **`Main.java`**: Entry Point da aplicação.
* **`Data_EN.txt`**: Ficheiro de texto (dataset) contendo as doenças e os respetivos sintomas em inglês.

---

## Como Executar o Projeto

### Pré-requisitos
* **Java JDK** instalado (versão 17 ou superior recomendada).
* **Apache NetBeans IDE** (ou outro editor Java compatível).

### Passos para Executar no NetBeans:
1. Abre o **Apache NetBeans**.
2. Vai a **File** > **Open Project...** e seleciona a pasta raiz do projeto (`trabAED`).
3. No painel esquerdo (*Projects*), expande `src` > `principal` > `Main.java`.
4. Clica com o botão direito em `Main.java` e seleciona **Run File** (ou prime `Shift + F6`).

---

## Estrutura de Pastas
```text
trabAED/
├── src/
│   ├── mycollections/       # Implementação das estruturas de dados do zero
│   │   ├── AbstractMap.java
│   │   ├── DoublyLinkedList.java
│   │   ├── LinkedMap.java
│   │   ├── Map.java
│   │   ├── SinglyLinkedList.java
│   │   └── SortedDoublyLinkedList.java
│   └── principal/           # Lógica do programa
│       ├── Data_EN.txt
│       ├── EasyDoctor.java
│       └── Main.java
└── README.md
```

---

## Conclusões e Aprendizagens

O desenvolvimento do **EasyDoctor** foi uma etapa fundamental na consolidação das bases da engenharia de software e programação em Java:

* **Domínio de Estruturas Dinâmicas:** Construir linked lists e maps sem recorrer às collections nativas do Java permitiu dominar o funcionamento das referências de memória, a alocação dinâmica de nodes.
* **Padrão de Desenho Iterator & Generics:** A utilização de tipos genéricos (`<E>`, `<K, V>`) e a implementação de iteradores personalizados garantiram que as coleções fossem reutilizáveis para qualquer tipo de dado e percorríveis de forma limpa com ciclos `for-each`.
* **Análise de Complexidade:** O projeto permitiu avaliar o impacto real de cada operação (ex.: acesso sequencial $O(n)$ nas listas ligadas vs. inserção controlada $O(1)$ nas extremidades).
* **Trabalho em Equipa e Modularidade:** A divisão clara entre o package das estruturas (`mycollections`) e a lógica da aplicação (`principal`) facilitou a colaboração em dupla, permitindo desenvolver e testar cada componente de forma independente.
