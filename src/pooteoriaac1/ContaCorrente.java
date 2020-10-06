package pooteoriaac1;

import java.security.InvalidParameterException;

public class ContaCorrente {
    private static int numeroContaCorrenteIndex = 1;
    private int numeroDaConta;
    private double saldo;
    private double saldoEspecial;
    private String nomeDoCliente;

    public void save() {
        this.numeroDaConta = numeroContaCorrenteIndex;
        numeroContaCorrenteIndex++;
    }

    public int getNumeroDaConta() {
        return numeroDaConta;
    }

    public String getNomeDoCliente() {
        return nomeDoCliente;
    }

    public void setNomeDoCliente(String nomeDoCliente) {
        this.nomeDoCliente = nomeDoCliente;
    }

    public double getSaldoEspecial() {
        return saldoEspecial;
    }

    public void setSaldoEspecial(double saldoEspecial) {
        if (saldoEspecial < 0)
            throw new InvalidParameterException("O saldo especial é necessario maior ou igual a zero.");
        this.saldoEspecial = saldoEspecial;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        if (saldo < -this.saldoEspecial)
            throw new InvalidParameterException("Sem dinheiro em conta para realizar essa operação.");
        this.saldo = saldo;
    }

    public void setNumeroDaConta(int numeroDaConta) {
        this.numeroDaConta = numeroDaConta;
    }
}
