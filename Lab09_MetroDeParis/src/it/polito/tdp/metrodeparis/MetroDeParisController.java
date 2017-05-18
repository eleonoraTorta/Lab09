/**
 * Sample Skeleton for 'MetroDeParis.fxml' Controller Class
 */

package it.polito.tdp.metrodeparis;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.metrodeparis.model.Collegamento;
import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MetroDeParisController {
	
	Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbPartenza"
    private ComboBox<Fermata> cmbPartenza; // Value injected by FXMLLoader

    @FXML // fx:id="cmbArrivo"
    private ComboBox<Fermata> cmbArrivo; // Value injected by FXMLLoader

    @FXML // fx:id="btnPercorso"
    private Button btnPercorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doPercorso(ActionEvent event) {
    	
    	Fermata partenza = cmbPartenza.getValue();
    	Fermata arrivo = cmbArrivo.getValue();
    	
    	if(partenza == null || arrivo == null){
    		txtResult.appendText("Errore: devi selezionare una stazione di partenza e una di arrivo\n");
    		return;
    	}
    	if(partenza.equals(arrivo)){
    		txtResult.appendText("Inserire una stazione di arrivo DIVERSA da quella di partenza\n");
    		return;
    	}
    	
    	List <Fermata> percorso = model.calcolaPercorsoMinimo(partenza, arrivo);
    	double tempoInSecondi = model.getTempoInSecondi();
    	
    	txtResult.appendText("Il percorso e` il seguente:\n\n");
    	
    	for (Fermata f : percorso) {
    		txtResult.appendText(f.getNome() +"\n");
    	
    	}
    	
    	txtResult.appendText("\nIl tempo di percorrenza stimato e` : " + model.getTempoTotale(tempoInSecondi) +"\n" );

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbPartenza != null : "fx:id=\"cmbPartenza\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert cmbArrivo != null : "fx:id=\"cmbArrivo\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'MetroDeParis.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		try{
			cmbPartenza.getItems().addAll(model.getFermate());
			cmbArrivo.getItems().addAll(model.getFermate());
			model.creaGrafo();
			
		}catch (RuntimeException e){
			txtResult.setText(e.getMessage());
		}
		
	}
}
