import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SmartphoneHomescreens {

    public static void main(String[] args) throws IOException {
        // Create a JFrame window
        JFrame frame = new JFrame("Smartphone Homescreen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 900);

        // Create a JPanel to hold the background image
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("background_1.jpg");
                Image image = icon.getImage();
                int screenWidth = getWidth();
                int screenHeight = getHeight();
                double imageAspectRatio = (double)image.getWidth(null) / image.getHeight(null);
                double screenAspectRatio = (double)screenWidth / screenHeight;
                int imageWidth;
                int imageHeight;
                int x;
                int y;
                if (screenAspectRatio > imageAspectRatio) {
                    imageWidth = screenWidth;
                    imageHeight = (int)(imageWidth / imageAspectRatio);
                    x = 0;
                    y = (screenHeight - imageHeight) / 2;
                } else {
                    imageHeight = screenHeight;
                    imageWidth = (int)(imageHeight * imageAspectRatio);
                    x = (screenWidth - imageWidth) / 2;
                    y = 0;
                }
                g.drawImage(image, x, y, imageWidth, imageHeight, null);
            }
        };

        // Add buttons and other components to the JPanel
        JButton button1 = new JButton();
        BufferedImage img = ImageIO.read(SmartphoneHomescreens.class.getResource("IMG.png")); // load the image from the file
        Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // scale the image to 50x50 pixels
        ImageIcon icon1 = new ImageIcon(scaledImg); // create a new ImageIcon with the scaled image
        button1.setIcon(icon1); // set the new ImageIcon as the button's icon
        button1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0)); // set the button's border to a 0 thickness LineBorder
        button1.setContentAreaFilled(false);
        button1.setFocusPainted(false);
        button1.setPreferredSize(new Dimension(icon1.getIconWidth(), icon1.getIconHeight())); // set the button's preferred size to the size of the image
  
        JButton button2 = new JButton("Button 2");
        panel.add(button1);
        panel.add(button2);


        button1.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AnalogClock analogClock = new AnalogClock();
                panel.add(analogClock);
                panel.revalidate();
                panel.repaint();
            }

           
        });

        // Add the JPanel to the JFrame and show the window
        frame.getContentPane().add(panel);
        frame.setVisible(true);

        

    }
}