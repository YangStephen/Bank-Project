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

public class Profile
{
   public static Client[] readData()
   {
      Client[] clients = new Client[50];
      int size = 0;
      for (int i = 0; i<50; i++)
      {
         clients[i] = new Client();
      }
      try
      {
         
         String last, first, sin, by, bm, bd, cheq, sav, cred, current; 
         FileReader fr = new FileReader ("data.txt");
         BufferedReader br = new BufferedReader (fr);
         while ((current=br.readLine())!=null)
         {
            last = current;
            first = br.readLine();
            sin = br.readLine();
            by = br.readLine();
            bm = br.readLine();
            bd = br.readLine();
            cheq = br.readLine();
            sav = br.readLine();
            cred = br.readLine();
            clients[size] = new Client(last, first, Integer.parseInt(sin), Integer.parseInt(by), Integer.parseInt(bm), Integer.parseInt(bd), cheq, sav, cred);
            size++;
         }
      }
      catch (IOException ioe)
      {
      
      }
      return clients;
   }
}