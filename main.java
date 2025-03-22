package ma.emsi.projets.banque;

import java.math.BigDecimal;

public class main {
	public static void main(String[] args) {

CompteBancaire[] t = new CompteBancaire[2];
t[0] = new CompteBancaire("1",new Personne("Touli","Yassine"),new BigDecimal(10000.00),new BigDecimal(2000.00));
t[1] = new CompteBancaire("2",new Personne("Personne","2"),new BigDecimal(10000.00),new BigDecimal(2000.00));
System.out.println(t[0].code);
System.out.println(t[0].solde);
System.out.println(t[0].decouvert);	
t[0].deposer(new BigDecimal(100));
System.out.println(t[0].solde);
t[0].retirer(new BigDecimal(200));	
System.out.println(t[0].solde);
t[0].decouvertAutorise(new BigDecimal(100));
System.out.println(t[0].decouvert);
t[0].retrait(new BigDecimal(200));;
int nbr = CompteBancaire.nbr_compte(t);
System.out.println(nbr);

//Tp3 

CompteBancaire[] tab = new CompteBancaire[2];
tab[0] = new CompteBancaire("3",new Personne("Personne","3"),new BigDecimal(10000.00),new BigDecimal(2000.00));
tab[1] = new CompteRenumere(new BigDecimal(1000),"4",new Personne("Personne","4"),new BigDecimal(100.00),new BigDecimal(20000.00));
//tab[2] = new CompteRenumere(new BigDecimal(1000),"5",new Personne("Personne","5"),new BigDecimal(10000.00),new BigDecimal(20000.00));
tab[1].deposer(new BigDecimal(200));
System.out.println(tab[1].solde);
tab[0].afficher();
tab[1].afficher();
CompteRenumere compterenumere = (CompteRenumere) tab[1];
BigDecimal future_solde = compterenumere.calculerSoldeFuture(5);
System.out.println(future_solde);
	
	}
}
