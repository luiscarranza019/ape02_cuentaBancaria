package edu.unl.cc.ape.domain.view;

/*
autor: Jhoan Rojas (XforceEEE)
 */

import java.util.Scanner;

public class FraccionExecutor {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Bienvenido al sistema de operaciones con fracciones.");

        MenuFraccion menu = new MenuFraccion(teclado);
        menu.ejecutar();

        teclado.close();
    }
}
