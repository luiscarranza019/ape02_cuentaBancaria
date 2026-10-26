package edu.unl.cc.ape.domain;
/**
 * @author Luis Rodriguez.
 * Represents in Ecuacion cuadratica.
 */
public class EcuacionCuadratica {

    private double a;
    private double b;
    private double c;

    public EcuacionCuadratica(double a, double b, double c) {
        setA(a);
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public void setA(double a) {
        if (a == 0) {
            throw new IllegalArgumentException("El coeficiente a no puede ser 0");
        }
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double calcularDiscriminante() {
        return b * b - 4 * a * c;
    }

    public String obtenerTipoRaices() {
        double d = calcularDiscriminante();
        if (d > 0) {
            return "Dos raíces reales distintas";
        } else if (d == 0) {
            return "Raíz real doble";
        } else {
            return "Raíces complejas";
        }
    }

    public String calcularRaices() {
        double d = calcularDiscriminante();

        if (d > 0) {
            double x1 = (-b + Math.sqrt(d)) / (2 * a);
            double x2 = (-b - Math.sqrt(d)) / (2 * a);
            return "x1 = " + x1 + ", x2 = " + x2;
        } else if (d == 0) {
            double x = -b / (2 * a);
            return "x = " + x;
        } else {
            double real = -b / (2 * a);
            double imaginaria = Math.sqrt(-d) / (2 * Math.abs(a));
            return "x1 = " + real + " + " + imaginaria + "i, x2 = " + real + " - " + imaginaria + "i";
        }
    }

    @Override
    public String toString() {
        return a + "x^2 + " + b + "x + " + c + " = 0";}
}
