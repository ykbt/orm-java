package com.ykbt.orm.repository;

import com.ykbt.jooq.tables.records.UserRecord;
import com.ykbt.orm.entity.UserEntity;
import org.jooq.DSLContext;
import org.jooq.UpdateConditionStep;
import org.jooq.types.UInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.ykbt.jooq.tables.User.USER;

@Repository
public class JooqRepository {

    private Logger log = LoggerFactory.getLogger(JooqRepository.class);

    @Autowired
    private DSLContext dsl;

    public List<UserEntity> findAll() {
        UserRecord[] records = dsl
                .selectFrom(USER)
                .fetchArray();

        return convertToEntityList(Arrays.stream(records));
    }

    public UserEntity get(int id) {
        UserRecord record = dsl
                .selectFrom(USER)
                .where(USER.ID.eq(UInteger.valueOf(id)))
                .fetchAny();

        return convertToEntity(record);
    }


    public int save(UserEntity entity) {
        if (entity == null && entity.getId() != null) {
            throw new IllegalArgumentException("insertに失敗しました、Entityにはidを含めないでください");
        }

        UserRecord record = dsl
                .insertInto(USER, USER.FIRST_NAME, USER.LAST_NAME)
                .values(entity.getFirstName(), entity.getLastName())
                .returning(USER.ID)
                .fetchOne();

        return record.getId().intValue();
    }

    public void update(UserEntity entity) {
        if (entity == null && entity.getId() == null) {
            throw new IllegalArgumentException("updateに失敗しました、Entityにはidを必ず含めてください");
        }

        UpdateConditionStep<UserRecord> step = createUpdate(entity);

        step.execute();
    }

    public void delete(int id) {
        dsl
                .delete(USER)
                .where(USER.ID.eq(UInteger.valueOf(id)))
                .execute();
    }

    private UserEntity convertToEntity(UserRecord record) {
        if (record == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        entity.setId(record.getId().intValue());
        entity.setFirstName(record.getFirstName());
        entity.setLastName(record.getLastName());

        return entity;
    }

    private List<UserEntity> convertToEntityList(Stream<UserRecord> stream) {
        List<UserEntity> list = new ArrayList<>();

        stream.forEach(record -> list.add(convertToEntity(record)));

        return list;
    }

    private UpdateConditionStep<UserRecord> createUpdate(UserEntity entity) {
        if (entity.getFirstName() == null && entity.getLastName() == null) {
            throw new IllegalArgumentException("updateに失敗しました、EntityにはFirstName LastNameのどちらかを含めてください" + entity.toString());
        } else if (entity.getFirstName() != null && entity.getLastName() == null) {
            return dsl
                    .update(USER)
                    .set(USER.FIRST_NAME, entity.getFirstName())
                    .where(USER.ID.eq(UInteger.valueOf(entity.getId())));
        } else if (entity.getFirstName() == null && entity.getLastName() != null) {
            return dsl
                    .update(USER)
                    .set(USER.LAST_NAME, entity.getLastName())
                    .where(USER.ID.eq(UInteger.valueOf(entity.getId())));
        }

        return dsl.update(USER)
                .set(USER.FIRST_NAME, entity.getFirstName())
                .set(USER.LAST_NAME, entity.getLastName())
                .where(USER.ID.eq(UInteger.valueOf(entity.getId())));
    }
}
