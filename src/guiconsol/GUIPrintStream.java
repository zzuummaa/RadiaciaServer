package guiconsol;

import javax.swing.*;
import java.io.PrintStream;

/**
 * Created by Cntgfy on 10.08.2016.
 */
public class GUIPrintStream extends PrintStream {
    private JTextArea ta;

    public GUIPrintStream(PrintStream out) {
        super(out);
    }

    public void setTextArea(JTextArea textArea) {
        this.ta = textArea;
    }

    /**
     * Записывает строку в текстовое поле
     *
     * @param s
     */
    private void writeToTA(String s) {
        if (s == null) {
            ta.append("null");
        }
        ta.append(s);
    }

    private void writeNewLineToTA() {
        writeToTA("\n");
    }

    @Override
    public void print(String s) {
        super.print(s);

        writeToTA(s);
    }

    @Override
    public void print(Object obj) {
        super.print(obj);

        writeToTA(String.valueOf(obj));
    }

    @Override
    public void print(boolean b) {
        super.print(b);

        writeToTA(String.valueOf(b));
    }

    @Override
    public void print(char c) {
        super.print(c);

        writeToTA(String.valueOf(c));
    }

    @Override
    public void print(int i) {
        super.print(i);

        writeToTA(String.valueOf(i));
    }

    @Override
    public void print(long l) {
        super.print(l);

        writeToTA(String.valueOf(l));
    }

    @Override
    public void print(float f) {
        super.print(f);

        writeToTA(String.valueOf(f));
    }

    @Override
    public void print(double d) {
        super.print(d);

        writeToTA(String.valueOf(d));
    }

    @Override
    public void print(char[] s) {
        super.print(s);

        writeToTA(String.valueOf(s));
    }

    @Override
    public void println() {
        super.println();

        writeNewLineToTA();
    }

    @Override
    public void println(boolean x) {
        print(x);
        println();
    }

    @Override
    public void println(char x) {
        print(x);
        println();
    }

    @Override
    public void println(int x) {
        print(x);
        println();
    }

    @Override
    public void println(long x) {
        print(x);
        println();
    }

    @Override
    public void println(float x) {
        print(x);
        println();
    }

    @Override
    public void println(double x) {
        print(x);
        println();
    }

    @Override
    public void println(char[] x) {
        print(x);
        println();
    }

    @Override
    public void println(String x) {
        print(x);
        println();
    }

    @Override
    public void println(Object x) {
        print(x);
        println();
    }
}
