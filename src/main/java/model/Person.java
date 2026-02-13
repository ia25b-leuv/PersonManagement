package model;

import java.util.UUID;
import java.time.LocalDate;

public class Person {
	private String uuid;
	private String vorname;
	private String nachname;
	private LocalDate geburtsdatum;
	
	public Person(){
	}
	
	public Person(String vorname, String nachname) {
		this.uuid = UUID.randomUUID().toString();
		this.vorname = vorname;
		this.nachname = nachname;
	}
	public Person(String vorname, String nachname, LocalDate geburtsdatum) {
		this.uuid = UUID.randomUUID().toString();
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtsdatum = geburtsdatum;
	}
	public Person(String uuid, String vorname, String nachname, LocalDate geburtsdatum) {
		this.uuid = uuid;
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtsdatum = geburtsdatum;
	}
	public String getUUID() {
		return uuid;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public LocalDate getGeburtsdatum() {
		return geburtsdatum;
	}
	public void setGeburtsdatum(LocalDate geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}
}
