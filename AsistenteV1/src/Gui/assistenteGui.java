package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class assistenteGui {

	private JFrame frame;
	private JTextField nombreUsuarioNuevo;
	JLabel nombreUsuarioActivo = new JLabel("");

	private List<Object> usuariosLista = new ArrayList<Object>();
	private Usuario usuarioActual;
	private Usuario usuarioBorrar;
	private JLabel cantidadUsuarios = new JLabel("");

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					assistenteGui window = new assistenteGui();
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
	public assistenteGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Timer timer = new Timer();                                                   //Creacion del timer para el feed
		timer.schedule(new LlamadasNoticias(), 0, 5000); 							 //Creacion de los parametros del timer
		
		for (int i = 0; i < intereses.size(); i++)                                   // revisamos todos los intereses del usuario
			intereses.get(i).setEnabled(false);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 878, 298);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		nombreUsuarioNuevo = new JTextField();
		nombreUsuarioNuevo.setBounds(10, 196, 188, 20);
		frame.getContentPane().add(nombreUsuarioNuevo);
		nombreUsuarioNuevo.setColumns(10);

		JButton agregarUsuarioNuevo = new JButton("AGREGAR");                        //al precionar el boton de agregar usuario
		agregarUsuarioNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (nombreUsuarioNuevo.getText().trim().length() > 0) {              //revisamos que no sean solo espacios en blanco
					//estadoUsuario.setEnabled(false);                                 //reiniciamos el estado de OCUPADO
					//estadoUsuario.setSelected(false);

					Usuario nuevoUsuario = new Usuario();                            // creo un nuevo usuario
					nuevoUsuario.nombre = nombreUsuarioNuevo.getText();              // le asigno el nombre del texto
					usuariosLista.add(nuevoUsuario);                                 // agrego el nuevo usuario a la lista de usuarios
					//Usuario usuario = (Usuario)usuariosLista.get(usuariosLista.size()-1);
					//prueba para extraer un usuario especifico de la lista de usuarios

					usuariosNombresListaDefault.addElement(nuevoUsuario.nombre);     // agregar el nombre del nuevo usuario a la lista defoult
					usuariosNombresLista.setModel(usuariosNombresListaDefault);      // seteamos el modeo de la lista visible a la lista defailt

					nombreUsuarioNuevo.setText("");                                  // eliminamos el texto del recuadro
					//nombreUsuarioActivo.setText("");                                 // eliminamos el nombre del usuario activo

					//agendaEventosListaDefault = new DefaultListModel<String>();      // reiniciamos la lista de eventos default			
					//feedListaDefault = new DefaultListModel<String>();               // reiniciamos la lista de feed default				
					//agendaEventosLista.setModel(agendaEventosListaDefault);          // ponemos el modelo a eventos
					//feedLista.setModel(feedListaDefault);                            // ponemos el modelo a feed
					cantidadUsuarios.setText("" + usuariosLista.size()); 
				}
			}
		});
		agregarUsuarioNuevo.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(agregarUsuarioNuevo);
		
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

					for (int i = 0; i < usuarioActual.agenda.size(); i++) {             // revisamos todos los eventos que tiene el usuario en su lista personal
						Evento evento = (Evento) usuarioActual.agenda.get(i);           // extraemos el evento de la lista personal
						agendaEventosListaDefault.addElement(evento.nombre);      // agregamos cada evento de la lista personal a la lista defoult
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
					
					agendaEventosLista.setModel(agendaEventosListaDefault);       // ponemos el modelo (copiamos de la defout a la que se muestra)
					feedLista.setModel(feedListaDefault);                         // ponemos el modelo (copiamos de la defout a la que se muestra)
				}
			}
		});
		aceptarUsuario.setBounds(10, 162, 89, 23);
		frame.getContentPane().add(aceptarUsuario);

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
					
						usuariosNombresListaDefault.removeElementAt(seleccionIndex); // removimos el nombre de la lista defoult
						usuariosLista.remove(seleccionIndex);                        // removimos el usuario de la lista de usuarios
						nombreUsuarioActivo.setText("");                             // eliminnamos el nombre del usuario

						agendaEventosListaDefault = new DefaultListModel<String>();  // reniciamos la lista de eventos default
						feedListaDefault = new DefaultListModel<String>();           // reniciamos la lista de feed default
						agendaEventosLista.setModel(agendaEventosListaDefault);      // ponemos el modelo
						feedLista.setModel(feedListaDefault);                        // ponemos el modelo
					}else {
						usuariosNombresListaDefault.removeElementAt(seleccionIndex); // removimos el nombre de la lista defoult
						usuariosLista.remove(seleccionIndex);                        // removimos el usuario de la lista de usuarios
					}
					cantidadUsuarios.setText("" + usuariosLista.size()); 
				}
			}
		});
		eliminarUsuario.setBounds(109, 162, 89, 23);
		frame.getContentPane().add(eliminarUsuario);
		
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
		estadoUsuario.setBounds(204, 59, 83, 23);
		frame.getContentPane().add(estadoUsuario);

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
		tituloUsuarioActivo.setBounds(208, 9, 105, 20);
		frame.getContentPane().add(tituloUsuarioActivo);

		nombreUsuarioActivo.setBounds(208, 38, 105, 14);
		frame.getContentPane().add(nombreUsuarioActivo);

		JButton agregarEventoAgenda = new JButton("AGREGAR");
		agregarEventoAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nombreUsuarioActivo.getText() != "") {
					Evento nuevoEvento = new Evento(); // creo un nuevo evento
					Random random = new Random();
					nuevoEvento.nombre = Integer.toString(random.nextInt(100) + 1); // le asigno el nombre al evento
					//nuevoEvento.horario = "hola"; // le asigno el horario al evento
					usuarioActual.agenda.add(nuevoEvento); // lo agregamos a la lista de eventos del usuario

					agendaEventosListaDefault.addElement(nuevoEvento.nombre); // agregar el nombre del nuevo evento a la
																				// lista defoult
					// agendaEventosLista.setModel(agendaEventosListaDefault); //ponemos el modelo
				}
			}
		});
		agregarEventoAgenda.setBounds(323, 227, 89, 23);
		frame.getContentPane().add(agregarEventoAgenda);

		JButton eliminarEventoAgenda = new JButton("ELIMINAR");
		eliminarEventoAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccionIndex = agendaEventosLista.getSelectedIndex(); // se revisa si se tiene algun elemento de
																			// la agenda selecionado
				if (seleccionIndex >= 0) {
					usuarioActual.agenda.remove(seleccionIndex); // lo agregamos a la lista de eventos del usuario
					agendaEventosListaDefault.removeElementAt(seleccionIndex); // se elimina el elemento de la lista
					// agendaEventosLista.setModel(agendaEventosListaDefault); //ponemos el modelo

				}
			}
		});
		eliminarEventoAgenda.setBounds(422, 227, 89, 23);
		frame.getContentPane().add(eliminarEventoAgenda);
		
		//Seleccion de los gustos para el feed

		feedDeporte.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if (nombreUsuarioActivo.getText() != "") {
					if(feedDeporte.isSelected())                                      //Se revisa si esta selecionado, si es asi se agrega el interes en la posicion 5
						usuarioActual.intereses.set(0,1);                  
					else
						usuarioActual.intereses.set(0,0); 							   //Caso contrario se le quita el interes en la posicion 5
					}
				}
		});
		feedDeporte.setBounds(715, 34, 127, 23);
		frame.getContentPane().add(feedDeporte);

		feedTecnologia.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if (nombreUsuarioActivo.getText() != "") {
					if(feedTecnologia.isSelected())                                      //Se revisa si esta selecionado, si es asi se agrega el interes en la posicion 5
						usuarioActual.intereses.set(1,1);                  
					else
						usuarioActual.intereses.set(1,0); 							   //Caso contrario se le quita el interes en la posicion 5
					}
				}
		});
		feedTecnologia.setBounds(715, 59, 127, 23);
		frame.getContentPane().add(feedTecnologia);

		feedNacional.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if (nombreUsuarioActivo.getText() != "") {
					if(feedNacional.isSelected())                                      //Se revisa si esta selecionado, si es asi se agrega el interes en la posicion 5
						usuarioActual.intereses.set(2,1);                  
					else
						usuarioActual.intereses.set(2,0); 							   //Caso contrario se le quita el interes en la posicion 5
					}
				}
		});
		feedNacional.setBounds(715, 85, 127, 23);
		frame.getContentPane().add(feedNacional);

		feedInternacional.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if (nombreUsuarioActivo.getText() != "") {
					if(feedInternacional.isSelected())                                      //Se revisa si esta selecionado, si es asi se agrega el interes en la posicion 5
						usuarioActual.intereses.set(3,1);                  
					else
						usuarioActual.intereses.set(3,0); 							   //Caso contrario se le quita el interes en la posicion 5
					}
				}
		});
		feedInternacional.setBounds(715, 111, 127, 23);
		frame.getContentPane().add(feedInternacional);

		feedComida.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if (nombreUsuarioActivo.getText() != "") {
					if(feedComida.isSelected())                                      //Se revisa si esta selecionado, si es asi se agrega el interes en la posicion 5
						usuarioActual.intereses.set(4,1);                  
					else
						usuarioActual.intereses.set(4,0); 							   //Caso contrario se le quita el interes en la posicion 5
					}
				}
		});
		feedComida.setBounds(715, 137, 127, 23);
		frame.getContentPane().add(feedComida);

		feedModa.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if (nombreUsuarioActivo.getText() != "") {
					if(feedModa.isSelected())                                      //Se revisa si esta selecionado, si es asi se agrega el interes en la posicion 5
						usuarioActual.intereses.set(5,1);                  
					else
						usuarioActual.intereses.set(5,0); 							   //Caso contrario se le quita el interes en la posicion 5
					}
				}
		});
		feedModa.setBounds(715, 162, 127, 23);
		frame.getContentPane().add(feedModa);
	

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(323, 28, 188, 189);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(agendaEventosLista);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 28, 188, 123);
		frame.getContentPane().add(scrollPane_1);
		scrollPane_1.setViewportView(usuariosNombresLista);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(521, 28, 188, 222);
		frame.getContentPane().add(scrollPane_2);
		scrollPane_2.setViewportView(feedLista);
		
		cantidadUsuarios.setBounds(208, 89, 105, 14);
		frame.getContentPane().add(cantidadUsuarios);
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
		//String horario;
	}

	private class LlamadasNoticias extends TimerTask {                            //clase Timer
	    public void run() {
	       if (nombreUsuarioActivo.getText() != "") {
	    	   usuarioActual.feed.add("EVENTO");                                        // lo agregamos a la lista de eventos del usuario
	    	   feedListaDefault.addElement("EVENTO");                             // agregar el nombre del nuevo evento a la
																		          // lista defoult
				// agendaEventosLista.setModel(agendaEventosListaDefault);        //ponemos el modelo
	       }
	   }
	}
}
