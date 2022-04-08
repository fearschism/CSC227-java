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
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE
    //Creating Global Variables to us to use with the static methods of read and main.
    public static ArrayList<Integer> time;
    public static ArrayList<Integer> prio;
    public static ArrayList<String> name;
    public static ArrayList<Integer> id;
    public static ArrayList<Integer> bt;
    public static ArrayList<Integer> AT;
    public static URL path;
    public static File f;
    public static Scanner s;
    public static Process Priority[];
    public static srtfProcess srtf[];
    public static rrProcess rr[];
    public static Scanner input;
    public static int choice;
    public static String p;
    public static Cpu PService;
    public static RR RRservice;
    public static SRTF SRTFservices;
    public static Path dir;
    public static String[] allfiles;
    public static File LIST;
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
                System.out.println(RED+"That's not a number!"+RESET);
                input.next();
            }
            choice = input.nextInt();



            switch (choice) {
                case 1:
                    //System.out.println("Please enter your File name that is stored in the Priority FOLDER(Example:'FILENAME.txt')");
                    System.out.println("Please choose a file Below:");
                    LIST = new File("src/Priorty/");
                    allfiles = LIST.list();
                    for(int i=0;i<allfiles.length;i++){
                        System.out.println(PURPLE+"["+(i+1)+"]"+" "+allfiles[i]+RESET);
                    }
                    while (!input.hasNextInt()) { //validating users input
                        System.out.println(RED+"Please select the correct number!"+RESET);
                        input.next();
                    }
                    choice = input.nextInt();
                   // p = input.next().trim();
                    try {
                        dir = Paths.get("src/Priorty/" + allfiles[choice-1]);
                    }catch (Exception e){System.out.println(RED+"ERROR[FILE IS NOT FOUND!]"+RESET);break;}

                    if (read_priority(dir.toString())) {
                        System.out.println(GREEN+"SUCCESS[DONE READING!]"+RESET);
                        PService = new Cpu();
                        Priority = new Process[name.size()];
                        for (int i = 0; i < name.size(); i++) {
                            Priority[i] = new Process(name.get(i), time.get(i), prio.get(i));
                        }
                        PService.priorityScheduling(Priority, name.size());
                    }
                    break;
                case 2:
                    System.out.println("Please choose a file Below:");
                    LIST = new File("src/RR/");
                    allfiles = LIST.list();
                    for(int i=0;i<allfiles.length;i++){
                        System.out.println(PURPLE+"["+(i+1)+"]"+" "+allfiles[i]+RESET);
                    }
                    while (!input.hasNextInt()) { //validating users input
                        System.out.println(RED+"That's not a number!"+RESET);
                        input.next();
                    }
                    choice = input.nextInt();
                    // p = input.next().trim();
                    try {
                        dir = Paths.get("src/RR/" + allfiles[choice-1]);
                    }catch (Exception e){System.out.println(RED+"ERROR[FILE IS NOT FOUND!]"+RESET);break;}

                    if (read_RR(dir.toString())) {
                        System.out.println(GREEN+"SUCCESS[DONE READING!]"+RESET);
                        RRservice = new RR();
                        rr = new rrProcess[name.size()];
                        for (int i = 0; i < name.size(); i++) {
                            rr[i] = new rrProcess(name.get(i), time.get(i));
                        }
                        RRservice.RRscheduling(rr, name.size());
                    }
                    break;
                case 3:
                    System.out.println("Please choose a file Below:");
                    LIST = new File("src/SRTF/");
                    allfiles = LIST.list();
                    for(int i=0;i<allfiles.length;i++){
                        System.out.println(PURPLE+"["+(i+1)+"]"+" "+allfiles[i]+RESET);
                    }
                    while (!input.hasNextInt()) { //validating users input
                        System.out.println(RED+"That's not a number!"+RESET);
                        input.next();
                    }
                    choice = input.nextInt();
                    // p = input.next().trim();
                    try {
                        dir = Paths.get("src/SRTF/" + allfiles[choice-1]);
                    }catch (Exception e){System.out.println(RED+"ERROR[FILE IS NOT FOUND!]"+RESET);break;}

                    if (read_strf(dir.toString())) {
                        System.out.println(GREEN+"SUCCESS[DONE READING!]"+RESET);
                        SRTFservices = new SRTF();
                        srtf = new srtfProcess[id.size()];
                        for (int i = 0; i < id.size(); i++) {
                            //System.out.println("ID: "+id.get(i)+" BT: "+bt.get(i)+" AT: "+AT.get(i));
                            srtf[i] = new srtfProcess(id.get(i), bt.get(i),AT.get(i));
                        }
                        SRTFservices.srtfScheduling(srtf, srtf.length);
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
            System.out.println(RED+"File not found!!"+RESET);
            return false;
        }
        Main.name = new ArrayList<>();
        Main.time = new ArrayList<>();
        Main.prio = new ArrayList<>();
        while (s.hasNextLine()) {
            try {
                name.add(s.nextLine());
                String a = s.nextLine();
                String holder[] = a.split("\\s*,\\s*");
                for (int i = 0; i < holder.length; i++) {
                    time.add(Integer.parseInt(holder[i]));
                    prio.add(Integer.parseInt(holder[i + 1]));
                    i++;
                }
            }catch (Exception e) {
                System.out.println(RED+"FILE FORMAT ERROR!"+RESET);
                System.out.println(RED+e.getMessage()+RESET); return false;
            }
        }
        if(name.size()>30){System.out.println(RED+"NUMBER OF JOBS CANNOT BE GREATER THAN 30![FILE IGNORED BY THE SYSTEM]"+RESET); return false;}
        return true;
    }

    public static boolean read_RR(String pp) {
        try {
            f = new File(pp);
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println(RED+"File not found!!"+RESET);
            return false;
        }
        name = new ArrayList<>();
        time = new ArrayList<>();
        while (s.hasNextLine()) {
            try {
                name.add(s.nextLine());
                time.add(Integer.parseInt(s.nextLine()));
            }catch (Exception e) {
                System.out.println(RED+"FILE FORMAT ERROR!"+RESET);
                System.out.println(RED+e.getMessage()+RESET); return false;
            }
        }
        if(name.size()>30){System.out.println(RED+"NUMBER OF JOBS CANNOT BE GREATER THAN 30![FILE IGNORED BY THE SYSTEM]"+RESET); return false;}
        return true;
    }
    public static boolean read_strf(String pp) {
        try {
            f = new File(pp);
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println(RED+"File not found!!"+RESET);
            return false;
        }
        bt = new ArrayList<>();
        id = new ArrayList<>();
        AT = new ArrayList<>();
        while (s.hasNextLine()) {
            String a = s.nextLine();
            if(!a.contains("//") &&!a.contains(" ")) {
                System.out.println(CYAN+"READING: "+a+RESET);
                try {
                    String holder[] = a.split(","); //1,2,3
                    for (int i = 0; i < holder.length; i++) {
                        id.add(Integer.parseInt(holder[i]));
                        AT.add(Integer.parseInt(holder[i + 1]));
                        bt.add(Integer.parseInt(holder[i + 2]));
                        i += 2;
                    }
                }catch (Exception e) {
                    System.out.println(RED+"FILE FORMAT ERROR! "+RESET);
                    System.out.println(RED+e.getMessage()+RESET);  return false;
                }
            }
           else{System.out.println(BLUE+"IGNORING: "+a+RESET); break;}
        }
        if(id.size()>30){System.out.println(RED+"NUMBER OF JOBS CANNOT BE GREATER THAN 30![FILE IGNORED BY THE SYSTEM]"+RESET); return false;}
        return true;
    }
}

