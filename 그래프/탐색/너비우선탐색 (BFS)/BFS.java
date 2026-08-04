import java.util.*;

class BFS {

    static int N;
    static int M;

	static List<List<Integer>> graph;
    static int[][] map;

    // 상하좌우
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    /**
     * 2차원 배열 범위 확인
     */
    static boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    /**
     * 1. 기본 그래프 BFS
     *
     * 시작 정점에서 도달할 수 있는 모든 정점을 탐색한다.
     */
    static void bfs(int vertexCount, int start) {
        boolean[] visited = new boolean[vertexCount + 1];
        Deque<Integer> dq = new ArrayDeque<>();
        visited[start] = true;
        dq.offer(start);

        while (!dq.isEmpty()) {
            int cur = dq.poll();

            for (int next : graph.get(cur)) {
                if (visited[next]) continue;

                visited[next] = true;
                dq.offer(next);
            }
        }
    }

    /**
     * 2. 2차원 배열 기본 BFS
     *
     * map[r][c] == 0: 이동 불가능
     * map[r][c] == 1: 이동 가능
     */
    static void bfs2D(int startR, int startC) {
        boolean[][] visited = new boolean[N][M];
        Deque<int[]> dq = new ArrayDeque<>();

        if (!isValid(startR, startC)) return;
        if (map[startR][startC] == 0) return;

        visited[startR][startC] = true;
        dq.offer(new int[] { startR, startC });

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();

            int r = cur[0];
            int c = cur[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (!isValid(nr, nc) || visited[nr][nc] || map[nr][nc] == 0) continue;

                visited[nr][nc] = true;
                dq.offer(new int[] { nr, nc });
            }
        }
    }

}
