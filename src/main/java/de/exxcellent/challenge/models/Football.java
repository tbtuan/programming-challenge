package de.exxcellent.challenge.models;

import com.opencsv.bean.CsvBindByName;

public class Football {
    @CsvBindByName(column = "Team", required = true)
    private String team;
    @CsvBindByName(column = "Goals", required = true)
    private int goals;
    @CsvBindByName(column = "Goals Allowed", required = true)
    private int goalsAllowed;

    public void setTeam(String team) {
        this.team = team;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public void setGoalsAllowed(int goalsAllowed) {
        this.goalsAllowed = goalsAllowed;
    }

    public String getTeam() {
        return team;
    }

    public int getGoals() {
        return goals;
    }

    public int getGoalsAllowed() {
        return goalsAllowed;
    }

    public int getGoalSpread() {
        return Math.abs(goals - goalsAllowed);
    }
}
