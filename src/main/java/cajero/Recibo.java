package cajero;

public class Recibo {
    private String ultimosDigitos;
    private String nombreTitular;
    private double cantidadRetirada;
    private double cobroComision;

    public Recibo(String ultimosDigitos, String nombreTitular, double cantidadRetirada, double cobroComision) {
        this.ultimosDigitos = ultimosDigitos;
        this.nombreTitular = nombreTitular;
        this.cantidadRetirada = cantidadRetirada;
        this.cobroComision = cobroComision;
    }

    public String getUltimosDigitos() {
        return ultimosDigitos;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public double getCantidadRetirada() {
        return cantidadRetirada;
    }

    public double getCobroComision() {
        return cobroComision;
    }
}
