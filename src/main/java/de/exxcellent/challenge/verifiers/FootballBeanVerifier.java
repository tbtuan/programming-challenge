package de.exxcellent.challenge.verifiers;

import com.opencsv.bean.BeanVerifier;
import de.exxcellent.challenge.models.Football;

public class FootballBeanVerifier implements BeanVerifier<Football> {

    @Override
    public boolean verifyBean(Football football) {
        // Goals within boundaries
        boolean isValidGoal = football.getGoals() >= 0;
        // Goals within boundaries
        boolean isValidGoalAllowed = football.getGoalsAllowed() >= 0;
        return isValidGoal && isValidGoalAllowed;
    }
}
