package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PowerOutages implements Comparable<PowerOutages>{
	
	private int id;
	private LocalDateTime inizio;
	private LocalDateTime fine;
	private int idNerc;
	private int clienti;
	private long durataMinuti;
	private int annoInizio;
	private int annoFine;
	
	public PowerOutages(int id, LocalDateTime inizio, LocalDateTime fine, int idNerc, int clienti) {
		super();
		this.id = id;
		this.inizio = inizio;
		this.fine = fine;
		this.idNerc = idNerc;
		this.clienti = clienti;
		
		// PER CALCOLO DURATA!!!!
		this.durataMinuti = inizio.until(fine, ChronoUnit.MINUTES);
		
		this.annoInizio = inizio.getYear();
		this.annoFine = fine.getYear();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnnoInizio() {
		return annoInizio;
	}

	public void setAnnoInizio(int annoInizio) {
		this.annoInizio = annoInizio;
	}

	public int getAnnoFine() {
		return annoFine;
	}

	public void setAnnoFine(int annoFine) {
		this.annoFine = annoFine;
	}

	public int getIdNerc() {
		return idNerc;
	}

	public void setIdNerc(int idNerc) {
		this.idNerc = idNerc;
	}

	public int getClienti() {
		return clienti;
	}

	public void setClienti(int clienti) {
		this.clienti = clienti;
	}

	public long getDurataMinuti() {
		return durataMinuti;
	}

	public void setDurataMinuti(long durataMinuti) {
		this.durataMinuti = durataMinuti;
	}

	@Override
	public String toString() {
		return "PowerOutages [id=" + id + ", annoInizio=" + annoInizio + ", annoFine=" + annoFine + ", idNerc=" + idNerc
				+ ", clienti=" + clienti + ", durataMinuti =" + durataMinuti + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutages other = (PowerOutages) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int compareTo(PowerOutages o) {
		return this.inizio.compareTo(o.inizio);
	}

	public LocalDateTime getInizio() {
		return inizio;
	}

	public void setInizio(LocalDateTime inizio) {
		this.inizio = inizio;
	}

	public LocalDateTime getFine() {
		return fine;
	}

	public void setFine(LocalDateTime fine) {
		this.fine = fine;
	}
	
	

}
