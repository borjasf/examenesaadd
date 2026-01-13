package tablones.repositorio;

import repositorio.RepositorioJPA;
import tablones.modelo.Tablon;

public class RepositorioTablonesJPA extends RepositorioJPA<Tablon> {

	@Override

	public Class<Tablon> getClase() {

		return Tablon.class;

	}

}
