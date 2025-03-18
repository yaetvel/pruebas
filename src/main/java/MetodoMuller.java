import org.apache.commons.math3.complex.Complex;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MetodoMuller {

    public boolean calcularMuller(Complex x0, Complex x1, Complex x2, double errorMaximo, int iteracionesMaximas, Complex[] raiz, JTable tablaMuller) {
        Complex a, b, c;
        Complex x3;
        Complex aux1, aux2;
        double errorActual;
        int iteracionActual = 0;
        Complex f0, f1, f2, delta;

        DefaultTableModel modeloTabla = (DefaultTableModel) tablaMuller.getModel();
        modeloTabla.setRowCount(0);

        while (iteracionActual < iteracionesMaximas) {
            iteracionActual++;

            f0 = funcionMuller(x0);
            f1 = funcionMuller(x1);
            f2 = funcionMuller(x2);

            delta = (x0.subtract(x2)).multiply(x1.subtract(x2)).multiply(x0.subtract(x1));

            c = f2;
            b = ((x0.subtract(x2)).multiply(x0.subtract(x2)).multiply(f1.subtract(f2)))
                    .subtract((x1.subtract(x2)).multiply(x1.subtract(x2)).multiply(f0.subtract(f2)))
                    .divide(delta);

            a = ((f0.subtract(f2)).multiply(x1.subtract(x2))
                    .subtract((f1.subtract(f2)).multiply(x0.subtract(x2))))
                    .divide(delta);

            Complex discriminante = b.multiply(b).subtract(a.multiply(c).multiply(4)).sqrt();
            aux1 = b.add(discriminante);
            aux2 = b.subtract(discriminante);

            if (aux1.abs() > aux2.abs())
                x3 = x2.subtract(c.multiply(2).divide(aux1));
            else
                x3 = x2.subtract(c.multiply(2).divide(aux2));

            errorActual = x3.subtract(x2).abs();

            // Agregar datos a la tabla
            modeloTabla.addRow(new Object[]{iteracionActual, x0, x1, x2, x3, errorActual});

            if (errorActual <= errorMaximo) {
                raiz[0] = x3;
                return true;
            }

            x0 = x1;
            x1 = x2;
            x2 = x3;
        }
        return false;
    }

    private Complex funcionMuller(Complex x) {
        return x.pow(3).subtract(x.multiply(2)).add(1);  // Ejemplo: f(x) = x³ - 2x + 1
    }
}

