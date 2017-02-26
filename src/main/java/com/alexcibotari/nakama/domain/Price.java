package com.alexcibotari.nakama.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Price {

    @Column(name = "price_currency_iso")
    private String currencyIso;

    @Column(name = "price_value")
    private BigDecimal value;
}
