package it.polito.tdp.rivers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.Simulator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RiversController {
	
	Model model ;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<River> boxRiver;

    @FXML
    private TextField txtStartDate;

    @FXML
    private TextField txtEndDate;

    @FXML
    private TextField txtNumMeasurements;

    @FXML
    private TextField txtFMed;

    @FXML
    private TextField txtK;

    @FXML
    private Button btnSimula;

    @FXML
    private TextArea txtResult;

    @FXML
    void doPopola(ActionEvent event) {
    	River r = boxRiver.getValue() ;
    	model.infoRiver( r ) ;
    	txtStartDate.setText(model.getPrimaData().toString());
    	txtEndDate.setText(model.getUltimaData().toString()) ;
    	txtNumMeasurements.setText(model.getMisurazioni()+"");
    	txtFMed.setText(model.getMedia()+"") ;
    	
    	

    }

    @FXML
    void doSimula(ActionEvent event) {
    	River r = boxRiver.getValue() ;
    	if(r==null)
    		txtResult.setText("Nessun fiume selezionato");
    	
    	else{
    		Simulator s = new Simulator (this.model);
    		try{
    			double k = Double.parseDouble(txtK.getText()) ;
    			txtResult.setText("Nessun fattore di scala selezionato");
        		s.run(k);
        		txtResult.setText("Giorni insodidsffatti: "+s.getInsoddisfatti()
        							+"\nOccupazione media"+ s.getMedia());
    		}catch(Exception e){
    			txtResult.setText("K non inserita");
    			e.printStackTrace();
    		}
    		
   			
    	}

    }

    public void setModel(Model m){
    	this.model = m ;
    	boxRiver.getItems().addAll(model.getAllRivers()) ;
     }
    @FXML
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Rivers.fxml'.";

    }
}
