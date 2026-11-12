package Strategy;

import model.Attack;

public class CriticalDamageStrategy implements DamageStrategy {
    @Override
    public int calcularDano(Attack ataque) {
        return ataque.getPower() * 2;
    }
}