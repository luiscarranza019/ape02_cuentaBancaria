package edu.unl.cc.ape.domain;

/*
autor: Jhoan Rojas (XforceEEE)
 */

import java.security.PublicKey;

public class Fraccion {
    private String etiqueta;
    private int numerador;
    private int denominador;

    public Fraccion(){
        this("", 1, 1);
    }

    public Fraccion(String etiqueta, int numerador, int denominador) {
        this.etiqueta = etiqueta;
        this.numerador = numerador;
        this.denominador = validarDenominador(denominador, "denominador");
    }

    private int validarDenominador(int valor, String atributo) {
        if (valor == 0) {
            throw new IllegalArgumentException("El valor de " + atributo + "no puede ser igual a 0");
        }
        return  valor;
    }

    public double obtenerDecimal(){
        return (double) numerador/denominador;
    }

    public Fraccion sumarFraccion(Fraccion segundaFraccion){
        int numeradorSuma= (this.numerador * segundaFraccion.denominador) + (segundaFraccion.numerador * this.denominador);
        int denominadorSuma = this.denominador * segundaFraccion.denominador;
        return new Fraccion("suma", numeradorSuma, denominadorSuma);
    }

    public Fraccion restarFraccion(Fraccion segundaFraccion){
        int numeradorResta = (this.numerador * segundaFraccion.denominador) - (segundaFraccion.numerador * this.denominador);
        int denominadorResta = this.denominador * segundaFraccion.denominador;
        return new Fraccion("Resta", numeradorResta, denominadorResta);

    }

    public Fraccion multiplicacionFraccion (Fraccion segundaFraccion){
        int numeradorMultiplicacion = this.numerador * segundaFraccion.numerador;
        int denominadorMultiplicacion = this.denominador * segundaFraccion.denominador;
        return new Fraccion("Multiplicacion", numeradorMultiplicacion, denominadorMultiplicacion);
    }

    public Fraccion divisionFraccion (Fraccion segundaFraccion){
        int numeradorDivision = this.numerador * segundaFraccion.denominador;
        int denominadorDivision = this.denominador * segundaFraccion.numerador;
        return new Fraccion("Division", numeradorDivision, denominadorDivision);
    }

    public Fraccion invertirFraccion (){
        validarDenominador(this.numerador, "numerador Inversa");
        return new Fraccion("Inversa", this.denominador, this.numerador);
    }

    private int calcularMCD(int a, int b){
        while (b != 0) {
            int residuo = a % b;
            a = b;
            b = residuo;
        }

        return a;
    }

    public Fraccion simplificarFraccion(){
        if(this.numerador == 0){
            return new Fraccion("Simplificada", 0, 1); // devolver 0/1 es la definicion de simplificacion de una fraccion con numerador 0
        }

        int mcd = calcularMCD(this.numerador, this.denominador);

        int numeradorSimplificado = this.numerador / mcd;
        int denominadorSimplificado = this.denominador / mcd;

        return new Fraccion ("Simplificacion", numeradorSimplificado, denominadorSimplificado);
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public int getNumerador() {
        return numerador;
    }

    public void setNumerador(int numerador) {
        this.numerador = numerador;
    }

    public int getDenominador() {
       return denominador;
    }

    public void setDenominador(int denominador) {
        this.denominador = validarDenominador(denominador, "denominador");
    }

    @Override
    public String toString(){
        return etiqueta + ":" + "["+ numerador + "/" + denominador + "]";
    }
}