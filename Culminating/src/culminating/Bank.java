/* 
Culminating Activity
ICS4U1
Stephen Yang
Teacher: Ms. Xie
Program Name: Bank.java
Description:
*/

// Import Packages
import java.io.*;
import java.util.*;

public class Bank
{
   private static Client clients[] = new Client[50];
   public static void displayMenu()
   {
      System.out.println("VP Bank; Administrative Console");
      System.out.println("Please choose from one of the following options");
      System.out.println("\t1: Add a member");
      System.out.println("\t2: Remove a member");
      System.out.println("\t3: Sort members by (Last Name, First Name)");
      System.out.println("\t4: Sort by SIN");
      System.out.println("\t5: Display member information");
      System.out.println("\t6: Find a member by name");
      System.out.println("\t7: Find a member by SIN");
      System.out.println("\t8: Quit");
   }
   public static void sort (Client[] i, int size)
   {
      int lowest, farthest = 0;
      Client temp;
      for (int index = 0; index<size-1; index++)
      {
         if (i[index].getName().compareTo("null, null")!=0);
         {
            lowest = index;
            for (int j = index+1; j<size; j++)
            {
               if (i[j].getName().compareTo("null, null")!=0)
               {
                  if (i[j].getName().compareTo(i[lowest].getName())<=0)
                  {
                     lowest=j;
                  }
               }
            }
            if (i[index].getName().compareTo("null, null")!=0)
            {              
               temp = i[index];
               i[index] = i[lowest];
               i[lowest] = temp;
            }
         }
      }
   }
   public static void sortBySin (Client[] i, int size)
   {
      int lowest, farthest = 0;
      Client temp;
      for (int index = 0; index<size-1; index++)
      {
         lowest = index;
         for (int j = index+1; j<size; j++)
         {
            if (i[j].getSin()<i[lowest].getSin())
            {
               lowest=j;
            }
         }
         if (i[index].getName().compareTo("null, null")!=0)
         {              
            temp = i[index];
            i[index] = i[lowest];
            i[lowest] = temp;
         }
      }
   }
   public static void main (String[] args)
   {
      Scanner scanner = new Scanner (System.in);
      int choice = 0;
      int size = 0;
      int sin = 0;
      String name;
      FileWriter fw = null;
      BufferedWriter bw = null;
      for (int i = 0; i<50; i++)
      {
         clients[i] = new Client();
      }
      clients = Profile.readData();
      for (int i = 0; i<50; i++)
      {
         if (clients[i]!=null)
         {
            if (clients[i].getName().compareTo("null, null")!=0) 
            {
               size++;
            }
         }
      }
      do
      {
         displayMenu();
         System.out.print("Command? ");
         choice = scanner.nextInt();
         if (choice == 1)
         {
            clients[size].initialize();
            size++;  
         }
         else if (choice == 2)
         {
            int farthest = -1;
            scanner.nextLine();
            System.out.print("Enter name of client(Last, First): ");
            name = scanner.nextLine();
            for (int i = 0; i<size; i++)
            {
               System.out.println(clients[i].getName());
               if (name.compareTo(clients[i].getName())==0)
               {
                  for (int j = 0; j<size; j++)
                  {           
                     if (clients[j].getName().compareTo("null, null")!=0)
                     {
                        farthest = j;
                     } 
                  }
                  clients[i] = clients[farthest];
                  clients[farthest] = new Client();
                  sort(clients, size);
                  size--;
                  break;
               }
              
            }
         }  
         else if (choice == 3)
         {
            sort(clients, size);
         }     
         else if (choice == 4)
         {
            sortBySin(clients, size);
         } 
         else if (choice == 5)
         {
            for (int i = 0; i<size; i++)
            {
               System.out.println("\tName\t\tBirthday\tSIN");
               System.out.print(i+1+". \t");
               clients[i].getData();
            }
         } 
         else if (choice == 6)
         {
            scanner.nextLine();
            System.out.print("Enter name of client(Last, First): ");
            name = scanner.nextLine();
            for (int i = 0; i<size; i++)
            {
               if (clients[i].getName().compareTo(name)==0)
               {
                  clients[i].profileServiceMenu();
               } 
            } 
         } 
         else if (choice == 7)
         {
            scanner.nextLine();
            System.out.print("Enter SIN of client: ");
            sin = scanner.nextInt();
            for (int i = 0; i<size; i++)
            {
               if (clients[i].getSin()==sin)
               {
                  clients[i].profileServiceMenu();
               } 
            } 
         } 
         else if (choice == 8)
         {
            System.out.println("Exiting Administrative Console");
            try
            {
               fw = new FileWriter("data.txt");
               bw = new BufferedWriter(fw);
            }
            catch (IOException ioe)
            {
            }
            for (int i = 0; i<50; i++)
            {
               try
               {
                  if (clients[i].getName().compareTo("null, null")!=0)
                  {
                     bw.write(clients[i].getAllData());
                  } 
                  
               }
               catch (IOException ioe)
               {
               
               }
            }
            try
            {
               bw.close();
            }
            catch(IOException ioe)
            {
            }
         } 
      }while (choice != 8);
   }
}