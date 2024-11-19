package recursionAndBackTracking;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PermutationAndCombinationTree {

    static int nodeId = 0; // Unique node identifier for each recursive call
    static StringBuilder graph = new StringBuilder();

    public static int permutationInfiniteCoin(ArrayList<Integer> arr, String ans, int target) {
        int currentNode = nodeId++;
        graph.append("Node").append(currentNode).append(" [label=\"PInf: ans=").append(ans)
                .append(", target=").append(target).append("\"];\n");

        if (target == 0) {
            graph.append("Node").append(currentNode).append(" [style=filled, fillcolor=green];\n");
            return 1;
        }

        int count = 0;

        for (int i = 0; i < arr.size(); i++) {
            int temp = arr.get(i);
            if (target >= temp) {
                int childNode = nodeId;
                count += permutationInfiniteCoin(arr, ans + temp, target - temp);
                graph.append("Node").append(currentNode).append(" -> Node").append(childNode).append(";\n");
            }
        }

        return count;
    }

    public static int permutationSingleCoin(ArrayList<Integer> arr, String ans, int target) {
        int currentNode = nodeId++;
        graph.append("Node").append(currentNode).append(" [label=\"PSing: ans=").append(ans)
                .append(", target=").append(target).append("\"];\n");

        if (target == 0) {
            graph.append("Node").append(currentNode).append(" [style=filled, fillcolor=green];\n");
            return 1;
        }

        int count = 0;

        for (int i = 0; i < arr.size(); i++) {
            int temp = arr.get(i);
            if (target >= temp && temp > 0) {
                arr.set(i, -temp);
                int childNode = nodeId;
                count += permutationSingleCoin(arr, ans + temp, target - temp);
                graph.append("Node").append(currentNode).append(" -> Node").append(childNode).append(";\n");
                arr.set(i, temp);
            }
        }

        return count;
    }

    public static int combinationInfiniteCoin(ArrayList<Integer> arr, int index, String ans, int target) {
        int currentNode = nodeId++;
        graph.append("Node").append(currentNode).append(" [label=\"CInf: ans=").append(ans)
                .append(", target=").append(target).append("\"];\n");

        if (target == 0) {
            graph.append("Node").append(currentNode).append(" [style=filled, fillcolor=green];\n");
            return 1;
        }

        int count = 0;

        for (int i = index; i < arr.size(); i++) {
            int ele = arr.get(i);
            if (target >= ele) {
                int childNode = nodeId;
                count += combinationInfiniteCoin(arr, i, ans + ele, target - ele);
                graph.append("Node").append(currentNode).append(" -> Node").append(childNode).append(";\n");
            }
        }

        return count;
    }

    public static int combinationSingleCoin(ArrayList<Integer> arr, int index, String ans, int target) {
        int currentNode = nodeId++;
        graph.append("Node").append(currentNode).append(" [label=\"CSing: ans=").append(ans)
                .append(", target=").append(target).append("\"];\n");

        if (target == 0) {
            graph.append("Node").append(currentNode).append(" [style=filled, fillcolor=green];\n");
            return 1;
        }

        int count = 0;

        for (int i = index; i < arr.size(); i++) {
            int ele = arr.get(i);
            if (target >= ele) {
                int childNode = nodeId;
                count += combinationSingleCoin(arr, i + 1, ans + ele, target - ele);
                graph.append("Node").append(currentNode).append(" -> Node").append(childNode).append(";\n");
            }
        }

        return count;
    }

    public static void writeGraphToFile(String fileName) throws IOException {
        Files.write(Paths.get(fileName), ("digraph G {\n" + graph + "}").getBytes());
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(2);
        arr.add(3);
        arr.add(5);
        arr.add(7);
        int target = 10;

        // Generate recursion tree for Permutation Infinite Coin
        nodeId = 0; // Reset node IDs
        graph = new StringBuilder(); // Reset graph
        permutationInfiniteCoin(arr, "", target);
        writeGraphToFile("permutationInfiniteCoin.dot");

        // Generate recursion tree for Permutation Single Coin
        nodeId = 0;
        graph = new StringBuilder();
        permutationSingleCoin(arr, "", target);
        writeGraphToFile("permutationSingleCoin.dot");

        // Generate recursion tree for Combination Infinite Coin
        nodeId = 0;
        graph = new StringBuilder();
        combinationInfiniteCoin(arr, 0, "", target);
        writeGraphToFile("combinationInfiniteCoin.dot");

        // Generate recursion tree for Combination Single Coin
        nodeId = 0;
        graph = new StringBuilder();
        combinationSingleCoin(arr, 0, "", target);
        writeGraphToFile("combinationSingleCoin.dot");

        System.out.println("Recursion trees generated as .dot files.");
    }
}