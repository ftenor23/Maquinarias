package Graphics.Windows.Info;

import javax.swing.*;

public abstract class ShowInfo {
    private final static String INFO = "Hecho por Facundo Tenor\n";
    private final static String CONTACT = "Contacto:\n\n";
    private final static String GITHUB = "Github: @ftenor23\n";
    private final static String LINKEDIN = "LinkedIn: @facundo-tenor";

    public static void showData() {
        JOptionPane.showMessageDialog(null, INFO + CONTACT + GITHUB + LINKEDIN);
    }
}
