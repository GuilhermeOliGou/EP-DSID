import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
 
public class Servidor implements Inter {
   public Servidor() {}
   public static void main(String[] args) {
      try {
         Servidor server = new Servidor();
         Inter stub = (Inter) UnicastRemoteObject.exportObject(server, 0);
         Registry registry = LocateRegistry.getRegistry();
         registry.bind("Alo", stub);
         System.out.println("Servidor pronto");
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }
   public String bind() throws RemoteException {
      System.out.println("Executando bind()");
      return "Bind";
   }
   public String listp() throws RemoteException {
      System.out.println("Executando listp()");
      return "Listp";
   }
   public String getp() throws RemoteException {
      System.out.println("Executando getp()");
      return "Getp";
   }
   public String showp() throws RemoteException {
      System.out.println("Executando showp()");
      return "Showp";
   }
   public String clearlist() throws RemoteException {
      System.out.println("Executando clearlist()");
      return "ClearList";
   }
   public String addsubpart() throws RemoteException {
      System.out.println("Executando addsubpart()");
      return "AddSubPart";
   }
   public String addp() throws RemoteException {
      System.out.println("Executando addp()");
      return "Addp";
   }
   public String quit() throws RemoteException {
      System.out.println("Executando quit()");
      return "Quit";
   }
}