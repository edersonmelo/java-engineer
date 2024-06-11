# Tarefa Bônus 4 - Versionamento da API, Como você versionaria a API da sua aplicação? Que estratégia usar?
Minha ideia é que caso tenhamos alguma mudança que quebre a compatibilidade com a versão atual. Neste caso devemos trabalhar o versionamento e talvez uma branche com uma nova versão de API e assim o número da versão. 
É possível manter sempre a versão anterior e a atual(nova) até que os clientes atualizem para a nova versão da API.

## Accept versions
Utilizar o cabeçalho Accept da requisição HTTPS para determinar o número de versão secundária.