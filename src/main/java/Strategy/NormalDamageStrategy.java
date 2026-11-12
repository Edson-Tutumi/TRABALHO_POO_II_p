package Strategy;

import model.Attack;

public class NormalDamageStrategy implements DamageStrategy {
    @Override
    public int calcularDano(Attack ataque) {
        return ataque.getPower();
    }
}