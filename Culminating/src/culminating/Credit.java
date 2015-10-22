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

public class Credit
{
   private boolean status = false;
   private double value;
   private Scanner scanner = new Scanner(System.in);
   public Credit ()
   {
   
   }
   public void initializeAccount()
   {
      status = true;
      value = 0;
   }
   public void setValue(double value)
   {
      status = true;
      this.value = value;
   }
   public boolean getStatus()
   {
      return status;
   }
   public double getValue()
   {
      return value;
   }
   public void payment(double amount)
   {
      value += amount;
   }
   public void withdraw(double amount)
   {
      value -= amount;
   }
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
   public void processPurchase()
   {
      double purchase = 0;
      System.out.println("Value of Purchase: $");
      purchase = scanner.nextDouble();
      value-=purchase;
   }
}