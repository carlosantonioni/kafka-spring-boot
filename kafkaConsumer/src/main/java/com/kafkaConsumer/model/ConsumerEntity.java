package com.kafkaConsumer.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@EqualsAndHashCode
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class ConsumerEntity {

    @Id
    @SequenceGenerator(
            name = "sequence_id",
            sequenceName = "sequence_id",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "sequence_value",
            strategy = GenerationType.AUTO
    )
    private Long id;

    @Nullable
    private Integer key;

    @NotNull
    private String value;

}
