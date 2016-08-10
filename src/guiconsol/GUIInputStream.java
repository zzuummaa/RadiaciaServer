package guiconsol;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Cntgfy on 10.08.2016.
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
        tf.addKeyListener(new TFListener(queue));
    }

    private class TFListener implements KeyListener {
        private List<String> queue;

        private TFListener(List<String> queue) {
            this.queue = queue;
        }

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
                e.printStackTrace();
            }
        }
    }

    private void writeFromQueue() throws IOException {
        synchronized (queue) {
            while (!queue.isEmpty()) {
                newOut.write(queue.remove(0).getBytes());
            }
            newOut.flush();
        }
    }

    /**
     * Записывает строку в этот выходной поток
     *
     * @throws IOException
     */
    private void writeLine(String str) throws IOException {
        newOut.write( (str + System.lineSeparator()).getBytes() );
    }

    @Override
    public void close() throws IOException {
        listenInThread.interrupt();
        super.close();
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
