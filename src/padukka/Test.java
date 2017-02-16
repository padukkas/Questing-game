package padukka;

import java.util.Scanner;

public class Test {
    public static boolean userOp = false;
    public static boolean choice = true;
   
    public static void main(String[] args){
        Scanner userInput = new Scanner(System.in);
        
        int userEntry;
        
        while(choice){
            GameCharacter player = new GameCharacter();
            
            InitializeCharacter(player, userInput);
            userOp = true;
            
            
            while(userOp){
                System.out.println(player.toString());
                String input = "";
                
                if(player.isCritical()){
                    System.out.println("Your Options are...\n 1) Buy a Potion\n" + " 2) Retire After One Last Quest");
                    userEntry = userInput.nextInt();
                    
                    switch(userEntry){
               
                        case 1:
                        System.out.println(BuyPotion(player));
                        
                        break;
                        
                        case 2:
                        FinalQuest(player, userInput);
                        
                        break;
                    }
                }
                else{
                    System.out.println("Your Options are \n1 - Buy a Potion\n" + "2 - Go Questing\n3 - Retire After One Last Quest");
                    userEntry = userInput.nextInt();

                    switch(userEntry){
                    
                    case 1:
                    	System.out.println(BuyPotion(player));                     	                 	
                    	break;
                    case 2:
                    	System.out.println(GoQuesting(player));
                    	break;
                    case 3:
                    	FinalQuest(player, userInput);
                    	break;
                    }
                }
            }
        }
    }
        public static void InitializeCharacter(GameCharacter p, Scanner userInput){
        boolean flag = true;
        
        flag = true;
        while(flag){
            System.out.println("Please enter a name for your character: ");
            String name = userInput.nextLine();
            try{
                p.setName(name);
                flag = false;
            }
            catch(IllegalArgumentException e){
                System.out.println("swhatawfwafw");
                flag = true;
            }
        }
        flag = true;
        while(flag){
            System.out.println("Please enter a class for your character: ");
            String Class = userInput.nextLine();
            try{
                p.setClass(Class);
                flag = false;
            }
            catch(IllegalArgumentException e){
                System.out.println("Please enter a Class name to procceed ");
                flag = true;
            }
        }
        flag = true;
        while(flag){
            System.out.println("Please enter the max HP for your character: ");
            int HP = userInput.nextInt();
            try{
                p.setMaxHP(HP);
                flag = false;
            }
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                flag = true;
            }
        }
    }
        
    public static String BuyPotion(GameCharacter warrior){
        String finalAnswer = "";
        if(warrior.trySpendGold((float)1.00)){
            int amount = (int)(Math.random() * 5) + 6;
            int remainder = warrior.adjustHP(amount);
            if(remainder == 0){
                finalAnswer = "All " + amount + " points of the potion were used.";
            }
            if(remainder > 0 && remainder < 6){
                finalAnswer = "HP is fully restored. "  + amount + " points were wasted.";
            }
            if(remainder >= 6){
                finalAnswer = "HP is fully restored. " + amount + " points were wasted. MaxHP goes up by 2!";
                warrior.setMaxHP(warrior.getMaxHP() + 2);
            }
        }
        else
            finalAnswer = "You cannot afford a potion!";
        return finalAnswer;
    }
    public static String GoQuesting(GameCharacter c){
        String result = "";
        int amount = ((int)(Math.random() * 20) - 20);
        float r = (float)((int)(Math.random() * 50) + 151)/100;
        c.addGold(r);
        if(c.adjustHP(amount) < 0){
            EndGame(c, 0);
        }
        else{
            result = ("You Lost " + amount + " points of HP." 
                    + "In exchange, you obtained $" + r + ".");
        }
        return result;
    }
    public static void FinalQuest(GameCharacter c, Scanner s){
        System.out.println("Why are you retiring?\n1 - I'm getting too old for" + " this...\n2 - I promised my family this would be the last" + " time...\n3 - It's one last suicide mission, with one in a " + "million odds of success...");
        
        String input = "";
        int loss;
       
        while(input.equals("1") == false && input.equals("2") == false && input.equals("3") == false){
                    input = s.nextLine();
                } 
         
       if(input.equals("1")){
                    loss = ((int)(Math.random() * (c.getMaxHP() -c.getCriticalHP())));
                    c.addGold((float)15.00);
                    EndGame(c, (c.adjustHP(loss-(loss*2))));
                }
                if(input.equals("2")){
                    loss = ((int)(Math.random() * (c.getMaxHP() - (c.getMaxHP()/2)) + (c.getMaxHP()/2)));
                    
                    //.c.addGold((float)((c.getCurrentGold())));
                    EndGame(c, ((c.adjustHP(loss-(loss*2)))));
                }
                if(input.equals("3")){
                    loss = ((int)(Math.random() * c.getCriticalHP()));
                    if((int)(Math.random()*2) == 1){
                        c.addGold((float)((int)(Math.random() * 1401) -500)/100);
                    }
                    EndGame(c, ((c.adjustHP(loss-(loss*2)))));
                }  
    }
    public static void EndGame(GameCharacter c, int x){
        String result = "";
        int points = 0;
        //points += (c.getCurrentGold()*5);
            points = ((c.getHP() - c.getCriticalHP()));
        result = c.getName() + ", Your Adventure is over.\nYou earned " + points + " points during your questing.";
        
        System.out.println(result);
        
        boolean flag = true;
        
        Scanner s = new Scanner(System.in);
        while(flag){
            System.out.println("\n\nWould you like to play again? Y/N.");
            String response = (s.nextLine()).toUpperCase();
            if(response.equals("N")){
                System.exit(0);
            }
            if(response.equals("Y")){
                userOp = false;
                flag = false;
            }
     
        }
    }
} 