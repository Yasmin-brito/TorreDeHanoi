import javax.swing.JFrame;

public class MenuJogo extends JFrame {
    public MenuJogo(ModoDeJogo modo, int numeroDisco){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JogoPainel painel = new JogoPainel(modo, numeroDisco);
        add(painel);
        setSize(1500,800);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
