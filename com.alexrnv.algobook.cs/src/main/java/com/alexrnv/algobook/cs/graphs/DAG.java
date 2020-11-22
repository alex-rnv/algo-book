package com.alexrnv.algobook.cs.graphs;

import com.alexrnv.algobook.cs.graphs.Graph.Node;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DAG {

    public static boolean isDAG(Graph graph) {
        return findCycleOrNull(graph) == null;
    }

    public static Cycle findCycleOrNull(Graph graph) {
        assert graph.isDirected();
        Set<Node> discoveredNodes = new HashSet<>();
        Set<Node> processedNodes = new HashSet<>();
        Iterator<Node> nodeIterator = graph.nodeIterator();
        while (nodeIterator.hasNext()) {
            Cycle cycle = dfs(graph, nodeIterator.next(), discoveredNodes, processedNodes);
            if (cycle != null)
                return cycle;
        }
        return null;
    }

    private static Cycle dfs(Graph graph, Node node, Set<Node> discoveredNodes, Set<Node> processedNodes) {
        if (processedNodes.contains(node))
            return null;

        discoveredNodes.add(node);
        for (Node adj : graph.getAdjNodes(node)) {
            if (!discoveredNodes.contains(adj)) {
                Cycle cycle = dfs(graph, adj, discoveredNodes, processedNodes);
                if (cycle != null)
                    return cycle;
            } else if (!processedNodes.contains(adj)){
                return new Cycle(adj, node);
            }
        }
        processedNodes.add(node);
        return null;
    }

    public static class Cycle {
        private final Node start;
        private final Node end;

        protected Cycle(Node start, Node end) {
            this.start = start;
            this.end = end;
        }

        public Node getStart() {
            return start;
        }

        public Node getEnd() {
            return end;
        }

        @Override
        public String toString() {
            return "Cycle{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
