import java.util.*;

class UnionFind {

	static int[] parent;
	static int[] rank;

	// 초기화
	static void init() {
		parent = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}

		rank = new int[n + 1];
	}

	// 대표 (root) 찾기
	static int find(int x) {
		if (parent[x] == x) return x;
		return find(parent[x]);
	}

	// 경로 압축
	static int pathCompressionFind(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}

	// 집합 합치기
	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA == rootB) return; // 이미 같은 집합

		parent[rootB] = rootA;
	}

	// 랭크/크기 기반 집합 합치기
	static void rankUnion(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA == rootB) return;

		if (rank[rootA] < rank[rootB]) {
			parent[rootA] = rootB;
		} else {
			parent[rootB] = rootA;
			if (rank[rootA] == rank[rootB]) {
				rank[rootA]++;
			}
		}
	}
}
