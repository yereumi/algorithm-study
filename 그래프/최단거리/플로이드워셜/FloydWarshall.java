import java.util.*;

class FloydWarshall {

    static final int INF = 1_000_000_000;
    static int V;
    static int[][] dist;

    static void floydWarshall() {
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if (dist[i][k] == INF || dist[k][j] == INF) continue;

                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }
}
