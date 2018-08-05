package factories;

import models.Blob;
import models.behaviuors.AbstractBehaviour;
import models.behaviuors.Aggressive;

public class BlobFactory implements Factory<Blob> {
    @Override
    public Blob create(String[] data) {
        //create Fiki 90 5 Inflated Blobplode
        String name = data[1];
        long health = Integer.parseInt(data[2]);
        long damage = Long.parseLong(data[3]);
        String behaviour = data[4];
        String typeOfAttack = data[5];

        AbstractBehaviour behaviour1 = null;
        switch (behaviour) {
            case "Inflated":

                break;

            case "Aggressive":
                behaviour1 = new Aggressive();
                break;
        }

//        Blob blob = new Blob(name, health, );

        return null;
    }
}
