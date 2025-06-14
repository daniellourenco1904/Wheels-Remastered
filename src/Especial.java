public class Especial extends Bicicleta{

    private int idade;
    private double valor;

    public Especial(int bicicletaId, String tipo, String modelo, double diaria, double deposito, Status status, int idade, double valor) {
        super(bicicletaId, tipo, modelo, diaria, deposito, status);
        this.idade = idade;
        this.valor = valor;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
