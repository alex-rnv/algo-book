package com.alexrnv.algobook.cs.graphs.shortestpath;

import com.alexrnv.algobook.cs.graphs.DAG;
import com.alexrnv.algobook.cs.graphs.EdgeWeightedDiGraph;
import com.alexrnv.algobook.cs.graphs.EdgeWeightedDiGraph.Edge;
import com.alexrnv.algobook.cs.graphs.Graph.Node;

import java.util.*;

public class DijkstraShortestPath extends AbstractShortestPath {

    private final PriorityQueue<EdgeAndDistance> edgeQueue;

    public DijkstraShortestPath(EdgeWeightedDiGraph graph, Node start, Node end, Comparator<EdgeAndDistance> comparator) {
        super(graph, start, end);
        assert hasNoNegativeWeights(graph);
        this.edgeQueue = new PriorityQueue<>(comparator);
        this.edgeQueue.add(this.distanceMap.get(start));
    }

    public static ShortestPath shortestPath(EdgeWeightedDiGraph graph, Node start, Node end) {
        return new DijkstraShortestPath(graph, start, end, Comparator.comparingLong(EdgeAndDistance::getDist))
                .calculateWithWeightComparator(Long::compareTo);
    }

    public static ShortestPath longestPath(EdgeWeightedDiGraph graph, Node start, Node end) {
        assert DAG.isDAG(graph);
        return new DijkstraShortestPath(graph, start, end, Comparator.comparingLong(EdgeAndDistance::getDist).reversed())
                .calculateWithWeightComparator(Comparator.reverseOrder());
    }

    private static boolean hasNoNegativeWeights(EdgeWeightedDiGraph graph) {
        Set<Edge> visited = new HashSet<>();

        Iterator<Node> iterator = graph.nodeIterator();
        while(iterator.hasNext()) {
            Node node = iterator.next();
            for (Edge edge : graph.getEdgesFrom(node)) {
                if (!visited.contains(edge)) {
                    if (edge.getWeight() < 0) return false;
                    visited.add(edge);
                }
            }
        }
        return true;
    }

    @Override
    protected Iterator<Node> getNodeIterator() {
        return new MutablePriorityQueueIterator(edgeQueue);
    }

    @Override
    protected void updateEdgeAndDistance(Node to, EdgeAndDistance distanceOld, EdgeAndDistance distanceNew) {
        super.updateEdgeAndDistance(to, distanceOld, distanceNew);
        this.edgeQueue.remove(distanceOld);
        this.edgeQueue.add(distanceNew);
    }

    @Override
    protected void markVisited(Node node) {
        super.markVisited(node);
        EdgeAndDistance edgeAndDistance = this.distanceMap.get(node);
        this.edgeQueue.remove(edgeAndDistance);
    }

    private static class MutablePriorityQueueIterator implements Iterator<Node> {

        private final PriorityQueue<EdgeAndDistance> queue;

        private MutablePriorityQueueIterator(PriorityQueue<EdgeAndDistance> queue) {
            this.queue = queue;
        }

        @Override
        public boolean hasNext() {
            return queue.peek() != null;
        }

        @Override
        public Node next() {
            return queue.remove().getEdge().getTo();
        }
    }
}
