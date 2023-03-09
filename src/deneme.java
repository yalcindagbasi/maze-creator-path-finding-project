import java.util.*;

public class deneme {
    private int width;
    private int height;
    private static int[][] maze;
    private Random rand;

    public static int[][] getMaze() {
        return maze;
    }

    public deneme(int width, int height) {
        this.width = width;
        this.height = height;
        maze = new int[height+1][width+1];
        rand = new Random();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j <height; j++) {
                maze[i][j]=1;
            }
        }
        generateMaze();
        for(int i=0; i<width;i++){
            maze[height][i]=1;
        }
        for(int i=0; i<height;i++){
            maze[i][width]=1;
        }
    }

    private void generateMaze() {
        // Start at a random cell
        //int startX = rand.nextInt(width);
        //int startY = rand.nextInt(height);
        int startX =1;
        int startY=1;
        maze[startY][startX] = 0;

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{startY, startX});

        while (!stack.isEmpty()) {
            int[] current = stack.peek();
            int currentY = current[0];
            int currentX = current[1];

            // Get unvisited neighbors
            List<int[]> neighbors = getUnvisitedNeighbors(currentX, currentY);

            if (!neighbors.isEmpty()) {
                // Choose a random neighbor
                int[] neighbor = neighbors.get(rand.nextInt(neighbors.size()));
                int neighborY = neighbor[0];
                int neighborX = neighbor[1];

                // Remove the wall between the current and the chosen neighbor
                int wallY = (currentY + neighborY) / 2;
                int wallX = (currentX + neighborX) / 2;
                maze[wallY][wallX] = 0;
                maze[neighborY][neighborX] = 0;

                // Add the neighbor to the stack
                stack.push(neighbor);
            } else {
                // Backtrack if there are no unvisited neighbors
                stack.pop();
            }
        }
    }

    private List<int[]> getUnvisitedNeighbors(int x, int y) {
        List<int[]> neighbors = new ArrayList<>();

        // Check north neighbor
        if (y > 1 && maze[y - 2][x] == 1) {
            neighbors.add(new int[]{y - 2, x});
        }
        // Check south neighbor
        if (y < height - 2 && maze[y + 2][x] == 1) {
            neighbors.add(new int[]{y + 2, x});
        }
        // Check west neighbor
        if (x > 1 && maze[y][x - 2] == 1) {
            neighbors.add(new int[]{y, x - 2});
        }
        // Check east neighbor
        if (x < width - 2 && maze[y][x + 2] == 1) {
            neighbors.add(new int[]{y, x + 2});
        }

        return neighbors;
    }

    public void printMaze() {
        for (int[] row : maze) {
            for (int cell : row) {
                System.out.print(cell == 1 ? "+ " : "0 ");
            }
            System.out.println();
        }
    }

    public static void main() {

        MazeCreator mazeCreator = new MazeCreator(30, 30);
        mazeCreator.printMaze();
    }
}