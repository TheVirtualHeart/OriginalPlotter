import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import javax.imageio.*;
import java.io.*;

public class WriteTest1 {
    public static void main(String[] args) {
        int width = 300;
        int height = 300;
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bi.createGraphics();
        g2.setColor(Color.white);
        g2.fillRect(0,0, width, height);
        g2.setStroke(new BasicStroke(10.0f));
        g2.setPaint(new GradientPaint(0,0, Color.green, 50,50, Color.yellow, true));
        g2.fill(new Ellipse2D.Float(50,50,200,200));
        File f = new File("image.png");
        try {
                // png is an image format (like gif or jpg)
                ImageIO.write(bi, "png", f);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
