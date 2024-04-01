import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
class Point {
    int y;
    int x;
    int move;
    public Point(int y, int x) {
        this.y = y;
        this.x = x;
        this.move = 0;
    }
     
    public Point(int y, int x, int move) {
        this.y = y;
        this.x = x;
        this.move = move;
    }
}
 
public class Solution {
     
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
     
    private static int N;
    private static Point[] points;
    private static int[] requireMoves;
 
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t=1; t<T+1; t++) {
            int answer = run();
            sb.append('#').append(t).append(' ').append(answer).append('\n');
        }
        System.out.println(sb);
    }
     
    private static int run() throws NumberFormatException, IOException {
        setInput();
         
        int maxMove = 0;
        for (int i=0; i<N; i++) {
            requireMoves[i] = getMoveCount(points[i].y, points[i].x);
            maxMove = Math.max(requireMoves[i], maxMove);
        }
 
        for (int i=0; i<N; i++) {
            if (requireMoves[i] % 2 == 0) {
                if ((maxMove - requireMoves[i]) % 4 == 1 || (maxMove - requireMoves[i]) % 4 == 2) {
                    return -1;
                }
            } else if (requireMoves[i] % 2 == 1) {
                if ((maxMove - requireMoves[i]) % 4 == 2 || (maxMove - requireMoves[i]) % 4 == 3) {
                    return -1;
                }
            }
        }
 
        return maxMove;
    }
     
    private static void setInput() throws NumberFormatException, IOException {
        N = Integer.parseInt(br.readLine());
        points = new Point[N];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(y, x);
        }
        requireMoves = new int[N];
    }
     
    private static int getMoveCount(int y, int x) {
         
        int move = 0;
        int distance = Math.abs(y) + Math.abs(x);
         
        while (distance != 0) {
            if (distance == move) {
                break;
            } else if (distance > move) {
                distance -= move;
            } else if (distance < move) {
                if ((move - distance) % 2 == 0) {
                    break;
                } else if (move % 2 == 0) {
                    return move + 1;
                } else if (move % 2 == 1) {
                    return move + 2;
                }
            }
            move++;
        }
         
        return move;
    }
 
}
