package ca.qc.bdeb.sim.projetmanhattan.view.digital;

import ca.qc.bdeb.sim.projetmanhattan.model.mixte.Noeud;
import ca.qc.bdeb.sim.projetmanhattan.view.mixte.ImageChangeable;
import ca.qc.bdeb.sim.projetmanhattan.view.mixte.TypeComposant;

/**
 *
 * @author Marc-Antoine Lalonde
 * @author Patrick Richer St-Onge
 */
public class LumiereOutput extends ImageChangeable implements ComposantDigital {

    private Noeud noeudEntree;

    /**
     * Initialise l'instance de la classe et ses variables si nécessaire.
     */
    public LumiereOutput() {
        super(TypeComposant.LUMIERE_OUTPUT);
        cotesConnectes[3] = 1;

        addImageActif("lightOn.png");
        addImage("lightOff.png");
        imageActive = listeImages.get(0);
    }

    @Override
    public void updateActif() {
        actif = noeudEntree.isActif();
    }

    @Override
    public void ajouterNoeudEntree(Noeud noeud) {
        this.noeudEntree = noeud;
    }

    @Override
    public void ajouterNoeudSortie(Noeud noeud) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isActif() {
        return actif;
    }

}
