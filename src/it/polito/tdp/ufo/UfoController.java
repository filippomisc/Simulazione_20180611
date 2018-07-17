/**
 * Sample Skeleton for 'Ufo.fxml' Controller Class
 */

package it.polito.tdp.ufo;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.ufo.db.AnnoEAvvistamento;
import it.polito.tdp.ufo.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class UfoController {
	
	private Model m;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private Button btnAnalizza;
    
    @FXML // fx:id="boxAnno"
    private ComboBox<AnnoEAvvistamento> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxStato"
    private ComboBox<String> boxStato; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void handleAnalizza(ActionEvent event) {

    	String stato = this.boxStato.getValue();
    	
    	if (stato==null) {
    		this.txtResult.appendText("ERRORE: selezionare uno stato!");
    		return;
    	}
    	
    	
    	this.txtResult.appendText("\nStato di Partenza: " + stato + "\n");
    	
    	this.txtResult.appendText("stati PRECEDENTI\n");
    	this.txtResult.appendText(m.getPredecessori(stato).toString() + "\n");    	
    	
    	this.txtResult.appendText("stati SUCCESSIVI\n");
    	this.txtResult.appendText(m.getSuccessori(stato).toString() + "\n");
    	
    	this.txtResult.appendText("stati RAGGIUNGIBILI\n");
    	this.txtResult.appendText(m.getStatiRaggiungibili(stato).toString() + "\n");
    	
    	


    	
    	
    	
    	
    	
    	
    }

    @FXML
    void handleAvvistamenti(ActionEvent event) {
    	
    	
    	AnnoEAvvistamento anno = this.boxAnno.getValue();
    	
    	if(anno == null)
    		this.txtResult.setText("ERRORE: scegliere un anno!");

    	m.createGraph(anno.getAnno());
    	
    	this.boxAnno.setDisable(false);
    	this.btnAnalizza.setDisable(false);
    	this.boxStato.setDisable(false);
    	
    	this.boxStato.getItems();
//    	this.boxStato.getItems().addAll(m.getGraph().vertexSet());//cosi abbiamo una lista non ordinata
    	this.boxStato.getItems().addAll(m.getStati());

    	
    	
    }

    @FXML
    void handleSequenza(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Ufo.fxml'.";
        assert boxStato != null : "fx:id=\"boxStato\" was not injected: check your FXML file 'Ufo.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Ufo.fxml'.";

    }
    
    public void setModel(Model m) {
    	
    	this.m=m;
    	
    	this.boxAnno.getItems().addAll(m.getAnniAvvistamenti());
    	
    }
}
