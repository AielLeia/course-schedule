package com.ismaelmohamedbouh;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution {

    public boolean canFinish(int nombreDeCours, int[][] prerequis) {
        List<List<Integer>> graphe = construireGraphe(nombreDeCours, prerequis);
        int[] degresEntrants = calculerDegresEntrants(nombreDeCours, prerequis);

        Deque<Integer> file = initialiserFile(nombreDeCours, degresEntrants);

        int coursTraites = traiterCours(graphe, degresEntrants, file);

        return coursTraites == nombreDeCours;
    }

    private List<List<Integer>> construireGraphe(int nombreDeCours, int[][] prerequis) {
        List<List<Integer>> graphe = new ArrayList<>();
        for (int i = 0; i < nombreDeCours; i++) {
            graphe.add(new ArrayList<>());
        }
        for (int[] prerequi : prerequis) {
            int cours = prerequi[0];
            int coursPrerequi = prerequi[1];
            graphe.get(coursPrerequi).add(cours);
        }
        return graphe;
    }

    private int[] calculerDegresEntrants(int nombreDeCours, int[][] prerequis) {
        int[] degresEntrants = new int[nombreDeCours];
        for (int[] prerequi : prerequis) {
            int cours = prerequi[0];
            degresEntrants[cours]++;
        }
        return degresEntrants;
    }

    private Deque<Integer> initialiserFile(int nombreDeCours, int[] degresEntrants) {
        Deque<Integer> file = new ArrayDeque<>();
        for (int i = 0; i < nombreDeCours; i++) {
            if (degresEntrants[i] == 0) {
                file.offer(i);
            }
        }
        return file;
    }

    private int traiterCours(List<List<Integer>> graphe, int[] degresEntrants, Deque<Integer> file) {
        int coursTraites = 0;
        while (!file.isEmpty()) {
            int cours = file.poll();
            coursTraites++;
            for (int voisin : graphe.get(cours)) {
                degresEntrants[voisin]--;
                if (degresEntrants[voisin] == 0) {
                    file.offer(voisin);
                }
            }
        }
        return coursTraites;
    }
}
