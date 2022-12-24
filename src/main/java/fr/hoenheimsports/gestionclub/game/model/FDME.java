package fr.hoenheimsports.gestionclub.game.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author bbseb
 * @version 1.0
 * @created 12-oct.-2022 23:22:50
 */
@Embeddable
@Data
@NoArgsConstructor
public class FDME {

    private URL url;

    public FDME(URL url) {
        this.url = url;
    }

    public FDME(String url) throws MalformedURLException {
        if (url == null || url.isBlank()) {
            this.url = null;
        } else {
            this.url = new URL(url);
        }
    }
}