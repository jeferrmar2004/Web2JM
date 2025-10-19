package hn.uth.atmgrup4.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    private final String numero;
    private final String pin;
    private BigDecimal saldo;

    public Account(String numero, String pin, BigDecimal saldo) {
        this.numero = numero;
        this.pin = pin;
        this.saldo = saldo;
    }

    public String getNumero() { return numero; }
    public String getPin() { return pin; }
    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account a = (Account) o;
        return Objects.equals(numero, a.numero);
    }
    @Override public int hashCode() { return Objects.hash(numero); }
}
