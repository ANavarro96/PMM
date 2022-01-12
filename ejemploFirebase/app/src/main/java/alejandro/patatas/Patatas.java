package alejandro.patatas;

public class Patatas {

    String marca;
    double precio;
    String sabor;

    public Patatas() {
    }


    public Patatas(String marca, double precio, String sabor) {
        this.marca = marca;
        this.precio = precio;
        this.sabor = sabor;
    }

    @Override
    public String toString() {
        return "marca='" + marca + '\'' +
                ", precio=" + precio +
                ", sabor='" + sabor + '\'' +
                '}';
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }
}
