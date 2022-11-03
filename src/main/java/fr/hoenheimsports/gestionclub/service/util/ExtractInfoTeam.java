package fr.hoenheimsports.gestionclub.service.util;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Getter
@NoArgsConstructor
@Service
@Scope("prototype")
public class ExtractInfoTeam {
    final static String REGEX = "([-\\/a-zA-Z ]+)(?: (?:(?:(?:(?:U|-)(\\d{2})(?:M|F))|(?:SM|SF)|)(\\d?))?|(?>\\(.*\\))?$)";
    /*
        *** REGEX ***
        1 groupe : Le nom de léquipe,
        2 groupe : catégorie ou null pour les sénior
        3 groupe : le numéro d'équipe au null

        Le groupe 1 est eronné si NOM CLUB SM ( ou SF), mais il semble qu'il ai tjs un numéro d'équipe pour les sénior

        Cas particulier, si il n'y a pas de catégorie, c'est soit SF soit SM et tjs l'équipe 1.

     */
    private static Pattern pattern = Pattern.compile(REGEX);

    @Setter
    private String csvTeamName;
    @Setter
    private String csvNumPool;
    private String clubName;
    private String categoryName;
    private String categoryShortName;
    private int teamNumber;
    private char gender;





    public void extract() {
        if (this.csvTeamName == null || this.csvTeamName.isBlank()) {
            throw new RuntimeException("Le nom du club n'est pas bon");
        }
        this.gender = this.csvNumPool.charAt(0);
        //Z est considéré comme mixte, mais en réalité, les équipes sont nommées M à chaque fois.
        if (this.gender == 'Z')
            this.gender = 'M';
        Matcher matcher = pattern.matcher(this.csvTeamName.trim());
        if (matcher.matches()) {
            this.clubName = matcher.group(1);
            //On récupère la catégorie des moins de [age], [age] => groupe 2
            String category = matcher.group(2);
            if (category != null) {
                this.categoryName = "moins de " + category + " ans";
                this.categoryShortName = "-" + category;
            } else {
                this.categoryName = "senior";
                this.categoryShortName = "S";
            }

            String teamNumber = matcher.group(3);
            if (teamNumber == null || teamNumber.isBlank()) {
                this.teamNumber = 1;


            } else {
                this.teamNumber = Integer.parseInt(teamNumber);
            }
        } else {

            throw new RuntimeException("Erreur d'extraction pour : |" + this.csvTeamName + "|");
        }


    }
}