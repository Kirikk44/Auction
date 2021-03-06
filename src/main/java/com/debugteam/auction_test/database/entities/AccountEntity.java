package com.debugteam.auction_test.database.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid") // генерация id
    @Column(length = 32, updatable = false, nullable = false)
    private String id; // primary key in data base

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP") // генерация даты
    private LocalDateTime addDate;

    @Generated(GenerationTime.INSERT) // Серийник для человека.
    private Integer serial;

    private String nickname;
    private Integer money;

    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<LotEntity> userLots;

    ///////////////////////////////////////////////////////////////////////////
    //                          equals + hash
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(addDate, that.addDate) &&
               Objects.equals(serial, that.serial) &&
               Objects.equals(nickname, that.nickname) &&
               Objects.equals(money, that.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addDate, serial, nickname, money);
    }
}
