import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
 
public class Servidor implements Inter {

   PartRepository repositorio;

   public Servidor(PartRepository repositorio) {
      this.repositorio = repositorio;
   }

   static int erro = -1;

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
      int contSub = 0;
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

   public Part getPart(int idPart, PartRepository repositorioA){
      for (int i = 0; i<100; i++){
         if (repositorioA.repositorio[i].id == idPart){
            return(repositorioA.repositorio[i]);
         }
      }
      Part invalido = new Part (-3, "Part invalido", "Part usado para retornar false");
      return invalido;
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

   public String listp(int servConect) throws RemoteException {
      System.out.println("Executando listp()");
      String resposta0 = "Pecas do repositorio 0: ";
      String resposta1 = "Pecas do repositorio 1: ";
      String resposta2 = "Pecas do repositorio 2: ";
      if (servConect == 0){
         for (int i = 0; i<100; i++){
            if (repositorio0.repositorio[i].id != -2){
               //System.out.println(repositorio0.repositorio[i].nome);
               resposta0 += repositorio0.repositorio[i].nome;
               resposta0 += " ";
            }
         }
         return resposta0;
      }
      if (servConect == 1){
         for (int i = 0; i<100; i++){
            if (repositorio1.repositorio[i].id != -2){
               //System.out.println(repositorio0.repositorio[i].nome);
               resposta1 += repositorio1.repositorio[i].nome;
               resposta1 += " ";
            }
         }
         return resposta1;
      }
      if (servConect == 2){
         for (int i = 0; i<100; i++){
            if (repositorio2.repositorio[i].id != -2){
               //System.out.println(repositorio0.repositorio[i].nome);
               resposta2 += repositorio2.repositorio[i].nome;
               resposta2 += " ";
            }
         }
         return resposta2;
      }
      return "Ocorreu um erro";
   }

   public int getp(int servConect, int idP) throws RemoteException {
      System.out.println("Executando getp()");
      if (servConect == 0){
         for (int i = 0; i<100; i++){
            if (repositorio0.repositorio[i].id == idP){
               System.out.println("Achou - Servidor 0");
               return repositorio0.repositorio[i].id;
            }
         }
      }
      if (servConect == 1){
         for (int i = 0; i<100; i++){
            if (repositorio1.repositorio[i].id == idP){
               System.out.println("Achou - Servidor 1");
               return repositorio1.repositorio[i].id;
            }
         }
      }
      if (servConect == 2){
         for (int i = 0; i<100; i++){
            if (repositorio2.repositorio[i].id == idP){
               System.out.println("Achou - Servidor 2");
               return repositorio2.repositorio[i].id;
            }
         }
      }
      return erro;
   }

   public String showp(int idP, int servConect) throws RemoteException {
      System.out.println("Executando showp()");
      String resp;
      int idPeca;
      String nomePeca;
      String descPeca;
      String subPart;

      if (servConect == 0){
         for (int i = 0; i<100; i++){
            if (repositorio0.repositorio[i].id == idP){
               idPeca = repositorio0.repositorio[i].id;
               nomePeca = repositorio0.repositorio[i].nome;
               descPeca = repositorio0.repositorio[i].descricao;
               if(repositorio0.repositorio[i].quant[0] == -1){
                  subPart = " e nao possui subPart";
               } else {
                  subPart = " e possui as subParts de id: ";
                  for (int j = 0; j<20; j++){
                     if(repositorio0.repositorio[i].idSubParts[j] != -1){ 
                        subPart += repositorio0.repositorio[i].idSubParts[j];
                        subPart += " ";
                     }  
                  }
               }
               resp = "id da Peca: "+idPeca+" nome : "+nomePeca+" descricao: "+descPeca+subPart;
               return resp;
            }
         }
      }
      if (servConect == 1){
         for (int i = 0; i<100; i++){
            if (repositorio1.repositorio[i].id == idP){
               idPeca = repositorio1.repositorio[i].id;
               nomePeca = repositorio1.repositorio[i].nome;
               descPeca = repositorio1.repositorio[i].descricao;
               if(repositorio0.repositorio[i].quant[0] == -1){
                  subPart = " e nao possui subPart";
               } else {
                  subPart = " e possui as subParts de id: ";
                  for (int j = 0; j<20; j++){
                     if(repositorio1.repositorio[i].idSubParts[j] != -1){ 
                        subPart += repositorio1.repositorio[i].idSubParts[j];
                        subPart += " ";
                     }  
                  }
               }
               resp = "id da Peca: "+idPeca+" nome : "+nomePeca+" descricao: "+descPeca+subPart;
               return resp;
            }
         }
      }
      if (servConect == 2){
         for (int i = 0; i<100; i++){
            if (repositorio2.repositorio[i].id == idP){
               idPeca = repositorio2.repositorio[i].id;
               nomePeca = repositorio2.repositorio[i].nome;
               descPeca = repositorio2.repositorio[i].descricao;
               if(repositorio0.repositorio[i].quant[0] == -1){
                  subPart = " e nao possui subPart";
               } else {
                  subPart = " e possui as subParts de id: ";
                  for (int j = 0; j<20; j++){
                     if(repositorio2.repositorio[i].idSubParts[j] != -1){ 
                        subPart += repositorio2.repositorio[i].idSubParts[j];
                        subPart += " ";
                     }  
                  }
               }
               resp = "id da Peca: "+idPeca+" nome : "+nomePeca+" descricao: "+descPeca+subPart;
               return resp;
            }
         }
      }
      return "Ocorreu um erro";
   }

   public String clearlist(int idP, int servConect) throws RemoteException {
      System.out.println("Executando clearlist()");
      if (idP == -1) return "Part corrente invalida :/ Primeiro busque uma Part valida com a funcao 3 (GetP)";
      if (servConect == 0){
         Part pRef = getPart(idP, repositorio0);
         for (int j = 0; j<20; j++){
            pRef.idSubParts[j] = -1;
            pRef.quant[j] = -1;
            return "Lista de SubParts da Peca corrente limpa";
         }
      }
      if (servConect == 1){
         Part pRef = getPart(idP, repositorio1);
         for (int j = 0; j<20; j++){
            pRef.idSubParts[j] = -1;
            pRef.quant[j] = -1;
            return "Lista de SubParts da Peca corrente limpa";
         }
      }
      if (servConect == 2){
         Part pRef = getPart(idP, repositorio2);
         for (int j = 0; j<20; j++){
            pRef.idSubParts[j] = -1;
            pRef.quant[j] = -1;
            return "Lista de SubParts da Peca corrente limpa";
         }
      }
      return "Ocorreu um erro";
   }

   public String addsubpart(int idP, int servConect, int nIdSub, String nomeSub, String descSub, int quantSub) throws RemoteException {
      System.out.println("Executando addsubpart()");
      if (idP == -1) return "Part corrente invalida :/ Primeiro busque uma Part valida com a funcao 3 (GetP)";
      Part subP = new Part (nIdSub, nomeSub, descSub);
      if (servConect == 0){
         Part pRef = getPart(idP, repositorio0);
         for (int j = 0; j<20; j++){
            if (pRef.idSubParts[j] == nIdSub) return "Ja existe uma subPart nessa peca com esse id :/";
         }
         for (int i = 0; i<100; i++){
            if (repositorio0.repositorio[i].id == idP){
               insereSubPart (pRef, nIdSub, quantSub, pRef.contSub);
               pRef.contSub ++;
            }
            return "SubPart(s) adicionada(s) a peca corrente :)";
         }
      }
      if (servConect == 1){
         Part pRef = getPart(idP, repositorio1);
         for (int j = 0; j<20; j++){
            if (pRef.idSubParts[j] == nIdSub) return "Ja existe uma subPart nessa peca com esse id :/";
         }
         for (int i = 0; i<100; i++){
            if (repositorio1.repositorio[i].id == idP){
               insereSubPart (pRef, nIdSub, quantSub, pRef.contSub);
               pRef.contSub ++;
            }
            return "SubPart(s) adicionada(s) a peca corrente :)";
         }
      }
      if (servConect == 2){
         Part pRef = getPart(idP, repositorio2);
         for (int j = 0; j<20; j++){
            if (pRef.idSubParts[j] == nIdSub) return "Ja existe uma subPart nessa peca com esse id :/";
         }
         for (int i = 0; i<100; i++){
            if (repositorio2.repositorio[i].id == idP){
               insereSubPart (pRef, nIdSub, quantSub, pRef.contSub);
               pRef.contSub ++;
            }
            return "SubPart(s) adicionada(s) a peca corrente :)";
         }
      }
      return "Ocorreu um erro";
   }

   public String addp (int idP, String nomeP, String descP, int servConect) throws RemoteException {
      if (servConect == 0){
         for (int i = 0; i<100; i++){
            if (repositorio0.repositorio[i].id == idP){
               return "Esse id ja existe para uma peca desse repositorio :/";
            }
         }
      }  
      if (servConect == 1){
         for (int i = 0; i<100; i++){
            if (repositorio1.repositorio[i].id == idP){
               return "Esse id ja existe para uma peca desse repositorio :/";
            }
         }
      }  
      if (servConect == 2){
         for (int i = 0; i<100; i++){
            if (repositorio2.repositorio[i].id == idP){
               return "Esse id ja existe para uma peca desse repositorio :/";
            }
         }
      }   

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
