package guiconsol;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Cntgfy on 10.08.2016.
 */
public class GUIConsole extends JFrame {
    public static void main(String[] args) throws IOException {
        GUIConsole guiConsole = new GUIConsole();

        System.out.println("Console worked.");
        System.out.println("Rely?");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (guiConsole.isActive()) {
            String str = reader.readLine();
            System.out.println(str);
        }
    }

    private JPanel p;

    private JTextArea ta;
    private JTextField tf;

    public GUIConsole() throws HeadlessException {
        super("GUI console");
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(600, 600);
        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(scrSize.width - getWidth(), 0, getWidth(), getHeight());

        setVisible(true);

        p = new JPanel();
        add(p);
        createComponents(p);
    }

    private void createComponents(JPanel p) {
        p.setLayout(null);

        ta = new JTextArea();
        associatedTextArea(ta);
        p.add(ta);

        tf = new JTextField(1);
        associatedTextField(tf);
        p.add(tf);

        repaint();
    }

    private void resizeComponents(JPanel p) {
        int tfHeight = 30;

        ta.setBounds(0, 0, p.getWidth(), p.getHeight() - tfHeight);

        tf.setBounds(0, p.getHeight() - tfHeight, p.getWidth(), tfHeight);
    }

    //Связывает с консольным потоком ввода текстовое поле
    private JTextField associatedTextField(JTextField tf) {
        try {
            GUIInputStream in = new GUIInputStream(System.in);
            in.setTextField(tf);
            System.setIn(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tf;
    }

    //Связывает с консольным выводом текстовую область
    private JTextArea associatedTextArea(JTextArea ta) {
        GUIPrintStream out = new GUIPrintStream(System.out);
        out.setTextArea(ta);
        System.setOut(out);

        return ta;
    }

    @Override
    public void repaint() {
        resizeComponents(p);
        super.repaint();
    }
}
