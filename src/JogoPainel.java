import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Font;

public class JogoPainel extends JPanel implements MouseListener, MouseMotionListener{
    private Torre[] torres = new Torre[3];
    private List<Color> cores = new ArrayList<>();

    private Disco discoSelecionado = null;
    private int torreSelecionadaOrigem = -1;

    private int mouseX = 0, mouseY = 0;

    private ModoDeJogo modo;
    private TorreHanoi auto;
    private Timer timer;

    private List<int[]> movimentosAuto;
    private int indiceMovimento;
    private int movimentos = 0;

    private final int alturaDisco = 30;
    private final int espaco = 0;

    private final int numeroDisco;

    public JogoPainel(ModoDeJogo modo, int numeroDisco){
        this.modo = modo;
        this.numeroDisco = Math.max(4, numeroDisco);

        setFocusable(true);
        setBackground(Color.BLACK);
        addMouseListener(this);
        addMouseMotionListener(this);

        carregarCores();
        iniciarTorres();

        if (modo == ModoDeJogo.AUTOMATICO) {
            iniciarSolucaoAutomatica();
        }
    }

    private void carregarCores(){
        cores.add(new Color(165,42,42));
        cores.add(new Color(147,219,112));
        cores.add(new Color(112,219,219));
        cores.add(new Color(219,219,112));
    }

    private void iniciarTorres(){

        int baseY = 500;
        int alturaPoste = 180;
        int larguraBase = 180;

        torres[0] = new Torre(375, baseY, alturaPoste, larguraBase);
        torres[1] = new Torre(750, baseY, alturaPoste, larguraBase);
        torres[2] = new Torre(1125, baseY, alturaPoste, larguraBase);

        int maxLargura = 160;
        for (int i = 0; i < numeroDisco; i++) {
            int largura = maxLargura - (maxLargura/numeroDisco) * i;
            Disco d = new Disco(largura, alturaDisco, cores.get(i));
            torres[0].push(d);
        }
    }

    private void iniciarSolucaoAutomatica(){

        auto = new TorreHanoi(numeroDisco);
        auto.gerar(0, 2, 1);

        movimentosAuto = auto.getMovimentos();
        indiceMovimento = 0;

        timer = new Timer(1000, e -> aplicarMovimentoAuto());
        timer.start();

    }

    private void aplicarMovimentoAuto(){
        if (indiceMovimento >= movimentosAuto.size()) {
            timer.stop();
            if (verificaVitoria()) {
                vitoria();
            }
            return;
        }

        int[] mov = movimentosAuto.get(indiceMovimento++);
        Disco d = torres[mov[0]].removerTopo();

        if (d != null) {
            torres[mov[1]].push(d);
        }

        repaint();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        desenharFrase(g);
        desenharTorres(g);
        desenharDiscoSelecionado(g);

    }

    private void desenharTorres(Graphics g){
        for( Torre t : torres){
            t.desenhar(g, alturaDisco, espaco);
        }
        torres[0].desenharBase(g);
    }

    private void desenharDiscoSelecionado(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        if (discoSelecionado != null) {
            int drawX = torres[torreSelecionadaOrigem].getCentroX() - discoSelecionado.getLargura() /2;
            int drawY = 150;

            discoSelecionado.desenhar(g, torres[torreSelecionadaOrigem].getCentroX(), drawY);

            g.setColor(new Color(230,232,250));
            g2d.setStroke(new BasicStroke(2));
            g.drawRoundRect(drawX+5, drawY, discoSelecionado.getLargura(), discoSelecionado.getAltura(), 25, 25);
        }
    }

     private void desenharFrase(Graphics g){
        int calculo = (int) Math.pow(2, numeroDisco) -1;

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));

        g.drawString("Mínimo de movimentos: " + calculo, 620, 100);
        g.drawString("Movimentos efetuados: " + movimentos, 620, 700);

    }

    private void limparSelecao(){
        discoSelecionado = null;
        torreSelecionadaOrigem = -1;
    }

    private void reposicionarDiscoorigem(){
        torres[torreSelecionadaOrigem].push(discoSelecionado);
        limparSelecao();
    }

    private int detectarTorre(int xMouse, int yMouse){
        for(int i = 0; i < torres.length; i++){
            if (torres[i].pontoBox(xMouse, yMouse)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if (modo == ModoDeJogo.AUTOMATICO) return;

        mouseX = e.getX();
        mouseY = e.getY();

        int torreX = detectarTorre(mouseX, mouseY);

        if (torreX == -1) {
            return;
        }

        if(discoSelecionado == null){
            selecionarDisco(torreX);
        } else{
            moverOuCancelarSelecao(torreX);
        }

        repaint();

    }

    private void selecionarDisco(int torreX){
        Disco topo = torres[torreX].removerTopo();
        if (topo != null) {
                discoSelecionado = topo;
                torreSelecionadaOrigem = torreX;
        }
        repaint();
    }

    private void moverOuCancelarSelecao(int torreX){
        if(torreX == torreSelecionadaOrigem){
            reposicionarDiscoorigem();
            repaint();
            return;
        }
        validarEMoverDisco(torreX);
        repaint();
    }

    private void validarEMoverDisco(int torreX){
        boolean moveu = torres[torreX].ehPossivel(discoSelecionado);
        repaint();

        if (moveu) {
            movimentos++;
        } else{
            reposicionarDiscoorigem();
            return;
        }

        limparSelecao();

        if (verificaVitoria()) {
            vitoria();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        if (discoSelecionado != null) {
            repaint();
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {}

    private boolean verificaVitoria(){
        return torres[1].tamanho() == numeroDisco;
    }

    private void vitoria(){
        if (modo == ModoDeJogo.AUTOMATICO && timer != null) {
            timer.stop();
        }

        String[] opcoes = {"Reiniciar", "Fechar"};

        int escolha = JOptionPane.showOptionDialog( this, "Você conseguiu!\nTotal de movimentos: " + movimentos + "\n\nO que deseja fazer?", "Parabéns!",
        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

        if (escolha == JOptionPane.YES_OPTION) {
            resetar();

        } else {
            System.exit(0);
        }
    }

    private void resetar(){
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        indiceMovimento = 0;
        movimentos = 0;

        for(Torre t : torres){
            t.clearDiscos();
        }

        limparSelecao();
        iniciarTorres();

        if (modo == ModoDeJogo.AUTOMATICO) {
            iniciarSolucaoAutomatica();
        }

        repaint();

    }

}
