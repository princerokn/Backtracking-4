// Time Complexity : O((n^(H*W)*(H*W)) where n = no. of building, H = height of grid, W = Width of grid
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
// Find all the places where we can place the buidings using backtacking
// When we have placed all buidings call BFS to find the max distance
// Pick the min and return
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
class GFG {
	public static void main (String[] args) {
		BuildingPlacement buildingPlacement = new BuildingPlacement();
        System.out.print(buildingPlacement.findMinDistance(5, 4, 3)); 
	}
	public static class BuildingPlacement {
	   int minDistance = Integer.MAX_VALUE;
	   public int findMinDistance(int H, int W, int n){
	       int [][] grid = new int[H][W];
	       for(int i = 0; i < H; i++){
	           for(int j = 0; j < W; j++){
	               grid[i][j] = -1;
	           }
	       }
	       backtrack(grid,0, 0, n, H, W);
	       return minDistance;
	   }
	   // breadth first for finding minDistance of each combination
	   private void bfs(int [][] grid, int H, int W){
	       Queue<int []> q = new LinkedList<>(); 
	       boolean [][] visited = new boolean[H][W];
	       for(int i = 0; i < H; i++){
	           for(int j = 0; j < W; j++){
	               if(grid[i][j] == 0){
	                   q.add(new int[] {i,j});
	                   visited[i][j] = true;
	               }
	           }
	       }
	       // now we have all buildings in queue
	       int [][] dirs = {{0,1}, {0,-1}, {1, 0}, {-1, 0}};
	       int dist = 0;
	       while(!q.isEmpty()){
	          int size = q.size();
	          for(int k = 0; k < size; k++){
	              int [] curr = q.poll();
	              for(int [] dir: dirs){
	                  int r = dir[0] + curr[0];
	                  int c = dir[1] + curr[1];
	                  if(r >= 0 && r < H && c >= 0 && c < W && !visited[r][c]){
	                     q.add(new int[] {r,c});
	                     visited[r][c] = true;
	                  }
	              }
	          }
	          dist++;
	       }
	       // in end dist is farthest distance of my parking lot
	       minDistance = Math.min(minDistance, dist - 1);
	   }
	   private void backtrack(int [][] grid, int r, int c, int n, int H, int W){
	       // base
	        if(n == 0){
	           bfs(grid, H, W);
	           return;
	       }
	       // logic
	       if(c >= W){
	           r++; c=0;
	       }
	       for(int i = r; i < H; i++){
	           for(int j = c; j < W; j++){
	               //action
	               grid[i][j] = 0; //put the building
	               // recurse
	               backtrack(grid, i, j + 1, n - 1, H, W);
	               // backtrack
	               grid[i][j] = -1;
	           }
	           c = 0;
	       }
	   }
	}
}

