package chapter1;

import nz.sodium.Cell;
import swidgets.SButton;
import swidgets.SDateField;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.Calendar;

public class FlightExample2 {
    private static Boolean unlucky(Calendar dt) {
        final int day = dt.get(Calendar.DAY_OF_MONTH);
        return day == 4 || day == 14 || day == 24;
    }

    public static void main(String... args) throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JFrame view = new JFrame("airline1");
            view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            SDateField dep = new SDateField();
            SDateField ret = new SDateField();

            Rule r1 = new Rule((d, r) -> d.compareTo(r) <= 0);
            Rule r2 = new Rule((d,r) -> !unlucky(d) && !unlucky(r));

            Rule r = r1.and(r2);

            Cell<Boolean> valid = r.reify(dep.date, ret.date);
            SButton ok = new SButton("OK", valid);

            GridBagLayout gridBag = new GridBagLayout();
            view.setLayout(gridBag);
            GridBagConstraints c = new GridBagConstraints();

            view.add(new JLabel("departure"), c);
            view.add(dep, c);
            view.add(new JLabel("return"), c);
            view.add(ret, c);
            view.add(ok, c);
            view.setSize(580, 140);
            view.setVisible(true);
        });
    }
}
