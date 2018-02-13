package com.alexcibotari.nakama.domain.util;

import com.alexcibotari.nakama.domain.IdEntity;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class EntityUtil {

  public static Collection<Long> getIds(Collection<? extends IdEntity<Long>> idEntity) {
    if (null == idEntity || idEntity.isEmpty()) {
      return null;
    }
    List<Long> rs = idEntity.stream().map(IdEntity::getId).collect(Collectors.toList());
    return rs;
  }

  public static Long getId(IdEntity<Long> idEntity) {
    return null == idEntity ? null : idEntity.getId();
  }
}
