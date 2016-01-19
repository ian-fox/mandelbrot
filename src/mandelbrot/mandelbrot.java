package mandelbrot;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class mandelbrot extends JPanel implements MouseListener {
    static final long serialVersionUID = 1;
    double rStart = -3;
    double rEnd = 2;
    double iStart = -2;
    double iEnd = 2;
    static int width = 1200;
    static int height = 800;
    static int maxDepth = 360;    
    
    protected void paintComponent(Graphics g) {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        int percent = 0;
        double rFactor = (rEnd - rStart) / (float) width;
        double iFactor = (iEnd - iStart) / (float) height;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ComplexNumber c = new ComplexNumber(rStart + rFactor * i, iStart + iFactor * j);
                ComplexNumber z = c;
                // c = new ComplexNumber(-0.74543, 0.11301);
                g2d.setColor(Color.black);
                for (int k = 0; k < maxDepth; k++) {
                    if (z.abs() > 2) {
                        float colour = k % 360 / (float) 360;
                        g2d.setColor(Color.getHSBColor(colour, 1, 1));
                        break;
                    }
                    z = z.multiply(z).add(c);
                }
                g2d.fillRect(i, j, 1, 1);
            }
            if (i * 100 / width != percent) {
                percent = i * 100 / width;
                System.out.println(percent + "%");
            }
        }
        
        System.out.println("100%");
        Graphics2D g2dComponent = (Graphics2D) g;
        g2dComponent.drawImage(bufferedImage, null, 0, 0);
    }
    
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle("Mandelbrot Set");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(9 + width, 38 + height);
        mandelbrot m = new mandelbrot();
        m.addMouseListener(m);
        f.add(m);
        f.setVisible(true);
    }
    
    public void mouseEntered(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {
        int button = e.getButton();
        if (button == MouseEvent.BUTTON1 || button == MouseEvent.BUTTON3) {
            double rFactor = (rEnd - rStart) / (float) width;
            double iFactor = (iEnd - iStart) / (float) height;
            float zoomFactor = 1;
            if (button == MouseEvent.BUTTON1) {
                zoomFactor = 1 / (float) 8;
            } else {
                zoomFactor = 8;
            }
            double newRStart = rStart + e.getX() * rFactor - (rEnd - rStart) * zoomFactor;
            double newREnd = rStart + e.getX() * rFactor + (rEnd - rStart) * zoomFactor;
            double newIStart = iStart + e.getY() * iFactor - (iEnd - iStart) * zoomFactor;
            double newIEnd = iStart + e.getY() * iFactor + (iEnd - iStart) * zoomFactor;
            rStart = newRStart;
            rEnd = newREnd;
            iStart = newIStart;
            iEnd = newIEnd;
            repaint();
        }
    }
    public void mouseExited(MouseEvent e) {}
}