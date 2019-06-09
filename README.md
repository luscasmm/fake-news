# Sistema de Detecção de *Fake News* através da Análise de Similaridade entre Conjuntos de Palavras

Este projeto implementa um sistema de detecção de *fake news* executado através da análise de similaridade entre conjuntos de palavras. Implementa, também, os módulos necessários ao funcionamento do programa: leitor de arquivos CSV, *web scrapper*, processamento de strings e algoritmo de similaridade por cosseno.

## Execução

Primeiramente, é necessário ter o arquivo csv com *fake news* localizado na pasta **data** do pacote de projeto java. Assim, executa-se o programa informando o dataset da seguinte forma:
> $ java -jar fakenews.jar fakenews/data/boatos.csv

É possível informar o dataset durante a execução:
> Insira o caminho do dataset de noticias: <arquivo_csv>

Após definir o arquivo csv, acessa-se o menu principal: 
>1. Carregar Dataset
>2. Verficar notícia
>3. Sair
>
>Opção:

Como primeira ação, deve-se carregar os dados do dataset escolhendo a opção **1**. O usuário é questionado sobre mudar o limiar de desconsideração de palavras:
>Deseja mudar limiar de desconsideração de palavras (s/n)?

Optando por mudar o limiar, requisita-se o novo valor e então a execução segue efetuando o carregamento.

De volta ao menu principal, a opção **2** permite a busca por uma notícia na internet por meio do link da da página:
>Informe o link da notícia:

É apresentado os resultados do *web scraping* e se a notícia já foi cadastrada no sistema ou não. Caso não tenha sido armazenada, começa-se o processo de análise de similaridade entre vetores de *string* por cosseno. O usuário é questionado quanto ao limiar de similariadade (*threshold*).

O processo de análise acaba e os resultados do procedimento são exibidos. Caso seja uma fakenews não cadastrada o sistema a armazena.

Por fim, no menu principal, pode-se escolher a opção **3**: sair do sistema e finalizar a execução.

## Autoria

Desenvolvido por Lucas Miguel Martiniano.
Github: [@luscasmm](https://github.com/luscasmm)
