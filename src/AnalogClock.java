import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AnalogClock extends JFrame {
    private ClockPanel clockPanel;

    public AnalogClock() {
        setTitle("Analog Clock");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        clockPanel = new ClockPanel();
        add(clockPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AnalogClock();
    }

    private class ClockPanel extends JPanel implements Runnable {
        private Thread thread;

        public ClockPanel() {
            setPreferredSize(new Dimension(300, 300));
            thread = new Thread(this);
            thread.start();
        }

        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Get current time
            Calendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);

            // Draw clock face
            int width = getWidth();
            int height = getHeight();
            int centerX = width / 2;
            int centerY = height / 2;
            int radius = Math.min(width, height) / 2;
            g.setColor(Color.WHITE);
            g.fillOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);

            // Draw hour marks
            g.setColor(Color.BLACK);
            for (int i = 0; i < 12; i++) {
                double angle = (i - 3) * Math.PI / 6;
                int x1 = (int) (centerX + (radius - 30) * Math.cos(angle));
                int y1 = (int) (centerY + (radius - 30) * Math.sin(angle));
                int x2 = (int) (centerX + radius * Math.cos(angle));
                int y2 = (int) (centerY + radius * Math.sin(angle));
                g.drawLine(x1, y1, x2, y2);
            }

            // Draw minute marks
            for (int i = 0; i < 60; i++) {
                if (i % 5 != 0) {
                    double angle = (i - 15) * Math.PI / 30;
                    int x1 = (int) (centerX + (radius - 15) * Math.cos(angle));
                    int y1 = (int) (centerY + (radius - 15) * Math.sin(angle));
                    int x2 = (int) (centerX + radius * Math.cos(angle));
                    int y2 = (int) (centerY + radius * Math.sin(angle));
                    g.drawLine(x1, y1, x2, y2);
                }
            }

            // Draw hour hand
            double hourAngle = (hour - 3) * Math.PI / 6 + (minute * Math.PI / 360);
            int hourLength = radius / 2;
            int x = (int) (centerX + hourLength * Math.cos(hourAngle));
            int y = (int) (centerY + hourLength * Math.sin(hourAngle));
            g.setColor(Color.BLACK);
            g.drawLine(centerX, centerY, x, y);

            // Draw minute hand
            double minuteAngle = (minute - 15) * Math.PI / 30;
            int minuteLength = radius * 3 / 4;
            x = (int) (centerX + minuteLength * Math.cos(minuteAngle));
            y = (int) (centerY + minuteLength * Math.sin(minuteAngle));
            g.setColor(Color.BLACK);
            g.drawLine(centerX, centerY, x, y);
    
            // Draw second hand
            double secondAngle = (second - 15) * Math.PI / 30;
            int secondLength = radius * 3 / 4;
            x = (int) (centerX + secondLength * Math.cos(secondAngle));
            y = (int) (centerY + secondLength * Math.sin(secondAngle));
            g.setColor(Color.RED);
            g.drawLine(centerX, centerY, x, y);
        }
    }
    
    }
