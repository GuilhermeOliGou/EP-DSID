import java.rmi.*;
 
public interface Inter extends Remote {
   public String bind() throws RemoteException;
   public String listp() throws RemoteException;
   public String getp() throws RemoteException;
   public String showp() throws RemoteException;
   public String clearlist() throws RemoteException;
   public String addsubpart() throws RemoteException;
   public String addp() throws RemoteException;
   public String quit() throws RemoteException;
}