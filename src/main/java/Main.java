
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);

        //1. Load and register driver
        //Name of mysql driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2. Establish the connection with the database
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket", "root","root");

        //3. Create the statement object - CAR
        Statement st = con.createStatement();

        boolean flag = true;
        while(flag){
            System.out.println("Enter the choice: ");
            System.out.println("1. View the scoreboard");
            System.out.println("2. Insert the new record");
            System.out.println("3. Update the record");
            System.out.println("4. Delete the record");
            System.out.println("5. Exit");

            int choice = sc.nextInt();
            switch(choice){
                    //VIEW
                case 1 :
                    viewTale(st);
                    break;
                case 2 :
                    //INSERT
                    insertTable(st, sc);
                    break;
                case 3 :
                    //UPDATE
                    updateTable(st, sc);
                    break;
                case 4 :
                    //DELETE sccoretable where id = 1;
                    deleteTable(st,sc);
                    break;
                default:
                    flag = false;
                    break;
            }
        }
    }
    public static void viewTale(Statement st) throws Exception{
        String sql = "select * from scoretable";
        ResultSet rs = st.executeQuery(sql);
        System.out.println("-----------------------------------");

        System.out.println("ID\t| RUNS\t| BALLS\t");
        while(rs.next())
            System.out.println(rs.getInt(1)+"\t"+
                    rs.getString(2)+"\t"+
                    rs.getInt(3)+"\t"+
                    rs.getInt(4));
        System.out.println("-----------------------------------");
    }
    public static void insertTable(Statement st, Scanner sc) throws Exception{
        System.out.println("Enter the id :");
        int id = sc.nextInt();
        System.out.println("Enter the name of the player: ");
        String name = sc.next();
        System.out.println("Enter the runs score: ");
        int runs = sc.nextInt();
        System.out.println("I hope many balls ??? ");
        int balls = sc.nextInt();

        String insertQuery = "INSERT INTO scoretable (id, name, runs, balls) VALUES (" + id + ", '" + name + "', " + runs + ", " + balls + ")";
        int rows = st.executeUpdate(insertQuery);

        System.out.println(rows+" rows inserted");
        System.out.println("-----------------------------------");
    }
    public static void updateTable(Statement st, Scanner sc) throws Exception{
        System.out.println("Enter the id of the player: ");
        int id = sc.nextInt();
        System.out.println("Enter new runs: ");
        int runs = sc.nextInt();
        System.out.println("In how many balls ?");
        int balls = sc.nextInt();

        String updateQuery = "UPDATE scoretable set runs = "+runs+", balls = "+balls+" WHERE id = "+id+";";

        int rows = st.executeUpdate(updateQuery);
        System.out.println(rows+" rows updated");
        System.out.println("-----------------------------------");
    }
    public static void deleteTable(Statement st, Scanner sc) throws Exception{
        System.out.println("Enter the id of the player: ");
        int id = sc.nextInt();

        String deleteQuery = "DELETE from scoretable where id="+id+";";

        int rows = st.executeUpdate(deleteQuery);
        System.out.println(rows+" rows deleted ");
        System.out.println("-----------------------------------");
    }
 }
