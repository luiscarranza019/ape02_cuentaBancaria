package edu.unl.cc.cuentabancaria;

import java.util.Scanner;

public class MenuCuentaBancaria {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CuentaBancaria cuenta = null;
        int opcion = 0;

        do {
            System.out.println("\n========= SISTEMA BANCARIO - MENÚ =========");
            System.out.println("1. Crear cuenta");
            System.out.println("2. Depositar / Retirar");
            System.out.println("3. Consultar saldo e historial");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            // Lectura con validación mediante clase Wrapper Integer para evitar caídas por letras
            String entradaOpcion = scanner.nextLine().trim();
            try {
                opcion = Integer.parseInt(entradaOpcion);
            } catch (NumberFormatException e) {
                System.out.println("[Error de entrada]: Debe ingresar un número entero válido (1-4).");
                opcion = 0;
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n--- CREAR CUENTA BANCARIA ---");
                    System.out.print("Ingrese el número de cuenta: ");
                    String numero = scanner.nextLine().trim();

                    System.out.print("Ingrese el nombre del titular: ");
                    String titular = scanner.nextLine().trim();

                    if (numero.isEmpty() || titular.isEmpty()) {
                        System.out.println("[Error]: El número de cuenta y el titular no pueden estar vacíos.");
                        break;
                    }

                    Double saldoInicial = leerMontoValido(scanner, "Ingrese el saldo inicial ($): ");
                    if (saldoInicial != null && saldoInicial >= 0) {
                        cuenta = new CuentaBancaria(numero, titular, saldoInicial);
                        System.out.println("¡Cuenta creada exitosamente!");
                        System.out.println(cuenta.toString());
                    } else {
                        System.out.println("[Error]: No se pudo crear la cuenta. El saldo inicial no puede ser negativo.");
                    }
                    break;

                case 2:
                    if (cuenta == null) {
                        System.out.println("[Aviso]: Primero debe crear una cuenta (Opción 1).");
                        break;
                    }
                    System.out.println("\n--- OPERACIONES DE CAJA ---");
                    System.out.println("1. Depositar");
                    System.out.println("2. Retirar");
                    System.out.print("Seleccione el tipo de transacción (1 o 2): ");
                    String tipoOp = scanner.nextLine().trim();

                    if (tipoOp.equals("1")) {
                        Double montoDep = leerMontoValido(scanner, "Ingrese el monto a depositar ($): ");
                        if (montoDep != null && cuenta.depositar(montoDep)) {
                            System.out.printf("Depósito exitoso. Nuevo saldo final: $%.2f%n", cuenta.getSaldo());
                        }
                    } else if (tipoOp.equals("2")) {
                        Double montoRet = leerMontoValido(scanner, "Ingrese el monto a retirar ($): ");
                        if (montoRet != null && cuenta.retirar(montoRet)) {
                            System.out.printf("Retiro exitoso. Nuevo saldo final: $%.2f%n", cuenta.getSaldo());
                        }
                    } else {
                        System.out.println("[Error]: Subopción no válida.");
                    }
                    break;

                case 3:
                    if (cuenta == null) {
                        System.out.println("[Aviso]: Primero debe crear una cuenta (Opción 1).");
                        break;
                    }
                    System.out.println("\n" + cuenta.toString());
                    System.out.println("HISTORIAL DE MOVIMIENTOS:");
                    System.out.print(cuenta.consultarHistorial());
                    System.out.println("----------------------------------------");
                    break;

                case 4:
                    System.out.println("Saliendo del sistema bancario...");
                    break;

                default:
                    System.out.println("[Error]: Opción fuera de rango. Seleccione entre 1 y 4.");
                    break;
            }
        } while (opcion != 4);

        scanner.close();
    }

    // Método auxiliar que aplica Double.parseDouble() para convertir cadenas y validar números reales
    private static Double leerMontoValido(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        String entrada = scanner.nextLine().trim().replace(",", ".");
        try {
            return Double.parseDouble(entrada); // Uso de método utilitario de la clase Wrapper Double
        } catch (NumberFormatException e) {
            System.out.println("[Error de formato]: Debe ingresar un valor numérico válido.");
            return null;
        }
    }
}
