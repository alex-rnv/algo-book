package com.alexrnv.algobook.cs.graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Graph {
    protected final List<Node> nodeList;

    protected Graph(int numNodes) {
        this.nodeList = new ArrayList<>(numNodes);
    }

    public void addNode(Node node) {
        this.nodeList.add(node);
    }

    protected void addNode(int i, Node node) {
        assert i > 0;
        this.nodeList.add(i-1, node);
    }

    protected Node getNode(int i) {
        return this.nodeList.get(i-1);
    }

    public int getNumberOfNodes() {
        return this.nodeList.size();
    }

    public Iterator<Node> nodeIterator() {
        return this.nodeList.iterator();
    }

    abstract protected List<Node> getAdjNodes(Node node);
    abstract protected boolean connected(Node nodeFrom, Node nodeTo);
    abstract public boolean isDirected();

    public static class Node {
        private final Object value;

        public Node(Object value) {
            this.value = value;
        }

        public Object getValue() {
            return value;
        }
    }
}
