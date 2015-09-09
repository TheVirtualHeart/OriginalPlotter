//  <applet code="PixelTest" width="200" height="200"></applet>
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
 
public class PixelTest extends Applet {
    Label label;
 
    public void init() {
        BufferedImage image = makeImage();
        PixelTestCanvas canvas = new PixelTestCanvas(image);
        canvas.addMouseMotionListener(mml);
        setLayout(new BorderLayout());
        add(canvas);
        add(getLabel(), "Last");
    }
 
    private Label getLabel() {
        label = new Label();
        label.setAlignment(Label.CENTER);
        return label;
    }
 
    private BufferedImage makeImage() {
        int w = 100, h = 100;
        GeneralPath path = new GeneralPath();
        path.moveTo(w/2, 20);
        path.lineTo(w-20, h/2);
        path.lineTo(w/2, h-20);
        path.lineTo(20, h/2);
        path.closePath();
        int type = BufferedImage.TYPE_INT_ARGB_PRE;
        BufferedImage image = new BufferedImage(w, h, type);
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(Color.red);
        g2.fill(path);
        g2.setPaint(Color.blue);
        g2.draw(path);
        g2.dispose();
        return image;
    }
 
    private MouseMotionListener mml = new MouseMotionAdapter() {
        public void mouseMoved(MouseEvent e) {
            PixelTestCanvas canvas = (PixelTestCanvas)e.getSource();
            Rectangle r = canvas.loc.getBounds();
            Point p = e.getPoint();
            if(r.contains(p)) {
                int x = p.x - r.x;
                int y = p.y - r.y;
                int alpha = (canvas.image.getRGB(x, y) >> 24) & 0xff;
                label.setText("alpha = " + alpha);
            }
        }
    };
}
 
class PixelTestCanvas extends Canvas {
    BufferedImage image;
    Rectangle loc;
 
    public PixelTestCanvas(BufferedImage image) {
        this.image = image;
        loc = new Rectangle(image.getWidth(), image.getHeight());
    }
 
    public void paint(Graphics g) {
        loc.x = (getWidth() - loc.width)/2;
        loc.y = (getHeight() - loc.height)/2;
        g.drawImage(image, loc.x, loc.y, this);
    }
}
