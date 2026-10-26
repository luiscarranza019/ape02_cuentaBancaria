package edu.unl.cc.ape.view;

import edu.unl.cc.ape.domain.EcuacionCuadratica;

import java.util.Scanner;

public class EcuacionExecutor {

    private Scanner scanner = new Scanner(System.in);
    private EcuacionCuadratica ecuacion;

    public static void main(String[] args) {
        EcuacionExecutor ejecutor = new EcuacionExecutor();
        int opcion = 0;

        while (opcion != 3) {
            ejecutor.mostrarMenu();
            opcion = ejecutor.leerOpcion();

            if (opcion == 1) {
                ejecutor.ingresarCoeficientes();
            } else if (opcion == 2) {
                ejecutor.mostrarRaices();
            } else if (opcion == 3) {
                System.out.println("Programa finalizado.");
            } else {
                System.out.println("Opción no válida.");
            }
        }
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("--- ECUACIÓN CUADRÁTICA ---");
        System.out.println("1. Ingresar coeficientes a, b, c");
        System.out.println("2. Calcular y mostrar las raíces");
        System.out.println("3. Salir");
    }

    private void ingresarCoeficientes() {
        double a = leerDouble("Ingrese a: ");
        double b = leerDouble("Ingrese b: ");
        double c = leerDouble("Ingrese c: ");

        try {
            ecuacion = new EcuacionCuadratica(a, b, c);
            System.out.println("Ecuación: " + ecuacion);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void mostrarRaices() {
        if (ecuacion == null) {
            System.out.println("Primero ingrese los coeficientes (opción 1).");
        } else {
            System.out.println("Ecuación: " + ecuacion);
            System.out.println("Discriminante: " + ecuacion.calcularDiscriminante());
            System.out.println("Tipo de raíces: " + ecuacion.obtenerTipoRaices());
            System.out.println("Raíces: " + ecuacion.calcularRaices());
        }
    }

    private double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida: ingrese un número.");
            }
        }
    }

    private int leerOpcion() {
        System.out.print("Seleccione una opción: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida: ingrese un número entero.");
            return 0;
        }
    }
}
