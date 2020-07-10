package graph.dfs;

import java.util.List;

/**
 * Title: 841. 钥匙和房间
 * Desc:
 * Created by Myth-Lab on 10/23/2019
 */
public class P841KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        visited[0] = true;
        dfs(rooms, 0, visited);
        for (boolean visit : visited) {
            if (!visit) {
                return false;
            }
        }
        return true;
    }
    private void dfs(List<List<Integer>> rooms, int i, boolean[] visited) {
        for (Integer key : rooms.get(i)) {
            if (!visited[key]) {
                visited[key] = true;
                dfs(rooms, key, visited);
            }
        }
    }
}
