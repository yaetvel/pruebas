import org.apache.commons.math3.complex.Complex;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MetodoMuller metodo = new MetodoMuller();
        Complex x0 = new Complex(1, 0);
        Complex x1 = new Complex(2, 0);
        Complex x2 = new Complex(1.5, 0);
        double errorMaximo = 1e-6;
        int iteracionesMaximas = 100;
        Complex[] raiz = new Complex[1]; // Para recibir el resultado

        JTable tabla = new JTable(new javax.swing.table.DefaultTableModel(
                new Object[]{"Iteración", "x0", "x1", "x2", "x3", "Error"}, 0));

        boolean resultado = metodo.calcularMuller(x0, x1, x2, errorMaximo, iteracionesMaximas, raiz, tabla);

        if (resultado) {
            System.out.println("Raíz encontrada: " + raiz[0]);
        } else {
            System.out.println("No se encontró la raíz en el número máximo de iteraciones.");
        }
    }
}
