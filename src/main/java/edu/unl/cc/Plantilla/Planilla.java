package edu.unl.cc.ape.domain;

/**
 * @author Abraham Maza
 */
public class Planilla {

    private String codigoMedidor;
    private String propietario;
    private double medicionPrevia;
    private double medicionActual;

    public Planilla(String codigoMedidor, String propietario, double medicionPrevia, double medicionActual) {
        this.codigoMedidor = codigoMedidor;
        this.propietario = propietario;
        this.medicionPrevia = medicionPrevia;
        setLecturaActual(medicionActual);
    }

    public String getNumeroMedidor() {
        return codigoMedidor;
    }

    public String getNombreTitular() {
        return propietario;
    }

    public double getLecturaAnterior() {
        return medicionPrevia;
    }

    public double getLecturaActual() {
        return medicionActual;
    }

    public void setNumeroMedidor(String codigoMedidor) {
        this.codigoMedidor = codigoMedidor;
    }

    public void setNombreTitular(String propietario) {
        this.propietario = propietario;
    }

    public void setLecturaAnterior(double medicionPrevia) {
        this.medicionPrevia = medicionPrevia;
    }

    public void setLecturaActual(double medicionActual) {
        if (medicionActual < this.medicionPrevia) {
            throw new IllegalArgumentException("La lectura actual (" + medicionActual
                    + " kWh) no puede ser inferior a la lectura anterior (" + this.medicionPrevia + " kWh).");
        }
        this.medicionActual = medicionActual;
    }

    public double calcularConsumo() {
        return medicionActual - medicionPrevia;
    }

    public double calcularSubtotal() {
        double kwh = calcularConsumo();

        if (kwh <= 100) {
            return kwh * 0.10;
        }

        if (kwh <= 200) {
            return 100 * 0.10 + (kwh - 100) * 0.15;
        }

        return 100 * 0.10 + 100 * 0.15 + (kwh - 200) * 0.20;
    }

    public double calcularIVA() {
        return calcularSubtotal() * 0.15;
    }

    public double calcularTotal() {
        return calcularSubtotal() + calcularIVA();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== FACTURA ELÉCTRICA ==========\n");
        sb.append(String.format("Propietario : %s%n", propietario));
        sb.append(String.format("Medidor     : %s%n", codigoMedidor));
        sb.append(String.format("Consumo     : %.2f kWh%n", calcularConsumo()));
        sb.append("---------------------------------------\n");
        sb.append(String.format("Subtotal    : $ %.2f%n", calcularSubtotal()));
        sb.append(String.format("IVA (15%%)   : $ %.2f%n", calcularIVA()));
        sb.append(String.format("TOTAL       : $ %.2f%n", calcularTotal()));
        sb.append("=======================================");
        return sb.toString();
    }
}
