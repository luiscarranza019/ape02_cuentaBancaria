package edu.unl.cc.cuentabancaria;

/**
 * @author: Luis Carranza
 * Represents a user accBANK
 */

public class CuentaBancaria {

    // Atributos encapsulados (privados)
    private String numeroCuenta;
    private String nombreTitular;
    private Double saldo; // Uso de clase Wrapper Double (autoboxing/unboxing)
    private String historial;

    // Constructor con validación de saldo inicial
    public CuentaBancaria(String numeroCuenta, String nombreTitular, Double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.nombreTitular = nombreTitular;
        if (saldoInicial != null && saldoInicial >= 0.0) {
            this.saldo = saldoInicial;
        } else {
            System.out.println("[Advertencia]: El saldo inicial no puede ser negativo. Se asignará $0.00.");
            this.saldo = 0.0;
        }
        this.historial = "Cuenta creada con saldo inicial: $" + formatearMonto(this.saldo) + "\n";
    }

    // Métodos Getter
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public Double getSaldo() {
        return saldo;
    }

    // Validar que el monto de retiro sea positivo y no exceda el saldo disponible
    public boolean validarRetiro(double monto) {
        return monto > 0 && monto <= this.saldo;
    }

    // Realizar depósito
    public boolean depositar(double monto) {
        if (monto <= 0) {
            System.out.println("[Error]: El monto a depositar debe ser mayor que cero.");
            return false;
        }
        this.saldo += monto; // Autoboxing y unboxing automático con Double
        this.historial += "Depósito: +" + formatearMonto(monto) + "\n";
        return true;
    }

    // Realizar retiro validando disponibilidad de fondos
    public boolean retirar(double monto) {
        if (monto <= 0) {
            System.out.println("[Error]: El monto a retirar debe ser mayor que cero.");
            return false;
        }
        if (!validarRetiro(monto)) {
            System.out.printf("[Error]: Fondos insuficientes. Su saldo disponible es $%.2f y desea retirar $%.2f.%n",
                    this.saldo, monto);
            return false;
        }
        this.saldo -= monto;
        this.historial += "Retiro: -" + formatearMonto(monto) + "\n";
        return true;
    }

    // Consultar el registro de movimientos
    public String consultarHistorial() {
        return this.historial;
    }

    // Método auxiliar para mostrar enteros sin decimales redundantes (ej. +1000) o con 2 decimales si tiene centavos
    private String formatearMonto(double monto) {
        if (monto % 1 == 0) {
            return Integer.toString((int) monto);
        } else {
            return String.format("%.2f", monto);
        }
    }

    // Sobrescritura del método toString() para mostrar el estado de la cuenta
    @Override
    public String toString() {
        return String.format(
                "----------------------------------------\n" +
                        "DATOS DE LA CUENTA BANCARIA\n" +
                        "----------------------------------------\n" +
                        "Número de Cuenta : %s\n" +
                        "Titular          : %s\n" +
                        "Saldo Actual     : $%.2f\n" +
                        "----------------------------------------",
                this.numeroCuenta, this.nombreTitular, this.saldo
        );
    }
}
