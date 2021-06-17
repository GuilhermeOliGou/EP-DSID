import java.util.Scanner;
import java.rmi.registry.*;
public class Cliente {
   public static int comunica (){
      System.out.println("Possiveis comandos: ");
      System.out.println("0 - Bind - Servidor 0");
      System.out.println("1 - Bind - Servidor 1");
      System.out.println("2 - Bind - Servidor 2");
      System.out.println("3 - GetP");
      System.out.println("4 - ShowP");
      System.out.println("5 - ClearList");
      System.out.println("6 - AddSubPart");
      System.out.println("7 - AddP");
      System.out.println("8 - listP");
      System.out.println("9 - Quit");
      System.out.print("Digite a opcao desejada ");
      Scanner ler = new Scanner(System.in);
      int n = 0;
      n = ler.nextInt();
      return n;
   }

   public static void main(String[] args) {
      String host = (args.length < 1) ? null : args[0];
      try {
         Registry registry = LocateRegistry.getRegistry(host);
         Inter stub = (Inter) registry.lookup("Alo");
         
         Registry registry1 = LocateRegistry.getRegistry(host);
         Inter stub1;
         //Inter stub1 = (Inter) registry1.lookup("Alo1");

         Registry registry2 = LocateRegistry.getRegistry(host);
         Inter stub2;
         //Inter stub2 = (Inter) registry2.lookup("Alo2");

         int n;
         n = comunica();

         int servConect = 0;
        
         do{
            if (n==0){
               stub = (Inter) registry1.lookup("Alo");
               String msg = stub.troca0();
               System.out.println("Mensagem do Servidor: " + msg); 
               n = comunica();
               servConect = 0;
            }   
            if (n==1){
               stub1 = (Inter) registry1.lookup("Alo1");
               String msg = stub1.troca1();
               System.out.println("Mensagem do Servidor: " + msg); 
               n = comunica();
               servConect = 1;
            }   
            if (n==2){
               stub2 = (Inter) registry2.lookup("Alo2");
               String msg = stub2.troca2();
               System.out.println("Mensagem do Servidor: " + msg); 
               n = comunica();
               servConect = 2;
            }   
            if (n==3){
               //Busca uma peca por codigo. A busca eh efetuada no repositorio corrente. Se encontrada,
               //a peca passa a ser a nova peca corrente.
               System.out.println("Qual o id da peca a ser buscada no repositorio corrente? (" + servConect + ")");
               Scanner ler1 = new Scanner(System.in);
               int idP = 0;
               idP = ler1.nextInt();
               String msg = stub.getp(servConect, idP);
               System.out.println("Mensagem do Servidor: " + msg); 
               n = comunica();
            }  
            if (n==4){
               String msg = stub.showp();
               System.out.println("Mensagem do Servidor: " + msg); 
               n = comunica();
            }  
            if (n==5){
               String msg = stub.clearlist();
               System.out.println("Mensagem do Servidor: " + msg); 
               n = comunica();
            }  
            if (n==6){
               String msg = stub.addsubpart();
               System.out.println("Mensagem do Servidor: " + msg); 
               n = comunica();
            }  
            if (n==7){
               System.out.println("Qual sera o ID da Part a ser adicionada no repositorio corrente (" +servConect+")? ");
               Scanner ler4 = new Scanner(System.in);
               int n4 = 0;
               n4 = ler4.nextInt();
               System.out.println("Qual o nome da Part? ");
               Scanner ler2 = new Scanner(System.in);
               String nome;
               nome = ler2.nextLine();
               System.out.println("Qual a descricao da Part? ");
               Scanner ler3 = new Scanner(System.in);
               String desc;
               desc = ler3.nextLine();

               String msg = stub.addp(n4, nome, desc, servConect);

               System.out.println("Mensagem do Servidor: " + msg); 
               n = comunica();
            } 
            if (n==8){
               String msg = stub.listp();
               System.out.println("Mensagem do Servidor: " + msg); 
               n = comunica();
            } 
            if (n==9){
               String msg = stub.quit();
               System.out.println("Mensagem do Servidor: " + msg); 
            }
         }while (n != 9);
      }catch (Exception ex) {
         ex.printStackTrace();
      }
   }  
}
