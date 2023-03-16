package br.com.erpsystem.mscliente.mapper;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DateMapper {

    public Timestamp asTimeStamp(LocalDate localDate){
        return Timestamp.valueOf(localDate.atStartOfDay());
    }

    public LocalDate asLocalDate(Timestamp timestamp){
        return timestamp.toLocalDateTime().toLocalDate();
    }

    public Timestamp asTimeStampDateTime(LocalDateTime localDateTime){
        return Timestamp.valueOf(localDateTime);
    }

    public LocalDateTime asLocalDateTime(Timestamp timestamp){
        return timestamp.toLocalDateTime();
    }


}
