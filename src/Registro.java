import java.util.ArrayList;
import java.util.List;

public class Registro {
    public List<Aluguel> alugueisAtivos;
    public Cliente cliente;

    public Registro(Cliente cliente) {
        this.alugueisAtivos = new ArrayList<>();
    }

    public void adicionarAluguel(Aluguel aluguel) {
        alugueisAtivos.add(aluguel);
    }

    public double calcularTotal() {
        return alugueisAtivos.stream()
                .mapToDouble(Aluguel::calcularCusto)
                .sum();
    }
}
