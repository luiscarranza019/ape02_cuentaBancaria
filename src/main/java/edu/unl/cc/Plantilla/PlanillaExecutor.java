package edu.unl.cc.ape.view;

import edu.unl.cc.ape.domain.Planilla;
import java.util.Scanner;

/**
 * @author Abraham Maza
 */
public class PlanillaExecutor {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        boolean registrado = false;

        System.out.println("===== CONTROL DE PLANILLAS DE ENERGÍA ELÉCTRICA =====");

        while (!registrado) {
            try {
                System.out.print("\nNúmero de medidor: ");
                String codigo = entrada.nextLine();

                System.out.print("Nombre del titular: ");
                String nombre = entrada.nextLine();

                System.out.print("Lectura anterior (kWh): ");
                double previa = Double.parseDouble(entrada.nextLine());

                System.out.print("Lectura actual (kWh): ");
                double actual = Double.parseDouble(entrada.nextLine());

                Planilla factura = new Planilla(codigo, nombre, previa, actual);

                System.out.println("\n>> Registro guardado correctamente.\n");
                System.out.println(factura);

                registrado = true;

            } catch (IllegalArgumentException ex) {
                System.out.println("\n[!] Dato inválido: " + ex.getMessage());
                System.out.println("[!] Vuelva a ingresar la información.");
            }
        }

        entrada.close();
        System.out.println("\nEjecución terminada.");
    }
}
