/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * not open the template in the editor.
 */
package ca.qc.bdeb.sim.projetmanhattan.view.digital;

import ca.qc.bdeb.sim.projetmanhattan.model.mixte.Noeud;
import ca.qc.bdeb.sim.projetmanhattan.view.mixte.TypeComposant;

/**
 *
 * @author blood_000
 */
public class NOTGate extends LogicGateAbstraite  {


    
    public NOTGate() {
        super(TypeComposant.NOTGATE);
        addImage("not1.png");
        addImage("not2.png");
        addImage("not3.png");
        
        addImageActif("not1on.png");
        addImageActif("not2on.png");
        addImageActif("not3on.png");  
        imageActive = listeImages.get(0);
    }

    @Override
    public void calculerCourant() {
        actifTemp = actif;
        if (noeudEntreeA == null) {
            actif = true;
        } else {
            actif = noeudEntreeA.isActif();
        }
        transfererCourant();
    }

    @Override
    public void ajouterNoeudEntree(Noeud noeud) {
        if (noeudEntreeA == null) {
            noeudEntreeA = noeud;
        } else {
            System.out.println("Erreur: Logic Gate mal connectée");
        }
    }
}
