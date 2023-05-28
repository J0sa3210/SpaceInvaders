package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

public abstract class AbsEnemyBullet extends AbsBullet {

    public AbsEnemyBullet(AbsEnemy e) {
        this.setWidth(2);
        this.setX((e.getX() + e.getWidth() / 2 - this.getWidth() / 2));
        this.setY((e.getY() + e.getHeight()));
        this.setHeight(5);
        this.setSpeed(5);
        this.setDamage(1);
    }

    @Override
    public boolean hasHit(AbsCreature player) {
        return this.getX() >= player.getX() && this.getX() - player.getX() <= player.getWidth() && this.getY() >= player.getY() && this.getY() <= player.getY() + player.getHeight();
    }

    @Override
    public void move(int fieldWidth) {
        this.moveY(this.getSpeed());
    }
}
