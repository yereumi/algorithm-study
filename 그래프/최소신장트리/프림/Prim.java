import java.util.*;

class Prim {

	static List<List<int[]>> graph; // graph[u] = {v, cost}

	static int prim(int v, int start) {
		boolean[] visited = new boolean[v + 1];

		// {정점, 비용}
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

		pq.offer(new int[] { start, 0 });

		int totalCost = 0;
		int count = 0;

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int u = cur[0];
			int cost = cur[1];

			if (visited[u]) continue;

			visited[u] = true;
			totalCost += cost;
			count++;

			if (count == v) break;

			for (int[] next : graph.get(u)) {
				int nv = next[0];
				int nw = next[1];

				if (!visited[nv]) {
					pq.offer(new int[] { nv, nw });
				}
			}
		}

		return totalCost;
	}
}
