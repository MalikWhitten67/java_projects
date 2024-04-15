import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask; 
class Bank{
    private double balance =  500; 
    private int account_no = 123456;
    private double apy = 2.5;
    private boolean hasLoop = false;
    public double getBalance(){
        return balance;
    }

    public void withdraw(int amount) throws Exception{
        Scanner scan = new Scanner(System.in);
        System.out.println(scan); 
    }

    void collectInterest(Bank bank){
        bank.balance = bank.balance +  bank.balance * bank.apy / 100; 
    }
    static  int loop(Bank bank, Scanner scan){
        System.out.println("\n What would you like to do? \n Options: \n 1. bal - check your balance \n 2. withdraw - withdraw money \n 3. deposit - deposit money \n 4. close - exit application");
        String action = scan.next(); 
       if(action.startsWith("bal")){
         System.out.println("\n Your balance is $" + bank.balance + " with  " + bank.apy + "% apy");
       }else if(action.startsWith("withdraw")){ 
         System.out.println("How much do you want to withdraw? " + " You have " + bank.balance + " note: there is a 2.5% wtihdraw interest");
         double amount = scan.nextDouble();
         double amountBefore = amount;
         amount = amount - amount * 2.5 / 100; // take out interest
         if(amountBefore >= bank.balance){
           System.out.println("\n Amount withdrew exceeds or is equal to the curent balance of $" + bank.balance );
           loop(bank, scan);
           return 0;
         }
         bank.balance = bank.balance - amount;
         System.out.println("\n Interest Collected $" + Math.ceil(amountBefore * 2.5 / 100));
         System.out.println("\n Your new balance is $" + Math.round(bank.balance) + " " + " Amount withdrew = $" + amount);
       } else if(action.startsWith("close")){ 
         return 0;
       }
       else if(action.startsWith("deposit")){ 
        System.out.println("\n How much? Note: there is a 2.5% deposit interest fee");
        double amount = scan.nextDouble(); 
        amount = amount - amount *2.5 / 100;
        bank.balance =  bank.balance + amount; 
        System.out.println("\n Ammount Deposited $" + amount); 
        System.out.println("\n New Balance $" + bank.balance);
      }
       if(!bank.hasLoop){
        bank.hasLoop = true;
         new Timer().scheduleAtFixedRate(new TimerTask() { 
            public void run(){
              bank.collectInterest(bank);
            }
         }, 0, 6000);
       }
       loop(bank, scan);
       return 1;
    }
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scan  = new Scanner(System.in);
        System.out.println("What is your account number?");
        int account_no  = scan.nextInt();
        bank.account_no = account_no; 
        loop(bank, scan);
       
    
    }
}

 
public class main {
     

    public static void main(String[] args) {
        new Bank(); 
    }
}
