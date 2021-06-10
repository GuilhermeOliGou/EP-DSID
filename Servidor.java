import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
 
public class Servidor implements Inter {

   public Servidor() {}

   static int contP0 = 0;
   static int contP1 = 0;
   static int contP2 = 0;

   public static class Part{
      int id;
      String nome;
      String descricao;
      //se tiver subPart, o id da primeira subPart fica na posicao 0 desse array
      int [] idSubParts = new int [20];
      //e a quantidade dessa sub part fica na posicao 0 nesse array
      int [] quant = new int [20];
      /*exemplo: subpart id 2 tem quantidade 3
      idSubParts[0] = 2
      quant[0] = 3*/

      public Part(int id1, String nome1, String desc){
         this.id = id1;
         this.nome = nome1;
         this.descricao = desc;
         for (int i = 0; i<20; i++){
            this.idSubParts[i] = -1;
            this.quant[i] = -1;
         }
      }
   }

   public static void insereSubPart (Part t, int idSub, int quantSub, int i){
      t.idSubParts [i] = idSub;
      t.quant [i] = quantSub;
   }

   public static void imprimePart (Part t){
      System.out.println("Id: " + t.id);
      System.out.println("Nome: " + t.nome);
      System.out.println("Descricao: " + t.descricao);
      if (t.quant[0] == -1){
         System.out.println("Nao possui subPart");
      } else {
         System.out.println ("Ids de SubPart: ");
         for (int j = 0; j<20; j++){
            if(t.idSubParts[j] != -1){ 
               System.out.println (t.idSubParts[j]);
            }  
         }
      }
   }

   public static class PartRepository{
         String nomeR;
         Part [] repositorio = new Part [100];

         public PartRepository (Part inv, String nm){
            for (int i = 0; i<100; i++){
               this.repositorio[i] = inv;
            }
            this.nome = nm;
         }

         public void insereNoRepositorio (Part t, int i){
            this.repositorio[i] = t;
         }

         public void exibeRepositorio(){
            System.out.println("Parts do repositorio: \n");
            for (int i = 0; i<100; i++){
               if (this.repositorio[i].id != -2){
                  System.out.println(this.repositorio[i].nome);
               }
            }
         }
   }

   public static void main(String[] args) {
      try {
         PartRepository partRep0 = criaRepositorio0();
         Servidor server = new Servidor();
         Inter stub = (Inter) UnicastRemoteObject.exportObject(server, 0);
         Registry registry = LocateRegistry.getRegistry();
         registry.bind("Alo", stub);
         System.out.println("Servidor 0 pronto");
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public static PartRepository criaRepositorio0(String nome){
      Part invalido = new Part (-2, "Part invalido", "Part usado para inicializar repositorio");
      PartRepository repositorio0 = new PartRepository(invalido, nome);
      return repositorio0;
   }

   public static PartRepository getRepositorio (PartRepository pR){
      return pR;
   }

   public String troca1() throws RemoteException {
      System.out.println("Executando troca1()");
       try {
         Part invalido1 = new Part (-2, "Part invalido", "Part usado para inicializar repositorio");
         PartRepository repositorio1 = new PartRepository(invalido1);
         Servidor server1 = new Servidor();
         Inter stub1 = (Inter) UnicastRemoteObject.exportObject(server1, 0);
         Registry registry1 = LocateRegistry.getRegistry();
         registry1.bind("Alo1", stub1);
         System.out.println("Servidor 1 pronto");
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      return "Trocou para o Servidor 1";
   }

   public String troca2() throws RemoteException {
      System.out.println("Executando troca2()");
       try {
         Part invalido2 = new Part (-2, "Part invalido", "Part usado para inicializar repositorio");
         PartRepository repositorio2 = new PartRepository(invalido2);
         Servidor server2 = new Servidor();
         Inter stub2 = (Inter) UnicastRemoteObject.exportObject(server2, 0);
         Registry registry2 = LocateRegistry.getRegistry();
         registry2.bind("Alo2", stub2);
         System.out.println("Servidor 2 pronto");
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      return "Trocou para o Servidor 2";
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

   public void inicializarServidores(String nomeRep){
      PartRepository partRep0 = criaRepositorio0(nomeRep);
   }

   public String addp(int numServ, int idP, String nomeP, String descP) throws RemoteException {
      Part p = new Part (idP, nomeP, descP);
      if (numServ == 0){
         String nomeRe0 = "Repositorio0";
         //inicializarServidores(nomeRe0);
         //PartRepository partRep0 = criaRepositorio0();
         //PartRepository pR0 = getRepositorio(partRep0);
         //pR0.insereNoRepositorio(p, contP0);
         //contP0++;
      }
      String r1 = "Part ";
      String r2 = p.nome;
      String r3 = " adicionada ao Servidor ";
      String r4 = String.valueOf(numServ);
      String rfinal = r1 + r2 + r3 + r4;
      System.out.println("Executando addp()" + rfinal);
      return rfinal;
   }
   public String quit() throws RemoteException {
      System.out.println("Executando quit()");
      return "Quit";
   }
}
