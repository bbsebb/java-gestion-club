package fr.hoenheimsports.gestionclub.service.csvimport;

import fr.hoenheimsports.gestionclub.model.TeamColor;
import fr.hoenheimsports.gestionclub.model.TeamsColor;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class TeamsColorExtract {
    static public TeamsColor extract(String shirtColor, String goalkeeperColor) {
        try {
            return new TeamsColor(split(shirtColor)[0], split(shirtColor)[1], split(goalkeeperColor)[0], split(goalkeeperColor)[1]);
        }catch (RuntimeException e) {
            log.warn("erreur de couleur : " + e.getMessage());
            return new TeamsColor();
        }
    }

    static private TeamColor[] split(String colors) {

        TeamColor[] teamsColor = new TeamColor[2];

        String[] colorsStr = colors.toUpperCase().split("-");

        if(colorsStr.length == 1 && !colorsStr[0].isBlank()) {

            teamsColor[0] = TeamColor.getByFrenchName(colorsStr[0]);
            teamsColor[1] = null;
        } else if (colorsStr.length == 2 && !colorsStr[0].isBlank() && !colorsStr[1].isBlank()) {
            teamsColor[0] = TeamColor.getByFrenchName(colorsStr[0]);
            teamsColor[1] = TeamColor.getByFrenchName(colorsStr[1]);
        }

        return teamsColor;
    }
}
