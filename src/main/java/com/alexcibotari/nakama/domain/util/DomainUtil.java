package com.alexcibotari.nakama.domain.util;

import com.alexcibotari.nakama.domain.generic.IdDomain;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class DomainUtil {
    public static Collection<Long> getIds(Collection<? extends IdDomain> idDomains) {
        if (null == idDomains || idDomains.isEmpty()) {
            return null;
        }
        List<Long> rs = idDomains.stream().map(IdDomain::getId).collect(Collectors.toList());
        return rs;
    }

    public static Long getId(IdDomain idDomain) {
        return null == idDomain ? null : idDomain.getId();
    }
}
