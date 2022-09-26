# CRUD API para Eventos

---
### Descrição
Aplicação feita com Java Spring Boot, JPA e H2database para o gerenciamento e organização de eventos e suas dependências.

---


Table of Contents
  - [Endpoints de Equipamentos](#endpoints-de-equipamentos)
    - [Post Equipamento](#post-equipamento)
    - [Get Equipamento](#get-equipamento)
    - [Put Equipamento](#put-equipamento)
    - [Delete Equipamento](#delete-equipamento)
####

  - [Endpoints de Colaboradores](#endpoints-de-colaboradores)
    - [Post Colaborador](#post-colaborador)
    - [Get Colaborador](#get-colaborador)
    - [Put Colaborador](#put-colaborador)
    - [Delete Colaborador](#delete-colaborador)
####
  - [Endpoints de Locais](#endpoints-de-locais)
    - [Post Local](#post-local)
    - [Get Local](#get-local)
    - [Put Local](#put-local)
    - [Delete Local](#delete-local)
####
  - [Endpoints de Agendas](#endpoints-de-agendas)
    - [Post Agenda](#post-agenda)
    - [Get Agenda](#get-agenda)
    - [Put Agenda](#put-agenda)
    - [Delete Agenda](#delete-agenda)
####
- [Endpoints Agenda Equipamentos ManyToMany](#endpoints-agenda-equipamentos-manytomany)
  - [Post Equipamento em uma Agenda](#post-equipamento-em-uma-agenda)
  - [Get Equipamentos de uma Agenda](#get-equipamentos-de-uma-agenda)

---
### Endpoints de Equipamentos

#### Post Equipamento
```js
POST api/v1/equipamentos
```

#### Get Equipamento
```js
GET api/v1/equipamentos/{id}
```
```js
GET api/v1/equipamentos/
```

#### Put Equipamento
```js
PUT api/v1/equipamentos/{id}
```

#### Delete Equipamento
```js
DELETE api/v1/equipamentos/{id}
```
---


### Endpoints de Colaboradores

#### Post Colaborador
```js
POST api/v1/colaboradores
```

#### Get Colaborador
```js
GET api/v1/colaboradores/{id}
```
```js
GET api/v1/colaboradores/
```

#### Put Colaborador
```js
PUT api/v1/colaboradores/{id}
```

#### Delete Colaborador
```js
DELETE api/v1/colaboradores/{id}
```
---


### Endpoints de Locais

#### Post Local
```js
POST api/v1/locais
```

#### Get Local
```js
GET api/v1/locais/{id}
```
```js
GET api/v1/locais/
```

#### Put Local
```js
PUT api/v1/locais/{id}
```

#### Delete Local
```js
DELETE api/v1/locais/{id}
```
---


### Endpoints de Agendas

#### Post Agenda
```js
POST api/v1/agendas
```

#### Get Agenda
```js
GET api/v1/agendas/{id}
```
```js
GET api/v1/agendas/
```

#### Put Agenda
```js
PUT api/v1/agendas/{id}
```

#### Delete Agenda
```js
DELETE api/v1/agendas/{id}
```
---


### Endpoints Agenda Equipamentos ManyToMany

#### Post Equipamento em uma Agenda
```js
POST api/v1/agendas/{id}/equipamentos
```

#### Get Equipamentos de uma Agenda
```js
GET api/v1/agendas/{id}/equipamentos
```
```js
GET api/v1/equipamentos/{id}/agendas
```

