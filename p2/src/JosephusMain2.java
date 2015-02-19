import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class JosephusMain2 {

 private static CLL<String> soldiers;

    public static final void main(String[] args) throws ElementNotFoundException {
     String filename = args[0];

  //creates new file
  File f = new File(filename);
  Scanner in; 
  try{
   in = new Scanner(f);
   soldiers = new CLL<String>();
   while(in.hasNextLine()){
    String nameFile = in.nextLine();
    
    soldiers.add(nameFile);
    soldiers.setCurrentPosition(1);

   }
   in.close();

  }
  catch(FileNotFoundException e){
   System.out.println("Unable to find file");
  }

        Scanner scan = new Scanner(System.in);

       

        while (true) {
            String line = scan.nextLine();
            if (line == null || line.length() == 0)
                continue;

            String[] command = line.split(" ");

            if (command.length == 0)
                continue;

            if (command[0].equalsIgnoreCase("a")) {
                String name = command[1];
                soldiers.add(name);
                System.out.println(soldiers.get(0));
            
            } else if (command[0].equalsIgnoreCase("p")) {
             
             System.out.println(soldiers.print(0));
             
    
            } else if (command[0].equalsIgnoreCase("r")) {
                // add code to implement this option
             try{
              int stepSize = Integer.parseInt(command[1]);
              String direction = command[2];
              int numCycles = Integer.parseInt(command[3]);
              if(stepSize < 0){
               System.out.println("Step size must be " +
                 "greater than or equal to zero");
              }
              if(numCycles < 0){
               System.out.println("Cycle must be " +
                 "greater than or equal to zero");
              }
              int count = 1;
              if(numCycles > 0){
               
   
              }

              CircularLinkedListIterator<String> itr = soldiers.iterator();
              int cycleCounter = 0;
              int countCounter = 0;
              int minSteps = soldiers.size();
              while(cycleCounter != numCycles){
               
               for(int i = soldiers.size(); i > 0; i--){
               
                countCounter++;
                if(countCounter % stepSize == 0){
                    itr.remove();
                    
                   }
                if(countCounter % stepSize !=0)
                {
                 itr.next();
                }
               
               
               
              }
              
              cycleCounter++;
              }
              System.out.println(countCounter + " " + cycleCounter);
              
               
              
             
              
              
              
              
              
              
             }catch(NumberFormatException e){
              System.out.println("Please enter steps followed " +
               "by the direction followed by the number of cycles");
             }
            } else if (command[0].equalsIgnoreCase("x")) {
             System.out.println(soldiers.remove());
//             System.out.println("exit");
//             break;
            } else {
                System.out.println("Unknown command");
            }
        }
    }
    
    private static void murder() throws ElementNotFoundException{
     String murderer = soldiers.get(-1);
     String victim = soldiers.get(0);
     System.out.println(victim + " has been eliminated by " + murderer); 
    }
}
