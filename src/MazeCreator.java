import java.util.*;

public class MazeCreator {
    private static int width;
    private static int height;
    private static int[][] maze;
    private Random rand;
    public static int[][] getMaze() {
        return maze;
    }
    public static int getHeight() {
        return height;
    }
    public static int getWidth() {
        return width;
    }
    public MazeCreator(int width, int height) {
        this.width = width;
        this.height = height;
        maze = new int[height+1][width+1];
        rand = new Random();
        for (int i = 0; i < height ;i++) {
            for (int j = 0; j <width; j++) {
                maze[i][j]=1;
            }
        }
        generateMaze();
        for(int i=0; i<width+1;i++){
            maze[height][i]=1;
        }
        for(int i=0; i<height+1;i++){
            maze[i][width]=1;
        }
    }

    private void generateMaze() {
        int startX =1;
        int startY=1;
        maze[startY][startX] = 0;
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{startY, startX});
        while (!stack.isEmpty()) {
            int[] current = stack.peek();
            int currentY = current[0];
            int currentX = current[1];
            List<int[]> komsular = ziyaretEdilmeyenKomsuBul(currentX, currentY);
            if (!komsular.isEmpty()) {
                // Rastgele komşu seç
                int[] neighbor = komsular.get(rand.nextInt(komsular.size()));
                int neighborY = neighbor[0];
                int neighborX = neighbor[1];
                // Seçili komşuyla aradaki duvarı kaldır.
                int wallY = (currentY + neighborY) / 2;
                int wallX = (currentX + neighborX) / 2;
                maze[wallY][wallX] = 0;
                maze[neighborY][neighborX] = 0;
                // Stack'e komşu ekle
                stack.push(neighbor);
            } else {
                // Backtrack if there are no unvisited komsular
                stack.pop();
            }
        }
    }
    private List<int[]> ziyaretEdilmeyenKomsuBul(int x, int y) {
        List<int[]> neighbors = new ArrayList<>();

        // Yukarıdaki komşuya bak
        if (y > 1 && maze[y - 2][x] == 1) {
            neighbors.add(new int[]{y - 2, x});
        }
        // Aşağıdaki komşuya bak
        if (y < height - 2 && maze[y + 2][x] == 1) {
            neighbors.add(new int[]{y + 2, x});
        }
        // soldaki komşuya bak
        if (x > 1 && maze[y][x - 2] == 1) {
            neighbors.add(new int[]{y, x - 2});
        }
        // sağdaki komşuya bak
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
    public static void main(int width,int height) {

        MazeCreator mazeCreator = new MazeCreator(width, height);
        mazeCreator.printMaze();
    }
}