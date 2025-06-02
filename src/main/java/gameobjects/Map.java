package gameobjects;

import java.util.HashMap;

import gameobjects.*;

public class Map {
    private static HashMap<String, Place> map;

    public void initMap(String[] args) {

        map = new HashMap<String, Place>();

        // Add Places to the map
        // map.put(key, new Place(name, desc, n, s, e, w));
        map.put("casa", new Place("minha casa", "Onde eu moro, foi construida pelo meu pai.", 1, -1, 1, -1));
        map.put("oficina", new Place("oficina", "Onde se encontram as ferramentas.", 1, -1, 2, 0));
    }
}