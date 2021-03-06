package ca.qc.bdeb.sim.projetmanhattan.view.digital;

import ca.qc.bdeb.sim.projetmanhattan.model.mixte.Noeud;
import ca.qc.bdeb.sim.projetmanhattan.view.mixte.TypeComposant;

/**
 *
 * @author Marc-Antoine Lalonde
 * @author Patrick Richer St-Onge
 */
public class NOTGate extends LogicGateAbstraite {

    /**
     * Initialise l'instance de la classe et ses variables si nécessaire.
     */
    public NOTGate() {
        super(TypeComposant.NOTGATE);
        addImage("not1.png");

        addImageActif("not1on.png");
        imageActive = listeImages.get(0);

        cotesConnectes[0] = 0;
        cotesConnectes[1] = 1;
        cotesConnectes[2] = 0;
        cotesConnectes[3] = -1;
    }

    @Override
    protected void calculerCourant() {
        if (noeudEntreeA == null) {
            actif = true;
            System.out.println("actif");
        } else {
            actif = !noeudEntreeA.isActif();
        }
    }

    @Override
    public void ajouterNoeudEntree(Noeud noeud) {
        System.out.println("ajout");
        if (noeudEntreeA == null) {
            noeudEntreeA = noeud;
        } else {
            System.out.println("Erreur: Logic Gate mal connectée");
        }
    }
}
