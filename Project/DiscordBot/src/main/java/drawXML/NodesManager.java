package drawXML;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class NodesManager {

    private int numberOfNodes;
    private HashMap<String, Integer> nodesMap = new HashMap<>();
    private List<Node> nodeList = new ArrayList<>();

    public NodesManager() {
    }

    public void manageNodes(String input) {
        //5 0-1 0-2 1-3 4-4
        String[] splitString = new String[100];
        splitString = input.split(" ");
        numberOfNodes = Integer.parseInt(splitString[0]);

        for (int index = 1; index < splitString.length; index++) {
            String[] splitString2 = new String[100];
            splitString2 = splitString[index].split("-");
            for (int index2 = 0; index2 < splitString2.length; index2++) {
                if (!nodesMap.containsKey(splitString2[index2])) {
                    nodesMap.put(splitString2[index2], Integer.parseInt(splitString2[index2]));
                    Node node = new Node(nodesMap.get(splitString2[index2]));
                    nodeList.add(node);
                }
            }
            for (int index2 = 0; index2 < nodeList.size(); index2++) {
                if (nodeList.get(index2).getNumber() == Integer.parseInt(splitString2[0])) {
                    for (int index3 = 0; index3 < nodeList.size(); index3++)
                        if (nodeList.get(index3).getNumber() == Integer.parseInt(splitString2[1])) {
                            nodeList.get(index2).addNeighbour(nodeList.get(index3));
                            nodeList.get(index3).addNeighbour(nodeList.get(index2));
                        }
                }
            }
        }
    }

    public List<Node> getNodeList() {
        return nodeList;
    }
}
