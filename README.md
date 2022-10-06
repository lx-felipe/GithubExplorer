Olá pessoa desenvolvedora! 👷🏽‍♂️

O **Github Explorer** é um aplicativo simples para listar os repositórios do GitHub com mais estrelas e com código Kotlin.

## Detalhes sobre o projeto:

- Desenvolvido inteiramente em Kotlin

- Uso de Paging 3 para paginação da lista de repositórios

- Desenvolvido a seguindo Clean Architecture e MVVM

- Utiliza Kotlin Flow para chamada assíncrona

- Testes unitários das camadas de data (PagingSource, Repository), domain (UseCase) e presentation (ViewModel)

## Tela inicial:

A tela inicial pode apresentar três estados distintos:

<img width="1053" alt="Captura de Tela 2022-10-06 às 17 23 12" src="https://user-images.githubusercontent.com/11378932/194411231-220c4955-4c68-46e6-8c99-7a5a6693df76.png">

##### Estado de carregamento

Ao entrar na tela o carregamento da lista é iniciado, exibindo um loading para o usuário.

##### Estado de sucesso

Se a chamada for realizada com sucesso, será exibida uma lista com os repositórios do Github.

##### Estado de erro

Se ocorrer uma falha no carregamento da lista, será exibida uma mensagem de erro com um botão para tentar novamente.

Se o device for rotacionado, o aplicativo mantém o estado da tela.
