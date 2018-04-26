import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Formula {

    public static void main(String[] args) {
       int numberOfVariables;
       Formula generateDST = new Formula();
       Scanner S = new Scanner(System.in);

       System.out.print("Enter the number of literals you wish to use: ");
       numberOfVariables = S.nextInt();

       int [] index = new int [numberOfVariables];
       int [] vc = new int [numberOfVariables];
       int [] dc = new int [numberOfVariables];

       for (int i = 0; i < numberOfVariables; i++) {
           index[i] = i+1;
           vc[i] = i+1;
           dc[i] = 1;
       }

       //A DST has to have at least 3 variables
       if (numberOfVariables < 3) {
           System.out.println("Not enough literals!");
       }
       else {
           ArrayList<String> sets;
           //Array of variables
           sets = generateDST.nChoose3(numberOfVariables);
           System.out.println(Arrays.toString(sets.toArray()) + " <- Literal combinations");

           ArrayList<String> binaries;
           //Array of binary permutations
           binaries = generateDST.binaryPerms();
           System.out.println(Arrays.toString(binaries.toArray()) + " <- Binary combinations");

           System.out.println();
           System.out.println("----------------------------------------"); //perhaps make this spacer an identifier for future fead from files

           for (int i = 0; i < sets.size(); i++) {

               //String formatting to get the output looking nice
               String splitLiteral [] = sets.get(i).split("\\|");
               String manipulateBinaryString = binaries.toString();
               manipulateBinaryString = manipulateBinaryString.replaceAll("\\[|\\]|\\,", "");
               String explosion [] = manipulateBinaryString.split(" ");

               for (int j = 0; j < (binaries.size() / 2) ; j++) {
                   String explodeBinaries [] = explosion[j].split("\\|");

                   for (int k = 0; k < 3; k++) {
                       System.out.println(splitLiteral[k] + ", " + splitLiteral[k] + ", " + explodeBinaries[k]);  //first set of DST
                   }

                   System.out.println();
                   for (int k = 0; k < 3; k++) {
                       generateDST.xOrFunction(splitLiteral[k], explodeBinaries[k]);  //inverted set of DST
                   }
                   System.out.println("----------------------------------------");
               }
           }
       }
    }

    // calculates all combinations of 3 from n number of inputs
    public ArrayList<String> nChoose3 (int n) {
        ArrayList<String> chosen = new ArrayList<String>();
        int count = 0;
        for (int i = 1; i <= n - 2; i++) {
            for (int j = 2; j <= n - 1; j++) {
                for ( int k = 3; k <= n; k++){
                    if (i < j && i < k && j < k) {
                        chosen.add(i + "|" + j + "|" + k); // "|" used for future separation
                        count++;
                    }
                }
            }
        }
        System.out.println("Number of Unique DST's  = " + count * 4);  //4 unique binary combinations for every literal combination
        return chosen;
    }

    // gets all permutations of 000 - 111
    public ArrayList<String> binaryPerms () {
        ArrayList<String> permutations = new ArrayList<String>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    permutations.add(i + "|" + j + "|" + k); // "|" used for future separation
                }
            }
        }
        return permutations;
    }

    // used to negate the given DST half
    public void xOrFunction (String a, String b) {
        if (b.equals("0")) {
            System.out.println(a + ", " + a + ", " + "1");
        } else
            System.out.println(a + ", " + a + ", " + "0");
    }
    
}
