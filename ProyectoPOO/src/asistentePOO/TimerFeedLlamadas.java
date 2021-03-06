package asistentePOO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimerTask;

import rss.RSSFeedReader;
import rss.RSSItem;
import rss.stores.ArrayListRSSFeedStore;
import java.util.ArrayList;

public class TimerFeedLlamadas extends TimerTask {                                           							//Clase Timer
	
	
	
    public void run() {
    	
    	ArrayListRSSFeedStore feedStore = new ArrayListRSSFeedStore();
		RSSFeedReader.read("http://rss.cnn.com/rss/edition_world.rss", feedStore);
		ArrayList<RSSItem> list = feedStore.getList();
		Random random = new Random();
    	
    	for(int i = 0; i < MainAsistente.getAgendaUsuarios().getLargo(); i ++) {
    		UnUsuario usuarioActualizacionFeedLlamadas = MainAsistente.getAgendaUsuarios().getUsuario(i);  
    		
    		
    		if(random.nextInt(10) > 7) {
	    		String nombreRandom = "";
	    	    for(int s = 0; s < 10; s++) {
	    	        char letra = (char) ('a' + random.nextInt(26));
	    	        nombreRandom = nombreRandom + letra;
	    	    }
	    	    
	    		String numeroRandom = "";
	    	    for(int s = 0; s < 8; s++) {
	    	        int numero = random.nextInt(9);
	    	        numeroRandom = numeroRandom + numero;
	    	    }
	    		
				Calendar fechaHoraActual = Calendar.getInstance();
				Date fechaHoraActualDate = fechaHoraActual.getTime();	
	    		UnaLlamada nuevaLlamada = new UnaLlamada(nombreRandom, numeroRandom, fechaHoraActualDate);
	    			if(usuarioActualizacionFeedLlamadas.getEstado() == EstadoUsuario.DESOCUPADO) {
	    				usuarioActualizacionFeedLlamadas.getLlamadasDesocupado().agregarLlamada(nuevaLlamada);
	    				if(usuarioActualizacionFeedLlamadas.equals(MainAsistente.getUsuarioActual())) { 
	    						String horaFechaString  = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(nuevaLlamada.getFechaHora());  
	    						MainAsistente.getLlamadasListaDefault().addElement("<html>" + nuevaLlamada.getNombre() + "<br>" +  nuevaLlamada.getNumero() + "<br>" + horaFechaString + "</html>");             
	    						MainAsistente.getLlamadasLista().setModel(MainAsistente.getLlamadasListaDefault());                                                    
	    		    	}
	    		    }else {
	    		    	usuarioActualizacionFeedLlamadas.getLlamadasOcupado().agregarLlamada(nuevaLlamada);	   	
	    		    }
    		}
 			
    		for (RSSItem rssItem : list) {
    			if(rssItem.getTitle() != null) {
    				if(usuarioActualizacionFeedLlamadas.getFeedDesocupado().estaNoticia(rssItem.getTitle()) || usuarioActualizacionFeedLlamadas.getFeedOcupado().estaNoticia(rssItem.getTitle())) {
    				}else {
    					if(usuarioActualizacionFeedLlamadas.getFeedEliminado().estaEliminada(rssItem.getTitle())) {  						
    					}else {
    						UnaNoticia nuevaNoticia = new UnaNoticia(rssItem.getTitle(), rssItem.getLink());
    						if(usuarioActualizacionFeedLlamadas.getEstado() == EstadoUsuario.DESOCUPADO) {
    							usuarioActualizacionFeedLlamadas.getFeedDesocupado().agregarNoticia(nuevaNoticia);
    							if(usuarioActualizacionFeedLlamadas.equals(MainAsistente.getUsuarioActual())) { 		    				   		    				
    								MainAsistente.getFeedListaDefault().addElement(rssItem.getTitle());                                                               
    							}
    						}else {
        		    		usuarioActualizacionFeedLlamadas.getFeedOcupado().agregarNoticia(nuevaNoticia);	    		    		
    						}
    					}
    				}
    			}
    		} 
    		
    		/*if(usuarioActualizacionFeedLlamadas.equals(MainAsistente.getUsuarioActual())) { 
    			MainAsistente.getFeedLista().setModel(MainAsistente.getFeedListaDefault());	
    		}
    		    	
    		    	/*if(usuarioActualizacionFeedLlamadas.getEstado() == EstadoUsuario.DESOCUPADO) {
    		    		usuarioActualizacionFeedLlamadas.getFeedDesocupado().agregarNoticia(nuevaNoticia);
    		    		//if (MainAsistente.nombreUsuarioActivo.getText() != "") {
    		    			if(usuarioActualizacionFeedLlamadas.equals(MainAsistente.getUsuarioActual())) { 		    				   		    				
    		    				MainAsistente.getFeedListaDefault().addElement(MainAsistente.getInteres(e).getText());             
    		    				MainAsistente.getFeedLista().setModel(MainAsistente.getFeedListaDefault());                                                    
    		    			//}
    		    		}
    		    	}else {
    		    		usuarioActualizacionFeedLlamadas.getFeedOcupado().agregarNoticia(nuevaNoticia);	    		    		
    		    	}
    		    }
    		}*/
    	}
   }
}
