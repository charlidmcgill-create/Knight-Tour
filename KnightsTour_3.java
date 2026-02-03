package HW02;

public class KnightsTour_3{
   //create chessboard
    static int[][] results = new int[8][8];
    int[][] chessboard = new int[8][8];
    int[][] accessibility = {
        {2,3,4,4,4,4,3,2},
        {3,4,6,6,6,6,4,3},
        {4,6,8,8,8,8,6,4},
        {4,6,8,8,8,8,6,4},
        {4,6,8,8,8,8,6,4},
        {4,6,8,8,8,8,6,4},
        {3,4,6,6,6,6,4,3},
        {2,3,4,4,4,4,3,2}
    };

    public KnightsTour_3() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessboard[i][j] = 0;
            }
        }
    }

    public void printResults(int fullTours) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.printf("%3d ", results[i][j]);
            }
            System.out.println();
            
        }
        System.out.println("Number of full tours: " + fullTours);
    }

    private int countAccessibility(int x, int y) {
        int[] dx = {-2,-1,1,2,2,1,-1,-2};
        int[] dy = {1,2,2,1,-1,-2,-2,-1};
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < 8 && ny >= 0 && ny < 8 && chessboard[nx][ny] == 0) {
                count++;
            }
        }
        return count;
    }

    public int runTour(int startX, int startY) {
        // reset board for this tour
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessboard[i][j] = 0;
            }
        }

        int x = startX;
        int y = startY;

        // mark starting square and initialize moveCount
        int moveCount = 1;
        chessboard[x][y] = moveCount;

        // knight move offsets
        int[] dx = {-2,-1,1,2,2,1,-1,-2};
        int[] dy = {1,2,2,1,-1,-2,-2,-1};

        // perform up to 64 moves (including start). stop when no valid moves.
        while (moveCount < 64) {
            int bestDir = -1;
            int bestAccessibility = 9; // higher than max possible accessibility

            for (int dir = 0; dir < 8; dir++) {
                int newX = x + dx[dir];
                int newY = y + dy[dir];
                if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8 && chessboard[newX][newY] == 0) {
                    int accessibility = countAccessibility(newX, newY);
                    if (accessibility < bestAccessibility) {
                        bestAccessibility = accessibility;
                        bestDir = dir; 
                    }
                }
            }
            if(bestDir == -1) {
                break; // no valid moves
            }
            x += dx[bestDir];
            y += dy[bestDir];
            moveCount++;
            chessboard[x][y] = moveCount;
        }
        return moveCount;
    }

    public static void main(String[] args) {
        int[] tourResults = new int[64];
        int fullTours = 0;
        KnightsTour_3 kt = new KnightsTour_3();

        // run knight's tour from each of 64 starting positions
        for (int startX = 0; startX < 8; startX++) {
            for (int startY = 0; startY < 8; startY++) {
                KnightsTour_3 kt3 = new KnightsTour_3();
                int moveCount = kt3.runTour(startX, startY);
                tourResults[startX * 8 + startY] = moveCount;
                if (moveCount == 64) {
                    fullTours++;
                }
                results[startX][startY] = moveCount;
            }
        }

        kt.printResults(fullTours);
    }
}