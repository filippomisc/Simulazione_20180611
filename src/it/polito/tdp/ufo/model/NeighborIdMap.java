package it.polito.tdp.ufo.model;

import java.util.HashMap;
import java.util.Map;

public class NeighborIdMap {
	
	
private Map<Integer, Neighbor> map;
	
	public NeighborIdMap() {
		map = new HashMap<>();
	}
	
	public Neighbor get(int neighborID) {
		return map.get(neighborID);
	}
	
	public Neighbor get(Neighbor neighbor) {
		Neighbor old = map.get(neighbor.getId());
		if (old == null) {
			map.put(neighbor.getId(), neighbor);
			return neighbor;
		}
		return old;
	}
	
	public void put(Neighbor neighbor, int neigborID) {
		map.put(neigborID, neighbor);
	}

}
