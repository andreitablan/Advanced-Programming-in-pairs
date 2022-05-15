package utils;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import serverClasses.*;

import java.awt.Graphics2D;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.dom.GenericDOMImplementation;

import org.w3c.dom.Document;
import org.w3c.dom.DOMImplementation;

public class TestSVGGen{

    private List<User> users = new ArrayList<User>();
    private HashMap<User, Integer> hashMapX=new HashMap<>();
    private HashMap<User, Integer> hashMapY=new HashMap<>();

    int xCoordonate=0;
    int yCoordonate=0;
    int xMiddle = 700;
    int yMiddle = 300;
    int math = Math.min(xMiddle, yMiddle);
    int radius = 4 * math / 5;
    int radius1 = Math.abs(math - radius) / 2;
    public TestSVGGen(List<User> users) throws SVGGraphics2DIOException, UnsupportedEncodingException {
        this.users=users;
        // Get a DOMImplementation.
        DOMImplementation domImpl =
                GenericDOMImplementation.getDOMImplementation();

        // Create an instance of org.w3c.dom.Document.
        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);

        // Create an instance of the SVG Generator.
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

        // Ask the test to render into the SVG Graphics2D implementation.

        this.paintPoints(svgGenerator);
        this.paintFriendship(svgGenerator);

        // Finally, stream out SVG to the standard output using
        // UTF-8 encoding.
        boolean useCSS = true; // we want to use CSS style attributes
        Writer out = new OutputStreamWriter(System.out, "UTF-8");
        svgGenerator.stream(out, useCSS);
    }

    public void paintPoints(Graphics2D g2d) {
        for (int index = 0; index < this.users.size(); index++) {
            double tangent = 2 * Math.PI * index / this.users.size();
            int xCoordonate = (int) Math.round(xMiddle + radius * Math.cos(tangent));
            int yCoordonate = (int) Math.round(yMiddle + radius * Math.sin(tangent));
            g2d.setPaint(Color.red);
            g2d.fillOval(xCoordonate - radius1, yCoordonate - radius1, 2 * radius1, 2 * radius1);
            g2d.setFont(g2d.getFont().deriveFont(15f));
            g2d.setPaint(Color.blue);
            g2d.drawString(this.users.get(index).getName(),xCoordonate - radius1 + 10, yCoordonate - radius1+25);
            hashMapX.put(this.users.get(index),(Integer) xCoordonate - radius1 + 10);
            hashMapY.put(this.users.get(index),(Integer) yCoordonate - radius1+25);
        }
    }
    public void paintFriendship(Graphics2D g2d)
    {
            for(User user : users)
            {
                for(User friend: user.getFriends())
                {
                    g2d.setPaint(Color.green);
                    g2d.drawLine(hashMapX.get(user),hashMapY.get(user),hashMapX.get(friend),hashMapY.get(friend));
                }
            }
    }
}