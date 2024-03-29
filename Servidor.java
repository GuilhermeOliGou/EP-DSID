import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
 
public class Servidor implements Inter {

   PartRepository repositorio;

   public Servidor(PartRepository repositorio) {
      this.repositorio = repositorio;
   }

   static int contP0 = 0;
   static int contP1 = 0;
   static int contP2 = 0;

   static PartRepository repositorio0 = criaRepositorio();
   static PartRepository repositorio1 = criaRepositorio();
   static PartRepository repositorio2 = criaRepositorio();

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
         Part [] repositorio = new Part [100];

         public PartRepository (Part inv){
            for (int i = 0; i<100; i++){
               this.repositorio[i] = inv;
            }
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
         Servidor server = new Servidor(repositorio0);
         Inter stub = (Inter) UnicastRemoteObject.exportObject(server, 0);
         Registry registry = LocateRegistry.getRegistry();
         registry.bind("Alo", stub);
         System.out.println("Servidor 0 pronto");
      } catch (Exception ex) {
         ex.printStackTrace();
      }

      try {
         Servidor server1 = new Servidor(repositorio1);
         Inter stub1 = (Inter) UnicastRemoteObject.exportObject(server1, 0);
         Registry registry1 = LocateRegistry.getRegistry();
         registry1.bind("Alo1", stub1);
         System.out.println("Servidor 1 pronto");
      } catch (Exception ex) {
         ex.printStackTrace();
      }

      try {
         Servidor server2 = new Servidor(repositorio2);
         Inter stub2 = (Inter) UnicastRemoteObject.exportObject(server2, 0);
         Registry registry2 = LocateRegistry.getRegistry();
         registry2.bind("Alo2", stub2);
         System.out.println("Servidor 2 pronto");
      } catch (Exception ex) {
         ex.printStackTrace();
      }

   }

   public static PartRepository criaRepositorio(){
      Part invalido = new Part (-2, "Part invalido", "Part usado para inicializar repositorio");
      PartRepository repositorio = new PartRepository(invalido);
      return repositorio;
   }

   public String troca0() throws RemoteException {
      System.out.println("Executando troca0()");
      return "Trocou para o Servidor 0";
   }

   public String troca1() throws RemoteException {
      System.out.println("Executando troca1()");
      return "Trocou para o Servidor 1";
   }

   public String troca2() throws RemoteException {
      System.out.println("Executando troca2()");
      return "Trocou para o Servidor 2";
   }

   public String bind() throws RemoteException {
      System.out.println("Executando bind()");
      return "Bind";
   }
   public String listp() throws RemoteException {
      System.out.println("Executando listp()");
      //exibeRepositorio();
      return "Listp";
   }
   public String getp(int servConect, int idP) throws RemoteException {
      System.out.println("Executando getp()");
      if (servConect == 0){
         for (int i = 0; i<100; i++){
            if (repositorio0.repositorio[i].id == idP){
               return "Peca " + repositorio0.repositorio[i].nome +" encontrada no repositorio 0";
            }
            else{
               return "Peca nao encontrada no repositorio 0";
            }
         }
      }
      if (servConect == 1){
         for (int i = 0; i<100; i++){
            if (repositorio1.repositorio[i].id == idP){
               return "Peca " + repositorio1.repositorio[i].nome +" encontrada no repositorio 1";
            }
            else{
               return "Peca nao encontrada no repositorio 1";
            }
         }
      }
      if (servConect == 2){
         for (int i = 0; i<100; i++){
            if (repositorio2.repositorio[i].id == idP){
               return "Peca " + repositorio2.repositorio[i].nome +" encontrada no repositorio 2";
            }
            else{
               return "Peca nao encontrada no repositorio 2";
            }
         }
      }
      return "Ocorreu um erro";
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

   public String addp (int idP, String nomeP, String descP, int servConect) throws RemoteException {
      Part p = new Part (idP, nomeP, descP);

      //System.out.println(numServ);
      boolean execOk = false;

      if (servConect == 0){ 
         repositorio0.insereNoRepositorio(p,contP0);
         //System.out.println("Entrou no 0");
         contP0++;
         execOk = true;
      }
      if (servConect == 1){
         repositorio1.insereNoRepositorio(p,contP1);
         //System.out.println("Entrou no 1");
         contP1++;
         execOk = true;
      }
      if (servConect == 2){
         repositorio2.insereNoRepositorio(p,contP2);
         //System.out.println("Entrou no 2");
         contP2++;
         execOk = true;
      } 

      /*
      repositorio0.exibeRepositorio();
      repositorio1.exibeRepositorio();
      repositorio2.exibeRepositorio();
      */
      if (execOk == true){
         String r1 = "Part ";
         String r2 = p.nome;
         String r3 = " adicionada ao Servidor ";
         String r4 = String.valueOf(servConect);
         String rfinal = r1 + r2 + r3 + r4;
         System.out.println("Executando addp() " + rfinal);
         return rfinal;
      } else{
         return "Ocorreu um erro";
      }   
   }

   public String quit() throws RemoteException {
      System.out.println("Executando quit()");
      return "Quit";
   }
}
