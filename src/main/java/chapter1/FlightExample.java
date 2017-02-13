package chapter1;
import javax.swing.*;
import java.awt.*;
import swidgets.*;
import nz.sodium.*;
/**
 * Created by coolk on 05-02-2017.
 */
public class FlightExample {
    public static void main(String[] args)
    {
        JFrame view = new JFrame("airline1");
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SDateField dep = new SDateField();
        SDateField ret = new SDateField();

        Cell<Boolean> valid = dep.date.lift(ret.date,
                (d, r) -> d.compareTo(r) <= 0);
        SButton ok = new SButton("OK", valid);

        GridBagLayout gridBag = new GridBagLayout();
        view.setLayout(gridBag);
        GridBagConstraints c = new GridBagConstraints();

        view.add(new JLabel("departure"), c);
        view.add(dep, c);
        view.add(new JLabel("return"), c);
        view.add(ok, c);
        view.setSize(380, 140);
        view.setVisible(true);

    }
}
