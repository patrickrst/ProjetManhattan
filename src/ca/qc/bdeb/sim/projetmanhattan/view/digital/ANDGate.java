/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.sim.projetmanhattan.view.digital;

import ca.qc.bdeb.sim.projetmanhattan.view.analog.Composant;
import ca.qc.bdeb.sim.projetmanhattan.view.mixte.TypeComposant;

/**
 *
 * @author blood_000
 */
public class ANDGate extends LogicGateAbstraite implements ComposantDigital {

    public ANDGate() {
        super(TypeComposant.ANDGATE);
    }

    @Override
    public void calculerCourant() {
        actifTemp = actif;
        actif = noeudEntreeA != null && noeudEntreeA.isActif() && noeudEntreeB != null && noeudEntreeB.isActif();

        transfererCourant();
    }


}
