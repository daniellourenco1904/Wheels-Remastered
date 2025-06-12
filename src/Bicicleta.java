public class Bicicleta {

    private int bicicletaId;
    private String tipo;
    private String modelo;
    private double diaria;
    private double deposito;
    private Status status;

    public enum Status {
        DISPONIVEL,
        ALUGADA,
        MANUTENCAO
    }

    public Bicicleta(int bicicletaId, String tipo, String modelo, double diaria, double deposito, Status status) {
        this.bicicletaId = bicicletaId;
        this.tipo = tipo;
        this.modelo = modelo;
        this.diaria = diaria;
        this.deposito = deposito;
        this.status = status;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getBicicletaId() {
        return bicicletaId;
    }

    public void setBicicletaId(int bicicletaId) {
        this.bicicletaId = bicicletaId;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getDiaria() {
        return diaria;
    }

    public void setDiaria(double diaria) {
        this.diaria = diaria;
    }

    public double getDeposito() {
        return deposito;
    }

    public void setDeposito(double deposito) {
        this.deposito = deposito;
    }
}
