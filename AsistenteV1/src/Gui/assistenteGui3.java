package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.toedter.calendar.JCalendar;

import javax.swing.JScrollPane;
import javax.swing.JSpinner;

public class assistenteGui3 {

	private JFrame frame;
	private JTextField nombreUsuarioNuevo;
	JLabel nombreUsuarioActivo = new JLabel("");

	private List<Object> usuariosLista = new ArrayList<Object>();
	private Usuario usuarioActual;
	private Usuario usuarioBorrar;

	private JList<String> usuariosNombresLista = new JList<String>();
	DefaultListModel<String> usuariosNombresListaDefault = new DefaultListModel<String>();

	private JList<String> agendaEventosLista = new JList<String>();
	DefaultListModel<String> agendaEventosListaDefault = new DefaultListModel<String>();

	private JList<String> feedLista = new JList<String>();
	DefaultListModel<String> feedListaDefault = new DefaultListModel<String>();

	private JRadioButton estadoUsuario = new JRadioButton("OCUPADO");
	
	private JRadioButton feedDeporte = new JRadioButton("DEPORTE");  
	private JRadioButton feedTecnologia = new JRadioButton("TECNOLOGIA");  
	private JRadioButton feedNacional = new JRadioButton("NACIONAL");  
	private JRadioButton feedInternacional = new JRadioButton("INTERNACIONAL");  
	private JRadioButton feedComida = new JRadioButton("COMIDA");  
	private JRadioButton feedModa = new JRadioButton("MODA");                                //Seleccion interes Moda
	List<JRadioButton> intereses = Stream.of(feedDeporte, feedTecnologia, feedNacional, feedInternacional, feedComida, feedModa).collect(Collectors.toList());
	private JTextField nombreEventoNuevo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					assistenteGui3 window = new assistenteGui3();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public assistenteGui3() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Timer timer = new Timer();                                                   //Creacion del timer para el feed
		timer.schedule(new LlamadasNoticias(), 0, 5000); 							 //Creacion de los parametros del timer
		
		frame = new JFrame();
		frame.setBounds(100, 100, 878, 330);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Spiner para la hora
		
