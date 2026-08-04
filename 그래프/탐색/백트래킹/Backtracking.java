import java.util.*;

class Backtracking {

    static int N;
    static int M;

    static int[] result;
    static boolean[] visited;

    static void backtracking(int depth) {
        if (depth == M) {
            // 정답 처리
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;

            // 가지치기
            if (!isPossible(i)) continue;

            visited[i] = true;
            result[depth] = i;

            backtracking(depth + 1);

            visited[i] = false;
        }
    }
}
