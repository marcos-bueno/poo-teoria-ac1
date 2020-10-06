package pooteoriaac1;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;

public class TesteContaCorrente {
    private static ArrayList<ContaCorrente> contas = new ArrayList<>();
	
    public static void main(String[] args) {
        int op;

        Scanner s = new Scanner(System.in);

        do {
            abrirMenu();

            System.out.print("Informe a opção: ");
            op = Integer.parseInt(s.nextLine());

            switch (op) {
                case 1:
                    cadastrarContaCorrente(s);
                    break;

                case 2:
                    depositarContaCorrente(s);
                    break;

                case 3:
                    saqueContaCorrente(s);
                    break;

                case 4:
                    listarSaldoContaCorrente(s);
                    break;

                case 5:
                    listarSomatoriaDeSaldosDeContasCorrentPositivas(s);
					break;
				
                default:
                    break;
            }
        } while (op != 6);
    }

    private static void abrirMenu() {
        System.out.println("Conta corrente: ");
		
        System.out.println("1. Cadastro conta corrente");
		
        System.out.println("2. Depositar");
		
        System.out.println("3. Saque");
		
        System.out.println("4. Ver saldo");
		
        System.out.println("5. Somatorio clientes que não usaram o especial");

        System.out.println("6. Sair");
    }

    private static void cadastrarContaCorrente(Scanner s) {
        try {
            ContaCorrente conta = new ContaCorrente();

            System.out.print("Digite o nome do cliente: ");
			
            conta.setNomeDoCliente(s.nextLine());

            System.out.print("Digite o saldo especial do cliente: ");
			
            conta.setSaldoEspecial(Double.parseDouble(s.nextLine()));

            conta.setSaldo(0);
			
            conta.save();

            contas.add(conta);

            System.out.println("A conta de numero: " + conta.getNumeroDaConta() + " foi criada com sucesso.");
            System.out.println("=============================================");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getLocalizedMessage());
            System.out.println("=============================================");
        }
    }

    private static void depositarContaCorrente(Scanner s) {
        try {
            System.out.print("Insira o numero da conta do cliente: ");
			
            int numeroConta = Integer.parseInt(s.nextLine());

            for (ContaCorrente conta : contas) {
                if (conta.getNumeroDaConta() != numeroConta)
                    continue;

                System.out.print("Insira o valor que voce deseja depositar: ");
				
                double deposito = Double.parseDouble(s.nextLine());

                if (deposito < 0)
                    throw new InvalidParameterException(
                            "Voce nao pode realizar o deposito informando um valor negativo.");

                conta.setSaldo(conta.getSaldo() + deposito);

                System.out.println("O deposito foi realizado com sucesso.");
				
                System.out.println("Novo saldo: " + conta.getSaldo());
				
                System.out.println("=============================================");
				
                return;
            }

            System.out.println("A conta de numero: " + numeroConta + " nao foi encontrada.");
			
            System.out.println("=============================================");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getLocalizedMessage());
            System.out.println("=============================================");
        }
    }

    private static void saqueContaCorrente(Scanner s) {
        try {
            System.out.print("Insira o numero da conta do cliente: ");
			
            int numeroConta = Integer.parseInt(s.nextLine());

            for (ContaCorrente conta : contas) {
                if (conta.getNumeroDaConta() != numeroConta)
                    continue;

                System.out.print("Insira o valor que voce deseja sacar: ");
				
                double saque = Double.parseDouble(s.nextLine());

                if (saque < 0)
                    throw new InvalidParameterException(
                            "Voce nao pode realizar o saque informando um valor negativo.");

                conta.setSaldo(conta.getSaldo() - saque);

                System.out.println("O saque foi realizado com sucesso.");
                System.out.println("Novo saldo: " + conta.getSaldo());
                System.out.println("=============================================");
                return;
            }

            System.out.println("A conta de numero: " + numeroConta + " nao foi encontrada!");
            System.out.println("=============================================");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getLocalizedMessage());
            System.out.println("=============================================");
        }
    }

    private static void listarSaldoContaCorrente(Scanner s) {
        try {
            System.out.print("Insira o numero da conta do cliente: ");
			
            int numeroConta = Integer.parseInt(s.nextLine());

            for (ContaCorrente conta : contas) {
                if (conta.getNumeroDaConta() != numeroConta)
                    continue;

                System.out.println("O saldo dessa conta eh: " + conta.getSaldo());
                System.out.println("O saldo especial dessa conta eh: " + conta.getSaldoEspecial());
                System.out.println("=============================================");
                return;
            }

            System.out.println("A conta de numero: " + numeroConta + " nao foi encontrada.");
            System.out.println("=============================================");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getLocalizedMessage());
            System.out.println("=============================================");
        }
    }

    private static void listarSomatoriaDeSaldosDeContasCorrentPositivas(Scanner s) {
        double sum = 0;

        for (ContaCorrente conta : contas) {
            if (conta.getSaldo() < 0)
                continue;

            sum += conta.getSaldo();
        }

        System.out.println("A somatoria dos saldos que nao usaram o saldo especial: " + sum);
        System.out.println("=============================================");
    }
}
