package br.com.strixcloud.lstop.entities.util.order.impl;


import br.com.strixcloud.lstop.entities.util.order.Orderable;
import lombok.Getter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrderByDESC {

    @Getter
    public static final OrderByDESC instance = new OrderByDESC();

    public List<Orderable> order(List<Orderable> list) {
        return list.stream()
                .sorted(Comparator.comparing(Orderable::getValue))
                .collect(Collectors.toList());
    }

}
