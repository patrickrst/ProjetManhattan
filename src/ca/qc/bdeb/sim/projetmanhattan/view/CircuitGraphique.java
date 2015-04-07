/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.sim.projetmanhattan.view;

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

    public CircuitGraphique(Circuit circuit) {
        this.circuit = circuit;
    }

    public void preparerAnalyse(Connectable[][] connectables) {
        this.connectables = connectables;
        circuit.wipe();
        connectablesPasses = new boolean[this.connectables.length][this.connectables[0].length];
        for (int i = 0; i < connectables.length; ++i) {
            for (int j = 0; j < connectables[i].length; ++j) {
                if (connectables[i][j] instanceof ResistanceGraphique) {
                    circuit.ajouterResistance((Resistance) ((ResistanceGraphique) connectables[i][j]).getEnfant());
                } else if (connectables[i][j] instanceof SourceCourantGraphique) {
                    circuit.ajouterSourceCourant((SourceCourant) ((SourceCourantGraphique) connectables[i][j]).getEnfant());
                } else if (connectables[i][j] instanceof SourceFEMGraphique) {
                    circuit.ajouterSourceFEM((SourceFEM) ((SourceFEMGraphique) connectables[i][j]).getEnfant());
                } else if (connectables[i][j] instanceof GroundGraphique) {
                    circuit.ajouterGround((Ground) ((GroundGraphique) connectables[i][j]).getEnfant());
                }
            }
        }
        creerLiens();
    }

    public void creerLiens() {
        for (int i = 0; i < connectables.length; ++i) {
            for (int j = 0; j < connectables[i].length; ++j) {
                if (connectables[i][j] instanceof FilAbstrait && !connectablesPasses[i][j]) {

                    connectablesPasses[i][j] = true;
                    System.out.println(" - " + i + "," + j + "  " + connectables[i][j] + connectablesPasses[i][j]);
                    Noeud noeud = new Noeud();
                    noeud.ajouterFil((FilAbstrait) connectables[i][j]);
                    retournerEnfants((FilAbstrait) connectables[i][j], i, j, noeud);
                    circuit.ajouterNoeud(noeud);
                }
            }
        }
    }

    public void gererLienDetecte(int i, int j, Noeud noeud, int origine) {
        if (connectables[i][j] instanceof FilAbstrait) {

            connectablesPasses[i][j] = true;
            System.out.println(i + "," + j + "  " + connectables[i][j] + connectablesPasses[i][j]);
            noeud.ajouterFil((FilAbstrait) connectables[i][j]);
            retournerEnfants(((FilAbstrait) connectables[i][j]), i, j, noeud);

        } else if (connectables[i][j] instanceof ResistanceGraphique) {
            noeud.getResistances().add((Resistance) ((ResistanceGraphique) connectables[i][j]).getEnfant());
        } else if (connectables[i][j] instanceof SourceCourantGraphique) {
            noeud.getSourcesCourant().add((SourceCourant) ((SourceCourantGraphique) connectables[i][j]).getEnfant());
        } else if (connectables[i][j] instanceof SourceFEMGraphique) {
            if (connectables[i][j].getCotesConnectes()[origine] == -1) { // PROBLEME TROUVE??
                System.out.println("source neg");
                noeud.getSourcesFEMNeg().add((SourceFEM) ((SourceFEMGraphique) connectables[i][j]).getEnfant());
            } else {
                System.out.println("source pos");
                noeud.getSourcesFEMPos().add((SourceFEM) ((SourceFEMGraphique) connectables[i][j]).getEnfant());
            }
        } else if (connectables[i][j] instanceof GroundGraphique) {
            noeud.setGround((Ground) ((GroundGraphique) connectables[i][j]).getEnfant());
        }

    }

    public void retournerEnfants(FilAbstrait fil, int i, int j, Noeud noeud) {

        if (fil.getCotesConnectes()[0] == 1 && i > 0 && connectables[i - 1][j] != null && connectables[i - 1][j].getCotesConnectes()[2] != 0 && !connectablesPasses[i - 1][j]) {
            System.out.println("*****");
            System.out.println("Case: (" + i + "," + j + ")");
            System.out.println("Enfant: (" + (i - 1) + "," + j + ")");
            System.out.println("*****");
            gererLienDetecte((i - 1), j, noeud, 2);

        }
        if (fil.getCotesConnectes()[1] == 1 && j < connectables[i].length - 1 && connectables[i][j + 1] != null && connectables[i][j + 1].getCotesConnectes()[3] != 0 && !connectablesPasses[i][j + 1]) {
            System.out.println("*****");
            System.out.println("Case: (" + i + "," + j + ")");
            System.out.println("Enfant: (" + (i) + "," + (j + 1) + ")");
            System.out.println("*****");
            gererLienDetecte(i, (j + 1), noeud, 3);
        }
        if (fil.getCotesConnectes()[2] == 1 && i < connectables.length - 1 && connectables[i + 1][j] != null && connectables[i + 1][j].getCotesConnectes()[0] != 0 && !connectablesPasses[i + 1][j]) {
            System.out.println("*****");
            System.out.println("Case: (" + i + "," + j + ")");
            System.out.println("Enfant: (" + (i + 1) + "," + j + ")");
            System.out.println("*****");
            gererLienDetecte((i + i), j, noeud, 0);
        }
        if (fil.getCotesConnectes()[3] == 1 && j > 0 && connectables[i][j - 1] != null && connectables[i][j - 1].getCotesConnectes()[1] != 0 && !connectablesPasses[i][j - 1]) {

            System.out.println("*****");
            System.out.println("Case: (" + i + "," + j + ")");
            System.out.println("Enfant: (" + (i) + "," + (j - 1) + ")");
            System.out.println("*****");
            gererLienDetecte(i, (j - 1), noeud, 1);
        }
    }

}
