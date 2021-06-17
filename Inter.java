import java.rmi.*;
 
public interface Inter extends Remote {
   public String troca0() throws RemoteException;
   public String troca1() throws RemoteException;
   public String troca2() throws RemoteException;
   public String listp(int servConect) throws RemoteException;
   public int getp(int servConect, int idP) throws RemoteException;
   public String showp(int idP, int servConect) throws RemoteException;
   public String clearlist(int idP, int servConect) throws RemoteException;
   public String addsubpart(int idP, int servConect, int nIdSub, String nomeSub, String descSub, int quantSub) throws RemoteException;
   public String addp (int idP, String nomeP, String descP, int servConect) throws RemoteException;
   public String quit() throws RemoteException;
}
