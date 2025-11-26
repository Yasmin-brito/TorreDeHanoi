import java.util.ArrayList;
import java.util.List;

public class TorreHanoi {
    private List<int[]> movimentos;
    private int numeroDisco;
    int origem, destino, auxiliar;

    public TorreHanoi(int numeroDisco){
        this.numeroDisco = numeroDisco;
        this.movimentos = new ArrayList<>();
    }

    public void gerar(int origem, int auxiliar, int destino){
       this.origem = origem;
       this.auxiliar = auxiliar;
       this.destino = destino;
       movimentos.clear();
       hanoi(numeroDisco, origem, destino, auxiliar);
    }

    public void hanoi(int n, int from, int to, int aux){
         if(n > 0){
            hanoi(n - 1, from, aux, to);
            movimentos.add(new int[]{from, to});
            hanoi(n - 1, aux, to, from);
        }
    }

    public List<int[]> getMovimentos(){
        return movimentos;
    }
}
