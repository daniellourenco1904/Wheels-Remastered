import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Bicicleta> bicicletas = new ArrayList<>();
    private static List<Aluguel> alugueis = new ArrayList<>();



    public static void devolverBicicleta(String nomeCliente, LocalDate dataDevolucao) {
        Aluguel aluguel = alugueis.stream()
                .filter(a -> a.getCliente().getNome().equalsIgnoreCase(nomeCliente) && a.isAtivo())
                .findFirst()
                .orElse(null);

        if (aluguel == null) {
            System.out.println("Nenhum aluguel ativo encontrado para o cliente: " + nomeCliente);
            return;
        }

        long diasAtraso = ChronoUnit.DAYS.between(aluguel.getDataRetorno(), dataDevolucao);
        double multa = 0.0;

        if (diasAtraso > 0) {
            multa = diasAtraso * aluguel.getBicicleta().getDiaria();
            System.out.printf("Devolução atrasada em %d dia(s). Multa: R$ %.2f%n", diasAtraso, multa);
        } else {
            System.out.println("Devolução feita no prazo.");
        }

        aluguel.getBicicleta().setStatus(Bicicleta.Status.DISPONIVEL);
        aluguel.setAtivo(false);

        double valorADevolver = aluguel.getValorDepositado() - multa;

        if (valorADevolver >= 0) {
            System.out.printf("Valor devolvido ao cliente: R$ %.2f%n", valorADevolver);
        } else {
            System.out.printf("Depósito insuficiente. Cliente deve pagar R$ %.2f%n", -valorADevolver);
        }

        System.out.printf("Resumo: Valor do aluguel: R$ %.2f | Multa: R$ %.2f%n", aluguel.calcularDiaria(), multa);
    }

    public static void cadastrarBicicletas(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Tipo: ");
        String tipo = sc.nextLine();
        System.out.print("Modelo:");
        String modelo = sc.nextLine();
        System.out.print("Diária: ");
        double diaria = sc.nextDouble();
        System.out.print("Depósito: ");
        double deposito = sc.nextDouble();
        sc.nextLine(); // Consumir o \n

        int novaId = bicicletas.size() + 1;

        Bicicleta novaBicicleta = new Bicicleta(novaId, tipo, modelo, diaria, deposito, Bicicleta.Status.DISPONIVEL);
        bicicletas.add(novaBicicleta);

        ArquivoCSV.salvarBicicletas(bicicletas, "dados/bicicletas.csv");

        System.out.println("Bicicleta cadastrada com sucesso!");
    };

    public static void buscarBicicletas(String modelo, String tipo) {
        List<Bicicleta> encontradas = bicicletas.stream()
                .filter(b -> (modelo == null || b.getModelo().equalsIgnoreCase(modelo)))
                .filter(b -> (tipo == null || b.getTipo().equalsIgnoreCase(tipo)))
                .toList();

        if (encontradas.isEmpty()) {
            System.out.println("Nenhuma bicicleta encontrada com os critérios fornecidos.");
        } else {
            System.out.println("Bicicletas encontradas:");
            for (Bicicleta b : encontradas) {
                System.out.printf("ID: %d | Modelo: %s | Tipo: %s | Status: %s%n",
                        b.getBicicletaId(), b.getModelo(), b.getTipo(), b.getStatus());
            }
        }
    }

    public static void novoAluguel(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Nome do cliente: ");
        String nomeCliente = sc.nextLine();
        Cliente cliente = clientes.stream()
                .filter(c -> c.getNome().equalsIgnoreCase(nomeCliente))
                .findFirst()
                .orElse(null);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Modelo da bicicleta: ");
        String modelo = sc.nextLine();
        Bicicleta bicicleta = bicicletas.stream()
                .filter (b -> b.getModelo().equalsIgnoreCase(modelo) && b.getStatus() == Bicicleta.Status.DISPONIVEL)
                .findFirst()
                .orElse(null);

        if (bicicleta == null) {
            System.out.println("Bicicleta não encontrada ou indisponível.");
            return;
        }

        System.out.print("Quantidade de dias do aluguel: ");
        int qtdDias = sc.nextInt();
        System.out.print("Valor depositado: ");
        double valorDepositado = sc.nextDouble();
        sc.nextLine();
        int aluguelId = alugueis.size() + 1;
        LocalDate dataAluguel = LocalDate.now();

        Aluguel aluguel = new Aluguel(aluguelId, valorDepositado, qtdDias, bicicleta, cliente, dataAluguel);
        alugueis.add(aluguel);
        cliente.setPreviousBike(bicicleta.getModelo());
        bicicleta.setStatus(Bicicleta.Status.ALUGADA);
    }

    public static void listarAlugueis(){
        if (alugueis.isEmpty()){
            System.out.print("Nenhum aluguel registrado.");
        }

        for (Aluguel a : alugueis){
            System.out.printf("ID: %d | Cliente: %s | Bicicleta: %s | Dias: %d | Ativo: %s | Início: %s | Retorno: %s%n",
                    a.getAluguelId(),
                    a.getCliente().getNome(),
                    a.getBicicleta().getModelo(),
                    a.getQtdDias(),
                    a.isAtivo() ? "Sim" : "Não",
                    a.getDataAluguel(),
                    a.getDataRetorno());
        }
    }

    public static void cadastrarCliente() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Endereço: ");
        String endereco = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        System.out.print("ID do cliente: ");
        int clienteId = clientes.size() + 1;
        sc.nextLine();

        String previousBike = "";
        Cliente novoCliente = new Cliente(nome, previousBike, telefone, clienteId, endereco);

        clientes.add(novoCliente);
        ArquivoCSV.salvarClientes(clientes,"dados/clientes.csv");
        System.out.println("Cliente cadastrado com sucesso!");
    }



    public static void main(String[] args) {
        clientes = ArquivoCSV.carregarClientes("clientes.csv");
        cadastrarCliente();

        // Para testes, você pode simular aluguéis e devoluções aqui
        // devolverBicicleta("João", LocalDate.now());
    }
}