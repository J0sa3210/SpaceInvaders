package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

public abstract class AbsBullet extends AbsEntity {
    private int damage;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public abstract boolean hasHit(AbsCreature creature);

}
