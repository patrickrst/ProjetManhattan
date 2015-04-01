/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.sim.projetmanhattan.view;

import ca.qc.bdeb.sim.projetmanhattan.controller.Controleur;
import ca.qc.bdeb.sim.projetmanhattan.model.Circuit;
import ca.qc.bdeb.sim.projetmanhattan.model.Ground;
import ca.qc.bdeb.sim.projetmanhattan.model.Noeud;
import ca.qc.bdeb.sim.projetmanhattan.model.Resistance;
import ca.qc.bdeb.sim.projetmanhattan.model.SourceCourant;
import ca.qc.bdeb.sim.projetmanhattan.model.SourceFEM;

/**
 *
 * @author blood_000
 */
public class CircuitGraphique {

    private Connectable[][] connectables;
    private boolean[][] connectablesPasses;
    private Circuit circuit;
    private Controleur controleur;

    public CircuitGraphique(Circuit circuit, Controleur controleur) {
        this.circuit = circuit;
        this.controleur = controleur;
    }

    public void preparerAnalyse() {
        this.connectables = connectables;
        connectablesPasses = new boolean[this.connectables.length][this.connectables[0].length];
        creerLiens();
    }

    public void creerLiens() {
        for (int i = 0; i < connectables.length; ++i) {
            for (int j = 1; j < connectables[i].length; ++j) {
                if (!connectablesPasses[i][j]) {
                    connectablesPasses[i][j] = true;
                    if (connectables[i][j] instanceof FilAbstrait) {
                        Noeud noeud = new Noeud();
                        retournerEnfants((FilAbstrait) connectables[i][j], i, j, noeud);
                        circuit.ajouterNoeud(noeud);
                    }
                }
            }
        }
    }

    public void gererLienDetecte(int i, int j, Noeud noeud) {
        if (connectables[i][j] instanceof ResistanceGraphique) {
            noeud.getResistances().add((Resistance) ((ResistanceGraphique) connectables[i][j]).getEnfant());
        } else if (connectables[i][j] instanceof SourceCourantGraphique) {
            noeud.getSourcesCourant().add((SourceCourant) ((SourceCourantGraphique) connectables[i][j]).getEnfant());
        } else if (connectables[i][j] instanceof SourceFEMGraphique) {
            if (connectables[i][j].getCotesConnectes()[2] == -1) {
                noeud.getSourcesFEMNeg().add((SourceFEM) ((SourceFEMGraphique) connectables[i][j]).getEnfant());
            } else {
                noeud.getSourcesFEMPos().add((SourceFEM) ((SourceFEMGraphique) connectables[i][j]).getEnfant());
            }
        } else if (connectables[i][j] instanceof FilAbstrait) {
            noeud.getFils().add((FilAbstrait) connectables[i][j]);
            retournerEnfants(((FilAbstrait) connectables[i][j]), i, j, noeud);
        } else if (connectables[i][j] instanceof GroundGraphique) {
            noeud.setGround((Ground) ((GroundGraphique) connectables[i][j]).getEnfant());
        }
        connectablesPasses[i][j] = false;
    }

    public void retournerEnfants(FilAbstrait fil, int i, int j, Noeud noeud) {

        if (fil.getCotesConnectes()[0] == 1 && i > 0 && connectables[i - 1][j].getCotesConnectes()[2] != 0) {
            gererLienDetecte(i - 1, j, noeud);
        }
        if (fil.getCotesConnectes()[1] == 1 && j < connectables[i].length - 1 && connectables[i][j + 1].getCotesConnectes()[3] != 0) {
            gererLienDetecte(i, j + 1, noeud);
        }
        if (fil.getCotesConnectes()[2] == 1 && i < connectables.length - 1 && connectables[i + 1][j].getCotesConnectes()[0] != 0) {
            gererLienDetecte(i + i, j, noeud);
        }
        if (fil.getCotesConnectes()[3] == 1 && j > 0 && connectables[i][j - 1].getCotesConnectes()[1] != 0) {
            gererLienDetecte(i, j - 1, noeud);
        }
    }

}
