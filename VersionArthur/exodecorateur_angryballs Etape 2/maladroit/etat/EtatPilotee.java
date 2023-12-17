package etat;

import modele.Bille;
import vues.Billard;
import vues.VueBillard;

public abstract class EtatPilotee extends Etat {

    public EtatPilotee(VueBillard billard, Bille bille) {
    	super(billard, bille);
    }

}
