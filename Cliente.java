import java.util.Scanner;
import java.rmi.registry.*;
public class Cliente {
   public static void main(String[] args) {
      String host = (args.length < 1) ? null : args[0];
      Scanner ler = new Scanner(System.in);
      int n;
      System.out.println("Possiveis comandos: ");
      System.out.println("1 - Bind");
      System.out.println("2 - ListP");
      System.out.println("3 - GetP");
      System.out.println("4 - ShowP");
      System.out.println("5 - ClearList");
      System.out.println("6 - AddSubPart");
      System.out.println("7 - AddP");
      System.out.println("8 - Quit");
      System.out.print("Digite a opcao desejada ");
      n = ler.nextInt();
      try {
         Registry registry = LocateRegistry.getRegistry(host);
         Inter stub= (Inter) registry.lookup("Alo");
         if (n==1){
            String msg = stub.bind();
            System.out.println("Mensagem do Servidor: " + msg); 
         }   
         if (n==2){
            String msg = stub.listp();
            System.out.println("Mensagem do Servidor: " + msg); 
         }  
         if (n==3){
            String msg = stub.getp();
            System.out.println("Mensagem do Servidor: " + msg); 
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
            String msg = stub.addp();
            System.out.println("Mensagem do Servidor: " + msg); 
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