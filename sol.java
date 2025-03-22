package ma.emsi.projets.banque;

import java.math.BigDecimal;

public class CompteBancaire {
	protected String code;
	protected Personne titulaire;
	protected BigDecimal solde;
	protected BigDecimal decouvert;
	 static int nbr_debiteur = 0;

public CompteBancaire() {
	code="0";
	titulaire = new Personne("0", "0");
	solde = BigDecimal.ZERO ;
	decouvert = BigDecimal.ZERO ;
}

public CompteBancaire(String code, Personne titulaire, BigDecimal solde, BigDecimal decouvert) {
	this.code = code;
	this.titulaire = titulaire;
	this.decouvert = decouvert;
	if(solde.compareTo(BigDecimal.ZERO)>0 ) {
		this.solde = solde;
	}
	else
		this.solde = BigDecimal.ZERO ;
}

public void deposer(BigDecimal montant) {
	solde = solde.add(montant);
}

public void retirer(BigDecimal montant) {
	if(montant.compareTo(BigDecimal.ZERO)>0)
	solde = solde.subtract(montant);
}

public BigDecimal getsolde() {
	return solde;
}
public void decouvertAutorise(BigDecimal montant) {
	if(montant.compareTo(BigDecimal.ZERO)>0)
		decouvert = montant;
	else
		decouvert = BigDecimal.ZERO ;
}

public void retrait(BigDecimal montant) {

	if(montant.compareTo(decouvert.add(solde))>=0)
	 solde = solde.subtract(montant);
	else
		System.out.println("retrait refusé");
}

public boolean estDebiteur() {
	if(solde.compareTo(BigDecimal.ZERO)<0)
		return true;
	else
		return false;
}

public static int nbr_compte(CompteBancaire t[]) {
	
	for(int i=0 ; i<t.length ; i++) {
		if(t[i].solde.compareTo(BigDecimal.ZERO)<0)
			nbr_debiteur++;
	}
	return nbr_debiteur;
}

public void afficher() {
    System.out.println("Code du compte : " + code);
    System.out.println("Titulaire : " + titulaire.getNom() + " " + titulaire.getprenom());
    System.out.println("Solde : " + solde);
    System.out.println("Découvert autorisé : " + decouvert);
    System.out.println("Débiteur : " + (estDebiteur() ? "Oui" : "Non"));
}

}

class CompteRenumere extends CompteBancaire {
	private BigDecimal taux;

	public CompteRenumere(BigDecimal taux ,String code , Personne titulaire ,BigDecimal solde ,BigDecimal decouvert) {
		super(code , titulaire ,solde ,decouvert);
		
		if(taux.compareTo(BigDecimal.ZERO)>0)
		this.taux = taux;
		else
			System.out.println("Le taux doit etre superieur strictement à 0");
	}

	@Override
	public String toString() {
		return "CompteRenumere [taux=" + taux + "]";
	}
	
	@Override
	public void deposer(BigDecimal montant) {
		BigDecimal bonus = solde.add(montant).multiply(BigDecimal.valueOf(0.02));
		solde = solde.add(bonus);		
	}
	
	public BigDecimal calculerSoldeFuture(int n) {
		BigDecimal solde_future = solde.multiply(BigDecimal.valueOf(1).add(taux.divide(BigDecimal.valueOf(100))).pow(n));
		return solde.add(solde_future);
	}
	
	@Override
	public void afficher() {
	    System.out.println("Code du compte : " + code);
	    System.out.println("Titulaire : " + titulaire.getNom() + " " + titulaire.getprenom());
	    System.out.println("Solde : " + solde);
	    System.out.println("Découvert autorisé : " + decouvert);
	    System.out.println("Débiteur : " + (estDebiteur() ? "Oui" : "Non"));
	    System.out.println("Taux : " + taux);
	}
}
