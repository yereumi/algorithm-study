import java.util.*;

class TopologicalSort {

	static int V;
	static List<List<Integer>> graph;
	static int[] indegree;

	static List<Integer> topoSort() {
		Queue<Integer> q = new LinkedList<>();
		List<Integer> result = new ArrayList<>();

		// 진입차수 0인 정점 큐에 삽입
		for (int i = 1; i <= V; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
			}
		}

		// BFS 진행
		while (!q.isEmpty()) {
			int cur = q.poll();
			result.add(cur);

			for (int next : graph.get(cur)) {
				indegree[next]--;
				if (indegree[next] == 0) {
					q.offer(next);
				}
			}
		}

		// 사이클 판별
		if (result.size() != V) {
			return null; // 사이클 존재
		}

		return result;
	}
}
