package com.alexrnv.algobook.cs.graphs;

import java.util.*;

public class TopologicalSort {

    public static List<Graph.Node> sort(Graph graph) {

        List<Graph.Node> postOrder = new ArrayList<>();
        Set<Graph.Node> visited = new HashSet<>();

        Iterator<Graph.Node> nodeIterator = graph.nodeIterator();
        while (nodeIterator.hasNext()) {
            Graph.Node node = nodeIterator.next();
            dfs(graph, node, postOrder, visited);
        }
        Collections.reverse(postOrder);
        return postOrder;
    }

    private static void dfs(Graph graph, Graph.Node node, List<Graph.Node> postOrder, Set<Graph.Node> visited) {
        if (visited.contains(node)) return;
        visited.add(node);

        for (Graph.Node adj : graph.getAdjNodes(node)) {
            dfs(graph, adj, postOrder, visited);
        }
        postOrder.add(node);
    }
}
