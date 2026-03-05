import database.DBConnection;
import player.Player;
import question.Question;
import java.util.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of players: ");
        int n = sc.nextInt();
        sc.nextLine();

        Player[] players = new Player[n];

        for(int i = 0; i < n; i++) {
            System.out.print("Enter player name: ");
            players[i] = new Player(sc.nextLine());
        }

        System.out.println("\nPlayers entered:");

        for(int i = 0; i < n; i++) {
            System.out.println(players[i].getName());
        }

        List<Question> questionList = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM questions");



            while(rs.next()) {
                questionList.add(new Question(
                        rs.getInt("id"),
                        rs.getString("question"),
                        rs.getString("optionA"),
                        rs.getString("optionB"),
                        rs.getString("optionC"),
                        rs.getString("optionD"),
                        rs.getInt("correct_answer")
                ));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.shuffle(questionList);

        for (Question q : questionList) {

            System.out.println("\n" + q.getQuestionText());
            System.out.println("1. " + q.getOption1());
            System.out.println("2. " + q.getOption2());
            System.out.println("3. " + q.getOption3());
            System.out.println("4. " + q.getOption4());

            for (Player p : players) {

                System.out.print(p.getName() + ", enter your answer: ");
                int answer = sc.nextInt();

                if (q.checkAnswer(answer)) {
                    p.increaseScore();
                    System.out.println("Correct!");
                } else {
                    System.out.println("Wrong!");
                }
            }
        }

        System.out.println("\nFinal Scores:");

        for (Player p : players) {
            System.out.println(p.getName() + " : " + p.getScore());
        }



        int highestScore = 0;

        for (Player p : players) {
            if (p.getScore() > highestScore) {
                highestScore = p.getScore();
            }
        }

        System.out.println("\n🏆 Winner(s):");

        for (Player p : players) {
            if (p.getScore() == highestScore) {
                System.out.println(p.getName() + " with score: " + p.getScore());
            }
        }
    }
}