public class RandomWalk { 

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        StdDraw.setXscale(-N, +N);
        StdDraw.setYscale(-N, +N);
        StdDraw.clear(StdDraw.GRAY);

        int x = 0, y = 0;
        int steps = 0;
        while (Math.abs(x) < N && Math.abs(y) < N) {
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledSquare(x, y, 0.45);
            double r = Math.random();
            if      (r < 0.25) x--;
            else if (r < 0.50) x++;
            else if (r < 0.75) y--;
            else if (r < 1.00) y++;
            steps++;
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledSquare(x, y, .45);
            StdDraw.show(40);
        }
        System.out.println("Total steps = " + steps);
    }

}

