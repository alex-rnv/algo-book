package com.alexrnv.algobook.cs.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;

public class DiGraph extends Graph {

    private final Map<Node, List<Node>> adjLists;

    public DiGraph(int numNodes) {
        super(numNodes);
        this.adjLists = new HashMap<>(numNodes+1);
    }

    public void addAdjNode(Node from, Node adj) {
        this.adjLists.computeIfAbsent(from, k -> new ArrayList<>()).add(adj);
    }

    @Override
    public List<Node> getAdjNodes(Node node) {
        return unmodifiableList(this.adjLists.getOrDefault(node, emptyList()));
    }

    @Override
    protected boolean connected(Node nodeFrom, Node nodeTo) {
        return adjLists.get(nodeFrom).contains(nodeTo);
    }

    @Override
    public boolean isDirected() {
        return true;
    }
}
