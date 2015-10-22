/* 
Culminating Activity
ICS4U1
@author Stephen Yang
@version 1.0
@since 2015/01/15
Teacher: Ms. Xie
Program Name: Chequing.java
Description: Chequing is a class used to store the data for a Client's chequing account. This class contains the required methods to initialize, withdraw, deposit, transfer and etc. from the chequing account.
*/

// Import Packages
import java.io.*;
import java.util.*;
import java.text.*;

public class Chequing
{
// Variable Declaration
   private boolean status = false;
   private double value;
   private Scanner scanner = new Scanner(System.in);
   /**
 * Constructor method for Chequing Object
 *
 */
   public Chequing ()
   {
   }
   /**
 * Method to initialize Account
 * @param month Month of Birthday.
 * @param day Day of Birthday
 * @param year Year of Birthday
 *
 */
   public void initializeAccount(int month, int day, int year)
   {
      if (status == false)
      {
         DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
         Date date = new Date();
         String timeanddate = df.format(date);
         String[] datearray = new String[3];
         int intarray[] = new int[3];
         datearray = timeanddate.split("/");
         datearray[2]=datearray[2].substring(0,2);
         int age;
         for(int i = 0 ; i < 3; i++)
         {
            intarray[i]=Integer.parseInt(datearray[i]);
         }
         if (intarray[0]-year >18)
         {
            status = true;
            value = 0;
         }
         else if (intarray[0]-year==18)
         {
            if (intarray[1]>month)
            {
               status = true;
               value = 0;
            }
            else if (intarray[1]==month)
            {
               if (intarray[0]>=day)
               {
                  status = true;
                  value = 0;
               }
               else
               {
                  System.out.println("Not 18");
               }
            }
            else
            {
               System.out.println("Not 18");
            }
         }
         else
         {
            System.out.println("Not 18");
         }
      }
      else
      {
         System.out.println("Account previously initialized");
      }
   }
   /**
 * Method to set Value of the account
 * @param value New Value to replace old
 *
 */
   public void setValue(double value)
   {
      status = true;
      this.value = value;
   }
   /**
 * Method to check if account is initialized
 * @return True Account initialized
           False Account not Initialized
 *
 */
   public boolean getStatus()
   {
      return status;
   }
   /**
 * Method to check value of account
 * @return value Value of Account
 *
 */
   public double getValue()
   {
      return value;
   }
   /**
 * Method to deposit a value into account
 * @param amount Amount to add to account
 *
 */
   public void deposit(double amount)
   {
      value += amount;
   }
   /**
 * Method to withdraw from the account
 * @param amount Amount to withdraw from account
 *
 */
   public void withdraw(double amount)
   {
      if (value-amount<0)
      {
         System.out.println("Cannot Withdraw set amount. Exceeds current value");
      }
      else
      {
         value -= amount;
      }
   }
   /**
 * Method to Cancel this account and clear values
 *
 */
   public void cancel()
   {
      status = false;
   }
   /**
 * Method to Process a Chequing/Deposit a Cheque
 *
 */
   public void processCheque()
   {
      double cheque = 0;
      boolean quit = false;
      do
      {
         quit = false;
         try
         {
            System.out.println("Value of Cheque: $");
            cheque = scanner.nextDouble();
         }
         catch (InputMismatchException ime)
         {
            System.out.println("Input Exception!");
            quit = true;
            scanner.nextLine();
         }
      } while (quit == true);
      value+=cheque;
      if (cheque <1000)
      {
         value-=0.15;
      }
   }
}