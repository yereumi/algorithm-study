import java.util.*;

class BellmanFord {

	static final int INF = Integer.MAX_VALUE;
	static int v;
	static List<int[]> edges; // {from, to, cost}
	static int[] dist;

	static boolean bellmanFord(int start) {
		Arrays.fill(dist, INF);
		dist[start] = 0;

		// V-1번 모든 간선 완화
		for (int i = 1; i <= v - 1; i++) {
			for (int[] edge : edges) {
				int from = edge[0];
				int to = edge[1];
				int cost = edge[2];

				if (dist[from] == INF) continue;

				if (dist[to] > dist[from] + cost) {
					dist[to] = dist[from] + cost;
				}
			}
		}

		// 음수 사이클 검사
		for (int[] edge : edges) {
			int from = edge[0];
			int to = edge[1];
			int cost = edge[2];

			if (dist[from] == INF) continue;

			if (dist[to] > dist[from] + cost) {
				return true; // 음수 사이클 존재
			}
		}

		return false;
	}
}
