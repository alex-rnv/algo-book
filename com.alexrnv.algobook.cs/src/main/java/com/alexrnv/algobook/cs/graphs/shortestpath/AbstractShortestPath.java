package com.alexrnv.algobook.cs.graphs.shortestpath;

import com.alexrnv.algobook.cs.graphs.EdgeWeightedDiGraph;
import com.alexrnv.algobook.cs.graphs.Graph.Node;

import java.util.*;

abstract class AbstractShortestPath implements ShortestPath {
    protected final EdgeWeightedDiGraph graph;
    protected final Node start;
    protected final Node end;
    protected final Map<Node, EdgeAndDistance> distanceMap = new HashMap<>();
    protected final List<Node> path = new ArrayList<>();

    protected AbstractShortestPath(EdgeWeightedDiGraph graph, Node start, Node end) {
        this.graph = graph;
        this.start = start;
        this.end = end;
        initDistanceMap();
    }

    protected void initDistanceMap() {
        this.distanceMap.put(start, new EdgeAndDistance(null, 0));
    }

    @Override
    public EdgeWeightedDiGraph getGraph() {
        return graph;
    }

    @Override
    public List<Node> getPath() {
        return Collections.unmodifiableList(path);
    }

    @Override
    public long getDistance() {
        return this.distanceMap.get(end).getDist();
    }

    abstract protected Iterator<Node> getNodeIterator();

    protected ShortestPath calculateWithWeightComparator(Comparator<Long> comparator) {
        relaxNode(start, comparator);
        Iterator<Node> nodeIterator = getNodeIterator();
        while (nodeIterator.hasNext()) {
            relaxNode(nodeIterator.next(), comparator);
        }
        calculatePath();
        return this;
    }

    private void relaxNode(Node node, Comparator<Long> comparator) {
        EdgeAndDistance distToNode = distanceMap.get(node);
        for (EdgeWeightedDiGraph.Edge edge : graph.getEdgesFrom(node)) {
            Node to = edge.getTo();
            long dist = distToNode.getDist() + edge.getWeight();
            EdgeAndDistance edgeAndDistance = distanceMap.get(to);
            if (edgeAndDistance == null || comparator.compare(edgeAndDistance.getDist(), dist) > 0) {
                EdgeAndDistance updatedEdgeAndDistance = new EdgeAndDistance(edge, dist);
                updateEdgeAndDistance(to, edgeAndDistance, updatedEdgeAndDistance);
            }
        }
        markVisited(node);
    }

    protected void markVisited(Node node) {}

    protected void updateEdgeAndDistance(Node to, EdgeAndDistance distanceOld, EdgeAndDistance distanceNew) {
        distanceMap.remove(to, distanceOld);
        distanceMap.put(to, distanceNew);
    }

    private void calculatePath() {
        Node node = end;
        while (node != start) {
            path.add(node);
            EdgeAndDistance edgeAndDistance = this.distanceMap.get(node);
            node = edgeAndDistance.getEdge().getFrom();
        }
        path.add(start);
        Collections.reverse(path);
    }

    class EdgeAndDistance {
        private final EdgeWeightedDiGraph.Edge edge;
        private final long dist;

        EdgeAndDistance(EdgeWeightedDiGraph.Edge edge, long dist) {
            this.edge = edge;
            this.dist = dist;
        }

        public EdgeWeightedDiGraph.Edge getEdge() {
            return edge;
        }

        public long getDist() {
            return dist;
        }
    }
}
