import java.util.*;

class Kruskal {

	static int[] parent;

	static int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]); // 경로 압축
		}
		return parent[x];
	}

	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA == rootB) return;

		parent[rootB] = rootA;
	}

	static int kruskal(int v, List<int[]> edges) {
		// edges: {u, v, cost}
		Collections.sort(edges, (a, b) -> a[2] - b[2]);

		parent = new int[v + 1];
		for (int i = 1; i <= v; i++) {
			parent[i] = i;
		}

		int totalCost = 0;
		int count = 0;

		for (int[] edge : edges) {
			int u = edge[0];
			int w = edge[1];
			int cost = edge[2];

			if (find(u) != find(w)) {
				union(u, w);
				totalCost += cost;
				count++;

				if (count == v - 1) break;
			}
		}

		return totalCost;
	}
}
