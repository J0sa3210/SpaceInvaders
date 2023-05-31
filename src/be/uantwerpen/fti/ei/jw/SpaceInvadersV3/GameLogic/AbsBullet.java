package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

public abstract class AbsBullet extends AbsEntity {
    private int damage;
    private SoundComponent sound;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public abstract boolean hasHit(AbsCreature creature);

    public SoundComponent getSound() {
        return this.sound;
    }

    public void setSound(SoundComponent sound) {
        this.sound = sound;
    }
}
