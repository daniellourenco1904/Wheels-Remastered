public class Bicicleta {

    private int bicicletaId;
    private String tipo;
    private String tamanho;
    private String marca;
    private String modelo;
    private double diaria;
    private double deposito;
    private Status status;

    public enum Status {
        DISPONIVEL,
        ALUGADA,
        MANUTENCAO
    }

    public Bicicleta(int bicicletaId, String tipo, String tamanho, String marca, String modelo, double diaria, double deposito, Status status) {
        this.bicicletaId = bicicletaId;
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.marca = marca;
        this.modelo = modelo;
        this.diaria = diaria;
        this.deposito = deposito;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Bicicleta{" +
                "bicicletaId=" + bicicletaId +
                ", tipo='" + tipo + '\'' +
                ", tamanho='" + tamanho + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", diaria=" + diaria +
                ", deposito=" + deposito +
                ", status=" + status +
                '}';
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
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
