/* 
Culminating Activity
ICS4U1
@author Stephen Yang
@version 1.0
@since 2015/01/15
Teacher: Ms. Xie
Program Name: Bank.java
Description: Bank Class is the main class for which the ICS4U1 culminating in based off of. This class, the main menu(Administrative Menu) and it's required methods. In this menu, You have control over members, Sorting of the array, and searching ability to look throughout the array.
*/

// Import Packages
import java.io.*;
import java.util.*;
import java.text.*;

public class Bank
{
// Variable Declaration
   private static Client clients[] = new Client[50];
   private static int size = 0;
   /**
 * Displays the Menu of VP Bank
 *
 */
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
 /**
 * Checks throughout the array to verify if there is a Duplicate. Used for sorting and verifying the SIN number of a client.
 *
 * @param  name The name of the client in question
 * @return true There are Duplicates
 *         false There are Duplicates
 */
   public static boolean duplicateName(String name)
   {
      int count = 0;
      for (int i = 0; i<50; i++)
      {
         if (clients[i].getName().compareTo("null, null")!=0) 
         {
            if (clients[i].getName().compareTo(name)==0)
            {
               count++;
            }
         }
      }
      if (count>1)
      {
         return true;
      }
      else
      {
         return false;
      }
   }
    /**
 * Checks throughout the array to verify if there is a Duplicate SIN number. 
 *
 * @param  sin The SIN number of the client in question
 * @return true Existing SIN
 *         false SIN not in database yet
 */
   public static boolean existingSin(int sin)
   {
      for (int i = 0; i<50; i++)
      {
         if (clients[i].getName().compareTo("null, null")!=0) 
         {
            if (clients[i].getSin() == sin)
            {
               return true;
            }
         }
      }
      return false;
      
   }
   /**
 * Finds a specific client given name and SIN. 
 *
 * @param  name The name of the client in question
 * @param  sin The SIN number of the client in question
 * @return Client object for that person
 *         null Client does not exist
 */
   public static Client fileDuplicate(String name, int sin)
   {
      int count = 0;
      boolean found = false;
      for (int i = 0; i<50; i++)
      {
         if (clients[i].getName().compareTo("null, null")!=0) 
         {
            if (clients[i].getName().compareTo(name.toUpperCase())==0)
            {
               if (clients[i].getSin()==sin)
               {
                  return clients[i];
               }
            }
         }
      }
      return null;
   }
    /**
 * Returns the location of person found in method fileDuplicate
 *
 * @param  name The name of the client in question
 * @param  sin The SIN number of the client in question
 * @return i Location for that person
 *         -1 Client does not exist
 */
   public static int fileDuplicateLocation(String name, int sin)
   {
      int count = 0;
      boolean found = false;
      for (int i = 0; i<50; i++)
      {
         if (clients[i].getName().compareTo("null, null")!=0) 
         {
            if (clients[i].getName().compareTo(name.toUpperCase())==0)
            {
               if (clients[i].getSin()==sin)
               {
                  return i;
               }
            }
         }
      }
      return -1;
   }
/**
 * Sorts entire array alphabetically. If duplicate, it will sort via SIN number.
 *
 * @param  clients Array of Clients
 * @param  size Size of array
 */
   public static void sort (Client[] clients, int size)
   {
      int lowest = 0, farthest = 0, lowestsin = 0, lowestdifference = 0;
      int count = 0;
      Client temp;
      Client[] values = clients;
      Client[] clientTemp = new Client[50];
      for (int index = 0; index<size-1; index++)
      {
         count = 0;
         if (values[index].getName().compareTo("null, null")!=0);
         {
            lowest = index;
            for (int j = index+1; j<size; j++)
            {
               if (values[j].getName().compareTo("null, null")!=0)
               {
                  if (values[j].getName().compareTo(values[lowest].getName())<=0)
                  {
                     lowest=j;
                  }
               }
            }
            if (values[index].getName().compareTo("null, null")!=0)
            {              
               temp = values[index];
               values[index] =values[lowest];
               values[lowest] = temp;
            }
         }
      }
      for (int i = 0; i<size-1; i++)
      {
         if (duplicateName(values[i].getName()))
         {
            lowest = i;
            for (int j = i+1; j<size; j++)
            {
               if (values[j].getName().compareTo(values[i].getName())==0)
               {
                  if (values[j].getSin()-values[lowest].getSin()<0)
                  {
                     lowest = j;
                  }
               }
            }
            temp = values[lowest];
            values[lowest] = values[i];
            values[i] = temp;
         }
      }
      clients = values;
   }
   /**
 * Moves an array to get rid of a empty Client in the middle of the array
 *
 * @param  location Location of the client
 */
   public static void clearEmpty (int location)
   {
      int lowest, farthest = 0;
      Client temp;
      for (int index = location; index<size; index++)
      {
         if (index != size-1)
         {
            temp =  clients[index+1];
            clients[index] = temp;
         
         }
         else
         {
            clients[index] = new Client();
         }
      }
   }
/**
 * Sorts an array by SIN Number
 *
 * @param  i Array of Clients
 * @param  size Size of array
 */
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
   /**
 * Decreases Size variable for Array
 */
   public static void decreaseSize()
   {
      size-=1;
   }
   /**
 * Main Method: Administrative panel
 * @param args String[] args
 */
   public static void main (String[] args)
   {
      Scanner scanner = new Scanner (System.in);
      int choice = 0;
      int sin = 0;
      boolean quit = false;
      boolean found = false;
      String name;
      int location;
      String filelocation = "";
      Client temp = new Client();
      boolean available = false;
      FileWriter fw = null;
      FileReader fr;
      BufferedWriter bw = null;
      DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
      Date date = new Date();
      String timeanddate = df.format(date);
      String[] datearray = new String[3];
      int intarray[] = new int[3];
      datearray = timeanddate.split("/");
      datearray[2]=datearray[2].substring(0,2);
      do
      {
         try
         {
            System.out.println("Where is the data? ");
            filelocation = scanner.nextLine();
            fr = new FileReader(filelocation);
            quit = false;
         }
         catch (FileNotFoundException fnf)
         {
            System.out.println("File Not Found. Try Again: ");
            quit = true;
         }
      } while (quit == true);
      quit = false;
      for (int i = 0; i<50; i++)
      {
         clients[i] = new Client();
      }
      clients = Profile.readData(filelocation);
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
         do
         {
            found = false;
            quit = false;
            try
            {
               System.out.print("Command? ");
               choice = scanner.nextInt();
            }
            catch (InputMismatchException ime)
            {
               System.out.println("Input Exception!");
               quit = true;
               scanner.nextLine();
            }
         } while (quit == true);
         if (choice == 1)
         {
            if (size >= 50)
            {
               System.out.println("Full");
            }
            else
            {
               clients[size].initialize();
               size++;  
            }
         }
         else if (choice == 2)
         {
            scanner.nextLine();
            System.out.print("Enter name of client(Last, First): ");
            name = scanner.nextLine();
            exitloop:
            for (int i = 0; i<size; i++)
            {
               if ((clients[i].getName().toUpperCase()).compareTo(name.toUpperCase())==0)
               {
                  if (duplicateName(name.toUpperCase())==true)
                  {
                     for (int j = 0; j<50; j++)
                     {
                        if (clients[j].getName().compareTo("null, null")!=0) 
                        {
                           if (clients[j].getName().toUpperCase().compareTo(name.toUpperCase())==0)
                           {
                              System.out.println(clients[j].getName()+"\t\t"+clients[j].getSin());
                           }
                        }
                     }
                     do
                     {
                        System.out.println("exit");
                        quit = false;
                        found = false;
                        try
                        {
                           System.out.print("Duplicate Accounts\nEnter a SIN Number: ");
                           sin = scanner.nextInt();
                           if ( fileDuplicate(name, sin)!=null)
                           {
                              scanner.nextLine();
                              location =fileDuplicateLocation(name, sin);
                              fileDuplicate(name, sin).delete();
                              clearEmpty(location);
                              decreaseSize();
                              quit = false;
                              found = true;
                              break;
                           }
                           else
                           {
                              System.out.println("Not Found!");
                           }
                        }
                        catch (InputMismatchException ime)
                        {
                           System.out.println("Input Exception!");
                           quit = true;
                           scanner.nextLine();
                        }
                     } while (quit == true);
                  }
                  else
                  {
                     for (int j = 0; j<50; j++)
                     {
                        if (clients[j].getName().compareTo("null, null")!=0) 
                        {
                           if (clients[j].getName().toUpperCase().compareTo(name.toUpperCase())==0)
                           {
                              clients[j]=new Client();
                              clearEmpty(j);
                              decreaseSize();
                              found = true;
                              break exitloop;
                              
                           }
                        }
                     }
                  }
                  found = true;
               } 
            } 
            if (found == false)
            {
               System.out.println("Not Found!");
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
            available = false;
            for (int i = 0; i<size; i++)
            {
               if ( i == 0)
               {
                  System.out.println("\tName\t\tBirthday\tSIN");
               }
               if (clients[i].getName().compareTo("null, null")!=0) 
               {
                  System.out.print(i+1+". \t");
                  clients[i].getData();
                  available = true;
               }
            }
            if (available == false)
            {
               System.out.println("No Accounts Available");
            }
         } 
         else if (choice == 6)
         {
            scanner.nextLine();
            System.out.print("Enter name of client(Last, First): ");
            name = scanner.nextLine();
            for (int i = 0; i<size; i++)
            {
               if (found == false)
               {
                  if ((clients[i].getName().toUpperCase()).compareTo(name.toUpperCase())==0)
                  {
                     if (duplicateName(name.toUpperCase())==true)
                     {
                        for (int j = 0; j<50; j++)
                        {
                           if (clients[j].getName().compareTo("null, null")!=0) 
                           {
                              if (clients[j].getName().toUpperCase().compareTo(name.toUpperCase())==0)
                              {
                                 System.out.println(clients[j].getName()+"\t\t"+clients[j].getSin());
                              }
                           }
                        }
                        do
                        {
                           quit = false;
                           try
                           {
                              System.out.print("Duplicate Accounts\nEnter a SIN Number: ");
                              sin = scanner.nextInt();
                              if ( fileDuplicate(name, sin)!=null)
                              {
                                 scanner.nextLine();
                                 location =fileDuplicateLocation(name, sin);
                                 temp = fileDuplicate(name, sin);
                                 temp.profileServiceMenu();
                                 if (temp.getName().compareTo("null, null")==0)
                                 {
                                    clearEmpty(location);
                                    decreaseSize();
                                 }
                                 break;
                              }
                              else
                              {
                                 System.out.println("Not Found!");
                              }
                           }
                           catch (InputMismatchException ime)
                           {
                              System.out.println("Input Exception!");
                              quit = true;
                              scanner.nextLine();
                           }
                        } while (quit == true);
                     }
                     else
                     {
                        System.out.println("1");
                        for (int j = 0; j<50; j++)
                        {
                           if (clients[j].getName().compareTo("null, null")!=0) 
                           {
                              if (clients[j].getName().toUpperCase().compareTo(name.toUpperCase())==0)
                              {
                                 clients[j].profileServiceMenu();
                                 if (clients[j].getName().compareTo("null, null")==0)
                                 {
                                    System.out.println("1");
                                    clearEmpty(j);
                                    decreaseSize();
                                 }
                                 break;
                              }
                           }
                        }
                     }
                     found = true;
                  } 
               }
            } 
            if (found == false)
            {
               System.out.println("Not Found!");
            }
            scanner = new Scanner (System.in);
         } 
         else if (choice == 7)
         {
            do
            {
               try
               {
                  System.out.print("Enter SIN of client: ");
                  sin = scanner.nextInt();
                  quit = false;
               }
               catch (InputMismatchException ime)
               {
                  System.out.println("Input Exception!");
                  quit = true;
                  scanner.nextLine();
               }
            } while (quit == true);
            for (int i = 0; i<size; i++)
            {
               if (clients[i].getSin()==sin)
               {
                  clients[i].profileServiceMenu();
                  if (clients[i].getName().compareTo("null, null")==0)
                  {
                     clearEmpty(i);
                     decreaseSize();
                     break;
                  }
               } 
            } 
            scanner.reset();
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
      }while (choice != 8||choice>8||choice<1);
   }
}