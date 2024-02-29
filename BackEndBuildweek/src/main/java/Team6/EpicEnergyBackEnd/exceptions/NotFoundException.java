package Team6.EpicEnergyBackEnd.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
	public NotFoundException(UUID id) {
		super("L'Elemento con id " + id + " non è stato trovato");
	}
	public NotFoundException(String message) {
		super(message);
	}

}
