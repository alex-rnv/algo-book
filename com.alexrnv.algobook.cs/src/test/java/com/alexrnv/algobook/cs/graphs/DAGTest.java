package com.alexrnv.algobook.cs.graphs;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DAGTest {

    @Test
    public void linearGraphIsDAG() {
        DiGraph graph = new DiGraph(4);
        Graph.Node node1 = new Graph.Node("1");
        Graph.Node node2 = new Graph.Node("2");
        Graph.Node node3 = new Graph.Node("3");
        Graph.Node node4 = new Graph.Node("4");

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);

        graph.addAdjNode(node1, node2);
        graph.addAdjNode(node2, node3);
        graph.addAdjNode(node3, node4);

        assertTrue(DAG.isDAG(graph));
    }

    @Test
    public void starGraphIsDAG() {
        DiGraph graph = new DiGraph(4);
        Graph.Node node1 = new Graph.Node("1");
        Graph.Node node2 = new Graph.Node("2");
        Graph.Node node3 = new Graph.Node("3");
        Graph.Node node4 = new Graph.Node("4");

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);

        graph.addAdjNode(node1, node2);
        graph.addAdjNode(node1, node3);
        graph.addAdjNode(node1, node4);

        assertTrue(DAG.isDAG(graph));
    }

    @Test
    public void biDirectionalEdgeGraphIsNotDAG() {
        DiGraph graph = new DiGraph(4);
        Graph.Node node1 = new Graph.Node("1");
        Graph.Node node2 = new Graph.Node("2");
        Graph.Node node3 = new Graph.Node("3");
        Graph.Node node4 = new Graph.Node("4");

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);

        graph.addAdjNode(node1, node2);
        graph.addAdjNode(node2, node3);
        graph.addAdjNode(node3, node4);
        graph.addAdjNode(node4, node3);

        assertFalse(DAG.isDAG(graph));
    }

    @Test
    public void loopGraphIsNotDAG() {
        DiGraph graph = new DiGraph(4);
        Graph.Node node1 = new Graph.Node("1");
        Graph.Node node2 = new Graph.Node("2");
        Graph.Node node3 = new Graph.Node("3");
        Graph.Node node4 = new Graph.Node("4");

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);

        graph.addAdjNode(node1, node2);
        graph.addAdjNode(node2, node3);
        graph.addAdjNode(node3, node4);
        graph.addAdjNode(node4, node1);

        assertFalse(DAG.isDAG(graph));
    }
}