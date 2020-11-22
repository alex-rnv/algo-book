package com.alexrnv.algobook.cs.graphs;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;

public class EdgeWeightedDiGraph extends Graph {

    private final Map<Node, List<Edge>> edges;

    protected EdgeWeightedDiGraph(int numNodes) {
        super(numNodes);
        this.edges = new HashMap<>(numNodes+1);
    }

    protected void addEdge(Node from, Node to, int weight) {
        this.edges.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(from, to, weight));
    }

    public List<Edge> getEdgesFrom(Node node) {
        return unmodifiableList(this.edges.getOrDefault(node, emptyList()));
    }

    @Override
    protected List<Node> getAdjNodes(Node node) {
        return getEdgesFrom(node).stream().map(edge -> edge.to).collect(Collectors.toList());
    }

    @Override
    protected boolean connected(Node nodeFrom, Node nodeTo) {
        return getAdjNodes(nodeFrom).contains(nodeTo);
    }

    @Override
    public boolean isDirected() {
        return true;
    }

    public static class Edge {
        private final Node from;
        private final Node to;
        private final int weight;

        public Edge(Node from, Node to, int weight) {
            this.from = Objects.requireNonNull(from);
            this.to = Objects.requireNonNull(to);
            this.weight = weight;
        }

        public Node getFrom() {
            return from;
        }

        public Node getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return weight == edge.weight &&
                    from.equals(edge.from) &&
                    to.equals(edge.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to, weight);
        }
    }
}
