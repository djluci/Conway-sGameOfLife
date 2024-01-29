public class Grid {

    public static void main(String[] args) {
        for (String arg: args){
            System.out.println(arg);
        }

        if(args.length < 2) {
            System.out.println("At least 2 command line arguments needed");
        }
        String[][] grid = new String[3][5];

        grid = new String[3][];
        for(int i = 0; i < 3; i++){
            grid[i] = new String[i + 3];
        } 


    }
}
