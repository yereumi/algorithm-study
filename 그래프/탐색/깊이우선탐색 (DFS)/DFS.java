import java.util.*;

class DFS {

    static List<Integer>[] graph;
    static boolean[] visited;

    // 재귀를 이용한 DFS
    static void recursiveDfs(int cur) {
        visited[cur] = true;

        for (int next : graph[cur]) {
            if (visited[next]) continue;

            recursiveDfs(next);
        }
    }

    // Stack을 이용한 반복문 DFS
    static void iterativeDfs(int start) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int cur = stack.pop();

            if (visited[cur]) continue;

            visited[cur] = true;

            for (int next : graph[cur]) {
                if (visited[next]) continue;

                stack.push(next);
            }
        }
    }
}
