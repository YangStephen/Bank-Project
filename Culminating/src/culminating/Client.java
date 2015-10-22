/* 
Culminating Activity
ICS4U1
Stephen Yang
Teacher: Ms. Xie
Program Name: Client.java
Description:
*/

// Import Packages
import java.io.*;
import java.util.*;

public class Client
{
   private String fname;
   private String lname;
   private int month;
   private int day;
   private int year;
   private int sin;
   private Chequing chequing = new Chequing ();
   private Savings savings = new Savings ();
   private Credit credit = new Credit ();
   private Scanner scanner = new Scanner (System.in);
   private String[] latest = new String[5];
   public Client()
   {
   }
   public Client(String last, String first, int newsin, int by, int bm, int bd, String cheq, String sav, String cred)
   {
      fname = first;
      lname = last;
      sin = newsin;
      year = by;
      month = bm;
      day = bd;
      if (cheq.compareTo("null")!=0)
      {
         chequing.initializeAccount();
         chequing.setValue(Double.parseDouble(cheq));
      }
      if (sav.compareTo("null")!=0)
      {
         savings.initializeAccount();
         savings.setValue(Double.parseDouble(sav));
      }
      if (cred.compareTo("null")!=0)
      {
         credit.initializeAccount();
         credit.setValue(Double.parseDouble(cred));
      }
      int size = 0;
      int count = 0;
      String temp = "";
      try
      {
         FileReader fr = new FileReader (lname+"."+fname+".txt");
         BufferedReader br = new BufferedReader (fr);
         while ((br.readLine())!=null)
         {
            size++;
         }
         fr = new FileReader (lname+"."+fname+".txt");
         br = new BufferedReader (fr);
         for (int i = 0; i<size-5; i++)
         {
            br.readLine();
         }
         for (int i = size-5; i<size; i++)
         {
            temp = br.readLine();
            latest[count] = temp;
            count++;
         }
      }
      catch (IOException ioe)
      {
            
      }
   
   }
   public void insert (String s)
   {
      for (int i = 0; i<5; i++)
      {
         if (i != 4)
         {
            latest[i] = latest[i+1];
         }
         else
         {
            latest[4] = s;
         }
      }
   }
   public static void displayMenu()
   {
      System.out.println("PROFILE SERVICE MENU: ");
      System.out.println("1. View Account Activity");
      System.out.println("2. Deposit");
      System.out.println("3. Withdraw");
      System.out.println("4. Process cheque");
      System.out.println("5. Process purchase");
      System.out.println("6. Process payment");
      System.out.println("7. Transfer Funds");
      System.out.println("8. Open Account or Issue Card");
      System.out.println("9. Cancel Account or Card");
      System.out.println("10. Return to the main menu");
   }
   public void initialize()
   {
      System.out.println("Initialing New Client Profile");
      System.out.print("Last Name: ");
      lname = scanner.nextLine();
      System.out.print("First Name: ");
      fname = scanner.nextLine();
      System.out.print("Month of Birth: ");
      month = scanner.nextInt();
      System.out.print("Date of Birth: ");
      day = scanner.nextInt();
      System.out.print("Year of Birth: ");
      year = scanner.nextInt();
      System.out.print("SIN: ");
      sin = scanner.nextInt();
   }
   public String getName()
   {
      return lname+", "+fname;
   }
   public int getSin()
   {
      return sin;
   }
   public void getData()
   {
      System.out.println(lname+", "+fname+"\t"+day+"/"+month+"/"+year+"\t\t"+sin);
      System.out.println("Accounts Available: ");
      if (chequing.getStatus()==true)
      {
         System.out.println("\tChequing: $"+String.valueOf(chequing.getValue()));
      }
      if (savings.getStatus()==true)
      {
         System.out.println("\tSavings: $"+String.valueOf(savings.getValue()));
      }
      if (credit.getStatus()==true)
      {
         System.out.println("\tCredit: $"+String.valueOf(credit.getValue()));
      }
   }
   public String getAllData()
   {
      String returnValue = "";
      returnValue = lname+"\r\n"+fname+"\r\n"+String.valueOf(month)+"\r\n"+String.valueOf(day)+"\r\n"+String.valueOf(year)+"\r\n"+String.valueOf(sin)+"\r\n";
      if (chequing.getStatus()==true)
      {
         returnValue += String.valueOf(chequing.getValue())+"\r\n";
      }
      else
      {
         returnValue += "null\r\n";
      }
      if (savings.getStatus()==true)
      {
         returnValue += String.valueOf(savings.getValue())+"\r\n";
      }
      else
      {
         returnValue += "null\r\n";
      }
      if (credit.getStatus()==true)
      {
         returnValue += String.valueOf(credit.getValue())+"\r\n";
      }
      else
      {
         returnValue += "null\r\n";
      }
      System.out.println(returnValue);
      return returnValue;
   }
   public void profileServiceMenu()
   {
      Scanner scanner = new Scanner(System.in);
      int choice = 0;
      double value = 0;
      String option ="";
      FileWriter fw = null;
      BufferedWriter bw = null;
      try
      {
         fw = new FileWriter(lname+"."+fname+".txt");
         bw = new BufferedWriter(fw);
      }
      catch (IOException ioe)
      {
         
      }
      do
      {
         displayMenu();
         choice = scanner.nextInt();
         if (choice == 1)
         {
            for (int i = 0; i<5; i++)
            {
               if (latest[i]!=null)
               {
                  System.out.println(latest[i]);
               }
            }
         }
         else if (choice == 2)
         {
            if (chequing.getStatus() == false && savings.getStatus() == false&&credit.getStatus() == false)
            {
               System.out.println("No Accounts Available");
            }
            else
            {
               scanner.nextLine();
               System.out.println("Which account would you like to use? ");
               option = scanner.nextLine();
               if (option.compareTo("Chequing")==0)
               {
                  if (chequing.getStatus() == true)
                  {
                     System.out.println("Chequing");
                     System.out.println("Deposit: $");
                     value = scanner.nextDouble();
                     chequing.deposit(value);
                     insert("Chequing Deposit: $"+value);
                  }
               }
               if (option.compareTo("Savings")==0)
               {
                  if (savings.getStatus() == true)
                  {
                     System.out.println("Savings");
                     System.out.println("Deposit: $");
                     value = scanner.nextDouble();
                     savings.deposit(value);
                     insert( "Savings Deposit: $"+value);
                  
                  }
               }
            }
         }
         else if (choice == 3)
         {
            if (chequing.getStatus() == false && savings.getStatus() == false&&credit.getStatus() == false)
            {
               System.out.println("No Accounts Available");
            }
            else
            {
               scanner.nextLine();
               System.out.println("Which account would you like to use? ");
               option = scanner.nextLine();
               if (option.compareTo("Chequing")==0)
               {
                  if (chequing.getStatus() == true)
                  {
                     System.out.println("Chequing");
                     System.out.println("Withdraw: $");
                     value = scanner.nextDouble();
                     chequing.withdraw(value);
                     insert("Chequing Withdrawl: $"+value);
                  }
               }
               if (option.compareTo("Saving")==0)
               {
                  if (savings.getStatus() == true)
                  {
                     System.out.println("Savings");
                     System.out.println("Withdraw: $");
                     value = scanner.nextDouble();
                     savings.withdraw(value);
                     insert("Savings Withdrawl: $"+value);
                  }
               }
               if (option.compareTo("Credit")==0)
               {
                  if (credit.getStatus() == true)
                  {
                     System.out.println("Credit");
                     System.out.println("Withdraw: $");
                     value = scanner.nextDouble();
                     credit.withdraw(value);
                     insert("Credit Withdrawl: $"+value);
                  }
               }
            }
         }
         else if (choice == 4)
         {
            if (chequing.getStatus()==false)
            {
               System.out.println("No Accounts Available");
            }
            else
            {
               chequing.processCheque();
               insert("Chequing Cheque Processed");
            }
         }
         else if (choice == 5)
         {
            credit.processPurchase();
         }
         else if (choice == 6)
         {
            if (chequing.getStatus()==false&&savings.getStatus()==false)
            {
               System.out.println("No Accounts Available");
            }
            else
            {
               scanner.nextLine();
               System.out.println("Value of Credit Card: "+credit.getValue());
               System.out.println("Accounts Available: ");
               if (chequing.getStatus() == true)
               {
                  System.out.println("Chequing");
               }
               if (savings.getStatus() == true)
               {
                  System.out.println("Savings");
               }
               System.out.println("Which account would you like? ");
               option = scanner.nextLine();
               if (option.compareTo("Chequing")==0)
               {
                  System.out.println("Enter value for transfer: ");
                  value = scanner.nextInt();
                  chequing.withdraw(value);
                  credit.payment(value);
                  insert("Chequing to Credit Transfer: $"+value);
               }
               else if (option.compareTo("Savings")==0)
               {
                  System.out.println("Enter value for transfer: ");
                  value = scanner.nextInt();
                  savings.withdraw(value);
                  credit.payment(value);
                  insert("Savings to Credit Transfer: $"+value);
               }
            }
         }
         else if (choice == 7)
         {
            if (chequing.getStatus()==false||savings.getStatus()==false)
            {
               System.out.println("Only one of the accounts available");
            }
            else
            {
               scanner.nextLine();
               System.out.println("Transfer from:\n Chequing to Savings\nSavings to Chequing ");
               option = scanner.nextLine();
               if (option.compareTo("Chequing to Savings")==0)
               {
                  System.out.println("Enter value for transfer: ");
                  value = scanner.nextInt();
                  chequing.withdraw(value);
                  savings.deposit(value);
                  insert("Chequing to Savings Transfer: $"+value);
               }
               else if (option.compareTo("Savings to Chequing")==0)
               {
                  System.out.println("Enter value for transfer: ");
                  value = scanner.nextInt();
                  savings.withdraw(value);
                  chequing.deposit(value);
                  insert("Savings and Chequing Transfer: $"+value);
               }
            }
         }
         else if (choice == 8)
         {
            scanner.nextLine();
            System.out.println("Which Account would you like to open? ");
            option = scanner.nextLine();
            if (option.compareTo("Chequing")==0)
            {
               chequing.initializeAccount();
               insert("Chequing Account initialized");
            }
            if (option.compareTo("Savings")==0)
            {
               savings.initializeAccount();
               insert("Savings Account Initialized");
            }
            if (option.compareTo("Credit")==0)
            {
               credit.initializeAccount();
               insert("Credit Card Account Initalized");
            }
         }
         else if (choice == 9)
         {
            scanner.nextLine();
            System.out.println("Which Account would you like to close? ");
            option = scanner.nextLine();
            if (option.compareTo("Chequing")==0)
            {
               if (chequing.getStatus() == false)
               {
               }
               else
               {
                  chequing.cancel();
                  insert("Chequing Account Canceled");
               }
            }
            if (option.compareTo("Savings")==0)
            {
               if (savings.getStatus() == false)
               {
               }
               else
               {
                  savings.cancel();
                  insert("Chequing Account Canceled");
               }
            }
            if (option.compareTo("Credit")==0)
            {
               if (credit.getStatus() == false)
               {
               }
               else
               {
                  credit.cancel();
                  insert("Chequing Account Canceled");
               }
            }
            if (chequing.getStatus() == false && savings.getStatus() == false&&credit.getStatus() == false)
            {
               fname = null;
               lname = null;
               chequing = new Chequing ();
               month = 0;
               day = 0;
               year = 0;
               sin = 0;
               Savings savings = new Savings ();
               Credit credit = new Credit ();
               String[] latest = new String[5];
               break;
            }
         }
         else if (choice == 10)
         {
            for (int i = 0; i<5; i++)
            {
               if (latest[i]!=null)
               {
                  try
                  {
                     bw.write(latest[i]);
                     bw.newLine();
                  }
                  catch (IOException ioe)
                  {
                  }                  
               }
            }
            try
            {
               bw.close();
            }
            catch (IOException ioe)
            {
            }
            break;
         }
         try
         {
            bw.flush();
         }
         catch (IOException ioe)
         {
         }
      }while (choice != 10);
   }
}