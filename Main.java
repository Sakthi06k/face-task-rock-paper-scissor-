import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {  
    File f = new File("./db.txt");
    int high = 0;
      try {
          String s = "";
          BufferedReader br = new BufferedReader(new FileReader(f));
          while((s = br.readLine()) != null){
              String a[] = new String[2];
              a = s.split(",");
              int score = parseInt(a[1]);
                if(score > high){
                    high = score;
                }
          }
      } catch (FileNotFoundException ex) {
          Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
          Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
      }
    Scanner scanner = new Scanner(System.in);
    System.out.println("enter your name");
    String name = scanner.next();
    int score = 0;
    while (true) {
      String[] rps = {"r", "p", "s"};
      String computerMove = rps[new Random().nextInt(rps.length)];
      
      String playerMove;
      
      while(true) {
          System.out.print("heighest score : "+high+" ");
        System.out.println("Please enter your move (r, p, or s)");
        playerMove = scanner.nextLine();
        if (playerMove.equals("r") || playerMove.equals("p") || playerMove.equals("s")) {
          break;
        }
        else{
            System.out.println(playerMove + " is not a valid move.");
        }
      }
      
      System.out.println("Computer played: " + computerMove);
      
      if (playerMove.equals(computerMove)) {
        System.out.println("The game was a tie!");
      }
      else if (playerMove.equals("r")) {
        if (computerMove.equals("p")) {
          System.out.println("You lose!");
          
        } else if (computerMove.equals("s")) {
          System.out.println("You win!");
          score++;
        }
      }
      
      else if (playerMove.equals("p")) {
        if (computerMove.equals("r")) {
          System.out.println("You win!");
          score++;
          
        } else if (computerMove.equals("s")) {
          System.out.println("You lose!");
        }
      }
      
      else if (playerMove.equals("s")) {
        if (computerMove.equals("p")) {
          System.out.println("You win!");
          score++;
          
        } else if (computerMove.equals("r")) {
          System.out.println("You lose!");
        }
      }
      
      System.out.println("Play again? (y/n)");
      String playAgain = scanner.nextLine();
      
      if (!playAgain.equals("y")) {
          System.out.println(name+" Your Score : "+score);
          try{
                PrintWriter pw = new PrintWriter(new FileOutputStream(f,true));
                pw.append(name+","+score);
                pw.close();
          }
          catch(FileNotFoundException e){
              System.out.println(e);
          }
        break;
      }
    }
    scanner.close();
  }
}
