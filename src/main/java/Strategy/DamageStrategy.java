package Strategy;

import model.Attack;

public interface DamageStrategy {
    public int calcularDano(Attack ataque);
}