package br.com.strixcloud.lstop.entities.util.order.impl;

import br.com.strixcloud.lstop.entities.util.order.Orderable;
import lombok.Getter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrderByASC {

    @Getter
    public static final OrderByASC instance = new OrderByASC();

    public List<Orderable> order(List<Orderable> list) {
        return list.stream()
                .sorted(Comparator.comparing(Orderable::getValue).reversed())
                .collect(Collectors.toList());
    }

}
