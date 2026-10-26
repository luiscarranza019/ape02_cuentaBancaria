package edu.unl.cc.ape.domain.view;

/*
autor: Jhoan Rojas (XforceEEE)
 */

import edu.unl.cc.ape.domain.Fraccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuFraccion {

    private final Scanner scanner;
    private final List<Fraccion> fracciones;

    public MenuFraccion(Scanner scanner) {
        this.scanner = scanner;
        this.fracciones = new ArrayList<>();
    }

    public void ejecutar() {
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    agregarFraccion();
                    break;
                case 2:
                    listarFracciones();
                    break;
                case 3:
                    realizarOperacionBinaria();
                    break;
                case 4:
                    realizarOperacionUnitaria();
                    break;
                case 5:
                    continuar = false;
                    despedida();
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.\n");
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\n========== MENU DE FRACCIONES ==========");
        System.out.println("1. Agregar fracción");
        System.out.println("2. Listar fracciones");
        System.out.println("3. Operación entre dos fracciones");
        System.out.println("4. Operación sobre una fracción");
        System.out.println("5. Salir");
        System.out.println("========================================");
        System.out.print("Seleccione una opción: ");
    }

    private int leerOpcion() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }

    private void agregarFraccion() {
        Fraccion nuevaFraccion = ingresarFraccion();
        fracciones.add(nuevaFraccion);
        System.out.println(nuevaFraccion + " agregada satisfactoriamente.");
    }

    private Fraccion ingresarFraccion() {
        while (true) {
            try {
                System.out.println("\n--- Ingresar nueva fracción ---");
                scanner.nextLine(); // limpiar buffer
                System.out.print("Etiqueta de la fracción (Ej.: F1, A, Inicial): ");
                String etiqueta = scanner.nextLine();

                System.out.print("Valor del numerador: ");
                int numerador = scanner.nextInt();

                System.out.print("Valor del denominador: ");
                int denominador = scanner.nextInt();

                return new Fraccion(etiqueta, numerador, denominador);

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Valor incorrecto: Debe ingresar un número entero.");
                scanner.nextLine(); // limpiar buffer
            }
        }
    }

    private void listarFracciones() {
        if (fracciones.isEmpty()) {
            System.out.println("No hay fracciones registradas.");
            return;
        }

        System.out.println("\n--- Fracciones registradas ---");
        for (int i = 0; i < fracciones.size(); i++) {
            Fraccion f = fracciones.get(i);
            System.out.printf("%d. %s (Decimal: %f)%n", i + 1, f, f.obtenerDecimal());
        }
    }

    private void realizarOperacionBinaria() {
        if (fracciones.size() < 2) {
            System.out.println("Se necesitan al menos 2 fracciones registradas para realizar una operación.");
            return;
        }

        mostrarSubMenuBinario();
        int opcion = leerOpcion();

        Fraccion f1 = seleccionarFraccion("primera");
        Fraccion f2 = seleccionarFraccion("segunda");

        Fraccion resultado;

        switch (opcion) {
            case 1:
                resultado = f1.sumarFraccion(f2);
                System.out.printf("Suma: %s + %s = %s%n", f1, f2, resultado);
                break;
            case 2:
                resultado = f1.restarFraccion(f2);
                System.out.printf("Resta: %s - %s = %s%n", f1, f2, resultado);
                break;
            case 3:
                resultado = f1.multiplicacionFraccion(f2);
                System.out.printf("Multiplicación: %s * %s = %s%n", f1, f2, resultado);
                break;
            case 4:
                resultado = f1.divisionFraccion(f2);
                System.out.printf("División: %s / %s = %s%n", f1, f2, resultado);
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        guardarResultado(resultado);
    }

    private void mostrarSubMenuBinario() {
        System.out.println("\n--- Operaciones entre dos fracciones ---");
        System.out.println("1. Suma");
        System.out.println("2. Resta");
        System.out.println("3. Multiplicación");
        System.out.println("4. División");
        System.out.print("Seleccione operación: ");
    }

    private void realizarOperacionUnitaria() {
        if (fracciones.isEmpty()) {
            System.out.println("No hay fracciones registradas.");
            return;
        }

        mostrarSubMenuUnitario();
        int opcion = leerOpcion();

        Fraccion fraccion = seleccionarFraccion("una");

        switch (opcion) {
            case 1:
                System.out.printf("Decimal de %s = %f%n", fraccion, fraccion.obtenerDecimal());
                break;
            case 2:
                try {
                    Fraccion inversa = fraccion.invertirFraccion();
                    System.out.printf("Inversa de %s = %s%n", fraccion, inversa);
                    guardarResultado(inversa);
                } catch (IllegalArgumentException e) {
                    System.out.println("No se puede invertir: " + e.getMessage());
                }
                break;
            case 3:
                Fraccion simplificada = fraccion.simplificarFraccion();
                System.out.printf("Simplificación de %s = %s%n", fraccion, simplificada);
                guardarResultado(simplificada);
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private void mostrarSubMenuUnitario() {
        System.out.println("\n--- Operaciones sobre una fracción ---");
        System.out.println("1. Obtener valor decimal");
        System.out.println("2. Invertir fracción");
        System.out.println("3. Simplificar fracción");
        System.out.print("Seleccione operación: ");
    }

    private Fraccion seleccionarFraccion(String ordinal) {
        listarFracciones();
        System.out.printf("Seleccione la %s fracción (número): ", ordinal);
        int indice = leerOpcion() - 1;

        if (indice < 0 || indice >= fracciones.size()) {
            System.out.println("Selección inválida.");
            return seleccionarFraccion(ordinal);
        }

        return fracciones.get(indice);
    }

    private void guardarResultado(Fraccion resultado) {
        scanner.nextLine(); // limpiar buffer
        System.out.print("¿Desea guardar el resultado? (s/n): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();

        if (respuesta.equals("s") || respuesta.equals("si")) {
            fracciones.add(resultado);
            System.out.println("Resultado guardado como: " + resultado);
        }
    }

    private void despedida() {
        System.out.println("\n¡Gracias por usar el menú de fracciones. Hasta pronto!");
    }
}
