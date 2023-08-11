package de.exxcellent.challenge.services;

import de.exxcellent.challenge.models.Football;
import de.exxcellent.challenge.readers.Reader;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class FootballService {
    Reader<Football> footballReader;

    public FootballService(Reader<Football> footballReader) {
        this.footballReader = footballReader;
    }

    public List<Football> getFootballs() {
        if (footballReader == null) {
            return new LinkedList<>();
        }
        return footballReader.readAll();
    }

    public Football getSmallestGoalSpread(List<Football> footballList) {
        if (footballList == null || footballList.isEmpty()) {
            return null;
        }
        Comparator<Football> footballComparator = Comparator.comparing(Football::getGoalSpread);
        Football footballSmallestGoalDistance = Collections.min(footballList, footballComparator);
        return footballSmallestGoalDistance;
    }

}
