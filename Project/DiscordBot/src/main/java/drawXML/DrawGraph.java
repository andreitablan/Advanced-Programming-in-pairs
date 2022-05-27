package drawXML;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import javax.xml.transform.TransformerException;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DrawGraph {

    int xMiddle = 700;
    int yMiddle = 300;
    int math = Math.min(xMiddle, yMiddle);
    int radius = 4 * math / 5;
    int radius1 = Math.abs(math - radius) / 2;
    private List<Node> nodeList = new ArrayList<Node>();
    private HashMap<Node, Integer> hashMapX = new HashMap<>();
    private HashMap<Node, Integer> hashMapY = new HashMap<>();

    public DrawGraph(List<Node> nodeList) throws IOException, TransformerException {
        this.nodeList = nodeList;

        DOMImplementation domImplementation = GenericDOMImplementation.getDOMImplementation();

        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImplementation.createDocument(svgNS, "svg", null);

        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

        this.paintNodes(svgGenerator);
        this.paintEdges(svgGenerator);

        boolean useCSS = true;

        OutputStream outputStream = new FileOutputStream("C:\\Users\\andre\\Desktop\\graph.xml");
        Writer outputStreamWriter = new OutputStreamWriter(outputStream);

        svgGenerator.stream(outputStreamWriter, useCSS);
        outputStreamWriter.close();
    }


    public void paintNodes(Graphics2D graphics2D) {
        for (int index = 0; index < this.nodeList.size(); index++) {
            double tangent = 2 * Math.PI * index / this.nodeList.size();
            int xCoordonate = (int) Math.round(xMiddle + radius * Math.cos(tangent));
            int yCoordonate = (int) Math.round(yMiddle + radius * Math.sin(tangent));
            graphics2D.setPaint(Color.red);
            graphics2D.fillOval(xCoordonate - radius1, yCoordonate - radius1, 2 * radius1, 2 * radius1);
            graphics2D.setFont(graphics2D.getFont().deriveFont(15f));
            graphics2D.setPaint(Color.blue);
            graphics2D.drawString(String.valueOf(this.nodeList.get(index).getNumber()), xCoordonate - radius1 + 10, yCoordonate - radius1 + 25);
            hashMapX.put(this.nodeList.get(index), xCoordonate - radius1 + 10);
            hashMapY.put(this.nodeList.get(index), yCoordonate - radius1 + 25);
        }
    }

    public void paintEdges(Graphics2D graphics2D) {
        for (Node node : nodeList) {
            for (Node neighbour : node.getNeighbours()) {
                graphics2D.setPaint(Color.green);
                graphics2D.drawLine(hashMapX.get(node), hashMapY.get(node), hashMapX.get(neighbour), hashMapY.get(neighbour));
            }
        }
    }
}