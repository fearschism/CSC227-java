import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
public class Main {
    //Creating Global Variables to us to use with the static methods of read and main.
    public static ArrayList<Integer> time;
    public static ArrayList<Integer> prio;
    public static ArrayList<String> name;
    public static URL path;
    public static File f;
    public static Scanner s;
    public static Process Priority[];
    public static srtfProcess srtf[];
    public static rrProcess rr[];
    public static Scanner input;
    public static int choice;
    public static String p;
    public static Cpu services;
    public static Path dir;
    public static void main(String[] args) {
        input = new Scanner(System.in);
        boolean loop = true;
        Path o = Paths.get("src/Priorty/testcase.txt");
        Path o1 = Paths.get("src/RR/testdata21.TXT");
        System.out.println("Welcome to CSC227 (CPU SCHEDULING PROGRAM(CLI))");
        while (loop) {
        System.out.println("Please choose your Desired Service below:");
        System.out.println("1- Priority");
        System.out.println("2- RR");
        System.out.println("3- SRTF");
        //System.out.println("4- Enter Your processes manually");
        System.out.println("4- Quit");
            System.out.println("------------------------------------------------------------");
            while (!input.hasNextInt()) { //validating users input
                System.out.println("That's not a number!");
                input.next();
            }
            choice = input.nextInt();



            switch (choice) {
                case 1:
                    System.out.println("Please enter your File name that is stored in the Priority FOLDER(Example:'FILENAME.txt')");
                    p = input.next().trim();
                    dir = Paths.get("src/Priorty/" + p);
                    if (read_priority(dir.toString())) {
                        services = new Cpu();
                        Priority = new Process[name.size()];
                        for (int i = 0; i < name.size(); i++) {
                            Priority[i] = new Process(name.get(i), time.get(i), prio.get(i));
                        }
                        services.priorityScheduling(Priority, name.size());
                    }
                    break;
                case 2:
                    System.out.println("Please enter your File name that is stored in the RR FOLDER(Example:'FILENAME.txt')");
                    p = input.next().trim();
                    dir = Paths.get("src/RR/" + p);
                    if (read_RR(dir.toString())) {
                        services = new Cpu();
                        rr = new rrProcess[name.size()];
                        for (int i = 0; i < name.size(); i++) {
                            rr[i] = new rrProcess(name.get(i), time.get(i));
                        }
                        services.RRscheduling(rr, name.size());
                    }
                    break;
                case 3:
                    System.out.println("Please enter your File name that is stored in the SRTF FOLDER(Example:'FILENAME.txt')");
                    p = input.next().trim();
                    dir = Paths.get("src/RR/" + p);
                    if (read_RR(dir.toString())) {
                        services = new Cpu();
                        srtf = new srtfProcess[name.size()];
                        for (int i = 0; i < name.size(); i++) {
                            srtf[i] = new srtfProcess(name.get(i), time.get(i));
                        }
                        services.srtfscheduling(srtf, name.size());
                    }
                    break;
                case 4:
                    loop = false;
                    break;
                default:
                    System.out.println("Please Enter the right number");
            }

        }
        System.out.println("GoodBye :)");
        }

    //Reader from file to ArrayList
    public static boolean read_priority(String pp) {
        try {
            //path = ClassLoader.getSystemResource(pp);
            f = new File(pp);
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!");
            return false;
        }
        Main.name = new ArrayList<>();
        Main.time = new ArrayList<>();
        Main.prio = new ArrayList<>();
        while (s.hasNextLine()) {
           /* if(s.hasNextInt()) {
                System.out.println("has int");
                String a = s.nextLine();
                String holder[] = a.split(", ");
                for( int i = 0 ; i < holder.length ; i++){
                    time.add(Integer.parseInt(holder[i]));
                    prio.add(Integer.parseInt(holder[i+1]));
                    i++;
                }
            }
            */
            name.add(s.nextLine());
            String a = s.nextLine();
            String holder[] = a.split("\\s*,\\s*");
            for (int i = 0; i < holder.length; i++) {
                time.add(Integer.parseInt(holder[i]));
                prio.add(Integer.parseInt(holder[i + 1]));
                i++;
            }
        }
        return true;
    }

    public static boolean read_RR(String pp) {
        try {
            f = new File(pp);
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return false;
        }
        name = new ArrayList<>();
        time = new ArrayList<>();
        while (s.hasNextLine()) {
            name.add(s.nextLine());
            time.add(Integer.parseInt(s.nextLine()));
        }
        return true;
    }
}

