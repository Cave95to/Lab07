package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	private PowerOutageDAO podao;
	private List<PowerOutages> result;
	private int maxPersone;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

	public List<PowerOutages> calcola(Nerc nerc, Integer durataAnniMax, Integer oreDisservizioMax) {
		
		List<PowerOutages> blackout = this.podao.getPowerOutages(nerc);
		
		if(blackout.size()==0)
			return null;
		
		Collections.sort(blackout);
		
		this.result = new ArrayList<>();
		this.maxPersone = 0;
		
		List<PowerOutages> parziale =  new ArrayList<>();
		
		this.cerca(parziale, blackout, durataAnniMax, oreDisservizioMax);
		
		
		return result;
		
	}

	private void cerca(List<PowerOutages> parziale, List<PowerOutages> blackout, Integer durataAnniMax,
			Integer oreDisservizioMax) {
		
		// se numero persone colpite è più alto, cambiamo il migliore
		int num = this.totPersoneColpite(parziale);
		
		if(num > this.maxPersone) {
			this.maxPersone = num;
			this.result = new ArrayList<>(parziale);
		}
		
		for(PowerOutages p : blackout) {
			if(!parziale.contains(p)) {
				if(aggiuntaPossibile(parziale, p, durataAnniMax, oreDisservizioMax)) {
					parziale.add(p);
					cerca(parziale, blackout, durataAnniMax, oreDisservizioMax);
					parziale.remove(p);		
					
				}
			}
		}		
	}

	private boolean aggiuntaPossibile(List<PowerOutages> parziale, PowerOutages p, Integer durataAnniMax,
			Integer oreDisservizioMax) {
		
		long totMinuti = 0;
		
		for(PowerOutages po : parziale) {
			totMinuti = totMinuti + po.getDurataMinuti();
		}
		
		if(totMinuti+p.getDurataMinuti()<=oreDisservizioMax*60) {
			
			if( parziale.size() == 0 ||
				(parziale.size()>0 && p.getAnnoInizio() - parziale.get(0).getAnnoInizio() <= durataAnniMax)) {
				
				return true;
			}
		
		}
		
		return false;
	}

	private int totPersoneColpite(List<PowerOutages> parziale) {
		int cont = 0;
		for(PowerOutages p : parziale) {
			cont = cont + p.getClienti();
		}
		return cont;
	}

	public int getMaxPersone() {
		return maxPersone;
	}

	public void setMaxPersone(int maxPersone) {
		this.maxPersone = maxPersone;
	}

	public long sumOutageHours() {
		long cont = 0;
		for(PowerOutages p : this.result) {
			cont = cont + p.getDurataMinuti();
		}
		return (cont/60);
	}

}
