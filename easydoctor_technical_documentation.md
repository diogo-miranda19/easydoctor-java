# Guia Completo e Documentação Técnica: Projeto EasyDoctor (AED)

> **Nota de estudo:** Este documento foi estruturado para servir como a tua folha de consulta completa. Contém a explicação detalhada da arquitetura, o funcionamento interno de cada ficheiro/classe, o fluxo de execução passo a passo e respostas preparadas caso precises de explicar o projeto a um recrutador, colega ou professor.

---

## 1. Visão Geral e O Conceito do Projeto

O **EasyDoctor** (originalmente designado *AIDoctor*) é um sistema de consola desenvolvido em **Java** para a unidade curricular de **Algoritmos e Estruturas de Dados (AED)** no IPB, realizado em dupla.

* **O que o programa faz na prática?** Lê um ficheiro de texto (`Data_EN.txt`) que contém doenças e os respetivos sintomas associados, apresenta uma lista de sintomas ao utilizador no terminal, recolhe os sintomas que o utilizador diz ter e cruza essa informação para sugerir o diagnóstico mais provável.
* **Qual é o verdadeiro objetivo técnico?** Não é inteligência artificial (apesar do nome original). O grande desafio académico de AED é **não usar as estruturas prontas do Java** (`ArrayList`, `LinkedList`, `HashMap`). Todo o armazenamento em memória foi programado **do zero** utilizando referências entre objetos (nós), tipos genéricos (`Generics`) e iteradores próprios.

---

## 2. Fluxo de Execução Passo a Passo (O que acontece quando carregas no Play)

Quando executas o ficheiro `Main.java`, o programa segue exatamente esta sequência lógica:

1. **Arranque (`Main.java`)**:
   * O método `main` cria uma instância da classe principal: `EasyDoctor doctor = new EasyDoctor();`.
2. **Carregamento de Dados (Parsing do `Data_EN.txt`)**:
   * No construtor (ou método de inicialização) do `EasyDoctor`, o programa abre o ficheiro `Data_EN.txt`.
   * Lê linha a linha, separa os nomes das doenças e os respetivos sintomas, e guarda essa informação em memória utilizando as estruturas personalizadas do pacote `mycollections` (como o `LinkedMap` e as listas ligadas).
3. **Exibição da Lista de Sintomas (`showSymptomList()`)**:
   * O programa percorre a coleção de sintomas únicos carregados (usando os iteradores personalizados) e imprime-os numerados na consola para o utilizador poder escolher.
4. **Interação e Recolha (`getSymptoms()`)**:
   * Através de um `Scanner`, o programa pede ao utilizador que introduza os números/nomes dos sintomas que sente.
   * Cada sintoma escolhido pelo utilizador é guardado numa lista ligada dinâmica (ex.: `SinglyLinkedList` ou `DoublyLinkedList`).
5. **Processamento e Diagnóstico**:
   * O algoritmo compara a lista de sintomas do utilizador com os sintomas associados a cada doença guardada no mapa (`LinkedMap`), calcula a correspondência/probabilidade (quantos sintomas batem certo) e apresenta o resultado final no ecrã.

---

## 3. Explicação Detalhada do Código por Pacote

### Pacote `mycollections` (O Motor de Estruturas de Dados)

Este pacote é o coração técnico do trabalho. Todas as classes usam **Generics** (`<E>`, `<K, V>`), o que significa que funcionam para qualquer tipo de objeto (Strings, Integers, Doenças, etc.).

#### A. `SinglyLinkedList.java` (Lista Simplesmente Ligada)
* **Como funciona:** É uma corrente de objetos chamados `Node` (Nó). Cada `Node` guarda duas coisas: o dado (`element`) e uma seta/referência para o nó seguinte (`next`). O último nó aponta para `null`.
* **Vantagem:** Cresce dinamicamente sem precisar de definir um tamanho fixo à partida (ao contrário de um array normal `String[]`).
* **Classe interna `SLLIterator`:** Implementa a interface `Iterator<E>` do Java. Serve para permitir percorrer a lista do início ao fim com um ciclo `for (String s : lista)` sem expor os nós internos ao resto do programa.
* **Complexidade:** Inserir no início é muito rápido — $O(1)$. Procurar um elemento obriga a percorrer desde o início — $O(n)$.

#### B. `DoublyLinkedList.java` (Lista Duplamente Ligada)
* **Como funciona:** Semelhante à anterior, mas cada `Node` tem **duas** referências: uma para o próximo nó (`next`) e outra para o nó anterior (`prev`). A lista guarda referência para a cabeça (`header`) e para a cauda (`trailer`).
* **Vantagem:** Permite andar para a frente e para trás na lista e facilita muito a remoção de elementos no meio ou no fim, pois não precisas de voltar ao início para descobrir quem estava antes.
* **Classe interna `DLLIterator`:** Iterador bidirecional/sequencial próprio para esta estrutura.

