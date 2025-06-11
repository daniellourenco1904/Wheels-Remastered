import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArquivoCSV {

    public static void salvarBicicletas(List<Bicicleta> bicicletas, String caminho) {
        try (PrintWriter writer = new PrintWriter(caminho)) {
            for (Bicicleta b : bicicletas) {
                writer.printf("%d,%s,%s,%s,%s,%.2f,%.2f,%s%n",
                        b.getBicicletaId(),
                        b.getTipo(),
                        b.getTamanho(),
                        b.getMarca(),
                        b.getModelo(),
                        b.getDiaria(),
                        b.getDeposito(),
                        b.getStatus().name());
            }
            System.out.println("Bicicletas salvas com sucesso em: " + caminho);
        } catch (IOException e) {
            System.out.println("Erro ao salvar bicicletas: " + e.getMessage());
        }
    }

    public static List<Bicicleta> carregarBicicletas(String caminho) {
        List<Bicicleta> bicicletas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(",");
                if (campos.length == 8) {
                    Bicicleta b = new Bicicleta(
                            Integer.parseInt(campos[0]),
                            campos[1],
                            campos[2],
                            campos[3],
                            campos[4],
                            Double.parseDouble(campos[5]),
                            Double.parseDouble(campos[6]),
                            Bicicleta.Status.valueOf(campos[7])
                    );
                    bicicletas.add(b);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar bicicletas: " + e.getMessage());
        }
        return bicicletas;
    }

    public static void salvarAlugueis(List<Aluguel> alugueis, String caminho) {
        try (PrintWriter writer = new PrintWriter(caminho)) {
            for (Aluguel a : alugueis) {
                writer.printf("%d,%d,%d,%d,%.2f,%s,%s,%b%n",
                        a.getAluguelId(),
                        a.getCliente().getClienteId(),
                        a.getBicicleta().getBicicletaId(),
                        a.getQtdDias(),
                        a.getValorDepositado(),
                        a.getDataAluguel(),
                        a.getDataRetorno(),
                        a.isAtivo()
                );
            }
            System.out.println("Aluguéis salvos com sucesso em: " + caminho);
        } catch (IOException e) {
            System.out.println("Erro ao salvar aluguéis: " + e.getMessage());
        }
    }

    public static List<Aluguel> carregarAlugueis(String caminho, List<Cliente> clientes, List<Bicicleta> bicicletas) {
        List<Aluguel> alugueis = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(",");
                if (campos.length == 8) {
                    int aluguelId = Integer.parseInt(campos[0]);
                    int clienteId = Integer.parseInt(campos[1]);
                    int bicicletaId = Integer.parseInt(campos[2]);
                    int qtdDias = Integer.parseInt(campos[3]);
                    double valorDepositado = Double.parseDouble(campos[4]);
                    LocalDate dataAluguel = LocalDate.parse(campos[5]);
                    boolean ativo = Boolean.parseBoolean(campos[7]);

                    Cliente cliente = clientes.stream()
                            .filter(c -> c.getClienteId() == clienteId)
                            .findFirst()
                            .orElse(null);

                    Bicicleta bicicleta = bicicletas.stream()
                            .filter(b -> b.getBicicletaId() == bicicletaId)
                            .findFirst()
                            .orElse(null);

                    if (cliente != null && bicicleta != null) {
                        Aluguel aluguel = new Aluguel(aluguelId, valorDepositado, qtdDias, bicicleta, cliente, dataAluguel);
                        aluguel.setAtivo(ativo);
                        alugueis.add(aluguel);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar aluguéis: " + e.getMessage());
        }
        return alugueis;
    }

    public static void salvarClientes(List<Cliente> clientes, String caminho) {
        try (PrintWriter writer = new PrintWriter(caminho)) {
            for (Cliente c : clientes) {
                writer.printf("%d,%s,%s,%s,%s%n",
                        c.getClienteId(),
                        c.getNome(),
                        c.getTelefone(),
                        c.getEndereco(),
                        c.getPreviousBike() != null ? c.getPreviousBike() : "");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    public static List<Cliente> carregarClientes(String caminho) {
        List<Cliente> clientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(",");
                if (campos.length >= 5) {
                    Cliente c = new Cliente(
                            campos[1],
                            campos[4],
                            campos[2],
                            Integer.parseInt(campos[0]),
                            campos[3]
                    );
                    clientes.add(c);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar clientes: " + e.getMessage());
        }
        return clientes;
    }


}
