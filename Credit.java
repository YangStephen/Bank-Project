/* 
Culminating Activity
ICS4U1
@author Stephen Yang
@version 1.0
@since 2015/01/15
Teacher: Ms. Xie
Program Name: Credit.java
Description: Credit is a class used to store the data for a Client's chequing account. This class contains the required methods to initialize, withdraw, transfer and etc. from the Credit account.
*/

// Import Packages
import java.io.*;
import java.util.*;
import java.text.*;

public class Credit
{
// Variable Declaration
   private boolean status = false;
   private double value;
   private Scanner scanner = new Scanner(System.in);
   /**
 * Constructor method for Credit Object
 *
 */
   public Credit ()
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
      status = true;
      value = 0;
   }
   /**
 * Method to initialize Account
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
 * Method to pay remaining debt on the account
 * @param amount Amount to pay
 *
 */
   public void payment(double amount)
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
      value -= amount;
   }
   /**
 * Method to Cancel this account and clear values
 *
 */
   public void cancel()
   {
      if (value == 0)
      {
         status = false;
      }
      else
      {
         System.out.println("Unable to close account! Account still contains unpaid Purachses");
      }
   }
   /**
 * Method to simulate making a purchase
 * @return purchase Amount of purchase
 */
   public double processPurchase()
   {
      double purchase = 0;
      boolean quit = false;
      do
      {
         quit = false;
         try
         {
            System.out.println("Value of Purchase: $");
            purchase = scanner.nextDouble();
         }
         catch (InputMismatchException ime)
         {
            System.out.println("Input Exception!");
            quit = true;
            scanner.nextLine();
         }
      } while (quit == true);
      value-=purchase;
      return purchase;
   }
}