#### C. `SortedDoublyLinkedList.java` (Lista Duplamente Ligada Ordenada)
* **Como funciona:** É uma evolução da `DoublyLinkedList` onde os elementos têm de implementar `Comparable<E>` (ou usar um comparador).
* **O que faz de especial:** No método `add(E element)`, em vez de meter o elemento simplesmente no fim, o código percorre a lista até encontrar a posição alfabética ou numérica exata onde o elemento encaixa, ajustando os apontadores `prev` e `next` dos vizinhos. Garante que a lista está **sempre ordenada** (útil, por exemplo, para apresentar os sintomas por ordem alfabética ao utilizador).

#### D. `Map.java`, `AbstractMap.java` e `LinkedMap.java` (Dicionário Chave-Valor)
* **`Map.java`:** É a *Interface* (o contrato) que define os métodos obrigatórios de um mapa: `put(key, value)`, `get(key)`, `remove(key)`, `size()`, `isEmpty()`.
* **`AbstractMap.java`:** Classe abstrata que implementa métodos comuns e define a classe interna `Entry<K, V>` (o par que junta uma Chave a um Valor).
* **`LinkedMap.java`:** É a implementação concreta. Usa internamente uma lista ligada de entradas (`Entry<K, V>`) para guardar associações.
  * *Exemplo prático no EasyDoctor:* A **Chave (`K`)** é o nome de uma Doença (ex: `"Flu"`) e o **Valor (`V`)** é uma lista ligada com os sintomas dessa doença (ex: `["Fever", "Cough", "Fatigue"]`).
  * Inclui ainda `KeyIterable` e `KeyIterator` para permitir percorrer apenas as chaves do mapa.

---

### Pacote `principal` (A Aplicação)

#### A. `Data_EN.txt`
* É a "base de dados" em formato de texto simples do programa. Contém a estrutura de doenças e respetivos sintomas separados por delimitadores específicos (como vírgulas, pontos e vírgulas ou quebras de linha) que o código sabe interpretar.

#### B. `EasyDoctor.java`
* Contém toda a lógica de negócio:
  * **Leitura/Parsing:** Usa `BufferedReader` ou `Scanner` para ler o `Data_EN.txt`, partir as strings (`split()`) e preencher as coleções do pacote `mycollections`.
  * **Interface de Consola:** Métodos como `showSymptomList()` e `getSymptoms()` que comunicam com o utilizador e validam os dados inseridos.
  * **Motor de Correspondência:** Compara os sintomas inseridos pelo utilizador com os dados armazenados no `LinkedMap` para determinar o diagnóstico.

#### C. `Main.java`
* É uma classe minimalista e limpa, seguindo as boas práticas de Programação Orientada a Objetos: serve apenas para arrancar o programa, instanciar `EasyDoctor` e chamar os métodos principais, mantendo a lógica pesada isolada na classe `EasyDoctor`.

---

## 4. Como Explicar Este Projeto Numa Entrevista (Guião Rápido)

Se um recrutador olhar para o teu GitHub e perguntar **"Fala-me deste projeto EasyDoctor em Java"**, podes responder exatamente assim:

> *"O EasyDoctor foi um projeto desenvolvido em dupla na cadeira de Algoritmos e Estruturas de Dados. O objetivo era criar um sistema de diagnóstico baseado em sintomas lidos de um ficheiro de texto, mas com uma regra fundamental: não podíamos usar as coleções nativas do Java como `ArrayList` ou `HashMap`.*
>
> *Por isso, dividimos a arquitetura em dois pacotes. No pacote `mycollections`, implementámos do zero listas simplesmente ligadas, duplamente ligadas, listas ordenadas e um `LinkedMap` chave-valor com tipos genéricos e iteradores próprios. No pacote `principal`, criámos a lógica da aplicação que faz o parsing dos dados médicos, armazena tudo nas nossas estruturas personalizadas e cruza os sintomas escolhidos pelo utilizador para apresentar o diagnóstico. Foi um projeto excelente para consolidar apontadores, gestão de memória e complexidade algorítmica."*

---

## 5. Perguntas Técnicas Frequentes (Para Estares Preparado)

* **Porque é que usaram `LinkedMap` em vez de uma matriz ou array simples?**
  * *Resposta:* Porque cada doença tem um número variável de sintomas e não sabíamos quantas doenças existiam no ficheiro à partida. O `LinkedMap` permite associar diretamente cada doença (Chave) à sua respetiva lista dinâmica de sintomas (Valor), tornando o código muito mais limpo e escalável.
* **Qual a diferença entre a `SinglyLinkedList` e a `DoublyLinkedList` no vosso código?**
  * *Resposta:* Na `SinglyLinkedList`, cada nó só conhece o próximo (`next`), o que poupa memória, mas obriga a percorrer a lista desde o início se quisermos remover o último elemento. Na `DoublyLinkedList`, cada nó tem `next` e `prev`, o que facilita operações nas duas extremidades e simplifica a ordenação na `SortedDoublyLinkedList`.
* **Para que servem os ficheiros `SLLIterator` e `DLLIterator`?**
  * *Resposta:* Implementam o padrão de desenho *Iterator*. Permitem que a classe `EasyDoctor` percorra as listas de sintomas usando um ciclo `for-each` normal do Java sem precisar de saber como os nós (`Node`) estão ligados por dentro (encapsulamento).