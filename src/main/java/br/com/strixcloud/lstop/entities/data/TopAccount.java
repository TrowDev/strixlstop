package br.com.strixcloud.lstop.entities.data;

import br.com.strixcloud.lstop.entities.util.order.Orderable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class TopAccount implements Orderable {

    private String player;
    private double value;

}
