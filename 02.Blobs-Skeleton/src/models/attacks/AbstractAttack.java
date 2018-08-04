package models.attacks;

import interfaces.Attack;
import models.Blob;

public abstract class AbstractAttack implements Attack {
    //TODO ako shte bude command pattern, to togava shte e po-dobre parametrite da sa kato poleta i ot tam da se raboti! Sushto premesti metodite v interface-a!
    //no puk sushtestvoto shte moje samo tazi ataka da pravi! No puk pri samoto suzdavane na sushtesvoto to shte ima pole vid ataka i kakvoto se podade
    //takova shte ostane!
    private Blob attacker;
    private Blob target;

    public AbstractAttack(Blob attacker, Blob target) {
        this.attacker = attacker;
        this.target = target;
    }

    protected Blob getAttacker() {
        return this.attacker;
    }

    protected Blob getTarget() {
        return this.target;
    }
}
