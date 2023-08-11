package de.exxcellent.challenge.controllers;

import de.exxcellent.challenge.models.Football;
import de.exxcellent.challenge.services.FootballService;

import java.util.List;

public class FootballController {

    private final FootballService footballService;

    public FootballController(FootballService footballService) {
        this.footballService = footballService;
    }

    public String retrieveTeamWithSmallestGoalSpread() {
        if (footballService == null) {
            return "No team found";
        }
        List<Football> footballList = this.footballService.getFootballs();
        Football footballSmallestGoalDistance = this.footballService.getSmallestGoalSpread(footballList);
        if (footballSmallestGoalDistance == null) {
            return "No team found";
        }
        return footballSmallestGoalDistance.getTeam();
    }

}
