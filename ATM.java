import java.util.Scanner;
import java.util.Hashtable;

public class ATM{
    public static void main(String[] args){
       run_atm();
    }

    public static void run_atm(){
        Hashtable<Integer,Card> cards = new Hashtable<>();
        System.out.println("------------------------------------");
        System.out.println("Welcome to Java ATM.");
        Scanner scnr = new Scanner(System.in);
        while(true){
            System.out.println("------------------------------------");
            System.out.println("Choose Option 1. Create Card 2. See Balance/Transaction 3.Exit");
            System.out.print("Type 1, 2, or 3:");
            String option = scnr.nextLine();
            if (option.equals("1")){
                cards = createCard(cards,scnr);
            } else if (option.equals("2")){
                cards = insertCard(cards,scnr);
            } else if (option.equals("3")){
                scnr.close();
                return;
            } else {
                System.out.println("Wrong option. Choose again.");
            }
        }
    }

    public static Hashtable<Integer,Card> createCard(Hashtable<Integer,Card> org_cards, Scanner scn){
        System.out.println("Card is being created now.");

        //register pin
        System.out.print("Enter four digit PIN you would like to use:");
        String pin = scn.nextLine();
        while (pin.length()!=4) {
            System.out.print("PIN entered was not four digits. Please enter again:");
            pin=scn.nextLine();
        }

        //card number set arbitraily
        int cardnum = org_cards.size()+1;

        //create accounts for the card
        Hashtable<Integer,Account> accounts = new Hashtable<>();
        accounts.put(0,new Account(0, 0, "Checking"));
        accounts.put(1,new Account(1, 0, "Savings"));
        Card newcard = new Card(cardnum, pin, accounts);

        //put created card into the system
        org_cards.put(cardnum,newcard);
        System.out.println("------------------------------------");
        System.out.println("Your card has been created. Checking, Savings accounts associated to your card have also been created.");
        System.out.println("Your card number is "+Integer.toString(cardnum));
        return org_cards;
    }

    public static Hashtable<Integer,Card> insertCard(Hashtable<Integer,Card> org_cards, Scanner scan){
        //Enter card number
        System.out.println("Insert your card. (Type card number)");
        System.out.print("Card Number:");
        String cnum = scan.nextLine();
        try{
            int cardnumber = Integer.parseInt(cnum);
            while (!org_cards.containsKey(cardnumber)){
                System.out.print("Wrong card number. Enter again:");
                cnum = scan.nextLine();
                cardnumber = Integer.parseInt(cnum);
            }
            
            Card usercard = org_cards.get(cardnumber);

            //Enter PIN
            System.out.print("Enter PIN:");
            boolean pin_corr = usercard.checkPin(scan.nextLine());
            while (!pin_corr){
                System.out.print("Wrong PIN. Enter Again:");
                pin_corr = usercard.checkPin(scan.nextLine());
            };

            //Choose account
            System.out.println("Choose Your Account: 1. Checking 2. Savings");
            System.out.print("Type 1 or 2:");
            String option = scan.nextLine();
            while (!option.equals("1") &&!option.equals("2")) {
                System.out.println("Wrong option. Choose again");
                option = scan.nextLine();
            }

            Account useraccount;
            int accountnum;
            if (option.equals("1")){
                accountnum=0;
                useraccount = usercard.getAccounts().get(0);
            } else{
                accountnum=1;
                useraccount = usercard.getAccounts().get(1);
            }

            //Choose transaction
            System.out.println("Choose Transaction: 1. See Balance 2. Deposit 3. Withdraw");
            System.out.print("Type 1, 2, or 3:");
            String option2 = scan.nextLine();
            while (!option2.equals("1") &&!option2.equals("2")&&!option2.equals("3") ) {
                System.out.println("Wrong option. Choose again");
                option2 = scan.nextLine();
            }
            //see balance
            if (option2.equals("1")){
                System.out.println("------------------------------------");
                System.out.println("Your balance is $"+useraccount.getBalance());
            } 
            //deposit
            else if (option2.equals("2")){
                System.out.print("Enter how much you would like to deposit:");
                String amtstr = scan.nextLine();
                int amt = Integer.parseInt(amtstr);
                while (amt<=0){
                    System.out.print("Amount must be greater than 0. Enter again: ");
                    amt = Integer.parseInt(scan.nextLine());
                }
                useraccount.setBalance(useraccount.getBalance()+amt);
                System.out.println("------------------------------------");
                System.out.println("Deposit Complete. Current Balance is $"+useraccount.getBalance());
            }
            //withdraw
            else if (option2.equals("3")){
                System.out.print("Enter how much you would like to withdraw:");
                String amtstr = scan.nextLine();
                int amt = Integer.parseInt(amtstr);
                while (amt<0){
                    System.out.print("Amount must be equal to or greater than 0. Enter again: ");
                    amt = Integer.parseInt(scan.nextLine());
                }
                while (useraccount.getBalance()<amt){
                    System.out.println("Insufficient Balance. Current balance is "+ useraccount.getBalance());
                    System.out.print("Enter again:");
                    amt = Integer.parseInt(scan.nextLine());
                }
                useraccount.setBalance(useraccount.getBalance()-amt);
                System.out.println("------------------------------------");
                System.out.println("Withdrawl Complete. Current Balance is $"+useraccount.getBalance());
            } 
            usercard.getAccounts().put(accountnum,useraccount);
            org_cards.put(cardnumber,usercard);
            return org_cards;
        }
        catch (NumberFormatException e){
            System.out.print("Not a number. Exiting.");
            return org_cards;
        }
    }


}