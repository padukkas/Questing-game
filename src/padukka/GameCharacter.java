package padukka;

public class GameCharacter {
    private int s_lastIDNum = 000001;
    private String m_id = "HERO-000001";//number component must be higher than above
    private String m_name = "Jim Gervasi";//cannot be null or blank space
    private int m_maxHP = 1;//must be greater than 0
    private int m_currentHP = 1;//modifying this above the max should return remainder
    private int m_criticalHP;//20% of maxHP rounded up greater than 0
    private float m_currentGold = 0;//two decimal places
   
    GameCharacter(){
        
    }
    GameCharacter(String Class, String name, int maxHP){
        setClass(Class);
        setName(name);
        setMaxHP(maxHP);
    }
    GameCharacter(String Class, String name, int maxHP, float startingGold){
        setClass(Class);
        setName(name);
        setMaxHP(maxHP);
        addGold(startingGold);
    }
    public String getID(){
        return m_id;
    }
    public void setClass(String Class){
        //checking for the user entry, if it is a blank entry throws an exception
        if ("".equals(Class))
           //Illeagal Argument Exception if the user didn't enter any value
            throw new IllegalArgumentException("Class cannot be null value");
            
            //When the user is being promped to enter a class name it checks if it meets the 4 character requirement if not does the default
            if(Class.length() == 4){
            String specialist = Class;
            //specialist = specialist.toUpperCase();
          
            if(specialist.equals("HERO") || specialist.equals("ARCH") || specialist.equals("SWRD") || specialist.equals("RANG") || specialist.equals("DRUD") || specialist.equals("MAGE") || specialist.equals("BARD")){
                //adds one to the id at the end 000001 + 1, the default is s_lastIDNum = 000001
                String num = Integer.toString(s_lastIDNum ++);
                int length = num.length();
                //this if statement looks at the length of the incrementing id
                if(length <= 6){
                    //if the id position hits or exceeds 6 runs the for loop which will take out one 0 and replace ut with the incrementing value 
                    for(int x = 0; x < 6-length; x++){
                        num = "0" + num;
                    }
                }
                //this will check if the id length is longer than 6 cgaracters
               if(num.length() > 6)
                    //if the id is longer than 6 characters it will throw an exception saying it's too long
                    throw new IllegalArgumentException("ID cant exceed 6 chars");
                    m_id = Class.length() + "-" + num;
            
            }else{
                Class = "HERO";
                String num = Integer.toString(s_lastIDNum + 1);
                if(num.length() < 6){
                    for(int x = 0; x < 6-num.length(); x++){
                        num = "0" + num;
                    }
                }
                if(num.length() > 6)
                    throw new IllegalArgumentException("ID cant exceed 6 chars");
                m_id = Class + "-" + num;
            }
           /* }else{
            
                Class = "HERO";
                String num = Integer.toString(s_lastIDNum + 1);
                if(num.length() < 6){
                    for(int x = 0; x < 6-num.length(); x++){
                        num = "0" + num;
               }
            }
              
            if(num.length() > 6)
                throw new IllegalArgumentException("ID cant exceed 6 chars");
            m_id = Class + "-" + num;
        }*/
    }
    }
    public String getName(){
        return m_name;
    }
    public void setName(String name){
        if(name != null || "".equals(name) || name.isEmpty()==false){
            m_name = name;
        }
    }
    public int getMaxHP(){
        return m_maxHP;
    }
    public void setMaxHP(int maxHP){
        
        if(maxHP > 0){
            m_currentHP = maxHP;
            m_maxHP = maxHP;
            
            if(m_maxHP % 5 != 0)
                m_criticalHP = (m_maxHP/5) + 1;
            else
                m_criticalHP = m_maxHP/5;
        }
        else
            throw new IllegalArgumentException("HP Must be more than 0");
    }
    public int getCriticalHP(){
        return m_criticalHP;
    }
    public boolean isCritical(){
        return (m_currentHP <= m_criticalHP);
    }
    public int getHP(){
        return m_currentHP;
    }
    public int adjustHP(int amount){
        int overload = 0;
        if(amount >= 0){
            overload = (m_currentHP + amount) - m_maxHP;
            if(overload < 0)
                overload = 0;
            m_currentHP = m_currentHP + amount - overload;
        }
        else{
            m_currentHP += amount;
        }     
        return overload;
    }
    public float getCurrentGold(){
        return m_currentGold;
    }
    public void addGold(float amount){
        if (amount >= 0){
            m_currentGold += amount;
        }
        if (amount < 0){
            m_currentGold -= amount;
            if(m_currentGold <= 0)
                m_currentGold = 0;
        }
    }
    public boolean hasGold(float amount){
        return (m_currentGold >= amount);
    }
    public boolean trySpendGold(float amount){
        boolean flag = false;
        if (hasGold(amount)){
            m_currentGold -= amount;
            flag = true;
        }
        return flag;
    }
    @Override
    public String toString(){
        String name = m_name;
      
        String print = String.format(m_id + " Name: " + name + " Gold: $" + "%.2f" + " CurrentHP: " + m_currentHP + "/" + m_maxHP , m_currentGold); 
        
        if (isCritical()){
            print = String.format(m_id + " Name: " + name + " Gold: $" + "%.2f" + " CurrentHP: " + m_currentHP + "/" + m_maxHP + " CRITICAL" , m_currentGold);
        }
        return print;
    }
}