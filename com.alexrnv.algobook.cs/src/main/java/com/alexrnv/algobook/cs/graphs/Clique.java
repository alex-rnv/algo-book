package com.alexrnv.algobook.cs.graphs;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * https://en.wikipedia.org/wiki/Clique_problem
 */
public class Clique {

    public static Collection<Graph.Node> findCliqueOfSize(Graph graph, int cliqueSize) {
        Checker checker = new Checker(graph, cliqueSize);
        Iterator<Graph.Node> nodeIterator = graph.nodeIterator();
        while (nodeIterator.hasNext()) {
            Graph.Node node = nodeIterator.next();
            Collection<Graph.Node> clique = checker.findCliqueAroundNode(node);
            if (clique != null)
                return clique;
        }
        return null;
    }

    public static Checker checker(Graph graph, int cliqueSize) {
        return new Checker(graph, cliqueSize);
    }

    /**
     * Checks if a clique is formed by some node and its adjacent nodes.
     * In practice, when the new node is added, only this new node can form a clique,
     * if it is not yet found, so this class will only check the vicinity of newly added node.
     */
    public static class Checker {
        private final Graph graph;
        private final int cliqueSize;

        private Checker(Graph graph, int cliqueSize) {
            this.graph = graph;
            this.cliqueSize = cliqueSize;
        }

        /**
         * @return a set of nodes forming a clique (complete subgraph) including the node, or null if there is no clique.
         */
        public Set<Graph.Node> findCliqueAroundNode(Graph.Node node) {
            var adjNodes = graph.getAdjNodes(node);
            Set<Graph.Node> complete = new HashSet<>();
            complete.add(node);
            for (Graph.Node adj : adjNodes) {
                if (complete.contains(adj))
                    continue;
                if(checkNode(adj, complete))
                    return complete;
            }
            complete.remove(node);
            return null;
        }

        private boolean checkNode(Graph.Node node, Set<Graph.Node> complete) {
            if(mutuallyConnected(node, complete)) {
                complete.add(node);
                if (complete.size() == cliqueSize)
                    return true;

                for (Graph.Node adj : graph.getAdjNodes(node)) {
                    if (complete.contains(adj))
                        continue;
                    if(checkNode(adj, complete))
                        return true;
                }
                complete.remove(node);
            }
            return false;
        }

        private boolean mutuallyConnected(Graph.Node node, Collection<Graph.Node> nodes) {
            return nodes.stream().allMatch(n -> mutuallyConnected(n, node));
        }

        private boolean mutuallyConnected(Graph.Node start, Graph.Node node) {
            return graph.connected(start, node) && graph.connected(node, start);
        }

    }
}
