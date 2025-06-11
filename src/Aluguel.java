import java.time.LocalDate;

public class Aluguel {

    private int aluguelId;
    private Cliente cliente;
    private Bicicleta bicicleta;
    private int qtdDias;
    private double valorDepositado;
    private LocalDate dataAluguel;
    private LocalDate dataRetorno;
    private boolean ativo;

    public Aluguel(int aluguelId, double valorDepositado, int qtdDias, Bicicleta bicicleta, Cliente cliente, LocalDate dataAluguel) {
        this.aluguelId = aluguelId;
        this.valorDepositado = valorDepositado;
        this.qtdDias = qtdDias;
        this.bicicleta = bicicleta;
        this.cliente = cliente;
        this.dataAluguel = dataAluguel;
        this.dataRetorno = calcularDataRetorno(dataAluguel, qtdDias);
        this.ativo = true;
    }

    @Override
    public String toString() {
        return "Aluguel{" +
                "aluguelId=" + aluguelId +
                ", cliente=" + cliente +
                ", bicicleta=" + bicicleta +
                ", qtdDias=" + qtdDias +
                ", valorDepositado=" + valorDepositado +
                ", dataAluguel=" + dataAluguel +
                ", dataRetorno=" + dataRetorno +
                ", ativo=" + ativo +
                '}';
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDate getDataRetorno() {
        return dataRetorno;
    }

    public LocalDate getDataAluguel() {
        return dataAluguel;
    }

    private LocalDate calcularDataRetorno(LocalDate dataInicial, int dias) {
        return dataInicial.plusDays(dias);
    }

    public double calcularDiaria(){
        return bicicleta.getDiaria() * qtdDias;
    }

    public double calcularCusto(){
        return (bicicleta.getDiaria() * qtdDias) + bicicleta.getDeposito();
    }

    public double getValorDepositado() {
        return valorDepositado;
    }

    public int getQtdDias() {
        return qtdDias;
    }

    public Bicicleta getBicicleta() {
        return bicicleta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getAluguelId() {
        return aluguelId;
    }
}
