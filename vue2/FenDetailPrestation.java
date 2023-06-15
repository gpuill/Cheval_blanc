package vue2;

import java.text.DecimalFormat;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import presentation2.Prestation;
import presentation2.Principale;

public class FenDetailPrestation extends Stage {
	
	// les composants du formulaire
	private GridPane			racine					= new GridPane();
	private HBox				zoneBoutons				= new HBox();
	private Label 				lblLibelle				= new Label("Libellé (*) : ");
	private Label 				lblTarif				= new Label("Tarif HT (*) : ");
	private TextField			txtLibelle				= new TextField();
	private TextField			txtTarif				= new TextField();
	private Label				lblPourcentage 			= new Label("Tarif modifié");
	private TextField			txtResultatPourcentage 	= new TextField();
	private Button 				bnOK 					= new Button("OK");
	private Button 				bnAnnuler 				= new Button("Annuler");
	private RadioButton			rbPourcentage 			= new RadioButton("Saisir un pourcentage");
	private RadioButton			rbTarif 				= new RadioButton("Saisir un tarif");
	private ToggleGroup			rbGroup 				= new ToggleGroup();
	private Double				prix 					= 25.68;
	private String 				libelle 				= "test";
	private Double 				resultatPourcentage 	= 0.0;
	private Alert				alert					= new Alert(AlertType.ERROR);
	private Prestation 			prestationModifier;
	private DecimalFormat df = new DecimalFormat("0.00");
	
	
	// constructeur : initialisation de la fenetre et des données
	public FenDetailPrestation(){		
		this.setTitle("Modifier Prestation");
		this.sizeToScene();
		this.setResizable(false);
		this.setScene(new Scene(creerContenu()));	
	}
	
	private Parent creerContenu() {
		txtLibelle.setDisable(true);	// le matricule n'est pas modifiable (c'est un identifiant)
		txtResultatPourcentage.setDisable(true);
		txtResultatPourcentage.setVisible(false);
		lblPourcentage.setVisible(false);
		txtTarif.setText(String.valueOf(prix));
		txtLibelle.setText(libelle);

        BooleanBinding vide = txtTarif.textProperty().isEmpty();
		
		bnOK.setPrefWidth(100);
		
		bnAnnuler.setPrefWidth(100);
		
		rbGroup.getToggles().addAll(rbPourcentage,rbTarif);
		rbTarif.setSelected(true);
		
		bnOK.disableProperty().bind(
				Bindings.when(vide).
				then(true).otherwise(false)
				);
		
		bnOK.setOnAction(e -> {
		    if (rbTarif.isSelected()) {
		        try {
		        	
		        	prix = Double.valueOf(txtTarif.getText());
		        	String prixText = String.format("%.2f", prix);
		        	prixText = prixText.replace(",", ".");
		        	
		            if (prix <= 999 && prix > 0) {
		            	try {
		            		prestationModifier.setPrix_ht(Float.parseFloat(prixText));
						} catch (Exception e2) {
							System.out.println("ça casse ici");
						}
		            	
		            	try {
		            		Principale.modifierPrestation(prestationModifier,Float.valueOf(prixText));
						} catch (Exception e2) {
							System.out.println("ça casse la");
						}
		            	
		            	
		                this.close();
		            } else {
		                alert.setTitle("Erreur valeur prix");
		                alert.setContentText("Veuillez saisir un prix inférieur à 999 euros et supérieur à 0 euros");
		                alert.showAndWait();
		            }
		        } catch (Exception e2) {
		            alert.setTitle("Erreur format prix");
		            alert.setContentText("Veuillez saisir uniquement des chiffres pour le tarif");
		            alert.showAndWait();
		        }
		    } else 
		        try {
		        	String prixText = txtResultatPourcentage.getText().replace(",", ".");
		        	prix = Double.valueOf(prixText);
		        	System.out.println(prixText);
		        	System.out.println(prix);
		            if (prix <= 999 && prix > 0) {
		            	prestationModifier.setPrix_ht(Float.parseFloat(prixText));
		            	Principale.modifierPrestation(prestationModifier,Float.parseFloat(prixText));
		                System.err.println(prix);
		                rbTarif.setSelected(true);
		                this.close();
		            } else {
		                alert.setTitle("Erreur valeur prix");
		                alert.setContentText("Veuillez saisir un pourcentage de sorte à ce que le prix soit inférieur à 999 euros et supérieur à 0 euros");
		                alert.showAndWait();
		            }
		        } catch (Exception e2) {
		            alert.setTitle("Erreur format pourcentage");
		            alert.setContentText("Veuillez saisir uniquement des chiffres pour le pourcentage");
		            alert.showAndWait();
		        }
		});

		
		bnAnnuler.setOnMouseClicked(e -> {
			this.close();
		});
		
		rbTarif.setOnAction(e -> {
			lblPourcentage.setVisible(false);
			txtResultatPourcentage.setVisible(false);
		});
		
		rbPourcentage.setOnAction(e -> {
			lblPourcentage.setVisible(true);
			txtResultatPourcentage.setVisible(true);
		});
		
		txtTarif.textProperty().addListener((observable, oldValue, newValue) -> {
		    if (rbPourcentage.isSelected()) {
		        if (!newValue.isEmpty()) {
		            try {
		                double pourcentage = Double.parseDouble(newValue);
		                if(pourcentage > 0 ) {
		                	resultatPourcentage = prix * (1 + pourcentage / 100);
		                }else
		                	resultatPourcentage = prix * (1 + pourcentage / 100);
		                txtResultatPourcentage.setText(String.format("%.2f", resultatPourcentage));
		            } catch (NumberFormatException e) {
		                txtResultatPourcentage.clear();
		            }
		        } else {
		            txtResultatPourcentage.clear();
		        }
		    }
		});


		
		// construction du Scene Graph
		zoneBoutons.getChildren().addAll(bnOK, bnAnnuler);
		zoneBoutons.setSpacing(10);
		racine.addRow(0,  lblLibelle, txtLibelle);
		racine.addRow(1,  lblTarif, txtTarif);
		racine.addRow(2, rbTarif, rbPourcentage);
		racine.addRow(3, lblPourcentage, txtResultatPourcentage);
		
		racine.add(zoneBoutons, 1, 10);
		racine.setHgap(10);
		racine.setVgap(15);
		racine.setPadding(new Insets(10));
		return racine;
	}
	
	public Prestation getPrestationModifier() {
		return prestationModifier;
	}

	public void setPrestationModifier(Prestation prestationModifier) {
		this.prestationModifier = prestationModifier;
	}

	public void init(Prestation pre) {
		setPrestationModifier(pre);
		txtLibelle.setText(pre.getLibelle());
		txtTarif.setText(String.valueOf(pre.getPrix_ht()));
	}

}