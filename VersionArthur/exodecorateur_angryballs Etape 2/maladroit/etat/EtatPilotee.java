package etat;

import modele.Bille;
import vues.Billard;

public abstract class EtatPilotee extends Etat {

    public EtatPilotee(Billard billard, Bille bille) {
	super(billard, bille);
    }

}
