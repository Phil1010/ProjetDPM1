package visiteurPilotee;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import modele.ComportementBillePilotee;

public class VisiteurPiloteeAWT implements VisiteurPilotee
{	
	private Component c;
	
	public VisiteurPiloteeAWT(Component c)
	{
		this.c = c;
	}
	
	@Override
	public void addMouseListener(ComportementBillePilotee cmp) {
		c.addMouseListener((MouseListener)cmp);
	}

}
