package com.thc.realspr.id;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserSubjectId implements Serializable {
    private String userId;
    private int subjectId;
}
