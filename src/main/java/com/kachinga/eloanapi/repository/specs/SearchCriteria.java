package com.kachinga.eloanapi.repository.specs;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchCriteria {
    private String key;
    private Object value;
    private SearchOperation operation;
}
