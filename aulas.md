# Aulas

## 20/02/2024

* Atributos de Qualidade = Requisitos não funcionais

### Atributo de qualidade:
* Uma pripriedade do sistema que pode ser medida ou testada
* Não são normalmente problemas de funcionaliades
* Existem duas categorias que vamos abordar:
    *   execução: sistema de execução, como disponibildiade, desempenho ou usabilidade
    *   desenvolvimento: sobre o desenvolvimento do sitema, como modificabilidade/flexibilidade.
* Descrevem cenários que incluem:
    * Estímulo
    * Fonte de estímulo
    * Resposta
    * Medidade da resposta
    * Ambiente
    * Artefacto: normalmente o sistema em si,mas pode ser mais específico
### Padrões e táticas/Patterns
* Um **padrão** é um conjunto de **táticas**.
* Os **padrões** fazem compromissos entre atributos de qualidade.

### Disponibilidade
* Propriedade do sistema executar uma tarefa quando necessária.
* As **faltas** podem acontecer tanto internamente como externamente.
* Padrões de disponibilidade:
    * *Redundância ativa*: Vários nós que recebem e procedem pedidos em paralelo.
    * *Reduncdância passiva/warm spare*: Só o nó ativo é que processa pedodps. Partilha periodicamente o estado com os nós redundantes.
    * *Spare(cold spare)*: Os nós redundantes estão inativos até ocorrer uma falha.

### Implantação/Deployment:
* Se o processo for totalmente automático (sem intervenção humana) - *continuous deployment*.
* O foco de um engenheiro de software é o grau com que uma arquitetura satisfaz implantações:
    * *Granulares*: com opções para diferentes implantações, ou seja não "tudo ou nada".
    * *Controláveis*: para implementar, monitorizar operações e reverter implantações mal sucedidas;
    * *Eficientes*: para dar suporte à implantação rápida
* Padrão de micro-serviços para implantação:
    * Cada micro-serviço vai ter uma base de dados independente.
    * Não possuem memória partilahda, por que se isso acontecesse não era independentes
* Desvantagens:
    * Se houver um problema na modificação dos dados nas bases de dados, houver dados inconsistentes nas diferentes bases de dados;
    * O controlo intelectual do sistema total pode ser dificil devido ao grande numer de micro-serviços;
    * ...

### Integrabilidade (Integrability)
* Podermos integrar diferentes compoennetes num mesmo sistema;
* Tamanho -> número de dependências;
* Distância -> dificuldade em resolver problemas criados por essas dependências;
* Podem haver várias distâncias:
    * *sintática*:
    * *semantica*: semantica dos dados, quero receber em milhas, mas envio a mensagem em kms
    * *comportamento semantico*: quem envia mensagem a quem
    * *temporal*: 
    * *Não vi*:
* Orquestração: um compenente que faz a orquestração dos micro-serviços.
* Padrões:
    * Arquiteturas baseada em micro-serviços;
    * Serviços RESTful;

### Desempenho
* Refere-se à capacidade de um Sistema de software de satisfazer requisitos temporais, (quanto tempo aquilo demora???)
* Medido por tarefas executadas / (a dividir) unidade de tempo e também temos a latência;

### Escalabilidade
* A habilidade do sistema sustentar o aumento de workload, ao usar mais recursos.
* Henning and Hasselbring definitions:
    * **Demand metric**: Com o aumento dos recursos como é que aumenta a intensidade de carga (pedidos concorrentes);
    * **Capacity metric**: Como é que a carga aumenta com o aumento de recursos;

# 27/02/2024

## Micro-serviços
* Existe uma base de dados para cada microsserviço
* A auntenticação também pode ser um microsserviço
* Promovem *Continous Delivery* e *Continous Deplyoment*

## SOA
* É mais conceptual

## Comunicação entre serviços
* Padões de interação baseados em mensagens síncronas (REST, SOAP, RPC)
* Padões de interação baseados em mensagens assíncronas (Kafka)

Formas de saber quais ips é que estão disponiveis:
* Ter um bd para ter quais ips e que serviço podem funcionar (como o RMI Registry)

