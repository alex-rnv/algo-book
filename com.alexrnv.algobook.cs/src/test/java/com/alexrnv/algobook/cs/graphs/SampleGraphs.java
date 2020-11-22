package com.alexrnv.algobook.cs.graphs;

import java.util.Iterator;

public class SampleGraphs {

    private static int[][] matrix() {
        int[][] r = new int[5][5];
        r[0][0] = 131; r[0][1] = 673; r[0][2] = 234; r[0][3] = 103; r[0][4] =  18;
        r[1][0] = 201; r[1][1] =  96; r[1][2] = 342; r[1][3] = 965; r[1][4] = 150;
        r[2][0] = 630; r[2][1] = 803; r[2][2] = 746; r[2][3] = 422; r[2][4] = 111;
        r[3][0] = 537; r[3][1] = 699; r[3][2] = 497; r[3][3] = 121; r[3][4] = 956;
        r[4][0] = 805; r[4][1] = 732; r[4][2] = 524; r[4][3] =  37; r[4][4] = 331;
        return r;
    }

    public static EdgeWeightedDiGraph sampleDAG() {
        return new RightDownGraph(matrix());
    }

    public static EdgeWeightedDiGraph sampleDirectedGraphWithNoNegativeWeights() {
        return new RightUpDownGraph(matrix());
    }

    public static Graph.Node getStart(EdgeWeightedDiGraph graph) {
        return graph.nodeIterator().next();
    }

    public static Graph.Node getEnd(EdgeWeightedDiGraph graph) {
        Iterator<Graph.Node> nodeIterator = graph.nodeIterator();
        Graph.Node node = nodeIterator.next();
        while (nodeIterator.hasNext()) {
            node = nodeIterator.next();
        }
        return node;
    }

    private static class RightDownGraph extends EdgeWeightedDiGraph {

        protected RightDownGraph(int[][] input) {
            super(input.length * input[0].length);
            build(input);
        }

        private void build(int[][] input) {
            int h = input.length;
            for (int i=0; i<h; i++) {
                for (int j=0; j<input[0].length; j++) {
                    int current = i*h+j + 1;
                    int w = input[i][j];
                    Node node = new Node(w);
                    this.addNode(current, node);
                    if (j > 0) {
                        Node left = this.getNode(current - 1);
                        this.addEdge(left, node, w);
                    }
                    if (i > 0) {
                        Node up = this.getNode(current - h);
                        this.addEdge(up, node, w);
                    }
                }
            }
        }
    }

    private static class RightUpDownGraph extends EdgeWeightedDiGraph {

        private Node start, end;

        public RightUpDownGraph(int[][] input) {
            super(input.length * input[0].length);
            build(input);
        }

        private void build(int[][] input) {
            int h = input.length;
            for (int i=0; i<h; i++) {
                for (int j=0; j<input[0].length; j++) {
                    int current = i*h+j + 1;
                    int w = input[i][j];
                    Node node = new Node(w);
                    this.addNode(current, node);
                    if (j > 0) {
                        Node left = this.getNode(current - 1);
                        this.addEdge(left, node, w);
                    }
                    if (i > 0) {
                        Node up = this.getNode(current - h);
                        this.addEdge(up, node, w);
                        this.addEdge(node, up, ((int) up.getValue()));
                    }
                }
            }

            start = new Node(0);
            this.addNode(start);

            for (int i=0; i<h; i++) {
                Node left = this.getNode(h*i+1);
                this.addEdge(start, left, (int)left.getValue());
            }

            end = new Node(0);
            this.addNode(end);

            for (int i=1; i<=h; i++) {
                Node right = this.getNode(h*i);
                this.addEdge(right, end, 0);
            }
        }
    }
}
