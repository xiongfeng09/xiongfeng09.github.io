package screenSnapshot;

import gui.ava.html.image.generator.HtmlImageGenerator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.net.URL;

public class Html2Image {
    public static void main(String[] args) {

        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        imageGenerator.loadHtml("<b>Hello World!</b> Please goto <a title=\"Goto Google\" href=\"http://www.google.com\">Google</a>.");
        imageGenerator.saveAsImage("hello-world.png");
        imageGenerator.saveAsHtmlWithMap("hello-world.html", "hello-world.png");
    }


    protected static void generateOutput() throws Exception {

        //load the webpage into the editor
        //JEditorPane ed = new JEditorPane(new URL("http://www.google.com"));
        JEditorPane ed = new JEditorPane(new URL("http://www.hefeipet.com/client/chongwuzhishi/shenghuozatan/2012/0220/95.html"));
        ed.setSize(200,200);

        //create a new image
        BufferedImage image = new BufferedImage(ed.getWidth(), ed.getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        //paint the editor onto the image
        SwingUtilities.paintComponent(image.createGraphics(),
                ed,
                new JPanel(),
                0, 0, image.getWidth(), image.getHeight());
        //save the image to file
        ImageIO.write((RenderedImage)image, "png", new File("html.png"));
    }
}
