package modele;

import java.util.Observable;

public class ObserveurBilles extends Observable {
	
	public ObserveurBilles() {
		
	}
	
	@Override
	public synchronized void setChanged() {
		// TODO Auto-generated method stub
		super.setChanged();
	}

}
