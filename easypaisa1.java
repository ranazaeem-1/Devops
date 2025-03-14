import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class easypaisa1 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("--------------welcome to easypaisa------------");
        System.out.println("press 1 to login ");
        System.out.println("press 2 to sign-up");

        File user = new File("C:\\Users\\DELL\\Desktop\\easypaisa\\signup.txt");
        File balance = new File("C:\\Users\\DELL\\Desktop\\easypaisa\\balance.txt");
        File transiction = new File("C:\\Users\\DELL\\Desktop\\easypaisa\\transiction.txt");

        try {
            if (!user.exists()) {
                user.createNewFile();
            }
            if (!balance.exists()) {
                balance.createNewFile();
            }
            if (!transiction.exists()) {
                transiction.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            char x = in.next().charAt(0);
            if (x == '1') {
                login(user, balance, transiction);
                break;
            } else if (x == '2') {
                signup(user, balance, transiction);
                break;
            } else
                System.out.println("wrong input!! enter again");

        }

    }

    public static void login(File user, File balance, File transiction) {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter phone number");
            String usr = in.nextLine();
            System.out.println("Enter password");
            String pswd = in.nextLine();
            FileReader fr = new FileReader(user);
            BufferedReader br = new BufferedReader(fr);

            boolean usercheck = false;
            boolean pswdcheck = false;

            for (int i = 0; br.readLine() != null; i++) {
                usercheck = Files.readAllLines(Paths.get("signup.txt")).get(i).contains(usr);
                pswdcheck = Files.readAllLines(Paths.get("signup.txt")).get(i).contains(pswd);
                if (usercheck & pswdcheck) {
                    break;
                }
            }
            if (usercheck && pswdcheck && usr.length() == 11 && usr.charAt(0) == '0' && usr.charAt(1) == '3'
                    && Character.isDigit(usr.charAt(2)) && Character.isDigit(usr.charAt(3))
                    && Character.isDigit(usr.charAt(4)) && Character.isDigit(usr.charAt(5))
                    && Character.isDigit(usr.charAt(6)) && Character.isDigit(usr.charAt(7))
                    && Character.isDigit(usr.charAt(8)) && Character.isDigit(usr.charAt(9))
                    && Character.isDigit(usr.charAt(10)) && pswd.length() == 4 && Character.isDigit(pswd.charAt(0))
                    && Character.isDigit(pswd.charAt(1))
                    && Character.isDigit(pswd.charAt(2)) && Character.isDigit(pswd.charAt(3))) {
                System.out.println("Successfully loged in...");
                mainmenu(user, balance, transiction, usr);
            } else {
                System.out.println("invalid password or username\nenter 1 to signup\nenter 2 to login again");
                try {
                    while (true) {
                        char x = in.next().charAt(0);
                        if (x == '1') {
                            signup(user, balance, transiction);
                            break;
                        } else if (x == '2') {
                            login(user, balance, transiction);
                            break;
                        } else
                            System.out.println("incorrect option!! .. enter again");
                    }
                } catch (InputMismatchException e) {
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println("yes");
        } catch (IOException ex) {
            System.out.println("no");
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public static void signup(File user, File balance, File transiction) {

        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("please enter your 11 digit phone number ");
            String phone = in.nextLine();
            try (FileReader fr = new FileReader(user)) {
                BufferedReader br = new BufferedReader(fr);
                boolean cond = false;
                String line;
                for (int j = 0; br.readLine() != null; j++) {
                    cond = Files.readAllLines(Paths.get("signup.txt")).get(j).contains(phone);
                    if (cond == true) {
                        System.out.println("phone number already regitered\nlogin here");
                        break;
                    }
                }
                br.close();
                if (cond == true) {
                    login(user, balance, transiction);
                }

            } catch (IOException e1) {
            } catch (NullPointerException n1) {
            }
            if (phone.length() == 11 && phone.charAt(0) == '0' && phone.charAt(1) == '3'
                    && Character.isDigit(phone.charAt(2)) && Character.isDigit(phone.charAt(3))
                    && Character.isDigit(phone.charAt(4)) && Character.isDigit(phone.charAt(5))
                    && Character.isDigit(phone.charAt(6)) && Character.isDigit(phone.charAt(7))
                    && Character.isDigit(phone.charAt(8))
                    && Character.isDigit(phone.charAt(9)) && Character.isDigit(phone.charAt(10))) {
                try {
                    FileWriter user1 = new FileWriter(user, true);
                    PrintWriter user2 = new PrintWriter(user1);
                    FileWriter bal1 = new FileWriter(balance, true);
                    PrintWriter bal2 = new PrintWriter(bal1);

                    user2.print(phone);
                    user2.print(" ");
                    bal2.print(phone);
                    bal2.print(", ");
                    int user_balance = 0;
                    bal2.println(user_balance);
                    user2.close();
                    bal2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }

            else {
                System.out.println("please enter correct phone number!! ");
            }
        }
        while (true) {
            System.out.println("set up a 4-digit pin");
            String pin = in.nextLine();
            if (pin.length() == 4 && Character.isDigit(pin.charAt(0)) && Character.isDigit(pin.charAt(1))
                    && Character.isDigit(pin.charAt(2)) && Character.isDigit(pin.charAt(3))) {
                try {
                    FileWriter user1 = new FileWriter(user, true);
                    PrintWriter user2 = new PrintWriter(user1);
                    user2.print(pin);
                    user2.print(" ");
                    user2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            } else {
                System.out.println("please enter correct pin !! ");
            }
        }

        System.out.println("enter your last name");
        String first = in.nextLine();

        try {
            FileWriter user1 = new FileWriter(user, true);
            PrintWriter user2 = new PrintWriter(user1);
            user2.print(first);
            user2.print(" ");
            user2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            System.out.println("enter your id card number");
            String card = in.nextLine();
            if (card.length() == 13 && Character.isDigit(card.charAt(0)) && Character.isDigit(card.charAt(1))
                    && Character.isDigit(card.charAt(2)) && Character.isDigit(card.charAt(3)) &&
                    Character.isDigit(card.charAt(4)) && Character.isDigit(card.charAt(5))
                    && Character.isDigit(card.charAt(6)) && Character.isDigit(card.charAt(7)) &&
                    Character.isDigit(card.charAt(8)) && Character.isDigit(card.charAt(9))
                    && Character.isDigit(card.charAt(10)) && Character.isDigit(card.charAt(11)) &&
                    Character.isDigit(card.charAt(12))) {
                try {
                    FileWriter user1 = new FileWriter(user, true);
                    PrintWriter user2 = new PrintWriter(user1);
                    user2.println(card);
                    user2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            } else {
                System.out.println("please enter correct id card number!! ");
            }
        }
        System.out.println("Successfully signed up.");
        System.out.println("login here");
        login(user, balance, transiction);

    }

    public static void mainmenu(File user, File balance, File transiction, String usr) throws IOException {

        while (true) { // for infinte loop which will only break will you press exit from option 7
            Scanner sc = new Scanner(balance);
            String user_balance1 = "";
            while (sc.hasNext()) {
                String number = sc.next();
                user_balance1 = sc.next();
                number = number.substring(0, 11);
                if (number.equals(usr)) {
                    System.out.println("Your balance is " + user_balance1);
                    break;
                }
            }
            int user_balance = Integer.parseInt(user_balance1);
            Scanner input = new Scanner(System.in);
            System.out.println("Welcome to main menu");
            System.out.println(
                    "\t\n1- View balance"
                            + "\t\n2- Deposit balance"
                            + "\t\n3- Money transfer"
                            + "\t\n4- Bank transfer"
                            + "\t\n5- Transaction History"
                            + "\t\n6- Utility bills"
                            + "\t\n7- Exit\n");
            System.out.println("Enter your option:");
            String in = input.nextLine();
            char user_input = ' ';
            if (in.length() == 1) {
                user_input = in.charAt(0);
            } else {
                System.out.println("\t\twrong input\nEnter again");
                mainmenu(user, balance, transiction, usr);
            }

            switch (user_input) { // main switch which will control all 7 functions

                case '1':
                    System.out.println("----------------Current Status/Balance page----------------");
                    System.out.println("Press 1 to view balance and 0 to exit.");

                    int balance_input = input.nextInt();
                    if (balance_input == 1) {
                        System.out.println("You current balance is " + user_balance);
                    }
                    break;

                case '2':
                    System.out.println("\n----------------Deposit balance page----------------");
                    System.out.println("Enter the amount of money you want to deposit:");
                    int user_deposit = input.nextInt();
                    boolean creditcondition = false;
                    String user_creditcard = "";
                    while (creditcondition == false) {
                        System.out.println("Enter the 16 digits of your credit card:");
                        user_creditcard = input.next();
                        creditcondition = user_creditcard.length() == 16 && Character.isDigit(user_creditcard.charAt(0))
                                && Character.isDigit(user_creditcard.charAt(1))
                                && Character.isDigit(user_creditcard.charAt(2))
                                && Character.isDigit(user_creditcard.charAt(3))
                                && Character.isDigit(user_creditcard.charAt(4))
                                && Character.isDigit(user_creditcard.charAt(5))
                                && Character.isDigit(user_creditcard.charAt(6))
                                && Character.isDigit(user_creditcard.charAt(7))
                                && Character.isDigit(user_creditcard.charAt(8))
                                && Character.isDigit(user_creditcard.charAt(9))
                                && Character.isDigit(user_creditcard.charAt(10))
                                && Character.isDigit(user_creditcard.charAt(11))
                                && Character.isDigit(user_creditcard.charAt(12))
                                && Character.isDigit(user_creditcard.charAt(13))
                                && Character.isDigit(user_creditcard.charAt(14))
                                && Character.isDigit(user_creditcard.charAt(15));
                        if (creditcondition == true) {
                            break;
                        } else {
                            System.out.println("Wrong credit card information");
                        }
                    }
                    // user_balance = user_deposit; //ye wala over write krta rahy ga user balance
                    // me
                    user_balance = user_balance + user_deposit;
                    updateBalance(balance, user_balance, usr);
                    String mode = "cash deposit";
                    storeintransiction(transiction, usr, mode, user_creditcard, user_deposit);

                    System.out.println("The amount " + user_deposit
                            + " is succesfully deposited. Go to balance page to view balance.");
                    break;
                case '3':
                    System.out.println("----------------Money transfer----------------");
                    try {
                        easypaisaTransfer(user, balance, transiction, usr, user_balance);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case '4':
                    System.out.println("----------------Bank transfer----------------");
                    try {
                        bankTransfer(user, balance, transiction, usr, user_balance);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case '5':
                    System.out.println("----------------Transaction history page----------------");
                    getTransactionHistory(transiction, usr);
                    break;
                case '6':
                    bills(user_balance, balance, transiction, usr);
                    break;

                case '7':
                    System.out.println("Thankyou for using Easypaisa!");
                    Runtime.getRuntime().exit(0); // fuction to exit program

            }

        } // mainmenu switch ends here
    }// main function ends here

    public static void easypaisaTransfer(File user, File balance, File transiction, String usr, int user_balance) {
        try (FileReader frb = new FileReader(balance)) {
            BufferedReader brb = new BufferedReader(frb);

            Scanner input = new Scanner(System.in);
            System.out.println("Enter Reciever easypaisa account number.");
            String ep_number = input.nextLine();
            System.out.println("Enter amount to be transferred");
            int ep_amount = input.nextInt();
            if (ep_amount > user_balance) {
                System.out.println("Sorry you dont have sufficent balance in your account");
                mainmenu(user, balance, transiction, usr);
            }
            boolean customer = false;
            int reciever_balance = 0;
            for (int j = 0; brb.readLine() != null; j++) {
                customer = Files.readAllLines(Paths.get("signup.txt")).get(j).contains(ep_number);

                String balance1 = "";
                if (customer) {
                    Scanner sce = new Scanner(balance);
                    while (sce.hasNext()) {
                        String phonenumber = sce.next();
                        balance1 = sce.next();
                        if (phonenumber.equals(ep_number)) {
                            break;
                        }
                    }
                    reciever_balance = Integer.parseInt(balance1);
                    break;
                }

            }

            brb.close();
            if (customer == false || usr.equals(ep_number)) {
                System.out.println(
                        "The number entered is not an existing easypaisa customer.\n enter 1 for main menu\n enter 2 to try again");
                try {
                    while (true) {
                        char x = input.next().charAt(0);
                        if (x == '1') {
                            mainmenu(user, balance, transiction, usr);
                            break;
                        } else if (x == '2') {
                            easypaisaTransfer(user, balance, transiction, usr, user_balance);
                            break;
                        } else
                            System.out.println("incorrect option!! .. enter again");
                    }
                } catch (Exception e) {
                }
            }
            user_balance = user_balance - ep_amount;
            updateBalance(balance, user_balance, usr);
            String mode = "money transfer";
            storeintransiction(transiction, usr, mode, ep_number, ep_amount);

            reciever_balance = reciever_balance + ep_amount;
            System.out.println(reciever_balance);
            updateBalance(balance, reciever_balance, ep_number);
            mode = "recieved money";
            storeintransiction(transiction, ep_number, mode, usr, ep_amount);

        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }

    }

    public static void bills(int user_balance, File balance, File transiction, String usr) {
        Scanner input = new Scanner(System.in);
        String in = input.nextLine();
        char user_bill = ' ';
        if (in.length() == 1) {
            user_bill = in.charAt(0);
        } else {
            System.out.println("\t\twrong input\nEnter again");
            bills(user_balance, balance, transiction, usr);
        }

        do {
            System.out.println("Welcome to bills payment");
            System.out.println(
                    "\t\n1- electricity bill"
                            + "\t\n2- education bill"
                            + "\t\n3- internet bill"
                            + "\t\n4- other bills"
                            + "\t\n5- Exit\n");
            user_bill = input.next().charAt(0);

            // if(user_bill!=5){ //yahan while use karkay end mein homepage dekhana.
            switch (user_bill) {
                case '1':
                    System.out.println("\n----------------ELECTRICITY BILL SECTION-----------------");
                    System.out.println("Enter 14 digit number of your bill:");
                    String electric_num = input.next();
                    while (electric_num.length() != 14) {
                        System.out.println("Enter again(14 DIGITS ONLY)");
                        electric_num = input.next();
                    } // while ends here
                    System.out.println("Enter the amount:");
                    int electric_amount = input.nextInt();
                    if (electric_amount > user_balance) {
                        System.out.println("Sorry you dont have sufficent balance in your account");
                        bills(user_balance, balance, transiction, usr);
                    }
                    System.out.println("Do you want to pay " + electric_amount + " for this number " + electric_num
                            + "\nPress 1 to confirm and 0 to exit.");
                    char electric_confirm = input.next().charAt(0);
                    if (electric_confirm == '1') {
                        System.out.println("Successfully deposited");
                    } else {
                        break;
                    }

                    user_balance = user_balance - electric_amount;
                    updateBalance(balance, user_balance, usr);
                    String mode = "electric bill";
                    storeintransiction(transiction, usr, mode, electric_num, electric_amount);

                    break; // yahan se bhi main mein jana chahiye
                case '2':
                    System.out.println("\n----------------EDUCATION BILL SECTION-----------------");
                    System.out.println("Enter 14 digit number of your bill:");
                    String education_num = input.next();
                    while (education_num.length() != 14) {
                        System.out.println("Enter again(14 DIGITS ONLY)");
                        education_num = input.next();
                    } // while ends here
                    System.out.println("Enter the amount:");
                    int education_amount = input.nextInt();
                    if (education_amount > user_balance) {
                        System.out.println("Sorry you dont have sufficent balance in your account");
                        bills(user_balance, balance, transiction, usr);
                    }
                    System.out.println("Do you want to pay " + education_amount + " for this number " + education_num
                            + "\nPress 1 to confirm and 0 to exit.");
                    char education_confirm = input.next().charAt(0);
                    if (education_confirm == '1') {
                        System.out.println("Successfully deposited");
                    } else {
                        break;
                    }

                    user_balance = user_balance - education_amount;
                    updateBalance(balance, user_balance, usr);
                    mode = "education bill";
                    storeintransiction(transiction, usr, mode, education_num, education_amount);
                    break;
                case '3':
                    System.out.println("\n----------------INTERNET BILL SECTION-----------------");
                    System.out.println("Enter 14 digit number of your bill:");
                    String internet_num = input.next();
                    while (internet_num.length() != 14) {
                        System.out.println("Enter again(14 DIGITS ONLY)");
                        internet_num = input.next();
                    } // while ends here
                    System.out.println("Enter the amount:");
                    int internet_amount = input.nextInt();
                    if (internet_amount > user_balance) {
                        System.out.println("Sorry you dont have sufficent balance in your account");
                        bills(user_balance, balance, transiction, usr);
                    }
                    System.out.println("Do you want to pay " + internet_amount + " for this number " + internet_num
                            + "\nPress 1 to confirm and 0 to exit.");
                    char internet_confirm = input.next().charAt(0);
                    if (internet_confirm == '1') {
                        System.out.println("Successfully deposited");
                    } else {
                        break;
                    }

                    user_balance = user_balance - internet_amount;
                    updateBalance(balance, user_balance, usr);
                    mode = "internet bill";
                    storeintransiction(transiction, usr, mode, internet_num, internet_amount);

                    break;
                case '4':
                    System.out.println("\n----------------OTHERS BILL SECTION-----------------");
                    System.out.println("Enter 14 digit number of your bill:");
                    String others_num = input.next();
                    while (others_num.length() != 14) {
                        System.out.println("Enter again(14 DIGITS ONLY)");
                        others_num = input.next();
                    } // while ends here
                    System.out.println("Enter the amount:");
                    int others_amount = input.nextInt();
                    if (others_amount > user_balance) {
                        System.out.println("Sorry you dont have sufficent balance in your account");
                        bills(user_balance, balance, transiction, usr);
                    }
                    System.out.println("Do you want to pay " + others_amount + " for this number " + others_num
                            + "\nPress 1 to confirm and 0 to exit.");
                    char others_confirm = input.next().charAt(0);
                    if (others_confirm == '1') {
                        System.out.println("Successfully deposited");
                    } else {
                        break;
                    }

                    user_balance = user_balance - others_amount;
                    updateBalance(balance, user_balance, usr);
                    mode = "other bill";
                    storeintransiction(transiction, usr, mode, others_num, others_amount);

                    break;

            }
            
        } while (user_bill != '5');
    }

    public static void bankTransfer(File user, File balance, File transiction, String usr, int user_balance)
            throws Exception {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter Reciever bank account number.");
        String bank_acc = input.nextLine();
        boolean condition = false;
        if (bank_acc.length() == 14 && Character.isDigit(bank_acc.charAt(0)) && Character.isDigit(bank_acc.charAt(1))
                && Character.isDigit(bank_acc.charAt(2)) && Character.isDigit(bank_acc.charAt(3)) &&
                Character.isDigit(bank_acc.charAt(4)) && Character.isDigit(bank_acc.charAt(5))
                && Character.isDigit(bank_acc.charAt(6)) && Character.isDigit(bank_acc.charAt(7)) &&
                Character.isDigit(bank_acc.charAt(8)) && Character.isDigit(bank_acc.charAt(9))
                && Character.isDigit(bank_acc.charAt(10)) && Character.isDigit(bank_acc.charAt(11)) &&
                Character.isDigit(bank_acc.charAt(12)) && Character.isDigit(bank_acc.charAt(13))) {
            condition = true;
        } else {
            System.out.println("invalid account number.\nEnter again.");
            bankTransfer(user, balance, transiction, usr, user_balance);
        }
        System.out.println("Enter amount to be transferred");
        int bank_amount = input.nextInt();
        if (bank_amount > user_balance) {
            System.out.println("Sorry you dont have sufficent balance in your account");
            mainmenu(user, balance, transiction, usr);
        }

        user_balance = user_balance - bank_amount;
        updateBalance(balance, user_balance, usr);
        String mode = "bank transfer";
        storeintransiction(transiction, usr, mode, bank_acc, bank_amount);

    }

    public static void storeintransiction(File transiction, String usr, String mode, String source, int amount) {
        String amountStr = String.valueOf(amount);
        Date date = new Date();
        try (FileWriter fw = new FileWriter(transiction, true)) {
            PrintWriter pw = new PrintWriter(fw);
            pw.print(usr);
            pw.print("\t");
            pw.print(mode);
            pw.print("\t");
            pw.print(amountStr);
            pw.print("\t\t");
            pw.print(source);
            pw.print("\t\t");
            pw.println(date);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void updateBalance(File balance, int user_balance, String usr) {
        try {

            String user_balance_new = String.valueOf(user_balance);
            int i = 0, j = 0;
            Scanner scanner = new Scanner(balance);
            int count = 0;
            String[] name = new String[2];
            String reading = "";
            for (i = 0; scanner.hasNext(); i++) {
                reading = scanner.nextLine();
                count += 1;
            }

            Scanner sc_2 = new Scanner(balance);
            String[][] array_temp = new String[count][2];
            for (i = 0; sc_2.hasNext(); i++) {
                name = sc_2.nextLine().split(", ");
                for (j = 0; j < 2; j++) {
                    array_temp[i][j] = name[j];

                }

            }
            Scanner sc_3 = new Scanner(balance);
            for (i = 0; sc_3.hasNext(); i++) {
                if (sc_3.nextLine().contains(usr) == true) {
                    array_temp[i][0] = usr;
                    array_temp[i][1] = user_balance_new;
                    break;
                }

            }

            BufferedWriter sc_4 = new BufferedWriter(new FileWriter(balance));

            for (i = 0; i < array_temp.length; i++) {
                for (j = 0; j < 2; j++) {
                    if (j == 1) {
                        sc_4.write(array_temp[i][j]);
                        break;
                    }
                    sc_4.write(array_temp[i][j] + ", ");
                }
                sc_4.write("\n");
            }
            sc_4.close();

        } catch (Exception e) {
            System.out.println("An error occured");
        }
    }

    public static void getTransactionHistory(File transiction, String usr) {
        try {
            FileReader fr = new FileReader(transiction);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if ((line.substring(0, 11)).contains(usr)) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {

        }
    }

}
