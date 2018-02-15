package com.edusancon.wewac.bigbrother.model;

import com.edusancon.wewac.util.JsonWritable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Insurance extends JsonWritable {

    private BigDecimal amount;
    private Date creationDate;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
