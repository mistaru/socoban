import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyFileReader {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File(args[0]));
        List<int[]> data = new ArrayList<>();


        while (in.hasNextLine()) {
            
            String strLine = in.nextLine().replaceAll("[^0-4]", "");

            if(strLine.isEmpty())
                continue;

            int[] _line = map(strLine.toCharArray());

            data.add(_line);
        }

        int[][] res = data.toArray(new int[data.size()][]);

        print(res);
    }

    private static int[] map(char[] line) {
        int[] _line = new int[line.length];
        for (int i = 0; i < line.length; i++)
            _line[i] = line[i] - '0';

        return _line;
    }

    private static void print(int[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++)
                System.out.print(input[i][j]);
            System.out.println();
        }
    }

}
