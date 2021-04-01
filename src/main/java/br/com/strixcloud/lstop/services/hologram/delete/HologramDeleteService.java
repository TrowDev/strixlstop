package br.com.strixcloud.lstop.services.hologram.delete;

import br.com.strixcloud.lstop.provider.config.IStorageProvider;
import br.com.strixcloud.lstop.provider.hologram.IHologramProvider;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HologramDeleteService {

    private final IHologramProvider hologramProvider;
    private final IStorageProvider storageProvider;

    public void execute() {
        storageProvider.clear();
        hologramProvider.getHolograms().forEach(hologramProvider::delete);
    }

}
