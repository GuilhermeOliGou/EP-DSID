import java.rmi.*;
 
public interface Inter extends Remote {
   public String troca0() throws RemoteException;
   public String troca1() throws RemoteException;
   public String troca2() throws RemoteException;
   public String bind() throws RemoteException;
   public String listp() throws RemoteException;
   public String getp(int servConect, int idP) throws RemoteException;
   public String showp() throws RemoteException;
   public String clearlist() throws RemoteException;
   public String addsubpart() throws RemoteException;
   public String addp (int idP, String nomeP, String descP, int servConect) throws RemoteException;
   public String quit() throws RemoteException;
}
