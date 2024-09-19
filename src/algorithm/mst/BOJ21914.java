package algorithm.mst;

import java.util.*;
import java.io.*;



public class BOJ21914 {

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, M;
    private static PriorityQueue<Edge> edges;

    private static int[] parents;
    private static long totalWeight;

    public static void main(String[] args) throws Exception {
        setInput();
        long minWeight = getMinWeight();

        if (!isUnity()) {
            System.out.println(-1);
            return;
        }
        System.out.println(totalWeight - minWeight);
    }

    private static void setInput() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new PriorityQueue<>();

        parents = new int[N+1];
        for (int i=1; i<N+1; i++) {
            parents[i] = i;
        }
        totalWeight = 0;

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            totalWeight += c;
            edges.add(new Edge(a, b, c));
            edges.add(new Edge(b, a, c));
        }
    }

    private static long getMinWeight() {
        long minWeight = 0;
        while (!edges.isEmpty()) {
            Edge nowEdge = edges.poll();
            if (find(nowEdge.from) == find(nowEdge.to)) {
                continue;
            }
            union(nowEdge.from, nowEdge.to);
            minWeight += nowEdge.weight;
        }
        return minWeight;
    }

    private static boolean isUnity() {
        int p = find(1);
        for (int i=1; i<N+1; i++) {
            if (p != find(i)) {
                return false;
            }
        }
        return true;
    }


    private static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return find(parents[x]);
    }

    private static void union(int y, int x) {
        y = find(y);
        x = find(x);
        if (y < x) {
            parents[x] = y;
        } else if (y > x) {
            parents[y] = x;
        }
    }



}
