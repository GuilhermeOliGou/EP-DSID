import java.util.Scanner;
import java.rmi.registry.*;
public class Cliente {
   public static int comunica (){
      System.out.println("Possiveis comandos: ");
      System.out.println("1 - Bind - Servidor 1");
      System.out.println("2 - Bind - Servidor 2");
      System.out.println("3 - GetP");
      System.out.println("4 - ShowP");
      System.out.println("5 - ClearList");
      System.out.println("6 - AddSubPart");
      System.out.println("7 - AddP");
      System.out.println("8 - Quit");
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

         int n = comunica();
         if (n==1){
            n=0;
            String msg = stub.troca1();
            System.out.println("Mensagem do Servidor: " + msg); 
            try {
               Registry registry1 = LocateRegistry.getRegistry(host);
               Inter stub1 = (Inter) registry1.lookup("Alo1");
            } catch (Exception ex) {
            ex.printStackTrace();
             } 
            n = comunica();
         }   
         if (n==2){
            n=0;
            String msg = stub.troca2();
            System.out.println("Mensagem do Servidor: " + msg); 
            try {
               Registry registry2 = LocateRegistry.getRegistry(host);
               Inter stub2 = (Inter) registry2.lookup("Alo2");
            } catch (Exception ex) {
            ex.printStackTrace();
             } 
            n = comunica();
         }   
         /*if (n==2){
            String msg = stub.listp();
            System.out.println("Mensagem do Servidor: " + msg); 
         } 
         */ 
         if (n==3){
            n=0;
            String msg = stub.getp();
            System.out.println("Mensagem do Servidor: " + msg); 
            n = comunica();
         }  
         if (n==4){
            String msg = stub.showp();
            System.out.println("Mensagem do Servidor: " + msg); 
         }  
         if (n==5){
            String msg = stub.clearlist();
            System.out.println("Mensagem do Servidor: " + msg); 
         }  
         if (n==6){
            String msg = stub.addsubpart();
            System.out.println("Mensagem do Servidor: " + msg); 
         }  
         if (n==7){
            System.out.println("Em qual Servidor adicionar a Part? 0, 1 ou 2? ");
            Scanner ler1 = new Scanner(System.in);
            int n1 = 0;
            n1 = ler1.nextInt();
            System.out.println("Qual sera o ID da Part? ");
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

            String msg = stub.addp(n1, n4, nome, desc);
            
            System.out.println("Mensagem do Servidor: " + msg); 
            n = comunica();
         }  
         if (n==8){
            String msg = stub.quit();
            System.out.println("Mensagem do Servidor: " + msg); 
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }  
}
