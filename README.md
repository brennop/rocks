# rocks

## Descrição de Problema

Neste projeto, desenvolvemos um clone do jogo Asteroids usando a linguagem de
programação Java e os conceitos de programação orientada a objetos.

Asteroids é um jogo do tipo arcade, lançado em Novembro de 1979 pela Atari, Inc.
O jogador controla uma nave espacial num campo de asteróides, e deve atirar e
destruir os asteróides enquanto evita colidir com os mesmos.

![Screenshot do Jogo](https://upload.wikimedia.org/wikipedia/en/thumb/1/13/Asteroi1.png/220px-Asteroi1.png)

Nosso objetivo é implementar um jogo similar, com uma nave espacial se movendo
livremente pelo tela do jogo, e asteróides errantes que ameaçam colidir com o
jogador. No entanto, nosso objetivo é bem mais simples, pensando apenas em
implementar o fluxo básico do jogo, para desenvolver a prática dos conceitos de
orientação à objeto.

## Regras de Negócio

1. O _jogador_ pode se mover livremente pela tela do jogo, usando as teclas `a`
   e `d` para rotacionar a nava, e a tecla `w` para adicionar propulsão.
2. Ao atingir a borda da tela do jogo, o _jogador_ deve ser movido para a borda
   oposta.
3. Durante o jogo, _asteróides_ aparecem em posições aleatórias.
4. Um _asteróide_ deverá ser destruído ao sair da região da tela.
5. O _jogador_ é capaz de atirar um número ilimitado de _balas_ com uma certa
   cadência.
6. Uma _bala_ tem uma direção inicial, e se move nessa direção com velocidade
   constante.
7. Uma _bala_ deverá ser destruída ao sair da região da tela.
8. Um _asteróide_ é destruído ao colidir com uma _bala_.
9. Uma _bala_ é destruída ao colidir com um _asteróide_.
10. A _pontuação_ é incrementada quando um _asteróide_ colide com uma _bala_.
11. O **fim do jogo** ocorre quando um _asteróide_ colide com o _jogador_.
12. O usuário pode inserir o seu nome no **fim do jogo** para salvar sua
    pontuação.

## Diagram de Classes

### Game

A classe Game é responsável por selecionar a _cena_ atual e criar o _loop de
jogo_. O loop de jogo é um loop infinito, que guarda o tempo que levou para
completar. Isso é importante para que nossa simulação de física precisa levar em
conta o tempo nos cálculos.

Cada iteração do loop de jogo vamos chamar de _frame_, e o tempo levado por um
_frame_ vamos chamar de _delta time_ ou `dt`. A cada frame a classe Game chama o
método `update` da cena atual, informando o delta time para o método. A
implementação do loop de jogo é baseada no artigo [Fix Your Timestep!](https://www.gafferongames.com/post/fix_your_timestep/).

### Scene

A classe Scene, ou cena, representa uma composição de _entidades_. Essa classe é
responsável por instanciar as entidades, atualizá-las, desenhá-las na tela e
destruí-las.

Cada cena do jogo herda dessa classe, e implementa sua própria lógica para
interagir com o ciclo de vida.

### Entity

```java
    protected final int size;
    protected float speed;
    protected Transform transform;
    protected final Scene scene;
    private BufferedImage texture;
```

A classe abstrata Entity, ou _entidade_, é uma abstração de todos os elementos
que serão desenhados na tela. Uma entidade possui tamanho, velocidade, um
[Transform](#transform), a cena que o criou e uma textura.

A cada frame, a textura da entidade é desenhada na tela, na posição e rotação
definidas pelo seu Transform.

A entidade implementa os métodos `destroy`, para sinalizar a sua cena que deve
ser destruída, e os métodos abstratos `update`, chamada a cada frame com o delta
time, e `onCollision`, chamado quando ocorre uma colisão envolvendo aquela
entidade.

### Transform

A classe Transform representa informações do sistema de coordenadas.

### Asteroid

Asteroid herda da classe Entity. No seu método `update`, se move em uma direção
com velocidade constante e verifica se sua posição saiu da região da tela.
