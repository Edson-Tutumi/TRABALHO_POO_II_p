package model;

public class AttackBuilder {
    private int attackId;
    private String attackName;
    private int attackPower;
    private String attackEffect;
    private int attackAccuracy;
    private String attackType;

    public AttackBuilder setAttackId(int attackId) {
        this.attackId = attackId;
        return this;
    }

    public AttackBuilder setAttackName(String attackName) {
        this.attackName = attackName;
        return this;
    }

    public AttackBuilder setAttackPower(int attackPower) {
        this.attackPower = attackPower;
        return this;
    }

    public AttackBuilder setAttackEffect(String attackEffect) {
        this.attackEffect = attackEffect;
        return this;
    }

    public AttackBuilder setAttackAccuracy(int attackAccuracy) {
        this.attackAccuracy = attackAccuracy;
        return this;
    }

    public AttackBuilder setAttackType(String attackType) {
        this.attackType = attackType;
        return this;
    }

    public Attack build() {
        Attack attack = new Attack();
        attack.setAttackId(attackId);
        attack.setAttackName(attackName);
        attack.setAttackPower(attackPower);
        attack.setAttackEffect(attackEffect);
        attack.setAttackAccuracy(attackAccuracy);
        attack.setAttackType(attackType);
        return attack;
    }
}