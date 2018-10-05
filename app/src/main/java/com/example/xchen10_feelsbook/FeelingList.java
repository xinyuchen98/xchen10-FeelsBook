package com.example.xchen10_feelsbook;

import java.util.ArrayList;
import java.util.Collection;

public class FeelingList extends ArrayList{

    protected ArrayList<Feeling> feelingList;

    public FeelingList() {
        feelingList = new ArrayList<Feeling>();
    }

    public Collection<Feeling> getFeelings() {
        return feelingList;
    }

    public void addFeeling(Feeling feeling) {
        feelingList.add(feeling);
    }

    public void deleteFeeling(Feeling feeling) {
        feelingList.remove(feeling);
    }
}
