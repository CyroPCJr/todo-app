# README.md

## Módulos

- **Domain**: Responsável pela lógica de negócios e regras da aplicação.

## Arquitetura

A arquitetura do projeto é baseada no padrão de camadas, onde temos:

- Camada de apresentação
- Camada de domínio
- Camada de dados

## Estrutura de Pastas

```
├── src
│   ├── Domain
│   │   ├── models
│   │   ├── services
│   │   └── repositories
│   ├── Presentation
│   └── Data
```

Este módulo é fundamental para garantir que as regras de negócios sejam mantidas separadas da lógica de apresentação e de dados.