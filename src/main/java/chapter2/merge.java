package chapter2;
import swidgets.SButton;
import nz.sodium.Stream;
import swidgets.STextField;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.FlowLayout;

public class merge {
    public static void main(String[] args)  throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JFrame frame = new JFrame("gamechat");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());

            SButton onegai = new SButton("Onegai shimasu");
            SButton thanks = new SButton("Thank you");

            Stream<String> sThanks = thanks.sClicked.map(u -> "Thank you");
            Stream<String> sOnegai = onegai.sClicked.map(u -> "Onegai shimasu");
            Stream<String> sCanned = sOnegai.orElse(sThanks);

            STextField text = new STextField(sCanned, "");
            frame.add(text);
            frame.add(onegai);
            frame.add(thanks);
            frame.setSize(400, 160);
            frame.setVisible(true);
        });
    }
}