		JSpinner eleccionHora = new JSpinner(new SpinnerDateModel(new Date(1523242800653L), null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor de = new JSpinner.DateEditor(eleccionHora, "HH:mm");
		eleccionHora.setEditor(de);
		eleccionHora.setBounds(323, 226, 51, 20);
		frame.getContentPane().add(eleccionHora);
		
		//Recuadro para introduccion de nombre
		
		nombreUsuarioNuevo = new JTextField();
		nombreUsuarioNuevo.setBounds(10, 226, 188, 20);
		frame.getContentPane().add(nombreUsuarioNuevo);
		nombreUsuarioNuevo.setColumns(10);
		
		//Boton de agregar usuario

		JButton agregarUsuarioNuevo = new JButton("AGREGAR");                        //al precionar el boton de agregar usuario
		agregarUsuarioNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (nombreUsuarioNuevo.getText().trim().length() > 0) {              //revisamos que no sean solo espacios en blanco
					Usuario nuevoUsuario = new Usuario();                            // creo un nuevo usuario
					nuevoUsuario.nombre = nombreUsuarioNuevo.getText();              // le asigno el nombre del texto
					usuariosLista.add(nuevoUsuario);                                 // agrego el nuevo usuario a la lista de usuarios
					
					usuariosNombresListaDefault.addElement(nuevoUsuario.nombre);     // agregar el nombre del nuevo usuario a la lista defoult
					usuariosNombresLista.setModel(usuariosNombresListaDefault);      // seteamos el modeo de la lista visible a la lista defailt

					nombreUsuarioNuevo.setText("");                                  // eliminamos el texto del recuadro

				}
			}
		});
		agregarUsuarioNuevo.setBounds(10, 257, 89, 23);
		frame.getContentPane().add(agregarUsuarioNuevo);
		
		//Boton de elejir usuario
		
		JButton aceptarUsuario = new JButton("ACEPTAR");
		aceptarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccionIndex = usuariosNombresLista.getSelectedIndex();     // obtenemos el index de la seleccion
				if (seleccionIndex >= 0) {
					estadoUsuario.setEnabled(true);

					usuarioActual = (Usuario) usuariosLista.get(seleccionIndex);        // seleccionamos y extraemos al usuario elejido
					nombreUsuarioActivo.setText(usuarioActual.nombre);                  // seteamos el nombre del usuario activo

					agendaEventosListaDefault = new DefaultListModel<String>();   // reiniciamos la lista de eventos
					feedListaDefault = new DefaultListModel<String>();            // reiniciamos la lista de eventos
					agendaEventosLista.setModel(agendaEventosListaDefault);       // ponemos el modelo (copiamos de la defout a la que se muestra)
					feedLista.setModel(feedListaDefault);                         // ponemos el modelo (copiamos de la defout a la que se muestra)

					for (int i = 0; i < usuarioActual.agenda.size(); i++) {             // revisamos todos los eventos que tiene el usuario en su lista personal
						Evento evento = (Evento) usuarioActual.agenda.get(i);           // extraemos el evento de la lista personal
						
						String fechaString  = new SimpleDateFormat("dd/MM/yyyy").format(evento.fechaHora);	
						String horaString  = new SimpleDateFormat("HH:mm").format(evento.fechaHora);	
						agendaEventosListaDefault.addElement("<html>" + evento.nombre + "<br>" + fechaString + " " + horaString + "</span></html>");
					}
					
					for (int i = 0; i < usuarioActual.feed.size(); i++) {               // revisamos todos los feed que tiene el usuario en su lista feed
						feedListaDefault.addElement((String)usuarioActual.feed.get(i)); // agregamos cada feed de la lista personal a la lista defoult
					}
					
					if(usuarioActual.estado == 1)
						estadoUsuario.setSelected(true);
					else
						estadoUsuario.setSelected(false);
					
					for (int i = 0; i < usuarioActual.intereses.size(); i++) {           // revisamos todos los intereses del usuario
						intereses.get(i).setEnabled(true);
						if(usuarioActual.intereses.get(i) == 0)                          // si es que no tiene el interes i se deselecciona
							intereses.get(i).setSelected(false);
						else
							intereses.get(i).setSelected(true); // si es que se tiene el interes i se selecciona
					}
				}
			}
		});
		aceptarUsuario.setBounds(10, 192, 89, 23);
		frame.getContentPane().add(aceptarUsuario);
		
		//Boton de eliminar usuario

		JButton eliminarUsuario = new JButton("ELIMINAR");                       //al precionar el boton de eliminar usuario
		eliminarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccionIndex = usuariosNombresLista.getSelectedIndex();    // revisamos el index para que existe una seleccion
				if (seleccionIndex >= 0) {
					usuarioBorrar = (Usuario) usuariosLista.get(seleccionIndex);
					if(usuarioBorrar.equals(usuarioActual)) {
						estadoUsuario.setEnabled(false);
						estadoUsuario.setSelected(false);
					
						for (int i = 0; i < usuarioActual.intereses.size(); i++) {           // revisamos todos los intereses del usuario
							intereses.get(i).setSelected(false);
							intereses.get(i).setEnabled(false);
						}
						
						nombreUsuarioActivo.setText("");                             // eliminnamos el nombre del usuario

						agendaEventosListaDefault = new DefaultListModel<String>();  // reniciamos la lista de eventos default
						feedListaDefault = new DefaultListModel<String>();           // reniciamos la lista de feed default
						agendaEventosLista.setModel(agendaEventosListaDefault);      // ponemos el modelo
						feedLista.setModel(feedListaDefault);                        // ponemos el modelo
						
					}
					
					usuariosNombresListaDefault.removeElementAt(seleccionIndex); // removimos el nombre de la lista defoult
					usuariosNombresLista.setModel(usuariosNombresListaDefault);   
					usuariosLista.remove(seleccionIndex);                        // removimos el usuario de la lista de usuarios
				}
			}
		});
		eliminarUsuario.setBounds(109, 192, 89, 23);
		frame.getContentPane().add(eliminarUsuario);
		
		//Boton de agregar evento de agenda

		JButton agregarEventoAgenda = new JButton("AGREGAR");
		agregarEventoAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nombreUsuarioActivo.getText() != "") {
					if (nombreEventoNuevo.getText().trim().length() > 0) {            //revisamos que no sean solo espacios en blanco
						
						JCalendar fechaSeleccion = new JCalendar();
						String titulo = null;
						Object[] parametros = {titulo, fechaSeleccion};					
						Object[] opciones = {"AGENDAR", "CANCELAR"}; 
					
						int siOno = JOptionPane.showOptionDialog(frame, parametros, "FECHA", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
					
						if (siOno == JOptionPane.YES_OPTION) {
							String eventoNombre = nombreEventoNuevo.getText(); 
							nombreEventoNuevo.setText("");                                  // eliminamos el texto del recuadro																				
							
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							String fechaString = sdf.format(((JCalendar)parametros[1]).getDate());
						    String horaString  = new SimpleDateFormat("HH:mm").format(eleccionHora.getValue());								    						    
							String[] fechaArray = fechaString.split("/");
							String[] horaArray = horaString.split(":");
							
							Calendar cal = Calendar.getInstance();
							cal.setTimeInMillis(0);
							cal.set(Integer.parseInt(fechaArray[2]), Integer.parseInt(fechaArray[1]) - 1, Integer.parseInt(fechaArray[0]), Integer.parseInt(horaArray[0]), Integer.parseInt(horaArray[1]), 0);
							Date date = cal.getTime();                                        
					        						
							Evento nuevoEvento = new Evento();                                              // creo un nuevo evento
							nuevoEvento.nombre = eventoNombre;                                              // le asigno el nombre al evento
							nuevoEvento.fechaHora = date;                                                   // le asigno la fecha y horo al evento
							
							int ubicacion = 0;
							for(int i = 0; i < usuarioActual.agenda.size(); i ++) {
								Evento comparacion = (Evento) usuarioActual.agenda.get(i);
								if(nuevoEvento.fechaHora.after(comparacion.fechaHora)) {
									ubicacion ++;
								}else
									break;
							}
							
							usuarioActual.agenda.add(ubicacion, nuevoEvento);							
							agendaEventosListaDefault.add(ubicacion, "<html>" + nuevoEvento.nombre + "<br>" + fechaString + " " + horaString + "</span></html>");
							agendaEventosLista.setModel(agendaEventosListaDefault);                         //ponemos el modelo
						} 
					
					}
				}
			}
		});
		agregarEventoAgenda.setBounds(384, 225, 127, 23);
		frame.getContentPane().add(agregarEventoAgenda);
		
		//Boton de eliminar evento de agenda

		JButton eliminarEventoAgenda = new JButton("ELIMINAR");
		eliminarEventoAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccionIndex = agendaEventosLista.getSelectedIndex();      // se revisa si se tiene algun elemento de
																			     // la agenda selecionado
				if (seleccionIndex >= 0) {
					usuarioActual.agenda.remove(seleccionIndex);                 // lo agregamos a la lista de eventos del usuario
					agendaEventosListaDefault.removeElementAt(seleccionIndex);   // se elimina el elemento de la lista
					agendaEventosLista.setModel(agendaEventosListaDefault);      //ponemos el modelo

				}
			}
		});
		eliminarEventoAgenda.setBounds(323, 257, 188, 23);
		frame.getContentPane().add(eliminarEventoAgenda);
		estadoUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		//Botones de seleccion de los gustos para el feed y estado
		
		estadoUsuario.setEnabled(false);
		estadoUsuario.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if (nombreUsuarioActivo.getText() != "") {
					if(estadoUsuario.isSelected())                                     
						usuarioActual.estado = 1;                  
					else
						usuarioActual.estado = 0; 						       
					}
				}
		});
		estadoUsuario.setBounds(203, 111, 105, 23);
		frame.getContentPane().add(estadoUsuario);
		feedDeporte.setEnabled(false);

		feedDeporte.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if (nombreUsuarioActivo.getText() != "") {
					if(feedDeporte.isSelected())                         //Se revisa si esta selecionado, si es asi se agrega el interes en la posicion 0
						usuarioActual.intereses.set(0,1);                  
					else
						usuarioActual.intereses.set(0,0); 				 //Caso contrario se le quita el interes en la posicion 0
					}
				}
		});
		feedDeporte.setBounds(715, 34, 127, 23);
		frame.getContentPane().add(feedDeporte);
		feedTecnologia.setEnabled(false);

		feedTecnologia.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if (nombreUsuarioActivo.getText() != "") {
					if(feedTecnologia.isSelected())                      //Se revisa si esta selecionado, si es asi se agrega el interes en la posicion 1
						usuarioActual.intereses.set(1,1);                  
					else
						usuarioActual.intereses.set(1,0); 				 //Caso contrario se le quita el interes en la posicion 1
					}
				}
		});
		feedTecnologia.setBounds(715, 59, 127, 23);
		frame.getContentPane().add(feedTecnologia);
		feedNacional.setEnabled(false);

		feedNacional.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if (nombreUsuarioActivo.getText() != "") {
					if(feedNacional.isSelected())                        //Se revisa si esta selecionado, si es asi se agrega el interes en la posicion 2 
						usuarioActual.intereses.set(2,1);                  
					else
						usuarioActual.intereses.set(2,0); 				 //Caso contrario se le quita el interes en la posicion 2
					}
				}
		});
		feedNacional.setBounds(715, 85, 127, 23);
		frame.getContentPane().add(feedNacional);
		feedInternacional.setEnabled(false);

		feedInternacional.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if (nombreUsuarioActivo.getText() != "") {
					if(feedInternacional.isSelected())                   //Se revisa si esta selecionado, si es asi se agrega el interes en la posicion 3
						usuarioActual.intereses.set(3,1);                  
					else
						usuarioActual.intereses.set(3,0); 				 //Caso contrario se le quita el interes en la posicion 3
					}
				}
		});
		feedInternacional.setBounds(715, 111, 127, 23);
		frame.getContentPane().add(feedInternacional);
		feedComida.setEnabled(false);

		feedComida.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if (nombreUsuarioActivo.getText() != "") {
					if(feedComida.isSelected())                          //Se revisa si esta selecionado, si es asi se agrega el interes en la posicion 4
						usuarioActual.intereses.set(4,1);                  
					else
						usuarioActual.intereses.set(4,0); 				 //Caso contrario se le quita el interes en la posicion 4
					}
				}
		});
		feedComida.setBounds(715, 137, 127, 23);
		frame.getContentPane().add(feedComida);
		feedModa.setEnabled(false);

		feedModa.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if (nombreUsuarioActivo.getText() != "") {
					if(feedModa.isSelected())                            //Se revisa si esta selecionado, si es asi se agrega el interes en la posicion 5
						usuarioActual.intereses.set(5,1);                  
					else
						usuarioActual.intereses.set(5,0); 				 //Caso contrario se le quita el interes en la posicion 5
					}
				}
		});
		feedModa.setBounds(715, 162, 127, 23);
		frame.getContentPane().add(feedModa);
		
		
		//Lables
		
		JLabel tituloUsuarios = new JLabel("USUARIOS");
		tituloUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		tituloUsuarios.setBounds(10, 12, 188, 14);
		frame.getContentPane().add(tituloUsuarios);

		JLabel tituloFeed = new JLabel("FEED");
		tituloFeed.setHorizontalAlignment(SwingConstants.CENTER);
		tituloFeed.setBounds(521, 12, 188, 14);
		frame.getContentPane().add(tituloFeed);

		JLabel tituloAgenda = new JLabel("AGENDA");
		tituloAgenda.setHorizontalAlignment(SwingConstants.CENTER);
		tituloAgenda.setBounds(323, 12, 188, 14);
		frame.getContentPane().add(tituloAgenda);

		JLabel tituloUsuarioActivo = new JLabel("USUARIO ACTIVO");
		tituloUsuarioActivo.setHorizontalAlignment(SwingConstants.LEFT);
		tituloUsuarioActivo.setBounds(208, 26, 105, 23);
		frame.getContentPane().add(tituloUsuarioActivo);
		nombreUsuarioActivo.setHorizontalAlignment(SwingConstants.LEFT);

		nombreUsuarioActivo.setBounds(208, 48, 105, 23);
		frame.getContentPane().add(nombreUsuarioActivo);
	
		
        //Paneles de scroll
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(323, 28, 188, 153);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(agendaEventosLista);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 28, 188, 153);
		frame.getContentPane().add(scrollPane_1);
		scrollPane_1.setViewportView(usuariosNombresLista);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(521, 28, 188, 252);
		frame.getContentPane().add(scrollPane_2);
		scrollPane_2.setViewportView(feedLista);
		
		nombreEventoNuevo = new JTextField();
		nombreEventoNuevo.setColumns(10);
		nombreEventoNuevo.setBounds(323, 193, 188, 20);
		frame.getContentPane().add(nombreEventoNuevo);
		
		JLabel tituloEstado = new JLabel("ESTADO");
		tituloEstado.setHorizontalAlignment(SwingConstants.LEFT);
		tituloEstado.setBounds(208, 85, 105, 23);
		frame.getContentPane().add(tituloEstado);
	}

	private class Usuario {                                                       //Clase usuario
		String nombre;
		int estado = 0;
		List<Object> agenda = new ArrayList<Object>();
		List<Object> feed = new ArrayList<Object>();	
		List<Integer> intereses = Stream.of(0, 0, 0, 0, 0, 0).collect(Collectors.toList());
	}
	
	private class Evento {                                                        //Clase evento
		String nombre;
		Date fechaHora;
	}

	private class LlamadasNoticias extends TimerTask {                                     //clase Timer
	    public void run() {	    	
	    	for(int i = 0; i < usuariosLista.size(); i ++) {
	    		Usuario usuarioActualizacionFeed = (Usuario) usuariosLista.get(i);      	
	    		for(int e = 0; e < usuarioActualizacionFeed.intereses.size(); e ++) {
	    		    if(usuarioActualizacionFeed.intereses.get(e) == 1)  {		    
	    		    	usuarioActualizacionFeed.feed.add(intereses.get(e).getText());
	    		    	if (nombreUsuarioActivo.getText() != "") {
	    		    		if(usuarioActualizacionFeed.equals(usuarioActual)) {
	    	    			feedListaDefault.addElement(intereses.get(e).getText());             
	    	    			feedLista.setModel(feedListaDefault);                                                    
	    		    		}
	    	    		}
	    		    	
	    		    }
	    		}
	    	}
	   }
	}
}
