/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.sim.projetmanhattan.view.digital;


import ca.qc.bdeb.sim.projetmanhattan.view.mixte.TypeComposant;
import javafx.scene.image.Image;


/**
 *
 * @author blood_000
 */

public class ANDGate extends LogicGateAbstraite{

    public ANDGate() {
        super(TypeComposant.ANDGATE);
        
        addImage("and1.png");
        addImage("and2.png");
        addImage("and3.png");
        
        addImageActif("and1on.png");
        addImageActif("and2on.png");
        addImageActif("and3on.png");    
        imageActive = listeImages.get(0);
    }
    

    @Override
    public void calculerCourant() {
        actifTemp = actif;
        actif = noeudEntreeA != null && noeudEntreeA.isActif() && noeudEntreeB != null && noeudEntreeB.isActif();

        transfererCourant();
    }

    



}
