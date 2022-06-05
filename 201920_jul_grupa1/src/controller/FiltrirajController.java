package controller;

import java.util.function.BiPredicate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Polaznik;
import model.TipAkcije;

public class FiltrirajController implements EventHandler<ActionEvent> {

	private ListView<Polaznik> lvPolaznici;
	private TextField tfTextFilter;
	private ComboBox<String> cmbPoredjenje;
	private TextField tfPoredjenjeFilter;
	private ComboBox<TipAkcije> cmbTipAkcije;
	
	public FiltrirajController(ListView<Polaznik> lvPolaznici, TextField tfTextFilter, ComboBox<String> cmbPoredjenje,
			TextField tfPoredjenjeFilter, ComboBox<TipAkcije> cmbTipAkcije) {
		this.lvPolaznici = lvPolaznici;
		this.tfTextFilter = tfTextFilter;
		this.cmbPoredjenje = cmbPoredjenje;
		this.tfPoredjenjeFilter = tfPoredjenjeFilter;
		this.cmbTipAkcije = cmbTipAkcije;
	}

	private BiPredicate<Integer, Integer> getOperator(String znak){
		switch(znak) {
			case "<": return (a, b) -> a < b;
			case ">": return (a, b) -> a > b;
			default: return (a, b) -> a == b;
		}
	}
	
	@Override
	public void handle(ActionEvent event) {
		//FILTRIRAMO ZA TRAZENI DEO TEXTA
		String text = tfTextFilter.getText();
		lvPolaznici.getItems().removeIf(polaznik -> (!polaznik.getIme().toLowerCase().contains(text) &&
				!polaznik.getPrezime().toLowerCase().contains(text)));
		
		String znak = cmbPoredjenje.getSelectionModel().getSelectedItem();
		String brojS = tfPoredjenjeFilter.getText();
		String tipAkcije = cmbTipAkcije.getSelectionModel().getSelectedItem().toString();
		Integer broj;
		
		try {
			broj = Integer.parseInt(brojS);
		} catch(NumberFormatException e) {
			return;
		}
		
		BiPredicate<Integer, Integer> op = getOperator(znak);
		switch(tipAkcije) {
			case "ČAS_VOŽNJE" : 
				lvPolaznici.getItems().removeIf(polaznik -> !op.test(polaznik.getBrCasovaVoznje(), broj)); break;
			case "ČAS_TEORIJE" :
				lvPolaznici.getItems().removeIf(polaznik -> !op.test(polaznik.getBrCasovaTeorije(), broj)); break;
			case "UPLATA" :
				lvPolaznici.getItems().removeIf(polaznik -> !op.test(polaznik.getBrUplata(), broj)); break;
			case "POLAGANJE" :
				lvPolaznici.getItems().removeIf(polaznik -> !op.test(polaznik.getBrPolaganja(), broj)); break;

		}
	}

}
