package asistentePOO;

public class ListaLlamadasDesocupadoUsuario {
	
	private UnaLlamada[] agendaLlamadas = new UnaLlamada[0];
	
	public ListaLlamadasDesocupadoUsuario() {
	}
	
	public  void removerLlamada(int index) {
		UnaLlamada[] nuevaAgendaLlamadas = new UnaLlamada[agendaLlamadas.length - 1];

		int e = 0;
	    for(int i = 0 ; i < agendaLlamadas.length ; i++) {
	        if(i != index) {
	        	nuevaAgendaLlamadas[e] = agendaLlamadas[i];
	        	e++;
	        }
	    }
	    agendaLlamadas = nuevaAgendaLlamadas;
	}
	
	public  void agregarLlamada(UnaLlamada llamada) {
		UnaLlamada[] nuevasAgendaLlamadas = new UnaLlamada[agendaLlamadas.length + 1];

	    for(int i = 0 ; i < agendaLlamadas.length ; i++) {
	    	nuevasAgendaLlamadas[i] = agendaLlamadas[i];
	        }
	    nuevasAgendaLlamadas[agendaLlamadas.length] = llamada;
	    agendaLlamadas = nuevasAgendaLlamadas;
	}
	
	public  UnaLlamada getLlamada(int index) {
		return agendaLlamadas[index];
	}
	
	public  int getLargo() {
		return agendaLlamadas.length;
	}
	
		
}
