package model;

public class Attack {
    private int attackId;
    private String attackName;
    private int attackPower;
    private String attackEffect;
    private int attackAccuracy;
    private String attackType;

    public Attack() {
    }

    public Attack(int attackId, String attackName, int attackPower, String attackType, int attackAccuracy, String attackEffect) {
        this.attackId = attackId;
        this.attackName = attackName;
        this.attackPower = attackPower;
        this.attackType = attackType;
        this.attackAccuracy = attackAccuracy;
        this.attackEffect = attackEffect;
    }

    public int getAttackId() {
        return attackId;
    }
    public void setAttackId(int attackId) {
        this.attackId = attackId;
    }

    public String getAttackName() {
        return attackName;
    }
    public void setAttackName(String attackName) {
        this.attackName = attackName;
    }

    public int getAttackPower() {
        return attackPower;
    }
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public String getAttackEffect() {
        return attackEffect;
    }
    public void setAttackEffect(String attackEffect) {
        this.attackEffect = attackEffect;
    }

    public int getAttackAccuracy() {
        return attackAccuracy;
    }
    public void setAttackAccuracy(int attackAccuracy)
    { this.attackAccuracy = attackAccuracy;
    }

    public String getAttackType() {
        return attackType;
    }
    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    @Override
    public String toString(){
        return "Id: " + attackId + " Name: " + attackName + "Power: " + attackPower + " Type: " + attackType + " Accuracy: " + attackAccuracy + " Effect: " + attackEffect;
    }
}