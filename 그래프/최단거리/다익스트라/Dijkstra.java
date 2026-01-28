import java.util.*;

class Dijkstra {

	static final int INF = Integer.MAX_VALUE;
	static List<List<int[]>> graph;
	static int[] dist;

	static void dijkstra(int start) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(
				(o1, o2) -> o1[1] - o2[1]
		);

		dist[start] = 0;
		pq.offer(new int[] { start, 0 });

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int curNode = cur[0];
			int curDist = cur[1];

			if (curDist > dist[curNode]) continue;

			for (int[] next : graph.get(curNode)) {
				int nextDist = curDist + next[1];

				if (dist[next[0]] > nextDist) {
					dist[next[0]] = nextDist;
					pq.offer(new int[] { next[0], nextDist });
				}
			}
		}
	}
}
