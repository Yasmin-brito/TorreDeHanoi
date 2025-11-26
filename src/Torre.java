import java.util.Stack;
import java.awt.Color;
import java.awt.Graphics;

public class Torre {
    private Stack<Disco> discos;
    private int centroX;
    private int baseY;
    private int alturaPoste;
    private int larguraBase;

    private int hitBoxX, hitBoxY;
    private int hitBoxLargura, hitBoxAltura;

    public Torre(int centroX, int baseY, int alturaPoste, int larguraBase){
        this.centroX = centroX;
        this.baseY = baseY;
        this.alturaPoste = alturaPoste;
        this.larguraBase = larguraBase;
        this.discos = new Stack<>();

        this.hitBoxLargura = larguraBase + 100;
        this.hitBoxAltura = alturaPoste + 60;
        this.hitBoxX = centroX - (hitBoxLargura/2) + 20;
        this.hitBoxY = baseY - alturaPoste - 50;
    }

    public void desenhar(Graphics g, int alturaDisco, int espaco){
        int posteX = centroX;
        int posteY = baseY - alturaPoste;

        //poste
        g.setColor(Color.WHITE);
        g.fillRect(posteX, posteY, 10, alturaPoste);

        //discos
        int discoSize = discos.size();
        int yTopo = baseY -alturaDisco;

        for(int i = discoSize -1 ; i >= 0 ; i--){
            Disco d = discos.get(i);
            d.desenhar(g, centroX, yTopo - i * (alturaDisco + espaco));
        }
    }

    public void desenharBase(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(centroX - larguraBase/2, baseY, larguraBase + 825, 15);
    }

    public boolean ehPossivel(Disco d){
        Disco topo = discos.isEmpty() ? null : discos.peek();
        if (topo == null || d.comparaTamanho(topo)) {
            discos.push(d);
            return true;
        }
        return false;
    }

    public boolean pontoBox(int x, int y){
        return x >= hitBoxX && x <= hitBoxX + hitBoxLargura &&
                y >= hitBoxY && y <= hitBoxY + hitBoxAltura;
    }

    public void push(Disco d){
        discos.push(d);
    }

    public Disco removerTopo(){
        if (discos.isEmpty()) return null;
        return discos.pop();
    }

    public Disco getTopo(){
        if (discos.isEmpty()) return null;
        return discos.peek();
    }

    public Stack<Disco> getDiscos(){
        return this.discos;
    }

    public void clearDiscos(){
        discos.clear();
    }

    public int tamanho(){
        return discos.size();
    }

    public int getCentroX(){
        return centroX;
    }

}
