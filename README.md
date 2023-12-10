# SongListRoom
Projeto final da disciplina de Persistência de Dados (PDD) do curso de Pós-Graduação Lato Sensu em Desenvolvimento de Sistemas para Dispositivos Móveis do IFSP São Carlos.

## Descrição
Foi implementado um CRUD de lista de músicas, utilizando a biblioteca Room para persistência dos dados

<img src="https://github.com/gabrielmoreira-dev/SongListRoom/assets/52149023/4f34bf7f-861d-4a40-a913-fdc3288f4ca8" height="500"/>
<img src="https://github.com/gabrielmoreira-dev/SongListRoom/assets/52149023/0d6244fa-a8f5-419c-afe1-444c7d097bfa" height="500"/>

## User Stories
#1 - Incluir músicas na lista

#2 - Exibir lista de músicas

#3 - Exibir detalhes de música

#4 - Editar informações de música

#5 - Excluir música

#6 - Filtrar músicas pelo nome

## Arquitetura
Foi adotada uma arquitetura MVVM com Repository, empregando LiveData e a biblioteca Room para persistência de dados. 

<img src="https://github.com/gabrielmoreira-dev/SongListRoom/assets/52149023/1ecc28c0-b46c-4688-817d-57b5a7745623" height="300" />
<img src="https://github.com/gabrielmoreira-dev/SongListRoom/assets/52149023/d8489737-3f5c-4b6a-af0d-37d9ba0f0ab3" height="300" />

## Dados para teste
Para testar as funcionalidades do aplicativo podem ser usados os seguintes dados:

| Nome          | Artista       | Álbum            | Imagem                                                                                             |
| ------------- | ------------- | ---------------- | -------------------------------------------------------------------------------------------------- |
| Alive         | Pearl Jam     | Ten              | https://upload.wikimedia.org/wikipedia/pt/thumb/d/da/Pearl_Jam_-_Ten.jpg/220px-Pearl_Jam_-_Ten.jpg |
| Enter Sandman | Metallica     | Metallica        | https://m.media-amazon.com/images/I/61Na6eN05jS._UF350,350_QL80_.jpg                               |
| High and Dry  | Radiohead     | The Bends        | https://upload.wikimedia.org/wikipedia/pt/b/bf/TheBends.jpg                                        |
| Something     | The Beatles   | Abbey Road       | https://upload.wikimedia.org/wikipedia/pt/3/3d/Abbey_Road.jpg                                      |
| Wasting Love  | Iron Maiden   | Fear of the Dark | https://upload.wikimedia.org/wikipedia/pt/6/64/Fear_of_the_dark_-_iron_maiden.jpg                  |
