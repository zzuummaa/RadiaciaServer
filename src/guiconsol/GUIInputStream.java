package guiconsol;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Collection;
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
    public synchronized int read() throws IOException {
        if (newIn.available() == 0) {
            if (queue.isEmpty()) {
                return -1;
            }

            synchronized (queue) {
                newOut.write(queue.remove(0).getBytes());
                newOut.flush();
            }
        }

        return newIn.read();
    }

    private void waitData(Collection collection) {
        while (collection.isEmpty()) Thread.yield();
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
            if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                synchronized (queue) {
                    queue.add(tf.getText() + System.lineSeparator());
                    tf.setText("");
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    private class ListenInThread extends Thread {
        private BufferedReader reader;

        private ListenInThread(InputStream in) {
            this.reader = new BufferedReader(new InputStreamReader(in));
        }

        @Override
        public void run() {
            try {
                while (!isInterrupted()) queue.add(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void close() throws IOException {
        listenInThread.interrupt();
        super.close();
    }
}
