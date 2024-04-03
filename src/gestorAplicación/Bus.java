package gestorAplicación;

import java.io.Serializable;
import java.util.ArrayList;

public class Bus implements Serializable {
	private int totalAsientos;
	private String numeroBus;
	
	public int getTotalAsientos() {
		return totalAsientos;
	}
	
	public void setTotalAsientos(int totalAsientos) {
		this.totalAsientos = totalAsientos;
	}
	
	public String getNumeroBus() {
		return numeroBus;
	}
	
	public void setNumeroBus(String numeroBus) {
		this.numeroBus = numeroBus;
	}
	

}
