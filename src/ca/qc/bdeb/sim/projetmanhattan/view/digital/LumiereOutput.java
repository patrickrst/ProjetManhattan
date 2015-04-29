/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.sim.projetmanhattan.view.digital;

import ca.qc.bdeb.sim.projetmanhattan.model.mixte.Noeud;
import ca.qc.bdeb.sim.projetmanhattan.view.mixte.Connectable;
import ca.qc.bdeb.sim.projetmanhattan.view.mixte.TypeComposant;
import javafx.scene.image.Image;

/**
 *
 * @author blood_000
 */
public class LumiereOutput extends Connectable implements ComposantDigital {

    protected Noeud noeudEntree;
    
    private boolean actif;
    private transient Image lightOn;
    private transient Image lightOff;

    public LumiereOutput() {
        super(TypeComposant.LUMIERE_OUTPUT);
        cotesConnectes[0] = 1;
        
        actif = false;
        lightOn = new Image("file:src/ca/qc/bdeb/sim/projetmanhattan/view/img/lightOn.png");
        lightOff = new Image("file:src/ca/qc/bdeb/sim/projetmanhattan/view/img/lightOff.png");
    }

    @Override
    public void updateActif() {
        if (noeudEntree.isActif()) {

        } else {

        }

    }

    @Override
    public void ajouterNoeudEntree(Noeud noeud) {
        this.noeudEntree = noeud;
    }

    @Override
    public void ajouterNoeudSortie(Noeud noeud) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    public void switchOn() {
        actif = true;
    }
    
    public void switchOff() {
        actif = false;
    }
    
    public Image getImage() {
        if (actif == true) {
            return lightOn;
        } else {
            return lightOff;
        }
    }
    
}
