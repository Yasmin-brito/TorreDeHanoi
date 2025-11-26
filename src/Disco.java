import java.awt.Graphics;
import java.awt.Color;

public class Disco {
    private int largura;
    private int altura;
    private Color cor;
    // private int x, y;

    public Disco(int largura, int altura, Color cor){
        this.altura = altura;
        this.largura = largura;
        this.cor = cor;
    }

    public void desenhar(Graphics g, int centroX, int TopoY){
        int x = (centroX + 5) - largura / 2;
        g.setColor(this.cor);
        g.fillRoundRect(x, TopoY, largura, altura, 25, 25);
    }
    
    public boolean comparaTamanho(Disco d){
        if(d == null) return true;
        return this.largura <= d.largura;
    }

    public int getLargura(){
        return this.largura;
    }

    public int getAltura(){
        return this.altura;
    }
}
