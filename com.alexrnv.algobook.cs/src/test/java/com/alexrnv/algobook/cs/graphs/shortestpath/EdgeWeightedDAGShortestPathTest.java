package com.alexrnv.algobook.cs.graphs.shortestpath;

import com.alexrnv.algobook.cs.graphs.EdgeWeightedDiGraph;
import com.alexrnv.algobook.cs.graphs.Graph;
import com.alexrnv.algobook.cs.graphs.SampleGraphs;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EdgeWeightedDAGShortestPathTest {

    @Test
    public void testDAG() {
        EdgeWeightedDiGraph graph = SampleGraphs.sampleDAG();
        Graph.Node start = SampleGraphs.getStart(graph);
        Graph.Node end = SampleGraphs.getEnd(graph);
        ShortestPath shortestPath = EdgeWeightedDAGShortestPath.shortestPath(graph, start, end);
        assertEquals(2296L, shortestPath.getDistance());

        ShortestPath longestPath = EdgeWeightedDAGShortestPath.longestPath(graph, start, end);
        assertEquals(4285L, longestPath.getDistance());
    }

    @Test(expected = AssertionError.class)
    public void testNotDAG() {
        EdgeWeightedDiGraph graph = SampleGraphs.sampleDirectedGraphWithNoNegativeWeights();
        Graph.Node start = SampleGraphs.getStart(graph);
        Graph.Node end = SampleGraphs.getEnd(graph);
        EdgeWeightedDAGShortestPath.shortestPath(graph, start, end);
    }

}