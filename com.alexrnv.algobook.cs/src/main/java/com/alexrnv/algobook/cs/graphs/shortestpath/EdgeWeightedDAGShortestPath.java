package com.alexrnv.algobook.cs.graphs.shortestpath;

import com.alexrnv.algobook.cs.graphs.DAG;
import com.alexrnv.algobook.cs.graphs.EdgeWeightedDiGraph;
import com.alexrnv.algobook.cs.graphs.Graph;
import com.alexrnv.algobook.cs.graphs.TopologicalSort;

import java.util.Comparator;
import java.util.Iterator;

public class EdgeWeightedDAGShortestPath extends AbstractShortestPath {

    public EdgeWeightedDAGShortestPath(EdgeWeightedDiGraph graph, Graph.Node start, Graph.Node end) {
        super(graph, start, end);
        assert DAG.isDAG(graph);
    }

    public static ShortestPath shortestPath(EdgeWeightedDiGraph graph, Graph.Node start, Graph.Node end) {
        return new EdgeWeightedDAGShortestPath(graph, start, end).calculateWithWeightComparator(Long::compareTo);
    }

    public static ShortestPath longestPath(EdgeWeightedDiGraph graph, Graph.Node start, Graph.Node end) {
        return new EdgeWeightedDAGShortestPath(graph, start, end).calculateWithWeightComparator(Comparator.reverseOrder());
    }

    @Override
    protected Iterator<Graph.Node> getNodeIterator() {
        return TopologicalSort.sort(graph).iterator();
    }

}
