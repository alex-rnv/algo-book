package com.alexrnv.algobook.cs.graphs.shortestpath;

import com.alexrnv.algobook.cs.graphs.Graph;

import java.util.List;

public interface ShortestPath {

    Graph getGraph();

    List<Graph.Node> getPath();

    long getDistance();
}
