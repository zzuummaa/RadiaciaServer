package guiconsol;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.LinkedList;

/**
 * Created by Cntgfy on 10.08.2016.
 *
 */
public class GUIInputStream extends InputStream {
    private JTextField tf;

    private ListenInThread listenInThread;
    private InputStream in;

    private PipedInputStream newIn;
    private PipedOutputStream newOut;

    private volatile LinkedList<String> queue = new LinkedList<>();

    public GUIInputStream(InputStream in) throws IOException {
        this.in = in;
        this.listenInThread = new ListenInThread(in);
        listenInThread.start();

        newOut = new PipedOutputStream();
        newIn = new PipedInputStream(newOut);
    }

    @Override
    public int read() throws IOException {
        return newIn.read();
    }

    public void setTextField(JTextField tf) {
        this.tf = tf;
        tf.addKeyListener(new TFListener());
    }

    /*
     * Слушает данные с консоли gui
     *
     * Выбрасывает <code>IOException</code> при нажатии в <code>tf</code> на enter,
     * если поток, вызвавший конструктор класса GUIInputStream, уже завершил свою работу
     */
    private class TFListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            try {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    synchronized (newOut) {
                        writeLine(tf.getText());
                        tf.setText("");
                    }
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    /**
     * Слушает поток ввода со стандартной консоли
     */
    private class ListenInThread extends Thread {
        private BufferedReader reader;

        private ListenInThread(InputStream in) {
            this.reader = new BufferedReader(new InputStreamReader(in));
        }

        @Override
        public void run() {
            try {
                while (!isInterrupted()) writeLine(reader.readLine());
            } catch (IOException e) {
                if (!isInterrupted()) e.printStackTrace();
            }
        }
    }

    /**
     * Записывает строку в этот выходной поток
     *
     * @throws IOException
     */
    private void writeLine(String str) throws IOException {
        newOut.write((str + System.lineSeparator()).getBytes());
    }

    /**
     * Прекращает работать с потоками
     *
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        listenInThread.interrupt();
    }

    /**
     * Высвобождает ресурсы и прекращает работу
     * с gui и потоками
     *
     * @throws IOException
     */
    public void closeAll() throws IOException {
        close();
        newIn.close();
        newOut.close();

        System.setIn(in);
    }

    @Override
    public int available() throws IOException {
        return newIn.available();
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return newIn.read(b, off, len);
    }
}
