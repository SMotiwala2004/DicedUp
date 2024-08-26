public class Enemy {

    private int health;
    private int level;
    private int attackPower;

    public Enemy() {
        this.health = 50;
        this.level = 1;
        this.attackPower = 15;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

}
