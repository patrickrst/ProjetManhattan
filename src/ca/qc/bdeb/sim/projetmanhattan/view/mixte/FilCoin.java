/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.sim.projetmanhattan.view.mixte;

import ca.qc.bdeb.sim.projetmanhattan.view.mixte.FilAbstrait;

/**
 *
 * @author blood_000
 */
public class FilCoin extends FilAbstrait {

    public FilCoin() {
        super();
        cotesConnectes[0] = 1;
        cotesConnectes[1] = 1;

    }

    @Override
    public String toString() {
        return "FilCoin";
    }
}
