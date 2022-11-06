package fr.hoenheimsports.gestionclub.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.PositiveOrZero;

/**
 * @author bbseb
 * @version 1.0
 * @created 12-oct.-2022 23:22:50
 */
@Embeddable
@Data
@NoArgsConstructor
public class Score {

    @PositiveOrZero(message = "Le score ne peut etre négatif")
    @Column(nullable = false)
    private int scoreHomeTeam;
    @PositiveOrZero(message = "Le score ne peut etre négatif")
    @Column(nullable = false)
    private int scoreVisitingTeam;

    public Score(int scoreHomeTeam, int scoreVisitingTeam) {
        this.scoreHomeTeam = scoreHomeTeam;
        this.scoreVisitingTeam = scoreVisitingTeam;
    }

    public Score(String scoreHomeTeam, String scoreVisitingTeam) throws NumberFormatException {

        if (scoreHomeTeam == null || scoreHomeTeam.isBlank()
                || scoreVisitingTeam == null || scoreVisitingTeam.isBlank()) {
            this.scoreHomeTeam = 0;
            this.scoreVisitingTeam = 0;
        } else {
            this.scoreHomeTeam = Integer.parseInt(scoreHomeTeam);
            this.scoreVisitingTeam = Integer.parseInt(scoreVisitingTeam);
        }
    }
}