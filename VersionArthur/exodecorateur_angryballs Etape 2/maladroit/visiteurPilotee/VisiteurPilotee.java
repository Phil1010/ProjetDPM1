package visiteurPilotee;

import modele.ComportementBillePilotee;

/**
 * Visiteur qui sert pour la bille pilotée
 */
public interface VisiteurPilotee
{
	public abstract void addMouseListener(ComportementBillePilotee cmp);
}
