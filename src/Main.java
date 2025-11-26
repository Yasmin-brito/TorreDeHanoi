import javax.swing.JOptionPane;

public class Main{
    public static void main(String[] args) {
        Object[] opcoes = {"Modo Jogador", "Modo Automático"};
        int op = JOptionPane.showOptionDialog(
            null,
            "Escolha o modo de execução:",
            "Modo de execução",
            0,
            JOptionPane.PLAIN_MESSAGE,
            null,
            opcoes,
            opcoes[0]
        );

        ModoDeJogo modo = (op == 0) ? ModoDeJogo.JOGADOR : ModoDeJogo.AUTOMATICO;


        new MenuJogo(modo, 4);
    }
}