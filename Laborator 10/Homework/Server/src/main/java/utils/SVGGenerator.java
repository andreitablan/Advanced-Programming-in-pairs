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

import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.dom.GenericDOMImplementation;

import org.w3c.dom.Document;
import org.w3c.dom.DOMImplementation;

public class SVGGenerator {

    private List<User> users = new ArrayList<User>();
    private HashMap<User, Integer> hashMapX = new HashMap<>();
    private HashMap<User, Integer> hashMapY = new HashMap<>();
    int xMiddle = 700;
    int yMiddle = 300;
    int math = Math.min(xMiddle, yMiddle);
    int radius = 4 * math / 5;
    int radius1 = Math.abs(math - radius) / 2;

    public SVGGenerator(List<User> users) throws SVGGraphics2DIOException, UnsupportedEncodingException {
        this.users = users;

        DOMImplementation domImplementation = GenericDOMImplementation.getDOMImplementation();

        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImplementation.createDocument(svgNS, "svg", null);

        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

        this.paintPoints(svgGenerator);
        this.paintFriendship(svgGenerator);

        boolean useCSS = true;
        Writer outputStreamWriter = new OutputStreamWriter(System.out, "UTF-8");
        svgGenerator.stream(outputStreamWriter, useCSS);
    }

    public void paintPoints(Graphics2D graphics2D) {
        for (int index = 0; index < this.users.size(); index++) {
            double tangent = 2 * Math.PI * index / this.users.size();
            int xCoordonate = (int) Math.round(xMiddle + radius * Math.cos(tangent));
            int yCoordonate = (int) Math.round(yMiddle + radius * Math.sin(tangent));
            graphics2D.setPaint(Color.red);
            graphics2D.fillOval(xCoordonate - radius1, yCoordonate - radius1, 2 * radius1, 2 * radius1);
            graphics2D.setFont(graphics2D.getFont().deriveFont(15f));
            graphics2D.setPaint(Color.blue);
            graphics2D.drawString(this.users.get(index).getName(), xCoordonate - radius1 + 10, yCoordonate - radius1 + 25);
            hashMapX.put(this.users.get(index), (Integer) xCoordonate - radius1 + 10);
            hashMapY.put(this.users.get(index), (Integer) yCoordonate - radius1 + 25);
        }
    }

    public void paintFriendship(Graphics2D graphics2D) {
        for (User user : users) {
            for (User friend : user.getFriends()) {
                graphics2D.setPaint(Color.green);
                graphics2D.drawLine(hashMapX.get(user), hashMapY.get(user), hashMapX.get(friend), hashMapY.get(friend));
            }
        }
    }
}