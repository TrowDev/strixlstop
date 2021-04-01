package br.com.strixcloud.lstop.services.hologram.setup;

import br.com.strixcloud.lstop.data.AccountsDAO;
import br.com.strixcloud.lstop.entities.data.IPlacedHologram;
import br.com.strixcloud.lstop.entities.data.TopAccount;
import br.com.strixcloud.lstop.entities.util.order.Orderable;
import br.com.strixcloud.lstop.entities.util.order.impl.OrderByASC;
import br.com.strixcloud.lstop.provider.config.IDisplayProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.var;

import java.util.ArrayList;

@Data @AllArgsConstructor
public class HologramSetupService {

    private final IDisplayProvider displayProvider;

    public void execute(IPlacedHologram hologram) {
        hologram.clear();
        var holoData = displayProvider.getHologramData();
        holoData.getHeader().forEach(hologram::append);

        var toOrder = new ArrayList<Orderable>(AccountsDAO.getInstance().get());
        var accounts = OrderByASC.getInstance().order(toOrder);

        for (var obj : accounts) {
            var acc = (TopAccount) obj;
            var line = holoData.getContentValid()
                    .replace("@value", String.valueOf(obj.getValue()))
                    .replace("@player", acc.getPlayer());
            hologram.append(line);
        }

        holoData.getFooter().forEach(hologram::append);
    }

}
