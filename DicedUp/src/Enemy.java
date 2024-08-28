public class Enemy {

    private int health;
    private int level;
    private int attackPower;
    private String Name;

    public Enemy(int health, int level, int attackPower, String Name) {
        this.health = health;
        this.level = level;
        this.attackPower = attackPower;
        this.Name = Name;
    }

    public Enemy(int level, String name) {
        this((int)(level*10),level, (int) (level*2.5),name);
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    // Setter methods
    //Set Health
    public void addHealth(int health) {
        this.health += health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    //Set Level
    // Getters
    //Get Health
    public int getHealth() {
        return health;
    }
    //Get Level
    public int getLevel() {
        return level;
    }
    //Get Attack Power
    public int getAttackPower() {
        return attackPower;
    }
    //Get Enemy Name
    public String getName() {
        return Name;
    }
}

