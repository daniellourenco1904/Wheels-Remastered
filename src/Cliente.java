import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String nome;
    private String endereco;
    private String telefone;
    private int clienteId;
    private String previousBike;

    public Cliente(String nome, String previousBike, String telefone, int clienteId, String endereco) {
        this.nome = nome;
        this.previousBike = previousBike;
        this.telefone = telefone;
        this.clienteId = clienteId;
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                ", clienteId=" + clienteId +
                ", previousBike='" + previousBike + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreviousBike() {
        return previousBike;
    }

    public void setPreviousBike(String previousBike) {
        this.previousBike = previousBike;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
