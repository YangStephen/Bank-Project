/* 
Culminating Activity
ICS4U1
@author Stephen Yang
@version 1.0
@since 2015/01/15
Teacher: Ms. Xie
Program Name: Client.java
Description: Client class is a class to store information about each and every client. In this class, it contains links to 3 more objects: Chequing, Savings, and Credit. Each of which, have values to indicate what accounts this account holder has. In addition, there is the Customer Menu in this class with it's supporting methods to go through any commands a customer might like to make with their account.
*/

// Import Packages
import java.io.*;
import java.util.*;
import java.text.*;

public class Client
{
// Variable Declaration
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
   private Calendar calendar = new GregorianCalendar();
   /**
 * Constructor Client
 *
 */
   public Client()
   {
   }
   /**
 * Constructor Client
 *
 * @param last Last Name
 * @param first First Name
 * @param newsin SIN number
 * @param by Birth Year
 * @param bm Birth Month
 * @param bd Birth Day
 * @param cheq Chequing Data
 * @param sav Savings Data
 * @param cred Credit Card Data
 */
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
         chequing.initializeAccount(month, day, year);
         chequing.setValue(Double.parseDouble(cheq));
      }
      if (sav.compareTo("null")!=0)
      {
         savings.initializeAccount();
         savings.setValue(Double.parseDouble(sav));
      }
      if (cred.compareTo("null")!=0)
      {
         credit.initializeAccount(month, day, year);
         credit.setValue(Double.parseDouble(cred));
      }
      int size = 0;
      int count = 0;
      String temp = "";
      try
      {
         FileReader fr = new FileReader (sin+".txt");
         BufferedReader br = new BufferedReader (fr);
         while ((br.readLine())!=null)
         {
            size++;
         }
         fr = new FileReader (sin+".txt");
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
   /**
 * Method to insert a Event into latest array
 *
 * @param s String to insert
 */
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
   /**
 * Method to delete a client
 *
 */
   public void delete()
   {
      fname = "null";
      lname = "null";
      chequing = new Chequing ();
      month = 0;
      day = 0;
      year = 0;
      sin = 0;
      Savings savings = new Savings ();
      Credit credit = new Credit ();
      String[] latest = new String[5];
   }
   /**
 * Method to display Profile Service Menu
 *
 */
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
      System.out.println("10. Display Account Values");
      System.out.println("11. Return to the main menu");
      System.out.println("Option: ");
   }
   /**
 * Method to initialize a client with data
 *
 */
   public void initialize()
   {
      scanner = new Scanner(System.in);
      int tempSin = 0;
      int maxdays = 0;
      boolean exit = false;
      calendar.clear();
      boolean quit = false;
      System.out.println("Initialing New Client Profile");
      System.out.print("Last Name: "); // " " Catch
      lname = scanner.nextLine();
      lname = lname.toUpperCase();
      System.out.print("First Name: "); // " " Catch
      fname = scanner.nextLine();
      fname = fname.toUpperCase();
      do
      {
         calendar = new GregorianCalendar();
         quit = false;
         try
         {
            System.out.print("Year of Birth: ");
            year = scanner.nextInt();
            if (year >calendar.get(calendar.YEAR))
            {
               quit = true;
               System.out.println("Error! Year of Birth invalid");
            }
         }
         catch (InputMismatchException ime)
         {
            System.out.println("Input Exception!");
            quit = true;
            scanner.nextLine();
         }
      } while (quit == true);
      do
      {
         quit = false;
         try
         {
            System.out.print("Month of Birth: ");
            month = scanner.nextInt();
            if (month <1 || month >12)
            {
               quit = true;
               System.out.println("Error! Month of Birth invalid");
            }
            if (year == calendar.get(calendar.YEAR))
            {
               if (month-1 >calendar.get(calendar.MONTH))
               {
                  quit = true;
                  System.out.println("Error! Month of Birth invalid");
               }
            }
         }
         catch (InputMismatchException ime)
         {
            System.out.println("Input Exception!");
            quit = true;
            scanner.nextLine();
         }
      } while (quit == true);
      calendar.set(GregorianCalendar.YEAR, year);
      calendar.set(GregorianCalendar.MONTH, month-1);
      calendar.set(GregorianCalendar.DATE, 1);
      maxdays = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
      do
      {
         quit = false;
         try
         {
            System.out.print("Day of Birth: ");
            day = scanner.nextInt();
            if (day>maxdays)
            {
               quit = true;
               System.out.println("Error! Day does not exist in that month"); 
            }
            calendar = new GregorianCalendar();
            if (day<=0)
            {
               System.out.println("Negative/Zero Value!");
               quit = true;
            }
            if (quit == false)
            {
               if (year == calendar.get(calendar.YEAR))
               {
                  if (month-1 == calendar.get(calendar.MONTH))
                  {
                     if (day > calendar.get(calendar.DATE))
                     {
                        quit = true;
                        System.out.println("Error! Day of Birth invalid");
                     }
                     calendar.set(GregorianCalendar.YEAR, year);
                     calendar.set(GregorianCalendar.MONTH, month-1);
                     calendar.set(GregorianCalendar.DATE, 1);
                  }
               }
            }
         }
         catch (InputMismatchException ime)
         {
            System.out.println("Input Exception!");
            quit = true;
            scanner.nextLine();
         }
      } while (quit == true);
      System.out.print("SIN: ");
      do
      {
         quit = false;
         try
         {
            tempSin = scanner.nextInt();
         }
         catch (InputMismatchException ime)
         {
            System.out.println("Input Exception!\nEnter a new SIN: ");
            quit = true;
            scanner.nextLine();
         }
      } while (quit == true);
      if (tempSin < 100000000 || tempSin > 999999999)
      {
         do
         {
            System.out.println("SIN must be 9 characters");
            System.out.print("Enter a SIN: ");
            try
            {
               tempSin = scanner.nextInt();
            }
            catch (InputMismatchException ime)
            {
               System.out.println("Input Exception!\nEnter a new SIN: ");
               quit = true;
               scanner.nextLine();
            }
         }while(tempSin < 99999999 || tempSin > 999999999);
      }
      if (Bank.existingSin(tempSin) == true)
      {
         do
         {
            System.out.println("Already a member with SIN number: "+tempSin);
            System.out.print("Enter a SIN: ");
            try
            {
               tempSin = scanner.nextInt();
            }
            catch (InputMismatchException ime)
            {
               System.out.println("Input Exception!\nEnter a new SIN: ");
               quit = true;
               scanner.nextLine();
            }
         }while(Bank.existingSin(tempSin)==true);
      }
      sin = tempSin;
   }
    /**
 * Accessor method for name
 *
 * @return lname+", "+fname Name
 */
   public String getName()
   {
      return lname+", "+fname;
   }
   /**
 * Accessor method for SIN
 *
 * @return sin SIN number
 */
   public int getSin()
   {
      return sin;
   }
   /**
 * Print out data of this client
 *
 */
   public void getData()
   {
      System.out.println(lname+", "+fname+"\t"+day+"/"+month+"/"+year+"\t"+sin);
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
   /**
 * Print out data of this client
 *
 * @return returnValue String containing all data
 */
   public String getAllData()
   {
      String returnValue = "";
      returnValue = lname+"\r\n"+fname+"\r\n"+String.valueOf(year)+"\r\n"+String.valueOf(month)+"\r\n"+String.valueOf(day)+"\r\n"+String.valueOf(sin)+"\r\n";
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
   
      return returnValue;
   }
   /**
 * Profile Service Menu: Contains options for user to choose from for their account.
 *
 */
   public void profileServiceMenu()
   {
      Scanner scanner = new Scanner(System.in);
      int choice = 0, intchoice = 0;
      double value = 0, oldvalue = 0;
      boolean quit = false;
      boolean clear = false;
      String option ="";
      FileWriter fw = null;
      BufferedWriter bw = null;
      System.out.println("Account: "+lname+", "+fname);
      DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
      Date date = new Date();
      String timeanddate = df.format(date);
      String[] datearray = new String[3];
      int intarray[] = new int[3];
      datearray = timeanddate.split("/");
      datearray[2]=datearray[2].substring(0,2);
      for(int i = 0 ; i < 3; i++)
      {
         intarray[i]=Integer.parseInt(datearray[i]);
      }
      try
      {
         fw = new FileWriter(sin+".txt");
         bw = new BufferedWriter(fw);
      }
      catch (IOException ioe)
      {
         
      }
      do
      {
         
         do
         {
            quit = false;
            try
            {
               displayMenu();
               choice = scanner.nextInt();
            }
            catch (InputMismatchException ime)
            {
               System.out.println("Input Exception!");
               quit = true;
               scanner.nextLine();
            }
         } while (quit == true);
         if (choice == 1) // View Acc Activity
         {
            for (int i = 0; i<5; i++)
            {
               if (latest[i]!=null)
               {
                  System.out.println(latest[i]);
                  clear = true;
               }
               else
               {
               }
            }
            if (clear ==false)
            {
               System.out.println("No Prior Entries!");
            }
         }
         else if (choice == 2) // Deposit
         {
            if (chequing.getStatus() == false && savings.getStatus() == false&&credit.getStatus() == false)
            {
               System.out.println("No Accounts Available");
            }
            else
            {
               scanner.nextLine();
               System.out.println("Accounts Available: ");
               if (chequing.getStatus() == true)
               {
                  System.out.println("Chequing");
               }
               if (savings.getStatus() == true)
               {
                  System.out.println("Savings");
               }
               System.out.println("Which account would you like to use? ");
               option = scanner.nextLine();
               if (option.compareTo("Chequing")==0)
               {
                  if (chequing.getStatus() == true)
                  {
                     oldvalue= chequing.getValue();
                     System.out.println("Chequing");
                     System.out.println("Deposit: $");
                     do
                     {
                        quit = false;
                        try
                        {
                           value = scanner.nextDouble();
                           if (value <= 0)
                           {
                              System.out.println("Cannot enter 0 or a negative number!");
                              quit = true;
                           }
                        }
                        catch (InputMismatchException ime)
                        {
                           System.out.println("Input Exception!\nEnter a new Deposit Value: ");
                           quit = true;
                           scanner.nextLine();
                        }
                     } while (quit == true);
                     chequing.deposit(value);
                     insert("Chequing Deposit: $"+value+" Old Balance: " +oldvalue+" New: "+(chequing.getValue()));
                     System.out.println("Chequing Deposit: $"+value+" Old Balance: " +oldvalue+" New: "+(chequing.getValue()));
                  }
               }
               else if (option.compareTo("Savings")==0)
               {
                  if (savings.getStatus() == true)
                  {
                     oldvalue= savings.getValue();
                     System.out.println("Savings");
                     System.out.println("Deposit: $");
                     do
                     {
                        quit = false;
                        try
                        {
                           value = scanner.nextDouble();
                           if (value <= 0)
                           {
                              System.out.println("Cannot enter 0 or a negative number!");
                              quit = true;
                           }
                        }
                        catch (InputMismatchException ime)
                        {
                           System.out.println("Input Exception!\nEnter a new Deposit Value: ");
                           quit = true;
                           scanner.nextLine();
                        }
                     } while (quit == true);
                     savings.deposit(value);
                     insert("Savings Deposit: $"+value+" Old Balance: " +oldvalue+" New: "+(savings.getValue()));
                     System.out.println("Savings Deposit: $"+value+" Old Balance: " +oldvalue+" New: "+(savings.getValue()));
                  }
               }
               else
               {
                  System.out.println("Option not recognized");
               }
            }
         }
         else if (choice == 3) // Withdraw
         {
            if (chequing.getStatus() == false && savings.getStatus() == false)
            {
               System.out.println("No Accounts Available");
            }
            else
            {
               if (chequing.getStatus() == true)
               {
                  System.out.println("Chequing Available");
               }
               if (savings.getStatus() == true)
               {
                  System.out.println("Savings Available");
               }
               scanner.nextLine();
               System.out.println("Which account would you like to use? ");
               option = scanner.nextLine();
               if (option.compareTo("Chequing")==0)
               {
                  if (chequing.getStatus() == true)
                  {
                     oldvalue = chequing.getValue();
                     System.out.println("Chequing");
                     do
                     {
                        quit = false;
                        try
                        {
                           System.out.println("Withdraw: $");
                           value = scanner.nextDouble();
                           if (value <= 0||value>chequing.getValue())
                           {
                              System.out.println("Cannot enter 0, a negative number or a number greater than what you have!");
                              quit = true;
                           }
                        }
                        catch (InputMismatchException ime)
                        {
                           System.out.println("Input Exception!");
                           quit = true;
                           scanner.nextLine();
                        }
                     } while (quit == true);
                     chequing.withdraw(value);
                     insert("Chequing Withdrawl: $"+value+" Old Balance: " +oldvalue+" New: "+(chequing.getValue()));
                     System.out.println("Chequing Withdrawl: $"+value+" Old Balance: " +oldvalue+" New: "+(chequing.getValue()));                     }
               }
               if (option.compareTo("Savings")==0)
               {
                  if (savings.getStatus() == true)
                  {
                     System.out.println("Savings");
                     do
                     {
                        quit = false;
                        try
                        {
                           System.out.println("Withdraw: $");
                           value = scanner.nextDouble();
                           if (value <= 0)
                           {
                              System.out.println("Cannot enter 0 or a negative number!");
                              quit = true;
                           }
                        }
                        catch (InputMismatchException ime)
                        {
                           System.out.println("Input Exception!");
                           quit = true;
                           scanner.nextLine();
                        }
                     } while (quit == true);
                     savings.withdraw(value);
                     insert("Savings Withdrawl: $"+value+" Old Balance: " +oldvalue+" New: "+(savings.getValue()));
                     System.out.println("Savings Withdrawl: $"+value+" Old Balance: " +oldvalue+" New: "+(savings.getValue()));
                  }
               }
               if (option.compareTo("Saving")!=0&&option.compareTo("Chequing")!=0)
               {
                  System.out.println("Option Not Recognized. Returning.");
               }
            }
         }
         else if (choice == 4) // Process Cheque
         {
            oldvalue = chequing.getValue();
            if (chequing.getStatus()==false)
            {
               System.out.println("No Accounts Available");
            }
            else
            {
               chequing.processCheque();
               insert("Chequing Cheque Processed Old Value: "+ oldvalue +" New Value: "+chequing.getValue());
               System.out.println("Chequing Cheque Processed Old Value: "+ oldvalue +" New Value: "+chequing.getValue());
            }
         }
         else if (choice == 5) // Process Purchase
         {
            oldvalue = credit.getValue();
            if (credit.getStatus()==false)
            {
               System.out.println("No Accounts Available");
            }
            else
            {
               insert("Purchase Processed: $"+credit.processPurchase()+" Old Value: "+oldvalue+" New Value: "+credit.getValue());
               System.out.println("Purchase Processed: $"+credit.processPurchase()+" Old Value: "+oldvalue+" New Value: "+credit.getValue());
            }
         }
         else if (choice == 6)// Process Payment
         {
            if (chequing.getStatus()==false&&savings.getStatus()==false)
            {
               System.out.println("No Accounts Available");
            }
            else
            {
               if (credit.getValue() == 0)
               {
                  System.out.println("Nothing Owed on Credit Card!");
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
                  if(savings.getStatus() ==false&&chequing.getStatus() == false)
                  {
                     System.out.println("No Accoutns Available");
                  }
                  System.out.println("Which account would you like? ");
                  option = scanner.nextLine();
                  if((savings.getStatus() ==true)||(chequing.getStatus() == true))
                  {
                     if (option.compareTo("Chequing")==0)
                     {
                        if (chequing.getValue()>0)
                        {
                           do
                           {
                              quit = false;
                              try
                              {
                                 System.out.println("Enter value for transfer: ");
                                 value = scanner.nextDouble();
                                 if (value <= 0 || value >  credit.getValue()*-1||value>chequing.getValue())
                                 {
                                    System.out.println("Cannot enter 0, negative number or pay more than owed!");
                                    quit = true;
                                 }
                              }
                              catch (InputMismatchException ime)
                              {
                                 System.out.println("Input Exception!");
                                 quit = true;
                                 scanner.nextLine();
                              }
                           } while (quit == true);
                           insert("Chequing to Credit Transfer: $"+value+" Chequing: "+chequing.getValue() +" Credit: "+credit.getValue());
                           chequing.withdraw(value);
                           credit.payment(value);
                        }
                        else
                        {
                           System.out.println("Not Enough!");
                        }
                     }
                     else if (option.compareTo("Savings")==0)
                     {
                        if (savings.getValue()>0)
                        {
                           do
                           {
                              quit = false;
                              try
                              {
                                 System.out.println("Enter value for transfer: ");
                                 value = scanner.nextDouble();
                                 if (value <= 0 || value >  credit.getValue()*-1||value>savings.getValue())
                                 {
                                    System.out.println("Cannot enter 0, negative number or pay more than owed!");
                                    quit = true;
                                 }
                              }
                              catch (InputMismatchException ime)
                              {
                                 System.out.println("Input Exception!");
                                 quit = true;
                                 scanner.nextLine();
                              }
                           } while (quit == true);
                           insert("Savings to Credit Transfer: $"+value+" Savings: "+savings.getValue() +" Credit: "+credit.getValue());
                           savings.withdraw(value);
                           credit.payment(value);
                        }
                        else
                        {
                           System.out.println("Not Enough!");
                        }
                     }
                  }
               }
            }
         }
         else if (choice == 7) // Transfer Funds
         {
            if (chequing.getStatus()==false&&savings.getStatus()==false)
            {
               System.out.println("No Accounts Available");
            }
            else if (chequing.getStatus()==false||savings.getStatus()==false)
            {
               System.out.println("Only one of the accounts available");
            }
            else
            {
               scanner.nextLine();
               System.out.println("Transfer from:\n 1. Chequing to Savings\n2. Savings to Chequing ");
               do
               {
                  quit = false;
                  try
                  {
                     intchoice = scanner.nextInt();
                     if (intchoice == 1)
                     {
                        if (chequing.getValue()>0)
                        {
                           do
                           {
                              quit = false;
                              try
                              {
                                 System.out.println("Enter value for transfer: ");
                                 value = scanner.nextDouble();
                                 if (value <= 0 || value >  chequing.getValue())
                                 {
                                    System.out.println("Cannot enter 0, negative number or use more than you have!");
                                    quit = true;
                                 }
                              }
                              catch (InputMismatchException ime)
                              {
                                 System.out.println("Input Exception!");
                                 quit = true;
                                 scanner.nextLine();
                              }
                           } while (quit == true);
                           chequing.withdraw(value);
                           savings.deposit(value);
                           insert("Chequing to Savings Transfer: $"+value+ "Chequing Value: "+chequing.getValue()+" Savings Value: "+savings.getValue());
                        }
                        else
                        {
                           System.out.println("Not Enough!");
                        }
                     }
                     else if (intchoice == 2)
                     {
                        if (savings.getValue()>0)
                        {
                           do
                           {
                              quit = false;
                              try
                              {
                                 System.out.println("Enter value for transfer: ");
                                 value = scanner.nextDouble();
                                 if (value <= 0 || value >  savings.getValue())
                                 {
                                    System.out.println("Cannot enter 0, negative number or use more than you have!");
                                    quit = true;
                                 }
                              }
                              catch (InputMismatchException ime)
                              {
                                 System.out.println("Input Exception!");
                                 quit = true;
                                 scanner.nextLine();
                              }
                           } while (quit == true);
                           savings.withdraw(value);
                           chequing.deposit(value);
                           insert("Savings to Chequing Transfer: $"+value+ "Chequing Value: "+chequing.getValue()+" Savings Value: "+savings.getValue());
                        }
                        else
                        {
                           System.out.println("Not Enough!");
                        }
                     }
                     else
                     {
                        System.out.print("Not an Option Try Again: ");
                        quit = true;
                     }
                  }
                  catch(InputMismatchException ime)
                  {
                     System.out.println("Error! Input Mismatch");
                     quit = true;
                  }
               } while (quit == true);
            }
         }
         else if (choice == 8) //Open Account
         {
            scanner.nextLine();
            System.out.println("Which Account would you like to open? ");
            option = scanner.nextLine();
            try
            {
               if (option.compareTo("Chequing")==0)
               {
                  if (chequing.getStatus() == true)
                  {
                     System.out.println("Already Initialized!");
                  }
                  else
                  {
                     if (intarray[0]-year >18)
                     {
                        chequing.initializeAccount(month, day, year);
                        insert("Chequing Card Account Initalized");
                        System.out.println("Chequing Account Initialized");
                     }
                     else if (intarray[0]- year==18)
                     {
                        if (intarray[1]>month)
                        {
                           chequing.initializeAccount(month, day, year);
                           insert("Chequing Card Account Initalized");
                           System.out.println("Chequing Account Initialized");
                        }
                        else if (intarray[1]== month)
                        {
                           if (intarray[2]>=day)
                           {
                              chequing.initializeAccount(month, day, year);
                              insert("Chequing Card Account Initalized");
                              System.out.println("Chequing Account Initialized");
                           }
                           else
                           {
                              throw new NumberFormatException();
                           }
                        }
                        else
                        {
                           throw new NumberFormatException();
                        }
                     }
                     else
                     {
                        throw new NumberFormatException();
                     }
                  }
                  
               }
               if (option.compareTo("Credit")==0)
               {
                  if (credit.getStatus() == true)
                  {
                     System.out.println("Already Initialized!");
                  }
                  else
                  {
                     if (intarray[0]-year >18)
                     {
                        credit.initializeAccount(month, day, year);
                        insert("Credit Card Account Initalized");
                        System.out.println("Credit Account Initialized");
                     }
                     else if (intarray[0]- year==18)
                     {
                        if (intarray[1]>month)
                        {
                           credit.initializeAccount(month, day, year);
                           insert("Credit Card Account Initalized");
                           System.out.println("Credit Account Initialized");
                        }
                        else if (intarray[1]== month)
                        {
                           if (intarray[2]>=day)
                           {
                              credit.initializeAccount(month, day, year);
                              insert("Credit Card Account Initalized");
                              System.out.println("Credit Account Initialized");
                           }
                           else
                           {
                              throw new NumberFormatException();
                           }
                        }
                        else
                        {
                           throw new NumberFormatException();
                        }
                     }
                     else
                     {
                        throw new NumberFormatException();
                     }
                  }
                  
               }
            }
            catch (NumberFormatException nfe)
            {
               System.out.println("Age is not 18");
            }
            if (option.compareTo("Savings")==0)
            {
               savings.initializeAccount();
               insert("Savings Account Initialized");
               System.out.println("Savings Account Initialized");
            }
         }
         else if (choice == 9) // Cancel Acc
         {
            scanner.nextLine();
            System.out.println("Available things to close: ");
            if (chequing.getStatus() == true)
            {
               System.out.println("Chequing");
            }
            if (savings.getStatus() == true)
            {
               System.out.println("Savings");
            }
            if (credit.getStatus() == true)
            {
               System.out.println("Credit");
            }
            System.out.println("Account");
            System.out.println("Which Account would you like to close? ");
            option = scanner.nextLine();
            if (option.compareTo("Chequing")==0)
            {
               if (chequing.getStatus() == false)
               {
                  System.out.println("Not Initialized");
               }
               else
               {
                  System.out.println("Withdrawn: "+chequing.getValue());
                  chequing.cancel();
                  insert("Chequing Account Canceled");
                  System.out.println("Chequing Account Canceled");
               }
            }
            else if (option.compareTo("Savings")==0)
            {
               if (savings.getStatus() == false)
               {
                  System.out.println("Not Initialized");
               }
               else
               {
                  System.out.println("Withdrawn: "+savings.getValue());
                  savings.cancel();
                  insert("Savings Account Canceled");
                  System.out.println("Savings Account Canceled");
               }
            }
            else if (option.compareTo("Credit")==0)
            {
               if (credit.getStatus() == false)
               {
                  System.out.println("Not Initialized");
               }
               else
               {
                  credit.cancel();
                  insert("Credit Account Canceled");
                  System.out.println("Credit Account Canceled");
               }
            }
            else if (option.compareTo("Account")==0)
            {
               if (credit.getStatus() == true)
               {
                  if (credit.getValue() <0)
                  {
                     System.out.println("Amount owed in Credit Account");
                  }
                  else
                  {
                     System.out.println("\nAmount Withdrawn from Chequing: "+chequing.getValue() + "\nAmount Withdrawn from Savings: "+savings.getValue());
                     chequing.cancel();
                     savings.cancel();
                     credit.cancel();
                  }
               }
               else
               {
                  System.out.println("Account closed\nAmount Withdrawn from Chequing: "+chequing.getValue() + "\nAmount Withdrawn from Savings: "+savings.getValue());
                  chequing.cancel();
                  savings.cancel();
                  credit.cancel();
               }
            }
            else
            {
               System.out.println("Option Not Recognized");
            }
            if (chequing.getStatus() == false && savings.getStatus() == false&&credit.getStatus() == false)
            {
               fname = "null";
               lname = "null";
               chequing = new Chequing ();
               month = 0;
               day = 0;
               year = 0;
               sin = 0;
               Savings savings = new Savings ();
               Credit credit = new Credit ();
               String[] latest = new String[5];
               System.out.println("No Accounts Available, Account Closed");
               break;
            }
         }
         else if (choice == 10)
         {
            if (chequing.getStatus() == true)
            {
               System.out.println("Chequing Exists. Value: "+chequing.getValue());
            }
            if (savings.getStatus() == true)
            {
               System.out.println("Savings Exists. Value: "+savings.getValue());
            }
            if (credit.getStatus() == true)
            {
               System.out.println("Credit Exists. Value: "+credit.getValue());
            }
         }
         else if (choice == 11) // Return
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
      } while ( choice != 11);
   }
}