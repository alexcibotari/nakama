package com.alexcibotari.nakama.domain;

import java.io.Serializable;

public interface IdEntity<PK> extends Serializable {

    PK getId();
}
