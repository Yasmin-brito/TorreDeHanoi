# TorreDeHanoi
🏰 Torre de Hanoi — Java Swing

Um jogo completo da Torre de Hanoi desenvolvido em Java, utilizando Swing, JFrame, JPanel e MouseListener.
O projeto inclui interação por clicar e soltar discos, validação de regras do jogo, contagem de movimentos, interface gráfica intuitiva e opção de jogar manualmente ou ver a resolução automática

📌 Objetivo do Jogo

O jogador deve mover todos os discos da Torre A para a Torre B, seguindo três regras:

- Mover apenas um disco por vez.
- Só é possível mover o disco do topo de qualquer torre.
- Um disco nunca pode ser colocado sobre um disco menor.

🎮 Como Jogar

- Clique em um disco no topo da torre.
- Clique em outra torre.
- O jogo automaticamente verifica se o movimento é válido.
- O contador de movimentos é atualizado em tempo real.
- Quando todos os discos forem movidos para a Torre B, aparece a mensagem de vitória.

🧠 Lógica do Arrastar e Soltar

O MouseListener e MouseMotionListener permitem:

- Detectar clique da torre correto
- Atualizar posição entre o clique de uma torre e outra
- Determinar torre de destino
- Validar regras
- Atualizar o estado do jogo

🖥️ Tecnologias Utilizadas

- Java 17+
- Swing
- JFrame
- JPanel
- MouseListener / MouseMotionListener
- OOP (Programação Orientada a Objetos)

src/
│
├── Main.java
├── MenuJogo.java
├── JogoPainel.java
├── ModoDeJogo.java
|
├── Torre.java
├── Disco.java
│
└── TorreHanoi.java

| Classe             |        Responsabilidade                              |
| ------------------ | ---------------------------------------------------- |
| **Main**           | Inicia o jogo.                                       |
| **MenuJogo**       | Configura o menu principal.                          |
| **JogoPainel**     | Renderiza torres, discos e controla o fluxo do jogo. |
| **Torre**          | Representa cada torre e armazena pilhas de discos.   |
| **Disco**          | Representa graficamente um disco.                    |
| **TorreHanoi**     | Controla a parte automática de resolução do jogo     |
| **ModoDeJogo**     | Possui apenas 2 variáveis (JOGADOR, AUTOMATICO)      |

📏 Regras Implementadas

- Não permite mover disco do meio ou de baixo
- Não permite soltar disco sobre um menor
- Não permite soltar em local inválido
- O usuário pode escolher jogar manualmente ou ver a resolução automática

📄 Licença

Este projeto é livre para estudo e evolução.
Sinta-se à vontade para melhorar e publicar versões próprias!
