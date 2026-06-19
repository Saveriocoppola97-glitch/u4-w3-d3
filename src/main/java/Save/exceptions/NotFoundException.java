package Save.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String id) {
        super ("il record con id " + id + " non è stato trovato");
    }
}
