package com.api.util;

import com.api.entities.QuestaoAlternativa;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConversaoDeData {

    public static Timestamp localDateTimeToTimestamp(LocalDateTime data) {
        return Timestamp.valueOf(data);
    }

    public static LocalDateTime timestampToLocalDateTime(Timestamp data) {
        return data.toLocalDateTime();
    }
    // public static Array listToArray(ArrayList<> lqa) {
    public static QuestaoAlternativa[] listToArray(List<QuestaoAlternativa> lqa) {
        QuestaoAlternativa[] aqa = new QuestaoAlternativa[lqa.size()];
        for(int i = 0; i < lqa.size(); i++)
            aqa[i] = lqa.get(i);
        return aqa;
    }
    //Opa remor, tava tentando fazer funcionar no Daos
    public static List<QuestaoAlternativa> arrayToList(QuestaoAlternativa[] aqa) {
        List<QuestaoAlternativa> lqa = new ArrayList<QuestaoAlternativa>();
        for(int i = 0; i < aqa.length; i++)
            lqa.add(aqa[i]);
        return lqa;
    }
}
