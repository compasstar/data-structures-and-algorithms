package algorithm.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ1018 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, M;
    private static char[][] board;


    public static void main(String[] args) throws Exception {
        setInput();
        int minNumber = 64;
        for (int j=0; j<N-7; j++) {
            for (int i=0; i<M-7; i++) {
                minNumber = Math.min(minNumber, getMinNumber(j, i));
            }
        }
        System.out.println(minNumber);
    }

    private static void setInput() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];

        for (int j = 0; j < N; j++) {
            String line = br.readLine();
            for (int i=0; i<line.length(); i++) {
                board[j][i] = line.charAt(i);
            }
        }
    }

    private static int getMinNumber(int y, int x) {
        int cntB = 0;
        for (int j=0; j<8; j++) {
            for (int i=0; i<8; i++) {
                if (j % 2 == 0 && i % 2 == 0 && board[y+j][x+i] == 'W') {
                    cntB++;
                } else if (j % 2 == 0 && i % 2 == 1 && board[y+j][x+i] == 'B') {
                    cntB++;
                } else if (j % 2 == 1 && i % 2 == 1 && board[y+j][x+i] == 'W') {
                    cntB++;
                } else if (j % 2 == 1 && i % 2 == 0 && board[y+j][x+i] == 'B') {
                    cntB++;
                }
            }
        }

        int cntW = 0;
        for (int j=0; j<8; j++) {
            for (int i=0; i<8; i++) {
                if (j % 2 == 0 && i % 2 == 0 && board[y+j][x+i] == 'B') {
                    cntW++;
                } else if (j % 2 == 0 && i % 2 == 1 && board[y+j][x+i] == 'W') {
                    cntW++;
                } else if (j % 2 == 1 && i % 2 == 1 && board[y+j][x+i] == 'B') {
                    cntW++;
                } else if (j % 2 == 1 && i % 2 == 0 && board[y+j][x+i] == 'W') {
                    cntW++;
                }
            }
        }

        return Math.min(cntB, cntW);
    }






}
