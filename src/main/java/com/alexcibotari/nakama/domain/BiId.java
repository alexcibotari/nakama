package com.alexcibotari.nakama.domain;

import java.io.Serializable;

public interface BiId<FPK,SPK> extends Serializable{

    FPK getFirstId();

    SPK getSecondId();

    int hashCode();

    boolean equals(Object obj);
}
